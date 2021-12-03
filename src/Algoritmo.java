import java.util.Random;

public class Algoritmo {
	
	private double solucao;
	private double taxaMutacao;
	private double taxaCruzamento;
	private double min;
	private double max;
	
	public Algoritmo(double min, double max, double taxaMutacao, double taxaCruzamento) {
		this.min= min;
		this.max= max;
		this.taxaMutacao= taxaMutacao;
		this.taxaCruzamento= taxaCruzamento;
		//Fácil -> f(x1,x2,x3,x4,x5) = 2*x1 + x2+ 3*x3 + x4 - x5
		//Difícil -> f(x1,x2,x3,x4,x5) = 3*ln(x1) + 1/lnx2 -x3³ + 2*x4 - x5
		
		//this.solucao= (3*Math.log(this.max)) + (1/Math.log(this.max)) + Math.pow(this.max, 3) + 2*this.max - this.max;
		this.solucao= 2*this.max + this.max + 3*this.max + this.max - this.min;
	}
	
	public Populacao novaPopulacao(Populacao anterior, boolean flagElitismo) {
		Random random = new Random();
		Populacao novaPopulacao = new Populacao(anterior.getTamanho());
		anterior.bubbleSort();
		
		if(flagElitismo) {
			novaPopulacao.addIndividuo(anterior.getIndividuo(0));
			novaPopulacao.addIndividuo(anterior.getIndividuo(1));
			novaPopulacao.addIndividuo(anterior.getIndividuo(2));
		}
		
		while(novaPopulacao.getNumeroIndividuo() < novaPopulacao.getTamanho()) {
			Individuo[] pais = torneio(anterior);
			Individuo[] filhos = new Individuo[2];
			
			if(random.nextDouble() <= this.taxaCruzamento) {
				filhos= cruzamento(pais[0], pais[1]);
			}
			else {
				filhos[0]= new Individuo(this.min, this.max, pais[0].getValores(), this.taxaMutacao);
				filhos[1]= new Individuo(this.min, this.max, pais[1].getValores(), this.taxaMutacao);
			}
			novaPopulacao.addIndividuo(filhos[0]);
			novaPopulacao.addIndividuo(filhos[1]);
			
		}
		
		novaPopulacao.bubbleSort();	
		return novaPopulacao;
	}
	
	public Individuo[] cruzamento(Individuo pai, Individuo mae) {
		Individuo[] filhos = new Individuo[2];
		double[] valores1 = new double[5];
		double[] valores2 = new double[5];
		Random random = new Random();
		
		for(int i=0; i < valores1.length; i++) {
			int aleatorio = random.nextInt(); //1 - Pai / 0 - Mae
			if(aleatorio == 1) {
				valores1[i]= pai.getValores()[i];
				valores2[i]= mae.getValores()[i];
			}
			else {
				valores1[i]= mae.getValores()[i];
				valores2[i]= pai.getValores()[i];
			}
		}
		filhos[0] = new Individuo(this.min, this.max, valores1, this.taxaMutacao);
		filhos[1] = new Individuo(this.min, this.max, valores2, this.taxaMutacao);
		
		return filhos;
	}
	
	
	public Individuo[] torneio(Populacao populacao) {
		Random random = new Random();
		Populacao aux = new Populacao(2);
		Populacao aux2 = new Populacao(2);
		
		aux.addIndividuo(populacao.getIndividuo(random.nextInt(populacao.getTamanho())));
		aux.addIndividuo(populacao.getIndividuo(random.nextInt(populacao.getTamanho())));
		aux.bubbleSort();
		
		aux2.addIndividuo(populacao.getIndividuo(random.nextInt(populacao.getTamanho())));
		aux2.addIndividuo(populacao.getIndividuo(random.nextInt(populacao.getTamanho())));
		aux2.bubbleSort();
		
		Individuo[] melhores = new Individuo[2];
		
		melhores[0]= aux.getIndividuo(0);
		melhores[1]= aux2.getIndividuo(0);
		
		return melhores;
	}

	public double getSolucao() {
		return solucao;
	}

	public void setSolucao(double solucao) {
		this.solucao = solucao;
	}

	public double getTaxaMutacao() {
		return taxaMutacao;
	}

	public void setTaxaMutacao(double taxaMutacao) {
		this.taxaMutacao = taxaMutacao;
	}

	public double getTaxaCruzamento() {
		return taxaCruzamento;
	}

	public void setTaxaCruzamento(double taxaCruzamento) {
		this.taxaCruzamento = taxaCruzamento;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

}
