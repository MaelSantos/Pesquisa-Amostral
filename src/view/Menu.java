package view;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends FrameGenerico{
	
	private CriarPesquisa criar;
	private JMenuBar menu;
	private JMenuItem nova, visualizar, sair;
	
	public Menu() {
		super();
		setLayout(new BorderLayout());
		setVisible(true);
	}
	
	@Override
	public void inicializar() {
		
		menu = new JMenuBar();
		nova = new JMenuItem("Criar");
		visualizar = new JMenuItem("Visualizar");
		sair = new JMenuItem("Sair");
		
		menu.add(nova);
		menu.add(visualizar);
		menu.add(sair);
		setJMenuBar(menu);
		
		criar = new CriarPesquisa();	
		
		setLayout(new BorderLayout());
		add(criar,BorderLayout.CENTER);
		
	}

	public CriarPesquisa getCriar() {
		return criar;
	}

	public JMenuBar getMenu() {
		return menu;
	}

	public JMenuItem getNova() {
		return nova;
	}

	public JMenuItem getVisualizar() {
		return visualizar;
	}

	public JMenuItem getSair() {
		return sair;
	}
}
