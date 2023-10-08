package zavrsni.view;

import com.github.lgooddatepicker.components.DatePicker;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.model.Korisnik;
import zavrsni.util.Alati;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProzorKorisnik implements ViewInterface{
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtIme;
    private JTextField txtPrezime;
    private JTextField txtEmail;
    private JTextField txtDatumRodjenja;
    private JTextField txtObitelj;
    private JTextField txtUloga;
    private JTextField txtLozinka;
    private JButton btnNazad;
    private JButton btnDodaj;
    private JButton btnPromjeni;
    private JButton btnObrisi;
    private JTextField txtTrazi;
    private JLabel lblTrazi;
    private JButton btnTrazi;
    private DatePicker dpDatum;
    private ObradaKorisnik obrada;

public ProzorKorisnik() {
    obrada = new ObradaKorisnik();
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
        //e.setDatumRodjenja();

        //e.setObitelj();
    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        txtIme.setText(e.getIme());
        txtPrezime.setText(e.getPrezime());
        txtEmail.setText(e.getEmail());
        txtObitelj.setText(e.getObitelj().getObiteljskoPrezime());
        txtDatumRodjenja.setText(e.getDatumRodjenja().toString());


    }
}
