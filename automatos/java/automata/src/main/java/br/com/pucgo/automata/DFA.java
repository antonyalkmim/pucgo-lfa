package br.com.pucgo.automata;

/**
 * Created by danfma on 22/05/15.
 */
public interface DFA extends Automata {

    /**
     * Converte este autômato em uma instância mínima.
     *
     * @return
     */
    public DFA minimize();

}
