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
        int index = states.indexOf(sourceState);
        for(State s:targetStates){
            states.get(index).addTransition(symbol, (MState) s);
        }
    }

    public boolean accept(String word) {
        return this.toDFA().accept(word);
    }


    public DFA toDFA() {
        DFA dfa = AutomataFactory.createDFA();
        dfa.getSymbols().addAll(this.symbols);


        //adicionar somente quando nao houver transicoes para um determinado simbolo
        MState stateVAZIO = new MState("$VAZIO");

        MState currState = (MState) dfa.addState(this.startState.getName(), this.startState.isFinal());

        for(Character symbol: symbols){

            if(currState.getTransitions().containsKey(symbol)){
                List<MState> targets = currState.getTransitions().get(symbol);
                
            }
        }




        dfa.setStart(this.startState);
        return dfa;
    }


}
