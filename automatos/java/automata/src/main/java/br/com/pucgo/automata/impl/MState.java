package br.com.pucgo.automata.impl;

/**
 * Created by antonyalkmim on 05/06/15.
 */

import br.com.pucgo.automata.State;

import java.util.*;

public class MState extends State implements Cloneable {

    private boolean isFinal;

    //Armazena os estados que compoem este estado(Quando for criado a partir de uma lista de estados)
    private List<MState> compoundState = new ArrayList<MState>();

    //Mapa de transicoes. Ex: 'a'=>[s1,s2,s3],'b'=>[...]
    private Map<Character, List<MState>> transitions = new HashMap<Character, List<MState>>();

    public MState(String name) {
        super(name);
        this.isFinal = false;
    }

    public MState(String name, boolean isFinal) {
        super(name, isFinal);
        this.isFinal = isFinal;
    }

    /**
     * Construtor para quando o estado for criado a partir de uma lista de estados
     * @param name String
     * @param isFinal boolean
     * @param compoundState List
     */
    public MState(String name, boolean isFinal, List<MState> compoundState) {
        super(name, isFinal);
        this.isFinal = isFinal;
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

    /**
     * Retorna a lista de estados que compoe o estado
     * @return List<MState>
     */
    public List<MState> getCompoundState(){
        return this.compoundState;
    }

    public MState clonar(boolean isFinal) {
        MState cloned = new MState(this.getName(), isFinal);
        cloned.getTransitions().putAll((this.getTransitions()));

        return null;
    }

    /**
     * Retorna se estado e final
     * @return
     */
    @Override
    public boolean isFinal(){
        return this.isFinal;
    }

    /**
     * Seta o estado como final
     * @param isFinal
     */
    public void setIsFinal(boolean isFinal){
        this.isFinal = isFinal;
    }

}