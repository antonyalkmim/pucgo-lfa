using System;

namespace Automata
{

	public class DFA : Automata, IDFA
	{
		public override char Epsilon {
			get {
				throw new NotImplementedException ();
			}
		}

		public override State AddState (string stateName, bool isFinal = false)
		{
			throw new NotImplementedException ();
		}

		public override void SetStart (State state)
		{
			throw new NotImplementedException ();
		}

		public override void AddTransition (State sourceState, char symbol, params State[] targetStates)
		{
			throw new NotImplementedException ();
		}

		public override bool Accept (string word)
		{
			throw new NotImplementedException ();
		}

		public IDFA Minimize ()
		{
			throw new NotImplementedException ();
		}
	}

}
