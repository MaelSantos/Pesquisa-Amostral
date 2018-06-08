package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import model.Amostra;
import model.BancoDeDados;
import model.Calculo;
import model.Entidade;
import model.Verificar;
import view.Cadastro;
import view.Graficos;
import view.Histograma;
import view.Marcas;
import view.Mensagem;
import view.Menu;
import view.Qualitativo;
import view.Quantitativo;
import view.Tabela;
import view.Visualizador;

public class Controle implements ActionListener{

	private Menu menu;
	private Qualitativo qualitativo;
	private Quantitativo quantitativo;
	private Cadastro cadastro;
	private Visualizador visualizador;
	private Marcas marcas;
	private Graficos graficos;
	private Tabela tabela;
	private Histograma histograma;
	
	public Controle(Menu menu, Qualitativo qualitativo, Quantitativo quantitativo, Cadastro cadastro, Visualizador visualizador, 
			Marcas marcas, Graficos  graficos, Tabela tabela, Histograma histograma) {
		
		this.menu = menu;
		this.qualitativo = qualitativo;
		this.quantitativo = quantitativo;
		this.cadastro = cadastro;
		this.visualizador = visualizador;
		this.marcas = marcas;
		this.graficos = graficos;
		this.tabela = tabela;
		this.histograma = histograma;
		
		menu.getBtnQualitativos().addActionListener(this);
		menu.getBtnQuantitativos().addActionListener(this);
		menu.getBtnSair().addActionListener(this);
		
		qualitativo.getMniAdd().addActionListener(this);
		qualitativo.getMniVisualizar().addActionListener(this);
		qualitativo.getMniSair().addActionListener(this);
		qualitativo.getMniMarcas().addActionListener(this);
		qualitativo.getMniGraficos().addActionListener(this);
		cadastro.getBtnAdd().addActionListener(this);
		
		quantitativo.getMniTabela().addActionListener(this);
		quantitativo.getMniHistograma().addActionListener(this);
		quantitativo.getMniSair().addActionListener(this);
		
		tabela.getBtnAdd().addActionListener(this);
		
		visualizador.getBtnAmostra().addActionListener(this);
		
	}
	
	public void mudarTela(JPanel abrir)
	{		
		if(visualizador.isVisible())
			visualizador.setVisible(false);
		else if(cadastro.isVisible())
			cadastro.setVisible(false);
		else if(marcas.isVisible())
			marcas.setVisible(false);
		else if(graficos.isVisible())
			graficos.setVisible(false);
		
		abrir.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menu.getBtnQualitativos())
		{
			qualitativo.setVisible(true);
			menu.setVisible(false);
		}
		if(e.getSource() == menu.getBtnQuantitativos())
		{
			quantitativo.setVisible(true);
			menu.setVisible(false);
		}
		if(e.getSource() == qualitativo.getMniAdd())
		{
			mudarTela(cadastro);
		}
		if(e.getSource() == qualitativo.getMniVisualizar())
		{
			visualizador.getTbmPopulacao().addListaDeUsuarios(BancoDeDados.entidades);		
			mudarTela(visualizador);
		}
		if(e.getSource() == qualitativo.getMniMarcas())
		{
			mudarTela(marcas);
		}
		if(e.getSource() == qualitativo.getMniGraficos())
		{
			mudarTela(graficos);
		}
		if(e.getSource() == cadastro.getBtnAdd())
		{
			if(Verificar.VeriifcarCadastro(cadastro))
			{
				if((BancoDeDados.adicionar(new Entidade(cadastro.getTfdNome().getText(),
						cadastro.getCbxPesquisa().getSelectedItem().toString(),
						cadastro.getQuantidade()+1))))
				{
					System.out.println("atualizando...");
					cadastro.setQuantidade(cadastro.getQuantidade() + 1);
					cadastro.getLblQuantidade().setText("Quantidade: "+cadastro.getQuantidade());
					visualizador.getLblQuantidade().setText("Quantidade: "+cadastro.getQuantidade());
					Calculo.organizarLista(Arrays.asList(BancoDeDados.marcasNomes), 1);
					marcas.atualizar();
					graficos.atualizarDados();
				}
				else
				{
					Mensagem.exibirMensagem("Entidade Ja Existente - Falha ao Adicionar !!!");
				}
				
			}
			else
			{
				Mensagem.exibirMensagem("Preencha Todos Os Campos!!!");
			}
		}
		if(e.getSource() == visualizador.getBtnAmostra())
		{
			if(Verificar.VeriifcarPorcentagem(visualizador))
			{
				if(visualizador.getCbxAmostras().getSelectedIndex() == 0)
				{
					visualizador.getTbmAmostra().limpar();
					visualizador.getTbmAmostra().addListaDeUsuarios((Amostra.AmostraAleatoria(
							Integer.parseInt(visualizador.getFtxtPorcentagem().getText()),BancoDeDados.entidades)));								
				}
				else if(visualizador.getCbxAmostras().getSelectedIndex() == 1)
				{
					visualizador.getTbmAmostra().limpar();
					visualizador.getTbmAmostra().addListaDeUsuarios(
							(Amostra.AmostraSistematica(
									Integer.parseInt(visualizador.getFtxtPorcentagem().getText()),BancoDeDados.entidades)));								
				}
				else if(visualizador.getCbxAmostras().getSelectedIndex() == 2)
				{
					visualizador.getTbmAmostra().limpar();
					visualizador.getTbmAmostra().addListaDeUsuarios(
							(Amostra.AmostraEstratificada(
							Integer.parseInt(visualizador.getFtxtPorcentagem().getText()),BancoDeDados.entidades)));			
				}
			}
			else
			{
				Mensagem.exibirMensagem("Defina Uma Porcentagem Para Tirar Amostra!!!");
			}
		}
		if(e.getSource() == qualitativo.getMniSair())
		{
			qualitativo.setVisible(false);
			menu.setVisible(true);
		}
		if(e.getSource() == quantitativo.getMniSair())
		{
			quantitativo.setVisible(false);
			menu.setVisible(true);
		}
		if(e.getSource() == quantitativo.getMniTabela())
		{
			tabela.setVisible(true);
			histograma.setVisible(false);
		}
		if(e.getSource() == quantitativo.getMniHistograma())
		{
			histograma.setVisible(true);
			tabela.setVisible(false);
		}
		if(e.getSource() == tabela.getBtnAdd())
		{
			BancoDeDados.adicionar(Double.parseDouble(tabela.getTfdAdd().getText().trim()));
			tabela.getTxaDados().setText(BancoDeDados.valores.toString());
			tabela.atualizar();
			histograma.atualizarDados();
		}
		if(e.getSource() == menu.getBtnSair())
		{
			System.exit(0);
		}
	}

}
