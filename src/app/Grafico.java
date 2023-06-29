/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Grafico extends JPanel {
    public Grafico() {
        super(new BorderLayout());
        crearGrafico();
    }

    private void crearGrafico() {
        conexion con = new conexion();
        Connection cn = con.conexion();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cn.prepareStatement("SELECT ArtAuditar, GradoMadurez FROM " + main.empresa3);
            rs = ps.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            DefaultPieDataset datasetCircular = new DefaultPieDataset();

            while (rs.next()) {
                String valor = rs.getString("GradoMadurez");

                if (dataset.getColumnKeys().contains(valor)) {
                    Double ocurrenciasDouble = (Double) dataset.getValue("Ocurrencias", valor);
                    int ocurrencias = ocurrenciasDouble.intValue() + 1;
                    dataset.setValue(ocurrencias, "Ocurrencias", valor);
                } else {
                    dataset.addValue(1, "Ocurrencias", valor);
                }

                if (datasetCircular.getKeys().contains(valor)) {
                    double porcentaje = datasetCircular.getValue(valor).doubleValue() + 1.0;
                    datasetCircular.setValue(valor, porcentaje);
                } else {
                    datasetCircular.setValue(valor, 1.0);
                }
            }

            JFreeChart chart = ChartFactory.createBarChart("Gráfico de barras de Grados de Madurez", "Grado de Madurez", "Ocurrencias", dataset);
            JFreeChart chartCircular = ChartFactory.createPieChart("Gráfico circular de Grados de Madurez", datasetCircular, true, true, false);

            ChartPanel chartPanel = new ChartPanel(chart);
            ChartPanel circularChartPanel = new ChartPanel(chartCircular);

            add(chartPanel, BorderLayout.CENTER);
            add(circularChartPanel, BorderLayout.SOUTH);
        } catch (Exception e) {
            System.out.println("Error al procesar los resultados de la consulta SQL");
            e.printStackTrace();
        }
    }
}
