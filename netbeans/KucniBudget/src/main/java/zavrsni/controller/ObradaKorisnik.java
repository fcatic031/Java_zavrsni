package zavrsni.controller;

import zavrsni.model.Korisnik;
import zavrsni.util.BudgetException;

import java.util.List;

public class ObradaKorisnik extends ObradaOsoba<Korisnik> {


    @Override
    public List<Korisnik> read() {
        return session.createQuery("from Korisnik",Korisnik.class).list();
    }

    @Override
    protected void controlBrisanje() throws BudgetException {

    }

}
