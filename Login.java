package loginandsignup;

import java.time.Duration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.raven.main.AdminDashboard;
import Dashboard.UserDashboard;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.prefs.Preferences;

public class Login extends javax.swing.JFrame {
    
    
    private Timer lockoutTimer;
    private boolean isLockedOut = false;
    private Timer dbStatusTimer;
    private Timer loadingTimer;
    private int dotCount = 0;
    private int failedAttempts = 0;
private AtomicInteger lockoutTime = new AtomicInteger(0);
private int lockoutMultiplier = 1; // Track how many times user has been locked out

// Method to reset local lockout state (for admin unlock)
public void resetLocalLockoutState() {
    File lockoutFile = new File("lockout.txt");
    if (lockoutFile.exists()) {
        if (lockoutFile.delete()) {
            System.out.println("Lockout state file deleted successfully.");
        } else {
            System.out.println("Failed to delete lockout state file.");
        }
    } else {
        System.out.println("Lockout state file does not exist.");
    }
    // Reset in-memory state as well
    failedAttempts = 0;
    lockoutMultiplier = 1;
    browhy.setEnabled(true);
    browhy.setText("Login");
    lockoutLabel.setVisible(false);
}
private String userSerialNumber = null;

private static final String LOCKOUT_FILE = ".lockout_state.enc";
private static final String ENCRYPTION_KEY = "MySuperSecretKey"; // 16 chars for AES

private void saveLockoutState(int failedAttempts, LocalDateTime lockoutUntil) {
    try {
        Key aesKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(LOCKOUT_FILE), cipher);
             BufferedWriter writer = new BufferedWriter(new java.io.OutputStreamWriter(cos))) {
            writer.write("failedAttempts=" + failedAttempts);
            writer.newLine();
            writer.write("lockoutUntil=" + lockoutUntil.toString());
            writer.newLine();
            writer.write("lockoutMultiplier=" + lockoutMultiplier);
            writer.newLine();
            writer.write("userSerialNumber=" + (userSerialNumber != null ? userSerialNumber : ""));
            writer.newLine();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void loadLockoutState() {
    File file = new File("lockout.txt");
    if (!file.exists()) {
        return; // No previous lockout saved
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        int failedAttempts = 0;
        LocalDateTime lockoutUntil = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("failedAttempts=")) {
                failedAttempts = Integer.parseInt(line.substring("failedAttempts=".length()));
            } else if (line.startsWith("lockoutUntil=")) {
                lockoutUntil = LocalDateTime.parse(line.substring("lockoutUntil=".length()));
            } else if (line.startsWith("lockoutMultiplier=")) {
                lockoutMultiplier = Integer.parseInt(line.substring("lockoutMultiplier=".length()));
            }
        }

        // Now apply the lockout logic
        if (lockoutUntil != null && LocalDateTime.now().isBefore(lockoutUntil)) {
            // User is still locked out
            browhy.setEnabled(false);
            Duration remaining = Duration.between(LocalDateTime.now(), lockoutUntil);
            long secondsLeft = remaining.getSeconds();
            browhy.setText("Locked for " + secondsLeft + " seconds");
        } else if (failedAttempts >= 5) {
            // Permanently locked out condition (e.g., lockoutMultiplier exceeds threshold)
            if (lockoutMultiplier > 6) { // For example, after 6 lockouts (~1 hour+)
                browhy.setEnabled(false);
                JOptionPane optionPane = new JOptionPane(
                    "You are permanently locked out. Please contact the system administrator at admin@example.com",
                    JOptionPane.ERROR_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null,
                    new Object[]{},
                    null
                );
                JDialog dialog = optionPane.createDialog(this, "Permanent Lockout");
                dialog.setModal(true);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
            }
        }
    } catch (IOException | NumberFormatException | DateTimeParseException e) {
        e.printStackTrace();
    }
}
private void generateOrLoadUserSerialNumber() {
    String serialFilePath = "user_serial_number.txt";
    File serialFile = new File(serialFilePath);
    if (serialFile.exists()) {
        try {
            userSerialNumber = new String(Files.readAllBytes(Paths.get(serialFilePath))).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        userSerialNumber = UUID.randomUUID().toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(serialFilePath))) {
            writer.write(userSerialNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





    public Login() {
    initComponents();
    
     generateOrLoadUserSerialNumber();
    loadLockoutState();
    
    // Start DB Status Timer
        dbStatusTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConnectivityStatus();
            }
        });
        dbStatusTimer.start();

        // Set default button
        getRootPane().setDefaultButton(browhy);
        
     // Placeholder values so Java doesn't give red lines
String url = "jdbc:mysql://localhost:3307/hotel_reservation";
String dbUser = "your_db_username";
String dbPass = "your_db_password";
        
        // Start Loading Animation Timer
loadingTimer = new Timer(500, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        dotCount = (dotCount + 1) % 4; // 0 to 3
        String dots = "";
        for (int i = 0; i < dotCount; i++) {
            dots += ".";
        }
        connectivityLabel.setText("Checking connection" + dots);
    }
});
loadingTimer.start();
// Create a Timer to check every 5 seconds

