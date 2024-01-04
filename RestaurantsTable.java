/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package smartcity;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author janet
 */
public class RestaurantsTable extends javax.swing.JInternalFrame {

    /**
     * Creates new form HotelTable
     */
    public RestaurantsTable() {
        initComponents();
        JFrame frame = new JFrame("Restaurants Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JTable
        JTable tblRestaurant = new JTable();

        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane(tblRestaurant);
        frame.getContentPane().add(scrollPane);

        // Set size and make frame visible
        frame.setSize(600, 400);
        frame.setVisible(true);

        // Populate the table
        populateTable(tblRestaurant);
        
    }
    

    public static void populateTable(JTable tblRestaurant) {
        // Get connection
        Connection con = Connections.getConnection();

        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Restaurant Name");
        model.addColumn("Location");
        model.addColumn("Star Rating");

        try {
            // Fetch data from the database
            String query = "SELECT id, restName, location, starrating FROM restaurant_table";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Add data to the model
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("restName"),
                        rs.getString("location"),
                        rs.getString("starrating")
                });
            }

            // Set the model to the JTable
            tblRestaurant.setModel(model);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }

  


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblhotel = new javax.swing.JTable();

        tblhotel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Hotel Name", "Address", "Star Rating"
            }
        ));
        jScrollPane1.setViewportView(tblhotel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblhotel;
    // End of variables declaration//GEN-END:variables
}
