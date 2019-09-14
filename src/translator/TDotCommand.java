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
public class TDotCommand extends AToken {
    private final String stringValue;
    public TDotCommand(StringBuffer stringBuffer)
    {
        stringValue = new String(stringBuffer);
    }
    
    public String getDescription()
    {
       return String.format("Dot Command = %s", stringValue);
    }
    
    public String getStringValue()
    {
        return stringValue;
    }
}
