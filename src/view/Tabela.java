package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Dados;
import model.DadosTabelaFrequencia;
import model.Entidade;
import model.MedidasDeDispersao;
import model.Calculo;

public class Tabela extends PanelGenerico {

	private JButton btnAdd, btnCarregar;
	private JTextField tfdAdd;
	private JTextArea txaDados;
	private JTable tabela;
	private TableModel  model;
	private JScrollPane scpPanel;
	private JTextField tfdPercentil;
	private JLabel lblTitulo, lblMedia, lblModa, lblMediana, lblQuartil, lblPercentil, lblTotal;
	private JLabel lblVariancia, lblDesvio,lblCV;
	private JButton btnPercentil;
	private JFileChooser fcsArquivo;
	
	public Tabela() {
		super("Tabelas");
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
	}

	@Override
	public void inicializar() {

		model = new TableModel(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
		tabela = new JTable(model);
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 143));

		btnAdd = new JButton("Add");
		btnCarregar = new JButton("Carregar Dados");
		tfdAdd = new JTextField(10);
		txaDados = new JTextArea();

		scpPanel = new JScrollPane(tabela);

		lblTitulo = new JLabel();
		lblTotal = new  JLabel();
		lblMedia = new JLabel();
		lblModa = new JLabel();
		lblMediana = new JLabel();
		lblQuartil = new JLabel();
		lblPercentil = new JLabel("Percentil: ");

		lblVariancia = new JLabel(); 
		lblDesvio = new JLabel(); 
		lblCV = new JLabel();
		
		fcsArquivo = new JFileChooser();
		
		tfdPercentil = new JTextField(2);
		
