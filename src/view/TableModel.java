package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Dados;
import model.Entidade;

public class TableModel extends AbstractTableModel{

	private ArrayList<Entidade> entidades;
	private String[] colunas = new String[]{  
			"Nome","Pesquisa"};

	/** Creates a new instance of DevmediaTableModel */
	public TableModel(ArrayList<Entidade> usuarios) {
		this.entidades = usuarios;
	}

	public TableModel(){
		this.entidades = new ArrayList<Entidade>();
	}

	public int getRowCount() {
		return entidades.size();
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
		Entidade usuario = entidades.get(rowIndex);

		usuario.setNome(aValue.getNome());
		usuario.setDado(aValue.getDado());        
		usuario.setIndice(aValue.getIndice());

		fireTableCellUpdated(rowIndex, 0);  
		fireTableCellUpdated(rowIndex, 1);  
		fireTableCellUpdated(rowIndex, 2);  
	}

	@Override  
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
		Entidade usuario = entidades.get(rowIndex);

		switch (columnIndex) 
		{
		case 0:  
			usuario.setNome(aValue.toString());             
		case 1:  
			usuario.setDado(aValue.toString());             
		case 2:  
			usuario.setIndice(Integer.parseInt(aValue.toString()));

		default:  
			System.err.println("Índice da coluna inválido");
		}  
		fireTableCellUpdated(rowIndex, columnIndex);  
	}      


	public Object getValueAt(int rowIndex, int columnIndex) {
		Entidade usuarioSelecionado = entidades.get(rowIndex);
		String valueObject = null;
		switch(columnIndex){
		case 0: valueObject = usuarioSelecionado.getNome(); break;
		case 1: valueObject = usuarioSelecionado.getDado(); break;
		case 2: valueObject = usuarioSelecionado.getIndice()+""; break;
		default: System.err.println("Índice inválido para propriedade do bean Usuario.class");
		}

		return valueObject;
	}

	@Override  
	public boolean isCellEditable(int rowIndex, int columnIndex) {  
		return false;  
	}  


	public Entidade getUsuario(int indiceLinha) {  
		return entidades.get(indiceLinha);  
	}  

	public void addUsuario(Entidade u) {      
		entidades.add(u);  

		int ultimoIndice = getRowCount() - 1;  

		fireTableRowsInserted(ultimoIndice, ultimoIndice);  
	}  


	public void removeUsuario(int indiceLinha) {  
		entidades.remove(indiceLinha);  

		fireTableRowsDeleted(indiceLinha, indiceLinha);  
	}  


	public void addListaDeUsuarios(ArrayList<Entidade> novosUsuarios) {  

		int tamanhoAntigo = getRowCount();
		if(entidades.isEmpty())
			entidades.addAll(novosUsuarios);
		else
			entidades = Dados.getInstance().concatenarListas(entidades, novosUsuarios);
		fireTableDataChanged();
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);  
	}

	public void limpar() {  
		entidades.clear();
		fireTableDataChanged();  
	}  

	public boolean isEmpty() {  
		return entidades.isEmpty();  
	}

	public ArrayList<Entidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(ArrayList<Entidade> entidades) {
		this.entidades = entidades;
	}  

}

