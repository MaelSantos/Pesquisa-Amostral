package view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Oberservador;

public abstract class PanelGenerico extends JPanel implements Oberservador {

	public PanelGenerico(String borda) {
		
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
	
}
