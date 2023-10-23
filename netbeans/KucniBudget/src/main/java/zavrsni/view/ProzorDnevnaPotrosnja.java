package zavrsni.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.checkerframework.checker.units.qual.A;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.controller.ObradaKategorija;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class ProzorDnevnaPotrosnja implements ViewInterface {
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtPotrosnja;
    private JComboBox cmbKorisnik;
    private JComboBox cmbKategorija;
    private DatePicker dpDatum;
    private JTextField txtTrazi;
    private JButton btnTrazi;
    private JButton btnJSON;
    private JButton btnNatrag;
    private JButton btnDodaj;
    private JButton btnPromjeni;
    private JButton btnObrisi;
    private JLabel lblBroj;


    private ObradaDnevnaPotrosnja obrada;

public ProzorDnevnaPotrosnja() {
    obrada = new ObradaDnevnaPotrosnja();

    loadKorisnik();
    loadKategorija();
    settingsDate();
    load();

    lstValues.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()){
                return;
            }
            if (lstValues.getSelectedValue()==null){
                return;
            }
            obrada.setEntitet((DnevnaPotrosnja) lstValues.getSelectedValue());

            fillView();

        }
    });

    btnTrazi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
            model.addAll(obrada.read(txtTrazi.getText()));
            lstValues.setModel(model);
            lstValues.repaint();
            Alati.potrosnje = lstValues.getModel().getSize();
            lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);
        }
    });
    btnNatrag.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new Izbornik().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Izbornik",true);
            Alati.disposeApp(frame);
        }
    });
    btnDodaj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            obrada.setEntitet(new DnevnaPotrosnja());
            fillModel();
            try {
                obrada.create();
                load();
            } catch (BudgetException ex){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
            }
        }
    });
    btnPromjeni.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lstValues.getSelectedValue()==null){
                return;
            }

            var dnevnaPotrosnja = lstValues.getSelectedValue();
            obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);
            fillModel();
            try {
                obrada.update();
                load();
            } catch (BudgetException ex){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                obrada.refresh();
            }
        }
    });
    btnObrisi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lstValues.getSelectedValue()==null){
                return;
            }
            var dnevnaPotrosnja = lstValues.getSelectedValue();
            obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);

            if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), ((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getIme()
                            +" "+((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getPrezime()
                            +"\n"+((DnevnaPotrosnja) dnevnaPotrosnja).getDatum().toString()
                            +"\n"+((Kategorija)((DnevnaPotrosnja) dnevnaPotrosnja).getKategorija()).getNaziv(), "Sigurno obrisati?",
                    JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
                return;
            }

            try {
                obrada.delete();
                load();
            } catch (BudgetException ex){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
            }
        }
    });
    btnJSON.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new ProzorJSON(txtTrazi.getText()).panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"JSON - Dnevne potrošnje",true);
            //Alati.disposeApp(frame);
        }
    });
}


    public ProzorDnevnaPotrosnja(Korisnik k) {
        obrada = new ObradaDnevnaPotrosnja();

        loadKorisnik(k);
        loadKategorija();
        settingsDate();
        load(k);


        lstValues.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    return;
                }
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                obrada.setEntitet((DnevnaPotrosnja) lstValues.getSelectedValue());

                fillView();

            }
        });

        btnTrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
                model.addAll(obrada.read(k,txtTrazi.getText()));
                lstValues.setModel(model);
                lstValues.repaint();
                Alati.potrosnje = lstValues.getModel().getSize();
                lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);
            }
        });
        btnNatrag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = Alati.getFrame();
                //Alati.runApp((Alati.OPERATER.getUloga()) ? (Alati.panelKorisnik,"Korisnik",true) : (Alati.getPanelIzbornikKorisnik,"Korisnik",true);
                //(Alati.OPERATER.getUloga()) ? Alati.runApp(Alati.panelKorisnik,"Korisnik",true) : Alati.runApp(Alati.PanelIzbornikKorisnik,"Izbornik",true);
                if(Alati.OPERATER.getUloga()){
                    Alati.runApp(Alati.panelKorisnik,"Korisnik",true);
                } else {
                    Alati.runApp(Alati.panelIzbornik,"Izbornik",true);
                }
                Alati.disposeApp(frame);
            }
        });
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrada.setEntitet(new DnevnaPotrosnja());
                fillModel();
                try {
                    obrada.create();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnPromjeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }

                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);
                fillModel();
                try {
                    obrada.update();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                    obrada.refresh();
                }
            }
        });
        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);

                if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), ((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getIme()
                                +" "+((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getPrezime()
                                +"\n"+((DnevnaPotrosnja) dnevnaPotrosnja).getDatum().toString()
                                +"\n"+((Kategorija)((DnevnaPotrosnja) dnevnaPotrosnja).getKategorija()).getNaziv(), "Sigurno obrisati?",
                        JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
                    return;
                }

                try {
                    obrada.delete();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new ProzorJSON(k.getIme()+" "+k.getPrezime()).panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1,"JSON - Dnevne potrošnje",true);
                //Alati.disposeApp(frame);
            }
        });
    }

    public ProzorDnevnaPotrosnja(Obitelj o) {
        obrada = new ObradaDnevnaPotrosnja();

        loadClanove(o);
        loadKategorija();
        settingsDate();
        load(o);

        lstValues.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    return;
                }
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                obrada.setEntitet((DnevnaPotrosnja) lstValues.getSelectedValue());

                fillView();

            }
        });

        btnTrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
                model.addAll(obrada.read(o,txtTrazi.getText()));
                lstValues.setModel(model);
                lstValues.repaint();
                Alati.potrosnje = lstValues.getModel().getSize();
                lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);
            }
        });
        btnNatrag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = Alati.getFrame();
                Alati.runApp(Alati.panelObitelj,"Obitelj",true);
                Alati.disposeApp(frame);
            }
        });
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrada.setEntitet(new DnevnaPotrosnja());
                fillModel();
                try {
                    obrada.create();
                    load(o);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnPromjeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }

                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);
                fillModel();
                try {
                    obrada.update();
                    load(o);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                    obrada.refresh();
                }
            }
        });
        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);

                if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), ((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getIme()
                                +" "+((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getPrezime()
                                +"\n"+((DnevnaPotrosnja) dnevnaPotrosnja).getDatum().toString()
                                +"\n"+((Kategorija)((DnevnaPotrosnja) dnevnaPotrosnja).getKategorija()).getNaziv(), "Sigurno obrisati?",
                        JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
                    return;
                }

                try {
                    obrada.delete();
                    load(o);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JPanel panel1 = new ProzorJSON(k.getIme()+" "+k.getPrezime()).panel;
                //JFrame frame = Alati.getFrame();
                //Alati.runApp(panel1,"JSON - Dnevne potrošnje",true);
                //Alati.disposeApp(frame);
            }
        });
    }

    public ProzorDnevnaPotrosnja(Kategorija k) {
        obrada = new ObradaDnevnaPotrosnja();

        loadKorisnik();
        loadKategorija(k);
        settingsDate();
        load(k);


        lstValues.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()){
                    return;
                }
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                obrada.setEntitet((DnevnaPotrosnja) lstValues.getSelectedValue());

                fillView();

            }
        });

        btnTrazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
                model.addAll(obrada.read(k,txtTrazi.getText()));
                lstValues.setModel(model);
                lstValues.repaint();
                Alati.potrosnje = lstValues.getModel().getSize();
                lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);
            }
        });
        btnNatrag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = Alati.getFrame();
                //Alati.runApp((Alati.OPERATER.getUloga()) ? (Alati.panelKorisnik,"Korisnik",true) : (Alati.getPanelIzbornikKorisnik,"Korisnik",true);
                //(Alati.OPERATER.getUloga()) ? Alati.runApp(Alati.panelKorisnik,"Korisnik",true) : Alati.runApp(Alati.PanelIzbornikKorisnik,"Izbornik",true);
                if(Alati.OPERATER.getUloga()){
                    Alati.runApp(Alati.panelKorisnik,"Korisnik",true);
                } else {
                    Alati.runApp(Alati.panelIzbornik,"Izbornik",true);
                }
                Alati.disposeApp(frame);
            }
        });
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrada.setEntitet(new DnevnaPotrosnja());
                fillModel();
                try {
                    obrada.create();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnPromjeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }

                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);
                fillModel();
                try {
                    obrada.update();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                    obrada.refresh();
                }
            }
        });
        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                var dnevnaPotrosnja = lstValues.getSelectedValue();
                obrada.setEntitet((DnevnaPotrosnja) dnevnaPotrosnja);

                if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), ((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getIme()
                                +" "+((Korisnik)((DnevnaPotrosnja) dnevnaPotrosnja).getKorisnik()).getPrezime()
                                +"\n"+((DnevnaPotrosnja) dnevnaPotrosnja).getDatum().toString()
                                +"\n"+((Kategorija)((DnevnaPotrosnja) dnevnaPotrosnja).getKategorija()).getNaziv(), "Sigurno obrisati?",
                        JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
                    return;
                }

                try {
                    obrada.delete();
                    load(k);
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());
                }
            }
        });
        btnJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JPanel panel1 = new ProzorJSON(k.getIme()+" "+k.getPrezime()).panel;
                //JFrame frame = Alati.getFrame();
                //Alati.runApp(panel1,"JSON - Dnevne potrošnje",true);
                //Alati.disposeApp(frame);
            }
        });
    }

