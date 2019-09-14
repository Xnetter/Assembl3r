/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

/**
 *
 * @author Garrett
 */
public class EndInstr extends ACode {
    private final Mnemon mnemon;
    
    public EndInstr()
    {
        mnemon = Mnemon.M_END;
    }
    
    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemon) + "\n\n";
    }
    
    public String generateCode()
    {
        return "zz\n";
    }
}
