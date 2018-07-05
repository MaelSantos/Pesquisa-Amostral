package model;

import view.Cadastro;
import view.CriarPesquisa;
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

	public static boolean verificarCriarPesquisa(CriarPesquisa criar)
	{
		if(criar.getTfdAssunto().getText().trim().equals("") ||
				criar.getTfdTitulo().getText().trim().equals(""))
			return false;
		return true;
	}

}