// Create a Timer to check every 5 seconds
Timer connectivityChecker = new Timer(5000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPass);
            connectivityLabel.setText("Connected to Database");
            connectivityLabel.setForeground(new Color(0, 153, 0)); // Green
            connection.close();
        } catch (Exception ex) {
            connectivityLabel.setText("No Connection");
            connectivityLabel.setForeground(new Color(255, 0, 0)); // Red
        }
    }
});


connectivityChecker.setRepeats(true); // Keep checking every 5 seconds
connectivityChecker.start();


       

        // Focus movement
        emailTextField.addActionListener(evt -> {
            String email = emailTextField.getText().trim();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
                emailTextField.requestFocusInWindow();
            } else {
                jPasswordField1.requestFocusInWindow();
            }
        });

        jPasswordField1.addActionListener(evt -> {
            browhy.requestFocusInWindow();
            browhy.doClick();
        });

    

    }private int getNextLockoutTime(int currentTime) {
    // Increase lockout time incrementally (e.g., 10s -> 60s -> 3 minutes -> etc.)
    if (currentTime == 10) {
        return 60; // After 10 seconds, move to 1 minute
    } else if (currentTime == 60) {
        return 180; // After 1 minute, move to 3 minutes
    } else if (currentTime == 180) {
        return 300; // After 3 minutes, move to 5 minutes
    }
    return currentTime; // If time is too long, return as is (optional for extreme cases)
}
    

    private void updateConnectivityStatus() {
    try {
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3307/hotel_reservation", "Java", "java17"
        );
        connectivityLabel.setText("Connected");
        connectivityLabel.setForeground(new Color(0, 153, 0)); // Green
        connection.close();
    } catch (Exception e) {
        connectivityLabel.setText("No Connection");
        
       connectivityLabel.setForeground(new Color(255, 0, 0));
    }
    
    if (loadingTimer != null && loadingTimer.isRunning()) {
        loadingTimer.stop();
    }
}
    
    private void shakeWindow() {
    final int originalX = this.getLocationOnScreen().x;
    final int originalY = this.getLocationOnScreen().y;

    int shakeDistance = 10; // How far left-right
    int shakeTimes = 8;     // How many shakes
    int delay = 40;         // Milliseconds between shakes

    Timer timer = new Timer(delay, null);
    timer.addActionListener(new ActionListener() {
        int count = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            int offset = (count % 2 == 0) ? shakeDistance : -shakeDistance;
            setLocation(originalX + offset, originalY);
            count++;
            if (count >= shakeTimes) {
                timer.stop();
                setLocation(originalX, originalY); // Reset position
            }
        }
    });
    timer.start();
    }
    private void fallbackLogin(String username, String password) {
        if (isLockedOut) {
            JOptionPane.showMessageDialog(null, "Account locked! Please restart the app.", "Locked Out", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.equals("admin") && password.equals("password")) { 
            JOptionPane.showMessageDialog(null, "Login successful (Fallback Mode)!");
            new UserDashboard().setVisible(true); // or fallback dashboard
            dispose();
        } else {
            failedAttempts++;
            int attemptsLeft = 5 - failedAttempts;
            if (attemptsLeft > 0) {
                JOptionPane.showMessageDialog(null, "Wrong fallback credentials. You have " + attemptsLeft + " attempts left.");
            } else {
                isLockedOut = true;
                JOptionPane.showMessageDialog(null, "Too many failed attempts! You are locked out.");
            }
        }
    }


    @SuppressWarnings("unchecked")
    //real

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        browhy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        showpassword = new javax.swing.JCheckBox();
        RememberMe = new javax.swing.JCheckBox();
        connectivityLabel = new javax.swing.JLabel();
        lockoutLabel = new javax.swing.JLabel();
        userIdLabel = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(102, 102, 255));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Sitka Banner", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Eats");
        Right.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Script MT Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("copyright ©  All rights reserved");
        Right.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 190, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg_images/joseph-gonzalez-fdlZBWIP0aM-unsplash(1).jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        Right.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 570));

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 570);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Script MT Bold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LOGIN");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 51, -1, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Script MT Bold", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/mail.png"))); // NOI18N
        jLabel2.setText("Email");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, -1));

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(102, 102, 102));
        Left.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 350, 40));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Script MT Bold", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/pass.png"))); // NOI18N
        jLabel3.setText("Password");
        Left.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, -1));
        Left.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 261, 350, 40));

        browhy.setBackground(new java.awt.Color(0, 102, 102));
        browhy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        browhy.setForeground(new java.awt.Color(255, 255, 255));
        browhy.setText("Login");
        browhy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browhyActionPerformed(evt);
            }
        });
        Left.add(browhy, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 210, 50));

        jLabel4.setText("I don't have an account");
        Left.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Left.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 80, 20));

        showpassword.setText("Show Password");
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordActionPerformed(evt);
            }
        });
        Left.add(showpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        RememberMe.setText("Remeber Me");
        RememberMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RememberMeActionPerformed(evt);
            }
        });
        Left.add(RememberMe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 100, -1));
        Left.add(connectivityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 160, 30));

        lockoutLabel.setFont(new java.awt.Font("Segoe UI Black", 2, 10)); // NOI18N
        Left.add(lockoutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 110, 20));
        Left.add(userIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 150, 20));

        jPanel1.add(Left);
        Left.setBounds(470, 0, 510, 570);

        Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delivery-man.png"))); // NOI18N
        jPanel1.add(Icon);
        Icon.setBounds(400, 0, 64, 64);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        SignUp SignUpFrame = new SignUp();//this is creating a new instance bruh
        SignUpFrame.setVisible(true);//nah you surely know what this means
        SignUpFrame.pack();//Adjusts the size for all components to fit
        SignUpFrame.setLocationRelativeTo(null); //This is to centre the frame window to the screen bruh
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpasswordActionPerformed
   if (showpassword.isSelected()) {
            // Show the password
            jPasswordField1.setEchoChar((char) 0); // Shows characters
        } else {
            // Hide the password
            jPasswordField1.setEchoChar('*'); // Hides characters
        }

    }//GEN-LAST:event_showpasswordActionPerformed

    private void browhyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browhyActionPerformed
