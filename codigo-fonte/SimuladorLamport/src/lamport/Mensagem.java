package lamport;

import java.awt.Font;
import java.awt.geom.QuadCurve2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Mensagem 
{
	
	
	private JLabel emissao;
	private JLabel recepcao;
	private QuadCurve2D rota;
	private Processo emissor;
	private Processo receptor;
	private int idxEmissor;
	private int idxReceptor;
	private int tempoReceptor;
	private int diferenca;
	
	
	public Mensagem(Processo emissor, Processo receptor)
	{
		int tempoEmissor = Integer.valueOf(emissor.getTarefas().get(emissor.getTarefas().size()-1).getText())+1;
		
		this.emissor = emissor;
		this.receptor = receptor;
		idxEmissor = emissor.getTarefas().size()-1;
		idxReceptor = receptor.getTarefas().size()-1;
		emissao = new JLabel();    //Tempo: tempo de sáída do emissor.
		emissao.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
		emissao.setIcon(new ImageIcon(getClass().getResource("/imagens/send.png")));
		emissao.setToolTipText("<html>enviado<br>de: " + emissor.getNome().getText() + "<br>para: " + receptor.getNome().getText() + "<br>tempo: " + tempoEmissor + "</html>");
		recepcao = new JLabel();
		recepcao.setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
		recepcao.setIcon(new ImageIcon(getClass().getResource("/imagens/receive.png")));
		recepcao.setToolTipText("<html>recebido<br>de: " + emissor.getNome().getText() + "<br>para: " + receptor.getNome().getText() + "<br>tempo: " + tempoEmissor + "</html>");
		tempoReceptor = Integer.valueOf(receptor.getTarefas().get(idxReceptor).getText());
		
		if (tempoEmissor > tempoReceptor) {
			diferenca = tempoEmissor-tempoReceptor;
			tempoReceptor = tempoEmissor;
			
			int novoTempo = tempoReceptor;
			
			for (int i = idxReceptor; i < receptor.getTarefas().size(); i++) {
				receptor.getTarefas().get(i).setText( novoTempo+"" );
				novoTempo += receptor.getIncremento();
			}
		}
		else diferenca = 0;
	}

	
	public JLabel getEmissao()
	{
		return emissao;
	}
	
	
	public JLabel getRecepcao()
	{
		return recepcao;
	}
	
	
	public void setRota(int xI, int yI, int xF, int yF)
	{
		rota = new QuadCurve2D.Double(xI, yI, xI, yI, xF, yF);
	}
	
	
	public QuadCurve2D getRota()
	{
		return rota;
	}
	
	
	public Processo getEmissor()
	{
		return emissor;
	}
	
	
	public int getIdxEmissor()
	{
		return idxEmissor;
	}
	
	
	public Processo getReceptor()
	{
		return receptor;
	}
	
	
	public int getIdxReceptor()
	{
		return idxReceptor;
	}
	
	
	public int getTempoReceptor()
	{
		return tempoReceptor;
	}
	
	
	public int getDiferenca()
	{
		return diferenca;
	}


}