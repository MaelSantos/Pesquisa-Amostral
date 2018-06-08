
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import view.Mensagem;
import view.TableModel;

public class BancoDeDados{

	public static ArrayList<Entidade> entidades = Carregar();
	public static ArrayList<Double> valores = CarregarValores();
	
	public static String[] marcasNomes = {
			"LG", "Samsung", "Asus", "Motorola", 
			"Sony","BLU","Lenovo", "IPhone", "Outro" };
	
	public static String titulo = "Marcas De Smartphone Mais Utilizadas, S.I 2018.1";
	
	public static boolean adicionar(Entidade entidade) {

		if(entidades.isEmpty())
		{
			entidades.add(entidade);
			Salvar();
			System.out.println("add");
			return true;
		}
		else
		{
			boolean add = true;
			for(Entidade ent : entidades)
			{
				if (ent.equals(entidade)) 
				{
					add = false;
				}
			}
			if(add)
			{
				entidades.add(entidade);
				Salvar();
				System.out.println("add");
				return true;
			}
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void Salvar() {
		
		XStream xStream = new XStream(new Dom4JDriver());
		xStream.alias("Entidade", Entidade.class);
		
		ArrayList<Entidade> temp = new ArrayList<Entidade>();
		
		File file = new File("res/Entidades.xml");
		OutputStream stream = null;
		try {
			
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
			else
			{
				temp = (ArrayList<Entidade>) xStream.fromXML(file);
				
				file.delete();
				file.createNewFile();
			}
			stream = new FileOutputStream(file);
			
			entidades = concatenarListas(entidades, temp);
			
			xStream.toXML(entidades, stream);
			
		} catch (Exception e) {
			
			Mensagem.exibirMensagem("Falha Ao Salvar Xml"+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Entidade> Carregar()
	{
		XStream xStream = new XStream(new Dom4JDriver());
		ArrayList<Entidade> temp = new ArrayList<Entidade>();
		File file = new File("res/Entidades.xml");

		try {
			
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
	
			xStream.alias("Entidade", Entidade.class);
			temp = (ArrayList<Entidade>) xStream.fromXML(file);
			
		} catch (Exception e) {
			Mensagem.exibirMensagem("Falha Ao Carregar Xml"+e.getMessage());
		}

		if(temp == null)
			return new ArrayList<Entidade>();
		else
			return temp;
		
	}
	
	public static boolean adicionar(double valor) {

			valores.add(valor);
			SalvarValores();
			System.out.println("add");
			return true;
	}

	@SuppressWarnings("unchecked")
	private static void SalvarValores() {
		
		XStream xStream = new XStream(new Dom4JDriver());
		xStream.alias("Valor", double.class);
		
		ArrayList<Double> temp = new ArrayList<Double>();
		
		File file = new File("res/Valores.xml");
		OutputStream stream = null;
		try {
			
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
			else
			{
//				temp = (ArrayList<Double>) xStream.fromXML(file);
				
				file.delete();
				file.createNewFile();
			}
			stream = new FileOutputStream(file);
			
//			valores.addAll(temp);
			
			xStream.toXML(valores, stream);
			
		} catch (Exception e) {
			
			Mensagem.exibirMensagem("Falha Ao Salvar Xml"+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Double> CarregarValores()
	{
		XStream xStream = new XStream(new Dom4JDriver());
		ArrayList<Double> temp = new ArrayList<Double>();
		File file = new File("res/Valores.xml");

		try {
			
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
	
			xStream.alias("Valor", double.class);
			temp = (ArrayList<Double>) xStream.fromXML(file);
			
		} catch (Exception e) {
			Mensagem.exibirMensagem("Falha Ao Carregar Xml"+e.getMessage());
		}

		if(temp == null)
			return new ArrayList<Double>();
		else
			return temp;
		
	}
	
	public static ArrayList<Entidade> concatenarListas(ArrayList<Entidade> primeiraLista, ArrayList<Entidade> segundaLista) {
		
	    ArrayList<Entidade> ret = new ArrayList<Entidade>();
	    for (Entidade elementoLista : primeiraLista) {
	        if (! (ret.contains(elementoLista))) {
	            ret.add(elementoLista);
	        }
	    }
	    for (Entidade elementoLista : segundaLista) {
	        if (! (ret.contains(elementoLista))) {
	            ret.add(elementoLista);
	        }
	    }
	    return ret;
	}
	
}