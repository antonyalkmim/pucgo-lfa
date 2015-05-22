package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.State;

import java.util.List;

/**
 * ALTERE ESTA INSTÂNCIA.
 *
 * Created by danfma on 22/05/15.
 */
public class DFAImpl implements DFA {
    public DFA minimize() {
        return null;
    }

    public char getEpsilon() {
        return 0;
    }

    public List<Character> getSymbols() {
        return null;
    }

    public State addState(String stateName) {
        return null;
    }

    public State addState(String stateName, boolean isFinal) {
        return null;
    }

    public void setStart(State state) {

    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {

    }

    public boolean accept(String word) {
        return false;
    }
}
