package zavrsni.view;

import zavrsni.util.Alati;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Izbornik {
    protected JPanel panel;
    private JButton btnPotrosnja;
    private JButton btnStatistika;
    private JButton btnKorisnik;
    private JButton btnObitelj;
    private JButton btnKategorija;
public Izbornik() {
    btnObitelj.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new ProzorObitelj().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Obitelj");
            Alati.disposeApp(frame);
        }
    });
    btnKategorija.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new ProzorKategorija().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Kategorija");
            Alati.disposeApp(frame);
        }
    });
    btnKorisnik.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel1 = new ProzorKorisnik().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Korisnik");
            Alati.disposeApp(frame);
        }
    });
}
}
