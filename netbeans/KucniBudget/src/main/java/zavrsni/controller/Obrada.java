/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.controller;

import java.util.List;
import org.hibernate.Session;
import zavrsni.model.Entitet;
import zavrsni.util.BudgetException;
import zavrsni.util.HibernateUtil;

/**
 *
 * @author FILIP
 */
public abstract class Obrada <T extends Entitet> {
    
    protected T entitet;
    protected Session session;
    public abstract List<T> read();
    protected abstract void controlUnos() throws BudgetException;
    protected abstract void controlPromjena() throws BudgetException;
    protected abstract void controlBrisanje() throws BudgetException;

    public Obrada(){
        session = HibernateUtil.getSession();
    }

    public Obrada(T entitet){
        this();
        this.entitet = entitet;
    }

    public void create() throws BudgetException{
        controlNull();
        entitet.setId(null);
        controlUnos();
        persist();
    }

    public void update() throws BudgetException{
        controlNull();
        controlPromjena();
        persist();
    }

    public void delete() throws BudgetException{
        controlNull();
        controlBrisanje();
        session.beginTransaction();
        session.remove(entitet);
        session.getTransaction().commit();

    }


    private void controlNull() throws BudgetException {
        if(entitet==null){
            throw new BudgetException("ENTITET JE NULL");
        }
    }

    private void persist() {
        session.beginTransaction();
        session.persist(entitet);
        session.getTransaction().commit();
    }

    public T getEntitet(){
        return entitet;
    }

    public void setEntitet(T entitet){
        this.entitet = entitet;
    }

    public void refresh(){
        if (entitet!=null){
            session.refresh(entitet);
        }
    }


}
