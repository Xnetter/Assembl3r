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
public class Tokenizer {
    private final InBuffer b;
    public Tokenizer(InBuffer inBuffer)
    {
        b = inBuffer;
    }
    
    public AToken getToken()
    {
        char nextChar;
        StringBuffer localStringValue = new StringBuffer("");
        StringBuffer localAddressValue = new StringBuffer("");
        StringBuffer localDotCommand = new StringBuffer("");
        int localIntValue = 0;
        int sign = +1;
        AToken aToken = new TEmpty();
        LexState state = LexState.LS_START;
        do
        {
            nextChar = b.advanceInput();
            switch(state) {
                case LS_START:
                {
                    if(Util.isAlpha(nextChar))
                    {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    }
                    else if(Util.isDigit(nextChar))
                    {
                        if(nextChar == '0')
                        {
                            state = LexState.LS_INT1;
                        }
                        else
                        {
                            state = LexState.LS_INT2;
                            localIntValue = nextChar - '0';
                             if(localIntValue > 65535 || localIntValue < -32768)
                            {
                                aToken = new TInvalid();
                            }
                        }
                    }
                    else if(nextChar == '-' || nextChar == '+')
                    {
                        state = LexState.LS_SIGN;
                        if(nextChar == '-')
                        {
                            sign = -1;
                        }
                    }
                    else if(nextChar == '.')
                    {
                        state = LexState.LS_DOT1;
                        localDotCommand.append(nextChar);
                    }
                    else if(nextChar == ',')
                    {
                        state = LexState.LS_ADDR1;
                        localDotCommand.append(nextChar);
                    }
                    else if(nextChar == '\n')
                    {
                        state = LexState.LS_STOP;
                    }
                    else if(nextChar != ' ')
                    {
                        aToken = new TInvalid();
                    }
                    break;  
                }
                case LS_INT1:
                {
                    if(Util.isDigit(nextChar))
                    {
                        state = LexState.LS_INT2;
                        localIntValue = 10 * localIntValue + nextChar - '0';
                         if(localIntValue > 65535 || localIntValue < -32768)
                        {
                            aToken = new TInvalid();
                        }
                    }
                    else if(nextChar == 'x' || nextChar == 'X')
                    {
                        state = LexState.LS_HEX1;
                    }
                    else if((sign * localIntValue) < -32768)
                    {
                        aToken = new TInvalid();
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new TInteger(localIntValue * sign);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
                case LS_INT2:
                {
                    if(Util.isDigit(nextChar))
                    {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        if(localIntValue > 65535 || localIntValue < -32768)
                        {
                            aToken = new TInvalid();
                        }
                    }
                    else if((sign * localIntValue) < -32768)
                    {
                        aToken = new TInvalid();
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new TInteger(localIntValue * sign);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
                case LS_SIGN:
                {
                    if(Util.isDigit(nextChar))
                    {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                         if(localIntValue > 65535 || localIntValue < -32768)
                        {
                            aToken = new TInvalid();
                        }
                        state = LexState.LS_INT2;
                    }
                    else
                    {
                        aToken = new TInvalid();
                    }
                    break;
                }
                case LS_IDENT:
                {
                    if(Util.isDigit(nextChar) || Util.isAlpha(nextChar))
                    {
                        localStringValue.append(nextChar);
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new TIdentifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
                case LS_DOT1:
                {
                    if(Util.isAlpha(nextChar))
                    {
                        state = LexState.LS_DOT2;
                        localDotCommand.append(nextChar);
                    }
                    else
                    {
                        aToken = new TInvalid();
                    }
                    break;
                }
                case LS_ADDR1:
                {
                    if(nextChar == ' ')
                    {}
                    else if(Util.isAlpha(nextChar))
                    {
                        state = LexState.LS_ADDR2;
                        localAddressValue.append(nextChar);
                    }
                    else
                    {
                        aToken = new TInvalid();
                    }
                    break;
                }
                case LS_HEX1:
                {
                    if(Util.isHex(nextChar))
                    {
                        localIntValue = 16 * localIntValue + Util.hexVal(nextChar);
                        if(localIntValue > 65535 || localIntValue < -32768)
                        {
                            aToken = new TInvalid();
                        }
                        state = LexState.LS_HEX2;
                    }
                    else
                    {
                        aToken = new TInvalid();
                    }
                    break;
                }
                case LS_HEX2:
                {
                    if(Util.isHex(nextChar))
                    {
                        localIntValue = 16 * localIntValue + Util.hexVal(nextChar);
                        if(localIntValue > 65535)
                        {
                            aToken = new TInvalid();
                        }
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new THex(localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
                case LS_DOT2:
                {
                    if(Util.isAlpha(nextChar))
                    {
                        localDotCommand.append(nextChar);
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new TDotCommand(localDotCommand);
                        state = LexState.LS_STOP;
                    }
                    break;
                }
                case LS_ADDR2:
                {
                    if(Util.isAlpha(nextChar))
                    {
                        localAddressValue.append(nextChar);
                    }
                    else
                    {
                        b.backUpInput();
                        aToken = new TAddress(localAddressValue);
                        state = LexState.LS_STOP;   
                    } 
                    break;
                }
        } 
    }while(!(aToken instanceof TInvalid) && (state != LexState.LS_STOP));
     return aToken;
}
}
