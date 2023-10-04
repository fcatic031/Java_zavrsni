package zavrsni.view;

import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.model.DnevnaPotrosnja;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.math.BigDecimal;

public class ProzorDnevnaPotrosnja implements ViewInterface {
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtDatum;
    private JTextField txtKorisnik;
    private JTextField txtKategorija;
    private JTextField txtPotrosnja;


    private ObradaDnevnaPotrosnja obrada;

public ProzorDnevnaPotrosnja() {
    obrada = new ObradaDnevnaPotrosnja();
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
        //e.setDatum(new Date(txtDatum.getText()));
        //e.setKorisnik(txtKorisnik.getText());
        //e.setKategorija(txtKategorija.getText());
        e.setPotrosnja(new BigDecimal(txtPotrosnja.getText()));
    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        txtPotrosnja.setText(e.getPotrosnja().toString());
        
    }
}
