package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.BancoDeDados;
import model.Calculo;

public class Tabela extends PanelGenerico {

	private JButton btnAdd;
	private JTextField tfdAdd;
	private JTextArea txaDados;
	private JTable tabela;
	private TableModel  model;
	private JScrollPane scpPanel;
	private JLabel lblTitulo, lblMedia, lblModa, lblMediana, lblQuartil, lblPercentil, lblTotal;
	
	public Tabela() {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
	}
	
	@Override
	public void inicializar() {

		model = new TableModel(BancoDeDados.valores);
		tabela = new JTable(model);
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 143));

		btnAdd = new JButton("Add");
		tfdAdd = new JTextField(10);
		txaDados = new JTextArea(BancoDeDados.valores.toString());
		
		scpPanel = new JScrollPane(tabela);
				
		lblTitulo = new JLabel(BancoDeDados.titulo);
		lblTotal = new JLabel("Total: "+Calculo.total(BancoDeDados.valores));
		lblMedia = new JLabel("Media: "+Calculo.media(BancoDeDados.valores));
		lblModa = new JLabel("Moda: "+Calculo.moda(BancoDeDados.valores));
		lblMediana = new JLabel("Mediana: "+Calculo.mediana(BancoDeDados.valores));
		lblQuartil = new JLabel("Quatil: "+Calculo.quartil(BancoDeDados.valores));
		lblPercentil = new JLabel("Percentil: "+Calculo.percentil(BancoDeDados.valores,10));
		
		add(lblTitulo);
		add(btnAdd);
		add(tfdAdd);
		add(new JScrollPane(txaDados)).setPreferredSize(new Dimension(200, 100));;
		add(scpPanel);
		add(lblTotal);
		add(lblMedia);
		add(lblModa);
		add(lblMediana);
		add(lblQuartil);
		add(lblPercentil);
	}

	public void atualizar()
	{
		lblTotal.setText("Total: "+Calculo.total(BancoDeDados.valores));
		lblMedia.setText("Media: "+Calculo.media(BancoDeDados.valores));
		lblModa.setText("Moda: "+Calculo.moda(BancoDeDados.valores));
		lblMediana.setText("Mediana: "+Calculo.mediana(BancoDeDados.valores));
		lblQuartil.setText("Quatil: "+Calculo.quartil(BancoDeDados.valores));
		lblPercentil.setText("Percentil: "+Calculo.percentil(BancoDeDados.valores,10));
	}
	
	private class TableModel extends AbstractTableModel{

	    private ArrayList<Double> valores;
	    private double maior, menor;
	    private int min = 0, max = 0;
	    private int classe;
	    ArrayList<Integer> num;
	    private int i = 0;
	    
	    private String[] colunas = new String[]{  
	       "Classe","f","F","fr","Fr","Xi","Xi x Fi","Xi^2","Xi^2 x f"};
	   
	   /** Creates a new instance of DevmediaTableModel */
	   public TableModel(ArrayList<Double> valores) {
	       this.valores = valores;
	       Collections.sort(valores);
	       maior = Calculo.maior(valores);
		   menor = Calculo.menor(valores);
		   classe = Calculo.classes(valores);
		   num = Calculo.minmax(valores);
	   }
	   
	   public int getRowCount() {
	       return (int) Math.round( ( (double) (1+3.33*Math.log10(valores.size()))));
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
	 
	   public void setValueAt(Double aValue, int rowIndex) {  
	       double usuario = valores.get(rowIndex);
	       
	       usuario = aValue;

	       fireTableCellUpdated(rowIndex, 0);  
	       fireTableCellUpdated(rowIndex, 1);  
	       fireTableCellUpdated(rowIndex, 2);  
	   }
	   
	   @Override  
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	     double usuario = valores.get(rowIndex);
	    
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
	    	   for(double e : valores)
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

	    		   if(e >= min && e < max)
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
	   
	   public Object getValueAt(int rowIndex, int columnIndex) {
		   double usuarioSelecionado = valores.get(rowIndex);
	       String valueObject = null;
	      
	       switch(columnIndex){
	       case 0:    
	    	   if(rowIndex-1 >= 0 && rowIndex != 0)
	    	   {
	    		   min = Integer.parseInt(((String)(getValueAt(rowIndex-1, 0))).substring(4).trim());
	    		   max = min+classe;
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
	    	   for(double e : valores)
	    	   {
		    	   if(rowIndex-1 >= 0 && rowIndex != 0)
		    	   {
		    		   min = Integer.parseInt(((String)(getValueAt(rowIndex-1, 0))).substring(4).trim());
		    		   max = classe+min;
		    	   }
	    		   if(e >= min && e < max)
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
	    	   double total = Double.parseDouble(((String) getValueAt(getRowCount()-3, 2)).trim());
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
	 
	 
	   public double getUsuario(int indiceLinha) {  
	       return valores.get(indiceLinha);  
	   }  
	   
	   public void addUsuario(double u) {      
	       valores.add(u);  

	       int ultimoIndice = getRowCount() - 1;  
	 
	       fireTableRowsInserted(ultimoIndice, ultimoIndice);  
	   }  
	 
	   
	   public void removeUsuario(int indiceLinha) {  
	       valores.remove(indiceLinha);  
	 
	       fireTableRowsDeleted(indiceLinha, indiceLinha);  
	   }  
	   
	   
	   public void addListaDeUsuarios(ArrayList<Double> novosUsuarios) {  
	       
	       int tamanhoAntigo = getRowCount();
	       valores.addAll(novosUsuarios);    
	       fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);  
	   }  
	   
	   public void limpar() {  
	       valores.clear();    
	       fireTableDataChanged();  
	   }  
	 
	   public boolean isEmpty() {  
	       return valores.isEmpty();  
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
