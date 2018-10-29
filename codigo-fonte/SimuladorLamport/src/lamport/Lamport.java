package lamport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Lamport 
{

	
	private ArrayList<Processo> processos;
	private ArrayList<Mensagem> ordemEnvio;
	private int passoAtual;
	
	
	public void setProcessos(int quantidade)
	{
		processos = new ArrayList<Processo>();
		quantidade = (Math.abs((new Random()).nextInt())%quantidade) + 2;    //Mínimo de 2 processos.
		
		for (int i = 0; i < quantidade; i++) {
			processos.add(new Processo(i, i*5 + 5));
		}
	}
	
	
	public ArrayList<Processo> getProcessos()
	{
		return processos;
	}
	
	
	public void simular(int mensagens, int tempo)
	{
		int emissor;
		
		passoAtual = -1;
		ordemEnvio = new ArrayList<Mensagem>();
		
		for (emissor = 0; emissor < processos.size(); emissor++) {
			processos.get(emissor).setMensagensParaEnviar((Math.abs((new Random()).nextInt())%mensagens)+1);    //No mínimo uma mensagem.
			if (processos.get(emissor).getMensagensParaEnviar() > 0) processos.get(emissor).getNome().setBackground(Color.YELLOW);
		}
		
		while (mensagensParaEnviar()) {    //Escalonando o processo a enviar uma mensagem.
			if (processos.get(emissor = Math.abs((new Random()).nextInt())%processos.size()).getMensagensParaEnviar() > 0) {    //Escolhendo processo que ainda tenha mensagens a serem enviadas.
				int receptor, quando;
				
				processos.get(emissor).setMensagensParaEnviar(processos.get(emissor).getMensagensParaEnviar()-1);    //Decrementando quantidade de mensagens a enviar.
				
				//Envio:
				while ((receptor = Math.abs((new Random()).nextInt())%processos.size()) == emissor) ;    //Verificando para qual processo (diferente do emissor) a mensagem será enviada.
					
				if (processos.get(emissor).getTarefas().size() == 0) {    //Verificando quando a mensagem será enviada pelo emissor.
					quando = 0;
					processos.get(emissor).addTarefas(1);
				}
				else {
					quando = Math.abs((new Random()).nextInt())%tempo + processos.get(emissor).getTarefas().size() + 1;
					processos.get(emissor).addTarefas(Math.abs(quando - processos.get(emissor).getTarefas().size()));
				}
					
				processos.get(receptor).addTarefas(1);
				processos.get(emissor).enviarMensagem(processos.get(receptor));
				ordemEnvio.add(processos.get(emissor).getMensagens().get(processos.get(emissor).getMensagens().size()-1));
				
				//Resposta:
				quando = Math.abs((new Random()).nextInt())%tempo + 1 + processos.get(receptor).getTarefas().size();    //Verificando quando a mensagem será devolvida pelo receptor.
				processos.get(receptor).addTarefas(Math.abs(quando - processos.get(receptor).getTarefas().size()));
				processos.get(emissor).addTarefas(1);
				processos.get(receptor).enviarMensagem(processos.get(emissor));
				ordemEnvio.add(processos.get(receptor).getMensagens().get(processos.get(receptor).getMensagens().size()-1));
			}
		}
		
		int maxTarefas = 0;
		
		for (Processo p : processos) {
			if (p.getTarefas().size() > maxTarefas) maxTarefas = p.getTarefas().size();
		}
		
		for (Processo p : processos) {
			p.addTarefas(maxTarefas - p.getTarefas().size());
		}
		
		//Zerando a numeração das tarefas:
		for (int i = 0; i < processos.size(); i++) {
			int incremento = (i+1)*5, valorAtual = 0;
			
			for (int j = 0; j < processos.get(i).getTarefas().size(); j++) {
				processos.get(i).getTarefas().get(j).setText(valorAtual+"");
				valorAtual += incremento;
			}
		}
	}
	
	
	private boolean mensagensParaEnviar()
	{
		for (Processo p : processos) {
			if (p.getMensagensParaEnviar() > 0) return true;
		}
		
		return false;
	}
	
	
	public ArrayList<Mensagem> getOrdemEnvio()
	{
		return ordemEnvio;
	}
	
	
	public void setPassoAtual(int passoAtual)
	{
		this.passoAtual = passoAtual;
	}

	
	public int getPassoAtual()
	{
		return passoAtual;
	}
	
	
}