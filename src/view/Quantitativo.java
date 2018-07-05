package view;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Quantitativo extends FrameGenerico {

	private JMenuBar mnbMenu;
	private JMenuItem mniTabela, mniHistograma, mniSair;
	private Tabela tabela;
	private Histograma histograma;
	
	public Quantitativo() {
		super();
	}
	
	@Override
	public void inicializar() {
		
		this.tabela = new Tabela();
		this.histograma = new Histograma();
		
		mnbMenu = new JMenuBar();
		
		mniTabela = new JMenuItem("Tabela");
		mniHistograma = new JMenuItem("Histograma");
		mniSair = new JMenuItem("Voltar");
		
		mnbMenu.add(mniTabela);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniHistograma);
		mnbMenu.add(new JSeparator(1));
		mnbMenu.add(mniSair);
	
		setJMenuBar(mnbMenu);
		
		tabela.setVisible(true);
		add(tabela);
		add(histograma);

	}
	
	public JMenuItem getMniTabela() {
		return mniTabela;
	}

	public JMenuItem getMniHistograma() {
		return mniHistograma;
	}

	public JMenuItem getMniSair() {
		return mniSair;
	}

	public JMenuBar getMnbMenu() {
		return mnbMenu;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public Histograma getHistograma() {
		return histograma;
	}

}
