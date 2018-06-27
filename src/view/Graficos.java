package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarAxisLocation;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import model.Dados;
import model.Entidade;

public class Graficos extends PanelGenerico {


	private JComboBox<String> cbxGraficos;
	private JFreeChart fctPizza, fctColunas, fctBarras, fctPolar;
	private ChartPanel ctpGraficos;
	private DefaultCategoryDataset dtsCategory;
	private DefaultPieDataset dtsPie;
	private XYSeriesCollection xySeries;
	private JLabel lblTitulo;
	private String titulo = "Marcas De Celular";
	double salto;
	
	public Graficos() {
		super("Graficos");
		// TODO Stub de construtor gerado automaticamente
	}
	
	@SuppressWarnings("serial")
	@Override
	public void inicializar() {
		 
		lblTitulo = new JLabel(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTitulo());
		salto = ((double)360/Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size());
		
		dtsPie = new DefaultPieDataset();
		dtsCategory = new DefaultCategoryDataset();
		xySeries = new XYSeriesCollection();
		atualizar();
	
		ValueAxis radiusAxis = new NumberAxis();
		radiusAxis.setTickLabelsVisible(true);
		
		final DefaultPolarItemRenderer render = new DefaultPolarItemRenderer();
		render.setFillComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		
		PolarPlot polar = new PolarPlot(xySeries, radiusAxis, render) {
			
			@Override
			protected List<NumberTick> refreshAngleTicks() {
				
				List<NumberTick> ticks = new ArrayList<NumberTick>(); 
				
				for(int i = 0; i < Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().size(); i++)
				{
					if(i < Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().size()/2)
					{
						ticks.add(new NumberTick(i*salto, 
								Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i), 
								TextAnchor.BASELINE_LEFT, 
								TextAnchor.TOP_CENTER,
								0.0));
					}
					else
					{
						ticks.add(new NumberTick(i*salto, 
								Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i), 
								TextAnchor.TOP_RIGHT, 
								TextAnchor.TOP_CENTER,
								0.0));						
					}
					render.setSeriesFilled(i, true);
				}
				
				return ticks;
			}
		};
		polar.setBackgroundPaint(Color.WHITE); //fundo
		polar.setAngleGridlinePaint(Color.BLACK);//linha direçao ao nome
		polar.setRadiusMinorGridlinesVisible(false);//linhas de decoração invisiveis
		polar.setRadiusGridlinePaint(Color.RED);
		polar.setAxisLocation(PolarAxisLocation.NORTH_LEFT);
		
		fctColunas = ChartFactory.createBarChart3D(titulo, 
				"Marca", 
				"Quantidade",
				dtsCategory);
		
		fctBarras = ChartFactory.createBarChart3D(titulo,
				"Marca", 
				"Quantidade", 
				dtsCategory, 
				PlotOrientation.HORIZONTAL, true, true, false);
		
		//Cria um objeto JFreeChart passando os seguintes parametros
		fctPizza = ChartFactory.createPieChart(
				titulo, //Titulo do grafico
				dtsPie, //DataSet
				true, //Para mostrar ou não a legenda
				true, //Para mostrar ou não os tooltips
				true);
		
		fctPolar = new JFreeChart(titulo, JFreeChart.DEFAULT_TITLE_FONT, polar, false);
		
		ctpGraficos = new ChartPanel(fctColunas, 
				570, 370, 
				300, 300, 
				450, 450, 
				true, true, true, true, true, false);
		
		
		String[] nomes = {"Coluna", "Barras", "Pizza", "Polar"};
		cbxGraficos = new JComboBox<String>(nomes);
		cbxGraficos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				if(cbxGraficos.getSelectedIndex() == 0)
				{	
					ctpGraficos.setChart(fctColunas);
					System.out.println("Coluna");
				}
				
				if(cbxGraficos.getSelectedIndex() == 1)
				{
					ctpGraficos.setChart(fctBarras);
					System.out.println("Barras");
				}
				
				if(cbxGraficos.getSelectedIndex() == 2)
				{
					ctpGraficos.setChart(fctPizza);
					System.out.println("Pizza");
				}
				if(cbxGraficos.getSelectedIndex() == 3)
				{
					ctpGraficos.setChart(fctPolar);
					System.out.println("Polar");
				}
				ctpGraficos.setMouseZoomable(false);
			}
		});
				
		
		add(cbxGraficos);
		add(lblTitulo);
		add(ctpGraficos);
		
	}

	public void atualizar()
	{
		lblTitulo.setText(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTitulo());
		
		xySeries.removeAllSeries();
		dtsCategory.clear();
		dtsPie.clear();
		
		double total = Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades().size();
		double porcent = 0;
			
		ArrayList<String> ocorrencias = new ArrayList<String>();
		for(Entidade e: Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades())
			ocorrencias.add(e.getDado());
			
		//Adiciona os dados ao dataSet deve somar um total de 100%
		for (int i = 0; i < Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().size(); i++)
        {
			XYSeries series = new XYSeries(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i));
			
            int quant = Collections.frequency(ocorrencias, 
            		Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i));
//            System.out.println(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i)+" recebeu "+ quant+"votos ");
            porcent = (quant*100)/total;
            
			dtsPie.setValue(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i), porcent);
			dtsCategory.setValue(quant, 
					Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i), 
					Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i));
			
			if(i+1 >= Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().size())
			{
				series.add(i*salto, quant);
				series.add(0, 0);
				series.add(0*salto, quant);				
			}
			else
			{
				int prox = Collections.frequency(ocorrencias, 
						Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getTipos().get(i+1));
				series.add(i*salto, quant);
				series.add((i+1)*salto, prox);
				series.add(0, 0);
			}
			
			xySeries.addSeries(series);
        }
		
				
	}
	
	public JComboBox<String> getCbxGraficos() {
		return cbxGraficos;
	}

}
