package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Calculo {

	private static Compare compare = new Compare(-1);
	
	public static String media()
	{
		double total = BancoDeDados.entidades.size();
		double quant = BancoDeDados.marcasNomes.length;
		
		double media = total/quant;
		
		return media+"";
	}
	
	public static String media(ArrayList<Double> list)
	{
		double total = list.size();
		double quant = 0;
		
		for(double d : list)
			quant += d;
		
		double media = quant/total;
		
		return media+"";
	}
	
	public static String moda()
	{
		String moda = "";
		int mo = 0;
		int quant = 0;
		
		int i = 0;
		for(String s: BancoDeDados.marcasNomes)
		{
			quant = 0;
			i = 0;
			while(i < BancoDeDados.entidades.size())
			{
				String nome = BancoDeDados.entidades.get(i).getMarca();
				if(s.equalsIgnoreCase(nome))
					quant++;
				i++;
			}
			if(quant == mo)
			{
				moda += ", "+s;
			}
			if(quant > mo)
			{
				moda = s;
				mo = quant;
			}
			
		}
		return moda;
	}
	
	public static String moda(ArrayList<Double> list)
	{
		String moda = "";
		int mo = 0;
		int quant = 0;
		
		int i = 0;
		for(double d : list)
		{
			quant = 0;
			i = 0;
			while(i < list.size())
			{
				double valor = list.get(i);
				if(d == valor)
					quant++;
				i++;
			}
			if(quant == mo)
			{
				if(! (moda.equalsIgnoreCase(d+"")) )
					moda += ","+d;
				else
					moda = d+"";
			}
			if(quant > mo)
			{
				moda = d+"";
				mo = quant;
			}
			
		}
		return moda;
	}
	
	public static String mediana()
	{
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(Entidade e : BancoDeDados.entidades)//percorro a populaçao
				nomes.add(e.getMarca());//add todos as marcas de minha populaçao
		
		organizarLista(nomes, -1);
		
		int mediana = 0;
		mediana = BancoDeDados.entidades.size()/2;

		if(BancoDeDados.entidades.size() %2 == 0)
			if(! (nomes.get(mediana-1).equalsIgnoreCase(nomes.get(mediana))) )
				return nomes.get(mediana-1) +" e "+ nomes.get(mediana);
			else
				return nomes.get(mediana-1);
		else
			return nomes.get(mediana);
	}
	
	public static String mediana(ArrayList<Double> list)
	{
		Collections.sort(list);
		
		int mediana = 0;
		mediana = BancoDeDados.entidades.size()/2;
		
		if(list.size() %2 == 0)
			if(! (list.get(mediana-1) == (list.get(mediana))) )
			{
				return ((list.get(mediana-1) + list.get(mediana))/2)+"";				
			}
			else
				return list.get(mediana-1)+"";
		else
			return list.get(mediana)+"";
	}
	
	public static void organizarLista(List<String> marcasN, int org)
	{
		compare.setOrg(org);
		Collections.sort(marcasN, compare);
	}
	
	public static ArrayList<String> quartil()
	{
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(Entidade e : BancoDeDados.entidades)//percorro a populaçao
				temp.add(e.getMarca());//add todos as marcas de minha populaçao
		
		organizarLista(temp, -1);
		
		int inicio = (int) Math.round( ( (double)(BancoDeDados.entidades.size()/4)) ); 
		int quartil = inicio;

		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(quartil-1) != null)
			{
				nomes.add(temp.get(quartil-1));			
				quartil += inicio;
			}
			if(quartil > temp.size())
				break;
		}
			return nomes;
	}

	public static ArrayList<String> quartil(ArrayList<Double> list)
	{
		ArrayList<String> temp = new ArrayList<String>();
		
		Collections.sort(list);
	
		int inicio = (int) Math.round( ( (double)(list.size()/4)) ); 
		int quartil = inicio;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(quartil-1) != null)
			{
				temp.add(list.get(quartil-1)+"");
				quartil += inicio;
			}
			if(quartil > list.size())
				break;
		}
			return temp;
	}
	
	public static String percentil(int percentil)
	{
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(Entidade e : BancoDeDados.entidades)//percorro a populaçao
				nomes.add(e.getMarca());//add todos as marcas de minha populaçao
		
		organizarLista(nomes, -1);
		double p1 = ((double)(BancoDeDados.entidades.size()/100)); 
		double p = ((double)(p1 * percentil));
//		System.out.println(percentil);
//		System.out.println(p1);
//		System.out.println(p);
//		System.out.println(nomes);
		
		return nomes.get(0);
	}

	public static String percentil(ArrayList<Double> list, int percentil)
	{
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(Entidade e : BancoDeDados.entidades)//percorro a populaçao
				nomes.add(e.getMarca());//add todos as marcas de minha populaçao
		
		organizarLista(nomes, -1);
		double p1 = ((double)(BancoDeDados.entidades.size()/100)); 
		double p = ((double)(p1 * percentil));
//		System.out.println(percentil);
//		System.out.println(p1);
//		System.out.println(p);
//		System.out.println(nomes);
		
		return nomes.get(0);
	}

	
	private static class Compare implements Comparator<String>
	{
		int org = 0;
		
		public Compare(int org) {
			this.org = org;
		}

		public int compare(String a, String b) {
			
			int quantA = 0;
			for(Entidade e : BancoDeDados.entidades)
				if(e.getMarca().equalsIgnoreCase(a))
					quantA += 1;

			int quantB = 0;
			for(Entidade e : BancoDeDados.entidades)
				if(e.getMarca().equalsIgnoreCase(b))
					quantB += 1;
			
			if (quantA > quantB) {
		          return -1*(org);
		     }
			else if (quantA < quantB) {
		          return 1*(org);
		     }
		     return 0;
		}

		public int getOrg() {
			return org;
		}

		public void setOrg(int org) {
			this.org = org;
		}
		
	}
	

	public static double maior(ArrayList<Double> list)
	{
		double maior = 0;
		
		for(double d : list)
		{
			if(d > maior)
				maior = d;
		}
		
		return maior;	
	}
	
	public static double menor(ArrayList<Double> list)
	{
		double menor = list.get(0);
		
		for(double d : list)
		{
			if(d < menor)
				menor = d;
		}
		
		return menor;
	}
	
	public static double total(ArrayList<Double> list)
	{
		double valor = 0;
		
		for(double d : list)
			valor += d;
		
		return valor;
	}
	
	public static int classes(ArrayList<Double> list)
	{
		double classes = 0;
		
		classes = ((double) (1 +3.33*Math.log10((double)list.size())));
		
		double diferenca = maior(list)-menor(list);
		
		classes = diferenca / classes;
		
		return (int) Math.round(classes);
		
	}
	
	public static ArrayList<Integer> minmax(ArrayList<Double> list)
	{
		int classe = classes(list);
		int min = 0;
		int max = classe;
		
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
		while(max < list.size())
		{
			numeros.add(min);
			numeros.add(max);
			
			min = max;
			max += classe;
		
		}
		
		return numeros;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("min max = "+minmax(BancoDeDados.valores));
	}
	
}
