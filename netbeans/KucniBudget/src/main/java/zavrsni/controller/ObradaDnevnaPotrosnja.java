package zavrsni.controller;

import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.Alati;
import zavrsni.util.BudgetException;

import java.math.BigDecimal;
import java.util.List;

public class ObradaDnevnaPotrosnja extends Obrada<DnevnaPotrosnja> {

    @Override
    public List<DnevnaPotrosnja> read() {

        return session.createQuery("from DnevnaPotrosnja", DnevnaPotrosnja.class).list();
    }

    public List<DnevnaPotrosnja> read(String uvjet) {
        uvjet = uvjet==null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%"+uvjet+"%";

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                        "inner join dp.korisnik as korisnik "+
                        "inner join dp.kategorija as kategorija "+
                        "where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                        "like :uvjet " +
                        "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("uvjet",uvjet)
                .list();
        return lista;
    }

    public List<DnevnaPotrosnja> read(Korisnik k) {
        Integer korisnikID = k.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                "inner join dp.korisnik as korisnik "+
                                //"inner join dp.kategorija as kategorija "+
                                //"where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                                //"like :uvjet " +
                                "where korisnik.id=:k "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("k",korisnikID)
                .list();

        return lista;
    }

    public List<DnevnaPotrosnja> read(Obitelj o) {
        Integer obiteljID = o.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                "inner join dp.korisnik as korisnik "+
                                //"inner join dp.kategorija as kategorija "+
                                //"where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                                //"like :uvjet " +
                                "where korisnik.obitelj.id=:o "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("o",obiteljID)
                .list();

        return lista;
    }

    public List<DnevnaPotrosnja> read(Kategorija k) {
        Integer kategorijaID = k.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                //"inner join dp.korisnik as korisnik "+
                                //"inner join dp.kategorija as kategorija "+
                                //"where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                                //"like :uvjet " +
                                "where dp.kategorija.id=:k "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("k",kategorijaID)
                .list();

        return lista;
    }

    public List<DnevnaPotrosnja> read(Obitelj o,String uvjet) {
        uvjet = uvjet==null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%"+uvjet+"%";
        Integer obiteljID = o.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                "inner join dp.korisnik as korisnik "+
                                "inner join dp.kategorija as kategorija "+
                                "where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                                "like :uvjet and " +
                                "korisnik.obitelj.id=:o "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("o",obiteljID)
                .setParameter("uvjet",uvjet)
                .list();

        return lista;
    }

    public List<DnevnaPotrosnja> read(Korisnik k,String uvjet) {
        uvjet = uvjet==null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%"+uvjet+"%";
        Integer korisnikID = k.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                "inner join dp.korisnik as korisnik "+
                                "inner join dp.kategorija as kategorija "+
                                "where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',kategorija.naziv,' ',year(dp.datum)) " +
                                "like :uvjet and " +
                                "korisnik.id=:k "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("k",korisnikID)
                .setParameter("uvjet",uvjet)
                .list();
        return lista;
    }

    public List<DnevnaPotrosnja> read(Kategorija k,String uvjet) {
        uvjet = uvjet==null ? "" : uvjet;
        uvjet = uvjet.trim();
        uvjet = "%"+uvjet+"%";
        Integer kategorijaID = k.getId();

        List<DnevnaPotrosnja> lista = session
                .createQuery("from DnevnaPotrosnja dp " +
                                "inner join dp.korisnik as korisnik "+
                                "inner join dp.kategorija as kategorija "+
                                "where concat(korisnik.ime,' ',korisnik.prezime,' ',korisnik.ime,' ',year(dp.datum)) " +
                                "like :uvjet and " +
                                "kategorija.id=:k "+
                                "order by dp.potrosnja",
                        DnevnaPotrosnja.class)
                .setParameter("k",kategorijaID)
                .setParameter("uvjet",uvjet)
                .list();

        return lista;
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
        controlKorisnik();
        controlKategorija();
        controlDatum();
        controlPotrosnja();
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

        if (x==null || x.compareTo(BigDecimal.ZERO)==0){
            throw new BudgetException("Nema unosa potrosnja");
        }
        if (x.compareTo(BigDecimal.ZERO)<0){
            throw new BudgetException("Unos potrosnje mora biti pozitivan");
        }
    }
}
