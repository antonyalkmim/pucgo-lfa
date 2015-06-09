package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.NFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class NFAImpl implements NFA {

    //Lista de estados
    List<MState> states = new ArrayList<MState>();

    //Simbolos do Alfabeto
    List<Character> symbols = new ArrayList<Character>();

    //Estado inicial
    private MState startState = null;

    //Unico targetVazio
    private MState targetVazio = targetVazioFactory("$vazio");

    //Epsilon
    public static char Epsilon = '&';

    public char getEpsilon() {
        //Caso utilizar transicoes Epsilon entao o adiciona nos simbolos do alfabeto
        if(!this.symbols.contains(NFAImpl.Epsilon)){
            this.symbols.add(NFAImpl.Epsilon);
        }
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


    /**
     * Converte NFA para DFA
     * @return DFA
     */
    public DFA toDFA() {

        //Copia da lista de estados do NFA que sera modificano no decorrer do algoritmo
        List<MState> statesBKP = new ArrayList<MState>();
        statesBKP.addAll(states);

        DFAImpl dfa = new DFAImpl();
        dfa.getSymbols().addAll(this.symbols);

        /*
            statesBKP será modificada dinamicamente
            podendo adicionar mais states durante a execucao do loop
        */
        for(int i=0; i < statesBKP.size(); i++) {
            //Estado corrente
            MState currState = statesBKP.get(i);

            //Estado vazio nao necessita processar transicoes
            if(currState.equals(targetVazio))
                continue;

            //Processa todas as transicoes de cada simbolo
            for (Character symbol : symbols) {

                //Caso for estado inicial
                if (currState.getName().equals(this.startState.getName()))
                    dfa.setStart(currState);

                //Lista de transicoes para 'currState' quando ler 'symbol'
                List<MState> targets = currState.getTransitions().get(symbol);

                if(targets == null || targets.size() == 0){
                    //Caso nao houver targets entao adiciona a transicao para 'targetVazio'
                    currState.addTransition(symbol, targetVazio);

                    //Adiciona o targetVazio na lista de targets apenas uma vez
                    if(!statesBKP.contains(targetVazio)){
                        statesBKP.add(targetVazio);
                    }

                }else if (targets.size() > 1) {
                    /*
                        Caso houver varios targets, criar novo target a partir da lista de estados
                        e processar as transicoes desse novo target
                    */
                    MState newState = stateFromStates(targets, statesBKP);

                    //limpar lista de transicoes para adicionar apenas a transicao para o novo estado formado
                    targets.clear();

                    //adiciona transicao para novo estado criado
                    currState.addTransition(symbol, newState);
                }else if(targets.size() == 1){
                    /*
                        Caso tenha apenas 1 target entao apenas o adiciona na lista de targets do estado corrente.
                        Nao pode haver mais de uma transicao para targetVazio quando ler o mesmo symbol
                     */
                    if(!targets.get(0).getName().equals(targetVazio.getName())){
                        currState.addTransition(symbol, targets.get(0));
                    }
                }
            }
        }

        dfa.setStates(statesBKP);
        return dfa;
    }


    /**
     * Cria um novo estado a partir de uma lista de estados e o retorna
     * @param states
     * @return
     */
    private MState stateFromStates(List<MState> states){
        String newStateName = "";
        boolean newStateIsFinal = false;

        for(MState state: states){
            newStateName += state.getName();
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
        }

        return new MState(newStateName, newStateIsFinal, states);
    }


    /**
     * Cria um novo estado a partir de uma lista de estados e o adiciona na lista de dfaStates com suas devidas transicoes
     * @param states
     * @param dfaStates
     * @return
     */
    private MState stateFromStates(List<MState> states, List<MState> dfaStates){
        String newStateName = "";
        boolean newStateIsFinal = false;

        //Cria nome e verifica se e estado final
        for(MState state: states){
            newStateName += state.getName();
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
        }
        MState newState = new MState(newStateName, newStateIsFinal, states);

        //Adiciona as transicoes dos filhos na lista de transicoes do newState
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

        //verifica se a lista de estados filhos formam um estado ja criado
        for(Character symbol: symbols){
            List<MState> tar = newState.getTransitions().get(symbol);

            //Caso tenha varios filhos e um deles seja estado vazio
            if(tar != null && tar.size() > 1){
                tar.remove(targetVazio);
            }

            //Caso a lista de estados filhos formarem o mesmo estado criado, entao remove tudo e aponda para ele mesmo
            if(tar != null && tar.containsAll(states) && tar.size() == states.size()) {
                tar.clear();
                newState.addTransition(symbol, newState);
            }
        }
        //adiciona o novo estado na lista de states
        dfaStates.add(newState);

        return newState;
    }


    /**
     * Fabrica de targetVazio
     * @param stateName
     * @return MState
     */
    private MState targetVazioFactory(String stateName){
        //Criar target vazio e quando ler qualquer simbolo apondar para ele mesmo
        MState targetVazio = new MState(stateName, false);
        for(Character symbol: symbols) {
            targetVazio.addTransition(symbol, targetVazio);
        }

        //adiciona transicao para Epsilon
        targetVazio.addTransition(this.Epsilon, targetVazio);

        return targetVazio;
    }

}


