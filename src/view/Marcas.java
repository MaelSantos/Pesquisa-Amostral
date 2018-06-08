package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.BancoDeDados;
import model.Calculo;
import model.Entidade;

public class Marcas extends PanelGenerico{

	private JTable marcas;
	private TableModelMarcas model;
	private JScrollPane scpMarcas;
	private JLabel lblTitulo, lblMedia, lblModa, lblMediana, lblQuartil, lblPercentil;
	
	@Override
	public void inicializar() {
						
		Calculo.organizarLista(Arrays.asList(BancoDeDados.marcasNomes), 1);
		
		ArrayList<String> nomeMarcas = new ArrayList<String>();
		for(String s: BancoDeDados.marcasNomes)
			nomeMarcas.add(s);
		
		Calculo.organizarLista(nomeMarcas, 1);
		model = new TableModelMarcas(nomeMarcas);
		marcas = new JTable(model);
		marcas.setPreferredScrollableViewportSize(new Dimension(FrameGenerico.LARGURA-40, (int) (nomeMarcas.size()*16.25)));
		marcas.setShowHorizontalLines(false);
		marcas.setShowVerticalLines(false);
		
		scpMarcas = new JScrollPane(marcas);
		
		lblTitulo = new JLabel(BancoDeDados.titulo);
		lblMedia = new JLabel("Media: "+Calculo.media());
		lblModa = new JLabel("Moda: "+Calculo.moda());
		lblMediana = new JLabel("Mediana: "+Calculo.mediana());
		lblQuartil = new JLabel("Quatil: "+Calculo.quartil());
		lblPercentil = new JLabel("Percentil: "+Calculo.percentil(10));
		
		add(lblTitulo);
		add(scpMarcas);
		add(lblMedia);
		add(lblModa);
		add(lblMediana);
		add(lblQuartil);
		add(lblPercentil);
	}
	
	public void atualizar()
	{
		lblMedia.setText("Media: "+Calculo.media());
		lblModa.setText("Moda: "+Calculo.moda());
		lblMediana.setText("Mediana: "+Calculo.mediana());	
		lblQuartil.setText("Quatil: "+Calculo.quartil());
		lblPercentil.setText("Percentil: "+Calculo.percentil(10));
		
		Calculo.organizarLista(model.getNomeMarcas(), 1);
	}
	
	public JTable getMarcas() {
		return marcas;
	}

	public TableModelMarcas getModel() {
		return model;
	}

	public class TableModelMarcas extends AbstractTableModel
	{
		private ArrayList<String> nomeMarcas; 
	    private String[] colunas = new String[]{ 
	       "Marca", "Quantidade", "Frequencia Acumulada"};
	   
	   /** Creates a new instance of DevmediaTableModel */
	   public TableModelMarcas(ArrayList<String> usuarios) {
	       this.nomeMarcas = usuarios;
	   }
	   
	   public TableModelMarcas() {
		// TODO Stub de construtor gerado automaticamente
	}
	  
	   public int getRowCount() {
	       return nomeMarcas.size();
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
		   
	    String usuario = nomeMarcas.get(rowIndex);
	 
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
		   String usuarioSelecionado = nomeMarcas.get(rowIndex);
	       String valueObject = null;
	       switch(columnIndex){
	       case 0: valueObject = usuarioSelecionado; break;
	       case 1:
	    	   int quant = 0;
	    	   for(Entidade e : BancoDeDados.entidades)
	    		   if(e.getMarca().equalsIgnoreCase(usuarioSelecionado))
	    			   quant += 1;
	    	   valueObject = quant+""; break;
	       case 2: 
	    	   int soma = Integer.parseInt((String) getValueAt(rowIndex, 1));
	    	   if(rowIndex-1 < nomeMarcas.size() && rowIndex != 0 && columnIndex != 1)
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
		return nomeMarcas;
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
 	
}
