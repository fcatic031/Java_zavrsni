package zavrsni.view;

import org.apache.commons.validator.routines.EmailValidator;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.model.Korisnik;
import zavrsni.util.Alati;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Autorizacija {

    private ObradaKorisnik obrada;

    private JButton btnAutoriziraj;
    private JTextField txtEmail;
    private JLabel lblLozinka;
    private JLabel lblEmail;
    public JPanel panel;
    private JLabel lblEmailError;
    private JLabel lblLozinkaError;
    private JPasswordField txtLozinka;

    public Autorizacija() {
        obrada = new ObradaKorisnik();
        btnAutoriziraj.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            var email = txtEmail.getText();
            reset();

            if (email.isEmpty()){
                error(txtEmail);
                lblEmailError.setText("Email obavezno");
                return;
            }

            if (!EmailValidator.getInstance().isValid(email)){
                error(txtEmail);
                lblEmailError.setText("Email nije validan");
                return;
            }

            if (txtLozinka.getPassword().length==0){
                error(txtLozinka);
                lblLozinkaError.setText("Lozinka obavezno");
                return;
            }

            Korisnik k = obrada.autoriziraj(email,new String(txtLozinka.getPassword()));

            if (k==null){
                System.out.println("Neispravna kombinacija");
                return;
            }
            Alati.OPERATER = k;

            if (k.getUloga()){
                //Alati.panelSplash= new SplashScreen(false).panel;
                JPanel panel1 = new SplashScreen(false).panel;
                //Alati.panelSplashFact=false;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1,"LOADING...",true);
                Alati.disposeApp(frame);
            } else{
                Alati.panelIzbornik = new IzbornikKorisnik().panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(Alati.panelIzbornik,"Izbornik",true);

                Alati.panelDnevnaPotrosnja= new ProzorDnevnaPotrosnja(k).panel;
                Alati.runApp(Alati.panelDnevnaPotrosnja,"Dnevna potrošnja",false);

                Alati.disposeApp(frame);
            }

            //System.out.println("Uspjeh");
        }
    });
}

    private void error(JComponent c){
        c.setBackground(Color.RED);
        c.requestFocus();
    }

    private void reset(){
        lblEmailError.setText("");
        txtEmail.setBackground(Color.WHITE);
        txtLozinka.setBackground(Color.WHITE);
    }
}
