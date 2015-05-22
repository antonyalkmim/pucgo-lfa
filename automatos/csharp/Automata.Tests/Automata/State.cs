namespace Automata
{
	/// <summary>
	/// Um estado de um autômato.
	/// </summary>
	public sealed class State 
	{
		/// <summary>
		/// Inicializa uma nova instância de <see cref="Automata.State"/>.
		/// </summary>
		/// <param name="name">Nome deste estado.</param>
		public State (string name, bool isFinal)
		{
			Name = name;
			IsFinal = isFinal;
		}

		/// <summary>
		/// Nome do estado.
		/// </summary>
		/// <value>O nome do estado.</value>
		public string Name { get; private set; }

		/// <summary>
		/// Determina se o estado é final ou não.
		/// </summary>
		/// <value><c>true</c> se o estado é final; caso contrário, <c>false</c>.</value>
		public bool IsFinal { get; private set; }


		public override bool Equals (object obj)
		{
			if (obj == null)
				return false;

			if (ReferenceEquals (this, obj))
				return true;

			if (obj.GetType () != typeof(State))
				return false;

			State other = (State)obj;
		
			return Name == other.Name;
		}
		

		public override int GetHashCode ()
		{
			unchecked {
				return (Name != null ? Name.GetHashCode () : 0);
			}
		}

		public override string ToString ()
		{
			return string.Format ("[State: Name={0}, IsFinal={1}]", Name, IsFinal);
		}
		
	}
}
