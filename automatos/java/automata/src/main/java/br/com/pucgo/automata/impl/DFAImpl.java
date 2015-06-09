package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class DFAImpl implements DFA {


    //Lista de estados
    public List<MState> states = new ArrayList<MState>();

    //Simbolos do Alfabeto
    public List<Character> symbols = new ArrayList<Character>();

    //Estado inicial
    private MState startState = null;

    //Epsilon
    public static char Epsilon = '&';

    /**
     * Retorna o caracter Epsilon
     * @return char
     */
    public char getEpsilon() {
        return DFAImpl.Epsilon;
    }

    /**
     * Retorna o alfabeto
     * @return List<Character> symbols
     */
    public List<Character> getSymbols() {
        return symbols;
    }

    /**
     * Adiciona novo estado
     * @param stateName o nome do estado.
     * @return
     */
    public State addState(String stateName) {
        return this.addState(stateName, false);
    }

    /**
     * Adiciona novo estado
     * @param stateName O nome do estado.
     * @param isFinal Se o estado é final ou não.
     * @return
     */
    public State addState(String stateName, boolean isFinal) {
        //Caso o estado ja existir apenas o retorna
        MState state = existStateName(stateName);
        if(state != null){
            return state;
        }

        state = new MState(stateName, isFinal);
        this.states.add(state);
        return state;
    }

    /**
     * Informa o estado inicial do automato
     * @param state Um estado previamente criado para o autômato.
     */
    public void setStart(State state) {
        this.startState = (MState) state;
    }

    /**
     * Adiciona Transicao de sourceState para targetStates quando ler symbol
     * @param sourceState   O estado de início.
     * @param symbol        Símbolo para a transição.
     * @param targetStates  Os estados de destino.
     */
    public void addTransition(State sourceState, char symbol, State... targetStates) {
        states.get(states.indexOf(sourceState)).addTransition(symbol, (MState) targetStates[0]);
    }

    /**
     * Verifica se o automato aceita a entrada 'word'
     *
     * @param word a cadeia a ser testada.
     * @return boolean
     */
    public boolean accept(String word) {

        //Automato deve haver um estado inicial
        if(this.startState == null)
            return false;

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

    /**
     * Metodo para obter o DFA em sua forma minimizada com o menos numero de estados e transicoes
     * @return DFA
     */
    public DFA minimize() {
        //TODO: implementar algoritmo de minimizacao de DFA
        return null;
    }

    /**
     * Verifica se existe algum estado com o mesmo nome na lista de estados
     * Retorna o estado encontrado e null se o estado nao for encontrado
     *
     * @param stateName
     * @return
     */
    private MState existStateName(String stateName){
        for(MState state: states){
            if(state.getName().equals(stateName)){
                return state;
            }
        }
        return null;
    }

    /**
     * Adiciona nova lista de estado
     * @param states
     */
    public void setStates(List<MState> states){
        this.states = states;
    }

}
