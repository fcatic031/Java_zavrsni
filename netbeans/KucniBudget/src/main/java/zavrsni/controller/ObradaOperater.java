package zavrsni.controller;

import zavrsni.model.Operater;
import zavrsni.util.BudgetException;

import java.util.List;

//jos cu doraditi
public class ObradaOperater extends ObradaOsoba<Operater> {
    @Override
    public List<Operater> read() {
        return session.createQuery("from Operater", Operater.class).list();
    }


    @Override
    protected void controlBrisanje() throws BudgetException {

    }
}
