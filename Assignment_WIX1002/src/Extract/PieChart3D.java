package Extract;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;

public class PieChart3D extends JFrame {

    public PieChart3D(String applicationTitle, String chartTitle,HashMap<String, Integer> nodelist_map) {
        super(applicationTitle);
        // This will create the dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (String line : nodelist_map.keySet()) {
        	dataset.setValue(line, nodelist_map.get(line));
        }
        // based on the dataset we create the chart
        JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

//    public static void main(String[] args) {
//        PieChart3D chart = new PieChart3D("Doughnut Chart", "Number of jobs by partitions");
//        chart.pack();
//        chart.setVisible(true);
//    }
}
