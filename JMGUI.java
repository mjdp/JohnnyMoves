import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Color;
import java.time.*;

/**
	The class JMGUI functions as the view component of the Johnny Moves Model-View-Component application. It consists of all of the 
	Johnny Moves Application's Graphical User Interface, aside from the JMOpen class.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0
*/

public class JMGUI extends JFrame 
{
	private JPanel	pnlShip;
	private JPanel	pnlTrack;
	private JPanel	pnlAddItem;
	private	JPanel	pnlEditItem;
	private JPanel	pnlTrackItem;
	private JPanel	pnlParcels;
	private JPanel	pnlCompFee;
	private JPanel	pnlTrackSearch;
	private JPanel	pnlAdmin;
	private JPanel	pnlReport;
	
	public final String TO_DO = "To Do";
	public final String SHIP = "Make a Shipment";
	public final String TRACK = "Track an Item";
	
	private JComboBox<String>	cmbItemList;
	private	JComboBox<String>	cmbAvParcels; //Available Box Types
	private JComboBox<String>	cmbDest;
	private ArrayList<JComboBox<String>>	cmbItemType;
	
	private JCheckBox		cbxInsure;
	private JButton 		btnShip;
	private JButton			btnParcel;
	private JButton 		btnTrack;
	private JButton			btnAdmin;
	private JButton			btnBscReport;
	private JButton			btnAdvReport;
	private JButton			btnLogOut;
	private JButton			btnComp;
	private JButton			btnSearch;
	private JButton			btnExitProg;
	private JButton			btnCompFee;
	private JButton			btnBackAdmin;
	private JButton			btnFinish;
	private JButton[]		btnBack;
	private JTextField[][]	tfItemEdit;
	private JTextField		tfTrackNo;
	private JTextField		tfItemNo;
	private JTextField		tfRecipient;
	private JPasswordField	pwPassword;
	private JLabel			lblDate;
	
	private ArrayList<JPanel>	pnlItems;
	private JPanel				toDo;
	private JPanel				centerPane;
	private JPanel 				centerPane2;
	private JPanel				subPanel;
	private JPanel				pnlDate;
	
	private LocalDate	today;
	private String[]	itemList;
	private int			btnCount;
	private int			currDate;
	private int			time;
	
	private javax.swing.Timer	timer;
	
	/**
	* This constructor does not accept any parameters.
	*
	*/
	
	public JMGUI()
	{
		super("Johnny Moves");
		
		setLayout(new BorderLayout());
		init();
		
		time = 0;
		currDate = 0;
		
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		this.setBackground(Color.DARK_GRAY);
		setVisible(true);
	}
	
	/**
	* Increments the value of the GUI's date display by one day. 
	*
	*/
	
