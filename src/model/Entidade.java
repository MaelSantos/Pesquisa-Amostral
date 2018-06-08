package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Entidade")
public class Entidade {

	private String nome;
	private String marca;
	private int indice;
	
	public Entidade(String nome, String marca, int indice) {
		this.nome = nome;
		this.marca = marca;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Entidade [nome=" + nome + ", marca=" + marca + ", indice=" + indice + "]";
	}
	
}
