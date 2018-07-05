package view;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Qualitativo extends FrameGenerico {

	private Cadastro cadastro;
	private Visualizador visualizador;
	private Marcas marcas;
	private Graficos graficos;
	
	private JMenuBar mnbMenu;
	private JMenuItem mniAdd, mniVisualizar, mniSair, mniMarcas, mniGraficos;
	
	public Qualitativo() {
		super();
		
	}

	@Override
	public void inicializar() {
			
		this.cadastro = new Cadastro();
		this.visualizador = new Visualizador();
		this.marcas = new Marcas();
		this.graficos = new Graficos();
		
		mnbMenu = new JMenuBar();
		
		mniAdd = new JMenuItem("Adicionar Entidade");
		mniVisualizar = new JMenuItem("Tirar Amostras");
		mniSair = new JMenuItem("Voltar");
		mniMarcas = new JMenuItem("Tabela");
		mniGraficos = new JMenuItem("Graficos");
		
		mnbMenu.add(mniAdd);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniVisualizar);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniMarcas);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniGraficos);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniSair);
		
		setJMenuBar(mnbMenu);

		add(cadastro);
		add(visualizador);
		add(marcas);
		add(graficos);
		
		cadastro.setVisible(true);
	}

	public JMenuBar getMnbMenu() {
		return mnbMenu;
	}

	public JMenuItem getMniAdd() {
		return mniAdd;
	}

	public JMenuItem getMniVisualizar() {
		return mniVisualizar;
	}

	public JMenuItem getMniSair() {
		return mniSair;
	}

	public JMenuItem getMniMarcas() {
		return mniMarcas;
	}

	public JMenuItem getMniGraficos() {
		return mniGraficos;
	}

	public Cadastro getCadastro() {
		return cadastro;
	}

	public Visualizador getVisualizador() {
		return visualizador;
	}

	public Marcas getMarcas() {
		return marcas;
	}

	public Graficos getGraficos() {
		return graficos;
	}
	
}
