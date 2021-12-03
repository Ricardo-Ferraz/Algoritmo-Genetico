public class Populacao {
	
	private Individuo[] populacao;
	private int tamanho;
	
	public Populacao(int tamanho) {
		this.tamanho= tamanho;
		
		this.populacao = new Individuo[tamanho];
		for(int i=0; i < this.populacao.length; i++) {
			this.populacao[i]= null;
		}
	}
	
	public Populacao(int tamanho, double min, double max) {
		this.tamanho= tamanho;
		this.populacao= new Individuo[tamanho];
		for(int i=0; i < this.populacao.length; i++) {
			this.populacao[i] = new Individuo(min, max);
		}
	}
	
	
	public void addIndividuo(Individuo individuo) {
		for(int i=0; i < this.populacao.length; i++) {
			if(this.populacao[i] == null) {
				this.populacao[i]= individuo;
				break;
			}
		}
	}
	
	
	public void bubbleSort() {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int i=0; i < this.populacao.length -1; i++) {
				if((this.populacao[i].getFitness() < this.populacao[i+1].getFitness())) {
					Individuo aux= this.populacao[i];
					this.populacao[i]= this.populacao[i+1];
					this.populacao[i+1]= aux;
					flag = true;
				}
			}
		}
	}
	
	public int getNumeroIndividuo() {
		int num=0;
		for(int i=0; i < this.populacao.length; i++) {
			if(this.populacao[i] != null) {
				num++;
			}
		}
		return num;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public Individuo getMelhorIndividuo() {
		return this.populacao[0];
	}
	
	public Individuo getIndividuo(int pos) {
		return this.populacao[pos];
	}
	
	public void anula() {
		for(int i=0; i < this.populacao.length; i++) {
			this.populacao[i]= null;
		}
	}
	
}
