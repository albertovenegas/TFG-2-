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

public class DatabaseList2 extends JPanel {
    
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    public DatabaseList2() {
        super(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane, BorderLayout.CENTER);
        
        conexion con = new conexion();
        Connection cn = con.conexion();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cn.prepareStatement("SELECT * FROM "+main.empresa3+ " WHERE Evidencias LIKE '%No conformidad%'");
            rs = ps.executeQuery();
            

            while (rs.next()) {
                String item = rs.getString("ArtAuditar") + " - " + rs.getString("GradoMadurez") + " - " + rs.getString("Evidencias") + " - " + rs.getString("Observaciones");
                listModel.addElement(item);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
    
}
