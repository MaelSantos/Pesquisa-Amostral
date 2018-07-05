package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Dados;

public class CriarPesquisa extends PanelGenerico{

	private JTextField tfdTitulo, tfdAssunto, tfdEscolhas;
	private JLabel lblTitulo, lblAssunto, lblEscolhas, lblVisualizar;
	private JButton btnCriar, btnAddEscolha, btnVisualizar;
	private JComboBox<String> cbxPesquisas;
	private JRadioButton rdbQuantitativo, rdbQualitativo;
	private ButtonGroup group;
	private JScrollPane scpEscolhas;
	
	private ArrayList<String> escolhas;
	private JTextArea txaEscolhas;
	
	private JPanel visualizar, nova;
	
	public CriarPesquisa() {
		super("Pesquisa");
	
//		setLayout(new GridLayout(10, 3));
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
		lblVisualizar = new JLabel("Visualizar:");
		lblVisualizar.setVisible(false);
		
		rdbQuantitativo = new JRadioButton("Quantitativo");
		rdbQualitativo = new JRadioButton("Qualitativo");
		group = new ButtonGroup();
		group.add(rdbQualitativo);
		group.add(rdbQuantitativo);
		
		cbxPesquisas = new JComboBox<String>();
		for(String s : Dados.getInstance().getNomesPesquisas())
			cbxPesquisas.addItem(s);
		cbxPesquisas.setVisible(false);
		
		scpEscolhas = new JScrollPane(txaEscolhas);
		
		btnCriar = new JButton("Criar Pesquisa");
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setVisible(false);
		btnAddEscolha = new JButton("Adicionar Escolha");
		
		visualizar = new JPanel();
		visualizar.setLayout(new GridLayout(6, 2));
		
		nova = new JPanel();
		nova.setLayout(new GridLayout(8, 2));
		
		nova.add(lblTitulo);
		nova.add(tfdTitulo);
		
		nova.add(lblAssunto);
		nova.add(tfdAssunto);
		
		nova.add(rdbQualitativo);
		nova.add(rdbQuantitativo);
		
		nova.add(lblEscolhas);
		nova.add(tfdEscolhas);
		
		nova.add(scpEscolhas);
		
		nova.add(btnAddEscolha);
		
		nova.add(new JLabel());
		nova.add(btnCriar);
		
		visualizar.add(lblVisualizar);
		visualizar.add(cbxPesquisas);
		visualizar.add(btnVisualizar);		
		
//		setLayout(new BorderLayout());
//		add(nova,BorderLayout.CENTER);
//		add(visualizar,BorderLayout.NORTH);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(
				GridBagConstraints.RELATIVE, //gridx
				GridBagConstraints.RELATIVE, //gridy
				GridBagConstraints.RELATIVE, //gridwidth
				GridBagConstraints.RELATIVE, //gridheight
				0.1, //weightx
				0.1, //weighty
				GridBagConstraints.CENTER, //anchor
				GridBagConstraints.HORIZONTAL, //fill
				new Insets(0, 0, 0, 0), //insets
				0, //ipadx
				0); //ipady
		
//		nova.setBackground(Color.RED);
//		visualizar.setBackground(Color.BLUE);
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridheight = GridBagConstraints.REMAINDER;
		
		add(nova, c);
		add(visualizar, c);
		
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

	public JLabel getLblVisualizar() {
		return lblVisualizar;
	}

}
