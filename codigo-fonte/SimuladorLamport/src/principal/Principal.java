package principal;

import gui.JanelaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Principal 
{

	
	public static void main(String args[])
	{
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
		
		janelaPrincipal.setVisible(true);
	}


}