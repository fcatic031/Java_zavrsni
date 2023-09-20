package zavrsni.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import zavrsni.model.Operater;
import zavrsni.util.BudgetException;

import java.util.List;

//jos cu doraditi
public class ObradaOperater extends ObradaOsoba<Operater> {
    @Override
    public List<Operater> read() {
        return session.createQuery("from Operater", Operater.class).list();
    }

    public Operater autoriziraj(String email, String lozinka){
        Operater o;

        try {
            o=session.createQuery("from Operater o where o.email=:email",Operater.class).setParameter("email",email).getSingleResult();

            Argon2 argon2 = Argon2Factory.create();

            return  argon2.verify(o.getLozinka(),lozinka.toCharArray()) ? o : null;

        } catch (Exception e){
            return null;
        }
    }

    @Override
    protected void controlBrisanje() throws BudgetException {

    }
}
