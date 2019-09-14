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
public class IntArg extends AArg{
    private final int intValue;
    public IntArg(int i)
    {
        intValue = i;
    }
    public String generateCode()
    {
        return String.format("%d", intValue);
    }
    
    public int getValue()
    {
        return intValue;
    }
}

