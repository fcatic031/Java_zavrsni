package zavrsni.view;

import zavrsni.controller.ObradaKategorija;
import zavrsni.model.Kategorija;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProzorKategorija implements ViewInterface {
    protected JPanel panel;
    private JList lstValues;
    private JButton btnDodaj;
    private JButton btnPromjeni;
    private JButton btnBrisanje;
    private JLabel lblNaziv;
    private JTextField txtNaziv;
    private JButton btnNatrag;
    private JLabel lblBroj;
    private ObradaKategorija obrada;

    public ProzorKategorija() {
        obrada = new ObradaKategorija();
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
                obrada.setEntitet((Kategorija) lstValues.getSelectedValue());

                fillView();
            }
        });
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrada.setEntitet(new Kategorija());
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

                var kategorija = lstValues.getSelectedValue();
                obrada.setEntitet((Kategorija) kategorija);
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
        btnBrisanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }

                var kategorija = lstValues.getSelectedValue();
                obrada.setEntitet((Kategorija) kategorija);
                if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),((Kategorija) kategorija).getNaziv()
                        , "Sigurno obrisati?", JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
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
        btnNatrag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new Izbornik().panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1,"Izbornik",true);
                Alati.disposeApp(frame);
            }
        });
        lstValues.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {

                    Kategorija k = (Kategorija) lstValues.getSelectedValue();
                    JPanel panel1 = new ProzorDnevnaPotrosnja(k).panel;
                    JFrame frame = Alati.getFrame();
                    Alati.runApp(panel1, "Dnevna potrošnja - " + k.getNaziv(), true);
                    Alati.disposeApp(frame);

                }
            }
        });
    }

    @Override
    public void load() {
        DefaultListModel<Kategorija> model = new DefaultListModel<>();
        model.addAll(obrada.read());
        lstValues.setModel(model);
        lblBroj.setText("Broj kategorija: "+ lstValues.getModel().getSize());
        lstValues.repaint();
    }

    @Override
    public void fillModel() {
        var e = obrada.getEntitet();
        e.setNaziv(txtNaziv.getText());
    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        txtNaziv.setText(e.getNaziv());
    }
}
