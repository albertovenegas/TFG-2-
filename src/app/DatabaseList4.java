/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author albertovenegas
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseList4 extends JPanel {
    
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    public DatabaseList4() {
        super(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane, BorderLayout.CENTER);
        
        conexion con = new conexion();
        Connection cn = con.conexion();
        PreparedStatement ps;
        ResultSet rs;
        PreparedStatement ps2;
        ResultSet rs2;

        try {
            ps = cn.prepareStatement("SELECT * FROM "+main.empresa3+ "");
            rs = ps.executeQuery();
            

            while (rs.next()) {
                String observacion = rs.getString("Observaciones");
                if(observacion != null && !observacion.isEmpty()){
                    String item = rs.getString("ArtAuditar") + " - " + rs.getString("GradoMadurez")+ " - " + rs.getString("Evidencias") + " - " + rs.getString("Observaciones");
                    listModel.addElement(item);
                }else{
                    String item = rs.getString("ArtAuditar") + " - " + rs.getString("GradoMadurez");
                    listModel.addElement(item);
                }
                
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Auditoría completa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DatabaseList4 databaseList = new DatabaseList4();
        
        frame.getContentPane().add(databaseList);
        frame.setSize(400, 400);
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 400, Toolkit.getDefaultToolkit().getScreenSize().height - 400);
        frame.setVisible(true);
    }
}