// Disable button and show "Logging in..."
browhy.setEnabled(false);
browhy.setText("Logging in...");// Retrieve what the user entered

String username = emailTextField.getText().trim();
String password = new String(jPasswordField1.getPassword()).trim();

boolean isValid = true;

// Validate email
if (username.isEmpty() || !username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    isValid = false;
    emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Red border for email
} else {
    emailTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Reset to normal border
}

// Validate password
if (password.isEmpty()) {
    isValid = false;
    jPasswordField1.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Red border for password
} else {
    jPasswordField1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Reset to normal border
}

// If validation failed, show the message and return
if (!isValid) {
    JOptionPane.showMessageDialog(null, "Please enter valid credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
    browhy.setEnabled(true);
    browhy.setText("Login");
    // Create a Timer to reset the border after a delay (e.g., 2 seconds)
    Timer timer = new Timer(3500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            emailTextField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Reset to normal border
            jPasswordField1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Reset to normal border
        }
    });
    timer.setRepeats(false); // The timer runs only once
    timer.start(); // Start the timer

    return; // Stop execution if validation fails
}

// Lockout logic implementation
if (failedAttempts >= 5) {
    int baseLockoutTime = 10; // 10 seconds for first lockout
    int currentLockoutTime = baseLockoutTime * lockoutMultiplier;

    final LocalDateTime lockoutUntil = LocalDateTime.now().plusSeconds(currentLockoutTime);
    saveLockoutState(failedAttempts, lockoutUntil);

    final AtomicInteger remainingTime = new AtomicInteger(currentLockoutTime);

    Timer lockoutTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            int timeLeft = remainingTime.get();
            if (timeLeft > 0) {
                browhy.setText("Locked (" + timeLeft + "s)");
                remainingTime.set(timeLeft - 1);
                browhy.setEnabled(false);
            } else {
                ((Timer) evt.getSource()).stop();
                browhy.setEnabled(true);
                browhy.setText("Login");
                failedAttempts = 4; // Allow 1 attempt after lockout
                lockoutLabel.setVisible(false);
                lockoutMultiplier++;
            }
        }
    });
    lockoutTimer.start();
} else {
    int attemptsLeft = 5 - failedAttempts;
    lockoutLabel.setVisible(true);
    lockoutLabel.setForeground(Color.ORANGE);
    lockoutLabel.setText("Attempts left: " + attemptsLeft);
}




