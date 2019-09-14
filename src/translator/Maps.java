/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;
import java.util.*;
/**
 *
 * @author Garrett
 */
public final class Maps {
    public static final Map<String, Mnemon> unaryMnemonTable;
    public static final Map<String, Mnemon> nonunaryMnemonTable;
    public static final Map<String, Mnemon> dotcommandMnemonTable;
    public static final Map<String, Address> addressMnemonTable;
    public static final Map<String, Mnemon> endcommandMnemonTable;
    public static final Map<Mnemon, String> mnemonStringTable;
    public static final Map<Address, String> addressStringTable;
    
    public static final Map<Mnemon, Integer> unaryIntTable;
    public static final Map<Mnemon, Integer> nonunaryIntTable;
    
    
    static 
    {
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("STOP", Mnemon.M_STOP);
        unaryMnemonTable.put("ASLA", Mnemon.M_ASLA);
        unaryMnemonTable.put("ASRA", Mnemon.M_ASRA);
        
        unaryIntTable = new HashMap<>();
        unaryIntTable.put(Mnemon.M_STOP, 0);
        unaryIntTable.put(Mnemon.M_ASLA, 10);
        unaryIntTable.put(Mnemon.M_ASRA, 12);
        
        nonunaryMnemonTable = new HashMap<>();
        nonunaryMnemonTable.put("BR", Mnemon.M_BR);
        nonunaryMnemonTable.put("BRLT", Mnemon.M_BRLT);
        nonunaryMnemonTable.put("BREQ", Mnemon.M_BREQ);
        nonunaryMnemonTable.put("BRLE", Mnemon.M_BRLE);
        nonunaryMnemonTable.put("CPWA", Mnemon.M_CPWA);
        nonunaryMnemonTable.put("DECI", Mnemon.M_DECI);
        nonunaryMnemonTable.put("DECO", Mnemon.M_DECO);
        nonunaryMnemonTable.put("ADDA", Mnemon.M_ADDA);
        nonunaryMnemonTable.put("SUBA", Mnemon.M_SUBA);
        nonunaryMnemonTable.put("STWA", Mnemon.M_STWA);
        nonunaryMnemonTable.put("LDWA", Mnemon.M_LDWA);
        
        nonunaryIntTable = new HashMap<>();
        nonunaryIntTable.put(Mnemon.M_BR, 18);
        nonunaryIntTable.put(Mnemon.M_BRLT, 22);
        nonunaryIntTable.put(Mnemon.M_BREQ, 24);
        nonunaryIntTable.put(Mnemon.M_BRLE, 20);
        nonunaryIntTable.put(Mnemon.M_CPWA, 160);
        nonunaryIntTable.put(Mnemon.M_DECI, 48);
        nonunaryIntTable.put(Mnemon.M_DECO, 56);
        nonunaryIntTable.put(Mnemon.M_LDWA, 192);
        nonunaryIntTable.put(Mnemon.M_STWA, 224);
        nonunaryIntTable.put(Mnemon.M_ADDA, 96);
        nonunaryIntTable.put(Mnemon.M_SUBA, 112);
        
        
        
        endcommandMnemonTable = new HashMap<>();
        endcommandMnemonTable.put(".END", Mnemon.M_END);
        
        dotcommandMnemonTable = new HashMap<>();
        dotcommandMnemonTable.put(".BLOCK", Mnemon.M_BLOCK);
        
        addressMnemonTable = new HashMap<>();
        addressMnemonTable.put("i", Address.AD_I);
        addressMnemonTable.put("d", Address.AD_D);
        addressMnemonTable.put("s", Address.AD_S);
        addressMnemonTable.put("x", Address.AD_X);
        addressMnemonTable.put("sf", Address.AD_SF);
        addressMnemonTable.put("sx", Address.AD_SX);
        addressMnemonTable.put("sfx", Address.AD_SFX);
        addressMnemonTable.put("n", Address.AD_N);
        
        
        
        mnemonStringTable = new EnumMap<>(Mnemon.class);
        mnemonStringTable.put(Mnemon.M_STOP, "STOP");
        mnemonStringTable.put(Mnemon.M_ASLA, "ASLA");
        mnemonStringTable.put(Mnemon.M_ASRA, "ASRA");
        mnemonStringTable.put(Mnemon.M_BLOCK, ".BLOCK");
        mnemonStringTable.put(Mnemon.M_BR, "BR");
        mnemonStringTable.put(Mnemon.M_BREQ, "BREQ");
        mnemonStringTable.put(Mnemon.M_BRLE, "BRLE");
        mnemonStringTable.put(Mnemon.M_BRLT, "BRLT");
        mnemonStringTable.put(Mnemon.M_CPWA, "CPWA");
        mnemonStringTable.put(Mnemon.M_DECI, "DECI");
        mnemonStringTable.put(Mnemon.M_DECO,"DECO");
        mnemonStringTable.put(Mnemon.M_LDWA, "LDWA");
        mnemonStringTable.put(Mnemon.M_ADDA, "ADDA");
        mnemonStringTable.put(Mnemon.M_STWA, "STWA");
        mnemonStringTable.put(Mnemon.M_SUBA, "SUBA");
        mnemonStringTable.put(Mnemon.M_END, ".END");
        
        addressStringTable = new EnumMap<>(Address.class);
        addressStringTable.put(Address.AD_D, "d");
        addressStringTable.put(Address.AD_I, "i");
        addressStringTable.put(Address.AD_N, "n");
        addressStringTable.put(Address.AD_S, "s");
        addressStringTable.put(Address.AD_SF, "sf");
        addressStringTable.put(Address.AD_SFX, "sfx");
        addressStringTable.put(Address.AD_SX, "sx");
        addressStringTable.put(Address.AD_X, "x");
    }
        
        
        
        
    
}
