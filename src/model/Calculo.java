package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Calculo {

	private static Compare compare = new Compare(-1);
	
	public static String media()
	{
		double total = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		double quant = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().size();
		
		double media = total/quant;
		
		return media+"";
	}
	
	public static String media(ArrayList<Entidade> list)
	{
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		double total = num.size();
		double quant = 0;
		
		for(double d : num)
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
		for(String s: Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos())
		{
			quant = 0;
			i = 0;
			while(i < Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size())
			{
				String nome = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().get(i).getDado();
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
	
	public static String moda(ArrayList<Entidade> list)
	{
		String moda = "";
		int mo = 0;
		int quant = 0;
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		int i = 0;
		for(double d : num)
		{
			quant = 0;
			i = 0;
			while(i < list.size())
			{
				double valor = num.get(i);
				if(d == valor)
					quant++;
				i++;
			}
			if(quant == mo)
			{
//				if(! (moda.equalsIgnoreCase(d+"")) )
//					moda += d+" ";
//				else
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
		
		for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())//percorro a populašao
				nomes.add(e.getDado());//add todos as marcas de minha populašao
		
		organizarLista(nomes, -1);
		
		int mediana = 0;
		mediana = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size()/2;

		if(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size() %2 == 0)
			if(! (nomes.get(mediana-1).equalsIgnoreCase(nomes.get(mediana))) )
				return nomes.get(mediana-1) +" e "+ nomes.get(mediana);
			else
				return nomes.get(mediana-1);
		else
			return nomes.get(mediana);
	}
	
	public static String mediana(ArrayList<Entidade> list)
	{
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		Collections.sort(num);
		
		int mediana = 0;
		mediana = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size()/2;
		
		if(list.size() %2 == 0)
			if(! (num.get(mediana-1) == (num.get(mediana))) )
			{
				return ((num.get(mediana-1) + num.get(mediana))/2)+"";				
			}
			else
				return num.get(mediana-1)+"";
		else
			return num.get(mediana)+"";
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
		
		for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())//percorro a populašao
				temp.add(e.getDado());//add todos as marcas de minha populašao
		
		organizarLista(temp, -1);
		
		int inicio = (int) Math.round( ( (double)(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size()/4)) ); 
		int quartil = inicio;

		System.out.println(quartil);
		for(int i = 0; i < temp.size(); i++)
		{
			if(quartil-1 >= 0)
			{
				if(temp.get(quartil-1) != null)
				{
					nomes.add(temp.get(quartil-1));			
					quartil += inicio;
				}
				if(quartil > temp.size())
					break;				
			}
		}
		return nomes;
	}

	public static ArrayList<String> quartil(ArrayList<Entidade> list)
	{
		ArrayList<String> temp = new ArrayList<String>();
	
	
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		Collections.sort(num);
		
		int inicio = (int) Math.round( ( (double)(num.size()/4)) );
		int quartil = inicio;
		
		for(int i = 0; i < 4; i++)
		{
			if(quartil-1 >= 0)
				if(num.get(quartil-1) != null)
				{
					temp.add(num.get(quartil-1)+"");	
					quartil += inicio;
				}
			if(quartil > num.size())
				break;
		}
		return temp;
	}
	
	public static String percentil(int percentil)
	{
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(Entidade e :Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())//percorro a populašao
				nomes.add(e.getDado());//add todos as marcas de minha populašao
		
		organizarLista(nomes, -1);
		double p1 = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		p1 = p1/100;
		double p = ((double)(p1 * percentil));
		
		if(p-1 < 0)
			return nomes.get(0);
		else
			return nomes.get((int) p-1);
	}

	public static String percentil(ArrayList<Entidade> list, int percentil)
	{
		ArrayList<String> nomes = new ArrayList<String>();
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())//percorro a populašao
				nomes.add(e.getDado());//add todos as marcas de minha populašao
		
		System.out.println(nomes);
		Collections.sort(nomes);
		
		double p1 = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		p1 = p1/100;
		double p = ((double)(p1 * percentil));
		
		if(p-1 < 0)
			return nomes.get(0);
		else
			return nomes.get((int) p-1);
	}

	public static String variancia()
	{
		String variancia = "";
		
		ArrayList<String> nomes = new ArrayList<String>();
		for(Entidade e :Dados.getInstance().getPesquisas().get(0).getEntidades())
			nomes.add(e.getDado());
		
		organizarLista(nomes, -1);
		
		double m = nomes.size();
		
		
		double somatorio = 0.0;
		for(int i = 0; i < Dados.getInstance().getPesquisas().get(0).getTipos().size(); i++)
		{
			int quant = Collections.frequency(nomes, 
            		Dados.getInstance().getPesquisas().get(0).getTipos().get(i));
			
			somatorio += quant;
		}
		
		somatorio = Math.pow(somatorio, 2);
		
		return variancia;
	}
	
		private static class Compare implements Comparator<String>
	{
		int org = 0;
		
		public Compare(int org) {
			this.org = org;
		}

		public int compare(String a, String b) {
			
			int quantA = 0;
			for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())
				if(e.getDado().equalsIgnoreCase(a))
					quantA += 1;

			int quantB = 0;
			for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())
				if(e.getDado().equalsIgnoreCase(b))
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
	

	public static double maior(ArrayList<Entidade> list)
	{
		double maior = 0;
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		for(double d : num)
		{
			if(d > maior)
				maior = d;			
		}
		
		return maior;	
	}
	
	public static double menor(ArrayList<Entidade> list)
	{
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		double menor = num.get(0);
		for(double d : num)
		{
			if(d < menor)
				menor = d;
		}
		
		return menor;
	}
	
	public static double total(ArrayList<Entidade> list)
	{
		double valor = 0;
		
		ArrayList<Double> num = new ArrayList<Double>();
		try {
			
			for(Entidade e : list)
			{				
				num.add(Double.parseDouble(e.getDado()));	
			}
			
			for(double d : num)
				valor += d;			
			
			return valor;
			
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public static int classes(ArrayList<Entidade> list)
	{
		double classes = 0;
		
		ArrayList<Double> num = new ArrayList<Double>();
		for(Entidade e : list)
		{
			num.add(Double.parseDouble(e.getDado()));
		}
		
		
		classes = ((double) (1 +3.33*Math.log10((double)total(list))));
//		System.out.println(classes);
		
		double diferenca = maior(list)-menor(list);
//		System.out.println(maior(list));
//		System.out.println(menor(list));
//		System.out.println(diferenca);
		
		classes = diferenca / classes;
//		System.out.println(classes);
		
		
		return (int) Math.round(classes);
		
	}
	
	public static ArrayList<Integer> minmax(ArrayList<Entidade> list)
	{
		int classe = classes(list);
		int min = 0;
		int max = classe;
		
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		try {
			while(max < list.size() || list.size() == 0)
			{
				numeros.add(min);
				numeros.add(max);
				
				min = max;
				max += classe;
				
			}
			return numeros;		
			
		} catch (Exception e) {
			return new ArrayList<Integer>();
		}
	}

	public static void main(String[] args) {
		System.out.println(variancia());
	}
	
}
