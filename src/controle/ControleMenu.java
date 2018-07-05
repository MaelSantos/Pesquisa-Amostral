package controle;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import model.Dados;
import model.Entidade;
import model.Pesquisa;
import model.Verificar;
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
		
		menu.getCriar().getRdbQualitativo().addActionListener(this);
		menu.getCriar().getRdbQuantitativo().addActionListener(this);
		
		menu.getVisualizar().addActionListener(this);
		menu.getNova().addActionListener(this);
		menu.getSair().addActionListener(this);
		
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
			if(Verificar.verificarCriarPesquisa(menu.getCriar()))
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
				menu.getCriar().atualizar();
//			ControleQualitativo.getInstance().notificar();
//			ControleQuantitativo.getInstance().notificar();	
				
				Mensagem.exibirMensagem("Pesquisa criada com sucesso!!!");
			}
			else
				Mensagem.exibirMensagem("Preencha todos os dados nescessarios!!!");
			
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
			menu.getCriar().getTfdEscolhas().setVisible(true);
			menu.getCriar().getLblEscolhas().setVisible(true);
			menu.getCriar().getTxaEscolhas().setVisible(true);
			menu.getCriar().getScpEscolhas().setVisible(true);
			menu.getCriar().getBtnAddEscolha().setVisible(true);
		}
		if(obj == menu.getCriar().getRdbQuantitativo())
		{
			menu.getCriar().getTfdEscolhas().setVisible(false);
			menu.getCriar().getLblEscolhas().setVisible(false);
			menu.getCriar().getTxaEscolhas().setVisible(false);
			menu.getCriar().getScpEscolhas().setVisible(false);
			menu.getCriar().getBtnAddEscolha().setVisible(false);
		}
		if(obj == menu.getNova())
		{
			menu.getCriar().getTfdEscolhas().setVisible(true);
			menu.getCriar().getLblEscolhas().setVisible(true);
			menu.getCriar().getTxaEscolhas().setVisible(true);
			menu.getCriar().getScpEscolhas().setVisible(true);
			menu.getCriar().getBtnAddEscolha().setVisible(true);
			menu.getCriar().getRdbQualitativo().setVisible(true);
			menu.getCriar().getRdbQuantitativo().setVisible(true);
			menu.getCriar().getTfdAssunto().setVisible(true);
			menu.getCriar().getTfdEscolhas().setVisible(true);
			menu.getCriar().getTfdTitulo().setVisible(true);
			menu.getCriar().getLblAssunto().setVisible(true);
			menu.getCriar().getLblEscolhas().setVisible(true);
			menu.getCriar().getLblTitulo().setVisible(true);
			menu.getCriar().getBtnCriar().setVisible(true);
			
			menu.getCriar().getLblVisualizar().setVisible(false);
			menu.getCriar().getBtnVisualizar().setVisible(false);
			menu.getCriar().getCbxPesquisas().setVisible(false);
		}
		if(obj == menu.getVisualizar())
		{	
			menu.getCriar().getTfdEscolhas().setVisible(false);
			menu.getCriar().getLblEscolhas().setVisible(false);
			menu.getCriar().getTxaEscolhas().setVisible(false);
			menu.getCriar().getScpEscolhas().setVisible(false);
			menu.getCriar().getBtnAddEscolha().setVisible(false);
			menu.getCriar().getRdbQualitativo().setVisible(false);
			menu.getCriar().getRdbQuantitativo().setVisible(false);
			menu.getCriar().getTfdAssunto().setVisible(false);
			menu.getCriar().getTfdEscolhas().setVisible(false);
			menu.getCriar().getTfdTitulo().setVisible(false);
			menu.getCriar().getLblAssunto().setVisible(false);
			menu.getCriar().getLblEscolhas().setVisible(false);
			menu.getCriar().getLblTitulo().setVisible(false);
			menu.getCriar().getBtnCriar().setVisible(false);
			
			menu.getCriar().getLblVisualizar().setVisible(true);
			menu.getCriar().getBtnVisualizar().setVisible(true);
			menu.getCriar().getCbxPesquisas().setVisible(true);
		}
		if(obj == menu.getCriar().getBtnAddEscolha())
		{
			menu.getCriar().getEscolhas().add(menu.getCriar().getTfdEscolhas().getText());
			menu.getCriar().getTxaEscolhas().setText(
					menu.getCriar().getTxaEscolhas().getText()
					+"\n"+
					menu.getCriar().getTfdEscolhas().getText());
		}
		if(obj == menu.getSair())
		{
			System.exit(0);
		}
	}
	
	public Menu getMenu() {
		return menu;
	}

}
