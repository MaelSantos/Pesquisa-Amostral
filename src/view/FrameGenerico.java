package view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;

public abstract class FrameGenerico extends JFrame {

	public static final int LARGURA = 600;
	public static final int ALTURA = 500;
	private ArrayList<PanelGenerico> genericos;
	
	public FrameGenerico() {
		
		genericos = new ArrayList<PanelGenerico>();
		
		configurar();
		inicializar();
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
	
	@Override
	public Component add(Component comp) {
		
		if (comp instanceof PanelGenerico) {
			PanelGenerico panel = (PanelGenerico) comp;
			
			genericos.add(panel);
			
		}
		
		return super.add(comp);
	}
	
	public ArrayList<PanelGenerico> getGenericos() {
		return genericos;
	}
}
