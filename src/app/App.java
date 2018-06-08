package app;

import controle.Controle;
import javafx.scene.control.Tab;
import view.Cadastro;
import view.Graficos;
import view.Histograma;
import view.Marcas;
import view.Menu;
import view.Qualitativo;
import view.Quantitativo;
import view.Tabela;
import view.Visualizador;

public class App {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
		Cadastro cadastro = new Cadastro();
		
		Visualizador visualizador = new Visualizador(); 
		
		Marcas marcas = new Marcas();
		
		Graficos graficos = new Graficos();
		
		Qualitativo qualitativo = new Qualitativo(cadastro, visualizador, marcas, graficos);
		
		Tabela tabela = new Tabela();
		
		Histograma histograma = new Histograma();
		
		Quantitativo quantitativo = new Quantitativo(tabela, histograma);
		
		Controle controle = new Controle(menu, qualitativo, quantitativo, cadastro, 
				visualizador, marcas, graficos, tabela, histograma);
		
	}
	
}
