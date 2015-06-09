package br.com.pucgo.automata.impl;

/**
 * Created by antonyalkmim on 05/06/15.
 */

import br.com.pucgo.automata.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MState extends State {

    //Armazena os estados que compoem este estado(Quando for criado a partir de uma lista de estados)
    private List<MState> compoundState = new ArrayList<MState>();

    //Mapa de transicoes. Ex: 'a'=>[s1,s2,s3],'b'=>[...]
    private Map<Character, List<MState>> transitions = new HashMap<Character, List<MState>>();

    public MState(String name) {
        super(name);
    }

    public MState(String name, boolean isFinal) {
        super(name, isFinal);
    }

    /**
     * Construtor para quando o estado for criado a partir de uma lista de estados
     * @param name String
     * @param isFinal boolean
     * @param compoundState List
     */
    public MState(String name, boolean isFinal, List<MState> compoundState) {
        super(name, isFinal);
        this.compoundState.addAll(compoundState);
    }

    /**
     * Adiciona transicao para 'state' quando ler 'symbol'
     * @param symbol Character
     * @param state MState
     */
    public void addTransition(Character symbol, MState state){
        List<MState> targetStates;

        if(transitions.containsKey(symbol)) {
            targetStates = transitions.get(symbol);
            if(targetStates.indexOf(state) == -1) { //nao pode repetir mesmo estado
                targetStates.add(state);
            }
        }else{
            targetStates = new ArrayList<MState>();
            targetStates.add(state);
            transitions.put(symbol,targetStates);
        }

    }

    /**
     * Retorna o mapa de transicoes
     * @return Map
     */
    public Map<Character, List<MState>> getTransitions(){
        return this.transitions;
    }

}