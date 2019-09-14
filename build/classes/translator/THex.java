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
public class THex extends AToken {
    private final int hexValue;
    public THex(int i)
    {
        hexValue = i;
    }
    
    public String getDescription()
    {
        return String.format("Hexadecimal Constant = %d", hexValue);
    }
    
    public int getValue()
    {
        return hexValue;
    }
}
