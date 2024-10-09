
package loginandsignup; //package lol
//ALL imports for this project to work lol
import Dashboard.AdminDashboard;
import Dashboard.Dashboard;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends javax.swing.JFrame {

  
    public Login() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked") //this code was auto generated so no need to comment even bruh
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(102, 102, 255));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Sitka Banner", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BLISFUL RESORT");
        Right.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("copyright © Blissful Resort All rights reserved");
        Right.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Images/66735be1ae99cee72a8e151fc87bb7a3.png"))); // NOI18N
        Right.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 400, 500));

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 500);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGIN");
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 51, -1, -1));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Email");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 139, -1, -1));

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(102, 102, 102));
        Left.add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 171, 343, 40));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password");
        Left.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 229, -1, -1));
        Left.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 261, 343, 40));

        browhy.setBackground(new java.awt.Color(0, 102, 102));
        browhy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        browhy.setForeground(new java.awt.Color(255, 255, 255));
        browhy.setText("Login");
        browhy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browhyActionPerformed(evt);
            }
        });
        Left.add(browhy, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 327, 93, 36));

        jLabel4.setText("I don't have an account");
        Left.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 402, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Left.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 396, -1, -1));

        showpassword.setText("Show Password");
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordActionPerformed(evt);
            }
        });
        Left.add(showpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, -1, -1));

        jPanel1.add(Left);
        Left.setBounds(400, 0, 385, 423);

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
String emailPattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; // Regex pattern
Pattern pattern = Pattern.compile(emailPattern);
Matcher matcher = pattern.matcher(email);

// Validate the email format using regex matcher
if (!matcher.matches()) {
    // Show an error message if the email format is invalid
    JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Input Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit the method if email validation fails
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
         // Redirect based on the email address
            if (username.endsWith(".admin")) {
                AdminDashboard adminDashboard = new AdminDashboard(); // Create instance of AdminDashboard
                adminDashboard.setVisible(true); // Show admin dashboard
            } else if (username.endsWith(".com")) {
                Dashboard dashboard = new Dashboard(); // Create instance of Guest Dashboard
                dashboard.setVisible(true); // Show guest dashboard
            }
            
            dispose(); // Close the current login window
        } else {
            // If no matching record is found, show an error message
            JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }

    // Close the ResultSet, PreparedStatement, and the database connection to release resources
    resultSet.close(); // Close the ResultSet
    preparedStatement.close(); // Close the PreparedStatement
    connection.close(); // Close the database connection
    
} catch (Exception e) {
    // Print the stack trace for debugging if any exception occurs
    e.printStackTrace();
    // Show an error message if there is an issue with the database connection
    JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
}


 

     
     
     
     
     

           
    
    
     
               
                     
        
    }//GEN-LAST:event_browhyActionPerformed
                
    /**
  s   * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private javax.swing.JButton browhy;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JCheckBox showpassword;
    // End of variables declaration//GEN-END:variables

    void showTemporaryMessage(String you_may_now_log_in) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}