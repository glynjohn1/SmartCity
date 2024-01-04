/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartcity;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author janet
 */
public class Connections {
    static Connection con;
    
    //connect to database
    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartcitydb","root","");
                                                                                //database name, username and password
        }catch (Exception ex) {
            System.out.println("" + ex);
        }
        return con;
    }
    
}
