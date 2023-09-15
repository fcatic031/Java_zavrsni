/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package zavrsni;

import zavrsni.controller.ObradaObitelj;
import zavrsni.model.Obitelj;
import zavrsni.util.BudgetException;
import zavrsni.util.FakerInsert;
import zavrsni.util.HibernateUtil;

/**
 *
 * @author FILIP
 */
public class Start {

    public static void main(String[] args) {
        //HibernateUtil.getSession();
        new FakerInsert();


    }


    private void proba(){
        ObradaObitelj ob = new ObradaObitelj();

        Obitelj o = new Obitelj();
        o.setObiteljskoPrezime(null);
        ob.setEntitet(o);
        try {
            ob.create();
        } catch (BudgetException b){
            System.out.println(b.getPoruka());
        }
        System.out.println("GOTOVOOO");

    }
}
