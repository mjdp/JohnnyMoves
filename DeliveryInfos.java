import java.util.*;

/**
	The class DeliveryInfos represents the Delivery Information of the application. It contains an ArrayList of all the deliveries
	transacted.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0
*/

public class DeliveryInfos
{
	private ArrayList<Delivery> deliveries;
	
	public DeliveryInfos ()
	{
		deliveries = new ArrayList<Delivery> ();
		
	}

	/**
	* Adds a delivery to the object.
	*
	* @param d Delivery object to be added to the class.
	*/
	
	public void addDelivery(Delivery d)
	{
		deliveries.add(d);
	}
	
	/**
	* Updates the date and status attributes of each Delivery in the class.
	*
	*
	*/
	
	public void updateDeliveries()
	{
		if(deliveries.size()>0)
		{
			for(int i=0; i<deliveries.size(); i++)
			{
			if(deliveries.get(i).getNumDays()>1)
			{
				if(deliveries.get(i).getRegion().compareToIgnoreCase("MML") == 0)
				{
						deliveries.get(i).updateDays();
						if(deliveries.get(i).getNumDays() == 1)
							deliveries.get(i).setStatus(3);
				}
				else if(deliveries.get(i).getRegion().compareToIgnoreCase("LUZ") == 0)
				{
						deliveries.get(i).updateDays();
						if(deliveries.get(i).getNumDays() == 2)
							deliveries.get(i).setStatus(2);
						else if(deliveries.get(i).getNumDays() == 1)
							deliveries.get(i).setStatus(3);
				}
				else if(deliveries.get(i).getRegion().compareToIgnoreCase("VIS") == 0)
				{
					deliveries.get(i).updateDays();
					if(deliveries.get(i).getNumDays()<=4 && deliveries.get(i).getNumDays()>=2)
						deliveries.get(i).setStatus(2);
					else if(deliveries.get(i).getNumDays() == 1)
						deliveries.get(i).setStatus(3);
				}
				else if(deliveries.get(i).getRegion().compareToIgnoreCase("MIN") == 0)
				{
					deliveries.get(i).updateDays();
					if(deliveries.get(i).getNumDays()<=7 && deliveries.get(i).getNumDays()>=2)
						deliveries.get(i).setStatus(2);
					else if(deliveries.get(i).getNumDays() == 1)
						deliveries.get(i).setStatus(3);
				}
			}	
			
			
			}
		}
	}
	/**
	* Returns the information of the specific Delivery whose tracking information matches the input. If the input does not match to
	* any delivery, the message "Does not Exist!" is returned instead.
	*
	* @param t Tracking Number which will be used to find the delivery.
	* @return String information of the matching delivery. Returns "Does not Exist" instead if the input does not match any delivery.
	*/
	public String showDelivery(String t)
	{
		boolean isFound = false;
		String Message = "Does not Exist!";
		for(int i =0; i<deliveries.size() && !isFound; i++)
		{
			if(t.compareToIgnoreCase(deliveries.get(i).toString())==0)
					{
						isFound = true;
						Message = deliveries.get(i).getStatus();
						
					}
		}
		
		return Message;
	}
	/**
	* Gets the number of deliveries transacted.
	*
	* @return The number of deliveries contained within the class's array.
	*/
	public int getNumDeliveries()
	{
		return deliveries.size();
	}
	/**
	* Returns the specific delivery with the matching index in the array.
	*
	* @param i Index of the desired delivery.
	* @return Specific delivery with matching index.
	*/
	public Delivery getSpecificDelivery(int i)
	{
		return deliveries.get(i);
	}
	/**
	* Returns all of the Deliveries within the object's array.
	*
	* @return Array of Deliveries.
	*/
	public ArrayList<Delivery> getDeliveries()
	{
		return deliveries;
	}

}