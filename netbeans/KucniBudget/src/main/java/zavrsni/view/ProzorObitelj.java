package zavrsni.view;


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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProzorObitelj implements ViewInterface{
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtNaziv;
    private JLabel lblNaziv;
    private JButton btnDodaj;
    private JButton btnPromjena;
    private JButton btnObrisati;
    private JButton btnNatrag;
    private JList lstClanova;
    private JButton btnStatistika;
    private ObradaObitelj obrada;

    public ProzorObitelj() {
        obrada= new ObradaObitelj();
        ObradaKorisnik ok = new ObradaKorisnik();
        ProzorKorisnik pk = new ProzorKorisnik();
        load();
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrada.setEntitet(new Obitelj());
                fillModel();
                try {
                    obrada.create();
                    load();
                } catch (BudgetException ex){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getPoruka());

                }
            }
        });
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

                fillView();
                loadClanovi();


            }
        });
        btnPromjena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }

                var obitelj = lstValues.getSelectedValue();
                obrada.setEntitet((Obitelj) obitelj);
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
        btnObrisati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstValues.getSelectedValue()==null){
                    return;
                }
                var obitelj = lstValues.getSelectedValue();
                obrada.setEntitet((Obitelj) obitelj);

                if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), ((Obitelj) obitelj).getObiteljskoPrezime() , "Sigurno obrisati?",
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
        btnNatrag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new Izbornik().panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1,"Izbornik");
                Alati.disposeApp(frame);
            }
        });

        lstClanova.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount()==2){

                    Korisnik k = (Korisnik) lstClanova.getSelectedValue();
                    JPanel panel1 = new ProzorKorisnik(k).panel;
                    JFrame frame = Alati.getFrame();
                    Alati.runApp(panel1,"Korisnik");
                    Alati.disposeApp(frame);

                }
            }
        });
    }

    @Override
    public void load() {
        DefaultListModel<Obitelj> model = new DefaultListModel<>();
        model.addAll(obrada.read());
        lstValues.setModel(model);
        lstValues.repaint();

    }

    public void loadClanovi(){
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


    @Override
    public void fillModel() {
        var e = obrada.getEntitet();
        e.setObiteljskoPrezime(txtNaziv.getText());
    }

    @Override
    public void fillView() {
        var e = obrada.getEntitet();

        txtNaziv.setText(e.getObiteljskoPrezime());

    }
}
