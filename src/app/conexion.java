/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albertovenegas
 */
public class conexion {
    
    Connection cn;

    public conexion() {
    }
    Statement st;
   

    
    public Connection conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_auditoria", "root", "");
            System.out.println("Si se conectó.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conectó.");
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }
    
    public static void main (String[] args){
        conexion conexion = new conexion();
         conexion.conexion();
        
}
    
}