// If validations pass, continue to database part
String url = "jdbc:mysql://localhost:3307/hotel_reservation";
String dbUser = "Java";
String dbPass = "java17";

final boolean[] loginSuccess = {false};
final String[] errorMessage = {""};
boolean databaseConnected = false;


try {
    Connection connection = DriverManager.getConnection(url, dbUser, dbPass);
    databaseConnected = true;

    String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();

if (resultSet.next()) {
    failedAttempts = 0; // Reset attempts only on successful login
    lockoutMultiplier = 1; // Reset lockout multiplier on successful login
    String role = resultSet.getString("role");

    if (role != null && role.equalsIgnoreCase("Admin")) {
        loginSuccess[0] = true;
        new AdminDashboard().setVisible(true);
    } else {
        new UserDashboard().setVisible(true);
    }
    dispose();
} else {
    failedAttempts++;
    JOptionPane.showMessageDialog(this, errorMessage[0], "Login Failed", JOptionPane.ERROR_MESSAGE);
browhy.setEnabled(true);
browhy.setText("Login");
    
    
   // --- START of Lockout Code ---

// --- START of Lockout Code ---

if (!databaseConnected) {
    boolean fallbackLoginSuccess = false;

    if (username.equals("admin@example.com") && password.equals("fallbackPassword")) {
        fallbackLoginSuccess = true;
        loginSuccess[0] = true;
        JOptionPane.showMessageDialog(null, "Fallback sequence initiated, Welcome Admin", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        new AdminDashboard().setVisible(true);
        dispose();
    } else if (username.equals("user@example.com") && password.equals("userpass123")) {
        fallbackLoginSuccess = true;
        loginSuccess[0] = true;
        JOptionPane.showMessageDialog(null, "Fallback sequence initiated, Welcome User", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        new UserDashboard().setVisible(true);
        dispose();
    } 

    if (!fallbackLoginSuccess) {
        failedAttempts++; // Increase failed attempts here!

        int lockoutTime = 0;
        String warningMessage = "";

        // Same lockout behavior as normal mode
        if (failedAttempts == 1) {
            lockoutTime = 10; // 10 seconds
        } else if (failedAttempts == 2) {
            lockoutTime = 30; // 30 seconds
        } else if (failedAttempts == 3) {
            warningMessage = "You have 2 attempts left";
        } else if (failedAttempts == 4) {
            warningMessage = "You have 1 attempt left";
        } else if (failedAttempts >= 5) {
            lockoutTime = 600; // 10 minutes
        }

        if (!warningMessage.isEmpty()) {
            lockoutLabel.setVisible(true);
            lockoutLabel.setForeground(Color.ORANGE);
            lockoutLabel.setFont(new Font("Arial", Font.BOLD, 14));
            lockoutLabel.setText(warningMessage);
        }

        if (lockoutTime > 0) {
            browhy.setEnabled(false);
            lockoutLabel.setVisible(true);
            lockoutLabel.setForeground(Color.RED);
            lockoutLabel.setFont(new Font("Arial", Font.BOLD, 14));

            final AtomicInteger remainingTime = new AtomicInteger(lockoutTime);

            Timer countdownTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentTime = remainingTime.get();
                    if (currentTime > 0) {
                        lockoutLabel.setText("Locked out: " + currentTime + "s");
                        remainingTime.set(currentTime - 1);
                    } else {
                        ((Timer) e.getSource()).stop();
                        browhy.setEnabled(true);
                        browhy.setText("Login");
                        lockoutLabel.setVisible(false);
                        lockoutLabel.setText("");
                        failedAttempts = 0; // Reset after timeout
                    }
                }
            });
            countdownTimer.start();
        } else {
            int attemptsLeft = 5 - failedAttempts;
            lockoutLabel.setVisible(true);
            lockoutLabel.setForeground(Color.ORANGE);
            lockoutLabel.setFont(new Font("Arial", Font.BOLD, 14));
            lockoutLabel.setText("Attempts left: " + attemptsLeft);
        }

        JOptionPane.showMessageDialog(this, errorMessage[0], "Login Failed", JOptionPane.ERROR_MESSAGE);
browhy.setEnabled(true);
browhy.setText("Login");
    }

    // Set up Timer to reset button even in fallback mode
    Timer resetButtonTimer = new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!browhy.isEnabled() && lockoutLabel.isVisible()) {
                // If still locked out, do nothing
                return;
            }
            browhy.setEnabled(true);
            browhy.setText("Login");

            if (!loginSuccess[0] && !errorMessage[0].isEmpty()) {
                JOptionPane.showMessageDialog(null, errorMessage[0], "Login Error", JOptionPane.ERROR_MESSAGE);
                browhy.setEnabled(true);
                browhy.setText("Login");
            }
        }
    });
    resetButtonTimer.setRepeats(false);
    resetButtonTimer.start();
}
// --- END of Lockout Code ---

    }
    shakeWindow();
    


    resultSet.close();
    preparedStatement.close();
    connection.close();

} catch (Exception e) {
    failedAttempts++;
    JOptionPane.showMessageDialog(this, errorMessage[0], "Login Failed", JOptionPane.ERROR_MESSAGE);
browhy.setEnabled(true);
browhy.setText("Login");

// Track the current lockout time

if (failedAttempts >= 5) {
    // Calculate the current lockout time incrementally
int baseLockoutTime = 10; // 10 seconds for first lockout
// Use the lockoutMultiplier field instead of recalculating multiplier here
int currentLockoutTime = baseLockoutTime * lockoutMultiplier;

final LocalDateTime lockoutUntil = LocalDateTime.now().plusSeconds(currentLockoutTime); // Lock for the current time duration
saveLockoutState(failedAttempts, lockoutUntil);

// Send lockout info to database if connected
if (userSerialNumber != null && !userSerialNumber.isEmpty() && databaseConnected) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hotel_reservation", dbUser, dbPass);
        String updateSql = "UPDATE Users SET locked_out = 1 WHERE serial_number = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setString(1, userSerialNumber);
        updateStmt.executeUpdate();
        updateStmt.close();
        connection.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    // Create an atomic integer to track the time left
    final AtomicInteger remainingTime = new AtomicInteger(currentLockoutTime); // Use the current lockout time

    // Start the lockout timer
    Timer lockoutTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            int timeLeft = remainingTime.get();
            if (timeLeft > 0) {
                // Update the UI with the time left
                browhy.setText("Locked (" + timeLeft + "s)");
                remainingTime.set(timeLeft - 1); // Decrease time each second
                browhy.setEnabled(false); // Disable the login button during lockout
            } else {
                // Stop the timer after time is up
                ((Timer) evt.getSource()).stop(); // Stop the countdown timer
                browhy.setEnabled(true); // Re-enable the button
                browhy.setText("Login"); // Reset the button text
                failedAttempts = 4; // Reset failed attempts to 4 to allow 1 attempt after lockout
                lockoutLabel.setVisible(false); // Hide lockout label
                lockoutMultiplier++; // Increase lockout multiplier for next lockout
            }
        }
    });
    lockoutTimer.start(); // Start the timer
} else {
    int attemptsLeft = 5 - failedAttempts;
    lockoutLabel.setVisible(true);
    lockoutLabel.setForeground(Color.ORANGE);
    lockoutLabel.setFont(new Font("Arial", Font.BOLD, 14));
    lockoutLabel.setText("Attempts left: " + attemptsLeft);
}



