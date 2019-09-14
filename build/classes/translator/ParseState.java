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
public enum ParseState {
    PS_START, PS_UNARY, PS_NONUNARY, PS_OPERAND, PS_ADDRESS, PS_FINISH, PS_BLOCK, PS_OPBLOCK, PS_END;
}
