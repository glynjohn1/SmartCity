/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartcity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageUsersDialog extends javax.swing.JDialog {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public ManageUsersDialog(java.awt.Frame parent, boolean modal, Connection con) {
        super(parent, modal);
        initComponents();
        this.con = con;
        displayUsers();
    }

    
    //display users from db
    private void displayUsers() {
        try {
            pst = con.prepareStatement("SELECT * FROM login_table");
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("options")};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //adding a new user from admin
    private void addUser(String username, String password, String options) {
        try {
            pst = con.prepareStatement("INSERT INTO login_table (username, password, options) VALUES (?, ?, ?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, options);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //editing a user from admin using UPDATE
    private void editUser(int userId, String username, String password, String options) {
        try {
            pst = con.prepareStatement("UPDATE login_table SET username=?, password=?, options=? WHERE id=?");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, options);
            pst.setInt(4, userId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //admin deleting a user in db
    private void deleteUser(int userId) {
        try {
            pst = con.prepareStatement("DELETE FROM login_table WHERE id=?");
            pst.setInt(1, userId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //create table with buttons 
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        addUserBtn = new javax.swing.JButton();
        editUserBtn = new javax.swing.JButton();
        deleteUserBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Password", "Options"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userTable);

        addUserBtn.setText("Add User");
        addUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addUserBtnActionPerformed(evt);
            }
        });

        editUserBtn.setText("Edit User");
        editUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editUserBtnActionPerformed(evt);
            }
        });

        deleteUserBtn.setText("Delete User");
        deleteUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteUserBtnActionPerformed(evt);
            }
        });

        
        //javaxswing customisation
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addUserBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editUserBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteUserBtn)
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }

    private void addUserBtnActionPerformed(ActionEvent evt) {
        // Handle add user button click
        String username = JOptionPane.showInputDialog(this, "Enter username:", "Add User", JOptionPane.PLAIN_MESSAGE);
        String password = JOptionPane.showInputDialog(this, "Enter password:", "Add User", JOptionPane.PLAIN_MESSAGE);
        String options = JOptionPane.showInputDialog(this, "Enter options:", "Add User", JOptionPane.PLAIN_MESSAGE);

        if (username != null && password != null && options != null) {
            addUser(username, password, options);
        }
    }

    private void editUserBtnActionPerformed(ActionEvent evt) {
        // Handle edit user button click
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            String username = JOptionPane.showInputDialog(this, "Enter new username:", "Edit User", JOptionPane.PLAIN_MESSAGE);
            String password = JOptionPane.showInputDialog(this, "Enter new password:", "Edit User", JOptionPane.PLAIN_MESSAGE);
            String options = JOptionPane.showInputDialog(this, "Enter new options:", "Edit User", JOptionPane.PLAIN_MESSAGE);

            if (username != null && password != null && options != null) {
                editUser(userId, username, password, options);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUserBtnActionPerformed(ActionEvent evt) {
        // Handle delete user button click
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                deleteUser(userId);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageUsersDialog(new javax.swing.JFrame(), true, null).setVisible(true);
            }
        });
    }

    private javax.swing.JButton addUserBtn;
    private javax.swing.JButton deleteUserBtn;
    private javax.swing.JButton editUserBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable userTable;
}
