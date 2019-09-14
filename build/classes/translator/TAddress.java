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
public class TAddress extends AToken {
    private final String stringValue;
    
    public TAddress(StringBuffer stringBuffer)
    {
        stringValue = new String(stringBuffer);
    }
    
    public String getDescription()
    {
        return String.format("Addressing Mode = %s", stringValue);
    }
    
    public String getStringValue()
    {
        return stringValue;
    }
    
}
