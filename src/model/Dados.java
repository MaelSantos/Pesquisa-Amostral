
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import view.Mensagem;

public class Dados{

	public static int pesquisaAtual;
	
	private XStream xStream;
	private ArrayList<Pesquisa> pesquisas;
	private ArrayList<String> nomesPesquisas;
	
	private static Dados instance;
	
	@SuppressWarnings("unchecked")
	private Dados() {
		
		xStream = new XStream(new Dom4JDriver());
		xStream.autodetectAnnotations(true);
		xStream.alias("Pesquisa", Pesquisa.class);
		xStream.alias("Entidade", Entidade.class);
		xStream.alias("tipo", String.class);
//		xStream.aliasField("Entidade", Entidade.class, "entidade");
		
		pesquisas = (ArrayList<Pesquisa>) Carregar("res/pesquisas.xml");
		pesquisaAtual = 0;
		
		nomesPesquisas = new ArrayList<String>();
		for(int i = 0; i < pesquisas.size(); i++)
		{
			nomesPesquisas.add(pesquisas.get(i).getNome());
		}
		
	}
	public static Dados getInstance() {
		if(instance == null)
			instance = new Dados();
		return instance;
	}
	
	public void atualizarBanco(String titulo, ArrayList<String> escolhas, String nomePesquisa, int tipo)
	{	
		pesquisas.add(new Pesquisa(titulo, nomePesquisa, tipo, pesquisas.size(), escolhas, new ArrayList<Entidade>()));
		Salvar(pesquisas, "res/pesquisas.xml");
	
		pesquisaAtual = pesquisas.size()-1;
		nomesPesquisas.add(nomePesquisa);
	
	}
	
	public void entrarPesquisa(String nome)
	{
		for(Pesquisa p : pesquisas)
		{
			if(p.getNome().equalsIgnoreCase(nome))
			{
				pesquisaAtual = p.getIndice();
				System.out.println(pesquisaAtual);
				break;
			}
		}
		
	}
	
	public boolean addDado(Object object, String endereco) {

		if (object instanceof Pesquisa) {
			
			Pesquisa pesquisa = (Pesquisa) object;
			
			if(pesquisas.isEmpty())
			{
				pesquisas.add(pesquisa);
				Salvar(pesquisas, endereco);
				return true;
			}
			else
			{
				for(Pesquisa pes : pesquisas)
				{
					if (pes.equals(pesquisa)) 
					{
						return false;
					}
				}
				
				pesquisas.add(pesquisa);
				Salvar(pesquisas, endereco);
				return true;
			}
		}
		
		if (object instanceof Entidade) {
			Entidade entidade = (Entidade) object;
			
			pesquisas.get(pesquisaAtual).getEntidades().add(entidade);
			Salvar(pesquisas, endereco);
			return true;
		}
	
		return false;
		
	}

	public void Salvar(ArrayList<? extends Object> list, String endereco) {

		File file = new File(endereco);
		OutputStream stream = null;
		try {
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
			else
			{	
				file.delete();
				file.createNewFile();
			}
			
			stream = new FileOutputStream(file);
			
			xStream.toXML(list, stream);
			
			
		} catch (Exception e) {
			
			Mensagem.exibirMensagem("Falha Ao Salvar Xml"+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<? extends Object> Carregar(String endereco)
	{
		ArrayList<? extends Object> temp = null;
		File file = new File(endereco);

		try {
			
			if(! (file.exists()) )
			{
				file.createNewFile();
			}
	
			temp = (ArrayList<? extends Object>) xStream.fromXML(file);
			
		} catch (Exception e) {
			Mensagem.exibirMensagem("Falha Ao Carregar Xml"+e.getMessage());
		}

		return temp;
		
	}

	public ArrayList<String> getNomesPesquisas() {
		return nomesPesquisas;
	}

	public void setNomesPesquisas(ArrayList<String> nomesPesquisas) {
		this.nomesPesquisas = nomesPesquisas;
	}

	public ArrayList<Pesquisa> getPesquisas() {
		return pesquisas;
	}

	public void setPesquisas(ArrayList<Pesquisa> pesquisas) {
		this.pesquisas = pesquisas;
	}
	
	public ArrayList<Entidade> concatenarListas(ArrayList<Entidade> primeiraLista, 
			ArrayList<Entidade> segundaLista) {
		
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