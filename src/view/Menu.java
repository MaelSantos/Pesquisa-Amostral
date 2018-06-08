package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Menu extends FrameGenerico{

	private JButton btnQualitativos, btnQuantitativos, btnSair;
	private JLabel lblEscolha;
	
	public Menu() {
		super();
		setSize(230, 200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		inicializar();
	}
	
	@Override
	public void inicializar() {
		
		btnQualitativos = new JButton("Dados Qualitativos");
		btnQuantitativos = new JButton("Dados Quantitativos");
		btnSair = new JButton("Sair");
		
		lblEscolha = new JLabel("Escolha Qual Tipo De Dados Deseja:");
		
		add(lblEscolha);
		add(btnQualitativos);
		add(btnQuantitativos);
		add(btnSair);
		
		setVisible(true);
		
	}

	//metodos de acesso
	public JButton getBtnQualitativos() {
		return btnQualitativos;
	}

	public JButton getBtnQuantitativos() {
		return btnQuantitativos;
	}

	public JButton getBtnSair() {
		return btnSair;
	}

}
