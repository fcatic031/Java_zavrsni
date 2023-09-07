/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsni.util;

/**
 *
 * @author FILIP
 */
public class BudgetException extends Exception {
    
    private String poruka;
    
    public BudgetException(String poruka){
        super(poruka);
        this.poruka = poruka;
    }
    
    public String getPoruka() {
        return poruka;

    }
}
