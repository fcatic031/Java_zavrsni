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
