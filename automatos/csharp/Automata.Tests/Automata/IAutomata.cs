using System;
using System.Collections.Generic;

namespace Automata
{
	/// <summary>
	/// Define uma interface comum para um autômato.
	/// </summary>
	public interface IAutomata
	{
		/// <summary>
		/// Obtém o caractere utilizado para representar o epsilon (transição vazia).
		/// </summary>
		/// <value>O caractere epsilon.</value>
		char Epsilon { get; }

		/// <summary>
		/// Lista de símbolos do alfabeto deste autômato.
		/// </summary>
		/// <value>Os símbolos do alfabeto.</value>
		List<char> Symbols { get; }

		/// <summary>
		/// Adiciona um novo estado ao autômato.
		/// </summary>
		/// <param name="stateName">O nome do estado.</param>
		/// <param name="isFinal">Determina se o estado é um estado final.</param>
		/// <returns>O estado criado.</returns>
		State AddState (string stateName, bool isFinal = false);

		/// <summary>
		/// Define o estado inicial do autômato.
		/// </summary>
		/// <param name="state">O estado inicial.</param>
		void SetStart (State state);

		/// <summary>
		/// Adiciona uma nova transição ao autômato.
		/// </summary>
		/// <param name="sourceState">O estado inicial.</param>
		/// <param name="symbol">O símbolo lido.</param>
		/// <param name="targetStates">Os estados de destino.</param>
		void AddTransition (State sourceState, char symbol, params State[] targetStates);

		/// <summary>
		/// Determina se este autômato aceita a cadeia especificada por <paramref name="word"/>.
		/// </summary>
		/// <param name="word">Cadeia a ser testada.</param>
		bool Accept (string word);
	}


}

