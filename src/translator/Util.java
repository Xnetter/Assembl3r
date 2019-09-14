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
public class Util {
    public static char toAlpha(int x)
    {
        if(0 <= x && x <= 9)
        {
            return (char)(x+48);
        }
        if(x >= 10 && x <= 15)
        {
            return (char)((x-10)+65);
        }
        return 'z';
    }
    
    public static boolean isAlpha(char h)
    {
        return('a' <= h && h <= 'z' || 'A' <= h && h <= 'Z');
    }
    
    public static boolean isDigit(char h)
    {
        return '0' <= h && h <= '9';
    }
    
    public static boolean isHex(char h)
    {
        return isDigit(h) || (('a' <= h && 'f' >= h) || ('A' <= h && 
                'F' >= h));
    }
    
    public static int hexVal(char ch) //assuming h is a hex value
    {
        if('0' <= ch && ch <= '9')
        {
            return ch - '0';
        }
        else if('a' <= ch && ch <= 'f')
        {
            return 10 + ch - 'a';
        }
        else if(('A' <= ch && ch <= 'F'))
        {
            return 10 + ch - 'A';
        }
        return -1;
    }
    
    public static String hexConstant(int x)
    {
        int remainder = -1;
        String hexConst = "";
        while(x != 0)
        {
            remainder = x%16;
            hexConst = toAlpha(remainder) + hexConst;
            x = x/16;
        }
        while(hexConst.length()<4)
        {
            hexConst = "0" + hexConst;
        }
        hexConst = "0x" + hexConst;
        return hexConst;
    }
}
