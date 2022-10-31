import java.util.*;
import java.text.*;

/**
	This class Delivery represents an object which contains the individual Delivery information used for the application. It contains
	the recipient's name, the region of delivery, the parcel of the delivery, the current status of the delivery, and the tracking number
	of the delivery.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class Delivery
{

	/*public static final String[] DESTINATIONS= 
	{
		"Manila - MML", "Luzon-LUZ", "Visayas-VIS", "Mindanao-MIN" };
	
	*/

	/**
		This constructor accepts the name of the recipient, the region index, the number of items
		the parcel has, and if it will be insured.
		
		@param r Name of the Recipient
		@param reg Region Index of the Delivery's Destination
		@param it Number of items in the Delivery's Parcel
		@param insured Boolean condition if the Parcel is insured.
	
	*/
	public Delivery(String r, int reg, int it, boolean insured)
	{
		reciepientName = r;
		setRegion(reg);
		if (it>0)
			numItems = it;
		else 
			numItems = 1;
		parcel = new Parcel(numItems);
		isInsured = insured;
		totalFee = 0.0;
		if(regionDelivery.compareToIgnoreCase("MML") == 0)
			setNumDays(2);
		else if(regionDelivery.compareToIgnoreCase("LUZ")==0)
			setNumDays(3);
		else if(regionDelivery.compareToIgnoreCase("VIS") == 0)
			setNumDays(5);
		else if(regionDelivery.compareToIgnoreCase("MIN") == 0)
			setNumDays(8);
		
	}
	
	/**
	* Sets the region index of the Delivery.
	*
	* @param reg Input to be the index of the Delivery.
	*/

	public void setRegion(int reg)
	{
		if(reg == 1)
		regionDelivery = "MML";
		else if (reg == 2)
		regionDelivery = "LUZ";
		else if (reg == 3) 
		regionDelivery = "VIS";
		else if (reg == 4) 
		regionDelivery = "MIN";
		 
			
		
	}
	
	/**
	* Returns the Parcel of the Delivery.
	*
	* @return parcel - Parcel of the Delivery.
	*/
	
	public Parcel getParcel()
	{
		return parcel;
	}
	
	/**
	* Returns the Status of the Delivery.
	*
	* @return status - Status of the Delivery.
	*/
	
	public String getStatus()
	{
		return status;
	}
	
	/**
	* Sets the Status of the Delivery.
	*
	* @param i Input to be set as status of the Delivery.
	*/
	
	public void setStatus(int i)
	{
		if(i==1)
		status = "Preparing";
		else if(i==2)
		status = "Shipping";
		else if(i==3)
		status = "Delivered";
	}
	
	/**
	* Returns the name of the Recipient of the Delivery.
	*
	* @return recipientName - Name of the Recipient.
	*/

	public String getRecipient()
	{
		return reciepientName;
	}
	
	/**
	* Returns the Region Destination of the Delivery.
	*
	* @return regionDelivery - Region Destination of the Delivery.
	*/
	
	public String getRegion()
	{
		return regionDelivery;
	}
	
	/**
	* Returns the number of items in the Delivery.
	*
	* @return numItems - Number of items in the Delivery.
	*/

	public int getNumItems()
	{
		return numItems;
	}
	
	/**
	* Returns the Starting Date of the Delivery.
	*
	* @return startDate - Starting Date of the Delivery.
	*/

	public int getStartDate()
	{
		return startDate;
	}

	/**
	* Returns the Sequence Code of the Delivery.
	*
	* @return seqCode - Sequence Code of the Delivery.
	*/

	public int getSeqCode()
	{
		return seqCode;
	}
	/**
	* Adjusts the Delivery's date.
	*
	* @param k How many days will be added to the date.
	*/
	public void dDateAdjust(int k)
	{
		if(k>=0)
		{
		int temp, ml, md, mf;
		if(deliveryDate/100 == 1 || deliveryDate/100== 3 || deliveryDate/100 == 5 || deliveryDate/100== 7 
			|| deliveryDate/100 == 8 || deliveryDate/100 == 10 || deliveryDate/100 == 12)
			{
			
				if (deliveryDate %100 > 31 && deliveryDate/100 != 12)
				{ 	temp = deliveryDate/100 + 1;
					ml = (deliveryDate%100) - 31; // check math
					temp *= 100;
					temp += k;
					deliveryDate = temp;
				}
				else if(deliveryDate %100 > 31 && deliveryDate/100 == 12)
				{
					ml = (deliveryDate%100)-31;
					deliveryDate = 100 + k;
				}
				
			}
			else if(deliveryDate/100 == 4 || deliveryDate/100 == 6 || deliveryDate/100 == 9 || deliveryDate == 11)
			{
			
			if(deliveryDate%100 > 30)
			{
				temp = (deliveryDate/100) + 1;
				md = (deliveryDate%100)-31;
					temp *= 100;
					temp += md;
					deliveryDate = temp;
			}
			}
			
			else if(deliveryDate/100 == 2)
			{	
				if(deliveryDate%100 > 28)
				{
					temp = (deliveryDate/100) + 1;
					temp *= 100;
					mf = (deliveryDate%100) - 28;
					temp += mf; 
					deliveryDate = temp;
				}
			}
		}
	}
	
	/**
	* Sets the Starting Date of the Delivery.
	*
	* @param i Input to be set as Starting Date of the Delivery.
	*/
	
	public void setStartDate(int i)
	{
		startDate = i;
		if(regionDelivery.compareToIgnoreCase("MML") == 0)
		{
			deliveryDate = startDate + 1;
			dDateAdjust(deliveryDate);
		}
		else if(regionDelivery.compareToIgnoreCase("LUZ") == 0)
		{
			deliveryDate = startDate + 3;
			dDateAdjust(deliveryDate);
			
			
		}
		else if(regionDelivery.compareToIgnoreCase("VIS") == 0)
		{
			deliveryDate = startDate + 5;
			dDateAdjust(deliveryDate);
		}
		else if(regionDelivery.compareToIgnoreCase("MIN") == 0)
		{
			deliveryDate = startDate + 8;
			dDateAdjust(deliveryDate);
		}
	}
	
	/**
	* Sets the Sequence Code of the Delivery.
	*
	* @param i Input to be set as Sequence Code of the Delivery.
	*/
	
	public void setSeqCode(int i)
	{
		seqCode = i;
	}
	
	/**
	* Sets the number of days the Delivery will have.
	*
	* @param i Input to be set as number of days of the Delivery.
	*/
	
	public void setNumDays(int i)
	{
		numDays = i;
	}
	
	/**
	* Gets the number of days the Delivery has.
	*
	* @return numDays - Number of days the Delivery has.
	*/
	
	public int getNumDays()
	{
		return numDays;
	}
	
	/**
	* Updates the number of days left for the delivery.
	*
	*/
	
	public void updateDays()
	{
		numDays--;
	}
	
	/**
	* Returns the delivery's total fee.
	*
	* @return The total fee of the delivery.
	*/
	
	public double getTotalFee()
	{
		return totalFee;
	}
	
	/**
	* Computes the total fee the Delivery will have.
	*
	* 
	*/
	
	public void computeTotalFee()
	{
		double[] weight = new double[3];
		double totWeightcomp = 0.0;
	
		weight[0] = parcel.getTotalWeightofIrregObj();
		weight[1] = parcel.getTotalVolWeightofIrregObj();
		weight[2] = parcel.getTotalWeightofNonIrregObj();
	
		totWeightcomp += weight[2];
		if(weight[0]>weight[1])
			totWeightcomp+=weight[0];
		else
			totWeightcomp+=weight[1];
		
		parcel.computeParcelCost();
		if(regionDelivery.compareToIgnoreCase("MML") == 0)
				totalFee += 50.00;
			else if(regionDelivery.compareToIgnoreCase("LUZ") == 0)
				totalFee += 100.00;
			else if(regionDelivery.compareToIgnoreCase("VIS") == 0)
			{
				if((0.10*totWeightcomp) >1000.00)
					totalFee += (0.10 * totWeightcomp);
				else 
					totalFee += 1000.00;
			}
			else if(regionDelivery.compareToIgnoreCase("MIN") == 0)
			{
				if((0.25*totWeightcomp) >3000.00)
					totalFee += (0.25*totWeightcomp);
				else 
					totalFee += 3000.00;
			}
		
		
		if(isInsured)
		{
			totalFee += parcel.getItems().size()*5.00;
		}

		totalFee += parcel.getParcelCost();
		
	}
	
	
	/**
	* Returns the details of the Delivery in String Format.
	*
	* @return Delivery Details in String Format.
	*/
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#000");
		DecimalFormat df1 = new DecimalFormat("#00");
		DecimalFormat df2 = new DecimalFormat("#0000");
		return parcel.showParcelChoice() +  df2.format(startDate) + regionDelivery + df1.format(numItems) + df.format(seqCode);
	}
	/**
	* Returns the date when the delivery was prepared.
	*
	* @return The date of the delivery's preparation.
	*/
	public int getDeliveryDate()
	{
		return deliveryDate;
	}
	
	
private String reciepientName;
private String status;
private String regionDelivery;
private int numItems;
private Parcel parcel;
private boolean isInsured;
private int startDate;
private int deliveryDate;
private int seqCode;
private int numDays;
private double totalFee;
//public static final String[] DESTINATIONS;



}