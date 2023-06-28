package controller;

import java.awt.Panel;
import java.awt.TextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioPermission;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import genius.Campeonato;
import genius.Jogador;
import genius.Jogo;

public class Botao extends JButton implements Jogo {	
	private static final long serialVersionUID = 1L;
	Campeonato campeonato;

	//construtores
	public Botao() {
		super();
		//this.novoJogo = new Campeonato();
		// TODO Auto-generated constructor stub
	}	
	
	public Botao(String nome) {
		super();
		this.setText(nome);
		//this.novoJogo = new Campeonato();
		// TODO Auto-generated constructor stub
	}
	
	//métodos
	public void tocarSfx( int cor) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException{				
		switch (cor) {
			case 1: {
				String soundFx = "sfx1.wav";    
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFx).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				break;
				}
			case 2: {
				String soundFx = "sfx2.wav";    
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFx).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				break;
			}
			case 3: {
				String soundFx = "sfx3.wav";    
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFx).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				break;
			}	
			case 4: {
				String soundFx = "sfx4.wav";    
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFx).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				break;
			}			
		}				
	}
	
	public void exibirCor(Campeonato g, JLabel jl) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		int cor = g.getSequenciaRodada().get(g.getPosicaoSequencia());		
		
		switch(cor) {
			case 1:{	
				jl.setBounds(10, 10, 410, 300);				
				jl.setVisible(true);
				tocarSfx(cor);
				Thread.sleep(CONSTANTE_VELOCIDADE * g.getVelocidade());
				jl.setVisible(false);
				break;
			}
			case 2:{				
				jl.setBounds(460, 10, 410, 300);
				jl.setVisible(true);
				tocarSfx(cor);
				Thread.sleep(CONSTANTE_VELOCIDADE * g.getVelocidade());
				jl.setVisible(false);
				break;
			}
			case 3:{
				jl.setBounds(10, 420, 410, 300);
				jl.setVisible(true);
				tocarSfx(cor);
				Thread.sleep(CONSTANTE_VELOCIDADE * g.getVelocidade());				
				jl.setVisible(false);
				break;
			}
			case 4:{
				jl.setBounds(460, 420, 410, 300);
				jl.setVisible(true);
				tocarSfx(cor);	
				Thread.sleep(CONSTANTE_VELOCIDADE * g.getVelocidade());			
				jl.setVisible(false);
				break;
			}		
		}
	}
	//getLimite= total de acerto do jogador
	public void exibirJogo(Campeonato g, JLabel jl) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		for(int i = 0; i <= g.getLimite() ; i++) {
			exibirCor(g, jl);				
		}		
	}
	
	public void iniciar(Campeonato g, JLabel jl) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		g.criarSequenciaRodada();		
		exibirJogo(g, jl);		
	}
	
	public void verificarPartida(Campeonato g, Botao botao, int respostaJogador, JTextArea jt, JLabel jl) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		//VERIFICA SE EXISTE SEQUENCIA EM CAMPEONATO				
		if (g.getSequenciaRodada().isEmpty()) {
			JOptionPane.showMessageDialog(botao, "Jogo não encontrado! Click em INICIAR.");			
		}		
		else {				
			Status jogada = g.verificaJogada(g, respostaJogador);			
			switch(jogada) {							
				case EMPATE :{
					JOptionPane.showMessageDialog( botao, "Empate.\nPróximo jogador:  " + g.getJogadorDaVez().toStringJogador() );
					jt.setText((g.toString()));
					botao.exibirJogo(g, jl);
					Thread.sleep(5000);
					jt.setText("");
					break;
				}				
				case FIM_DE_JOGO:{
					JOptionPane.showMessageDialog( botao, "Fim de Jogo!\nVencedor:  " + g.vencedor(g).toStringJogador() );			
					jt.setText((g.toString()));
					Thread.sleep(5000);
					jt.setText("");
					break;
				}				
				case PROXIMO_JOGADOR:{
					JOptionPane.showMessageDialog( botao, "Próximo jogador:  " + g.getJogadorDaVez().toStringJogador() );
					botao.exibirJogo(g, jl);
					break;
				}
				case ACERTOU:{
					botao.exibirJogo(g, jl);
					break;
				}				
				case CONTINUA:{
					botao.exibirJogo(g, jl);
					break;
				}				
			}						
		}
	}	
	
	
	//CARREGAR JOGO
	 public void carregarJogo() throws IOException {
	    // TODO Auto-generated method stub
	    JFileChooser arquivoChooser = new JFileChooser();
	    arquivoChooser.setDialogTitle("Escolha o arquivo do jogo para carregar");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto (*.txt)", "txt");
	    arquivoChooser.setFileFilter(filter);

	    int selecaoUsuario = arquivoChooser.showSaveDialog(null);

	    if (selecaoUsuario == JFileChooser.APPROVE_OPTION) {
	      try {
	        File arquivoSelecionado = arquivoChooser.getSelectedFile();
	        FileInputStream arquivoIn = new FileInputStream(arquivoSelecionado);
	        ObjectInputStream fluxoSaidaIn = new ObjectInputStream(arquivoIn);
	        campeonato = (Campeonato) fluxoSaidaIn.readObject();
	        fluxoSaidaIn.close();
	        arquivoIn.close();

	        JOptionPane.showMessageDialog(null, "Jogo carregado com sucesso!", "Carregar Jogo",
	            JOptionPane.INFORMATION_MESSAGE);
	      } catch (Exception e2) {
	        // TODO: handle exception
	        JOptionPane.showMessageDialog(null, "Erro ao carregar o jogo.", "Carregar Jogo",
	            JOptionPane.ERROR_MESSAGE);
	      }

	    } else if (selecaoUsuario == JFileChooser.CANCEL_OPTION) {
	      JOptionPane.showMessageDialog(null, "Operação de carregamento cancelada.", "Cancelar",
	          JOptionPane.WARNING_MESSAGE);
	    }
	  }

	//SALVAR
	 public void salvarJogo() throws IOException {
		    JFileChooser arquivoChooser = new JFileChooser();
		    arquivoChooser.setDialogTitle("Escolha o diretório para salvar seu jogo");
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto (*.txt)", "txt");
		    arquivoChooser.setFileFilter(filter);
		    int selecaoUsuario = arquivoChooser.showSaveDialog(null);

		    if (selecaoUsuario == JFileChooser.APPROVE_OPTION) {
		      try {
		        String caminhoArquivo = arquivoChooser.getSelectedFile().getAbsolutePath();
		        if (!caminhoArquivo.endsWith(".txt")) {
		          caminhoArquivo += ".txt";
		        }

		        FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
		        ObjectOutputStream fluxoSaida = new ObjectOutputStream(arquivo);
		        fluxoSaida.writeObject(campeonato); // Salva a instância atual do Campeonato
		        fluxoSaida.close();
		        arquivo.close();
		        JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!", "Salvar Jogo",
		            JOptionPane.INFORMATION_MESSAGE);
		      } catch (Exception e1) {
		        JOptionPane.showMessageDialog(null, "Erro ao salvar o jogo.", "Salvar Jogo", JOptionPane.ERROR_MESSAGE);
		      }
		    } else if (selecaoUsuario == JFileChooser.CANCEL_OPTION) {
		      JOptionPane.showMessageDialog(null, "Operação de salvamento cancelada.", "Cancelar",
		          JOptionPane.WARNING_MESSAGE);
		    }
		  }
	 

} //fim
	

	
	


