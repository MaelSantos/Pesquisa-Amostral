package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Entidade")
public class Entidade {

	private String nome;
	private String dado;
	private int indice;
	
	public Entidade(String nome, String dado, int indice) {
		this.nome = nome;
		this.dado = dado;
		this.indice = indice;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Entidade) {
			return ((Entidade) obj).getNome().equalsIgnoreCase(nome);
		}
		return super.equals(obj);
	}
	
	//metodos de acesso
	public String getNome() {
		return nome;
	}

	public int getIndice() {
		return indice;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getDado() {
		return dado;
	}
	
	public void setDado(String dado) {
		this.dado = dado;
	}

	@Override
	public String toString() {
		return "Entidade [nome = " + nome + ", dado = " + dado + ", indice = "+ indice + "]";
	}

	
}
