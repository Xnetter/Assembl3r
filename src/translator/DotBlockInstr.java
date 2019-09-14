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
public class DotBlockInstr extends ACode {
    private final Mnemon mnemon;
    private final AArg constant;
    
    public DotBlockInstr(AArg arg)
    {
        mnemon = Mnemon.M_BLOCK;
        constant = arg;
    }
    
    public String generateListing()
    {
        return String.format("%-8s%s\n", Maps.mnemonStringTable.get(mnemon), constant.generateCode());
    }
    
    public String generateCode()
    {
        String output = "00";
        for(int i = 0; i < constant.getValue()-1; i++)
        {
            output += " 00";
        }
        return output + "\n";
    }
}
