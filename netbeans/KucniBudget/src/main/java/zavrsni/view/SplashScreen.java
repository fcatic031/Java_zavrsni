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
    private JLabel lblSlika2;
    private Boolean pocetak;

    public SplashScreen(Boolean pocetak){
        this.pocetak = pocetak;
        load();
    }
    private void load(){
        new Loading().start();
    }

    private class Loading extends Thread{

        @Override
        public void run(){

            if (pocetak) {
                lblSlika.setIcon(new ImageIcon(new ImageIcon("src\\main\\resources\\HomeBudgetSlika.jpeg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
                loadingBar(progressBar, 0, 50);
                Session s = HibernateUtil.getSession();
                loadingBar(progressBar, 50, 100);
                if (s.getMetamodel().getEntities().isEmpty()) {
                    System.out.println("Problem s bazom");
                    return;
                }
                JPanel panel1 = new Autorizacija().panel;
                JFrame frame = Alati.getFrame();
                Alati.runApp(panel1, "Autorizacija", true);
                Alati.disposeApp(frame);
                lblSlika.setIcon(null);
            } else {
                //lblSlika.setIcon(new ImageIcon(new ImageIcon("src\\main\\resources\\HomeBudgetSlika.jpeg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));

                JFrame frame = Alati.getFrame();
                Alati.panelIzbornik = new Izbornik().panel;
                loadingBar(progressBar, 0, 20);

                Alati.panelKorisnik = new ProzorKorisnik().panel;
                loadingBar(progressBar, 20, 40);

                Alati.panelObitelj = new ProzorObitelj().panel;
                loadingBar(progressBar, 40, 60);

                Alati.panelKategorija = new ProzorKategorija().panel;
                loadingBar(progressBar, 60, 80);

                Alati.panelDnevnaPotrosnja = new ProzorDnevnaPotrosnja().panel;
                loadingBar(progressBar, 80, 100);

                Alati.disposeApp(frame);
                Alati.runApp(Alati.panelIzbornik,"Izbornik",true);

            }
        }

        public void loadingBar(JProgressBar progressBar,int min,int max){
            progressBar.setMinimum(0);
            progressBar.setMaximum(100);
            progressBar.setValue(min);
            progressBar.setString("Očitavanje");

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
