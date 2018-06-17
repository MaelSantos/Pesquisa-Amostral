package controle;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import model.Amostra;
import model.Dados;
import model.Calculo;
import model.Entidade;
import model.Oberservador;
import model.Verificar;
import view.Cadastro;
import view.FrameGenerico;
import view.Graficos;
import view.Histograma;
import view.Marcas;
import view.Mensagem;
import view.Menu;
import view.PanelGenerico;
import view.Qualitativo;
import view.Quantitativo;
import view.Tabela;
import view.Visualizador;

public abstract class Controle implements ActionListener{

	protected FrameGenerico tela;
	protected ArrayList<Oberservador> oberservadores;

	public void mudarTela(JPanel abrir)
	{		
		for(Component c : tela.getContentPane().getComponents())
		{
			if(c.equals(abrir))
				c.setVisible(true);
			else
				c.setVisible(false);
		}
	}
	
	protected void init(FrameGenerico tela)
	{
		oberservadores = new ArrayList<Oberservador>();
		this.tela = tela;
		
		for(PanelGenerico p : tela.getGenericos())
		{
			oberservadores.add(p);
		}
		
	}
	
	public void notificar()
	{
		for(Oberservador o : tela.getGenericos())
		{
			o.atualizar();
		}
	}

}
