package model;

import java.util.ArrayList;
import java.util.Collections;

public class Amostra {

	public static ArrayList<Entidade> AmostraAleatoria(int porcentagem, ArrayList<Entidade> entidades) {
		
		System.out.println("Aleatoria");
		
		ArrayList<Entidade> amostra = new ArrayList<Entidade>();
		
		int quantidade = (int) Math.round( (double) (entidades.size()*porcentagem)/100 );
		
		if(quantidade <= 0)
			quantidade = 1;
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < entidades.size(); i++) 
		{
			numeros.add(i);
		}
		
		//embaralha a lista
		Collections.shuffle(numeros);
		
		for (int i = 0; i < quantidade; i++) 
		{
			amostra.add(entidades.get(numeros.get(i)));	
		}
		
		return amostra;
	}
	
	public static ArrayList<Entidade> AmostraSistematica(int porcentagem, ArrayList<Entidade> entidades) {
		
		System.out.println("Sistematica");
		
		ArrayList<Entidade> amostra = new ArrayList<Entidade>();
		
		int quantidade = (int) Math.round( (double) (entidades.size()*porcentagem)/100 );
		
		if(quantidade <= 0)
			quantidade = 1;
		
		int m = entidades.size()/quantidade;
		System.out.println("M = "+m);
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < entidades.size(); i++) 
		{
			numeros.add(i);
		}
		
		int indice = 0;
		for (int i = 0; i < quantidade; i++) 
		{
			if(i == 0)
			{
				indice = (int) (Math.random()*(entidades.size()));
				System.out.println("Inicio = "+indice);
				amostra.add(entidades.get(indice));
			}
			else if( (indice+m) < entidades.size())
			{
				amostra.add(entidades.get(indice+m));
				indice = (indice+m);
			}
			else if(indice+m >= entidades.size())
			{
				int anterior = indice;
				amostra.add(entidades.get(anterior-entidades.size()+m));
				indice = (anterior-entidades.size())+m;
			}
		}
		
		return amostra;
	}
	
	public static ArrayList<Entidade> AmostraEstratificada(int porcentagem,ArrayList<Entidade> entidades) {
		
		System.out.println("Estratificada");
		
		ArrayList<Entidade> amostra = new ArrayList<Entidade>();
		
		int quantidade = (int) Math.round( (double) (entidades.size()*porcentagem)/100 );
		
		if(quantidade <= 0)
			quantidade = 1;
		
		int m = entidades.size()/quantidade;
		System.out.println("M = "+m);
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for (int i = 0; i < entidades.size(); i++) 
		{
			numeros.add(i);
		}
		
		int indice = 0;
		for (int i = 0; i < quantidade; i++) 
		{
			if(i == 0)
			{
				indice = (int) (Math.random()*(entidades.size()));
				System.out.println("Inicio = "+indice);
				amostra.add(entidades.get(indice));
			}
			else if( (indice+m) < entidades.size())
			{
				amostra.add(entidades.get(indice+m));
				indice = (indice+m);
			}
			else if(indice+m >= entidades.size())
			{
				int anterior = indice;
				amostra.add(entidades.get(anterior-entidades.size()+m));
				indice = (anterior-entidades.size())+m;
			}
		}
		
		return amostra;
	}
}
