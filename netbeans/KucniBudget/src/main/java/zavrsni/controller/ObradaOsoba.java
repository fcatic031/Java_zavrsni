package zavrsni.controller;

import zavrsni.model.Entitet;
import zavrsni.model.Osoba;
import zavrsni.util.BudgetException;

public abstract class ObradaOsoba <T extends Osoba> extends Obrada<T> {

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
}
