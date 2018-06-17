package app;

import controle.ControleMenu;
import controle.ControleQualitativo;
import controle.ControleQuantitativo;

public class App {

	public static void main(String[] args) {
		
		ControleMenu controleMenu = ControleMenu.getInstance();
		
		ControleQualitativo controleQualitativo = ControleQualitativo.getInstance();
		
		ControleQuantitativo controleQuantitativo = ControleQuantitativo.getInstance();
		
	}
	
}
