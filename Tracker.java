import java.util.*;
import java.time.*;
/**
	The class Tracker represents an object which contains the Delivery Information of the application, as well as a thread to 
	update the current date of the simulation.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/

public class Tracker implements Runnable
{	
	private int time; 
	private DeliveryInfos delinfo;
	private int dateToday;
	private LocalDate today;
	private int dayCount;
	private int seqnum;
	private boolean running;

	/**
		This constructor does not need to accept any parameters.
	*/

	public Tracker()
	{
		time = 0;
		today = LocalDate.now();
		delinfo = new DeliveryInfos();
		dateToday = 0;
		dayCount = 1;
		seqnum = 1;
		setDate(today);
		running = true;
		
		
	}
	
	/**
	* Ends the tracker's running thread.
	*
	*/
	
	public void terminate()
	{
		running = false;
	}
	
	/**
	* Initiates the thread reliant commands of the class: Increments the current date, and updates the status of deliveries every
	* 2 minutes.
	*
	*/
	
	public void run()
	{
		
		while(running)
		{
			time++;
			if(time%60 == 0)
			{
				seqnum=0;
				dayCount++;
				adjustDate();
				delinfo.updateDeliveries();
				
			
			}
		
		try{
			Thread.sleep(2000);	
		}catch(InterruptedException ex){
		
		}
	
		}
		
	}
	
	/**
	* Sets the current date int value of the Tracker based on the given LocalDate parameter.
	*
	* @param t LocalDate object to be converted and set to the Tracker's date int value.
	*/


	public void setDate(LocalDate t)
	{
		dateToday += (t.getMonthValue() * 100);
		dateToday += t.getDayOfMonth();
	}
	
	
	
	/**
	* Increments the current date int value of the Tracker.
	*
	*/
	
	public void adjustDate()
	{
		int temp;
		dayCount++;
		if(dateToday/100 == 1 || dateToday/100== 3 || dateToday/100 == 5 || dateToday/100== 7 
			|| dateToday/100 == 8 || dateToday/100 == 10 || dateToday/100 == 12)
			{
				if(dateToday%100<31)
					dateToday +=1;
				else if (dateToday %100 == 31 && dateToday/100 != 12)
				{ 	temp = (dateToday/100) + 1;
					temp *= 100;
					temp +=1;
					dateToday = temp;
				}
				else if(dateToday %100 == 31 && dateToday/100 == 12)
				{
					dateToday = 101;
				}
				
			}
		else if(dateToday/100 == 4 || dateToday/100 == 6 || dateToday/100 == 9 || dateToday == 11)
		{
			if(dateToday %100 <30)
				dateToday +=1;
			else if(dateToday%100 == 30)
			{
				temp = (dateToday/100) + 1;
					temp *= 100;
					temp +=1;
					dateToday = temp;
			}
		}
		else if(dateToday/100 == 2)
		{	
			if(dateToday %100 <28)
				dateToday +=1;
			else if(dateToday%100 == 28)
			{
				temp = (dateToday/100) + 1;
					temp *= 100;
					temp +=1;
					dateToday = temp;
			}
		}
	}
	
	/**
	* Returns the current date in the Tracker in string format.
	*
	* @return String formatted version of dateToday attribute.
	*/

	public String showDate()
	{
		return "Date Today: " + (dateToday/100) + "/" + (dateToday%100); 
	}
	
	/**
	* Returns the current date int value of the Tracker.
	*
	* @return dateToday - Current date in the Tracker.
	*/

	public int getDate()
	{
		return dateToday;
	}
	
	/**
	* Returns the number of minutes passed since beginning the Tracker's running thread.
	*
	* @return time Number of minutes passed since the beginning of the Tracker's running thread.
	*/
	
	public int getTime()
	{
		return time;
	}
	
	/**
	* Returns the corresponding status of the Delivery with the matching Tracking Number. If the input parameter does not match any of
	* the stored Tracking Numbers, then it will return the message "Does not Exist!" instead. 
	*
	* @param tracker Input Tracking Number to be searched.
	* @return Status of the delivery, in String format. If the input does not match any of the stored Deliveries' numbers, then it
	* will return an error message instead.
	*/
	
	public String showSpecificShipping(String tracker)
	{
		return delinfo.showDelivery(tracker);
		
	}
	/**
	* Returns the Delivery Information of the Tracker.
	*
	* @return The Delivery Information Class attribute of the Tracker.  
	*/
	public DeliveryInfos getDeliInfo()
	{
		return delinfo;
	}
	/**
	* Updates the sequence number which will be added to the next Delivery's tracking information.
	*
	*/
	public void updateSeqNum()
	{
		seqnum+=1;
	}
	/**
	* Gets the current Sequence Number value of the Tracker.
	*
	* @return The Sequence Number to be added to the next Delivery's tracking information.
	*/
	public int getSeqNum()
	{
		return seqnum;
	}
	/**
	* Returns the list of pending deliveries, in string format.
	*
	* @return ArrayList of Deliveries which have not yet been delivered.
	*/
	public ArrayList<String> showDeliveryListPending()
	{
		Delivery tempD;
		ArrayList<String> strStats = new ArrayList<>(delinfo.getNumDeliveries());
		int tempNum=0;
		for(int delcnt = 0; delcnt<delinfo.getNumDeliveries(); delcnt++)
		{	
			tempD = delinfo.getSpecificDelivery(delcnt);
			if(tempD.getDeliveryDate() >= dateToday)
			{	
				String snum = tempD.toString();
				String stats = tempD.getStatus();
				int dateDelivered = tempD.getDeliveryDate();
				tempNum++;
				strStats.add(tempNum + ". " + "Shipping Number: " + snum + "\t Status: " + stats + "\n    Date of Delivery: " + 
				dateDelivered/100 + " / " + dateDelivered%100);  
			}
		}			
		return strStats;
	}
	/**
	* Returns the list of all deliveries, in string format.
	*
	* @return ArrayList of Deliveries.
	*/
	public ArrayList<String> showAllDeliveries()
	{
		Delivery tempD;
		ArrayList<String> strStats = new ArrayList<>(delinfo.getNumDeliveries());
		for(int delcnt = 0; delcnt<delinfo.getNumDeliveries(); delcnt++)
		{
			tempD = delinfo.getSpecificDelivery(delcnt);
			String snum = tempD.toString();
			String stats = tempD.getStatus();
			String recipName = tempD.getRecipient();
			int dateDelivered = tempD.getDeliveryDate();
			int tempNum = (delcnt+1);
			strStats.add(tempNum + ". " + "Shipping Number: " + snum + "\tStatus: " + stats + "\n    Recipient's Name: " + recipName +
			"\tDate of Delivery: " + dateDelivered/100 + " / " + dateDelivered%100);  
		}
		return strStats;
	}
}