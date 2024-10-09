package Content;


import javax.swing.JPanel;

public class FifthPanel extends JPanel {


    public FifthPanel() {
        initComponents(); // Initialize the GUI components
    }

    // Method to set the welcome message
    public void setWelcomeMessage(String username) {
        welcomeLol.setText("Welcome " + username); // Update the label with the user's name
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        welcomeLol = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 51, 102));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Blissful Resort");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        welcomeLol.setText("jLabel1");
        add(welcomeLol, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel welcomeLol;
    // End of variables declaration//GEN-END:variables

    public Object getEditProfileButton() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
