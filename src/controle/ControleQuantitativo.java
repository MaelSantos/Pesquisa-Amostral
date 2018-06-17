package controle;

import java.awt.event.ActionEvent;

import model.Dados;
import model.Entidade;
import view.Quantitativo;

public class ControleQuantitativo extends Controle{

	private static ControleQuantitativo instance;
	private Quantitativo quantitativo;
	
	protected ControleQuantitativo() {
		
		quantitativo = new Quantitativo();
		init(quantitativo);

		quantitativo.getMniHistograma().addActionListener(this);
		quantitativo.getMniSair().addActionListener(this);
		quantitativo.getMniTabela().addActionListener(this);
		quantitativo.getTabela().getBtnAdd().addActionListener(this);
		
	}

	public static ControleQuantitativo getInstance()
	{
		if(instance == null)
			instance = new ControleQuantitativo();
		return instance;
	}

	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		if(obj == quantitativo.getMniSair())
		{
			quantitativo.setVisible(false);
			ControleMenu.getInstance().getMenu().setVisible(true);
		}
		if(obj == quantitativo.getMniTabela())
		{
			quantitativo.getTabela().setVisible(true);
			quantitativo.getHistograma().setVisible(false);
		}
		if(obj == quantitativo.getMniHistograma())
		{
			quantitativo.getHistograma().setVisible(true);
			quantitativo.getTabela().setVisible(false);
		}
		if(obj == quantitativo.getTabela().getBtnAdd())
		{
			Entidade entidade = new Entidade("", 
					quantitativo.getTabela().getTfdAdd().getText().trim(), 
					Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getIndice()+1);
			
			Dados.getInstance().addDado(entidade,"res/pesquisas.xml");
			quantitativo.getTabela().getTxaDados().setText(
					Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().toString());
			
			quantitativo.getTabela().getModel().addUsuario(entidade);
			quantitativo.getTabela().atualizar();
			quantitativo.getHistograma().atualizar();
		}
		if(obj == quantitativo.getMniSair())
		{
			quantitativo.setVisible(false);
			ControleMenu.getInstance().getMenu().setVisible(true);
		}
		
	}

	public Quantitativo getQuantitativo() {
		return quantitativo;
	}
	
	
}
