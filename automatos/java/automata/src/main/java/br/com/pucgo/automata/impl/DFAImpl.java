package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class DFAImpl implements DFA {

    public List<Character> symbols = new ArrayList<Character>();
    public Map<State, Map<Character, List<State>>> transitions = new HashMap<State, Map<Character, List<State>>>();
    public State startState = null;

    public static char Epsilon = '&';

    /*
    {
        q1:{
            '0':[q1,q2,q3],
            '1':[q1,q2,q3]
        },
        q2:{
            '0':[q1,q2,q3],
            '1':[q1,q2,q3]
        }
    }*/

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
        State s = new State(stateName);
        this.transitions.put(s, new HashMap<Character, List<State>>());
        return s;
    }

    public State addState(String stateName, boolean isFinal) {
        State s = new State(stateName, isFinal);
        this.transitions.put(s, new HashMap<Character, List<State>>());
        return s;
    }

    public void setStart(State state) {
        this.startState = state;
    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {

        //verifica se symbol de transicao existe no alfabeto
        if(!symbols.contains(symbol)){
            System.out.println("Simbolo de transicao nao existe no alfabeto!");
            System.exit(1);
        }

        //Verifica se state informado existe
        if(!transitions.containsKey(sourceState)){
            System.out.println("SourceState Invalido!");
            System.exit(1);
        }

        //verifica se targetState informado existe
        if(!transitions.containsKey(targetStates[0])){
            System.out.println("TargetState Invalido!");
            System.exit(1);
        }

        //Busca a lista de targets para symbol a partir de sourceState
        List<State> targets = transitions.get(sourceState).get(symbol);
        if(targets == null){
            //Cria uma lista de targets e os adiciona para symbol
            transitions.get(sourceState).put(symbol, Arrays.asList(targetStates));
            return;
        }

        //Caso targets ja existir verifica se o targetState ja existe na lista
        if(targets.indexOf(targetStates[0]) != -1) {
            System.out.println("TargetState ja esta na lista de targets!");
            return;
        }

        //Adiciona na lista caso a lista ja exista e nao contenha targetStates
        targets.add(targetStates[0]);
    }


    public boolean accept(String word) {
        State currentState = this.startState;
        State nextState = null;

        char[] _word = word.toCharArray();
        for(char symbol: _word){

            //Alfabeto nao possui simbolo da palavra
            if(!this.symbols.contains(symbol)) {
                System.out.println("Alfabeto nao possui simbolo " + symbol + " da palavra");
                return false;
            }
            nextState = transitions.get(currentState).get(symbol).get(0);

            //Verifica se existe transicao para este simbolo
            if(nextState == null){
                return false;
            }

            currentState = nextState;
        }

        return currentState.isFinal();
    }
}
