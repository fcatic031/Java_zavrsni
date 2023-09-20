package zavrsni.model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Operater extends Osoba{
    //postoji mogucnost da cu praviti classu Osoba
    private String uloga;
    private String lozinka;

    public Operater() {
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
