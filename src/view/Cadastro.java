package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Dados;

public class Cadastro extends PanelGenerico {

	private JLabel lblNome,lblPesquisa ,lblQuantidade;
	private JTextField tfdNome;
	private JComboBox<String> cbxPesquisa;
	private JButton btnAdd;
	
	private int quantidade;
	
	public Cadastro() {
		super("Cadastro");
	}
	
	public void inicializar() {
				
		quantidade = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		
		lblNome = new JLabel("Nome:");
		lblPesquisa = new JLabel("Pesquisa:");
		lblQuantidade = new JLabel("Quantidade: "+quantidade);
		btnAdd = new JButton("Add");
		tfdNome = new JTextField(10);
		cbxPesquisa = new JComboBox<String>();
		
		add(lblNome);
		add(tfdNome);
		add(lblPesquisa);
		add(cbxPesquisa);
		add(lblQuantidade);
		add(btnAdd);
		
	}

	public void atualizar() {
		quantidade = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		lblQuantidade.setText("Quantidade: "+quantidade);

		cbxPesquisa.removeAllItems();

		for(String s: Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos())
			cbxPesquisa.addItem(s);
	}
	
	//metodos de acesso
	public JLabel getLblQuantidade() {
		return lblQuantidade;
	}

	public JTextField getTfdNome() {
		return tfdNome;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public JComboBox<String> getCbxPesquisa() {
		return cbxPesquisa;
	}
	
}
