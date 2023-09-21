package zavrsni.util;

import zavrsni.model.Operater;

import javax.swing.*;

public class Alati {
    public static Operater OPERATER;
    public static JFrame frame;

    public static String getOperater(){
        return OPERATER.getIme()+" "+ OPERATER.getPrezime()+" ("+OPERATER.getUloga()+ ")";
    }

    public static void runApp(JPanel panel){
        frame= new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static JFrame getFrame(){
        return frame;
    }

    public static void disposeApp(JFrame frame2){
        frame2.dispose();
    }


}
