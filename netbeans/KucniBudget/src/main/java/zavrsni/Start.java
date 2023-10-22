/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package zavrsni;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.controller.ObradaObitelj;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;
import zavrsni.util.FakerInsert;
import zavrsni.util.HibernateUtil;
import zavrsni.view.Autorizacija;
import zavrsni.view.SplashScreen;

import javax.swing.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

/**
 *
 * @author FILIP
 */
public class Start {

    public static void main(String[] args) {
        //HibernateUtil.getSession();
        //new FakerInsert();
        //new Start();

        JPanel panel = new SplashScreen(true).panel;
        Alati.runApp(panel,"LOADING...",true);
        //Alati.panelSplash = new SplashScreen(true).panel;
        //Alati.runApp(Alati.panelSplash,"Loading",true);
    }

    private Start(){
        radSJSON();
    }



    private  void radSJSON() {
        Type listType = new TypeToken<List<DnevnaPotrosnja>>() {}.getType();
        List<DnevnaPotrosnja> target = new ObradaDnevnaPotrosnja().read();


        Gson gson = new GsonBuilder()

                //.setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setExclusionStrategies(new CustomExclusionStrategy()).create();
        String json = gson.toJson(target, listType);

        System.out.println(json);
    }

    void ucitajIzJSON(){
        try {

            //Nov 28, 2018, 7:09:02?AM
            Type listType = new TypeToken<List<DnevnaPotrosnja>>(){}.getType();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<DnevnaPotrosnja> list = gson.fromJson(Files.readString(Path.of("podaci.json")), listType);


            ObradaDnevnaPotrosnja odp = new ObradaDnevnaPotrosnja();
            for(DnevnaPotrosnja dp : list){
                dp.setId(null);
                odp.setEntitet(dp);
                try {
                    odp.create();
                } catch (BudgetException e) {
                    System.out.println(e.getPoruka());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class CustomExclusionStrategy implements ExclusionStrategy {

        public boolean shouldSkipField(FieldAttributes f) {

            if(f.getDeclaringClass() == Kategorija.class && f.getName().equals("potrosnje")){
                return true;
            }

            if(f.getDeclaringClass() == Korisnik.class && f.getName().equals("potrosnje")){
                return true;
            }

            if(f.getDeclaringClass() == Obitelj.class && f.getName().equals("clanovi")){
                return true;
            }
            return false;
        }

        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    }
}
