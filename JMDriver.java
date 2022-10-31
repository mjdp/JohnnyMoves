import javax.swing.*;

public class JMDriver
{
	public static void main (String[] args) 
	{	
		JMOpen start = new JMOpen(null, true);
		boolean status = start.getStatus();
		
		if(status == true)
		{
			Tracker tracker = new Tracker();
			JMGUI gui = new JMGUI();
			JMController controller = new JMController(gui, tracker);
		}
		else
			System.exit(0);
	}
}