
import java.util.*; 
import java.lang.*; 
import java.io.*; 
/**
	This class Parcel represents a Parcel a set of possible dimensions, and an ArrayList of items.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class Parcel
{
	/**
		This constructor accept the total count of items that the Parcel will have.
		
		@param totalCount The total amount of items inside the Parcel.
	*/

	public Parcel(int totalCount)
	{
		dimensions = new Dimension[6];
		dimensions[0] = new Dimension(9, 14, 1, 3);
		dimensions[1] = new Dimension(12, 18, 3, 3);
		dimensions[2] = new Dimension(12, 10, 5);
		dimensions[3] = new Dimension(14, 11, 7);
		dimensions[4] = new Dimension(18, 12, 9);
		dimensions[5] = new Dimension(20, 16, 12);
		
		
		possibleParcels = new ArrayList <Integer> ();
		totalItems = totalCount;
		items = new ArrayList <Item> ();
		maxParcelCost = 0;
		totalWeightIrreg = 0;
		totalWeightNonIrreg = 0;
		totalVolWeightIrreg = 0;
		
		
		
	}
	
	/**
	* Removes an index for a Parcel Dimension.
	*
	* @param i Index number to be removed from list of Possible Parcels.
	*/
	
	public void removePossibleParcels(int i)
	{
		if(possibleParcels.size()>0)
		{
		possibleParcels.remove(possibleParcels.indexOf(i));
		}
	}
	
	/**
	* Adds a Document Object Class item to the Parcel.
	*
	* @param doc Document Object Class to be added to the Parcel.
	*/
	
	public void addItem(Document doc)
	{

		items.add(doc);
	
	
	}
	
	/**
	* Adds a Product Object Class item to the Parcel.
	*
	* @param prod Product Object Class to be added to the Parcel.
	*/
	
	public void addItem(Product prod)
	{
		
		items.add(prod);

		
	}
	
	/**
	* Adds an Irregular Object Class item to the Parcel.
	*
	* @param io Irregular Object Class to be added to the Parcel.
	*/
	
	public void addItem(IrregularObj io)
	{
		items.add(io);
		
		
	}
	/**
	* Sorts the dimensions of the input item into descending order.
	*
	* @param il Item whose dimensions are to be sorted.
	*/
	public void sortItemDimension(Item il)
	{
		double[] dimensions = new double[3];
		
		dimensions[0] = il.getDimensions().getLength();
		dimensions[1] = il.getDimensions().getWidth();
		dimensions[2] = il.getDimensions().getHeight();
		
		Arrays.sort(dimensions);
		
		il.getDimensions().setLength(dimensions[2]);
		il.getDimensions().setWidth(dimensions[1]);
		il.getDimensions().setHeight(dimensions[0]);
	}
	
	
	/**
	* Sorts items by Length.
	*
	*/
	public void sortItemsLength()
	{
		 Collections.sort(items, Item.lengthComparator);
	}
	/**
	* Sorts items by Width.
	*
	*/
	public void sortItemsWidth()
	{
		 Collections.sort(items, Item.widthComparator);
	}
	/**
	* Sorts items by Weight.
	*
	*/
	public void sortItemsWeight()
	{
		 Collections.sort(items, Item.weightComparator);
	}
	/**
	* Sorts items by Height.
	*
	*/
	
	public void sortItemsHeight()
	{
		 Collections.sort(items, Item.heightComparator);
	}
	/**
	* Checks if the total dimension of items can fit on a specific parcel.
	*	@param currD Dimension type representing a variation of Dimension
	*	@param currD1 Dimension type representing a variation of Dimension
	*/
	public void doesFit(Dimension currD, Dimension currD1)
	{
		int g;
		boolean found, ctrl;
		

		
	for(int j=0; j<6;j++)
	{
		
		found = false;
		ctrl = false;
		
	if(!ctrl)	
	{	
		
	
		if(((currD.getLength()<=dimensions[j].getLength() && currD.getWidth()<=dimensions[j].getWidth())
			|| (currD.getWidth()<=dimensions[j].getLength() && currD.getLength() <= dimensions[j].getWidth()))
				&& currD.getHeight()<= dimensions[j].getHeight())
				{	
				ctrl = true;
				for(g=0; g<possibleParcels.size();g++)
				{
					if(possibleParcels.get(g) == j)
						found = true;
				}
					if(!found)
					{
					ctrl = true;
					possibleParcels.add(j);
					}
				}
		else if(((currD1.getLength()<=dimensions[j].getLength() && currD1.getWidth()<=dimensions[j].getWidth()) || 
					(currD1.getWidth()<=dimensions[j].getLength() && currD1.getLength() <= dimensions[j].getWidth()))
				&& currD1.getHeight()<= dimensions[j].getHeight())
				{	
				ctrl = true;
				for(g=0; g<possibleParcels.size();g++)
				{
					if(possibleParcels.get(g) == j)
						found = true;
				}
					if(!found)
					{
					ctrl = true;
					possibleParcels.add(j);
					}
				}	
			
	}	

	}
	
	
		
	}
	/**
	* Checks the parcels which can contain the arraylist of items.
	*
	* 
	*/
	public void checkAvailableParcels()
	{
		int i, f;
		double itWe = 0.0;
		double itHe = 0.0;
		double itLe = 0.0;
		double itWi = 0.0;
		boolean isItemAdded = true;
		boolean added = true;
		boolean found = false;
		Dimension tempd, tempd1;
		tempd1 = new Dimension(0,0,0,0);
		tempd = new Dimension(0,0,0,0);
		
		sortItemsWeight();
		
		for(f=0; f<items.size(); f++)
		{
			sortItemDimension(items.get(f));
		}
		
		for(f=0; f<items.size(); f++)
		{	
				
				itWe +=items.get(f).getDimensions().getWeight();
				itHe += items.get(f).getDimensions().getHeight();
				itLe += items.get(f).getDimensions().getLength();
				itWi += items.get(f).getDimensions().getWidth();
				
		}
		
		//currDimension.setWeight(itWe);
		
		tempd1.setHeight(itHe);
		sortItemsLength();
		tempd1.setLength(items.get(0).getDimensions().getLength());
		sortItemsWidth();
		tempd1.setWidth(items.get(0).getDimensions().getWidth());
		sortItemsHeight();
		
		tempd.setLength(itLe);
		tempd.setWidth(itWi);
		tempd.setHeight(items.get(0).getDimensions().getHeight());
		if(itWe<=3.00)
		{
			
			doesFit(tempd, tempd1);
		}
		
			
		
		
		else if (itWe>3.00)
		{
			
			doesFit(tempd, tempd1);
		for(int mal = 0; mal<possibleParcels.size(); mal++)
		{
			if(possibleParcels.get(mal) == 0)
				removePossibleParcels(0);
			if(possibleParcels.get(mal) == 1)
			removePossibleParcels(1);
		}
		}
		
		
	}
	
	
	/**
	* Displays the list of Parcels in which the item can fit in.
	*
	* 
	*/
	
	public void displayPossibleParcels()
	{ 
	int i;
	checkAvailableParcels();
	System.out.println("xx:" + possibleParcels.size());
	if(possibleParcels.size()>0)
	{
		for(i=0; i<possibleParcels.size(); i++)
		{
			
			System.out.println((i+1) + ". Parcel with: " + dimensions[possibleParcels.get(i)].getLength() + " x "  
			+dimensions[possibleParcels.get(i)].getWidth() + " x "+ dimensions[possibleParcels.get(i)].getHeight());
		}
	}
	else 
		System.out.println("No Available Parcels!");
	}
	
	/**
	* Sets index of the chosen Parcel Size.
	*
	* @param i - Index of the chosen Parcel Size.
	*/
	
	public void setParcelChoice(int i)
	{
		parcelChoice = i-1;
	}
	/**
	* Gets the total weight of an Irregular object.
	*
	*	@return total weight of Irregular Objects
	*/
	public double getTotalWeightofIrregObj()
	{
		int compuloop;
		for(compuloop =0; compuloop<items.size(); compuloop++)
			{
					if(items.get(compuloop) instanceof IrregularObj)
					{
							totalWeightIrreg += Math.ceil(items.get(compuloop).getDimensions().getWeight());
					}
					
			}
		
		return totalWeightIrreg;
	}
	/**
	* Gets the total volumetric weight of an Irregular object.
	*
	*	@return total volumetric weight of Irregular Objects
	*/
	public double getTotalVolWeightofIrregObj()
	{
		int compuloop1;
		double volWeight;
		for(compuloop1 =0; compuloop1<items.size(); compuloop1++)
			{
				
					if(items.get(compuloop1) instanceof IrregularObj)
					{
						volWeight = (items.get(compuloop1).getDimensions().getLength() * items.get(compuloop1).getDimensions().getWidth() 
										*items.get(compuloop1).getDimensions().getHeight())/305;
						
							totalVolWeightIrreg += Math.ceil(volWeight);
					}
					
			}
		
		return totalVolWeightIrreg;
	}
	/**
	* Gets the total weight of a non Irregular object.
	*	
	*	@return total weight of non Irregular Object
	*
	*/
	public double getTotalWeightofNonIrregObj()
	{
		int compuloop2;
		for(compuloop2 =0; compuloop2<items.size(); compuloop2++)
			{
					if(!(items.get(compuloop2) instanceof IrregularObj))
					{
							totalWeightNonIrreg += Math.ceil(items.get(compuloop2).getDimensions().getWeight());
							
					}
					
			}
		
		return totalWeightNonIrreg;
	}
	/**
	* Computes the cost of the Parcel to be used.
	*
	* 
	*/

	public void computeParcelCost()
	{
		int compuloop; 
		
	
			if(possibleParcels.get(parcelChoice) == 0)
			{	
				maxParcelCost += 30;
				if(totalWeightIrreg !=0 || totalVolWeightIrreg!=0)
				{
				if(totalWeightIrreg*40 >= totalVolWeightIrreg*30)
					maxParcelCost += (totalWeightIrreg*40);
				else if(totalWeightIrreg*40 < totalVolWeightIrreg*30)
					maxParcelCost += (totalVolWeightIrreg*30);
				}
			}
			else if(possibleParcels.get(parcelChoice) == 1)
			{
				maxParcelCost += 50;
				if(totalWeightIrreg !=0 || totalVolWeightIrreg!=0)
				{
				if(totalWeightIrreg*40 >= totalVolWeightIrreg*30)
					maxParcelCost += (totalWeightIrreg*40);
				else if(totalWeightIrreg*40 < totalVolWeightIrreg*30)
					maxParcelCost += (totalVolWeightIrreg*30);
				}
			}
			else if (possibleParcels.get(parcelChoice) >= 2 && (possibleParcels.get(parcelChoice) <= 5))
			{
				maxParcelCost += (40*totalWeightNonIrreg);
				if(totalWeightIrreg !=0 || totalVolWeightIrreg!=0)
				{
				if(totalWeightIrreg*40 >= totalVolWeightIrreg*30)
					maxParcelCost += (totalWeightIrreg*40);
				else if(totalWeightIrreg*40 < totalVolWeightIrreg*30)
					maxParcelCost += (totalVolWeightIrreg*30);
				}
				
				
			}
			
	}
	
	/**
	* Returns the cost of the Parcel.
	*
	* @return maxParcelCost - Cost of the Parcel.
	*/

	public double getParcelCost()
	{
		return maxParcelCost;
	}
	
	/**
	* Returns the type of Parcel to be used in String format
	*
	* @return The String "FLT" if parcelChoice is less than 2.; The String "BOX" if parcelChoice is greater or equal to 2.
	*/
	
	public String showParcelChoice()
	{
	if(possibleParcels.get(parcelChoice) == 0 || possibleParcels.get(parcelChoice) == 1)
		return "FLT";
	else 
		return "BOX";
	}
	
	
	/**
	* Returns the ArrayList of items contained in the Parcel.
	*
	* @return ArrayList of contained items/
	*/
	public ArrayList<Item> getItems()
	{
		return items;
	}
		
	/**
	* Returns type String of the Parcel.
	*
	* @return parcelType - Type of Parcel set in the Object Class.
	*/
	
	public String getParcelType()
	{
		return parcelType;
	}
	
	/**
	* Returns the ArrayList of possible Parcels that can be used.
	*
	* @return ArrayList of valid parcels.
	*/
	public ArrayList<String> getPossibleParcels()
	{
		int i, l, w, h;
		checkAvailableParcels();
		ArrayList<String> strPossParcels = new ArrayList<String>(possibleParcels.size());
		
		for (i = 0; i<possibleParcels.size(); i++)
		{
			l = (int) dimensions[possibleParcels.get(i)].getLength();
			w = (int) dimensions[possibleParcels.get(i)].getWidth();
			h = (int) dimensions[possibleParcels.get(i)].getHeight();
			strPossParcels.add(l + " x " + w + " x " + h);
		}
		
		return strPossParcels;
	}


private String parcelType;
private Dimension[] dimensions;
private int totalItems;
private int parcelChoice;
private ArrayList<Item> items;
private double maxParcelCost;
private ArrayList<Integer> possibleParcels;
private double totalWeightIrreg;
private double totalVolWeightIrreg;
private double totalWeightNonIrreg;




}