	public void adjustDate()
	{
		today = today.plusDays(1);
		setDate(today);
		//System.out.println(today);
	}
	/**
	* Checks if a given String is numeric (ex: If the String is "22", it is numeric. If it is "abcd", it is not.). 
	*
	* @param in Input string to be checked by the method.
	* @return Returns true if the given String input is numeric. Otherwise returns false.
	*/
	public boolean isNumeric(String in)
	{
		try {
        double d = Double.parseDouble(in);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	* Updates the value of the currDate attribute based on the LocalDate input.
	*
	* @param t Date input in LocalDate format.
	*/
	public void setDate(LocalDate t)
	{
		currDate = (t.getMonthValue() * 100) + t.getDayOfMonth();
	}
	/**
	* Initializes the main menu, tracking, and shipping menus.
	*
	*/
	public void init()
	{
		today = LocalDate.now();
		setDate(today);
		
		String[] strDest = {"<Choose Destination>", "Manila", "Luzon", "Visayas", "Mindanao"};
		cmbDest = new JComboBox<>(strDest);
		initializeButtons();
		centerPane = new JPanel ();
		centerPane.setLayout (new CardLayout ());
		
		toDo = new JPanel();
		toDo.setLayout(new GridLayout(0,1));
		pnlDate = new JPanel();
		lblDate = new JLabel();
		pnlDate.add(lblDate);
		setCurrDatePanel(currDate);
		toDo.add(pnlDate);
		initTimer();
		JPanel pnlToDo = new JPanel();
		pnlToDo.add(new JLabel("What would you like to do?"));
		toDo.add(pnlToDo);
		pnlShip = new JPanel();
		pnlShip.setLayout(new FlowLayout());
		pnlShip.add (btnShip);
		toDo.add(pnlShip);
		pnlTrack = new JPanel();
		pnlTrack.setLayout(new FlowLayout());
		pnlTrack.add(btnTrack);
		toDo.add(pnlTrack);
		JPanel pnlAdm = new JPanel();
		pnlAdm.add(btnAdmin);
		toDo.add(pnlAdm);
		JPanel pnlExit = new JPanel();
		pnlExit.add(btnExitProg);
		toDo.add(pnlExit);
		centerPane.add(toDo, TO_DO);
		adminWindow();
		
		pnlAddItem = new JPanel();
		pnlAddItem.setLayout(new GridLayout(0,2));
		subPanel = new JPanel();
		subPanel.add(new JLabel("Name of Recipient: "));
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		tfRecipient = new JTextField(15);
		subPanel.add(tfRecipient);
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(new JLabel("Destination: "));
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(cmbDest);
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(new JLabel ("Number of Items: "));
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		tfItemNo = new JTextField(15);
		subPanel.add(tfItemNo);
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(new JLabel ("Insure Item?"));
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		cbxInsure = new JCheckBox();
		subPanel.add(cbxInsure);
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(btnBack[0]);
		pnlAddItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(btnComp);
		pnlAddItem.add(subPanel);
		centerPane.add(pnlAddItem, SHIP);
		
		pnlTrackItem = new JPanel();
		pnlTrackItem.setLayout(new GridLayout(0, 2));
		subPanel = new JPanel();
		subPanel.add(new JLabel ("Tracking Number: "));
		pnlTrackItem.add(subPanel);
		subPanel = new JPanel();
		tfTrackNo = new JTextField(15);
		subPanel.add(tfTrackNo);
		pnlTrackItem.add(subPanel);
		subPanel = new JPanel();
		subPanel.add(btnBack[1]);
		pnlTrackItem.add(subPanel);
		JPanel pnlFind = new JPanel();
		pnlFind.add(btnSearch);
		pnlTrackItem.add(pnlFind);
		centerPane.add(pnlTrackItem, TRACK);
		
		add(centerPane);
	}
	/**
	* Initializes the timer to be used for the date display in the main menu.
	*
	*/
	public void initTimer()
	{
		ActionListener updateClockAction = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				time++;
				if(time%60==0)
				{
					adjustDate();
					setCurrDatePanel(currDate);
				}
			}
		};
		
		timer = new javax.swing.Timer(2000, updateClockAction);
		timer.start();
	}
	/**
	* Updates the information of the currDatePanel attribute.
	*	
	*	@param date integer symbolizing date
	*/
	public void setCurrDatePanel(int date)
	{
		lblDate.setText("The current date is " + (date/100) + " / " + (date%100) + ".");
		pnlDate.revalidate();
		pnlDate.repaint();
	}
	/**
	* Initializes the "Edit Item Details" submenu.
	*	
	*	@param itemNo indicates the position of item selected
	*/
	public void initShipMenu(int itemNo)
	{
		JPanel northPane = new JPanel();
		JPanel southPane = new JPanel();
		JPanel blank	 = new JPanel();
		JPanel subPanel1;
		JPanel subPanel2;
		String[] strType = {"<Choose Item Type>", "Document", "Product", "Irregular Object"};
		pnlItems = new ArrayList<JPanel>(itemNo);
		itemList = new String[itemNo+1];
		cmbItemType = new ArrayList<JComboBox<String>>(itemNo);
		int i;
		
		pnlEditItem = new JPanel();
		pnlEditItem.setLayout(new BorderLayout());
		
		centerPane2 = new JPanel();
		centerPane2.setLayout(new CardLayout());
		
		itemList[0] = "<Select Item>";
		for (i = 1; i <= itemNo; i++)
			itemList[i] = "Item # " + i;
		cmbItemList = new JComboBox<>(itemList);
		cmbItemList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox combo = (JComboBox) e.getSource();
				
				if (combo.getSelectedItem().toString().contains("Item #"))
					updateCenterPane2(combo.getSelectedItem().toString());
			}
		});
		
		northPane.add(cmbItemList);
		pnlEditItem.add(northPane, BorderLayout.NORTH);
		tfItemEdit = new JTextField[itemNo][5];
		
		centerPane2.add(blank, "BLANK");
		
		for (i = 0; i < itemNo; i++)
		{
			pnlItems.add(new JPanel());
			pnlItems.get(i).setLayout(new GridLayout(0,2));
			
			subPanel1 = new JPanel();
			subPanel1.add(new JLabel ("Item Name: "));
			pnlItems.get(i).add(subPanel1);
			
			subPanel1 = new JPanel();
			tfItemEdit[i][0] = new JTextField(15);
			subPanel1.add(tfItemEdit[i][0]);
			pnlItems.get(i).add(subPanel1);
			
			subPanel1 = new JPanel();
			subPanel1.add(new JLabel ("Item Type: "));
			pnlItems.get(i).add(subPanel1);
			
			subPanel1 = new JPanel();
			cmbItemType.add(new JComboBox<>(strType));
			int num = i;
			cmbItemType.get(i).addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JComboBox combo = (JComboBox) e.getSource();
					if(combo.getSelectedIndex() == 1)
						specifyItemType(1, num);
					else if (combo.getSelectedIndex() == 2)
						specifyItemType(2, num);
					else if (combo.getSelectedIndex() == 3)
						specifyItemType(3, num);
				}
			});
		
			subPanel1.add(cmbItemType.get(i));
			pnlItems.get(i).add(subPanel1);
			
			centerPane2.add(pnlItems.get(i), itemList[i+1]);
		}
		southPane.add(btnBack[2]);
		southPane.add(btnParcel);
		pnlEditItem.add(centerPane2, BorderLayout.CENTER);
		pnlEditItem.add(southPane, BorderLayout.SOUTH);
		centerPane.add(pnlEditItem, "Edit Item Details");
	}
	/**
	* Initializes all the buttons of the GUI except buttons of JDialogs.
	*
	*/
	public void initializeButtons()
	{
		int i;
		btnCount = 5;
		
		btnShip = new JButton("Make a Shipment");
		btnTrack = new JButton("Track an Item");
		btnAdmin = new JButton("Admin Menu");
		btnExitProg = new JButton("Exit Program");
		btnComp = new JButton("Edit Item Details");
		btnSearch = new JButton("Search");
		btnBscReport = new JButton("Basic Report");
		btnAdvReport = new JButton("Advanced Report");
		btnLogOut = new JButton("Log Out");
		btnParcel = new JButton("Check Available Parcels");
		btnCompFee = new JButton("Compute Shipping Fee");
		btnBackAdmin = new JButton("Back to Admin Menu");
		btnFinish = new JButton("Finish");
		btnBack = new JButton[btnCount];
		for(i = 0; i < btnCount; i++)
			btnBack[i] = new JButton("Back");
	}
	/**
	* Adds ActionListeners to the buttons of the GUI, except for the buttons of JDialogs.
	*
	* @param listener EventListener which will be added to the GUI's buttons.
	*/
	public void addListeners(EventListener listener)
	{
		int i;
		
		btnShip.addActionListener((ActionListener) listener);
		btnTrack.addActionListener((ActionListener) listener);
		btnAdmin.addActionListener((ActionListener) listener);
		btnLogOut.addActionListener((ActionListener) listener);
		btnExitProg.addActionListener((ActionListener) listener);
		btnParcel.addActionListener((ActionListener) listener);
		btnComp.addActionListener((ActionListener) listener);
		btnCompFee.addActionListener((ActionListener) listener);
		btnSearch.addActionListener((ActionListener) listener);
		btnFinish.addActionListener((ActionListener) listener);
		btnBscReport.addActionListener((ActionListener) listener);
		btnAdvReport.addActionListener((ActionListener) listener);
		btnBackAdmin.addActionListener((ActionListener) listener);
		for (i = 0; i < btnCount; i++)
			btnBack[i].addActionListener((ActionListener) listener);
	}
	/**
	* Changes the content of the input menu of a specific item, based on the chosen item of its JComboBox.
	*	@param typeIndex index of entered text
	*	@param itemIndex index of item
	*/
	public void specifyItemType (int typeIndex, int itemIndex)
	{
		JPanel itemSubPanel = new JPanel();
		int endParam, endIndex, i;
		
		if (typeIndex > 0)
		{			
			if(pnlItems.get(itemIndex).getComponentCount() > 4)
			{
				endIndex = pnlItems.get(itemIndex).getComponentCount();
				for (i = 4; i < endIndex; i++)
				{
					pnlItems.get(itemIndex).remove(4);
				}
			}
			
			if (typeIndex == 1)
				endParam = 4;
			else
				endParam = 5;
			
			for (i = 1; i < endParam; i++)
			{
				itemSubPanel = new JPanel();
				switch (i)
				{
					case 1: //length
						if (typeIndex == 3)
							itemSubPanel.add(new JLabel ("Item Length (Largest Measure): "));
						else
							itemSubPanel.add(new JLabel ("Item Length: "));
						break;
					case 2: //width
						if (typeIndex == 3)
							itemSubPanel.add(new JLabel ("Item Width (Largest Measure): "));
						else
							itemSubPanel.add(new JLabel ("Item Width: "));
						break;
					case 3: //pages or height
						if (typeIndex == 1)
							itemSubPanel.add(new JLabel ("Number of Pages: "));
						else if (typeIndex == 2)
							itemSubPanel.add(new JLabel ("Item Height: "));
						else if (typeIndex == 3)
							itemSubPanel.add(new JLabel ("Item Height (Largest Measure): "));
						break;
					case 4: //weight
						itemSubPanel.add(new JLabel ("Item Weight (in KG): "));
						break;
				}
				pnlItems.get(itemIndex).add(itemSubPanel);
				
				itemSubPanel = new JPanel();
				tfItemEdit[itemIndex][i] = new JTextField(15);
				itemSubPanel.add(tfItemEdit[itemIndex][i]);
				pnlItems.get(itemIndex).add(itemSubPanel);
			}
			pnlItems.get(itemIndex).revalidate();
			pnlItems.get(itemIndex).repaint();
		}
	}

	/**
	* Gets the parcel chosen by the user in the cmbAvParcels combo box.
	*
	* @return Chosen parcel by the user, in int index format.
	*/
	public int getParcelType()
	{
	return cmbAvParcels.getSelectedIndex();
	}
	/**
	* Gets the name of the recipient written in the taRecipient text field.
	*
	* @return User input for recipients name, in string format
	*/
	public String getRecipientName()
	{
		return tfRecipient.getText();
	}
	/**
	* Gets the region chosen by the user in the cmbDest combo box.
	*
	* @return Chosen region by the user, in int index format.
	*/
	public int getRegion()
	{
		return cmbDest.getSelectedIndex();
	}
	/**
	* Gets the value chosen by the user on the cbxInsure check box.
	*
	* @return True if the user has checked the box, false if not.
	*/
	public boolean isInsured()
	{
		return cbxInsure.isSelected();
	}
	/**
	* Gets the number of items written by the user on the tfItemNo text field.
	*
	* @return Number of items input by the user.
	*/
	public int getItemNo()
	{
		return Integer.parseInt(tfItemNo.getText());
	}
	/**
	* Gets the tracking number written by the user on the tfTrackNo text field.
	*
	* @return Tracking Number input by the user.
	*/
	public String getTrackerContent()
	{
		return tfTrackNo.getText();
	}
	/**
	* Returns the information added by the user, regarding a specific item
	*
	* @param index Index of item whose information will be returned.
	* @return Information string array of the specified item.
	*/
	public String[] getItemInfo(int index)
	{
		String[] itemInfo;
		String itemType = cmbItemType.get(index).getSelectedItem().toString();
		int endInd;
		int i;
		
		if (itemType.equals("Document"))
			endInd = 4;
		else
			endInd = 5;
		
		itemInfo = new String[endInd+1];
		
		for (i = 0; i < endInd; i++)
		{
			itemInfo[i] = tfItemEdit[index][i].getText();
		}
		
		itemInfo[i] = itemType;
		
		return itemInfo;
	}
	/**
	* Checks if all the text field in the edit items panel are filled.
	*
	* @return True if all text fields are filled, false otherwise.
	*/
	public boolean checkEditItemsFull()
	{
		int i, j;
		int paramSize;
		
		for (i = 0; i < pnlItems.size(); i ++)
		{
			switch(cmbItemType.get(i).getSelectedIndex())
			{
				case 0:
					return false;
				case 1:
					paramSize = 4;
					break;
				default:
					paramSize = 5;
					break;
			}
			
			for (j = 0; j < paramSize; j++)
			{
				switch(j)
				{
					case 0:
						if (tfItemEdit[i][j].getText().equals(""))
							return false;
						break;
					default:
						if(tfItemEdit[i][j].getText().equals("") || isNumeric(tfItemEdit[i][j].getText()) == false || 
						Double.parseDouble(tfItemEdit[i][j].getText()) < 1)
							return false;
				}
			}
		}
		
		return true;
	}
	/**
	* Checks if all text fields in the make a shipment menu are filled.
	*
	* @return True if all text fields are filled, false otherwise.
	*/
	public boolean checkAddItemsFull()
	{
		if (tfRecipient.getText().equals("") || cmbDest.getSelectedIndex() == 0 || tfItemNo.getText().equals("") ||
		isNumeric(tfItemNo.getText()) == false)
			return false;
		else return true;
	}
	/**
	* Initializes a window of available parcels if there are any, returns an error message otherwise.
	*
	* @param parcels List of available parcels.
	*/
	public void availParcelsPanel(ArrayList<String> parcels)
	{
		pnlParcels = new JPanel();
		String[] arrParcels;
		
		pnlParcels.setLayout(new GridLayout(0, 2));
		
		
		if (parcels.size() > 0)
		{
			arrParcels = parcels.toArray(new String[parcels.size()]);
			
			subPanel = new JPanel();
			subPanel.add(new JLabel("Available Parcels:"));
			pnlParcels.add(subPanel);
			
			subPanel = new JPanel();
			cmbAvParcels = new JComboBox<>(arrParcels);
			subPanel.add(cmbAvParcels);
			pnlParcels.add(subPanel);
			
			subPanel = new JPanel();
			subPanel.add(btnBack[3]);
			pnlParcels.add(subPanel);
			
			subPanel = new JPanel();
			subPanel.add(btnCompFee);
			pnlParcels.add(subPanel);
			
			centerPane.add(pnlParcels, "Check Available Parcels");
			updateCenterPane("Check Available Parcels");
		}
		else
		{	
			displayErrorMessage("There are no available Parcels for your shipment.");
		}
	}
	/**
	* Initializes a window which consists of the shipment's tracking number and total fee.
	* @param recipName name of recipient 
	* @param delivDate date of expected delivery
	* @param delivery Tracking number of the shipment.
	* @param totalFee Total fee of the shipment.
	*/
	public void confirmDelivery(String recipName, int delivDate, String delivery, double totalFee)
	{
		JTextArea taDeliv = new JTextArea("To be delivered to " + recipName);
		JPanel pnlTop = new JPanel(), pnlBottom = new JPanel();
		JLabel label = new JLabel("Shipping Booked!");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlCompFee = new JPanel();
		pnlCompFee.setLayout(new BorderLayout());
		
		taDeliv.append("\nOn " + (delivDate/100) + " / " + (delivDate%100));
		taDeliv.append("\nYour tracking number is " + delivery + "\nYour shipping fee is Php " + totalFee);
		taDeliv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		pnlTop.add(label);
		pnlBottom.add(btnFinish);
		
		pnlCompFee.add(taDeliv, BorderLayout.CENTER);
		pnlCompFee.add(pnlTop, BorderLayout.NORTH);
		pnlCompFee.add(pnlBottom, BorderLayout.SOUTH);
		
		centerPane.add(pnlCompFee, "Compute Shipping Fee");
	}
	/**
	* Displays the status of the delivery with the matching input tracking number. If the tracking number is not found, an error 
	* message is displayed instead.
	*	@param trackerDetails represents tracking detail displayed
	*
	*/
	public void trackerWindow(String trackerDetails)
	{
		JTextArea taTrack = new JTextArea("Current Status: " + trackerDetails);
		JPanel pnlTop = new JPanel(), pnlBottom = new JPanel();
		JLabel label = new JLabel("Delivery Found:");
		pnlTrackSearch = new JPanel();
		pnlTrackSearch.setLayout(new BorderLayout());
		
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		taTrack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		pnlTop.add(label);
		pnlBottom.add(btnBack[4]);
		pnlTrackSearch.add(taTrack, BorderLayout.CENTER);
		pnlTrackSearch.add(pnlTop, BorderLayout.NORTH);
		pnlTrackSearch.add(pnlBottom, BorderLayout.SOUTH);
		centerPane.add(pnlTrackSearch, "Search");
	}
	/**
	* Initializes the Admin Menu, which contains the Basic and Advanced Report features of the application.
	*
	*
	*/
	public void adminWindow()
	{
		pnlAdmin = new JPanel();
		pnlAdmin.setLayout(new GridLayout(4,1));
		
		subPanel = new JPanel();
		subPanel.add(new JLabel("Admin Menu"));
		pnlAdmin.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.add(btnBscReport);
		pnlAdmin.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.add(btnAdvReport);
		pnlAdmin.add(subPanel);
		
		subPanel = new JPanel();
		subPanel.add(btnLogOut);
		pnlAdmin.add(subPanel);
		
		centerPane.add(pnlAdmin, "Admin Menu");
	}
	/**
	* Initializes the panel which contains either the Basic or Advanced reports.
	*
	* @param statuses List of deliveries to be displayed in the panel.
	* @param title String to be set as the header of the panel.
	*/
	public void reportWindow(ArrayList<String> statuses, String title)
	{
		JTextArea taReport = new JTextArea();
		JScrollPane scroll;
		JPanel pnlTop = new JPanel(), pnlBottom = new JPanel();
		JLabel label = new JLabel(title, SwingConstants.CENTER);
		JLabel label2;
		pnlReport = new JPanel();
		pnlReport.setLayout(new BorderLayout());
		int i;
		
		setDate(today);
		
		if(statuses.size() > 0)
		{
			label.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pnlTop.setLayout(new GridLayout(0, 1));
			label2 = new JLabel ("The current date today is " + currDate/100 + " / " + currDate%100, SwingConstants.CENTER);
			pnlTop.add(label);
			pnlTop.add(label2);
			pnlBottom.add(btnBackAdmin);
			
			for (i = 0; i < statuses.size(); i++)
				taReport.append(statuses.get(i) + "\n");
			
			scroll = new JScrollPane(taReport);
			pnlReport.add(pnlTop, BorderLayout.NORTH);
			pnlReport.add(scroll, BorderLayout.CENTER);
			pnlReport.add(pnlBottom, BorderLayout.SOUTH);
			centerPane.add(pnlReport, "Report");
			updateCenterPane("Report");
		}
		else 
			displayErrorMessage("No Shipments Listed!");
	}
	/**
	* Creates a JDialog which displays the parameter message.
	*
	* @param message Message to be displayed.
	*/
	public void displayErrorMessage(String message)
	{
		JDialog d = new JDialog(this, "Error");
		JPanel dPanel = new JPanel();
		JPanel dPanel2 = new JPanel(), dPanel3 = new JPanel();
		JButton	btnOK = new JButton("OK");
		
		btnOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				d.setVisible(false);
			}
		});
		
		d.setLayout(new BorderLayout());
		d.setSize(350,150);
		dPanel.setLayout(new GridLayout(2, 1));
		dPanel2.add(new JLabel(message));
		dPanel.add(dPanel2);
		dPanel3.add(btnOK);
		dPanel.add(dPanel3);
		d.add(dPanel, BorderLayout.CENTER);
		d.setVisible(true);
	}
	/**
	* Initializes a dialog which will ask for a password to be entered. If the password is correct, the entire application exits. Else,
	* the dialog will display an error message and will subsequenty dispose.
	*/
	public void exitPassDialog()
	{
		JDialog p = new JDialog(this, "Password Authentication");
		JPanel pPanel = new JPanel();
		JPanel pPanel2 = new JPanel(), pPanel3 = new JPanel(), pPanel4 = new JPanel();
		JButton btnExit = new JButton("Exit");
		pwPassword = new JPasswordField(15);
		
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (String.valueOf(pwPassword.getPassword()).equals("Password"))
				{
					System.exit(0);
				}
				else
				{
					displayErrorMessage("Wrong Password");
					p.setVisible(false);
				}					
			}
		});
		
		p.setLayout(new BorderLayout());
		p.setSize(350,150);
		pPanel.setLayout(new GridLayout(3, 1));
		pPanel2.add(new JLabel("Enter Password:"));
		pPanel3.add(pwPassword);
		pPanel4.add(btnExit);
		pPanel.add(pPanel2);
		pPanel.add(pPanel3);
		pPanel.add(pPanel4);
		p.add(pPanel, BorderLayout.CENTER);
		p.setVisible(true);
	}
	/**
	* Initializes a dialog which will ask for a password to be entered. If the password is correct, then the user will be navigated
	* to the Admin menu. Otherwise, an error message is displayed.
	*
	*/
	public void adminPassDialog()
	{
		JDialog p = new JDialog(this, "Password Authentication");
		JPanel pPanel = new JPanel();
		JPanel pPanel2 = new JPanel(), pPanel3 = new JPanel(), pPanel4 = new JPanel();
		JButton btnLogIn = new JButton("Log In");
		pwPassword = new JPasswordField(15);
		
		btnLogIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (String.valueOf(pwPassword.getPassword()).equals("Password"))
				{
					p.setVisible(false);
					updateCenterPane("Admin Menu");
				}
				else
				{
					displayErrorMessage("Wrong Password");
					p.setVisible(false);
				}					
			}
		});
		
		p.setLayout(new BorderLayout());
		p.setSize(350,150);
		pPanel.setLayout(new GridLayout(3, 1));
		pPanel2.add(new JLabel("Enter Password:"));
		pPanel3.add(pwPassword);
		pPanel4.add(btnLogIn);
		pPanel.add(pPanel2);
		pPanel.add(pPanel3);
		pPanel.add(pPanel4);
		p.add(pPanel, BorderLayout.CENTER);
		p.setVisible(true);
	}
	
	/**
	* Updates the contents of the GUI's main cardLayout.
	*
	* @param name Destination window after updating the center pane.
	*/
	public void updateCenterPane(String name)
	{
		CardLayout cards = (CardLayout) centerPane.getLayout ();
		
		cards.show(centerPane, name);
	}
	/**
	* Updates the contents of the GUI's edit item details cardLayout.
	*
	* @param name Destination window after updating the center pane.
	*/
	public void updateCenterPane2(String name)
	{
		CardLayout cards = (CardLayout) centerPane2.getLayout ();
		
		cards.show(centerPane2, name);
	}
	/**
	* Resets the Shipment and Tracking menu's text fields.
	*
	*/
	public void resetTextFields()
	{
		tfRecipient.setText("");
		cmbDest.setSelectedIndex(0);
		tfItemNo.setText("");
		tfTrackNo.setText("");
		cbxInsure.setSelected(false);
	}
}