package zavrsni.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.controller.ObradaObitelj;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class ProzorKorisnik implements ViewInterface{
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtIme;
    private JTextField txtPrezime;
    private JTextField txtEmail;
    private JTextField txtDatumRodjenja;
    private JTextField txtLozinka;
    private JButton btnNazad;
    private JButton btnDodaj;
    private JButton btnPromjeni;
    private JButton btnObrisi;
    private JTextField txtTrazi;
    private JLabel lblTrazi;
    private JButton btnTrazi;
    private DatePicker dpDatum;
    private JComboBox cmbObitelj;
    private JRadioButton rbtnZensko;
    private JRadioButton rbtnMusko;
    private JLabel lblSpolError;
    private ObradaKorisnik obrada;

public ProzorKorisnik() {
    obrada = new ObradaKorisnik();
    loadObitelj();
    settingsDate();
    settingsSpol();

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
            obrada.setEntitet((Korisnik) lstValues.getSelectedValue());

            fillView();
        }
    });
    btnDodaj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    btnPromjeni.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    btnObrisi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    btnNazad.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new Izbornik().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Izbornik");
            Alati.disposeApp(frame);
        }
    });
    btnTrazi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel<Korisnik> model = new DefaultListModel<>();
            model.addAll(obrada.read(txtTrazi.getText()));
            lstValues.setModel(model);
            lstValues.repaint();
        }
    });

    rbtnMusko.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            settingsSpol();
        }
    });
    rbtnZensko.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            settingsSpol();
        }
    });
}

    private void settingsDate(){
        DatePickerSettings dps = new DatePickerSettings(Locale.of("hr","HR"));
        dps.setFormatForDatesCommonEra("dd. MM. YYYY.");
        dps.setTranslationClear("Očisti");
        dps.setTranslationToday("Danas");
        dpDatum.setSettings(dps);


    }

    private void loadObitelj(){
        DefaultComboBoxModel<Obitelj> model = new DefaultComboBoxModel<>();
        Obitelj o = new Obitelj();
        o.setId(0);
        o.setObiteljskoPrezime("Odaberite kategoriju: ");
        model.addElement(o);

        model.addAll(new ObradaObitelj().read());

        cmbObitelj.setModel(model);
        cmbObitelj.repaint();
    }

    private void settingsSpol(){
        if (rbtnMusko.isSelected()){
            rbtnZensko.setSelected(false);
            rbtnMusko.setSelected(true);
        }
        if (rbtnZensko.isSelected()){
            rbtnMusko.setSelected(false);
            rbtnZensko.setSelected(true);
        }
    }


    @Override
    public void load() {
        DefaultListModel <Korisnik> model = new DefaultListModel<>();
        model.addAll(obrada.read());
        lstValues.setModel(model);
        lstValues.repaint();
    }

    @Override
    public void fillModel() {
        var e = obrada.getEntitet();

        e.setIme(txtIme.getText());
        e.setPrezime(txtPrezime.getText());
        e.setEmail(txtEmail.getText());
        e.setObitelj((Obitelj) cmbObitelj.getSelectedItem());
        if (rbtnMusko.isSelected()){
            e.setSpol(true);
        } else if (rbtnZensko.isSelected()){
            e.setSpol(false);
        } else{
            lblSpolError.setText("Spol nije označen");
        }

        LocalDate ld = dpDatum.getDate();

        e.setDatumRodjenja(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()));

    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        txtIme.setText(e.getIme());
        txtPrezime.setText(e.getPrezime());
        txtEmail.setText(e.getEmail());
        cmbObitelj.setSelectedItem(e.getObitelj());

        if (e.isSpol()){
            rbtnMusko.setSelected(true);
            rbtnZensko.setSelected(false);
        } else {
            rbtnZensko.setSelected(true);
            rbtnMusko.setSelected(false);
        }

        if(e.getDatumRodjenja()==null){
            dpDatum.setDate(null);
        }else{
            LocalDate ld = e.getDatumRodjenja().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dpDatum.setDate(ld);

        }

    }
}
