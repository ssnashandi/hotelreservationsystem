package loginandsignup;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*; // Importing Swing components for the GUI
import java.awt.event.ActionEvent; // Importing ActionEvent for handling button clicks
import java.awt.event.ActionListener; // Importing ActionListener interface for event handling
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SignUp extends javax.swing.JFrame {
    
    // Password strength checking method
    private String checkPasswordStrength(String password) {
        int strengthScore = 0;

        // Increase strength score for each rule met
        if (password.length() >= 8) strengthScore++;
        if (password.matches(".*[A-Z].*")) strengthScore++; // At least one uppercase letter
        if (password.matches(".*[a-z].*")) strengthScore++; // At least one lowercase letter
        if (password.matches(".*\\d.*")) strengthScore++; // At least one digit
        if (password.matches(".*[!@#$%^&*()_+].*")) strengthScore++; // At least one special character

        // Evaluate the strength score
        switch (strengthScore) {
            case 5: return "Very Strong";
            case 4: return "Strong";
            case 3: return "Medium";
            case 2: return "Weak";
            default: return "Very Weak";
        }
    }

 
    public SignUp() {
      // Initialize your components (assuming this part is already done in your existing code)
        // For example:
       FirstNameTextField = new JTextField(20);
       LastNameTextField = new JTextField(20);
       PhoneNumberTextField = new JTextField(10);
       EmailTextField = new JTextField(25);
       PasswordTextField = new JPasswordField(20);
       ConfirmPasswordTextField = new JPasswordField(20);
       SignUpButton = new JButton("Sign Up");  
        // Initialize the password strength label
        passwordStrengthLabel = new JLabel(); 
        passwordStrengthLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set font if desired
       
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
        
        
        
     
        
        
        initComponents(); // Ensure this is called

    // Add DocumentListener to PasswordTextField
    PasswordTextField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updatePasswordStrength();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updatePasswordStrength();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updatePasswordStrength();
        }

        
         private void updatePasswordStrength() {
                String password = new String(PasswordTextField.getPassword());
                String strength = checkPasswordStrength(password);
                passwordStrengthLabel.setText("Password Strength: " + strength);

                // Set color based on strength
                switch (strength) {
                    case "Very Strong":
                        passwordStrengthLabel.setForeground(Color.GREEN);
                        break;
                    case "Strong":
                        passwordStrengthLabel.setForeground(Color.BLUE);
                        break;
                    case "Medium":
                        passwordStrengthLabel.setForeground(Color.ORANGE);
                        break;
                    case "Weak":
                        passwordStrengthLabel.setForeground(Color.RED);
                        break;
                    default: // Very Weak
                        passwordStrengthLabel.setForeground(Color.GRAY);
                        break;
                }
            }
        });
    
    
    

    // Add your KeyListeners here
    FirstNameTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                LastNameTextField.requestFocus();
            }
        }
    });

    LastNameTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                PhoneNumberTextField.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                FirstNameTextField.requestFocus();
            }
        }
    });

    PhoneNumberTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                CountryComboBox.requestFocus();
                CountryComboBox.showPopup();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                LastNameTextField.requestFocus();
            }
        }
    });

    CountryComboBox.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                EmailTextField.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                PhoneNumberTextField.requestFocus();
            }
        }
    });

    EmailTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                PasswordTextField.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                CountryComboBox.requestFocus();
            }
        }
    });

    PasswordTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
                ConfirmPasswordTextField.requestFocus();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                EmailTextField.requestFocus();
            }
        }
    });

    ConfirmPasswordTextField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                SignUpButton.doClick();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                PasswordTextField.requestFocus();
            }
        }
    });

    // You may want to add the password strength label to the GUI here as well
    // For example:
    // jPanel3.add(passwordStrengthLabel); // Add the label to your panel
}
    

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        passwordStrengthLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 129, -1, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Banner", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("copyright © company name All rights reserved");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 560, -1, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel Images/5d083f73618c9af3c323e1e8a3b642b8.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 590));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 500, 590);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("SIGN UP");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        FirstNameLable.setBackground(new java.awt.Color(102, 102, 102));
        FirstNameLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FirstNameLable.setText("First Name");
        jPanel3.add(FirstNameLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        FirstNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FirstNameTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(FirstNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 420, 30));

        PhoneNumberLable.setBackground(new java.awt.Color(102, 102, 102));
        PhoneNumberLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PhoneNumberLable.setText("Phone Number");
        jPanel3.add(PhoneNumberLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        EmailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(EmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 420, 30));

        ConfirmPasswordLable.setBackground(new java.awt.Color(102, 102, 102));
        ConfirmPasswordLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConfirmPasswordLable.setText("Confirm Password");
        jPanel3.add(ConfirmPasswordLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        ConfirmPasswordTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ConfirmPasswordTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(ConfirmPasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 420, 30));

        IDontHaveAnAccountLable.setText("I have an account");
        jPanel3.add(IDontHaveAnAccountLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        SignUpButton.setBackground(new java.awt.Color(0, 102, 102));
        SignUpButton.setForeground(new java.awt.Color(255, 255, 255));
        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        jPanel3.add(SignUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 130, 37));

        LoginButton.setForeground(new java.awt.Color(255, 51, 51));
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        jPanel3.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 110, 31));

        PhoneNumberTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PhoneNumberTextField.setForeground(new java.awt.Color(102, 102, 102));
        PhoneNumberTextField.setText("+264");
        jPanel3.add(PhoneNumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 420, 30));

        LastNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LastNameTextField.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.add(LastNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 420, 30));

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
        jPanel3.add(PasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 420, 30));

        EmailLable.setBackground(new java.awt.Color(102, 102, 102));
        EmailLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmailLable.setText("Email");
        jPanel3.add(EmailLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        CountryLable.setBackground(new java.awt.Color(102, 102, 102));
        CountryLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CountryLable.setText("Country");
        jPanel3.add(CountryLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        CountryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo (Congo-Brazzaville)", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czechia", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (formerly Burma)", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe", " " }));
        jPanel3.add(CountryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 420, 30));
        jPanel3.add(passwordStrengthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 240, 20));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(500, 0, 470, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
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
    String firstName = FirstNameTextField.getText().trim(); // Get first name input
    String lastName = LastNameTextField.getText().trim(); // Get last name input
    String phoneNumber = PhoneNumberTextField.getText().trim(); // Get phone number input
    String email = EmailTextField.getText().trim(); // Get email input
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
    // Define allowed domains
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



    // Validate that the phone number starts with +264 and contains only digits after
    if (!phoneNumber.startsWith("+264") || !phoneNumber.substring(4).matches("\\d+") || phoneNumber.length() < 8) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Namibian phone number starting with +264", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Validate password length
    if (password.length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check password strength (add this part)
    String strength = checkPasswordStrength(password); // Implement this method to determine the strength
    if (strength.equals("weak") || strength.equals("very weak")) {
        JOptionPane.showMessageDialog(null, "Password strength is too weak. Please choose a stronger password.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit if the password strength is not acceptable
    }

    // Validate password match
    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(null, "Passwords do not match", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel passwordStrengthLabel;
    // End of variables declaration//GEN-END:variables
}
