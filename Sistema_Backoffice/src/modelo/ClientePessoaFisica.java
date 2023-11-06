package modelo;

public class ClientePessoaFisica {

	public int CPF;
	public String Nome;
	public String Logradouro;
	public int Numero;
	public String Complemento;
	public int CEP;
	public int Celular;

	public ClientePessoaFisica() {
		super();
	}

	public ClientePessoaFisica(int cpf, String nome, String logradouro, int numero, String complemento, int cep, int celular) {
		this.CPF = cpf;
		this.Nome = nome;
		this.Logradouro = logradouro;
		this.Numero = numero;
		this.Complemento = complemento;
		this.CEP = cep;
		this.Celular = celular;
	}
	
	public String toString() {
		return "CPF: "+CPF+"\nNome: "+Nome+"\nLogradouro"+Logradouro+"\nNúmero: "+Numero+"\nComplemento: "+Complemento+"\nCEP: "+CEP +"\nCelular: "+Celular;
	}
}
