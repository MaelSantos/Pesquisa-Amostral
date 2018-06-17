package model;

import java.util.ArrayList;

public class Pesquisa {

	public static final int QUALITATIVO = 0;
	public static final int QUANTITATIVO = 1;
	
	private String titulo;
	private String nome;
	private int tipo;
	private int indice;
	private ArrayList<String> tipos;
	private ArrayList<Entidade> entidades;
	
	public Pesquisa(String titulo, String nome, int tipo, int indice, ArrayList<String> tipos, ArrayList<Entidade> entidades) {
		this.titulo = titulo;
		this.nome = nome;
		this.tipo = tipo;
		this.indice = indice;
		this.tipos = tipos;
		this.entidades = entidades;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Pesquisa) {
			 Pesquisa pesquisa = (Pesquisa) obj;
			
			 if(pesquisa.getNome().equalsIgnoreCase(nome))
				 return true;
		}
		
		return super.equals(obj);
	}
	
	public ArrayList<Entidade> getEntidades() {
		return entidades;
	}
	public void setEntidades(ArrayList<Entidade> entidades) {
		this.entidades = entidades;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<String> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<String> tipos) {
		this.tipos = tipos;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
