package zavrsni.view;

import zavrsni.util.Alati;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Izbornik {
    protected JPanel panel;
    private JButton btnPotrosnja;
    private JButton btnStatistika;
    private JButton btnKorisnik;
    private JButton btnObitelj;
    private JButton btnKategorija;
    private JLabel lblAdmin;
    private JLabel lblNaslov;

    public Izbornik() {
        lblNaslov.setFont(new Font("Ariel",Font.PLAIN,30));
    btnObitelj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JPanel panel1 = new ProzorObitelj().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(Alati.panelObitelj,"Obitelj",true);
            Alati.disposeApp(frame);
        }
    });
    btnKategorija.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JPanel panel1 = new ProzorKategorija().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(Alati.panelKategorija,"Kategorija",true);
            Alati.disposeApp(frame);
        }
    });
    btnKorisnik.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JPanel panel1 = new ProzorKorisnik().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(Alati.panelKorisnik,"Korisnik",true);
            Alati.disposeApp(frame);
        }
    });
    btnPotrosnja.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JPanel panel1 = new ProzorDnevnaPotrosnja().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(Alati.panelDnevnaPotrosnja,"Dnevna potrošnja",true);
            Alati.disposeApp(frame);
        }
    });
    btnStatistika.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new ProzorStatistika().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Statistika",true);
            Alati.disposeApp(frame);
        }
    });
}
}
