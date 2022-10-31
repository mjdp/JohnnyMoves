import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
	The class JMOpen functions as an introduction dialog separate from the main JMGUI view of the application.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0
*/


public class JMOpen extends JDialog implements ActionListener
{
	private JButton btnStart;
	private ImageIcon imgLogo;
	private boolean status;
	/**
	* This constructor accepts the JFrame it belongs to, as well as the modal.
	*	@param owner JFrame type 
	*	@param modal boolean typ
	*/
	public JMOpen (JFrame owner, boolean modal) 
	{
		super(owner, modal);
		init();
		
		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
		setSize(400, 200);
		
		setVisible(true);
	}
	/**
	* Initializes the contents of the dialog
	*
	*/
	public void init() 
	{
		setLayout (new GridLayout(2,1));
		btnStart = new JButton("Get Started");
		imgLogo = new ImageIcon("JMLogoSmall.png");
		status = false;
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel(imgLogo));
		add(panel);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(btnStart);
		btnStart.addActionListener(this);
		add(panel);
	}
	/**
	* Adds an ActionListener to the dialog's button. The dialog disposes once the button is clicked.
	*
	*/
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Get Started"));
		{
			status = true;
			dispose();
		}
	}
	/**
	* Returns the status of the dialog. The status is used to determine whether or not the main GUI will initialize.
	*	@return status boolean type for dialog
	*/
	public boolean getStatus()
	{
		return status;
	}
}