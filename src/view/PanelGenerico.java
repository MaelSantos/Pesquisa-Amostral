package view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public abstract class PanelGenerico extends JPanel {

	public PanelGenerico() {
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
