package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Dados;

public class CriarPesquisa extends PanelGenerico{

	private JTextField tfdTitulo, tfdAssunto, tfdEscolhas;
	private JLabel lblTitulo, lblAssunto, lblEscolhas;
	private JButton btnCriar, btnAddEscolha, btnVisualizar, btnSair;
	private JComboBox<String> cbxPesquisas;
	private JRadioButton rdbQuantitativo, rdbQualitativo;
	private ButtonGroup group;
	private JScrollPane scpEscolhas;
	
	private ArrayList<String> escolhas;
	private JTextArea txaEscolhas;
	
	public CriarPesquisa() {
		super("Criar Pesquisa");
		
		FlowLayout f = new FlowLayout();
		f.setHgap(10);
		f.setVgap(10);
		
		setLayout(f);
		setVisible(true);
	}

	@Override
	public void inicializar() {
		
		escolhas = new ArrayList<String>();
		txaEscolhas = new JTextArea();
		txaEscolhas.setPreferredSize(new Dimension(200, 200));
		
		tfdTitulo = new JTextField(10); 
		tfdAssunto = new JTextField(10);
		tfdEscolhas = new JTextField(10);
		
		lblTitulo = new JLabel("Titulo"); 
		lblAssunto = new JLabel("Assunto");
		lblEscolhas = new JLabel("Escolhas");
		
		rdbQuantitativo = new JRadioButton("Quantitativo");
		rdbQualitativo = new JRadioButton("Qualitativo");
		group = new ButtonGroup();
		group.add(rdbQualitativo);
		group.add(rdbQuantitativo);
		
		cbxPesquisas = new JComboBox<String>();
		for(String s : Dados.getInstance().getNomesPesquisas())
			cbxPesquisas.addItem(s);
		
		scpEscolhas = new JScrollPane(txaEscolhas);
		
		btnCriar = new JButton("Criar Pesquisa");
		btnVisualizar = new JButton("Visualizar");
		btnAddEscolha = new JButton("Adicionar Escolha");
		btnSair = new JButton("Sair");
		
		add(lblTitulo);
		add(tfdTitulo);
		
		add(lblAssunto);
		add(tfdAssunto);
		
		add(lblEscolhas);
		add(tfdEscolhas);
		add(btnAddEscolha);
		
		add(rdbQualitativo);
		add(rdbQuantitativo);
		
		add(scpEscolhas);
		
		add(btnCriar);
		
		add(cbxPesquisas);
		add(btnVisualizar);
		
		add(btnSair);
		
	}

	public void atualizar() {
		
		cbxPesquisas.removeAllItems();
		for(String s : Dados.getInstance().getNomesPesquisas())
			cbxPesquisas.addItem(s);
		
		txaEscolhas.setText("");
		tfdTitulo.setText(""); 
		tfdAssunto.setText("");
		tfdEscolhas.setText("");
	}
	
	public JTextField getTfdTitulo() {
		return tfdTitulo;
	}

	public JTextField getTfdAssunto() {
		return tfdAssunto;
	}

	public JButton getBtnCriar() {
		return btnCriar;
	}

	public JButton getBtnVisualizar() {
		return btnVisualizar;
	}

	public JButton getBtnSair() {
		return btnSair;
	}

	public JRadioButton getRdbQuantitativo() {
		return rdbQuantitativo;
	}

	public JRadioButton getRdbQualitativo() {
		return rdbQualitativo;
	}

	public JComboBox<String> getCbxPesquisas() {
		return cbxPesquisas;
	}

	public JTextField getTfdEscolhas() {
		return tfdEscolhas;
	}

	public JLabel getLblEscolhas() {
		return lblEscolhas;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JLabel getLblAssunto() {
		return lblAssunto;
	}

	public ArrayList<String> getEscolhas() {
		return escolhas;
	}

	public JTextArea getTxaEscolhas() {
		return txaEscolhas;
	}

	public JButton getBtnAddEscolha() {
		return btnAddEscolha;
	}

	public JScrollPane getScpEscolhas() {
		return scpEscolhas;
	}

}
