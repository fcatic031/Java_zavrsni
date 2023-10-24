package zavrsni.util;

import zavrsni.controller.ObradaObitelj;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;

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
    public static boolean panelSplashFact;

    public static Integer potrosnje;

    private static void dodavanjeObitelji(){

        ObradaObitelj ob = new ObradaObitelj();

        Obitelj o = new Obitelj();
        o.setObiteljskoPrezime(null);
        ob.setEntitet(o);
        try {
            ob.create();
        } catch (BudgetException b){
            System.out.println(b.getPoruka());
        }
        System.out.println("GOTOVOOO");
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


    public static void runAppAdd(JPanel panel,String title,Boolean visible){
        //samo je različito to da se cijela aplikacije ne zatvara koristenjem ove naredbe
        frame= new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
