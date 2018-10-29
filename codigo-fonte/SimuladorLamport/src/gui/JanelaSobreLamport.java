package gui;

import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;



public class JanelaSobreLamport extends JDialog 
{

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane txtrAlanMathisonTuring;

	
	public JanelaSobreLamport() 
	{
		setFont(new Font("DejaVu Serif", Font.PLAIN, 12));
		setModal(true);
		setTitle("Sobre...");
		setBounds(100, 100, 436, 285);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel fotoTuring = new JLabel("");
		fotoTuring.setIcon(new ImageIcon(JanelaSobreLamport.class.getResource("/imagens/lamport.gif")));
		fotoTuring.setToolTipText("Leslie Lamport");
		
		JLabel lblAlan = new JLabel("Leslie Lamport");
		lblAlan.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtrAlanMathisonTuring = new JEditorPane();
		txtrAlanMathisonTuring.setContentType("text/html");
		txtrAlanMathisonTuring.setEditable(false);
		txtrAlanMathisonTuring.setText("<div style=\"font-family: monospace; font-size: 12;\">\r\n\r\n<p>    Leslie B. Lamport (nascido em 07 de fevereiro de 1941, em Nova York) é um cientista da computação estadunidense. Lamport é mais conhecido por seu trabalho seminal em sistemas distribuídos e como o desenvolvedor inicial do LaTeX, sistema de preparação de documentos. <p><br>\n\n<p>    Leslie Lamport foi o vencedor do Prêmio Turing de 2013, por impôr coerência clara e bem definida sobre o aparentemente comportamento caótico de sistemas de computação distribuída, em que vários computadores autônomos se comunicam uns com os outros, passando mensagens. Ele desenvolveu algoritmos importantes e desenvolvidos de modelagem e verificação de protocolos formais que melhorem a qualidade dos sistemas distribuídos reais. Essas contribuições resultaram na melhoria da exatidão, desempenho e confiabilidade de sistemas de computador.</p><br>\r\n\r\nSaiba mais em: <a href=\"http://en.wikipedia.org/wiki/Leslie_Lamport\">Wikipédia</a><br>\r\n\r\nImagem extraída de: <a href=\"http://www.nae.edu/File.aspx?id=33649\"> NAE Website </a>\r\n\r\n</div>");
		txtrAlanMathisonTuring.setCaretPosition(0); 
		txtrAlanMathisonTuring.setFont(new Font("Monospaced", Font.PLAIN, 11));
		scrollPane.setViewportView(txtrAlanMathisonTuring);
		txtrAlanMathisonTuring.addHyperlinkListener(new HyperlinkListener() 
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
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fotoTuring))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblAlan)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblAlan)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(fotoTuring)
							.addGap(0))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setVisible(false);
	}


	public void exibir()
	{
		txtrAlanMathisonTuring.setCaretPosition(0);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
}