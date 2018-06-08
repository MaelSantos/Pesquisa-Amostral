package view;

import javax.swing.JFrame;

public abstract class FrameGenerico extends JFrame {

	public static final int LARGURA = 600;
	public static final int ALTURA = 500;
	
	public FrameGenerico() {
		
//		inicializar();
		configurar();
	}
	
	public abstract void inicializar();
	
	public void configurar()
	{
		setSize(LARGURA, ALTURA);
		
		setTitle("Estatistica");
		
		setLayout(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
//		setUndecorated(true);
		
		setVisible(false);
		
	}
	
}
