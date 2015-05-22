package br.com.pucgo.automata;

import java.util.ArrayList;

/**
 * Representa um estado de um autômato.
 *
 * Created by danfma on 22/11/14.
 */
public class State {
    private String name;
    private boolean isFinal;
    private ArrayList<State> adjacentes;
    private ArrayList<Character> symbol_transition;

    /**
     * Inicializa uma instância desta classe.
     *
     * @param name nome deste estado.
     */
    public State(String name) {
        this.name = name;
        adjacentes = new ArrayList<State>();
        symbol_transition = new ArrayList<Character>();
    }

    /**
     * Inicializa uma instância desta classe.
     *
     * @param name      nome deste estado.
     * @param isFinal   se o estado é final ou não.
     */
    public State(String name, boolean isFinal) {
        this(name);
        this.name = name;
        this.isFinal = isFinal;
    }

    /**
     * O nome deste estado.
     *
     * @return o nome.
     */
    public String getName() {
        return name;
    }

    /**
     * Determina se este autômato é final ou não.
     *
     * @return true, se o autômato é final; caso contrário, false.
     */
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return String.format("State: %s (final = %s)", name, isFinal ? "true" : "false");
    }

    public void setTransition(Character symbol, State state)
    {
        adjacentes.add(state);
        symbol_transition.add(symbol);
    }

    public void trantionsSymbol( ArrayList<State> adjacente, Character symbol)
    {
        int j = symbol_transition.size();
        for(int i=0; i<j; i++)
            if(symbol_transition.get(i) == symbol)
                adjacente.add(this.adjacentes.get(i));
    }
}
