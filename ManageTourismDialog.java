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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author janet
 */

//manage connection
public class ManageTourismDialog extends javax.swing.JDialog {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    private JTable hotelsTable;
    private JScrollPane jScrollPaneHotels;
    private JPanel hotelsPanel;

    public ManageTourismDialog(java.awt.Frame parent, boolean modal, Connection con) {
        super(parent, modal);
        this.con = con;
        createHotelTable();
        displayHotelData();
    }

    
    //create and manage table from db
    private void createHotelTable() {
        hotelsTable = new JTable();
        jScrollPaneHotels = new JScrollPane();
        hotelsPanel = new JPanel();

        hotelsTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Hotel Name", "Postcode", "Star Rating"
            }
        ));

        jScrollPaneHotels.setViewportView(hotelsTable);
        hotelsPanel.add(jScrollPaneHotels);

        addButtons(hotelsPanel, "hotel_table", "hotelname", "postcode", "starrating");

        getContentPane().add(hotelsPanel);

        pack();
    }

    private void addButtons(JPanel panel, String tableName, String column1, String column2, String column3) {
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHotelEntry(tableName, column1, column2, column3);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editHotelEntry(tableName, column1, column2, column3);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHotelEntry(tableName);
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
    }

    
    // code for adding hotel from admin
    
    private void addHotelEntry(String tableName, String column1, String column2, String column3) {
        String entry1 = JOptionPane.showInputDialog(this, "Enter " + column1 + ":");
        String entry2 = JOptionPane.showInputDialog(this, "Enter " + column2 + ":");
        String entry3 = JOptionPane.showInputDialog(this, "Enter " + column3 + ":");

        if (entry1 != null && entry2 != null && entry3 != null) {
            try {
                pst = con.prepareStatement("INSERT INTO " + tableName + " (" + column1 + ", " + column2 + ", " + column3 + ") VALUES (?, ?, ?)");
                pst.setString(1, entry1);
                pst.setString(2, entry2);
                pst.setString(3, entry3);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Entry added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayHotelData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add entry", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    //code for editing hotels from admin
    private void editHotelEntry(String tableName, String column1, String column2, String column3) {
        int selectedRow = hotelsTable.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = (int) hotelsTable.getValueAt(selectedRow, 0);
            String entry1 = JOptionPane.showInputDialog(this, "Enter new " + column1 + ":");
            String entry2 = JOptionPane.showInputDialog(this, "Enter new " + column2 + ":");
            String entry3 = JOptionPane.showInputDialog(this, "Enter new " + column3 + ":");

            if (entry1 != null && entry2 != null && entry3 != null) {
                try {
                    pst = con.prepareStatement("UPDATE " + tableName + " SET " + column1 + "=?, " + column2 + "=?, " + column3 + "=? WHERE id=?");
                    pst.setString(1, entry1);
                    pst.setString(2, entry2);
                    pst.setString(3, entry3);
                    pst.setInt(4, hotelId);

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Entry updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        displayHotelData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update entry", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an entry to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //delete code from admin
    private void deleteHotelEntry(String tableName) {
        int selectedRow = hotelsTable.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = (int) hotelsTable.getValueAt(selectedRow, 0);
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this entry?", "Delete Entry", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    pst = con.prepareStatement("DELETE FROM " + tableName + " WHERE id=?");
                    pst.setInt(1, hotelId);

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Entry deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        displayHotelData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete entry", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an entry to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayHotelData() {
        displayData("hotel_table", "hotelname", "postcode", "starrating");
    }

    
    //display data
    private void displayData(String tableName, String column1, String column2, String column3) {
        DefaultTableModel model = (DefaultTableModel) hotelsTable.getModel();
        model.setRowCount(0);

        try {
            pst = con.prepareStatement("SELECT * FROM " + tableName);
            rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(column1), rs.getString(column2), rs.getString(column3)});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageTourismDialog(new javax.swing.JFrame(), true, null).setVisible(true);
            }
        });
    }
}

