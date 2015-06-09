package br.com.pucgo.automata;

import br.com.pucgo.automata.impl.MState;

import java.util.List;

/**
 * Define uma interface comum para um autômato.
 *
 * Created by danfma on 22/11/14.
 */
public interface Automata {

    /**
     * Obtém o caractere utilizado para representar o epsilon (transição vazia).
     *
     * @return o caractere epsilon.
     */
    public char getEpsilon();

    /**
     * Lista de símbolos do alfabeto deste autômato.
     *
     * @return a lista de símbolos.
     */
    public List<Character> getSymbols();

    /**
     * Adiciona um novo estado não-final ao autômato.
     *
     * @param stateName o nome do estado.
     * @return o estado adicionado.
     */
    public State addState(String stateName);

    /**
     * Adiciona um novo estado ao autômato.
     *
     * @param stateName O nome do estado.
     * @param isFinal Se o estado é final ou não.
     * @return o estado adicionado.
     */
    public State addState(String stateName, boolean isFinal);

    /**
     * Define o estado inicial do autômato.
     *
     * @param state Um estado previamente criado para o autômato.
     */
    public void setStart(State state);

    /**
     * Adiciona uma nova transição ao autômato.
     *
     * @param sourceState   O estado de início.
     * @param symbol        Símbolo para a transição.
     * @param targetStates  Os estados de destino.
     */
    public void addTransition(State sourceState, char symbol, State... targetStates);

    /**
     * Determina se este autômato aceita a cadeia especificada por <paramref name="word"/>.
     *
     * @param word a cadeia a ser testada.
     *
     * @return true, se for aceita; caso contrário, false.
     */
    public boolean accept(String word);

}
