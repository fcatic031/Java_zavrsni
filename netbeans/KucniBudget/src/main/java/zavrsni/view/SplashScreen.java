package zavrsni.view;

import org.hibernate.Session;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;
import zavrsni.util.HibernateUtil;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    public JPanel panel;
    private JProgressBar progressBar;
    private JLabel lblSlika;

    public SplashScreen(){

        load();
        //new Loading().loading();
    }
    private void load(){
        new Loading().start();
    }

    private class Loading extends Thread{

        @Override
        public void run(){
            lblSlika.setIcon(new ImageIcon(new ImageIcon("src\\main\\resources\\HomeBudgetSlika.jpeg").getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT)));
            loadingBar(progressBar,0,50);
            Session s = HibernateUtil.getSession();
            loadingBar(progressBar,50,100);
            if (s.getMetamodel().getEntities().isEmpty()){
                System.out.println("Problem s bazom");
                return;
            }
            JPanel panel1 = new Autorizacija().panel;
            JFrame frame = Alati.getFrame();
            Alati.runApp(panel1,"Autorizacija");
            Alati.disposeApp(frame);

        }

        public void loadingBar(JProgressBar progressBar,int min,int max){
            progressBar.setMinimum(0);
            progressBar.setMaximum(100);
            progressBar.setValue(0);

            for (int i = min; i<=max; i++){
                progressBar.setValue(i);

                try{
                    sleep(35);
                } catch (InterruptedException ex){

                }
            }
        }
    }
}
