package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

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
	
	public static ArrayList<Entidade> AmostraEstratificada(int porcentagem, ArrayList<Entidade> entidades) {
		
		System.out.println("Estratificada");
		
		ArrayList<Entidade> amostra = new ArrayList<Entidade>();
		ArrayList<Entidade> temp = new ArrayList<Entidade>();
		ArrayList<Integer> num = new ArrayList<Integer>();

		double contador = 0;
		double total = 0;
		double quantidade = 0;
		
		ArrayList<String> nomes = new ArrayList<String>();
		for(Entidade e : entidades)
		{
			if(!nomes.contains(e.getDado()))
			{
				nomes.add(e.getDado());
			}
			total++;
		}
		
		for(String s : nomes) 
		{
			for(Entidade e : entidades)
				if(e.getDado().equalsIgnoreCase(s))
				{
					temp.add(e);
					contador++;
				}
			
			for(int j = 0; j < temp.size(); j++)
				num.add(j);
			
			Collections.shuffle(num);
		
			System.out.println("Total: "+total);
			System.out.println("Contador: "+contador);
			quantidade = Math.round(contador * (porcentagem / total));
			System.out.println("Quantidade: "+quantidade);
			
			for(int i = 0; i < quantidade; i++)
				amostra.add(temp.get(num.get(i)));

			temp.clear();
			num.clear();
			contador = 0;
			quantidade = 0;
		}
		
		return amostra;
	}
	
	
	public static void main(String[] args) {
	
		System.out.println("População: "+Dados.getInstance().getPesquisas().get(0).getEntidades());
		System.out.println(AmostraEstratificada(10, Dados.getInstance().getPesquisas().get(0).getEntidades()));
		
	}
}
