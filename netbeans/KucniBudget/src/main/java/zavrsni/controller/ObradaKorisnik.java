package zavrsni.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import zavrsni.model.Korisnik;
import zavrsni.util.BudgetException;

import java.util.List;

public class ObradaKorisnik extends Obrada<Korisnik> {


    @Override
    public List<Korisnik> read() {
        return session.createQuery("from Korisnik",Korisnik.class).list();
    }

    @Override
    protected void controlUnos() throws BudgetException {
        controlIme();
        controlPrezime();
        controlEmail();
        controlDatumRodjenja();
        controlSpol();
    }

    @Override
    protected void controlPromjena() throws BudgetException {
        controlIme();
        controlPrezime();
        controlEmail();
        controlDatumRodjenja();
        controlSpol();
    }

    @Override
    protected void controlBrisanje() throws BudgetException {
        if (!entitet.getPotrosnje().isEmpty()){
            throw new BudgetException("Korisnik ima potrosnje");
        }
    }

    private void controlIme() throws BudgetException {
        if(entitet.getIme()==null || entitet.getIme().isEmpty()){
            throw new BudgetException("Ne postoji ime korisnika");
        }
    }
    private void controlPrezime() throws BudgetException{
        if (entitet.getPrezime()==null || entitet.getPrezime().isEmpty()){
            throw new BudgetException("Ne postoji prezime korisnika");
        }
    }
    private void controlEmail() throws  BudgetException{
        if (entitet.getEmail()==null || entitet.getEmail().isEmpty()){
            throw new BudgetException("E-mail je prazan ili ne postoji");
        }
        if (!entitet.getEmail().contains("@")){
            throw new BudgetException("E-mail ne sadrzi \"@\"");
        }
    }
    private void controlDatumRodjenja() throws BudgetException {
        if (entitet.getDatumRodjenja()==null){
            throw new BudgetException("Datum rodjenja je prazan");
        }
    }
    private void controlSpol() {
        //zasad ne znam tocno kako bi gresku prikazao kod unosa spola
    }
    private void controlObitelj() {
        //korisnik moze biti bez obitelji
    }


    public Korisnik autoriziraj(String email, String lozinka) {
        Korisnik o;

        try {
            o = session.createQuery("from Korisnik k where k.email=:email", Korisnik.class).setParameter("email", email).getSingleResult();

            Argon2 argon2 = Argon2Factory.create();

            return argon2.verify(o.getLozinka(), lozinka.toCharArray()) ? o : null;

        } catch (Exception e) {
            return null;
        }
    }
}
