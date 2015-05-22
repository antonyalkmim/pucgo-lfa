package br.com.pucgo.automata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos R on 06/12/2014.
 */
public class Machine implements Automata {

    State init;
    private char epsilon = '#';
    private ArrayList<Character> alfabeto = new ArrayList<Character>();

    @Override
    public char getEpsilon()
    {
        return epsilon;
    }

    @Override
    public List<Character> getSymbols() {
        return alfabeto;
    }

    @Override
    public State addState(String stateName) {
        return new State(stateName);
    }

    @Override
    public State addState(String stateName, boolean isFinal) {
        return new State(stateName,isFinal);
    }

    @Override
    public void setStart(State state) {
        init = state;
    }

    @Override
    public void addTransition(State sourceState, char symbol, State... targetStates) {
        int j = targetStates.length;
        Character symbol2 = symbol;
        for(int i=0; i<j;i++) {
            sourceState.setTransition(symbol2, targetStates[i]);
        }
    }

    @Override
    public boolean accept(String word) {
        return testCadeia(word,0,init);
    }

    private boolean testCadeia(String word, int interator, State actual)
    {
        /*
        int word_size = word.length();
        if(interator == word_size)
            return actual.isFinal();
        ArrayList<State> adjacentes = new ArrayList<State>();

        actual.trantionsSymbol(adjacentes, getEpsilon());
        for(State next : adjacentes)
            if (testCadeia(word, interator, next))
                return true;

        adjacentes.clear();
        Character symbol = word.charAt(interator);
        actual.trantionsSymbol(adjacentes, symbol);
        for (State next : adjacentes)
            if(testCadeia(word, interator++, next))
                return true;
        return actual.isFinal();
        */
        int word_size = word.length();
        ArrayList<State> adjacentes = new ArrayList<State>(); //Lista vai receber nós de permutação
        for(;interator < word_size ;interator++) //interaje sobre a Palavra
        {
            adjacentes.clear();
            actual.trantionsSymbol(adjacentes,getEpsilon()); //Transição com epsilon
            for (State next : adjacentes) //Interação sobre os nós adjacentes
                if (testCadeia(word, interator, next)) //Prosegue com uma nova ramificação no automoto
                    return true;

            adjacentes.clear();
            Character symbol = word.charAt(interator); //Retira simbolo a simbolo da Palavra
            actual.trantionsSymbol(adjacentes, symbol); //Recupera todas as transiçoes do nó atual para os demais nós
            if(adjacentes.size()> 1) { //Se a lista de adjacencia tem mais de uma transição se inicia o NFE
                for (State next : adjacentes) //Interação sobre os nós adjacentes
                    if (testCadeia(word, interator++, next)) //Prosegue com uma nova ramificação no automoto
                        return true; // Caso a chamada termine a cadeia em um simbolo valido então retorna verdadeiro
            }
            else { //Se existe apenas uma transição para o simbolo no nó então segue fluxo normal para ele
                if(adjacentes.isEmpty())
                    return false;
                actual = adjacentes.get(0); //Nó atual passa a ser o nó da permutação
            }
        }
        return actual.isFinal(); //Findada a execução teste se terminou em um nó de estado final valido
    }
}
