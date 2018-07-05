package model;
import java.util.ArrayList;
import java.util.Collections;
//Conferir dados.calculo percentil
public class MedidasDeDispersao {
	public static float mediaNormal(String[] base){
		float[] medidas = cast(base);
		float media = 0;
		for(int i = 0;i<medidas.length;i++){
			media+=medidas[i];
		}
		System.out.println("Soooooooooooooooooma "+ media);
		System.out.println(medidas.length);
		return (float)media/medidas.length;
	}
	public static float modaNormal(String[] base){
		float[] medidas = cast(base);
		ArrayList<Integer>repete = new ArrayList<Integer>();
		for(int i = 0;i<medidas.length;i++){
			int cont = 0;
			//media+=medidas[i];
			for(int j = 0;j < medidas.length;j++){
				if(medidas[i]==medidas[j]){
					cont++;
				}
			}
			repete.add(cont);
		}
		return medidas[repete.indexOf(Collections.max(repete))];
	}
	public static float medianaNormal(String[] base){
		float[] medidas = cast(base);
		ordenar(medidas);
		if(medidas.length % 2 ==0){
			return (float)(medidas[medidas.length/2 - 1] + medidas[medidas.length/2])/ 2;
		}
		return (medidas[(int)medidas.length/2 + 1]) ;
	}
	public static float quartil(String[] base,int numeroQuatil){
		float[] medidas = cast(base);
		int posQuartil1 = medidas.length/4;
		if(numeroQuatil == 2)
			return medidas[posQuartil1*2 - 1];
		else if(numeroQuatil == 3)				//[0,1,2,3,4,5,6]no vetor
			return medidas[posQuartil1*3 - 1];//[1,2,3,4,5,6,7]como eu vejo
		return medidas[posQuartil1 - 1];
	}
	public static float percentil(String[] base,int posPercentil){
		float[] medidas = cast(base);
		int percentil = medidas.length/100 * posPercentil;//
		if(percentil> 1)
			return medidas[percentil - 1];
		return medidas[percentil];
	}
	public static float amplitude(String[] base){
		float[] medida = cast(base);
		ordenar(medida);
		float maior = medida[medida.length  - 1];
		float menor = medida[0];
		System.out.println("Amplitudeeeee "+(maior - menor));
		return maior - menor;
	}
	public static float varianciaPopulacional(String[] base){
		float[] medidas = cast(base);
		float soma = 0;
		for(int i = 0;i<medidas.length;i++){
			soma+= (medidas[i]*medidas[i]);
			System.out.println(soma);
		}
		//System.out.println("!"+(float)1/medidas.length * soma);

		//ok
		return (float)1/medidas.length * soma - mediaNormal(base)*mediaNormal(base);

	}
	//variancia amostral
	public static float varianciaAmostral(String[] base){
		float[] medidas = cast(base);
		float somaQ = 0,somaN = 0;
		for(int i = 0;i<medidas.length;i++){
			somaQ+= (medidas[i]*medidas[i]);
			somaN +=medidas[i];
			//System.out.println(somaQ);
			//System.out.println(somaN);

		}
		//System.out.println("!"+(float)1/(medidas.length - 1) * somaQ);

		//ok
		return (float)1/(medidas.length - 1) * (somaQ - ((float)(somaN)*(somaN))/medidas.length);

	}
	public static float desvioPadraoPopulacional(String[] base){
		return (float)Math.sqrt(varianciaPopulacional(base));
	}
	public static float desvioPadraoAmostral(String[] base){
		return (float) Math.sqrt(varianciaAmostral(base));
	}
	public static float coeficienteDeVariacaoPopulacional(String[] base){
		return desvioPadraoPopulacional(base)/mediaNormal(base);
	}
	public static float coeficienteDeVariacaoAmostral(String[] base){
		return desvioPadraoAmostral(base)/mediaNormal(base);
	}
	public static float mediaNaTabela(int total){
		float soma = 0;
		for(int i =0;i<DadosTabelaFrequencia.XiFi.length;i++){
			soma+=(float)DadosTabelaFrequencia.XiFi[i];
		}
		return (float)soma/total;
	}
	public static float modaNaTabela(){
		int ind = 0;
		float maior = DadosTabelaFrequencia.frequencia[0];
		for(int i = 0;i<DadosTabelaFrequencia.frequencia.length;i++){
			if(DadosTabelaFrequencia.frequencia[i] > maior){
				maior = DadosTabelaFrequencia.frequencia[i];
				ind = i;
			}
		}
		return DadosTabelaFrequencia.Xi[ind];
	}
	public static float medianaNaTabela(int tam){
		float hi = 0;
		float pmr = 0;
		int fant = 0;
		float m1,m2;
		float li = 0,Li = 0;
		if(tam % 2 ==0){
			pmr = tam/2;
		
			fant = Fant(pmr);
		
			li = DadosTabelaFrequencia.inferior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
					//(acharPosicaoClasse(pmr)+ 1) - DadosTabelaFrequencia.pulo;
			Li = DadosTabelaFrequencia.superior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
					//(acharPosicaoClasse(pmr)+ 1);
			hi = Li - li;
			
			m1 =((float) li +(hi*(pmr - fant)/DadosTabelaFrequencia.frequencia[acharPosicaoClasse(pmr)]));
			System.out.println("PMR "+pmr);
			System.out.println("Fant "+fant);
			System.out.println("Hi "+hi);
			System.out.println("M1 "+m1);
			System.out.println("Pos classe"+acharPosicaoClasse(pmr));
			//Part 2
			pmr =(float) tam/2 + 1;
			fant = Fant(pmr);
			li = DadosTabelaFrequencia.inferior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
			//(acharPosicaoClasse(pmr)+ 1) - DadosTabelaFrequencia.pulo;
			Li = DadosTabelaFrequencia.superior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
			//(acharPosicaoClasse(pmr)+ 1);
			hi = Li - li;
			m2 =((float) li +(hi*(pmr - fant)/DadosTabelaFrequencia.frequencia[acharPosicaoClasse(pmr)]));
			System.out.println("PMR "+pmr);
			System.out.println("Fant "+fant);
			System.out.println("Hi "+hi);
			System.out.println("M "+m2);
			System.out.println("Pos classe"+acharPosicaoClasse(pmr));
			return (float)(m1+m2)/2;
		}else{
			pmr = (float)tam/2;
			fant = Fant(pmr);
			li = DadosTabelaFrequencia.inferior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
			//(acharPosicaoClasse(pmr)+ 1) - DadosTabelaFrequencia.pulo;
			Li = DadosTabelaFrequencia.superior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
			//(acharPosicaoClasse(pmr)+ 1);
			hi = Li - li;
			return ((float) li +(hi*(pmr - fant)/DadosTabelaFrequencia.frequencia[acharPosicaoClasse(pmr)]));
		}
	}
	public static float quartilNaTabela(int tam,int posQ){
		
		float posQ1 =(float) tam/4;
		float hi = 0;
		float pmr = 0;
		int fant = 0;
		float li = 0,Li = 0;
		pmr = (float)posQ1*posQ;
		System.out.println("Pos Quatil 1 "+ posQ1);
		System.out.println("PMR "+pmr);
		fant = Fant(pmr);
		System.out.println("Fant"+fant);
		li = DadosTabelaFrequencia.inferior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
		//(acharPosicaoClasse(pmr)+ 1) - DadosTabelaFrequencia.pulo;
		Li = DadosTabelaFrequencia.superior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
		//(acharPosicaoClasse(pmr)+ 1);
		hi = Li - li;
		System.out.println("Hi "+ hi);
		return ((float) li +(hi*(pmr - fant)/DadosTabelaFrequencia.frequencia[acharPosicaoClasse(pmr)]));

	}
	public static float percentilTabela(float posP,int tam){
		//o que acontece se o Fant for a primeira classe
		float per1 =(float) tam/100;
		float hi = 0;
		float pmr = 0;
		int fant = 0;
		float li = 0,Li = 0;
		pmr = (float)per1*posP;
		fant = Fant(pmr);
		li = DadosTabelaFrequencia.inferior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
		//(acharPosicaoClasse(pmr)+ 1) - DadosTabelaFrequencia.pulo;
		Li = DadosTabelaFrequencia.superior.get(acharPosicaoClasse(pmr));//DadosTabelaFrequencia.mini  + DadosTabelaFrequencia.pulo*
		//(acharPosicaoClasse(pmr)+ 1);
		hi = Li - li;
		return ((float) li +(hi*(pmr - fant)/DadosTabelaFrequencia.frequencia[acharPosicaoClasse(pmr)]));

	}
	public static double varianciaNaTabela(int tam){
		float somaQ = 0,soma = 0;
		for(int i = 0;i<DadosTabelaFrequencia.Xi.length;i++){
			somaQ += (double)DadosTabelaFrequencia.Xi[i]*DadosTabelaFrequencia.Xi[i] *DadosTabelaFrequencia.frequencia[i];
			soma+=(double)DadosTabelaFrequencia.Xi[i]*DadosTabelaFrequencia.frequencia[i];

		}
		System.out.println("Soma quadrada "+somaQ);
		System.out.println("Soma Normal "+soma);
		System.out.println("RE "+((float)1/(tam -1))*(somaQ - ((double)soma*soma/tam)));
		return ((double)1/(tam -1))*(somaQ - ((double)soma*soma/tam));
	}
	public static float desvioNaTabela(int tam){
		return (float)Math.sqrt(varianciaNaTabela(tam));
	}
	public static float coeficienteDeVariacaoNaTabela(int tam){
		return (float)desvioNaTabela(tam)/mediaNaTabela(tam);
	}
	/**
	 * Retorna a posição da classe em termos de índicie começando do 0 
	 *Obs:numeração de vetor[0,1,2...]
	 * */
	public static int acharPosicaoClasse(float valor){
		//achando a FA da pra achar a pos da classe
		for(int i = 0;i<DadosTabelaFrequencia.frequenciaAcumulada.length;i++){
			if(valor <=(float)DadosTabelaFrequencia.frequenciaAcumulada[i]){
					return i;
			}
		}
		return 0;

	}
	private static int Fant(float valor){//frequencia acumulada da classe anterior
		for(int i = 0;i<DadosTabelaFrequencia.frequenciaAcumulada.length;i++){
			if(valor <=(float)DadosTabelaFrequencia.frequenciaAcumulada[i] ){
				if(i-1>=0)//garante que nao estou na primeira posição
					return (int) DadosTabelaFrequencia.frequenciaAcumulada[i-1];
				else
					return (int) 0;
			}
		}
		return 0;
	}
	public  static float [] cast(String[] base){
		float b[] = new float[base.length];
		for(int i = 0;i<base.length;i++){
			b[i] = Float.parseFloat(base[i]);
		}
		return b;
	}
	static void ordenar(float []base){
		for(int i=0;i<base.length;i++){
			for(int j=i+1;j<base.length;j++){
				if(base[i] > base[j]){
					float  aux = base[j];
					base[j] = base[i];
					base[i] = aux;
				}
			}
		}
	}
}
