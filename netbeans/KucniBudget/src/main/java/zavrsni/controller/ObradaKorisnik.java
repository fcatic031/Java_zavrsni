package zavrsni.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.commons.validator.routines.EmailValidator;
import zavrsni.model.Korisnik;
import zavrsni.util.BudgetException;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

public class ObradaKorisnik extends Obrada<Korisnik> {


    @Override
    public List<Korisnik> read() {
        return session.createQuery("from Korisnik",Korisnik.class).list();
    }


    public List<Korisnik> read(String uvjet) {
        uvjet = uvjet==null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%"+uvjet+"%";

        List<Korisnik> list = session.createQuery("from Korisnik k " +
                " where concat(k.ime,' ',k.prezime,' ',k.ime,' ') like :uvjet "
                +" order by k.prezime,k.ime",Korisnik.class)
                .setParameter("uvjet",uvjet)
                .list();

        Collator spCollator = Collator.getInstance(Locale.of("hr", "HR"));

        list.sort((e1, e2)-> spCollator.compare(e1.getPrezime(), e2.getPrezime()));

        return list;
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
        controlObitelj();
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

        if (!EmailValidator.getInstance().isValid(entitet.getEmail())){
            throw new BudgetException("E-mail nije validan");
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
        Korisnik k;

        try {
            k = session.createQuery("from Korisnik k where k.email=:email", Korisnik.class).setParameter("email", email).getSingleResult();

            Argon2 argon2 = Argon2Factory.create();

            return argon2.verify(k.getLozinka(), lozinka.toCharArray()) ? k : null;

        } catch (Exception e) {
            return null;
        }
    }
}
