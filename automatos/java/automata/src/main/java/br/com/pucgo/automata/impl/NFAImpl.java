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

    public void addStates(List<MState> s) {

    }


    public DFA toDFA() {

        List<MState> statesBKP = new ArrayList<MState>();
        statesBKP.addAll(states);

        DFA dfa = AutomataFactory.createDFA();
        dfa.getSymbols().addAll(this.symbols);


        //Criar target vazio e quando ler qualquer simbolo apondar para ele mesmo
        MState targetVazio = new MState("$vazio", false);
        for(Character symbol: symbols) {
            targetVazio.addTransition(symbol, targetVazio);
        }


        for(int i=0; i < statesBKP.size(); i++) {
            MState currState = statesBKP.get(i);
            //MState currState = (MState) dfa.addState(_currState.getName(), _currState.isFinal());

            for (Character symbol : symbols) {
                /*
                //passa as transicoes de _currState para currState do DFA
                if(_currState.getCompoundState().size() > 0) {
                    currState = _currState;
                }else if(_currState.getTransitions().containsKey(symbol)) {
                    List<MState> list = _currState.getTransitions().get(symbol);
                    for(MState s:list)
                        currState.addTransition(symbol, s);
                }*/

                //Caso for estado inicial
                if(currState.getName().equals(this.startState.getName()))
                    dfa.setStart(currState);

                List<MState> targets = currState.getTransitions().get(symbol);
                if (targets.size() > 1) {
                    MState newState = stateFromStates(targets, statesBKP);
                    if(currState.getTransitions().containsKey(symbol))
                        currState.getTransitions().get(symbol).clear();

                    currState.addTransition(symbol, newState);
                }else if(targets.size() == 1){
                    currState.addTransition(symbol, targets.get(0));
                }else{
                    if(!statesBKP.contains(targetVazio)){
                        statesBKP.add(targetVazio);
                    }

                    currState.addTransition(symbol, targetVazio);
                }
            }
        }

        //TODO: adicionar os statesBKP na lista de states do DFA
        dfa.addStates(statesBKP);
        return dfa;
    }


    private MState stateFromStates(List<MState> states){
        String newStateName = "";
        boolean newStateIsFinal = false;

        for(MState state: states){
            newStateName += state.getName();
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
        }

        return new MState(newStateName, newStateIsFinal, states);
    }




    private MState stateFromStates(List<MState> states, List<MState> dfaStates){
        String newStateName = "";
        boolean newStateIsFinal = false;

        for(MState state: states){
            newStateName += state.getName();
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
        }

        MState newState = new MState(newStateName, newStateIsFinal, states);

        for(Character symbol: symbols) {
            for(MState state: states){
                if(state.getTransitions().containsKey(symbol)){
                    List<MState> tmpTargets = state.getTransitions().get(symbol);
                    for (MState t : tmpTargets) {
                        newState.addTransition(symbol, t);
                    }
                }
            }
        }


        for(Character symbol: symbols){
            if(newState.getTransitions().get(symbol).containsAll(states) && newState.getTransitions().get(symbol).size() == states.size()) {
                newState.getTransitions().get(symbol).clear();
                newState.addTransition(symbol, newState);
            }
        }


        dfaStates.add(newState);
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


