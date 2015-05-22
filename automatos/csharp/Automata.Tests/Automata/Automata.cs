using System;
using System.Collections.Generic;

namespace Automata
{
	public abstract class Automata : IAutomata
	{
		public abstract char Epsilon { get; }

		public List<char> Symbols { get; }

		public abstract State AddState (string stateName, bool isFinal = false);

		public abstract void SetStart (State state);

		public abstract void AddTransition (State sourceState, char symbol, params State[] targetStates);

		public abstract bool Accept (string word);
	}

}
