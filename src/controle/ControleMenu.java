package controle;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import model.Dados;
import model.Entidade;
import model.Pesquisa;
import view.Mensagem;
import view.Menu;

public class ControleMenu extends Controle{

	private static ControleMenu instance;
	private Menu menu;
	
	private ControleMenu() {
		this.menu = new Menu();
		init(menu);
		
		menu.getCriar().getBtnCriar().addActionListener(this);
		menu.getCriar().getBtnVisualizar().addActionListener(this);
		menu.getCriar().getBtnAddEscolha().addActionListener(this);
		menu.getCriar().getBtnSair().addActionListener(this);
		
		menu.getCriar().getRdbQualitativo().addActionListener(this);
		menu.getCriar().getRdbQuantitativo().addActionListener(this);
		
	}
	
	public static ControleMenu getInstance()
	{
		if(instance == null)
			instance = new ControleMenu();
		
		return instance;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == menu.getCriar().getBtnCriar())
		{	
			int tipo = 2;			
			if(menu.getCriar().getRdbQualitativo().isSelected())
				tipo = Pesquisa.QUALITATIVO;
			else if(menu.getCriar().getRdbQuantitativo().isSelected())
				tipo = Pesquisa.QUANTITATIVO;
			
			Dados.getInstance().atualizarBanco( 
					menu.getCriar().getTfdTitulo().getText().trim(),//titulo, 
					menu.getCriar().getEscolhas(),//escolhas, 
					menu.getCriar().getTfdAssunto().getText().trim(),
					tipo); //nomePesquisa,
			
			notificar();
//			ControleQualitativo.getInstance().notificar();
//			ControleQuantitativo.getInstance().notificar();
			
		}
		if(obj == menu.getCriar().getBtnVisualizar())
		{
			Dados.getInstance().entrarPesquisa(menu.getCriar().getCbxPesquisas().getSelectedItem().toString());
			
			if(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipo() == Pesquisa.QUALITATIVO)
			{
				menu.setVisible(false);
				ControleQualitativo.getInstance().getQualitativo().setVisible(true);
				ControleQualitativo.getInstance().notificar();
			}
			else if(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipo() == Pesquisa.QUANTITATIVO)
			{
				menu.setVisible(false);
				ControleQuantitativo.getInstance().getQuantitativo().setVisible(true);
				ControleQuantitativo.getInstance().notificar();
			}			
			notificar();
			
		}
		if(obj == menu.getCriar().getRdbQualitativo())
		{
			menu.getCriar().getRdbQuantitativo().setSelected(false);
			menu.getCriar().getTfdEscolhas().setVisible(true);
			menu.getCriar().getLblEscolhas().setVisible(true);
			menu.getCriar().getTxaEscolhas().setVisible(true);
		}
		if(obj == menu.getCriar().getRdbQuantitativo())
		{
			menu.getCriar().getRdbQualitativo().setSelected(false);
			menu.getCriar().getTfdEscolhas().setVisible(false);
			menu.getCriar().getLblEscolhas().setVisible(false);
			menu.getCriar().getTxaEscolhas().setVisible(false);
		}
		if(obj == menu.getCriar().getBtnAddEscolha())
		{
			menu.getCriar().getEscolhas().add(menu.getCriar().getTfdEscolhas().getText());
			menu.getCriar().getTxaEscolhas().setText(
					menu.getCriar().getTxaEscolhas().getText()
					+"\n"+
					menu.getCriar().getTfdEscolhas().getText());
		}
		if(obj == menu.getCriar().getBtnSair())
		{
			System.exit(0);
		}
	}
	
	public Menu getMenu() {
		return menu;
	}

}
