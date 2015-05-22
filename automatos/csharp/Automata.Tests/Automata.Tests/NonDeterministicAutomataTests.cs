using NUnit.Framework;

namespace Automata.Tests
{

	[TestFixture]
	public class NonDeterministicAutomataTests
	{
		[TestCase ("abc")]
		[TestCase ("def")]
		public void ShouldAcceptNFALanguage (string word)
		{
			var automata = AutomataFactory.CreateNFA ();

			automata.Symbols.AddRange (new[] { 'a', 'b', 'c' });

			var s0 = automata.AddState ("S0");
			var s1 = automata.AddState ("S1");
			var s2 = automata.AddState ("S2");
			var s3 = automata.AddState ("S3");
			var s4 = automata.AddState ("S4", isFinal: true);

			automata.SetStart (s0);

			automata.AddTransition (s0, automata.Epsilon, s1);
			automata.AddTransition (s1, 'a', s2);
			automata.AddTransition (s2, 'b', s3);
			automata.AddTransition (s3, 'c', s4);

			var s5 = automata.AddState ("S5");
			var s6 = automata.AddState ("S6");
			var s7 = automata.AddState ("S7");
			var s8 = automata.AddState ("S8", isFinal: true);

			automata.AddTransition (s0, automata.Epsilon, s5);
			automata.AddTransition (s5, 'd', s6);
			automata.AddTransition (s6, 'e', s7);
			automata.AddTransition (s7, 'f', s8);

			Assert.IsTrue (automata.Accept (word));
		}

		[TestCase ("a")]
		[TestCase ("ab")]
		[TestCase ("abcd")]
		[TestCase ("d")]
		[TestCase ("de")]
		[TestCase ("defa")]
		[TestCase ("abcd")]
		public void ShouldNotAcceptNFALanguage (string word)
		{
			var automata = AutomataFactory.CreateNFA ();

			automata.Symbols.AddRange (new[] { 
				'a', 'b', 'c', 'd', 'e', 'f' 
			});

			var s0 = automata.AddState ("S0");
			var s1 = automata.AddState ("S1");
			var s2 = automata.AddState ("S2");
			var s3 = automata.AddState ("S3");
			var s4 = automata.AddState ("S4");

			automata.SetStart (s0);

			automata.AddTransition (s0, automata.Epsilon, s1);
			automata.AddTransition (s1, 'a', s2);
			automata.AddTransition (s2, 'b', s3);
			automata.AddTransition (s3, 'c', s4);

			var s5 = automata.AddState ("S5");
			var s6 = automata.AddState ("S6");
			var s7 = automata.AddState ("S7");
			var s8 = automata.AddState ("S8");

			automata.AddTransition (s0, automata.Epsilon, s5);
			automata.AddTransition (s5, 'd', s6);
			automata.AddTransition (s6, 'e', s7);
			automata.AddTransition (s7, 'f', s8);

			Assert.IsFalse (automata.Accept (word));
		}

		[TestCase ("c")]
		[TestCase ("ac")]
		[TestCase ("b")]
		[TestCase ("cc")]
		[TestCase ("abc")]
		public void ShouldAcceptNFALanguage (string word)
		{
			var automata = AutomataFactory.CreateNFA ();

			automata.Symbols.AddRange (new[] {
				'a', 'b', 'c'
			});

			var s0 = automata.AddState ("S0");
			var s1 = automata.AddState ("S1", isFinal: true);
			var s2 = automata.AddState ("S2", isFinal: true);

			automata.SetStart (s0);

			automata.AddTransition (s0, 'a', s0);
			automata.AddTransition (s0, 'c', s1);
			automata.AddTransition (s0, automata.Epsilon, s2);
			automata.AddTransition (s1, 'c', s1);
			automata.AddTransition (s2, 'b', s1, s2);

			Assert.IsTrue (automata.Accept (word));
		}
	}
}
