/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package zavrsni;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.controller.ObradaKorisnik;
import zavrsni.controller.ObradaObitelj;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;
import zavrsni.util.FakerInsert;
import zavrsni.util.HibernateUtil;
import zavrsni.view.Autorizacija;
import zavrsni.view.SplashScreen;

import javax.swing.*;
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
        //dodavanjeObitelji();
        //dodavanjeKorisnika();

        JPanel panel = new SplashScreen().panel;
        Alati.runApp(panel,"LOADING...");
        //JSON();

    }

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

    private static void JSON(){
        FakerInsert faker = new FakerInsert();
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        //Obitelj obitelj = gson.fromJson();
        String json;
        for (Obitelj o : faker.obitelji){

            json = gson.toJson(o);
            System.out.println(o.getObiteljskoPrezime());
        }
        //System.out.println(json);
    }


    private static void dodavanjeKorisnika(){
        //zbog uporabe kao operatera
        ObradaKorisnik ok = new ObradaKorisnik();

        Korisnik k = new Korisnik();
        k.setIme("Proba");
        k.setPrezime("Probanic");
        k.setEmail("proba@gmail.com");
        k.setDatumRodjenja(new Date());

        Argon2 argon2 = Argon2Factory.create();
        String hash = argon2.hash(10, 65536, 1, "lozinka".toCharArray());
        k.setUloga(true);
        k.setLozinka(hash);
        k.setObitelj(null);

        ok.setEntitet(k);
        try {
            ok.create();
        } catch (BudgetException b){
            System.out.println(b.getPoruka());
        }

    }

}
