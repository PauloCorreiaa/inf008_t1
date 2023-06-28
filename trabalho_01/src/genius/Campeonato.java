package genius;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;

import genius.Jogo.Status;

public class Campeonato implements Jogo {
	//atributos
	private Jogador jogador1;
	private Jogador jogador2;
	private int dificuldade = 1;
	private int velocidade = 1;
	private ArrayList<Integer> sequenciaRodada;
	private Status jogadorDaVez = Status.JOGADOR1;
	//private LocalDate data_partida;	
	private int posicaoSequencia = 0;
	private int limite = 0;	
	
	//construtores
	public Campeonato() {
		this.jogador1 = new Jogador("Jogador 01");
		this.jogador2 = new Jogador("Jogador 02");
		this.sequenciaRodada = new ArrayList<>();	
	}
	
	//métodos de acesso	
	public Jogador getJogadorDaVez() {
		if(jogadorDaVez == Status.JOGADOR1)
			return jogador1;
		else
			return jogador2;		
	}

	public void setJogadorDaVez(Status jogadorDaVez) {
			this.jogadorDaVez = jogadorDaVez;
	}
	
	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}
	
	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}	
	
	public ArrayList<Integer> getSequenciaRodada() {
		return sequenciaRodada;
	}

	public void setSequenciaRodada(ArrayList<Integer> sequenciaRodada) {
		this.sequenciaRodada = sequenciaRodada;
	}
	
	public int getPosicaoSequencia() {
		return posicaoSequencia;
	}	

	public void setPosicaoSequencia(int posicaoSequencia) {
		this.posicaoSequencia = posicaoSequencia;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	//métodos
	private  int gerarNumeroAleatorio() {	
		SecureRandom aleatorio = new SecureRandom();
		int num = 1 + aleatorio.nextInt(4);
		return num;
	}
	
	public void criarSequenciaRodada () {		
		int tamanhoSequenciaRodada = CONSTANTE_DIFICULDADE  * dificuldade;
		
		for(int i = 0;  i < tamanhoSequenciaRodada; i++)			
			this.getSequenciaRodada().add(i, gerarNumeroAleatorio());
	}
	
	//g de genius	
	public Jogador vencedor ( Campeonato g) {
		if(g.getJogador1().getTotalPontos() > g.getJogador2().getTotalPontos() ) 
			return g.getJogador1();		
		
		else 
			return g.getJogador2();	
	}	

	public Status gameStatus(Campeonato g) {		
		if (g.jogador1.getTotalPontos() == g.jogador2.getTotalPontos())
						return Status.EMPATE ;
		 else {
			 if(jogador1.getTotalPontos()> 0 &&  g.jogador2.getTotalPontos() == 0) 
					return  Status.PROXIMO_JOGADOR;
			 else
				 return Status.FIM_DE_JOGO;	 
		 }
					
	}
	
	public Status verificaJogada(Campeonato g, int respostaJogador) {
		int posicao = g.getPosicaoSequencia();		
		Status game;
		
		g.getJogadorDaVez().setTempoInicial();
		g.getJogadorDaVez().setTotalPontos(100);
		//JOGADOR ACERTOU
		if(respostaJogador == g.getSequenciaRodada().get(posicao) &&
			g.getLimite() <= g.getSequenciaRodada().size()) {			
				g.getJogadorDaVez().setTotalPontos(10);
				//final da sequencia
				if(posicao == g.getLimite()) {
					g.setPosicaoSequencia(0);
					g.setLimite(getLimite() + 1);					
					return Status.CONTINUA;
				}
				else {
					setPosicaoSequencia(posicao++);
					return Status.ACERTOU ;
				}
			}					
			//JOGADOR ERROU OU TERMINOU
			else {
				//verifica acerto ao final da sequencia
				if(respostaJogador == g.getSequenciaRodada().get(posicao))
					g.getJogadorDaVez().setTotalPontos(10);				
				
				//fim da rodada
				g.setPosicaoSequencia(0);
				g.setLimite(0);
				g.getJogadorDaVez().setTempoFinal();				
				
				game = g.gameStatus(g);
				
				switch(game) {					
					case PROXIMO_JOGADOR:{
						g.setJogadorDaVez(Status.JOGADOR2);
						g.getSequenciaRodada().clear();
						g.criarSequenciaRodada();
						return Status .PROXIMO_JOGADOR ;
					}						
					case EMPATE:{						
						g.setJogadorDaVez(Status.JOGADOR1);
						g = new Campeonato();
						g.criarSequenciaRodada();
						return Status.EMPATE ;
					}					
					case FIM_DE_JOGO :{
						g = new Campeonato();
						return Status.FIM_DE_JOGO ;
					}					
					default: throw new ExceptionInInitializerError();
				} 
			} 
		}
	
	public void zerarCampeonato(Campeonato g) {
		g.setLimite(0);
		g.setPosicaoSequencia(0);
		g.getJogador1().setTotalPontos(0);
		g.getJogador2().setTotalPontos(0);		
	}
	
	@Override
	public String toString() {
		return	"Total pontos de "+ jogador1 +" = " + jogador1.getTotalPontos() +							
						"\nTotal pontos de "+ jogador2 +" = " + jogador2.getTotalPontos();					
						
	}	

}//fim
