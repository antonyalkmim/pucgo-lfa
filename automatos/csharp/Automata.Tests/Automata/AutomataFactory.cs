using System;
using Automata;

namespace Automata
{

	public static class AutomataFactory
	{
		/// <summary>
		/// Cria um autômato não-determinístico.
		/// </summary>
		/// <returns>The automata.</returns>
		public static INFA CreateNFA ()
		{
			return new NFA ();
		}

		/// <summary>
		/// 
		/// </summary>
		/// <returns>The DF.</returns>
		public static IDFA CreateDFA ()
		{
			return new DFA ();
		}
	}

}
