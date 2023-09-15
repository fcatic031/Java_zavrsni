package zavrsni.controller;

import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.BudgetException;

import java.util.List;

public class ObradaObitelj extends Obrada<Obitelj> {

    @Override
    public List<Obitelj> read() {
        return session.createQuery("from Obitelj",Obitelj.class).list();
    }

    @Override
    protected void controlUnos() throws BudgetException {
        controlObiteljskoPrezime();
    }

    @Override
    protected void controlPromjena() throws BudgetException {
        controlObiteljskoPrezime();
    }

    @Override
    protected void controlBrisanje() throws BudgetException {
        if (!entitet.getClanovi().isEmpty()){
            Korisnik kor;
            StringBuilder sb = new StringBuilder("Obitelj "+entitet.getObiteljskoPrezime()+" ima clanove: ");
            for (Korisnik k : entitet.getClanovi()){
                sb.append(k.getIme());
                if (k!=entitet.getClanovi().get(entitet.getClanovi().size()-1)){
                    sb.append(", ");
                }
            }
            throw new BudgetException(sb.toString());
        }
    }


    private void controlObiteljskoPrezime() throws BudgetException {
        if (entitet.getObiteljskoPrezime()==null){
            throw new BudgetException("Obiteljsko prezime je nula");
        }
        if (entitet.getObiteljskoPrezime().isEmpty()){
            throw new BudgetException("Obiteljsko prezime je prazan");
        }
    }
}
