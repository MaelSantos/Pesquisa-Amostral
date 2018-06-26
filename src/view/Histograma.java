package view;

import java.awt.Dimension;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import model.Dados;
import model.Entidade;
import model.Calculo;

public class Histograma extends PanelGenerico{

	private JFreeChart histograma;
	private ChartPanel panel;
	private HistogramDataset dataset;

	public Histograma() {
		super("Histograma");
	}

	@Override
	public void inicializar() {

		dataset = new HistogramDataset();

		histograma = ChartFactory.createHistogram(
				"Dados Qualitativos", 
				"Classes",
				"Frequencia",
				dataset, 
				PlotOrientation.VERTICAL,
				true, 
				true, 
				true);

		panel = new ChartPanel(histograma);
		panel.setPreferredSize(new Dimension(550, 400));
		panel.setRangeZoomable(false);
		add(panel);

	}

	public void atualizar()
	{
		pesquisa.getEntidades().clear();
		pesquisa.getEntidades().addAll(Dados.getInstance().getPesquisas().get(Dados.pesquisaAtual).getEntidades());
		
		if(!pesquisa.getEntidades().isEmpty())
		{
			ArrayList<Integer> num = Calculo.minmax(pesquisa.getEntidades());	
			ArrayList<Double> temp = new ArrayList<Double>();

			int min = 0;
			int max = 0;
			double soma;

			ArrayList<Double> n = new  ArrayList<Double>();
			for(Entidade e : pesquisa.getEntidades())
				n.add(Double.parseDouble(e.getDado()));

			System.out.println(num);
			for(int i = 0; i <= num.size(); i++)
			{
				min = max;
				if(i*2 < num.size())
					max = num.get(i*2);
				else break;
				soma = 0;
				temp.clear();

				for(double d : n)
				{
					if(d >= min && d < max)
					{
						soma++;
						if(soma != 0)
							temp.add(1.0);
					}
				}
				
				System.out.println(num);
				
				double[] dados = new double[temp.size()];
				for(int j = 0; j < dados.length; j++)
				{
					dados[j] = temp.get(j);
				}

				System.out.println(min+"-"+max);
				if(!temp.isEmpty())
				{
					if(i < dataset.getSeriesCount() && dataset.getSeriesCount() > 0)
					{
						if(dataset.getSeriesKey(i) != min+"-"+max)
							dataset.addSeries(min+"-"+max, dados, 1, (double) min, (double ) max);
					}
					else if(dataset.getSeriesCount() == 0)
						dataset.addSeries(min+"-"+max, dados, 1, (double) min, (double ) max);					
				}
	
			}
		}
	}
}
