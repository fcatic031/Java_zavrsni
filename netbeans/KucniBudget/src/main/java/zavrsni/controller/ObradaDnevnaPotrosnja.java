package zavrsni.controller;

import zavrsni.model.DnevnaPotrosnja;
import zavrsni.util.BudgetException;

import java.math.BigDecimal;
import java.util.List;

public class ObradaDnevnaPotrosnja extends Obrada<DnevnaPotrosnja> {

    @Override
    public List<DnevnaPotrosnja> read() {
        return session.createQuery("from DnevnaPotrosnja", DnevnaPotrosnja.class).list();
    }

    @Override
    protected void controlUnos() throws BudgetException {
        controlKorisnik();
        controlKategorija();
        controlDatum();
        controlPotrosnja();
    }


    @Override
    protected void controlPromjena() throws BudgetException {

    }

    @Override
    protected void controlBrisanje() throws BudgetException {

    }

    private void controlKorisnik() throws BudgetException{
        if (entitet.getKorisnik()==null){
            throw new BudgetException("Nije unesen korisnik u potrosnju");
        }
    }
    private void controlKategorija() throws BudgetException{
        if (entitet.getKategorija()==null){
            throw new BudgetException("Nije unesena kategorija u potrosnju");
        }
    }
    private void controlDatum() throws BudgetException{
        if (entitet.getDatum()==null){
            throw new BudgetException("Nije unesen datum u potrosnji");
        }
    }
    private void controlPotrosnja() throws BudgetException{
        var x = entitet.getPotrosnja();
        if (x==null){
            throw new BudgetException("Nema unosa potrosnja");
        }
        if (x.compareTo(BigDecimal.ZERO)<=0){
            throw new BudgetException("Unos potrosnje mora biti pozitivan");
        }
    }
}
