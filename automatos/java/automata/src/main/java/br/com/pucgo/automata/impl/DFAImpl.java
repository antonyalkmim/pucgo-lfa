package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class DFAImpl implements DFA {


    //Lista de estados
    List<MState> states = new ArrayList<MState>();

    //Simbolos do Alfabeto
    List<Character> symbols = new ArrayList<Character>();

    private MState startState = null;

    //Epsilon
    public static char Epsilon = '&';

    public DFA minimize() {
        //TODO: implementar algoritmo de minimizacao de DFA
        return null;
    }


    public char getEpsilon() {
        return DFAImpl.Epsilon;
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
        MState state = states.get(states.indexOf(sourceState));
        state.getTransitions().put(symbol, (MState) targetStates[0]);
    }

    public boolean accept(String word) {
        MState currState = this.startState;

        char[] _word = word.toCharArray();
        for(Character w:_word){

            /* Verifica se existe transicao para esse simbolo */
            if(!currState.getTransitions().containsKey(w))
                return false;

            currState = currState.getTransitions().get(w);

        }

        return currState.isFinal();
    }



    private class MState extends State{

        private Map<Character, MState> transitions = new HashMap<Character, MState>();

        public MState(String name) {
            super(name);
        }

        public MState(String name, boolean isFinal) {
            super(name, isFinal);
        }

        public void addTransition(Character symbol, MState state){
            this.transitions.put(symbol, state);
        }

        public Map<Character, MState> getTransitions(){
            return this.transitions;
        }

    }

}
