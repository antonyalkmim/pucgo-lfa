using Automata;

namespace Automata
{
	/// <summary>
	/// Um autômato finito não-determinístico.
	/// </summary>
	public interface INFA : IAutomata
	{
		/// <summary>
		/// Converte este autômato para um DFA.
		/// </summary>
		/// <returns>Uma instância de um DFA equivalente a este.</returns>
		IDFA ToDFA ();
	}

}
