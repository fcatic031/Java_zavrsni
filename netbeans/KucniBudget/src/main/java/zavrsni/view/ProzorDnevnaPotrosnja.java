package zavrsni.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.controller.ObradaKategorija;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
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

        }
    });
    btnTrazi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel<DnevnaPotrosnja> model = new DefaultListModel<>();
            model.addAll(obrada.read(txtTrazi.getText()));
            lstValues.setModel(model);
            lstValues.repaint();
        }
    });
    btnNatrag.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new Izbornik().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Izbornik");
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
