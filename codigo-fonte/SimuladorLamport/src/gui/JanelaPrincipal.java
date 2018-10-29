package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import lamport.Lamport;
import lamport.Processo;



public class JanelaPrincipal extends JFrame 
{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_1;
	private Lamport lamport;
	private Thread thread;
	private boolean pause;
	
	
	
	public JanelaPrincipal()
	{
		final JanelaCreditos janelaCreditos = new JanelaCreditos();
		final JanelaUso janelaUso = new JanelaUso();
		final JanelaSobreLamport janelaSobreLamport = new JanelaSobreLamport();
		lamport = new Lamport();
		setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
		setTitle("Simulador do Algoritmo de Lamport");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 542);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu mnSobre = new JMenu("Informações");
		mnSobre.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre...");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				janelaSobreLamport.exibir();
			}
		});
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mntmSobre.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		mnSobre.add(mntmSobre);
		
		JMenuItem mntmFuncionamento = new JMenuItem("Funcionamento");
		mntmFuncionamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				janelaUso.exibir();
			}
		});
		mntmFuncionamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
		mntmFuncionamento.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		mnSobre.add(mntmFuncionamento);
		
		JMenuItem mntmCrditos = new JMenuItem("Créditos");
		mntmCrditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				janelaCreditos.exibir();
			}
		});
		mntmCrditos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmCrditos.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		mnSobre.add(mntmCrditos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE))
					.addGap(27))
		);
		
		final JButton button = new JButton("<");
		button.setEnabled(false);
		button.setToolTipText("Voltar");
		button.setFont(new Font("DejaVu Serif", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (lamport.getPassoAtual() >= 0) {
					//Removendo o passo atual:
					panel_1.remove(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissao());
					panel_1.remove(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRecepcao());
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i < lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().size(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setText( (Integer.valueOf(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getText()) - lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getDiferenca())+"" );
					}
					
					lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get( lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor() ).setBackground(Color.WHITE);;
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor() - 1; i >= 0; i--) {
						if (!lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).getEnviouOuRecebeu()) lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).setBackground(Color.WHITE);
						else break;
					}
					
					lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get( lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor() ).setBackground(Color.WHITE);;
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor() - 1; i >= 0; i--) {
						if (!lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getEnviouOuRecebeu()) lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setBackground(Color.WHITE);
						else break;
					}
					
					lamport.setPassoAtual(lamport.getPassoAtual()-1);
					
					revalidate();
					repaint();
				}
			}
		});
		
		final JButton button_1 = new JButton(">");
		button_1.setEnabled(false);
		button_1.setToolTipText("Avançar");
		button_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 13));
		button_1.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (lamport.getPassoAtual() < lamport.getOrdemEnvio().size()-1) {
					lamport.setPassoAtual(lamport.getPassoAtual()+1);
					panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissao());
					panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRecepcao());
					((Graphics2D) panel_1.getGraphics()).draw(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRota());
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i < lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().size(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setText( (Integer.valueOf(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getText()) + lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getDiferenca()) +"" );
					}
					
					//Tarefas já executadas:
					for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
					}
					
					for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
					}
					
					revalidate();
					repaint();
				}
			}
		});
		
		final JLabel lblProcessos = new JLabel("Processos");
		lblProcessos.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		
		final JSlider slider = new JSlider();
		
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		JLabel aux = new JLabel("Baixo");
		
		aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (1, aux);
	    aux = new JLabel("Médio");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (10, aux);
	    aux = new JLabel("Alto");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (20, aux);
	    slider.setLabelTable (table);
		
	    slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(4);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setMaximum(20);
		slider.setUI(new Slider(slider));
		
		final JLabel lblMensagens = new JLabel("Mensagens");
		lblMensagens.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		
		final JSlider slider_1 = new JSlider();
		
		table = new Hashtable<Integer, JLabel>();
		aux = new JLabel("Baixo");
		aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (1, aux);
	    aux = new JLabel("Médio");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (10, aux);
	    aux = new JLabel("Alto");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (20, aux);
	    slider_1.setLabelTable (table);
		
		slider_1.setUI(new Slider(slider_1));
		slider_1.setValue(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setMinorTickSpacing(1);
		slider_1.setMaximum(10);
		slider_1.setMajorTickSpacing(5);
		
		final JLabel lblDurao = new JLabel("Duração");
		lblDurao.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		
		final JSlider slider_2 = new JSlider();
		
		table = new Hashtable<Integer, JLabel>();
		aux = new JLabel("Lento");
		aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (1, aux);
	    aux = new JLabel("Médio");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (10, aux);
	    aux = new JLabel("Rápido");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (20, aux);
	    slider_2.setLabelTable (table);
		
		slider_2.setUI(new Slider(slider_2));
		
		slider_2.setValue(2);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		slider_2.setMinorTickSpacing(1);
		slider_2.setMaximum(10);
		slider_2.setMajorTickSpacing(5);
		
		final JButton btnSimular = new JButton("Simular");
		btnSimular.setForeground(new Color(255, 255, 255));
		btnSimular.setBackground(new Color(0, 0, 255));
		btnSimular.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		
		final JButton button_3 = new JButton("|<<");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				while (lamport.getPassoAtual() >= 0) {
					//Removendo o passo atual:
					panel_1.remove(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissao());
					panel_1.remove(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRecepcao());
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i < lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().size(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setText( (Integer.valueOf(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getText()) - lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getDiferenca())+"" );
					}
					
					lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get( lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor() ).setBackground(Color.WHITE);;
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor() - 1; i >= 0; i--) {
						if (!lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).getEnviouOuRecebeu()) lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).setBackground(Color.WHITE);
						else break;
					}
					
					lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get( lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor() ).setBackground(Color.WHITE);;
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor() - 1; i >= 0; i--) {
						if (!lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getEnviouOuRecebeu()) lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setBackground(Color.WHITE);
						else break;
					}
					
					lamport.setPassoAtual(lamport.getPassoAtual()-1);
				}
				
				revalidate();
				repaint();
			}
		});
		button_3.setEnabled(false);
		button_3.setToolTipText("Recomeçar");
		button_3.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		
		final JLabel lblControle = new JLabel("Controle");
		lblControle.setEnabled(false);
		lblControle.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		
		final JButton button_4 = new JButton(">>|");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				while (lamport.getPassoAtual() < lamport.getOrdemEnvio().size()-1) {
					//Adicionando os passos que faltam:
					lamport.setPassoAtual(lamport.getPassoAtual()+1);
					panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissao());
					panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRecepcao());
					((Graphics2D) panel_1.getGraphics()).draw(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRota());
					
					for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i < lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().size(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setText( (Integer.valueOf(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getText()) + lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getDiferenca()) +"" );
					}
					
					//Tarefas já executadas:
					for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
					}
					
					for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i++) {
						lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
					}
					
					revalidate();
					repaint();
				}
			}
		});
		button_4.setEnabled(false);
		button_4.setToolTipText("Terminar");
		button_4.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		
		final JLabel lblVelocidade = new JLabel("Velocidade");
		lblVelocidade.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		lblVelocidade.setEnabled(false);
		
		final JSlider slider_3 = new JSlider();
		slider_3.setEnabled(false);
		
		table = new Hashtable<Integer, JLabel>();
		aux = new JLabel("Lento");
		aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (0, aux);
	    aux = new JLabel("Médio");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (50, aux);
	    aux = new JLabel("Rápido");
	    aux.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
	    table.put (100, aux);
	    slider_3.setLabelTable(table);
		
		slider_3.setUI(new Slider(slider_3));
		
		slider_3.setValue(2);
		slider_3.setPaintTicks(true);
		slider_3.setPaintLabels(true);
		slider_3.setMinorTickSpacing(5);
		slider_3.setMajorTickSpacing(50);
		
		final JButton button_2 = new JButton("|>");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (lamport.getPassoAtual() < lamport.getOrdemEnvio().size()-1) {
					button_2.setSelected(true);
					button.setEnabled(false);
					button_1.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					slider_3.setEnabled(false);
					lblVelocidade.setEnabled(false);
					
					thread = new Thread()
					{
						public void run() 
						{
							pause = false;
							
							while (lamport.getPassoAtual() < (lamport.getOrdemEnvio().size()-1) && !pause) {
								//Adicionando os passos que faltam:
								lamport.setPassoAtual(lamport.getPassoAtual()+1);
								panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissao());
								panel_1.add(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRecepcao());
								((Graphics2D) panel_1.getGraphics()).draw(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getRota());
								
								for (int i = lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i < lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().size(); i++) {
									lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setText( (Integer.valueOf(lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).getText()) + lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getDiferenca()) +"" );
								}
								
								//Tarefas já executadas:
								for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxEmissor(); i++) {
									lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getEmissor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
								}
								
								for (int i = 0; i <= lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getIdxReceptor(); i++) {
									lamport.getOrdemEnvio().get(lamport.getPassoAtual()).getReceptor().getTarefas().get(i).setBackground(Color.LIGHT_GRAY);
								}
								
								revalidate();
								repaint();
								
								try {
									sleep((long) (1000.0/(slider_3.getValue()+1)));
								} 
								catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							
							button_2.setSelected(false);
							button.setEnabled(true);
							button_1.setEnabled(true);
							button_3.setEnabled(true);
							button_4.setEnabled(true);
							slider_3.setEnabled(true);
							lblVelocidade.setEnabled(true);
						}
					};
						
					thread.start();
				}
			}
		});
		button_2.setEnabled(false);
		button_2.setToolTipText("Executar");
		button_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 16));
		
		final JButton button_5 = new JButton("||");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				button_2.setSelected(false);
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_3.setEnabled(true);
				button_4.setEnabled(true);
				slider_3.setEnabled(true);
				lblVelocidade.setEnabled(true);
				pause = true;
			}
		});
		
		button_5.setEnabled(false);
		button_5.setToolTipText("Pausar");
		button_5.setFont(new Font("DejaVu Serif", Font.PLAIN, 14));
		
		btnSimular.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (lblControle.isEnabled()) {
					button_2.setSelected(false);
					pause = true;
					
					//Habilitando os controles dos parâmetros de simulação:
					lblDurao.setEnabled(true);
					lblMensagens.setEnabled(true);
					lblProcessos.setEnabled(true);
					slider.setEnabled(true);
					slider_1.setEnabled(true);
					slider_2.setEnabled(true);
					
					//Desabilitando controles da simulação:
					lblControle.setEnabled(false);
					button.setEnabled(false);
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					slider_3.setEnabled(false);
					lblVelocidade.setEnabled(false);
					
					panel_1 = new JPanel();
					panel_1.setLayout(null);
					scrollPane.setViewportView(panel_1);
					btnSimular.setText("Simular");
				}
				else {
					//Desabilitando os controles dos parâmetros de simulação:
					lblDurao.setEnabled(false);
					lblMensagens.setEnabled(false);
					lblProcessos.setEnabled(false);
					slider.setEnabled(false);
					slider_1.setEnabled(false);
					slider_2.setEnabled(false);
					
					//Habilitando controles da simulação:
					lblControle.setEnabled(true);
					button.setEnabled(true);
					button_1.setEnabled(true);
					button_2.setEnabled(true);
					button_3.setEnabled(true);
					button_4.setEnabled(true);
					button_5.setEnabled(true);
					slider_3.setEnabled(true);
					lblVelocidade.setEnabled(true);
					
					panel_1 = new JPanel();
					panel_1.setLayout(null);
					scrollPane.setViewportView(panel_1);
					criarProcessos(slider.getValue()+2, slider_1.getValue()+2, slider_2.getValue()+2);
					btnSimular.setText("Cancelar");
				}
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProcessos)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMensagens)
						.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDurao)
						.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(34)
							.addComponent(btnSimular, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblControle)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button_3)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(13)
									.addComponent(button)))
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(12)
									.addComponent(button_5))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(button_4)
										.addComponent(button_1)))))
						.addComponent(lblVelocidade)
						.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblProcessos)
					.addGap(10)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblMensagens)
					.addGap(6)
					.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblDurao)
					.addGap(6)
					.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSimular)
					.addGap(18)
					.addComponent(lblControle)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button_3)
							.addGap(5)
							.addComponent(button))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button_4)
							.addGap(5)
							.addComponent(button_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_5)
					.addGap(18)
					.addComponent(lblVelocidade, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		panel.setLayout(gl_panel);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		scrollPane.setViewportView(panel_1);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public void criarProcessos(int processos, int mensagens, int duracao)
	{
		lamport.setProcessos(processos);
		lamport.simular(mensagens, duracao);
		
		Dimension d = panel_1.getPreferredSize();
		
		for (Processo p : lamport.getProcessos()) {
			panel_1.add(p.getNome());
			
			for (JTextField t : p.getTarefas()) {
				panel_1.add(t);
				
				//Redimensionamento automático:
				if (t.getBounds().x+t.getBounds().width + 20 > d.width) d.width = t.getBounds().x+t.getBounds().width + 10;
				if (t.getBounds().y+t.getBounds().height + 20 > d.height) d.height = t.getBounds().y+t.getBounds().height + 20;
			}
		}
		
		panel_1.setPreferredSize(d);
	}


}