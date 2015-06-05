package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.AutomataFactory;
import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.NFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class NFAImpl implements NFA {

    //Lista de estados
    List<MState> states = new ArrayList<MState>();

    //Simbolos do Alfabeto
    List<Character> symbols = new ArrayList<Character>();

    private MState startState = null;

    //Epsilon
    public static char Epsilon = '&';


    public char getEpsilon() {
        return NFAImpl.Epsilon;
    }

    public List<Character> getSymbols() {
        return symbols;
    }

    public State addState(String stateName) {
        MState state = new MState(stateName, false);
        this.states.add(state);
        return state;
    }

    public State addState(String stateName, boolean isFinal) {
        MState state = new MState(stateName, isFinal);
        this.states.add(state);
        return state;
    }

    public void setStart(State state) {
        this.startState = (MState) state;
    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {
        for(State s:targetStates){
            states.get(states.indexOf(sourceState)).addTransition(symbol, (MState) s);
        }
    }

    public boolean accept(String word) {
        return this.toDFA().accept(word);
    }


    public DFA toDFA() {
        DFA dfa = AutomataFactory.createDFA();

        //TODO: implementar conversao para DFA

        return dfa;
    }

}
