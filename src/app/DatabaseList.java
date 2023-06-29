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

public class DatabaseList extends JPanel {
    private DefaultListModel<String> listModel1, listModel2;
    private JList<String> list1, list2;

    public DatabaseList() {
        super(new GridLayout(1,2));
        listModel1 = new DefaultListModel<>();
        list1 = new JList<>(listModel1);
        JScrollPane scrollPane1 = new JScrollPane(list1);

        listModel2 = new DefaultListModel<>();
        list2 = new JList<>(listModel2);
        JScrollPane scrollPane2 = new JScrollPane(list2);

        add(scrollPane1);
        add(scrollPane2);

        conexion con = new conexion();
        Connection cn = con.conexion();
        PreparedStatement ps1, ps2;
        ResultSet rs1, rs2;

        try {
            ps1 = cn.prepareStatement("SELECT ArtAuditar, GradoMadurez FROM "+main.empresa3+ " WHERE GradoMadurez = 'L1' OR GradoMadurez = 'L2'");
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                String item = rs1.getString("ArtAuditar") + " - " + rs1.getString("GradoMadurez");
                listModel1.addElement(item);
            }
            rs1.close();

            ps2 = cn.prepareStatement("SELECT ArtAuditar, GradoMadurez FROM "+main.empresa3+ " WHERE GradoMadurez = 'L4' OR GradoMadurez = 'L5'");
            rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String item = rs2.getString("ArtAuditar") + " - " + rs2.getString("GradoMadurez");
                listModel2.addElement(item);
            }
            rs2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
