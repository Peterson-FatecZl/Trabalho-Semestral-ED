package modelo;

public class ClientePessoaJuridica {

	public int CNPJ;
	public String NomeFantasia;
	public String Logradouro;
	public int Numero;
	public String Complemento;
	public int CEP;
	public int Telefone;
	public String Email;

	public ClientePessoaJuridica() {
		super();
	}

	public ClientePessoaJuridica(int cnpj, String nomeFantasia, String logradouro, int numero, String complemento, int cep, int telefone, String email) {
		this.CNPJ = cnpj;
		this.NomeFantasia = nomeFantasia;
		this.Logradouro = logradouro;
		this.Numero = numero;
		this.Complemento = complemento;
		this.CEP = cep;
		this.Telefone = telefone;
		this.Email = email;
	}
	
	public String toString() {
		return "CNPJ: "+CNPJ+"\nNome: "+NomeFantasia+"\nLogradouro"+Logradouro+"\nNúmero: "+Numero+"\nComplemento: "+Complemento+"\nCEP: "+CEP +"\nTelefone: "+Telefone+"\nEmail: "+Email;
	}
}
