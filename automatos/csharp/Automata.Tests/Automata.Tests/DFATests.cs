using NUnit.Framework;

namespace Automata.Tests
{
	[TestFixture]
	public class DFATests
	{
		[TestCase ("01")]
		[TestCase ("011")]
		[TestCase ("0101")]
		[TestCase ("1")]
		[TestCase ("11")]
		[TestCase ("1101")]
		public void ShouldAcceptDFALanguage (string word)
		{
			var automata = AutomataFactory.CreateDFA ();

			automata.Symbols.Add ('0');
			automata.Symbols.Add ('1');

			var q0 = automata.AddState ("Q0");
			var q1 = automata.AddState ("Q1", true);

			automata.SetStart (q0);

			automata.AddTransition (q0, '0', q0);
			automata.AddTransition (q0, '1', q1);
			automata.AddTransition (q1, '0', q0);
			automata.AddTransition (q1, '1', q1);

			Assert.IsTrue (automata.Accept (word));
		}

		[TestCase ("0")]
		[TestCase ("010")]
		[TestCase ("01010")]
		[TestCase ("10")]
		[TestCase ("00")]
		[TestCase ("11010")]
		public void ShouldNotAcceptDFALanguage (string word)
		{
			var automata = AutomataFactory.CreateDFA ();

			automata.Symbols.Add ('0');
			automata.Symbols.Add ('1');

			var q0 = automata.AddState ("Q0");
			var q1 = automata.AddState ("Q1", true);

			automata.SetStart (q0);

			automata.AddTransition (q0, '0', q0);
			automata.AddTransition (q0, '1', q1);
			automata.AddTransition (q1, '0', q0);
			automata.AddTransition (q1, '1', q1);

			Assert.IsFalse (automata.Accept (word));
		}
	}
	
}
