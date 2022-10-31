import java.awt.event.*;
import java.util.*;
import java.lang.*;
/**
	The class JMController functions as the controller component of the Johnny Moves Model-View-Component application. Majority
	of the JMGUI's button commands are implemented in this class.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0
*/
public class JMController implements ActionListener//, ItemListener
{
	private JMGUI gui;
	private Tracker tracker;
	private Delivery delivery;
	private Thread thread;
	
	/**
	* This constructor accepts the JMGUI View and the Tracker Model as parameters.
	*
	* @param gui The view component of the the Johnny Moves application.
	* @param tracker The model component of the Johnny Moves application.
	*/
	public JMController (JMGUI gui, Tracker tracker)
	{
		this.gui = gui;
		this.tracker = tracker;
		thread = new Thread(this.tracker);
		thread.start();
		gui.addListeners(this);
	}
	
	/**
	* Receives the GUI's commands (Action Events), and implements it to the Model.
	*
	* @param e ActionEvent received from the JMGUI view.
	*/
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Make a Shipment"))
		{
			gui.updateCenterPane(gui.SHIP);
		}
		else if (e.getActionCommand().equals("Track an Item"))
		{
			gui.updateCenterPane(gui.TRACK);
		}
		else if (e.getActionCommand().equals("Admin Menu"))
		{
			gui.adminPassDialog();
		}
		else if (e.getActionCommand().equals("Basic Report"))
		{
			ArrayList<String> storedItems = tracker.showDeliveryListPending();
			gui.reportWindow(storedItems, "Basic Shipping Report");
		}
		else if (e.getActionCommand().equals("Advanced Report"))
		{
			ArrayList<String> storedItems2 = tracker.showAllDeliveries();
			gui.reportWindow(storedItems2, "Advanced Shipping Report");
		}
		else if (e.getActionCommand().equals("Back to Admin Menu"))
		{
			gui.updateCenterPane("Admin Menu");
		}
		else if (e.getActionCommand().equals("Log Out"))
		{
			gui.updateCenterPane(gui.TO_DO);
		}
		else if (e.getActionCommand().equals("Exit Program"))
		{
			gui.exitPassDialog();
		}
		else if (e.getActionCommand().equals("Back"))
		{
			gui.resetTextFields();
			gui.updateCenterPane(gui.TO_DO);
		} 
		else if (e.getActionCommand().equals("Edit Item Details"))
		{
			if(gui.checkAddItemsFull())
			{
				if(gui.getItemNo() > 0)
				{
					gui.initShipMenu(gui.getItemNo());
					gui.updateCenterPane("Edit Item Details");
				}
				else gui.displayErrorMessage("Please fill up all items correctly.");
			} else gui.displayErrorMessage("Please fill up all items correctly.");
		}
		else if (e.getActionCommand().equals("Check Available Parcels"))
		{
			if(gui.checkEditItemsFull())
				checkAvailParcels();
			else 
				gui.displayErrorMessage("Please fill up all items correctly.");
		}
		else if (e.getActionCommand().equals("Compute Shipping Fee"))
		{
			delivery.getParcel().setParcelChoice(gui.getParcelType()+1);
			delivery.computeTotalFee();
			delivery.setStatus(1);
			delivery.setStartDate(tracker.getDate());
			delivery.setSeqCode(tracker.getSeqNum());
			tracker.updateSeqNum();
			tracker.getDeliInfo().addDelivery(delivery);
			gui.confirmDelivery(delivery.getRecipient(), delivery.getDeliveryDate(), delivery.toString(), delivery.getTotalFee());
			gui.updateCenterPane("Compute Shipping Fee");
		}
		else if (e.getActionCommand().equals("Search"))
		{	
			String trackInfo = gui.getTrackerContent();
			System.out.println(trackInfo);
			System.out.println(tracker.showSpecificShipping(trackInfo));
			if (tracker.showSpecificShipping(trackInfo).equals("Does not Exist!"))
				gui.displayErrorMessage("No Results Found!");
			else
			{
				tracker.showSpecificShipping(trackInfo);
				gui.trackerWindow(tracker.showSpecificShipping(trackInfo));
				gui.updateCenterPane("Search");
				System.out.println("xxx");
			}
		}
		else if (e.getActionCommand().equals("Finish"))
		{
			gui.resetTextFields();
			gui.updateCenterPane(gui.TO_DO);
		}
	}
	
	/**
	* Checks the parcels applicable for the Delivery, then navigates the user to the "Check Available Parcels" menu. If there are
	* no applicable parcels, an error message is instead displayed and the user is navigated back to the main menu.
	*
	*/
	public void checkAvailParcels()
	{
		int itemNo = gui.getItemNo();
		int i, j;
		delivery = new Delivery(gui.getRecipientName(), gui.getRegion(), gui.getItemNo(), gui.isInsured());
		ArrayList<String> possibleParcels;
		Product prod;
		IrregularObj irreg;
		Document docu;
		String[][] strInput = new String[itemNo][6];
		
		for (i = 0; i < itemNo; i++)
		{
			strInput[i] = gui.getItemInfo(i);

			
			switch (strInput[i][strInput[i].length-1])
			{
				case "Document":
					docu = new Document(strInput[i][0], Double.parseDouble(strInput[i][1]), Double.parseDouble(strInput[i][2]),
					Integer.parseInt(strInput[i][3]));
					delivery.getParcel().addItem(docu);
					break;
				case "Product":
					prod = new Product (strInput[i][0], Double.parseDouble(strInput[i][1]), Double.parseDouble(strInput[i][2]),
					Double.parseDouble(strInput[i][3]), Double.parseDouble(strInput[i][4]));
					delivery.getParcel().addItem(prod);
					break;
				case "Irregular Object":
					irreg = new IrregularObj (strInput[i][0], Double.parseDouble(strInput[i][1]), Double.parseDouble(strInput[i][2]),
					Double.parseDouble(strInput[i][3]), Double.parseDouble(strInput[i][4]));
					delivery.getParcel().addItem(irreg);
					break;
			}
		}
		possibleParcels = delivery.getParcel().getPossibleParcels();
		//System.out.println(possibleParcels.get(0));
		gui.availParcelsPanel(possibleParcels);
	}
	
	
	
	
}