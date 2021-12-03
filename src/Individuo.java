import java.util.Random;

public class Individuo {

	private double[] valores; //Genes
	private double fitness;

	public Individuo(double min, double max) {
		this.valores = new double[5];
		for (int i = 0; i < this.valores.length; i++) {
			this.valores[i] = getRandom(min, max);
		}
		this.fitness = this.calcFunction();
	}


	public Individuo(double min, double max, double[] valores, double taxaMutacao) {
		this.valores = valores;
		Random r = new Random();

		if (r.nextDouble() <= taxaMutacao) {
			int pos = r.nextInt(this.valores.length);
			for (int i = 0; i < this.valores.length; i++) {
				if (pos == i) {
					this.valores[i] = getRandom(min, max);
					break;
				}
			}
		}
		this.fitness = this.calcFunction();
	}

	private double calcFunction() {
		// Fácil -> f(x1,x2,x3,x4,x5) = 2*x1 + x2+ 3*x3 + x4 - x5
		// Difícil -> f(x1,x2,x3,x4,x5) = 3*ln(x1) + 1/ln(x2) -x3³ + 2*x4 - x5
		double result= 2*this.valores[0] + this.valores[1] + 3*this.valores[2] + this.valores[3] - this.valores[4];
		/*double result = (3 * Math.log(this.valores[0])) + (1 / Math.log(this.valores[1])) + Math.pow(this.valores[2], 3)
				+ 2 * this.valores[3] - this.valores[4];*/
		return result;
	}

	public double[] getValores() {
		return valores;
	}

	public void setValores(double[] valores) {
		this.valores = valores;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	private double getRandom(double min, double max) {
		Random random = new Random();
		return random.nextInt((int) max - (int) min) + min;
	}

	public String valores() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.valores.length; i++) {
			sb.append(this.valores[i]).append(" ");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Valores: ");
		for (int i = 0; i < this.valores.length; i++) {
			sb.append(this.valores[i]).append(" ");
		}
		sb.append("\n");
		sb.append("Fitness: ").append(this.fitness).append("\n");
		return sb.toString();
	}
}
