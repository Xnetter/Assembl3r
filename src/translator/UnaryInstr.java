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
public class UnaryInstr extends ACode {
    private final Mnemon mnemonic;
    public UnaryInstr(Mnemon mn)
    {
        mnemonic = mn;
    }
    public String generateListing()
    {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    }
    public String generateCode()
    {
        int mnemVal = Maps.unaryIntTable.get(mnemonic);
        return String.format("%02X", mnemVal)+ "\n";
    }
    
}
