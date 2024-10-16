
package loginandsignup; //package lol

//ALL imports for this project to work lol
// Importing classes for handling HTTP connections
import java.net.HttpURLConnection; // Class for managing HTTP connections
import java.net.URL; // Class for handling URLs

// Importing classes for dashboard functionality
import Dashboard.AdminDashboard; // Class for the admin dashboard
import Dashboard.Dashboard; // Class for the user dashboard

// Importing classes for handling exceptions
import java.awt.HeadlessException; // Exception for headless environments

// Importing classes for GUI elements and event handling
import javax.swing.JOptionPane; // Class for showing dialog boxes
import java.awt.event.*; // Package for handling various events (e.g., key events, action events)

// Importing classes for regular expression pattern matching
import java.util.regex.Pattern; // Class for defining regex patterns
import java.util.regex.Matcher; // Class for matching regex patterns against strings

// Importing classes for database connectivity
import java.sql.Connection; // Interface for managing database connections
import java.sql.DriverManager; // Class for managing database drivers
import java.sql.PreparedStatement; // Class for precompiled SQL statements
import java.sql.ResultSet; // Class for representing a set of results from a database query
import java.sql.SQLException; // Class for handling SQL exceptions

// Importing classes for user preferences management
import java.util.prefs.Preferences; // Class for managing user and system preferences




public class Login extends javax.swing.JFrame {
    
    private final Preferences prefs;
    private static final String EMAIL_KEY = "email";
    
    


  
    // Constructor for the Login class
public Login() {
    initComponents(); // Initialize the GUI components

    // Retrieve user preferences for the email and load it into the text field
    prefs = Preferences.userNodeForPackage(Login.class);
    String savedEmail = prefs.get(EMAIL_KEY, ""); // Get the saved email using EMAIL_KEY
    emailTextField.setText(savedEmail); // Set the text field with the saved email
    
    // Add a key listener to the email text field to handle the Enter key press
    emailTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            // Check if the pressed key is the Enter key
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                jPasswordField1.requestFocus(); // Move the focus to the password field
            }
        }
    });
    
    // Add a key listener to the password field to handle the Enter key press
    jPasswordField1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            // Check if the pressed key is the Enter key
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                browhy.doClick(); // Simulate a button click action for the login button
            }
        }
    });
}

