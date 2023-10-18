package zavrsni.view;

import zavrsni.util.Alati;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzbornikKorisnik {
    private JButton btnPotrosnja;
    private JButton btnStatistika;
    private JLabel lblNaslov;
    protected JPanel panel;

    public IzbornikKorisnik(){
        lblNaslov.setFont(new Font("Ariel",Font.PLAIN,30));

        btnPotrosnja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel1 = new ProzorDnevnaPotrosnja(Alati.OPERATER).panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1,"Potrošnje");
                Alati.disposeApp(frame);
            }
        });
    }
}
