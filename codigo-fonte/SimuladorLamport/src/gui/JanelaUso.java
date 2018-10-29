package gui;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;



public class JanelaUso extends JFrame 
{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane textArea;

	
	public JanelaUso() 
	{
		setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		setTitle("Funcionamento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		textArea = new JEditorPane();
		textArea.setContentType("text/html");
		textArea.setEditable(false);
		textArea.setText("<div style=\"font-family: monospace; font-size: 12;\">\r\n\r\n<p>    Esse programa foi desenvolvido para simular o algoritmo de Lamport para ordenar eventos (mensagens) entre processos de um sistema distribuído usando relógios lógicos. </p>\r\n\r\n<p>    Não espere encontrar algoritmos explícitos nesse programa. Ele é simples, e apenas simula o algoritmo de Lamport aplicado à um ambiente distribuídos hipotético, constituído por processos (que, por se tratar de um sistema distribuído, podem ou não pertencer ao mesmo computador ou à computadores diferentes), que se comunicam através de mensagens. Esse comunicação é feita por envio/recebimento de mensagens, que pode ser controlado pelo usuário através de um controle do tipo passo-a-passo.</p>\r\n\r\n<p>    O simulador possui um painel de controle (lado esquerdo) por onde o usuário pode escolher a quantidade de processos a constituir o sistema, a intensidade de mensagens enviadas/recebidas e a duração com que essas mensagens demoram para serem enviadas. É impossível criar um ambiente de simulação perfeito, e sabemos disso. Portanto, tentamos possibilitar ao usuário uma experiência mais realista possível. Isso significa que, mesmo o usuário escolhendo os parâmetros de simulação, os valores reais de criação do ambiente de simulação são criados pseudo-aleatoriamente, com base nos parâmetros informados. Após a escolha dos parâmetros de simulação, o usuário deve pressionar o botão de <b>Simular</b>, situado abaixo do controle de Duração. Isso fará com que o ambiente de configuração seja criado corretamente, apresentando os processos, indicados por <b>pN</b>, sendo N o identificador do processo, e seus campos de \"tarefas\". A partir desse momento, os controles de criação da simulação são desabilitados e os controles de passo-a-passo são habilitados. Para realizar uma simulação com outros parâmetros, o usuário deve pressionar o botão <b>Cancelar</b>.</p>\n\n<p>    Os controles de passo-a-passo são similares aos controles de um rádio antigo. Eles permitem você avançar ou recuar um passo, avançar ou recuar todos os passos, ou então apreciar uma execução automática, através do botão <i>play</i>, que vai executando paulatinamente os passos seguintes ao passo atual do estado de execução. O <i>play</i> também pode ser configurado, podendo executar mais rápido ou mais devagar os passos, de acordo com o controle de <b>Velocidade</b>, que está abaixo dos controles de passo-a-passo. Quando está em modo de execução automática, através do <i>play</i>, a simulação só é interrompida quando chega ao final ou através do botão de <i>pause</i>. </p>\n\n<p>    As mensagens enviadas entre os processos são representadas por uma setinha com uma miniatura de uma carta (em alusão à um pacote de dados em rede), onde as verdes significam que a mensagem foi enviada pelo processo que está mais próxima, e as vermelhas representam recebimento. Quando uma mensagem é enviada/recebida, uma linha temporária é desenhada entre essas representações para representar a transmissão/recebimento. As representação do envio/recebimento (setinhas) são alinhadas com a respectiva tarefas de onde saiu ou para onde foi.</p>\n\n<p>    Processos que enviam mensagens (que não sejam respostas à mensagens recebidas) para outros processos, são destacados com a cor amarelo. Conforme as mensagens são enviadas, as tarefas já processadas dos processos são coloridas na cor cinza. Para facilitar na simulação, as mensagens (setinhas) identificam se uma mensagem foi enviada ou recebida, bastando passar o cursor do <i>mouse</i> sobre ela; mostram também o processo emissor e receptor, e o tempo de saída do processo emissor. Se o tempo de saída do emissor for maior do que o de chegada do receptor, o contador de tarefas do receptor é atualizado, com base no tempo recebido na mensagem. Para toda mensagem enviada por um processo, uma resposta é recebida pelo destinatário, no passo seguinte. A ordem em que os processos enviam mensagens também é pseudo-aleatório, simulando um escalonador de tarefas. E isso é tudo que é simulado! </p>\n\n<br>\r\n\r\nSaiba mais em: <a href=\"http://www.di.ubi.pt/~pprata/sdtf/SDTF_10_11_T03_Ordenacao.pdf\">UBI</a><br>\r\n\r\n</div>");
		textArea.setCaretPosition(0);
		textArea.addHyperlinkListener(new HyperlinkListener() 
		{
		    public void hyperlinkUpdate(HyperlinkEvent e) 
		    {
		        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
		        	try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} 
		        	catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
		        }
		    }
		});
		scrollPane.setViewportView(textArea);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setVisible(false);
	}

	
	public void exibir()
	{
		textArea.setCaretPosition(0);
		setLocationRelativeTo(null);
		setVisible(true);
	}


}