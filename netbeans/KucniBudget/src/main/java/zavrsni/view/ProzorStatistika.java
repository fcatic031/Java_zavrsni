package zavrsni.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.IntervalXYDataset;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.controller.ObradaObitelj;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ProzorStatistika {
    protected JPanel panel;
    private JList lstValues;
    private JList lstClanova;
    private JPanel panelGraf1;
    private JCheckBox chbClanovi;
    private ObradaObitelj obrada;
    private ObradaKorisnik obradaKorisnik;
    public ProzorStatistika() {
        obrada= new ObradaObitelj();
        obradaKorisnik = new ObradaKorisnik();
        load();
        prviGraf(new Obitelj());


    lstValues.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()){
                return;
            }
            if (lstValues.getSelectedValue()==null){
                return;
            }
            obrada.setEntitet((Obitelj) lstValues.getSelectedValue());

            loadClanovi();
        }
    });
    lstClanova.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    });
}
    private void loadClanovi(){
        DefaultListModel<Korisnik> model = new DefaultListModel<>();
        Obitelj e = obrada.getEntitet();
        if (lstValues.getSelectedValue()!=null){
            int i = 0;
            for (Korisnik k : e.getClanovi()){
                model.add(i,k);
                i++;
            }
            lstClanova.setModel(model);
            lstClanova.repaint();
        }
    }

    public void load() {
        DefaultListModel<Obitelj> model = new DefaultListModel<>();
        model.addAll(obrada.read());
        lstValues.setModel(model);
        lstValues.repaint();
    }

    private void prviGraf(Obitelj o){

        for (int i =0; i<12;i++){

        }
        double[] dataArray = {2.00,3.22,4.00};
        //JFreeChart chart = ChartFactory.createHistogram("Po mjesecu" , "Mjeseci","Potrošnja",dataset);
        JFreeChart chart = getHistogramChart("Test", dataArray);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(3000);
        chartPanel.setMaximumDrawWidth(3000);

        panelGraf1.setLayout(new BorderLayout());
        panelGraf1.add(chartPanel, BorderLayout.CENTER);
        panelGraf1.validate();

        //frame.add(chartPanel);
        //frame.setSize(2500, 600);
        //frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
    }

    private static JFreeChart getHistogramChart(String name, double[] dataArray)
    {
        String plotTitle = name;
        String xAxisLabel = "Length of transaction";
        String yAxis = "Frequency";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (int i = 0; i < dataArray.length; i++)
        {
            dataSet.addValue(dataArray[i], (Integer) 0, (Integer) i);
        }
        boolean show = true;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createBarChart(plotTitle, xAxisLabel,
                yAxis, dataSet, orientation, show, toolTips, urls);
        chart.setBackgroundPaint(Color.WHITE);

        // Set a very small font for the labels, and rotate them...
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 8));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        return chart;
    }

}