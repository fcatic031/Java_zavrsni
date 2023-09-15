package zavrsni.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Operater extends Osoba{
    //postoji mogucnost da cu praviti classu Osoba
    private String uloga;
    private String sifra;

    public Operater() {
    }


    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
