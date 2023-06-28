package genius;

public interface Jogo {
	//status de jogo
	 enum Status { ACERTOU,
		 						CONTINUA, 
		 						EMPATE,
		 						FIM_DE_JOGO,
		 						PROXIMO_JOGADOR,
		 						JOGADOR1,
		 						JOGADOR2 } ;	
	 
	//configurações da partida 
	final int CONSTANTE_DIFICULDADE = 7;
	final int CONSTANTE_VELOCIDADE = 1050;	
	
	 //configurações de jogo
	 final int NEUTRO = 0;
	 final int VERDE  = 1;
	final int VERMELHO = 2;
	final int AMARELO = 3;
	final int AZUL = 4;	

}//fim
