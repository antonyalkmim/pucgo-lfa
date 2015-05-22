using System;
using System.Collections.Generic;
using Automata;

namespace Automata
{
	/// <summary>
	/// Autômato finito determinístico.
	/// </summary>
	public interface IDFA : IAutomata
	{
		/// <summary>
		/// Miminiza este autômato, retornando uma nova instância equivalente e minimizada desta instância.
		/// </summary>
		IDFA Minimize ();
	}


}
