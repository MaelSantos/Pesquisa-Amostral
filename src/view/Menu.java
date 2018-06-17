package view;

public class Menu extends FrameGenerico{
	
	private CriarPesquisa criar;
	
	public Menu() {
		super();
		setVisible(true);
	}
	
	@Override
	public void inicializar() {
		
		criar = new CriarPesquisa();		
		add(criar);
		
	}

	public CriarPesquisa getCriar() {
		return criar;
	}
}
