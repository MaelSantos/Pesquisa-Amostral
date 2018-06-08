package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.BancoDeDados;

public class Cadastro extends PanelGenerico {

	private JLabel lblNome,lblPesquisa ,lblQuantidade;
	private JTextField tfdNome;
	private JComboBox<String> cbxPesquisa;
	private JButton btnAdd;
	
	private int quantidade;
	
	public Cadastro() {
		super();
	}
	
	public void inicializar() {
				
		quantidade = BancoDeDados.entidades.size();
		
		lblNome = new JLabel("Nome:");
		lblPesquisa = new JLabel("Pesquisa:");
		lblQuantidade = new JLabel("Quantidade: "+quantidade);
		btnAdd = new JButton("Add");
		tfdNome = new JTextField(10);
		cbxPesquisa = new JComboBox<String>();
		for(String s : BancoDeDados.marcasNomes)
			cbxPesquisa.addItem(s);
		
		add(lblNome);
		add(tfdNome);
		add(lblPesquisa);
		add(cbxPesquisa);
		add(lblQuantidade);
		add(btnAdd);
		
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