public void loadKorisnik(){
    DefaultComboBoxModel<Korisnik> model = new DefaultComboBoxModel<>();
    Korisnik k = new Korisnik();
    k.setId(0);
    k.setIme("Odaberite ");
    k.setPrezime("korisnika: ");
    model.addElement(k);
    model.addAll(new ObradaKorisnik().read());

    cmbKorisnik.setModel(model);
    cmbKorisnik.repaint();
}

public void loadKorisnik(Korisnik k){
    DefaultComboBoxModel<Korisnik> model = new DefaultComboBoxModel<>();
    model.addElement(k);

    //model.addAll(new ObradaKorisnik().read());

    cmbKorisnik.setModel(model);
    cmbKorisnik.repaint();
}

    public void loadClanove(Obitelj o){
        DefaultComboBoxModel<Korisnik> model = new DefaultComboBoxModel<>();
        for (Korisnik k : o.getClanovi()){
            model.addElement(k);
        }

        //model.addAll(new ObradaKorisnik().read());

        cmbKorisnik.setModel(model);
        cmbKorisnik.repaint();
    }

public void loadKategorija(){
    DefaultComboBoxModel<Kategorija> model = new DefaultComboBoxModel<>();
    Kategorija k = new Kategorija();
    k.setId(0);
    k.setNaziv("Odaberite kategoriju: ");
    model.addElement(k);

    model.addAll(new ObradaKategorija().read());

    cmbKategorija.setModel(model);
    cmbKategorija.repaint();
}

    public void loadKategorija(Kategorija k){
        DefaultComboBoxModel<Kategorija> model = new DefaultComboBoxModel<>();
        model.addElement(k);

        cmbKategorija.setModel(model);
        cmbKategorija.repaint();
    }
