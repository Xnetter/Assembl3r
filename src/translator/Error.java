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
public class Error extends ACode {
    private final String errorMessage;
    public Error(String errMessage)
    {
        errorMessage = errMessage;
    }
    public String generateListing()
    {
        return "ERROR:  " + errorMessage + "\n";
    }
    public String generateCode()
    {
        return "";
    }
}
