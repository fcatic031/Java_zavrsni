package zavrsni.util;

import zavrsni.model.Korisnik;

import javax.swing.*;

public class Alati {
    public static Korisnik OPERATER;
    public static JFrame frame;
    public static JPanel panelIzbornik;
    public static JPanel panelKorisnik;
    public static JPanel panelObitelj;
    public static JPanel panelDnevnaPotrosnja;
    public static JPanel panelKategorija;
    public static JPanel panelSplash;

    public static String getOperater(){
        return OPERATER.getIme()+" "+ OPERATER.getPrezime()+" ("+OPERATER.getUloga()+ ")";
    }

    public static void runApp(JPanel panel,String title,Boolean visible){
        frame= new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setSize(800,600);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }


    public static JFrame getFrame(){
        return frame;
    }

    public static void disposeApp(JFrame frame2){
        frame2.dispose();
    }


}
