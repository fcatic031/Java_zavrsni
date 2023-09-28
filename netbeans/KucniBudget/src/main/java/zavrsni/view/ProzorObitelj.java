package zavrsni.view;

import zavrsni.controller.ObradaObitelj;
import zavrsni.model.Obitelj;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorObitelj implements ViewInterface{
    protected JPanel panel;
    private JList lstValues;
    private JTextField txtNaziv;
    private JLabel lblNaziv;
    private JButton btnDodaj;
    private ObradaObitelj obrada;

    public ProzorObitelj() {
        obrada= new ObradaObitelj();
        load();
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    @Override
    public void fillModel() {

    }

    @Override
    public void fillView() {

    }
}
