package br.com.pucgo.automata;

/**
 * A fábrica de autômatos.
 *
 * Created by danfma on 22/11/14.
 */
public final class AutomataFactory {

    /**
     * Cria um novo autômato NFA.
     *
     * @return a instância do autômato.
     */
    public static Automata createNonDeterministicAutomata() {
        return new NFA(); // substitua pela sua implementação
    }

}
