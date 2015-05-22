/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pucgo.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo
 */
public class NFA implements Automata {
    ArrayList<Character> simbolos;
    ArrayList<State> estados;
    State estadoInicial;
    ArrayList<State> estadosAtuais;

    Map<State, Map<Character, Map<String, State>>> transicoes;

    public NFA() {
        simbolos = new ArrayList<Character>();
        estados = new ArrayList<State>();
        estadosAtuais = new ArrayList<State>();
        transicoes = new HashMap<State, Map<Character, Map<String, State>>>();
    }

    public char getEpsilon() {
        return '$';
    }

    public List<Character> getSymbols() {
        return simbolos;

    }

    public State addState(String stateName) {
        State aux = new State(stateName);
        estados.add(aux);
        return aux;
    }

    public State addState(String stateName, boolean isFinal) {
        State aux = new State(stateName, isFinal);
        estados.add(aux);
        return aux;
    }

    public void setStart(State state) {
        estadoInicial = state;
    }

    public boolean accept(String word) {
        //adicionar o estado inicial ao estado atual
        estadosAtuais.add(estadoInicial);
        Integer adicionou = 0;
        String word2 = '$' + word;
        for (int i = 0; i < word2.length(); i++) {
            Character cadeiaAtual = word2.charAt(i);
            ArrayList<State> novosEstadosAtuais = new ArrayList<State>();
            for (int ii = 0; ii < estadosAtuais.size(); ii++) {//pegar todos os estados atuais e transformar em novos
                Map<String, State> proxEstados = pegarEstados(estadosAtuais.get(ii), cadeiaAtual);
                if (proxEstados != null) {
                    adicionou++;
                    for (int iii = 0; iii < proxEstados.size(); iii++) {//colocar todos os estados na lista de estados novos
                        novosEstadosAtuais.add(proxEstados.get(String.valueOf(iii)));
                    }
                    proxEstados.clear();
                }
                if (cadeiaAtual != '$') {
                    proxEstados = pegarEstados(estadosAtuais.get(ii), '$');
                    if (proxEstados != null) {
                        adicionou++;
                        for (int iii = 0; iii < proxEstados.size(); iii++) {//colocar todos os estados na lista de estados novos
                            novosEstadosAtuais.add(proxEstados.get(String.valueOf(iii)));
                        }
                    }
                }
                if (adicionou == 0) {
                    novosEstadosAtuais.add(estadosAtuais.get(ii));
                }
                adicionou = 0;
            }
            if (!novosEstadosAtuais.isEmpty()) {
                estadosAtuais.clear();
                for (int ii = 0; ii < novosEstadosAtuais.size(); ii++) {
                    estadosAtuais.add(novosEstadosAtuais.get(ii));
                }
            }

            novosEstadosAtuais.clear();
        }
        //depois, verificar se algum estado dos atuais � final
        for (int i = 0; i < estadosAtuais.size(); i++) {
            if (estadosAtuais.get(i).isFinal()) {
                return true;
            }
        }//se nenhum deles for final vai retornar false
        return false;
    }

    public Map<String, State> pegarEstados(State estado, Character symbol) {
        Map<Character, Map<String, State>> simbolosDaLista = transicoes.get(estado);
        if (simbolosDaLista != null) {
            return simbolosDaLista.get(symbol);
        } else {
            return null;
        }
    }

    public void addTransition(State sourceState, char symbol, State... targetStates) {
        if (transicoes.containsKey(sourceState)) {//se ja existe um espa�o para aquele estado
            Map<Character, Map<String, State>> estadoNaLista = transicoes.get(sourceState);
            if (estadoNaLista.containsKey(symbol)) {//se ja existe um espa�o para aquele simbolo
                Map<String, State> simboloNaLista = estadoNaLista.get(symbol);
                for (int i = 0; i < targetStates.length; i++) {
                    simboloNaLista.put(String.valueOf(estadoNaLista.size()), targetStates[i]);
                }
            } else {
                //criar ele
                Map<String, State> simboloNaLista = new HashMap<String, State>();
                for (int i = 0; i < targetStates.length; i++) {
                    simboloNaLista.put(String.valueOf(i), targetStates[i]);
                }
                estadoNaLista.put(symbol, simboloNaLista);
            }
        } else {
            //criar ele
            Map<Character, Map<String, State>> estadoNaLista = new HashMap<Character, Map<String, State>>();
            Map<String, State> simboloNaLista = new HashMap<String, State>();
            for (int i = 0; i < targetStates.length; i++) {
                simboloNaLista.put(String.valueOf(i), targetStates[i]);
            }
            estadoNaLista.put(symbol, simboloNaLista);
            transicoes.put(sourceState, estadoNaLista);
        }
    }
}
