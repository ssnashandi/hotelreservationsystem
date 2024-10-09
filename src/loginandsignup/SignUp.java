package loginandsignup;

import javax.swing.*; // Importing Swing components for the GUI
import java.awt.event.ActionEvent; // Importing ActionEvent for handling button clicks
import java.awt.event.ActionListener; // Importing ActionListener interface for event handling
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class SignUp extends javax.swing.JFrame {

 
    public SignUp() {
      // Initialize your components (assuming this part is already done in your existing code)
        // For example:
       FirstNameTextField = new JTextField(20);
       LastNameTextField = new JTextField(20);
       PhoneNumberTextField = new JTextField(20);
       EmailTextField = new JTextField(20);
       PasswordTextField = new JPasswordField(20);
       ConfirmPasswordTextField = new JPasswordField(20);
       SignUpButton = new JButton("Sign Up");  
       
       String[] countries = {
    "Select Country", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
    "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
    "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
    "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia",
    "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros",
    "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia", "Democratic Republic of the Congo",
    "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
    "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia",
    "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
    "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
    "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", 
    "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
    "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
    "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
    "Myanmar (formerly Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", 
    "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine State",
    "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
    "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa",
    "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
    "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", 
    "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan",
    "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
    "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
    "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
                };
        CountryComboBox = new JComboBox<>(countries);

        
        
        initComponents();
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        FirstNameLable = new javax.swing.JLabel();
        FirstNameTextField = new javax.swing.JTextField();
        PhoneNumberLable = new javax.swing.JLabel();
        EmailTextField = new javax.swing.JTextField();
        ConfirmPasswordLable = new javax.swing.JLabel();
        ConfirmPasswordTextField = new javax.swing.JPasswordField();
        IDontHaveAnAccountLable = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();
        LoginButton = new javax.swing.JButton();
        PhoneNumberTextField = new javax.swing.JTextField();
        LastNameTextField = new javax.swing.JTextField();
        LastNameLable = new javax.swing.JLabel();
        PasswordLable = new javax.swing.JLabel();
        PasswordTextField = new javax.swing.JPasswordField();
        EmailLable = new javax.swing.JLabel();
        CountryLable = new javax.swing.JLabel();
        CountryComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 129, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Blissful Resort");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Banner", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("copyright © company name All rights reserved");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, -1, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Images/5d083f73618c9af3c323e1e8a3b642b8.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 570));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 420, 570);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("SIGN UP");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        FirstNameLable.setBackground(new java.awt.Color(102, 102, 102));
        FirstNameLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FirstNameLable.setText("First Name");
        jPanel3.add(FirstNameLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        FirstNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FirstNameTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(FirstNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 330, 30));

        PhoneNumberLable.setBackground(new java.awt.Color(102, 102, 102));
        PhoneNumberLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PhoneNumberLable.setText("Phone Number");
        jPanel3.add(PhoneNumberLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        EmailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(EmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 330, 30));

        ConfirmPasswordLable.setBackground(new java.awt.Color(102, 102, 102));
        ConfirmPasswordLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConfirmPasswordLable.setText("Confirm Password");
        jPanel3.add(ConfirmPasswordLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        ConfirmPasswordTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConfirmPasswordTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(ConfirmPasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 332, 30));

        IDontHaveAnAccountLable.setText("I have an account");
        jPanel3.add(IDontHaveAnAccountLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        SignUpButton.setBackground(new java.awt.Color(0, 102, 102));
        SignUpButton.setForeground(new java.awt.Color(255, 255, 255));
        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        jPanel3.add(SignUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 120, 37));

        LoginButton.setForeground(new java.awt.Color(255, 51, 51));
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        jPanel3.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 110, 31));

        PhoneNumberTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PhoneNumberTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(PhoneNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 330, 30));

        LastNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LastNameTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(LastNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 330, 30));

        LastNameLable.setBackground(new java.awt.Color(102, 102, 102));
        LastNameLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LastNameLable.setText("Last Name");
        jPanel3.add(LastNameLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        PasswordLable.setBackground(new java.awt.Color(102, 102, 102));
        PasswordLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PasswordLable.setText("Password");
        jPanel3.add(PasswordLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        PasswordTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PasswordTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(PasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 332, 30));

        EmailLable.setBackground(new java.awt.Color(102, 102, 102));
        EmailLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailLable.setText("Email");
        jPanel3.add(EmailLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        CountryLable.setBackground(new java.awt.Color(102, 102, 102));
        CountryLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CountryLable.setText("Country");
        jPanel3.add(CountryLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        CountryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe", " " }));
        jPanel3.add(CountryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 330, 30));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(410, 0, 480, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        // Retrieve values from the input fields
    String firstName = FirstNameTextField.getText(); // Get first name input
    String lastName = LastNameTextField.getText(); // Get last name input
    String phoneNumber = PhoneNumberTextField.getText(); // Get phone number input
    String email = EmailTextField.getText(); // Get email input
    String password = new String(PasswordTextField.getPassword()); // Get password input
    String confirmPassword = new String(ConfirmPasswordTextField.getPassword()); // Get confirm password input

    // Use a simple regex pattern to validate email format
    String emailPattern = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"; // Define regex for email validation

    // Validate that no fields are empty
    if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields", "Message", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if any validation fails
    }

    // Validate email format using regex
    if (!email.matches(emailPattern)) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Input Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if email validation fails
    }

    // Validate phone number (simple check: digits only and length)
    if (!phoneNumber.matches("\\d{10}")) { // Adjust based on your requirements (e.g., length can vary)
        JOptionPane.showMessageDialog(null, "Please enter a valid phone number (10 digits)", "Input Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if phone number validation fails
    }

    // Validate password length (must be at least 8 characters)
    if (password.length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long", "Input Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if password length validation fails
    }

    // Validate that password and confirm password fields match
    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(null, "Passwords do not match", "Input Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if password confirmation fails
    }
    
     // Hash the password for security (recommended)
    // You can use libraries like BCrypt for hashing.
    // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    // Database connection and insertion
    Connection conn = null;
    PreparedStatement pstmt = null;
    
   try {
    // Establish connection to the database
    String url = "jdbc:mysql://localhost:3307/hotel_reservation"; // Replace with your database URL
    String user = "Java"; // Replace with your database username
    String dbPassword = "java17"; // Replace with your database password

    // Create a connection to the database
    conn = DriverManager.getConnection(url, user, dbPassword);

    // SQL query to insert the user data into the Guests table
    String sql = "INSERT INTO Guests (first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?, ?)";

    // Prepare the statement
    pstmt = conn.prepareStatement(sql);

    // Set the parameters with the user input data
    pstmt.setString(1, firstName); // First Name from the form
    pstmt.setString(2, lastName);  // Last Name from the form
    pstmt.setString(3, phoneNumber); // Phone Number from the form
    pstmt.setString(4, email); // Email from the form
    pstmt.setString(5, password); // Password from the form (Consider hashing the password for security)

    // Execute the query and insert the data
    int rowsInserted = pstmt.executeUpdate();

    // Check if the data was successfully inserted
    if (rowsInserted > 0) {
        JOptionPane.showMessageDialog(null, "Signup successful, you can now return to the login page!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Redirect to the login page (or another page as per your application)
        Login login = new Login(); // Replace with your login class
        login.setVisible(true);
        dispose(); // Close the signup form after success
    } else {
        JOptionPane.showMessageDialog(null, "Signup failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (SQLException e) {
    // Handle any SQL exceptions (e.g., connection failure, SQL query error)
    JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

} finally {
    // Close the PreparedStatement and Connection objects to prevent memory leaks
    try {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
     
    
    }//GEN-LAST:event_SignUpButtonActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ConfirmPasswordLable;
    private javax.swing.JPasswordField ConfirmPasswordTextField;
    private javax.swing.JComboBox<String> CountryComboBox;
    private javax.swing.JLabel CountryLable;
    private javax.swing.JLabel EmailLable;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JLabel FirstNameLable;
    private javax.swing.JTextField FirstNameTextField;
    private javax.swing.JLabel IDontHaveAnAccountLable;
    private javax.swing.JLabel LastNameLable;
    private javax.swing.JTextField LastNameTextField;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel PasswordLable;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JLabel PhoneNumberLable;
    private javax.swing.JTextField PhoneNumberTextField;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
