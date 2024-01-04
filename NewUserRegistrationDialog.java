/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartcity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class NewUserRegistrationDialog extends javax.swing.JDialog {

    private Connection con;
    private PreparedStatement pst;

    private javax.swing.JButton jButtonAdd;
    private javax.swing.JComboBox<String> jComboBoxOptions;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelOptions;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JPasswordField jPasswordField1;

    public NewUserRegistrationDialog(java.awt.Frame parent, boolean modal, Connection con) {
        super(parent, modal);
        initComponents();
        this.con = con;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonAdd = new javax.swing.JButton();
        jLabelFirstName = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabelLastName = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabelOptions = new javax.swing.JLabel();
        jComboBoxOptions = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelUsername.setText("Enter Username:");

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelPassword.setText("Enter Password:");

        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabelFirstName.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelFirstName.setText("Enter First Name:");

        jLabelLastName.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelLastName.setText("Enter Last Name:");

        jLabelOptions.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelOptions.setText("Select User Type:");

        jComboBoxOptions.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jComboBoxOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsername)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelFirstName)
                            .addComponent(jLabelLastName)
                            .addComponent(jLabelOptions))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsername)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFirstName)
                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastName)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOptions)
                    .addComponent(jComboBoxOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonAdd)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Add Button event code for new user registration
        String uname = jTextFieldUsername.getText();
        String pword = new String(jPasswordField1.getPassword());
        String firstName = jTextFieldFirstName.getText();
        String lastName = jTextFieldLastName.getText();
        String userType = jComboBoxOptions.getSelectedItem().toString();

        if (uname.isEmpty() || pword.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || userType.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                pst = con.prepareStatement("INSERT INTO login_table (username, password, options, firstname, lastname) VALUES (?, ?, ?, ?, ?)");
                pst.setString(1, uname);
                pst.setString(2, pword);
                pst.setString(3, userType);
                pst.setString(4, firstName);
                pst.setString(5, lastName);
                int result = pst.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(rootPane, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close the registration dialog after successful registration
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Failed to register user", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("" + ex);
            }
        }
    }                                       
}

