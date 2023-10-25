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
        controlKategorijaNaziv();
    }

    @Override
    protected void controlBrisanje() throws BudgetException {
        //ako dnevne potrosnje imaju nemoj brisati
        if (!entitet.getPotrosnje().isEmpty()){
            throw new BudgetException("Neke potrosnje imaju navedenu kategoriju: "+entitet.getNaziv());
        }
    }

    private void controlKategorijaNaziv() throws BudgetException {
        if (entitet.getNaziv().trim().isEmpty() || entitet.getNaziv()==null){
            throw new BudgetException("Kategorija nema naziv");
        }
    }

}
