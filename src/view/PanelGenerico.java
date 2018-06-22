package view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Oberservador;
import model.Pesquisa;

public abstract class PanelGenerico extends JPanel implements Oberservador {

	protected Pesquisa pesquisa;
	
	public PanelGenerico(String borda) {
		
		pesquisa = new Pesquisa();
		
		setBorder(BorderFactory.createTitledBorder(borda));
		
		inicializar();
		configurar();
	}
	
	public abstract void inicializar();
	
	public void configurar()
	{
		setSize(FrameGenerico.LARGURA, FrameGenerico.ALTURA);
		
		setLayout(new FlowLayout(10,10, 10));
		
		setVisible(false);		
	}

	public Pesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}
	
}
