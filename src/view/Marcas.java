package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Dados;
import model.DadosTabelaFrequencia;
import model.Calculo;
import model.Entidade;
import model.MedidasDeDispersao;

public class Marcas extends PanelGenerico{

	private JTable marcas;
	private TableModelMarcas model;
	private JScrollPane scpMarcas;
	private JTextField tfdPercentil;
	private JLabel lblTitulo, lblMedia, lblModa, lblMediana, lblQuartil, lblPercentil;
//	private JLabel lblVariancia, lblDesvio,lblCV;
	private JButton btnPercentil;
	public Marcas() {
		super("Marcas");
	}

	@Override
	public void inicializar() {

		Calculo.organizarLista((Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos()), 1);

		model = new TableModelMarcas((Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos()));
		marcas = new JTable(model);
		marcas.setPreferredScrollableViewportSize(new Dimension(FrameGenerico.LARGURA-40, 200));
		marcas.setShowHorizontalLines(false);
		marcas.setShowVerticalLines(false);

		scpMarcas = new JScrollPane(marcas);

		tfdPercentil = new JTextField(2);
		
		lblTitulo = new JLabel(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTitulo());
		lblMedia = new JLabel("Media: "+Calculo.media());
		lblModa = new JLabel("Moda: "+Calculo.moda());
		lblMediana = new JLabel("Mediana: "+Calculo.mediana());
		lblQuartil = new JLabel("Quatil: "+Calculo.quartil());
		lblPercentil = new JLabel("Percentil: "+Calculo.percentil(10));

//		lblVariancia = new JLabel(); 
//		lblDesvio = new JLabel(); 
//		lblCV = new JLabel();
		
		btnPercentil = new JButton("Calcular Percentil");
		btnPercentil.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				lblPercentil.setText("Percentil: "+Calculo.percentil(Integer.parseInt(tfdPercentil.getText().trim())));
				
			}
		});;
		
		add(lblTitulo);
		add(scpMarcas);
		add(lblMedia);
		add(lblModa);
		add(lblMediana);
		add(lblQuartil);
		add(btnPercentil);
		add(tfdPercentil);
		add(lblPercentil);
		
//		add(lblVariancia);
//		add(lblDesvio);
//		add(lblCV);
	}

	public void atualizar()
	{
		pesquisa.getEntidades().clear();
		pesquisa.getEntidades().addAll(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
		
		lblTitulo.setText(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTitulo());

		lblMedia.setText("Media: "+Calculo.media());
		lblModa.setText("Moda: "+Calculo.moda());
		lblMediana.setText("Mediana: "+Calculo.mediana());	
		lblQuartil.setText("Quatil: "+Calculo.quartil());

		ArrayList<String> list = new ArrayList<String>();
		
		for(Entidade e : pesquisa.getEntidades())
			list.add(e.getDado());
		
		String[] temp = new String[list.size()]; 
		
//		lblVariancia.setText("Variancia: "+MedidasDeDispersao.varianciaPopulacional(list.toArray(temp))); 
//		lblDesvio.setText("Desvio: "+MedidasDeDispersao.desvioNaTabela(pesquisa.getEntidades().size()));
//		lblCV.setText("Coeficiente De Variação: "+MedidasDeDispersao.coeficienteDeVariacaoNaTabela(pesquisa.getEntidades().size()));
		
		model.nomesPesquisa = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos();
		Calculo.organizarLista(model.getNomeMarcas(), 1);
		model.fireTableDataChanged();
	}

	public JTable getMarcas() {
		return marcas;
	}

	public TableModelMarcas getModel() {
		return model;
	}

	public class TableModelMarcas extends AbstractTableModel
	{
		private ArrayList<String> nomesPesquisa; 
		private String[] colunas = new String[]{ 
				"Pesquisa", "Quantidade", "Frequencia Acumulada"};

		/** Creates a new instance of DevmediaTableModel */
		public TableModelMarcas(ArrayList<String> usuarios) {
			this.nomesPesquisa = usuarios;

			Calculo.organizarLista(nomesPesquisa, 1);
		}

		public TableModelMarcas() {
			// TODO Stub de construtor gerado automaticamente
		}

		public int getRowCount() {
			return nomesPesquisa.size();
		}

		public int getColumnCount() {
			return colunas.length;
		}

		@Override
		public String getColumnName(int columnIndex){
			return colunas[columnIndex];
		}    

		@Override  
		public Class<?> getColumnClass(int columnIndex) {  
			return String.class;  
		}

		@Override  
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  

			String usuario = nomesPesquisa.get(rowIndex);

			switch (columnIndex) 
			{
			case 0:  
				usuario = aValue.toString();             
			case 1:
				usuario = (String) getValueAt(rowIndex, columnIndex);
			case 2:
				usuario = (String) getValueAt(rowIndex, columnIndex); 		 

			default:  
				System.err.println("Índice da coluna inválido");
			}  
			fireTableCellUpdated(rowIndex, columnIndex);  
		}      

		public Object getValueAt(int rowIndex, int columnIndex) {
			String usuarioSelecionado = nomesPesquisa.get(rowIndex);
			String valueObject = null;
			switch(columnIndex){
			case 0: valueObject = usuarioSelecionado; break;
			case 1:
				int quant = 0;
				for(Entidade e : Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())
					if(e.getDado().equalsIgnoreCase(usuarioSelecionado))
						quant += 1;
				valueObject = quant+""; break;
			case 2: 
				int soma = Integer.parseInt((String) getValueAt(rowIndex, 1));
				if(rowIndex-1 < nomesPesquisa.size() && rowIndex != 0 && columnIndex != 1)
				{
					soma += Integer.parseInt((String) getValueAt(rowIndex-1, 2));
				}

				valueObject = soma+"";	

				//	       default: System.err.println("Índice inválido para propriedade do bean Usuario.class");
			}

			return valueObject;
		}

		@Override  
		public boolean isCellEditable(int rowIndex, int columnIndex) {  
			return false;  
		}

		public ArrayList<String> getNomeMarcas() {
			return nomesPesquisa;
		}

	}
	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public JLabel getLblMedia() {
		return lblMedia;
	}

	public JLabel getLblModa() {
		return lblModa;
	}

	public JLabel getLblMediana() {
		return lblMediana;
	}

	public JTextField getTfdPercentil() {
		return tfdPercentil;
	}

}
