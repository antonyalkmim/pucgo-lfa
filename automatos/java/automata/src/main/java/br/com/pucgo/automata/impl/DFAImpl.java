package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class DFAImpl implements DFA {


    //Lista de estados
    public List<MState> states = new ArrayList<MState>();

    //Simbolos do Alfabeto
    public List<Character> symbols = new ArrayList<Character>();

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
        MState state = existStateName(stateName);
        if(state != null){
            return state;
        }
        state = new MState(stateName, false);
        this.states.add(state);
        return state;
    }

    public State addState(String stateName, boolean isFinal) {
        MState state = existStateName(stateName);
        if(state != null){
            return state;
        }

        state = new MState(stateName, isFinal);
        this.states.add(state);
        return state;
    }

    public void setStart(State state) {
        this.startState = (MState) state;
    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {
        states.get(states.indexOf(sourceState)).addTransition(symbol, (MState) targetStates[0]);
    }

    public boolean accept(String word) {
        MState currState = this.startState;

        char[] _word = word.toCharArray();
        for(Character w:_word){

            /* Verifica se existe transicao para esse simbolo */
            if(!currState.getTransitions().containsKey(w))
                return false;

            currState = currState.getTransitions().get(w).get(0);
        }

        return currState.isFinal();
    }


    private MState existStateName(String stateName){
        for(MState state: states){
            if(state.getName().equals(stateName)){
                return state;
            }
        }
        return null;
    }

    public List<MState> getStates(){
        return this.states;
    }

}
