package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.AutomataFactory;
import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.NFA;
import br.com.pucgo.automata.State;

import java.lang.reflect.Array;
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


        //Criar target vazio e quando ler qualquer simbolo apondar para ele mesmo
        MState targetVazio = (MState) dfa.addState("$vazio");
        for(Character symbol: symbols) {
            dfa.addTransition(targetVazio, symbol, targetVazio);
        }



        for(MState currState: states) {
            for (Character symbol : symbols) {
                MState state = (MState) dfa.addState(currState.getName(), currState.isFinal());

                if(currState.getName().equals(this.startState.getName()))
                    dfa.setStart(state);

                List<MState> targets = currState.getTransitions().get(symbol);
                if (targets.size() > 1) {
                    MState newState = stateFromStates(targets, dfa);
                    state.addTransition(symbol, newState);
                }else if(targets.size() == 1){
                    state.addTransition(symbol, targets.get(0));
                }else{
                    state.addTransition(symbol, targetVazio);
                }
            }
        }


        return dfa;
    }

    private MState stateFromStates(List<MState> states, DFA dfa){
        String newStateName = "";
        boolean newStateIsFinal = false;

        for(MState state: states){
            newStateName += state.getName();
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
        }
        MState newState = (MState) dfa.addState(newStateName, newStateIsFinal);

        for(Character symbol: dfa.getSymbols()) {
            for(MState state: states){
                if(state.getTransitions().containsKey(symbol)){
                    List<MState> tmpTargets = state.getTransitions().get(symbol);
                    for(MState t:tmpTargets) {
                        newState.addTransition(symbol, t);
                    }
                }
            }
        }



        return newState;
    }

    private MState verificaState(List<MState> states, List<MState> targetStates){

        for(MState state: states){

        }

        return null;
    }

    private MState existStateName(String stateName){
        for(MState state: states){
            if(state.getName().equals(stateName)){
                return state;
            }
        }
        return null;
    }

}


