package zavrsni.view;

import org.hibernate.Session;
import zavrsni.util.Alati;
import zavrsni.util.HibernateUtil;

import javax.swing.*;

public class SplashScreen {
    public JPanel panel;

    public SplashScreen(){
        load();
    }
    private void load(){
        new Loading().start();
    }

    private class Loading extends Thread{

        @Override
        public void run(){
            Session s = HibernateUtil.getSession();
            if (s.getMetamodel().getEntities().isEmpty()){
                System.out.println("Problem s bazom");
                return;
            }

            JPanel panel1 = new Autorizacija().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1);
            Alati.disposeApp(frame);


        }

    }
}
