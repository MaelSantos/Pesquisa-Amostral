package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DadosTabelaFrequencia {
	public static float mini = 0,max = 0;
	public static float pulo = 0;
	public static String[] classe;
	public static int[] frequencia = null,frequenciaAcumulada = null;
	public static float fr[] = null;
	public static float[] FR;
	public static float[] Xi;
	public static float[] XiFi;
	public static ArrayList<Float>inferior = new ArrayList<Float>();
	public static ArrayList<Float>superior = new ArrayList<Float>();
	public static int numeroClasses(String[]base){ 
		float k = (float)Math.sqrt(base.length);
		int k2 = 0;
		System.out.println("K:"+k);
		return (int)k;
	}
	public static float pulo(String[]base){
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return Float.parseFloat(df.format(MedidasDeDispersao.amplitude(base)/(float)numeroClasses(base)).replaceAll(",",".") );
	}
	public static void frequencia(String []base){
		//assumindo que a base esteja odernada;
		superior.clear();
		inferior.clear();
		float intervalo = pulo(base);
		System.out.println("Intervalooo:"+intervalo);
		int num = numeroClasses(base) +1;
		frequencia = new int[num];
		float novoIntervalo = 0;
		float intervaloAntigo = 0;
		float min = min(base);
		mini = min;
		max = max(base);
		DecimalFormat d = new DecimalFormat("0.000");
		DecimalFormat df = new DecimalFormat("0.000");
		d.setRoundingMode(RoundingMode.HALF_UP);
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		ArrayList<Integer>freq = new ArrayList<Integer>();
		for(int i = 0;i<num;i++){
			int cont = 0;
			if(i==0){
				novoIntervalo = Float.parseFloat(d.format((float) miniConvertido(min) + intervalo*(i+1)).replace(",","."));
				intervaloAntigo = miniConvertido(min);
			}else{
				novoIntervalo = Float.parseFloat(d.format((float) miniConvertido(min) + intervalo*(i+1)).replace(",","."));
				intervaloAntigo = Float.parseFloat(d.format((float)novoIntervalo - intervalo).replace(",","."));
			}
			for(int j = 0;j<base.length;j++){
				if(Float.parseFloat(base[j])>=intervaloAntigo && Float.parseFloat(base[j])<novoIntervalo){
					cont++;
				}
			}
			if(cont==0){
				superior.set(superior.size()-1,novoIntervalo);
			}else{
				freq.add( cont);
				inferior.add(intervaloAntigo);
				superior.add(novoIntervalo);
			}
		}
		frequencia =  new int[freq.size()];
		//verificar se a frequencia é 0
		for(int i=0;i<frequencia.length;i++){
			frequencia[i]=freq.get(i);
		}
		//mostrar
		classe = new String[frequencia.length];
		for(int i = 0;i<frequencia.length;i++){
//			 novoIntervalo = min + intervalo*(i+1);
//			 intervaloAntigo = novoIntervalo - intervalo ;
			 classe[i]= d.format(inferior.get(i))+" - "+d.format(superior.get(i));
			//System.out.println(intervaloAntigo+" - "+novoIntervalo +" "+frequencia[i]);
		}

	}
	public static void frequenciaAcumulada(){
		frequenciaAcumulada = new int[frequencia.length];
		System.out.println("Acumulada");

		for(int i = 0;i<frequencia.length;i++){
			if(i==0)
				frequenciaAcumulada[i] = frequencia[i];
			else
				frequenciaAcumulada[i] = frequencia[i] + frequenciaAcumulada[i-1];
		}
		for(int i = 0;i<frequenciaAcumulada.length;i++){
			System.out.println(frequenciaAcumulada[i]);
		}
	}
	public static void frequenciaRelativa(int tam){
		System.out.println("frequencia R");
		fr = new float[frequencia.length];
		for(int i = 0;i<frequencia.length;i++){
			fr[i] = (float)frequencia[i]/tam;
			System.out.println(fr[i]);
		}
	}
	public static void FrequenciaRelativa(){
		System.out.println("Frequencia R");
		FR = new float[frequencia.length];
		for(int i = 0;i<frequencia.length;i++){
			if(i==0)
				FR[i] = fr[i];
			else
				FR[i] = fr[i] + FR[i-1];
			
			System.out.println(FR[i]);
		}
	}
	public static void Xi(String base[]){
		pulo = pulo(base);
//		float intervalo = pulo(base);
//		float min  = min(base);
//		mini = min;
//		int num = numeroClasses(base) + 1;
//		float novoIntervalo = 0;
//		float intervaloAntigo = 0;
		Xi = new float[frequencia.length];
		System.out.println("Ponto Medio");
		for(int i = 0;i<frequencia.length;i++){
//			novoIntervalo =(float) min + intervalo*(i+1);
//			intervaloAntigo =(float)novoIntervalo - intervalo;
			Xi[i] = (superior.get(i) + inferior.get(i))/2;
			System.out.println(Xi[i]);
		}
	}
	public static void XiFi(){
		System.out.println("XI*FI");
		XiFi = new float[frequencia.length];
		for(int i = 0;i<frequencia.length;i++){
			XiFi[i] = (float)frequencia[i]*Xi[i];
			System.out.println(XiFi[i]);
		}
	}
	public static float min(String []base){
		float[] medida = MedidasDeDispersao.cast(base);
		MedidasDeDispersao.ordenar(medida);
		return medida[0];
	}
	public static float max(String[]base){
		float[] medida = MedidasDeDispersao.cast(base);
		MedidasDeDispersao.ordenar(medida);
		return  medida[medida.length  - 1];
	}
	private static float miniConvertido(float min){
		DecimalFormat daux = new DecimalFormat("0.000");
		daux.setRoundingMode(RoundingMode.DOWN);
		return  Float.parseFloat(daux.format((float) min).replace(",","."));
	}
	
}