		btnPercentil = new JButton("Calcular Percentil");
		btnPercentil.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				lblPercentil.setText("Percentil: "+Calculo.percentil(pesquisa.getEntidades(),
						Integer.parseInt(tfdPercentil.getText().trim())));
				
			}
		});;
		
		btnCarregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int escolha = fcsArquivo.showOpenDialog(getParent());

				Dados.getInstance().buscarArquivo(fcsArquivo.getSelectedFile().getAbsolutePath());
				
				System.out.println(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
				
				atualizar();
			}
		});
		
		add(lblTitulo);
		add(btnAdd);
		add(tfdAdd);
		add(btnCarregar);
		add(new JScrollPane(txaDados)).setPreferredSize(new Dimension(200, 100));;
		add(scpPanel);
		add(lblTotal);
		add(lblMedia);
		add(lblModa);
		add(lblMediana);
		add(lblQuartil);
		add(btnPercentil);
		add(tfdPercentil);
		add(lblPercentil);
		
		add(lblVariancia);
		add(lblDesvio);
		add(lblCV);
	}

	public void atualizar()
	{
		pesquisa.getEntidades().clear();
		pesquisa.getEntidades().addAll(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
		model.setValores(pesquisa.getEntidades());
		
		
		if(!model.valores.isEmpty())
		{
			lblTotal.setText("Total: "+Calculo.total(pesquisa.getEntidades()));
			lblMedia.setText("Media: "+Calculo.media(pesquisa.getEntidades()));
			lblModa.setText("Moda: "+Calculo.moda(pesquisa.getEntidades()));
			lblMediana.setText("Mediana: "+Calculo.mediana(pesquisa.getEntidades()));
			lblQuartil.setText("Quatil: "+Calculo.quartil(pesquisa.getEntidades()));			
			
			ArrayList<String> list = new ArrayList<String>();
			
			for(Entidade e : pesquisa.getEntidades())
				list.add(e.getDado());
			
			String[] temp = new String[list.size()];
			
			DadosTabelaFrequencia.frequencia(list.toArray(temp));
			DadosTabelaFrequencia.Xi(list.toArray(temp));
			DadosTabelaFrequencia.XiFi();
			DadosTabelaFrequencia.frequenciaRelativa(pesquisa.getEntidades().size());
			DadosTabelaFrequencia.FrequenciaRelativa();
			DadosTabelaFrequencia.frequenciaAcumulada();
			
			lblVariancia.setText("Variancia: "+MedidasDeDispersao.varianciaPopulacional(list.toArray(temp))); 
			lblDesvio.setText("Desvio: "+MedidasDeDispersao.desvioNaTabela(pesquisa.getEntidades().size()));
			lblCV.setText("Coeficiente De Variação: "+MedidasDeDispersao.coeficienteDeVariacaoNaTabela(pesquisa.getEntidades().size()));
			
			model.maior = Calculo.maior(model.valores);
			model.menor = Calculo.menor(model.valores);
			model.classe = Calculo.classes(model.valores);
			model.num = Calculo.minmax(model.valores);
		}
		
		String s = "";
		for(Entidade e : pesquisa.getEntidades())
			s += e.getDado()+"\n";
			txaDados.setText(s);
		model.fireTableDataChanged();		
	}

	public class TableModel extends AbstractTableModel{

		private ArrayList<Entidade> valores;
		private double maior, menor;
		private int min = 0, max = 0;
		private int classe;
		ArrayList<Integer> num;
		private int i = 0;

		private String[] colunas = new String[]{  
				"Classe","f","F","fr","Fr","Xi","Xi x Fi","Xi^2","Xi^2 x f"};

		/** Creates a new instance of DevmediaTableModel */
		public TableModel(ArrayList<Entidade> valores) {
			this.valores = valores;
			//	       Collections.sort(valores);
		}

		public int getRowCount() {
			return (int) Math.round( ( (double) (1+3.33*Math.log10(Calculo.total(valores)))));
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

		public void setValueAt(Entidade aValue, int rowIndex) {  
			Entidade usuario = valores.get(rowIndex);

			usuario = aValue;

			fireTableCellUpdated(rowIndex, 0);  
			fireTableCellUpdated(rowIndex, 1);  
			fireTableCellUpdated(rowIndex, 2);  
		}

		@Override  
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
			double usuario = Double.parseDouble(valores.get(rowIndex).getDado());

			switch (columnIndex) 
			{
			//	     case 0: //classe 
			//	    	 int min = 0 , max = 1;
			//	    	 
			//	    	 usuario = 0;
			//	    	 
			//	    	 break;
			case 1:  
				double valor = 0;
				min = 0; max = 1;
				for(Entidade e : valores)
				{
					min = max;
					if(i*2 < num.size())
					{
						max = num.get(i*2);
						System.out.println("max: "+max);
					}
					else
					{
						i = 0;
						max = 0;
						min = 0;
					}

					if(Double.parseDouble(e.getDado()) >= min && Double.parseDouble(e.getDado()) < max)
						valor++;
				}
				i++;
				usuario = valor;
				break;
			case 2:
				double soma = Double.parseDouble((String) getValueAt(rowIndex, 1));
				if(rowIndex-1 < valores.size() && rowIndex != 0 && columnIndex != 1)
				{
					soma += Double.parseDouble((String) getValueAt(rowIndex-1, 2));
				}

				usuario = soma;
				break;

			default:  
				System.err.println("Índice da coluna inválido");
			}  
			fireTableCellUpdated(rowIndex, columnIndex);  
		}      

		public void gerarMinMax(String linha)
		{
			linha = linha.replace(" ", "");
			System.out.println("Classe: "+linha);
			
			if(linha != null)
			{
				StringTokenizer token = new StringTokenizer(linha, "-");
				
				while(token.hasMoreElements()){
					min = Integer.parseInt(token.nextToken());
					max = min + classe;
				}
			}
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) throws NumberFormatException {
			Entidade usuarioSelecionado = valores.get(rowIndex);
			String valueObject = null;

			switch(columnIndex){
			case 0:    
				if(rowIndex-1 >= 0 && rowIndex != 0)
				{					
//					String linha = ((String)(getValueAt(rowIndex-1, 0)));
//					linha = linha.replace(" ", "");
//					System.out.println("Classe: "+linha);
//					
//					if(linha != null)
//					{
//						StringTokenizer token = new StringTokenizer(linha, "-");
//						
//						while(token.hasMoreElements()){
//							min = Integer.parseInt(token.nextToken());
//							max = min + classe;
//						}
//					}
//					min = Integer.parseInt(((String)(getValueAt(rowIndex-1, 0))).substring(4).trim());
//					max = min+classe;
					
					gerarMinMax(((String)(getValueAt(rowIndex-1, 0))));
				}
				else
				{
					min = 0;
					max = classe;
				}
				valueObject = min+" - "+max;
				break;
			case 1:
				double valor = 0;
				min = 0; max = classe;
				for(Entidade e : valores)
				{
					if(rowIndex-1 >= 0 && rowIndex != 0)
					{
						gerarMinMax(((String)(getValueAt(rowIndex-1, 0))));
//						min = Integer.parseInt(((String)(getValueAt(rowIndex-1, 0))).substring(4).trim());
//						max = classe+min;
					}
					if(Double.parseDouble(e.getDado()) >= min && Double.parseDouble(e.getDado()) < max)
						valor++;
				}
				valueObject = valor+""; 
				break;
			case 2:
				double soma = Double.parseDouble((String) getValueAt(rowIndex, 1));
				if(rowIndex-1 >= 0 && rowIndex != 0)
				{
					soma += Double.parseDouble((String) getValueAt(rowIndex-1, 2));
				}

				valueObject = soma+"";
				break;
			case 3:
				//	    	   double total = Double.parseDouble(((String) getValueAt(getRowCount(), 2)).trim());

				double total = 0;
				for(Entidade e : valores)
					total = Double.parseDouble(e.getDado());
				double dado = Double.parseDouble((String) getValueAt(rowIndex, 1));
				if(dado != 0)
					valueObject = (dado/total)+"";
				else
					valueObject = 0+"";

				break;
			case 4:
				double soma1 = Double.parseDouble((String) getValueAt(rowIndex, 3));				
				if(rowIndex-1 >= 0 && rowIndex != 0)
				{
					soma1 += Double.parseDouble((String) getValueAt(rowIndex-1, 4));
				}

				valueObject = soma1+"";
				break;
			case 5:
				if(rowIndex - 1 >= 0)
				{
					min = Integer.parseInt(((String)(getValueAt(rowIndex-1, 0))).substring(4).trim());
					max = min+classe;	    		   
				}
				double xi = ((double) (max + min)/2); 
				valueObject = xi+"";
				break;
			case 6:
				double f1 = Double.parseDouble((String) getValueAt(rowIndex, 1));
				double x1 = Double.parseDouble((String) getValueAt(rowIndex, 5));
				valueObject = (f1*x1)+"";
				break;
			case 7:
				double x = Double.parseDouble((String) getValueAt(rowIndex, 5));
				double xi2 = x*x; 
				valueObject = xi2+"";
				break;
			case 8:
				double f2 = Double.parseDouble((String) getValueAt(rowIndex, 1));
				double x2 = Double.parseDouble((String) getValueAt(rowIndex, 7));
				valueObject = (f2*x2)+"";
				break;
			default: System.err.println("Índice inválido para propriedade do bean Usuario.class");
			}

			return valueObject;
		}		

		@Override  
		public boolean isCellEditable(int rowIndex, int columnIndex) {  
			return false;  
		}  


		public Entidade getUsuario(int indiceLinha) {  
			return valores.get(indiceLinha);  
		}  

		public void addUsuario(Entidade u) {      
			valores.add(u);  

			int ultimoIndice = getRowCount() - 1;  

			fireTableRowsInserted(ultimoIndice, ultimoIndice);  
		}  


		public void removeUsuario(int indiceLinha) {  
			valores.remove(indiceLinha);  

			fireTableRowsDeleted(indiceLinha, indiceLinha);  
		}  


		public void addListaDeUsuarios(ArrayList<Entidade> novosUsuarios) {  

			int tamanhoAntigo = getRowCount(); 
			
			valores = Dados.getInstance().concatenarListas(valores, novosUsuarios);
			
			fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
		}  

		public void limpar() {  
			valores.clear();    
			fireTableDataChanged();  
		}  

		public boolean isEmpty() {  
			return valores.isEmpty();  
		}

		public ArrayList<Entidade> getValores() {
			return valores;
		}

		public void setValores(ArrayList<Entidade> valores) {
			this.valores = valores;
		}  
		
}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JTextField getTfdAdd() {
		return tfdAdd;
	}

	public JTextArea getTxaDados() {
		return txaDados;
	}

	public JTable getTabela() {
		return tabela;
	}

	public TableModel getModel() {
		return model;
	}

	public JScrollPane getScpPanel() {
		return scpPanel;
	}

}
