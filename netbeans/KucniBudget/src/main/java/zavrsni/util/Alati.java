package zavrsni.util;

import zavrsni.model.Korisnik;

import javax.swing.*;

public class Alati {
    public static Korisnik OPERATER;
    public static JFrame frame;

    public static String getOperater(){
        return OPERATER.getIme()+" "+ OPERATER.getPrezime()+" ("+OPERATER.getUloga()+ ")";
    }

    public static void runApp(JPanel panel,String title){
        frame= new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public static JFrame getFrame(){
        return frame;
    }

    public static void disposeApp(JFrame frame2){
        frame2.dispose();
    }


}
