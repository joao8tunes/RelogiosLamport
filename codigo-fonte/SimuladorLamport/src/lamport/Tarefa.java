package lamport;

import javax.swing.JTextField;



public class Tarefa extends JTextField
{	
	

	private static final long serialVersionUID = 1L;
	private boolean enviouOuRecebeu;    //É necessário para pintar de cinza as tarefas que já foram realizadas, mais precisamente pra realizar o "voltar" passo. 
	
	
	public Tarefa(String texto)
	{
		super(texto);
		enviouOuRecebeu = false;
	}


	public void setEnviouOuRecebeu(boolean b)
	{
		enviouOuRecebeu = b;
	}
	
	
	public boolean getEnviouOuRecebeu()
	{
		return enviouOuRecebeu;
	}


}