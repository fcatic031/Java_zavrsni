package zavrsni.model;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public abstract class Osoba extends Entitet{


    private String ime;
    private String prezime;
    private String email;
    private Date datumRodjenja;
    private boolean spol;

    public Osoba() {
    }

    public Osoba(Integer id, String ime, String prezime, String email, Date datumRodjenja, boolean spol) {
        super(id);
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.spol = spol;
    }

    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }
    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
    public boolean isSpol() {
        return spol;
    }
    public void setSpol(boolean spol) {
        this.spol = spol;
    }

}
