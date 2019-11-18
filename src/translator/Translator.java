/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;
import java.util.ArrayList;
/**
 *
 * @author Garrett
 */
public class Translator {
    private final InBuffer b;
    private Tokenizer t;
    private ACode aCode;
    
    public Translator(InBuffer inBuffer) 
    {
        b = inBuffer;
    }
    
    private boolean parseLine()
    {
        boolean terminate = false;
        AArg operandSpecifier = new IntArg(-1);
        Address addressingMode = Address.AD_N;
        //Compiler requires following useless 
        Mnemon localMnemon = Mnemon.M_END;
        AToken aToken;
        aCode = new EmptyInstr();
        ParseState state = ParseState.PS_START;
        do
        {
            aToken = t.getToken();
            switch (state) {
                case PS_START:
                    if(aToken instanceof TIdentifier)
                    {
                        TIdentifier localTIdentifier = (TIdentifier) aToken;
                        String tempStr = localTIdentifier.getStringValue();
                        if(Maps.unaryMnemonTable.containsKey(tempStr.toUpperCase()))
                        {
                            localMnemon = Maps.unaryMnemonTable.get(tempStr.toUpperCase());
                            aCode = new UnaryInstr(localMnemon);
                            state = ParseState.PS_UNARY;
                        }
                        else if(Maps.nonunaryMnemonTable.containsKey(tempStr.toUpperCase()))
                        {
                            localMnemon = Maps.nonunaryMnemonTable.get(tempStr.toUpperCase());
                            state = ParseState.PS_NONUNARY;
                        }
                        else
                            aCode = new Error("Unrecognized Mnemonic");
                    }
                    else if(aToken instanceof TDotCommand)
                    {
                        TDotCommand localTDotCommand = (TDotCommand) aToken;
                        String tempStr = localTDotCommand.getStringValue();
                        if(Maps.dotcommandMnemonTable.containsKey(tempStr.toUpperCase()))
                        {
                            localMnemon = Maps.dotcommandMnemonTable.get(tempStr.toUpperCase());
                            //will need to declare aCode later at next argument
                            state = ParseState.PS_BLOCK;
                        }
                        else if(Maps.endcommandMnemonTable.containsKey(tempStr.toUpperCase()))
                        {
                            localMnemon = Maps.endcommandMnemonTable.get(tempStr.toUpperCase());
                            aCode = new UnaryInstr(localMnemon);
                            state = ParseState.PS_END;
                        }
                    }
                    else if(aToken instanceof TEmpty)
                    {
                        state = ParseState.PS_FINISH;
                        aCode = new EmptyInstr();
                    }
                    else
                    {
                        aCode = new Error("Illegal Mnemonic.");
                        
                    }
                break;
                case PS_UNARY:
                {
                    if(aToken instanceof TEmpty)
                    {
                        
                        state = ParseState.PS_FINISH;
                    }
                    else
                    {
                        aCode = new Error("Illegal trailing character.");
                    }
                }
                break;
                case PS_END:
                    if(aToken instanceof TEmpty)
                    {
                        state = ParseState.PS_FINISH;
                        terminate = true;
                        aCode = new EndInstr();
                    }
                    else
                    {
                        aCode = new Error("Illegal trailing character.");
                    }
                break;
                case PS_BLOCK:
                    if(aToken instanceof TInteger)
                    {
                        TInteger localTInteger = (TInteger) aToken;
                        state = ParseState.PS_OPBLOCK;
                        operandSpecifier = new IntArg(localTInteger.getValue());
                        aCode = new DotBlockInstr(operandSpecifier);
                    }
                    else if(aToken instanceof THex)
                    {
                        THex localTHex = (THex) aToken;
                        state = ParseState.PS_OPBLOCK;
                        operandSpecifier = new HexArg(localTHex.getValue());
                        aCode = new DotBlockInstr(operandSpecifier);
                    }
                    else
                    {
                        aCode = new Error("Expected Hex or Integer constant, received invalid input.");
                    }
                break;
                case PS_NONUNARY:
                {
                    if(aToken instanceof TInteger)
                    {
                        TInteger localTInteger = (TInteger) aToken;
                        state = ParseState.PS_OPERAND;
                        operandSpecifier = new IntArg(localTInteger.getValue());
                    }
                    else if(aToken instanceof THex)
                    {
                        THex localTHex = (THex) aToken;
                        state = ParseState.PS_OPERAND;
                        operandSpecifier = new HexArg(localTHex.getValue());
                    }
                    else
                    {
                        aCode = new Error("Illegal Operand Specifier.");
                    }
                }
                break;
                case PS_OPERAND:
                {
                    if(aToken instanceof TAddress)
                    {
                        TAddress localTAddress = (TAddress) aToken;
                        String tempStr = localTAddress.getStringValue();
                        if(Maps.addressMnemonTable.containsKey(tempStr.toLowerCase()))
                        {
                            addressingMode = Maps.addressMnemonTable.get(tempStr.toLowerCase());
                            state = ParseState.PS_ADDRESS;
                            aCode = new NonUnaryInstr(localMnemon, operandSpecifier, addressingMode);
                            String testStr = Maps.mnemonStringTable.get(localMnemon);
                            if(!(Maps.addressStringTable.get(addressingMode) == "i") && !(Maps.addressStringTable.get(addressingMode) == "x") && testStr.substring(0,2).equals("BR") )
                            {
                                aCode = new Error("Illegal addressing mode.");
                            }
                            else if(Maps.addressStringTable.get(addressingMode) == "i" && (testStr.equals("STWA") || testStr.equals("DECI")))//added second test on new line
                            {                                                                                                                    //for readability
                                aCode = new Error("Illegal addressing mode.");
                            }    
                        }
                        else
                        {
                            aCode = new Error("Illegal addressing mode.");
                        }
                    }
                    else if(aToken instanceof TEmpty)
                    {
                        String mnemString = Maps.mnemonStringTable.get(localMnemon);
                        if(mnemString.substring(0,2).equals("BR"))
                        {
                            
                            addressingMode = Address.AD_I;
                            aCode = new NonUnaryInstr(localMnemon, operandSpecifier, addressingMode);
                            state = ParseState.PS_FINISH;
                        }
                        else
                        {
                            aCode = new Error("Missing addressing mode.");
                        }
                    }
                    else
                    {
                        aCode = new Error("Invalid Addressing Mode.");
                    }
                }
                break;
                case PS_ADDRESS:
                {
                    if(aToken instanceof TEmpty)
                    {
                        state = ParseState.PS_FINISH;
                    }
                    else
                    {
                        aCode = new Error("Illegal Trailing Character.");
                    }
                }
                break;
                case PS_OPBLOCK:
                {
                    if(aToken instanceof TEmpty)
                    {
                        state = ParseState.PS_FINISH;
                    }
                    else
                    {
                        aCode = new Error("Illegal Trailing Character.");
                    }
                }
                break;
                 
            }               
        }while (state != ParseState.PS_FINISH && !(aCode instanceof Error));
        return terminate;
    }
      public void translate() {
      ArrayList<ACode> codeTable = new ArrayList<>();
      int numErrors = 0;
      t = new Tokenizer(b);
      boolean terminateWithEnd = false;
      b.getLine();
      while (b.inputRemains() && !terminateWithEnd) {
         terminateWithEnd = parseLine(); // Sets aCode and returns boolean.
         codeTable.add(aCode);
         if (aCode instanceof Error) {
            numErrors++;
         }
         b.getLine();
      }
      if (!terminateWithEnd) {
         aCode = new Error("Missing .END sentinel.");
         codeTable.add(aCode);
         numErrors++;
      }
      if (numErrors == 0) {
         System.out.printf("Object code:\n");
         for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateCode());
         }
      }
      if (numErrors == 1) {
         System.out.printf("One error was detected.\n");
      } else if (numErrors > 1) {
         System.out.printf("%d errors were detected.\n", numErrors);
      }
      System.out.printf("\nProgram Listing:\n");
      
      for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateListing());
        }
   }
}
