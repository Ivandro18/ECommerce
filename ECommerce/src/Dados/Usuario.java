package Dados;
import java.io.Serializable;

public class Usuario implements Serializable{

	protected String nome;
	protected String senha;
	protected String telefone;
	//protected Endereco enderecos;
	
	public Usuario( String nome, String senha, String telefone) {
		
		this.nome = nome;
		this.senha = senha;
		this.telefone = telefone;
		//this.enderecos = new Endereco();
	}
	
	public String toString() {
		
		return this.nome;
	}

	public Object getSenha() {
		
		return this.senha;
	}

	public String getTelefone() {
		
		return telefone;
	}

	public void setTelefone(String telefone) {
		
		this.telefone = telefone;
	}

}
