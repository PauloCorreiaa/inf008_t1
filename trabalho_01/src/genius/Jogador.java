package genius;

import java.util.ArrayList;

public class Jogador implements Jogo{
	//atributos
	private String nome;
	private double totalPontos = 0;	 
	private long tempoInicial;
	private long tempoFinal;
	private ArrayList <Long> listaTempoRodada;
	
	//construtores
	public Jogador(String nome) {		
		this.nome = nome;
		this.totalPontos = 0;
		this.listaTempoRodada = new ArrayList<Long>();
	}
	
	public Jogador() {
		this.totalPontos = 0;
		this.listaTempoRodada = new ArrayList<Long>();
	}

	//métodos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getTotalPontos() {
		return totalPontos;
	}

	public void setTotalPontos(double totalPontos) {
		this.totalPontos += totalPontos;
	}

	public long getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial() {
		this.tempoInicial = System.currentTimeMillis();
	}

	public long getTempoFinal() {
		return tempoFinal;
	}
	
	public void setTempoFinal() {
		this.tempoFinal = System.currentTimeMillis() - tempoInicial;
	}


	public ArrayList<Long> getListaTempoRodada() {
		return listaTempoRodada;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public String toStringJogador() {
		return this.nome;
	}

}//fim
