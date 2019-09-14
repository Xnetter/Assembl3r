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
public class NonUnaryInstr extends ACode {
    private final Mnemon mnemon;
    private final AArg operandSpecifier;
    private final Address addressingMode;
    
    public NonUnaryInstr(Mnemon mn, AArg iS, Address aM)
    {
        mnemon = mn;
        operandSpecifier = iS;
        addressingMode = aM;
    }
    public String generateListing() //Code determining whether or not the addressing mode is printed along with the BR instruction.
    
    {
        if(!Maps.mnemonStringTable.get(mnemon).substring(0,2).equals("BR") || (!Maps.addressStringTable.get(addressingMode).equals("i") && Maps.mnemonStringTable.get(mnemon).substring(0,2).equals("BR")))
        {
            return String.format("%-8s%s%s\n", Maps.mnemonStringTable.get(mnemon), operandSpecifier.generateCode(),","+Maps.addressStringTable.get(addressingMode));
        }
        return String.format("%-8s%s\n", Maps.mnemonStringTable.get(mnemon), operandSpecifier.generateCode());
    }
    
    public String generateCode()
    {
        String retVal;
        if(!Maps.mnemonStringTable.get(mnemon).substring(0,2).equals("BR"))
        {
             retVal = String.format("%02X ", Maps.nonunaryIntTable.get(mnemon) + addressingMode.ordinal());
        }
        else
        {
            int adMode;
            if(addressingMode == Address.AD_X)
            {
                adMode = 1;
            }
            else
            {
                adMode = 0;
            }
            retVal = String.format("%02X ", Maps.nonunaryIntTable.get(mnemon) + adMode);
        }
        String opSpec = String.format("%04X", operandSpecifier.getValue());
        opSpec = opSpec.substring(0,2) + " " + opSpec.substring(2,opSpec.length()); 
        retVal += opSpec;
        retVal += "\n";
        return retVal;
    }
}
