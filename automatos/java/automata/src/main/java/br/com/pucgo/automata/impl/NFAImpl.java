package br.com.pucgo.automata.impl;

import br.com.pucgo.automata.AutomataFactory;
import br.com.pucgo.automata.DFA;
import br.com.pucgo.automata.NFA;
import br.com.pucgo.automata.State;

import java.util.*;


public class NFAImpl implements NFA {

    protected List<Character> symbols = new ArrayList<Character>();
    protected Map<State, Map<Character, List<State>>> transitions = new HashMap<State, Map<Character, List<State>>>();
    protected State startState = null;
    public static char Epsilon = '&';

    public char getEpsilon() {
        return NFAImpl.Epsilon;
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

    public DFA toDFA() {

        /*
        * 1. A partir do estado inicial, percorrer todas os seus filhos
        *   1.1. Caso o filho tenha mais de um estado target entao
        *       => Criar um novo estado e montar as transicoes de acordo com todos os estados de partida
        *
        * */


        Map<State, Map<Character, List<State>>> transitionsTMP = new HashMap<State, Map<Character, List<State>>>();

        DFA dfa = AutomataFactory.createDFA();
        dfa.setStart(startState);
        dfa.getSymbols().addAll(symbols);

        State targetVazio = new State("VAZIO", false);
        transitionsTMP.put(targetVazio, new HashMap<Character, List<State>>());

        convert(startState, targetVazio, transitionsTMP);

        //Adiciona as transicoes formatadas no DFA
        Set<State> states = transitionsTMP.keySet();
        for(State s: states){
            for(Character c: symbols) {
                dfa.addTransition(s, c, transitionsTMP.get(s).get(c).get(0));
            }
        }

        return dfa;
    }

    private void convert(State currState, State emptyState, Map<State, Map<Character, List<State>>> transitionsTMP){

        Set<State> statesInAutomata = transitionsTMP.keySet();

        for(Character symbol: symbols){

            List<State> targetStates = transitionsTMP.get(currState).get(symbol);
            Map<Character, List<State>> mapTrans = new HashMap<Character, List<State>>();

            if(targetStates.size() > 1){
                State target = stateFromTargetStates(targetStates);

                mapTrans.put(symbol, Arrays.asList(target));
                transitionsTMP.put(currState, mapTrans);

                transitionsTMP.put(target, new HashMap<Character, List<State>>());
                addTransitionsTargets(target, emptyState, symbol, targetStates, transitionsTMP);
            }

        }

    }

    private void addTransitionsTargets(State target, State emptyState, Character symbol,  List<State> targets, Map<State, Map<Character, List<State>>> transitionsTMP){

        Map<Character, List<State>> mapTransitions = transitionsTMP.get(target);

        for(Character sym:symbols){
            List<State> trans = new ArrayList<State>();

            for(State state:targets){
                if(transitionsTMP.containsKey(state)){
                    trans.addAll(transitionsTMP.get(state).get(sym));
                }else{
                    trans.addAll(transitions.get(state).get(sym));
                }



            }

            //mapTransitions.put(sym, Arrays.asList(new State()));
        }


        //adicionar a transicao do estado para o proximo estado

        transitionsTMP.put(target, mapTransitions);
    }

    /**
     * Cria um State a partir de uma lista de States
     * @param targetStates
     * @return State
     */
    private State stateFromTargetStates(List<State> targetStates){
        String newStateName = "";
        boolean newStateIsFinal = false;

        for(State state: targetStates){
            newStateIsFinal = newStateIsFinal ? newStateIsFinal : state.isFinal();
            newStateName += state.getName();
        }

        return new State(newStateName, newStateIsFinal);
    }


    /*
    * Problema com o algoritmo:
    *   Ao criar o estado S0S1 e seus filhos, os filhos sao gerados como uma lista por exemplo:
    *       S0-> s0s1
    *       s0s1->[s0,s1]; // quando chegar aqui cria outra vez o filho s0s1 com outra instancia dele
    *
    * */
    private void convertState(State currState, State targetVazio, Map<State, Map<Character, List<State>>> transitionsTMP){
        Map<Character, List<State>> trans = transitions.get(currState);
        State target = targetVazio;

        for(Character symbol: symbols){
            Map<Character, List<State>> m = new HashMap<Character, List<State>>();
            List<State> targets = trans.get(symbol);

            if(targets.size() > 1){
                String newStateName = "";
                boolean newStateIsFinal = false;
                Map<Character, List<State>> subM = new HashMap<Character, List<State>>();

                for(State _s:targets){
                    newStateIsFinal = newStateIsFinal ? true : _s.isFinal();
                    newStateName += _s.getName();
                }
                for(Character syn: symbols){
                    List<State> listaTargets = new ArrayList<State>();
                    for(State _s: targets){
                        List<State> tmpStates = transitions.get(_s).get(syn);
                        if(tmpStates==null){
                            if(transitionsTMP.get(_s) != null){
                                tmpStates = transitionsTMP.get(_s).get(syn);
                            }else{
                                tmpStates = new ArrayList<State>();
                            }
                        }

                        for(State tmpState: tmpStates){
                            if(!listaTargets.contains(tmpState)) {
                                listaTargets.add(tmpState);
                            }
                        }

                    }
                    subM.put(syn, listaTargets);
                }
                target = new State(newStateName, newStateIsFinal);


                if(transitionsTMP.get(currState) == null){
                    m.put(symbol, Arrays.asList(target));
                    transitionsTMP.put(currState, m);
                }else{
                    transitionsTMP.get(currState).put(symbol, Arrays.asList(target));
                }


                transitionsTMP.put(target, subM);

                convertState(target,targetVazio, transitionsTMP);

            }else{

                if(targets.size() == 0)
                    targets.add(target);

                m.put(symbol, targets);
                transitionsTMP.put(currState, m);

                //converte o resto das transicoes sendo que a ultima e o estado VAZIO
                //if(targets.get(0) != targetVazio)
                //    convertState(targets.get(0),targetVazio, transitionsTMP);
            }
        }


    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {

        //verifica se symbol de transicao existe no alfabeto
        if(!symbols.contains(symbol) && symbol != this.getEpsilon()){
            System.out.println("Simbolo de transicao nao existe no alfabeto!");
            System.exit(1);
        }

        //Verifica se state informado existe
        if(!transitions.containsKey(sourceState)){
            System.out.println("SourceState Invalido!");
            System.exit(1);
        }

        //Busca a lista de targets para symbol a partir de sourceState
        List<State> targets = transitions.get(sourceState).get(symbol);
        if(targets == null){
            //Cria uma lista de targets e os adiciona para symbol
            transitions.get(sourceState).put(symbol, Arrays.asList(targetStates));
            return;
        }

        for(State target: targetStates){

            //Caso targets ja existir verifica se o targetState ja existe na lista
            if(targets.indexOf(target) != -1) {
                System.out.println(target.getName() + " ja esta na lista de targets!");
                continue;
            }

            //Adiciona na lista caso a lista ja exista e nao contenha targetStates
            targets.add(target);
        }
    }

    public boolean accept(String word) {
        //converter para DFA e depois verificar se aceita a palavra
        return this.toDFA().accept(word);
    }
}