if (!databaseConnected) {
    if (errorMessage[0].isEmpty()) {
        errorMessage[0] = "There was a problem connecting to the database. Please try again later.";
    }
browhy.setEnabled(true);
browhy.setText("Login");;
    boolean fallbackLoginSuccess = false;
   

    if (username.equals("admin@example.com") && password.equals("fallbackPassword")) {
         fallbackLoginSuccess = true;
         loginSuccess[0] = true;
        JOptionPane.showMessageDialog(null, "Fallback sequence initiated, Welcome Admin", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        new AdminDashboard().setVisible(true);
        dispose();
    }

    if (username.equals("user@example.com") && password.equals("userpass123")) {
         fallbackLoginSuccess = true;
         loginSuccess[0] = true;
        JOptionPane.showMessageDialog(null, "Fallback sequence initiated, Welcome User", "Login Success", JOptionPane.INFORMATION_MESSAGE);
       
        new UserDashboard().setVisible(true);
        dispose();
    }
// <<< ONLY NOW: Set errorMessage if fallback ALSO failed >>>
    if (!fallbackLoginSuccess) {
        errorMessage[0] = "Database connection error";  // <<<<< here!
        
    }
    // Set up the Timer to reset button and show error if needed
Timer resetButtonTimer = new Timer(3000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        browhy.setEnabled(true);
        browhy.setText("Login");

       if (!loginSuccess[0] && !errorMessage[0].isEmpty()) {
    JOptionPane.showMessageDialog(null, errorMessage[0], "Login Error", JOptionPane.ERROR_MESSAGE);
}

    }
});
resetButtonTimer.setRepeats(false); // Only run once
resetButtonTimer.start();



}
// Load saved credentials if "Remember Me" was selected
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        String savedEmail = prefs.get("email", "");
        String savedPassword = prefs.get("password", "");
        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            emailTextField.setText(savedEmail);
            jPasswordField1.setText(savedPassword);
            RememberMe.setSelected(true);
        }



}
 
                 
        
    }//GEN-LAST:event_browhyActionPerformed

    private void RememberMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RememberMeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RememberMeActionPerformed
                
    /**
  s   * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon;
    private javax.swing.JPanel Left;
    private javax.swing.JCheckBox RememberMe;
    private javax.swing.JPanel Right;
    private javax.swing.JButton browhy;
    private javax.swing.JLabel connectivityLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel lockoutLabel;
    private javax.swing.JCheckBox showpassword;
    private javax.swing.JLabel userIdLabel;
    // End of variables declaration//GEN-END:variables

    void showTemporaryMessage(String you_may_now_log_in) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}