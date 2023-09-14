/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.util;

import org.hibernate.Session;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
/**
 *
 * @author FILIP
 */
public class FakerInsert {
    
    private static final int BROJ_OBITELJI=460;
    private static final int BROJ_KATEGORIJA=20;
    private static final int BROJ_KORISNIKA=2000;
    private static final int BROJ_POTROSNJI=15000;
    
    private Session session;
    private Faker faker;
    private List<Obitelj> obitelji;
    private List<Kategorija> kategorije;
    private List<Korisnik> korisnici;
    private List<DnevnaPotrosnja> potrosnje;
    
    public FakerInsert(){
        faker = new Faker();
        session = HibernateUtil.getSession();
        obitelji = new ArrayList<>();
        kategorije = new ArrayList<>();
        korisnici = new ArrayList<>();
        potrosnje = new ArrayList<>();
        session.getTransaction().begin();
        kreirajObitelji();
        kreirajKategorije();
        kreirajKorisnike();
        kreirajPotrosnje();
        session.getTransaction().commit();
    }

    private void kreirajObitelji() {
        Obitelj o;
        for (int i=0; i<BROJ_OBITELJI;i++){
            o = new Obitelj();
            o.setObiteljskoPrezime(faker.gameOfThrones().house());
            
            session.persist(o);
            obitelji.add(o);
        }
        
    }

    private void kreirajKategorije() {
        Kategorija k;
        for (int i=0; i<BROJ_KATEGORIJA;i++){
            k = new Kategorija();
            k.setNaziv(faker.job().field());
            session.persist(k);
            kategorije.add(k);
        }
    }

    private void kreirajKorisnike() {
        Korisnik k;
        Obitelj o;
        for (int i=0; i<BROJ_KORISNIKA;i++){
            k = new Korisnik();
            k.setIme(faker.hobbit().character());
            o = new Obitelj();
            o = obitelji.get(faker.number().numberBetween(0, BROJ_OBITELJI-1));
            k.setObitelj(o);
            //npr jon snow je stark
            int luck = faker.number().numberBetween(0, 100);
            k.setPrezime(luck<=90 ? o.getObiteljskoPrezime() : faker.company().name());
            k.setEmail(k.getIme().trim().toLowerCase().replace(" ", "").substring(0, 1)+k.getPrezime().trim().toLowerCase().replace(" ", "")+"@"+faker.internet().domainName());
            k.setDatumRodjenja(faker.date().birthday(15, 82));
            k.setSpol(faker.bool().bool());
            session.persist(k);
            korisnici.add(k);
        }
        
    }

    private void kreirajPotrosnje() {
        DnevnaPotrosnja d;
        for (int i=0; i<BROJ_POTROSNJI;i++){
            d = new DnevnaPotrosnja();
            d.setDatum(faker.date().birthday(0, 10));
            d.setKorisnik(korisnici.get(faker.number().numberBetween(0, BROJ_KORISNIKA-1)));
            d.setKategorija(kategorije.get(faker.number().numberBetween(0, BROJ_KATEGORIJA-1)));
            d.setPotrosnja(new BigDecimal(faker.number().numberBetween(2, 1000)));
            
            session.persist(d);
            potrosnje.add(d);
        }
    }
    
    
    
}
