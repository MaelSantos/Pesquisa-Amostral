package model;

import view.Cadastro;
import view.Marcas;
import view.Visualizador;

public class Verificar {

	public static boolean VeriifcarCadastro(Cadastro cadastro)
	{
		if(cadastro.getTfdNome().getText().trim().equals(""))
			return false;
		return true;		
		
	}
	
	public static boolean VeriifcarPorcentagem(Visualizador visualizador)
	{
		if(visualizador.getTxtPorcentagem().getText().trim().equals(""))
			return false;
		return true;		
		
	}

}

