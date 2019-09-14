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
public class HexArg extends AArg {
    private final int HexValue;
    private final String hexConst;
    public HexArg(int HexConst)
    {
        HexValue = HexConst;
        hexConst = Util.hexConstant(HexValue);
    }
    
    public String generateCode()
    {
        return hexConst;//Format actual string for Hex Values here later if it needs to be done here. TODO
    }
    
    public int getValue()
    {
        return HexValue;
    }
}
