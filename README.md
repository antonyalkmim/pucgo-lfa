> Repositório para atividades relacionadas à disciplina de LFA ministrada na PUC Goiás 


# Autômato DFA e NFA

Implementação em `Java` dos autômatos DFA(Autômato Finito Determinístico) e NFA(Autômato Finito Não Determinístico)

## DFA
Implementação do DFA
- `boolean accept(String word);` Retorna verdadeiro caso o autômato aceite a palavra `word`.
- `DFA minimize();` Retorna a instância do DFA minimizado.
- `List<Character> getSymbols();` Retorna a lista de símbolos aceitos, ou seja, o alfabeto.
- `State addState(String stateName);` Adiciona um estado no autômato e retorna a instância do estado(State).
- `char getEpsilon();` Retorna o caractér referente ao `Epsilon`.
- `State addState(String stateName, boolean isFinal);` Adiciona um estado no autômato informando se o estado é final e retorna a instância do estado(State).
- `void setStart(Start state);` Informa qual é o estado inicial do autômato.
- `void (State sourceState, char symbol, State... targetState);` Adiciona uma transição de `sourceState` para `targetState` quando ler um símbolo `symbol`.


## NFA
Implementação do NFA
- `boolean accept(String word);` Retorna verdadeiro caso o autômato aceite a palavra `word`.
- `DFA minimize();` Retorna a instância do NFA convertido em DFA em sua forma minimizada.
- `List<Character> getSymbols();` Retorna a lista de símbolos aceitos, ou seja, o alfabeto.
- `State addState(String stateName);` Adiciona um estado no autômato e retorna a instância do estado(State).
- `char getEpsilon();` Retorna o caractér referente ao `Epsilon`.
- `State addState(String stateName, boolean isFinal);` Adiciona um estado no autômato informando se o estado é final e retorna a instância do estado(State).
- `void setStart(Start state);` Informa qual é o estado inicial do autômato.
- `void (State sourceState, char symbol, State... targetState);` Adiciona uma transição de `sourceState` para os `targetState` quando ler um símbolo `symbol`.
- `DFA toDFA();` Retorna o NFA convertido para DFA.




