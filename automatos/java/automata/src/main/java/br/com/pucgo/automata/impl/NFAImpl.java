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
        dfa.getSymbols().addAll(this.symbols);
        dfa.setStart(this.startState);

        MState stateVAZIO = (MState) dfa.addState("$VAZIO");
        MState currState = (MState) dfa.addState(this.startState.getName(), this.startState.isFinal());

        boolean converter = true;
        while(converter){

            for(Character symbol: symbols){
                if(!currState.getTransitions().containsKey(symbol)){
                    dfa.addTransition(currState, symbol, stateVAZIO);
                }else{
                    List<MState> targetStates = currState.getTransitions().get(symbol);
                    if(targetStates.size() == 1){
                        dfa.addTransition(currState, symbol, targetStates.get(0));
                    }else{
                        MState state = this.stateFromStates(targetStates);

                        state = (MState) dfa.addState(state.getName(), state.isFinal());
                        dfa.addTransition(currState, symbol, state);
                    }
                }
            }


        }

        return dfa;
    }




    /**
     * Cria um estado a partir de uma lista de estados
     * @param states
     * @return MState
     */
    private MState stateFromStates(List<MState> states){
        String name = "";
        boolean isFinal = false;

        for(MState s:states){
            name += s.getName();
            if(!isFinal)
                isFinal = s.isFinal();
        }
        MState state = new MState(name, isFinal);

        //TODO: implementar transicoes de state

        return state;
    }


    private MState existStateName(String stateName, List<MState> states){
        for(MState s:states){
            if(s.getName().equals(stateName))
                return s;
        }
        return null;
    }

}