// Inner class to check internet connectivity
public class InternetCheck {
    // Method to check if the internet is available
    public static boolean isInternetAvailable() {
        try {
            // Create a URL object for Google's homepage
            URL url = new URL("https://www.google.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // Open a connection to the URL
            urlConnection.setRequestMethod("HEAD"); // Set the request method to HEAD
            urlConnection.setConnectTimeout(3000); // Set a timeout of 3 seconds for the connection
            urlConnection.connect(); // Connect to the URL
            // Return true if the response code is 200 (HTTP OK), indicating internet availability
            return (urlConnection.getResponseCode() == 200);
        } catch (Exception e) {
            return false; // Return false if an exception occurs, indicating no internet connection
        }
    }
}


 
    @SuppressWarnings("unchecked") //this code was auto generated so no need to comment even bruh
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        browhy = new javax.swing.JButton();
        showpassword = new javax.swing.JCheckBox();
        LocalLoginCheckBox = new javax.swing.JCheckBox();
        rememberMeCheckBox = new javax.swing.JCheckBox();
        forgotPasswordLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 550));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(153, 153, 153));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("copyright © Blissful Resort All rights reserved");
        Right.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg_images/blissful-resort-high-resolution-logo-transparent(6).png"))); // NOI18N
        Right.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, 430));

        jPanel1.add(Right);
        Right.setBounds(0, 0, 550, 550);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGIN");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 51, -1, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Email");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 60, -1));

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(102, 102, 102));
        Left.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 343, 40));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Password");
        Left.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));
        Left.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 343, 40));

        browhy.setBackground(new java.awt.Color(0, 102, 102));
        browhy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        browhy.setForeground(new java.awt.Color(255, 255, 255));
        browhy.setText("Login");
        browhy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browhyActionPerformed(evt);
            }
        });
        Left.add(browhy, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 93, 36));

        showpassword.setText("Show Password");
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordActionPerformed(evt);
            }
        });
        Left.add(showpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 120, -1));

        LocalLoginCheckBox.setText("Local Login");
        Left.add(LocalLoginCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        rememberMeCheckBox.setText("Remember Me");
        Left.add(rememberMeCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        forgotPasswordLabel.setForeground(new java.awt.Color(0, 0, 204));
        forgotPasswordLabel.setText("Forgot Password?");
        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotPasswordLabelMouseClicked(evt);
            }
        });
        Left.add(forgotPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jPanel1.add(Left);
        Left.setBounds(550, 0, 480, 400);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(620, 450, 90, 27);

        jLabel4.setText("I don't have an account");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(600, 480, 140, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
// Inside your actionPerformed method

 // Check if the Local Login checkbox is selected
    if (LocalLoginCheckBox.isSelected()) {
        // Proceed with normal login logic without checking internet
    } else {
        // Check internet availability for remote login
        if (!InternetCheck.isInternetAvailable()) {
            JOptionPane.showMessageDialog(null, "Internet connection is required for this feature.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if internet is not available
        }
    }

// Retrieve what the user entered
String username = emailTextField.getText();
String password = new String(jPasswordField1.getPassword());
String email = emailTextField.getText();



// Validating that fields are not left empty
if(username.isEmpty() || password.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Validating email length
if(email.length() > 30) {
    JOptionPane.showMessageDialog(null, "Email should not exceed 30 characters", "Input Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Validating email format
String emailPattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; // Define regex for email validation
Pattern pattern = Pattern.compile(emailPattern);
Matcher matcher = pattern.matcher(email);




// Validate the email format using regex matcher
if (!matcher.matches()) {
    // Show an error message if the email format is invalid
    JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Input Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit the method if email validation fails
}


// Define allowed domains
String[] allowedDomains = { "gmail.com", 
    "yahoo.com", 
    "hotmail.com", 
    "outlook.com", 
    "live.com", 
    "icloud.com", 
    "mail.com", 
    "zoho.com", 
    "aol.com", 
    "protonmail.com", 
    "tutanota.com",
    "gov.na",         // Namibian government emails
    "edu.na",         // Namibian educational institutions
    "nust.na",        //Namibia University of Science and Technology
    "com.na",         // Namibian commercial organizations
    "org.na",         // Namibian non-profit organizations
    "info.na",        // Information-related domains in Namibia
    "mil.na",         // Namibian military organizations
    "ac.na",          // Academic institutions in Namibia
    "web.na",         // Web domains in Namibia
    // Include other countries and their typical domains
    "gov",            // General government domains (e.g., .gov in other countries)
    "edu",            // General education domains (e.g., .edu in other countries)
    "mil",            // General military domains (e.g., .mil in other countries)
    // International domains
    "gov.uk",         // UK Government
    "gov.us",         // US Government
    "gov.ca",         // Canadian Government
    "gov.au",         // Australian Government
    "gov.in",         // Indian Government
    "gov.za",         // South African Government
    "edu.au",         // Australian educational institutions
    "edu.uk",         // UK educational institutions
    "edu.ca",         // Canadian educational institutions
    "edu.in",         // Indian educational institutions
    // Popular organizational and business domains
    "microsoft.com", 
    "ibm.com", 
    "amazon.com", 
    "facebook.com", 
    "twitter.com", 
    "linkedin.com",
    // Other common domains
    "example.com", 
    "test.com" }; // Add more allowed domains as needed

// Extract the domain from the email
String domain = email.substring(email.indexOf('@') + 1);

// Check if the domain is in the allowed list
boolean isAllowedDomain = false;
for (String allowedDomain : allowedDomains) {
    if (domain.equalsIgnoreCase(allowedDomain)) {
        isAllowedDomain = true;
        break;
    }
}

if (!isAllowedDomain) {
    JOptionPane.showMessageDialog(null, "Email domain not allowed. Please use an allowed email address.", "Domain Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit the method if the domain is not allowed
}
    // Ensure email does not end with ".adm"
    if (email.endsWith(".adm")) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Check if the email ends with a valid TLD
    // Comprehensive list of email domains to allow login for various user types
    //general email providers
String[] validTLDs = {
    "com", "net", "org", "edu", "gov", "mil", "info", "biz", "co", "io", 
    "na", // Namibia
    "ac", "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", 
    "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", 
    "bg", "bh", "bi", "bj", "bm", "bn", "bo", "bq", "br", "bs", "bt", 
    "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", 
    "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", 
    "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", 
    "es", "et", "eu", "fi", "fj", "fm", "fo", "fr", "ga", "gb", "gd", 
    "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", 
    "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", 
    "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", 
    "jo", "jp", "ke", "kg", "kh", "ki", "kj", "km", "kn", "kp", "kr", 
    "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", 
    "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", 
    "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", 
    "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", 
    "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", 
    "pk", "pl", "pm", "pn", "pr", "pt", "pw", "py", "qa", "re", "ro", 
    "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", 
    "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", 
    "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", 
    "tn", "to", "tr", "ts", "tt", "tv", "tz", "ua", "ug", "uk", "us", 
    "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vut", "wf", "ws", 
    "ye", "yt", "za", "zm", "zw"
};// list of all valid tld's xd


// Function to check if the email has a valid domain
boolean validDomain = false;

 // If not found in valid TLDs, it will check against the guest domains
// Loop through each valid TLD in the validTLDs array
for (String tld : validTLDs) {
    // Check if the email ends with the current TLD
    if (email.endsWith("." + tld)) {
        validDomain = true; // Set validDomain to true if a match is found
        break; // Exit the loop early as a valid TLD has been found
    }
}

// Check if the email does not match any of the valid guest domains
if (!validDomain) {
    // Show an error message prompting the user to enter a valid email address
    JOptionPane.showMessageDialog(null, "Please enter an email address with a valid domain (e.g., .com, .net)", "Input Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit the method if the TLD is not valid
}


// Validate password length (at least 8 characters)
if (password.length() < 8) {
    // Show an error message if the password is too short
    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long", "Input Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit the method if password validation fails
    
    
    
}

// Database connection parameters (adjust these to your database)
String url = "jdbc:mysql://localhost:3307/hotel_reservation"; // Your MySQL database URL
String user = "Java"; // Your MySQL database username
String pass = "java17"; // Your MySQL database password

try {
        // Establish connection to the database using the provided URL, username, and password
        Connection connection = DriverManager.getConnection(url, user, pass);

        // Prepare SQL query to check if the user exists in the Guests table with matching email and password
        String sql = "SELECT first_name FROM Guests WHERE email = ? AND password = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // Set the values for the email (1st parameter) and password (2nd parameter)
        preparedStatement.setString(1, username); // 'username' refers to the entered email
        preparedStatement.setString(2, password); // 'password' refers to the entered password

        // Execute the query and store the result in a ResultSet
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the query returned a result (i.e., user exists)
        if (resultSet.next()) {
            String fetchedUsername = resultSet.getString("first_name"); // Change "first_name" to the appropriate column name
            
            // Show the welcome message with the user's name
            JOptionPane.showMessageDialog(null, "Welcome, " + fetchedUsername + "!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
         // Redirect based on the email address
            // List of allowed domains for guest users
String[] guestDomains = {
    "gmail.com", 
    "yahoo.com", 
    "hotmail.com", 
    "outlook.com", 
    "live.com", 
    "icloud.com", 
    "mail.com", 
    "zoho.com", 
    "aol.com", 
    "protonmail.com", 
    "tutanota.com",
    "gov.na",         // Namibian government emails
    "edu.na",         // Namibian educational institutions
    "nust.na",        //Namibia University of Science and Technology
    "com.na",         // Namibian commercial organizations
    "org.na",         // Namibian non-profit organizations
    "info.na",        // Information-related domains in Namibia
    "mil.na",         // Namibian military organizations
    "ac.na",          // Academic institutions in Namibia
    "web.na",         // Web domains in Namibia
    // Include other countries and their typical domains
    "gov",            // General government domains (e.g., .gov in other countries)
    "edu",            // General education domains (e.g., .edu in other countries)
    "mil",            // General military domains (e.g., .mil in other countries)
    // International domains
    "gov.uk",         // UK Government
    "gov.us",         // US Government
    "gov.ca",         // Canadian Government
    "gov.au",         // Australian Government
    "gov.in",         // Indian Government
    "gov.za",         // South African Government
    "edu.au",         // Australian educational institutions
    "edu.uk",         // UK educational institutions
    "edu.ca",         // Canadian educational institutions
    "edu.in",         // Indian educational institutions
    // Popular organizational and business domains
    "microsoft.com", 
    "ibm.com", 
    "amazon.com", 
    "facebook.com", 
    "twitter.com", 
    "linkedin.com",
    // Other common domains
    "example.com", 
    "test.com"
    // they seem enough ig
};

// Redirect based on the email address, admins only
if (username.endsWith(".adm")) {
    AdminDashboard adminDashboard = new AdminDashboard(); // Create instance of AdminDashboard
    adminDashboard.setVisible(true); // Show admin dashboard
} else {
    // Check if the email domain is in the guestDomains list
    boolean isGuestDomain = false;
    for (String guestDomain : guestDomains) {
        if (username.endsWith(guestDomain)) {
            isGuestDomain = true;
            break; // Exit loop if a match is found
        }
    }

    // Redirect to the guest dashboard if the domain is valid, guests and other users
    if (isGuestDomain) {
        Dashboard dashboard = new Dashboard(); // Create instance of Guest Dashboard
        dashboard.setVisible(true); // Show guest dashboard
    } else {
        // Handle case for invalid domains
        JOptionPane.showMessageDialog(null, "Invalid email domain. Access denied.", "Access Error", JOptionPane.ERROR_MESSAGE);
    }
}
            

    // If a matching record is found, close the current login window
    this.dispose();

    // Save email if Remember Me is checked
    if (rememberMeCheckBox.isSelected()) {
        prefs.put(EMAIL_KEY, username);
    } else {
        prefs.remove(EMAIL_KEY); // Remove saved email if not checked
    }
            
            
        } else {
            // If no matching record is found, show an error message
            JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }

    // Close the ResultSet, PreparedStatement, and the database connection to release resources
    resultSet.close(); // Close the ResultSet
    preparedStatement.close(); // Close the PreparedStatement
    connection.close(); // Close the database connection
    
} catch (HeadlessException | SQLException e) {
// Print the stack trace for debugging if any exception occurs
        // Show an error message if there is an issue with the database connection
    JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
}


 

     
     
     
     
     

           
    
    
     
               
                     
        
    }//GEN-LAST:event_browhyActionPerformed

    private void forgotPasswordLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordLabelMouseClicked
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Open your password recovery panel or dialog here
        JOptionPane.showMessageDialog(null, "Password recovery dialog would appear here!");
    }
    int option = JOptionPane.showOptionDialog(
    null, 
    "Enter your registered email:", 
    "Forgot Password", 
    JOptionPane.OK_CANCEL_OPTION, 
    JOptionPane.QUESTION_MESSAGE, 
    null, 
    new String[]{"Send", "Cancel"}, // Custom button names
    "Send" // Default button selected
);

    
});
    }//GEN-LAST:event_forgotPasswordLabelMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JCheckBox LocalLoginCheckBox;
    private javax.swing.JPanel Right;
    private javax.swing.JButton browhy;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel forgotPasswordLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JCheckBox rememberMeCheckBox;
    private javax.swing.JCheckBox showpassword;
    // End of variables declaration//GEN-END:variables

  
};