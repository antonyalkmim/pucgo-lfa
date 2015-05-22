package br.com.pucgo.automata;

/**
 * Created by danfma on 22/05/15.
 */
public interface NFA extends Automata {

    /**
     * Converte esta instância para um DFA.
     * @return
     */
    public DFA toDFA();

}
