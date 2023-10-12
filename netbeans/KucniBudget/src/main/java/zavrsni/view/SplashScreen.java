package zavrsni.view;

import org.hibernate.Session;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;
import zavrsni.util.HibernateUtil;

import javax.swing.*;

public class SplashScreen {
    public JPanel panel;
    private JProgressBar progressBar;

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
            loading();
            JPanel panel1 = new Autorizacija().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Autorizacija");
            Alati.disposeApp(frame);

        }

        public void loading(){
            progressBar.setMinimum(0);
            progressBar.setMaximum(100);
            progressBar.setValue(0);

            for (int i = 0; i<100; i++){
                progressBar.setValue(i);

                try{
                    sleep(100);
                } catch (InterruptedException ex){

                }
            }
        }
    }
}