public void settingsDate(){
    DatePickerSettings dps = new DatePickerSettings(Locale.of("hr","HR"));
    dps.setFormatForDatesCommonEra("dd. MM. YYYY.");
    dps.setTranslationClear("Očisti");
    dps.setTranslationToday("Danas");
    dpDatum.setSettings(dps);


}

    @Override
    public void load() {
        DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
        model.addAll(obrada.read());
        lstValues.setModel(model);
        lstValues.repaint();
        Alati.potrosnje = lstValues.getModel().getSize();
        lblBroj.setText("Broj potrošnji: " +Alati.potrosnje);

    }

    public void load(Korisnik k){
        DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
        model.addAll(obrada.read(k));
        lstValues.setModel(model);
        lstValues.repaint();
        Alati.potrosnje = lstValues.getModel().getSize();
        lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);
    }

    public void load(Obitelj o){
        DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
        model.addAll(obrada.read(o));
        lstValues.setModel(model);
        lstValues.repaint();
        Alati.potrosnje = lstValues.getModel().getSize();
        lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);

    }

    public void load(Kategorija k){
        DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
        model.addAll(obrada.read(k));
        lstValues.setModel(model);
        lstValues.repaint();
        Alati.potrosnje = lstValues.getModel().getSize();
        lblBroj.setText("Broj potrošnji: "+Alati.potrosnje);

    }

    @Override
    public void fillModel() {
        var e = obrada.getEntitet();
        e.setKorisnik((Korisnik) cmbKorisnik.getSelectedItem());
        e.setKategorija((Kategorija) cmbKategorija.getSelectedItem());
        e.setPotrosnja(new BigDecimal(txtPotrosnja.getText()));

        LocalDate ld = dpDatum.getDate();

        e.setDatum(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()));

    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        cmbKategorija.setSelectedItem(e.getKategorija());
        cmbKorisnik.setSelectedItem(e.getKorisnik());
        txtPotrosnja.setText(e.getPotrosnja().toString());

        if(e.getDatum()==null){
            dpDatum.setDate(null);
        }else{
            LocalDate ld = e.getDatum().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dpDatum.setDate(ld);

        }

    }
}
