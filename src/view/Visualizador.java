package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import model.Dados;
import model.Entidade;

public class Visualizador extends PanelGenerico {

	private JButton btnAmostra;
	private JTable tblPopulacao, tblAmostra;
	private TableModel tbmPopulacao, tbmAmostra;
	private JScrollPane scpAmostra, scpPopulacao;
	private JFormattedTextField ftxtPorcentagem;
	private MaskFormatter mascara;
	private JLabel lblPorcentagem, lblTitulo, lblQuantidade;
	private JComboBox<String> cbxAmostras;

	public Visualizador() {
		super("Vizualizar");
		setLayout(new FlowLayout(100,5,0));
	}

	@Override
	public void inicializar() {

		btnAmostra = new JButton("Tirar Amostra");

		mascara = new MaskFormatter();
		mascara.setValidCharacters("1234567890");
		try {
			mascara.setMask("##");
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		ftxtPorcentagem = new JFormattedTextField(mascara);
		ftxtPorcentagem.setText("40");

		lblPorcentagem = new JLabel("Porcentagem: ");
		lblTitulo = new JLabel("Vazio"); 
		lblQuantidade = new JLabel("Quantidade: Vazio");

		tbmAmostra = new TableModel();
		tbmPopulacao = new TableModel(pesquisa.getEntidades());
		tblPopulacao = new JTable(tbmPopulacao);
		tblAmostra = new JTable(tbmAmostra);
		tblAmostra.setPreferredScrollableViewportSize(new Dimension(230, 300));
		tblPopulacao.setPreferredScrollableViewportSize(new Dimension(230, 300));
		tblAmostra.setShowHorizontalLines(false);
		tblAmostra.setShowVerticalLines(false);
		tblPopulacao.setShowHorizontalLines(false);
		tblPopulacao.setShowVerticalLines(false);
		scpPopulacao = new JScrollPane(tblPopulacao);
		scpAmostra = new JScrollPane(tblAmostra);

		cbxAmostras = new JComboBox<String>();
		cbxAmostras.addItem("Inteiramente Aleatoria");
		cbxAmostras.addItem("Sistematica");
		cbxAmostras.addItem("Estratificamente Proporcional");

		add(lblTitulo);
		add(lblQuantidade);
		add(scpPopulacao);
		add(scpAmostra);
		add(cbxAmostras);
		add(lblPorcentagem);
		add(ftxtPorcentagem);
		add(btnAmostra);

	}

	public void atualizar() {

		lblTitulo.setText(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTitulo()); 
		lblQuantidade.setText("Quantidade: "+Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size());

		pesquisa.getEntidades().clear();
		pesquisa.getEntidades().addAll(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
		tbmPopulacao.setEntidades(pesquisa.getEntidades());


	}

	//metodos de acesso
	public JButton getBtnAmostra() {
		return btnAmostra;
	}

	public JScrollPane getScpAmostra() {
		return scpAmostra;
	}

	public JScrollPane getScpPopulacao() {
		return scpPopulacao;
	}

	public JFormattedTextField getTxtPorcentagem() {
		return ftxtPorcentagem;
	}

	public JTable getTblPopulacao() {
		return tblPopulacao;
	}

	public JTable getTblAmostra() {
		return tblAmostra;
	}

	public JFormattedTextField getFtxtPorcentagem() {
		return ftxtPorcentagem;
	}

	public MaskFormatter getMascara() {
		return mascara;
	}

	public JLabel getLblPorcentagem() {
		return lblPorcentagem;
	}

	public JComboBox<String> getCbxAmostras() {
		return cbxAmostras;
	}

	public TableModel getTbmPopulacao() {
		return tbmPopulacao;
	}

	public TableModel getTbmAmostra() {
		return tbmAmostra;
	}

	public JLabel getLblQuantidade() {
		return lblQuantidade;
	}

}
