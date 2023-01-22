package Extract;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import javax.swing.*;

public class job_piechart3D extends JFrame {
    private int a, b, c, d;
    private String chartTitle;
    public job_piechart3D(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        this.chartTitle = chartTitle;
    }
    public void presentdata(){
        // This will create the dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("WEXITSTATUS 0", a);
        dataset.setValue("WEXITSTATUS 1", b);
        dataset.setValue("WEXITSTATUS 2", c);
        dataset.setValue("WEXITSTATUS other than 0,1,2", d);
        // based on the dataset we create the chart
        JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        // add it to our application
        setContentPane(chartPanel);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
    }

    public void getdata(int a, int b, int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public void pie() {
        //PieChart3D chart = new PieChart3D("NUmber ", "Completed jobs based on WEXITSTATUS");
        pack();
        setVisible(true);
    }
}