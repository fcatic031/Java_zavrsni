package zavrsni.view;

import zavrsni.controller.ObradaKorisnik;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

    }

    @Override
    public void fillView() {

    }
}
