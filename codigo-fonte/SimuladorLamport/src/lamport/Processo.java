package lamport;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Processo 
{

	
	private int id;
	private int incremento;
	private JTextField nome;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Mensagem> mensagens;    //Mensagens a serem enviadas por esse processo.
	private int mensagensParaEnviar;
	
	
	public Processo(int id, int incremento)
	{
		this.id = id;
		this.incremento = incremento;
		nome = new JTextField("p"+id);
		nome.setFont(new Font("DejaVu Serif", Font.BOLD, 10));
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setBorder(null);
		nome.setEditable(false);
		nome.setBounds(10, 20 + id*80, 30, 20);
		tarefas = new ArrayList<Tarefa>();
		mensagens = new ArrayList<Mensagem>();
	}

	
	public void removerTarefas()
	{
		tarefas = new ArrayList<Tarefa>();
		mensagens = new ArrayList<Mensagem>();
	}
	
	
	public void addTarefas(int quantidade)
	{
		int j = tarefas.size();
		
		if (j == 0) {
			tarefas.add(new Tarefa("0"));
			tarefas.get(tarefas.size()-1).setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
			tarefas.get(tarefas.size()-1).setHorizontalAlignment(SwingConstants.CENTER);
			tarefas.get(tarefas.size()-1).setEditable(false);
			tarefas.get(tarefas.size()-1).setBackground(Color.WHITE);
			tarefas.get(tarefas.size()-1).setBounds(60, 20 + id*80, 40, 20);
			j = tarefas.size();
			--quantidade;
		}
		
		for (int i = 0; i < quantidade; i++) {
			tarefas.add(new Tarefa(""+(Integer.valueOf(tarefas.get(tarefas.size()-1).getText()) + incremento)));
			tarefas.get(tarefas.size()-1).setFont(new Font("DejaVu Serif", Font.PLAIN, 10));
			tarefas.get(tarefas.size()-1).setHorizontalAlignment(SwingConstants.CENTER);
			tarefas.get(tarefas.size()-1).setEditable(false);
			tarefas.get(tarefas.size()-1).setBackground(Color.WHITE);
			tarefas.get(tarefas.size()-1).setBounds(60 + (i+j)*40, 20 + id*80, 40, 20);
		}
	}
	
	
	public ArrayList<Tarefa> getTarefas()
	{
		return tarefas;
	}

	
	public ArrayList<Mensagem> getMensagens()
	{
		return mensagens;
	}

	
	public JTextField getNome()
	{
		return nome;
	}
	
	
	public void enviarMensagem(Processo receptor)
	{
		Mensagem novaMensagem = new Mensagem(this, receptor); 
		int xS = tarefas.get(tarefas.size()-1).getX(), yS = tarefas.get(tarefas.size()-1).getY(), xR = receptor.getTarefas().get(receptor.getTarefas().size()-1).getX(), yR = receptor.getTarefas().get(receptor.getTarefas().size()-1).getY();
		
		if (id < receptor.getId()) {
			novaMensagem.getEmissao().setBounds(xS + tarefas.get(tarefas.size()-1).getWidth()/2 - 10, yS - 20, 20, 20);
			novaMensagem.getRecepcao().setBounds(xR + tarefas.get(tarefas.size()-1).getWidth()/2 - 10, yR + tarefas.get(tarefas.size()-1).getHeight(), 20, 20);
			novaMensagem.setRota(xS + tarefas.get(tarefas.size()-1).getWidth()/2, yS + tarefas.get(tarefas.size()-1).getHeight(), xR + receptor.getTarefas().get(receptor.getTarefas().size()-1).getWidth()/2, yR);
		}
		else {
			novaMensagem.getEmissao().setBounds(xS + tarefas.get(tarefas.size()-1).getWidth()/2 - 10, yS + tarefas.get(tarefas.size()-1).getHeight(), 20, 20);
			novaMensagem.getRecepcao().setBounds(xR + tarefas.get(tarefas.size()-1).getWidth()/2 - 10, yR - 20, 20, 20);
			novaMensagem.setRota(xR + tarefas.get(tarefas.size()-1).getWidth()/2, yR + tarefas.get(tarefas.size()-1).getHeight(), xS + tarefas.get(tarefas.size()-1).getWidth()/2, yS);
		}
		
		getTarefas().get(novaMensagem.getIdxEmissor()).setEnviouOuRecebeu(true);
		receptor.getTarefas().get(novaMensagem.getIdxReceptor()).setEnviouOuRecebeu(true);
		mensagens.add(novaMensagem);    //Verificando quando a mensagem será enviada.
	}
	
	
	public int getIncremento()
	{
		return incremento;
	}
	
	
	public int getId()
	{
		return id;
	}
	
	
	public int getMensagensParaEnviar()
	{
		return mensagensParaEnviar;
	}
	
	
	public void setMensagensParaEnviar(int m)
	{
		mensagensParaEnviar = m;
	}

	
}