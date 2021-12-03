import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o valor m�nimo do intervalo de busca: ");
		double min = input.nextDouble();
		System.out.println("Informe o valor m�ximo do intervalo de busca: ");
		double max = input.nextDouble();
		System.out.println("Informe a taxa de muta��o: Ex -> 10");
		double mutacao= input.nextDouble() / 100;
		System.out.println("Informe a taxa de cruzamento: Ex -> 90");
		double cruzamento= input.nextDouble() / 100;
		System.out.println("Informe o tamanho da popula��o");
		int tamanho= input.nextInt();
		System.out.println("Informe o n�mero de gera��es: ");
		int numGeracao= input.nextInt();
		boolean flagElitismo= true;
		
		//double min, double max, double taxaMutacao, double taxaCruzamento
		Algoritmo algoritmo = new Algoritmo(min, max, mutacao, cruzamento);
		
		Populacao populacao = new Populacao(tamanho, min, max);
		int qtd= 0;
		
		System.out.println("Fitness do melhor individuo da popula��o atual: "+populacao.getMelhorIndividuo().getFitness());
		
		do {
			qtd++;
			populacao = algoritmo.novaPopulacao(populacao, flagElitismo);
			System.out.println("Melhor individuo: ");
			System.out.println("Gera��o: "+qtd+" | Fitness: "+populacao.getMelhorIndividuo().getFitness()+" | Valores: "+populacao.getMelhorIndividuo().valores());
		}while((qtd < numGeracao) || populacao.getMelhorIndividuo().getFitness() == algoritmo.getSolucao());
		
		System.out.println("Fim das gera��es! Valor da solu��o: "+algoritmo.getSolucao());
		input.close();
	}
	
}
