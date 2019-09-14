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
public enum LexState
{
    LS_START, LS_INVALID, LS_SIGN, LS_IDENT, LS_DOT1, LS_DOT2, LS_ADDR1, LS_ADDR2, LS_INT1, LS_INT2, LS_HEX1, LS_HEX2, LS_STOP;
}
