package controle;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import model.Amostra;
import model.Dados;
import model.Calculo;
import model.Entidade;
import model.Verificar;
import view.Mensagem;
import view.Qualitativo;
import view.Quantitativo;

public class ControleQualitativo extends Controle{

	private static ControleQualitativo instance;
	private Qualitativo qualitativo;


	private ControleQualitativo()
	{
		qualitativo = new Qualitativo();
		init(qualitativo);

		qualitativo.getMniAdd().addActionListener(this);
		qualitativo.getMniGraficos().addActionListener(this);
		qualitativo.getMniMarcas().addActionListener(this);
		qualitativo.getMniSair().addActionListener(this);
		qualitativo.getMniVisualizar().addActionListener(this);
		qualitativo.getCadastro().getBtnAdd().addActionListener(this);

		qualitativo.getVisualizador().getBtnAmostra().addActionListener(this);

	}

	public static ControleQualitativo getInstance()
	{
		if(instance == null)
			instance = new ControleQualitativo();

		return instance;
	}

	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if(obj == qualitativo.getMniAdd())
		{
			mudarTela(qualitativo.getCadastro());
		}
		if(obj == qualitativo.getMniVisualizar())
		{
			mudarTela(qualitativo.getVisualizador());
		}
		if(obj == qualitativo.getMniMarcas())
		{
			mudarTela(qualitativo.getMarcas());
		}
		if(obj == qualitativo.getMniGraficos())
		{
			mudarTela(qualitativo.getGraficos());
		}

		if(e.getSource() == qualitativo.getCadastro().getBtnAdd())
		{
			if(Verificar.VeriifcarCadastro(qualitativo.getCadastro()))
			{
				Entidade entidade = new Entidade(qualitativo.getCadastro().getTfdNome().getText(),
						qualitativo.getCadastro().getCbxPesquisa().getSelectedItem().toString(),
						qualitativo.getCadastro().getQuantidade()+1);

				if((Dados.getInstance().addDado(entidade,"res/pesquisas.xml")))
				{
					System.out.println("atualizando...");
					qualitativo.getCadastro().setQuantidade(qualitativo.getCadastro().getQuantidade() + 1);
					qualitativo.getCadastro().getLblQuantidade().setText("Quantidade: "+qualitativo.getCadastro().getQuantidade());

					qualitativo.getVisualizador().atualizar();
					qualitativo.getMarcas().atualizar();
					qualitativo.getGraficos().atualizar();
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
		if(e.getSource() == qualitativo.getVisualizador().getBtnAmostra())
		{
			if(Verificar.VeriifcarPorcentagem(qualitativo.getVisualizador()))
			{
				if(qualitativo.getVisualizador().getCbxAmostras().getSelectedIndex() == 0)
				{
					qualitativo.getVisualizador().getTbmAmostra().limpar();
					qualitativo.getVisualizador().getTbmAmostra().addListaDeUsuarios((
							Amostra.AmostraAleatoria(Integer.parseInt(qualitativo.getVisualizador().getFtxtPorcentagem().getText()),
									Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())));								
				}
				else if(qualitativo.getVisualizador().getCbxAmostras().getSelectedIndex() == 1)
				{
					qualitativo.getVisualizador().getTbmAmostra().limpar();
					qualitativo.getVisualizador().getTbmAmostra().addListaDeUsuarios(
							(Amostra.AmostraSistematica(
									Integer.parseInt(qualitativo.getVisualizador().getFtxtPorcentagem().getText()),
									Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())));								
				}
				else if(qualitativo.getVisualizador().getCbxAmostras().getSelectedIndex() == 2)
				{
					if(!(Integer.parseInt(qualitativo.getVisualizador().getFtxtPorcentagem().getText()) >
					Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size()))
					{
						qualitativo.getVisualizador().getTbmAmostra().limpar();
						qualitativo.getVisualizador().getTbmAmostra().addListaDeUsuarios(
								(Amostra.AmostraEstratificada(
										Integer.parseInt(qualitativo.getVisualizador().getFtxtPorcentagem().getText()),
										Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())));									
					}
					else
						Mensagem.exibirMensagem("Quantidade excede numero da população");
				}

				qualitativo.getVisualizador().getTbmAmostra().fireTableDataChanged();
			}
			else
			{
				Mensagem.exibirMensagem("Defina Uma Porcentagem Para Tirar Amostra!!!");
			}

		}
		if(e.getSource() == qualitativo.getMniSair())
		{
			qualitativo.setVisible(false);
			ControleMenu.getInstance().getMenu().setVisible(true);
		}

		}
		public Qualitativo getQualitativo() {
			return qualitativo;
		}

	}
