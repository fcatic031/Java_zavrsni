package zavrsni.controller;

import zavrsni.model.Kategorija;
import zavrsni.util.BudgetException;

import java.util.List;

public class ObradaKategorija extends Obrada<Kategorija> {


    @Override
    public List<Kategorija> read() {
        return session.createQuery("from Kategorija", Kategorija.class).list();
    }

    @Override
    protected void controlUnos() throws BudgetException {
        controlKategorijaNaziv();
    }


    @Override
    protected void controlPromjena() throws BudgetException {

    }

    @Override
    protected void controlBrisanje() throws BudgetException {

    }

    private void controlKategorijaNaziv() throws BudgetException {
        if (entitet.getNaziv().isEmpty() || entitet.getNaziv()==null){
            throw new BudgetException("Kategorija nema naziv");
        }
    }

}
