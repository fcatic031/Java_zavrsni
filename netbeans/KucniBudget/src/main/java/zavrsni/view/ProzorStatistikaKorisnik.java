package zavrsni.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.controller.ObradaKategorija;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.controller.ObradaObitelj;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProzorStatistikaKorisnik {
    protected JPanel panel;
    private JLabel lblObitelj;
    private JList lstPotrosnje;
    private JButton btnObitelj;
    private JComboBox cmbKategorija;
    private JComboBox cmbGodina;
    private JButton btnNazad;
    private JList lstClanovi;
    private JLabel lblBroj;
    private ObradaObitelj obrada;
    private ObradaKorisnik obradaKorisnik;
    private ObradaDnevnaPotrosnja obradaDnevnaPotrosnja;
    private JPanel panelGraf1;





public ProzorStatistikaKorisnik() {
    obradaDnevnaPotrosnja = new ObradaDnevnaPotrosnja();
    obrada= new ObradaObitelj();
    obradaKorisnik = new ObradaKorisnik();
    loadKategorija();
    loadGodina();
    loadClanovi();
    loadPotrosnje();
    lblObitelj.setText(Alati.OPERATER.getObitelj().getObiteljskoPrezime());
    btnNazad.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = Alati.getFrame();
            Alati.runApp(Alati.panelIzbornik,"Izbornik",true);
            Alati.disposeApp(frame);

        }
    });

    btnObitelj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelGraf1.removeAll();
            int godina = (2023-cmbGodina.getItemCount())+cmbGodina.getSelectedIndex()+1; //+1 zbog prvog item-a
            obiteljGraf(Alati.OPERATER.getObitelj(),godina,(Kategorija) cmbKategorija.getSelectedItem());

        }
    });
    lstClanovi.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            panelGraf1.removeAll();
            if (e.getClickCount()==2){
                int godina = (2023-cmbGodina.getItemCount())+cmbGodina.getSelectedIndex()+1; //+1 zbog prvog item-a

                korisnikGraf((Korisnik) lstClanovi.getSelectedValue(),godina,(Kategorija) cmbKategorija.getSelectedItem());

            }
        }
    });
}
    private void loadClanovi(){
        DefaultListModel<Korisnik> model = new DefaultListModel<>();
        Obitelj o = Alati.OPERATER.getObitelj();
        int i=0;
        for (Korisnik k : o.getClanovi()){
            model.add(i,k);
            i++;
        }
            lstClanovi.setModel(model);
            lstClanovi.repaint();
        }
    public void loadKategorija(){
        DefaultComboBoxModel<Kategorija> model = new DefaultComboBoxModel<>();
        Kategorija k = new Kategorija();
        k.setId(0);
        k.setNaziv("Sve kategorije ");
        model.addElement(k);

        model.addAll(new ObradaKategorija().read());

        cmbKategorija.setModel(model);
        cmbKategorija.repaint();
    }

    public void loadGodina(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Neodređeno ");
        for (int i=2020;i<=2023;i++){
            model.addElement(i+".");
        }
        cmbGodina.setModel(model);
        cmbGodina.repaint();
    }

    public void loadPotrosnje(){
        DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
        Obitelj o = Alati.OPERATER.getObitelj();
        model.addAll(obradaDnevnaPotrosnja.read(o));
        lstPotrosnje.setModel(model);
        lstPotrosnje.repaint();
        Alati.potrosnje = lstPotrosnje.getModel().getSize();
        lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);

    }

    private void obiteljGraf(Obitelj o, int godina,Kategorija kategorija){
        double[] podaci= new double[12];
        double ukupno=0;
        double ukupno2;
        for (int i = 0; i<12; i++){
            ukupno2 = 0;
            for (Korisnik clan: o.getClanovi()) {
                for (DnevnaPotrosnja dp : clan.getPotrosnje()) {
                    if (dp.getDatum().getMonth() == i) {
                        //nisu određene ni godina ni kategorija
                        if (cmbGodina.getSelectedIndex() == 0 && cmbKategorija.getSelectedIndex() == 0) {
                            ukupno2 += dp.getPotrosnja().doubleValue();
                        }
                        //određena godina ali ne kategorija
                        if (cmbKategorija.getSelectedIndex()==0 && cmbGodina.getSelectedIndex() != 0 && dp.getDatum().getYear() == (godina - 1900)) {
                            ukupno2 += dp.getPotrosnja().doubleValue();
                        }
                        //određena kategorija ali ne godina
                        if (cmbGodina.getSelectedIndex() == 0 && cmbKategorija.getSelectedIndex()!=0 && dp.getKategorija() == kategorija) {
                            ukupno2 += dp.getPotrosnja().doubleValue();
                        }
                        //određena godina i kategorija
                        if (cmbKategorija.getSelectedIndex()!=0  && cmbGodina.getSelectedIndex() != 0 && dp.getDatum().getYear() == (godina - 1900) && dp.getKategorija() == kategorija) {
                            ukupno2 += dp.getPotrosnja().doubleValue();
                        }
                    }
                }
            }

            ukupno+=ukupno2;
            podaci [i] = ukupno2;
        }

        JFreeChart chart = getHistogramChart(o.getObiteljskoPrezime()+" : "+
                (cmbGodina.getSelectedIndex()!=0 ? godina : cmbGodina.getItemAt(1) +"-"+cmbGodina.getItemAt(cmbGodina.getItemCount()-1)) +" : "+
                (cmbKategorija.getSelectedIndex()!=0 ? kategorija.getNaziv() : "Sve kategorije")+" : "+ukupno+" €", podaci);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(3000);
        chartPanel.setMaximumDrawWidth(3000);
        panelGraf1.setLayout(new BorderLayout());
        panelGraf1.add(chartPanel, BorderLayout.CENTER);
        panelGraf1.validate();

        JFrame frame = new JFrame();
        frame.setContentPane(panelGraf1);
        frame.add(chartPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void korisnikGraf(Korisnik k, int godina,Kategorija kategorija){
        double[] podaci= new double[12];
        double ukupno=0;
        double ukupno2;
        for (int i = 0; i<12; i++){
            ukupno2 = 0;
            for (DnevnaPotrosnja dp : k.getPotrosnje()) {
                if (dp.getDatum().getMonth() == i) {
                    //nisu određene ni godina ni kategorija
                    if (cmbGodina.getSelectedIndex() == 0 && cmbKategorija.getSelectedIndex() == 0) {
                        ukupno2 += dp.getPotrosnja().doubleValue();
                    }
                    //određena godina ali ne kategorija
                    if (cmbKategorija.getSelectedIndex()==0 && cmbGodina.getSelectedIndex() != 0 && dp.getDatum().getYear() == (godina - 1900)) {
                        ukupno2 += dp.getPotrosnja().doubleValue();
                    }
                    //određena kategorija ali ne godina
                    if (cmbGodina.getSelectedIndex() == 0 && cmbKategorija.getSelectedIndex()!=0 && dp.getKategorija() == kategorija) {
                        ukupno2 += dp.getPotrosnja().doubleValue();
                    }
                    //određena godina i kategorija
                    if (cmbKategorija.getSelectedIndex()!=0  && cmbGodina.getSelectedIndex() != 0 && dp.getDatum().getYear() == (godina - 1900) && dp.getKategorija() == kategorija) {
                        ukupno2 += dp.getPotrosnja().doubleValue();
                    }
                }
            }
            ukupno +=ukupno2;
            podaci [i] = ukupno2;
        }

        JFreeChart chart = getHistogramChart(k.getIme()+" "+k.getPrezime()+": "+
                (cmbGodina.getSelectedIndex()!=0 ? godina : cmbGodina.getItemAt(1) +"-"+cmbGodina.getItemAt(cmbGodina.getItemCount()-1)) +" : "+
                (cmbKategorija.getSelectedIndex()!=0 ? kategorija.getNaziv() : "Sve kategorije")+" : "+ukupno+" €", podaci);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(3000);
        chartPanel.setMaximumDrawWidth(3000);
        panelGraf1.setLayout(new BorderLayout());
        panelGraf1.add(chartPanel, BorderLayout.CENTER);
        panelGraf1.validate();

        JFrame frame = new JFrame();
        frame.setContentPane(panelGraf1);
        frame.add(chartPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JFreeChart getHistogramChart(String name, double[] dataArray) {
        String plotTitle = name;
        String xAxisLabel = "Mjeseci";
        String yAxisLabel = "Potrošnja";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String[] mjeseci = {"Siječanj","Veljača","Ožujak","Travanj","Svibanj","Lipanj",
                "Srpanj","Kolovoz","Rujan","Listopad","Studeni","Prosinac"};
        for (int i = 0; i < dataArray.length; i++)
        {
            dataSet.addValue(dataArray[i], (Integer) 0, (String) mjeseci[i]);
        }
        boolean show = true;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createBarChart(plotTitle, xAxisLabel,
                yAxisLabel, dataSet, orientation, show, toolTips, urls);
        chart.setBackgroundPaint(Color.WHITE);

        //Kopirano iz stackflow-a
        // Set a very small font for the labels, and rotate them...
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 8));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        return chart;
    }
}
