package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//classes do jogo
import genius.Campeonato;
import genius.Jogo;
import genius.Jogador;
import genius.Jogo.Status;

import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.event.ChangeListener;

import controller.Botao;
import javax.swing.event.ChangeEvent;
import java.lang.Thread;  


public class InterfaceGrafica extends JFrame implements Jogo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Jogador jogadorDaVez;
	public static int respostaJogador = NEUTRO;
	Campeonato g = new Campeonato();
	public Timer timer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGrafica frame = new InterfaceGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
			
	@SuppressWarnings("deprecation")
	public InterfaceGrafica() {
		initGUI();
	}
	private void initGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 800);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 880, 760);
		contentPane.add(tabbedPane);
		
		//painel jogo
		JPanel panel_Genius = new JPanel();
		tabbedPane.addTab("Genius", null, panel_Genius, null);
		panel_Genius.setToolTipText("Vamos lá!!");
		panel_Genius.setLayout(null);
		
		JTextArea textArea_geniusResumoJogador = new JTextArea();
		textArea_geniusResumoJogador.setBounds(288, 358, 304, 53);
		panel_Genius.add(textArea_geniusResumoJogador);
		
		//DESTAQUE BOTAO
		JLabel destaqueCor = new JLabel();		
		destaqueCor.setOpaque(true);		
		destaqueCor.setBackground(new Color(255, 255, 255));		
		destaqueCor.setBounds(10, 10, 410, 300); //inicia na posicao Verde
		destaqueCor.setVisible(false);
		panel_Genius.add(destaqueCor);		
		
		//INICIAR
		Botao Button_Comecar = new Botao("INICIAR");
		Button_Comecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				try {
					JOptionPane.showMessageDialog(Button_Comecar, "Preparem-se!!");
					Button_Comecar.iniciar(g, destaqueCor);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//SALVAR
		Botao btnSalvar = new Botao("Salvar");
		btnSalvar.setBounds(292, 321, 78, 25);
		panel_Genius.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSalvar.salvarJogo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//CARREGAR
		Botao btnCarregar = new Botao("Carregar");
		btnCarregar.setBounds(495, 321, 97, 25);
		panel_Genius.add(btnCarregar);
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCarregar.carregarJogo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Button_Comecar.setFont(new Font("Dialog", Font.PLAIN, 16));
		Button_Comecar.setBounds(375, 309, 115, 45);
		panel_Genius.add(Button_Comecar);
		
		//BOTAO VERDE
		Botao btnVerde = new Botao();
		btnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					btnVerde.tocarSfx(genius.Jogo.VERDE);					
					
				} catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				respostaJogador = VERDE ;				
				try {
					btnVerde.tocarSfx(respostaJogador);
					btnVerde.verificarPartida(g, btnVerde, respostaJogador, textArea_geniusResumoJogador, destaqueCor );
				} catch (InterruptedException | UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				respostaJogador = NEUTRO;
			}
		});
		btnVerde.setBackground(new Color(75, 255, 50));
		btnVerde.setBounds(10, 10, 410, 300);
		panel_Genius.add(btnVerde);
		
		//BOTAO VERMELHO
		Botao btnVermelho = new Botao();
		btnVermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respostaJogador = VERMELHO ;				
				try {
					btnVermelho.tocarSfx(respostaJogador);
					btnVermelho.verificarPartida(g, btnVermelho, respostaJogador, textArea_geniusResumoJogador, destaqueCor);					
				} catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				respostaJogador= NEUTRO;			
			}
		});
		btnVermelho.setBackground(new Color(255, 75, 50));
		btnVermelho.setBounds(460, 10, 410, 300);
		panel_Genius.add(btnVermelho);
		
		//BOTAO AMARELO
		Botao btnAmarelo = new Botao();
		btnAmarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respostaJogador = AMARELO ;
				try {
					btnAmarelo.tocarSfx(respostaJogador);
					btnAmarelo.verificarPartida(g, btnAmarelo, respostaJogador, textArea_geniusResumoJogador, destaqueCor);					
				} catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				respostaJogador= NEUTRO;
			}
		});
		btnAmarelo.setBackground(new Color(255, 255, 50));
		btnAmarelo.setBounds(10, 420, 410, 300);
		panel_Genius.add(btnAmarelo);
		
		//BOTAO AZUL
		Botao btnAzul = new Botao();
		btnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respostaJogador = AZUL;
				try {
					btnAzul.tocarSfx(respostaJogador);
					btnAzul.verificarPartida(g, btnAzul, respostaJogador, textArea_geniusResumoJogador, destaqueCor);
					
				} catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				respostaJogador = NEUTRO;
			}
		});
		btnAzul.setBackground(new Color(50, 75, 255));
		btnAzul.setBounds(460, 420, 410, 300);
		panel_Genius.add(btnAzul);
		
		//DIFICULDADE
		JLabel lbl_escolhaDificuldade = new JLabel("New label");
		lbl_escolhaDificuldade.setBounds(71, 392, 137, 16);
		panel_Genius.add(lbl_escolhaDificuldade);
		lbl_escolhaDificuldade.setText("Grau de Dificuldade: 1" );
		
		JSlider slider_Dificuldade = new JSlider(1, 3, 1);
		slider_Dificuldade.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lbl_escolhaDificuldade.setText("Grau de Dificuldade: " + slider_Dificuldade.getValue());
				g.setDificuldade(slider_Dificuldade.getValue());				
			}
		});
		slider_Dificuldade.setMinimum(1);
		slider_Dificuldade.setMaximum(3);
		slider_Dificuldade.setBounds(20, 322, 231, 67);
		panel_Genius.add(slider_Dificuldade);
		slider_Dificuldade.setPaintTrack(true);
		slider_Dificuldade.setPaintTicks(true);
		slider_Dificuldade.setPaintLabels(true);
		slider_Dificuldade.setMajorTickSpacing(1);
		//slider_Dificuldade.setMinorTickSpacing(1);
		
		//VELOCIDADE
		JLabel lbl_escolhaVelocidade = new JLabel("New label");
		lbl_escolhaVelocidade.setBounds(664, 395, 159, 16);
		panel_Genius.add(lbl_escolhaVelocidade);
		lbl_escolhaVelocidade.setText("Grau de Velocidade: 1" );
		
		JSlider slider_Velocidade = new JSlider(1, 3, 1);
		slider_Velocidade.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lbl_escolhaVelocidade.setText("Grau de Velocidade: " + slider_Velocidade.getValue());		
				g.setVelocidade(slider_Dificuldade.getValue());
			}
		});
		slider_Velocidade.setMinimum(1);
		slider_Velocidade.setMaximum(3);
		slider_Velocidade.setBounds(612, 333, 246, 44);
		panel_Genius.add(slider_Velocidade);	
		slider_Velocidade.setPaintTrack(true);
		slider_Velocidade.setPaintTicks(true);
		slider_Velocidade.setPaintLabels(true);
		slider_Velocidade.setMajorTickSpacing(1);
		btnVerde.setBackground(new Color(75, 255, 50));
	}

}//fim
