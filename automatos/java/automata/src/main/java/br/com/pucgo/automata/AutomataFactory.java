package br.com.pucgo.automata;

import br.com.pucgo.automata.impl.DFAImpl;
import br.com.pucgo.automata.impl.NFAImpl;

/**
 * A fábrica de autômatos.
 *
 * Created by danfma on 22/11/14.
 */
public final class AutomataFactory {

    /**
     * Cria um novo DFA.
     *
     * @return a instância do autômato.
     */
    public static DFA createDFA() {
        return new DFAImpl();
    }

    /**
     * Cria um novo NFA.
     *
     * @return
     */
    public static NFA createNFA() {
        return new NFAImpl();
    }

}
