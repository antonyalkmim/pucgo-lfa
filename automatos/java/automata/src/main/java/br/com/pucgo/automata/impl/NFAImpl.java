package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.AutomataFactory;
import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.NFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class NFAImpl implements NFA {


    public static char Epsilon = '&';

    public char getEpsilon() {
        return NFAImpl.Epsilon;
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


    public DFA toDFA() {
        return null;
    }


    public void addTransition(State sourceState, char symbol, State... targetStates) {

    }

    public boolean accept(String word) {
        return false;
    }
}
