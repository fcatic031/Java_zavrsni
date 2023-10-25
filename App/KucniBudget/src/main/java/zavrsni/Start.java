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
        //new FakerInsert();
        new Start();

    }

    private Start(){
        JPanel panel = new SplashScreen(true).panel;
        Alati.runApp(panel,"Loading...",true);

    }


}
