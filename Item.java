import java.util.*;
/**
	The class Item represents an object with a given name and dimensions. It is an abstract class and therefore cannot be instantiated.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public abstract class Item
{
	private String	  name;	
	private Dimension dimensions;
	
	/**
	* This constructor accepts the name String value and the width, length, height, and weight double values as parameters.
	*
	* @param n Name of the Item.
	* @param wi Width dimension attribute of the Item.
	* @param l Length dimension attribute of the Item.
	* @param h Height dimension attribute of the Item.
	* @param we Weight dimension attribute of the Item.
	*/
	public Item(String n, double wi, double l, double h, double we)
	{
		name = n; 
		dimensions = new Dimension(wi, l, h, we);
	}
	
	/**
	* Returns the Item's name.
	*
	* @return The name of the Item, in String format.
	*/
	public String getName()
	{
		return name;
	}
	/**
	* Returns the Item's dimensions.
	*
	* @return The Dimensions of the item.
	*/
	public Dimension getDimensions()
	{
		return dimensions;
	}
	/**
	* Compares the length of two items.
	*
	*/
  public static Comparator<Item> lengthComparator = new Comparator<Item>() {         

    @Override         
	/**
	* Compares the weight of two items.
	*
	* @param it1 First item to be compared.
	* @param it2 Other item to be compared.
	* @return int value of Parcel with greater length
	*/
    public int compare(Item it1, Item it2) {             

      return (it2.getDimensions().getLength() < it1.getDimensions().getLength() ? -1 :                     

              (it2.getDimensions().getLength() == it1.getDimensions().getLength() ? 0 : 1));           

    }     

  };       
	/**
	* Compares the weight of two items.
	*
	*/
  public static Comparator<Item> weightComparator = new Comparator<Item>() {         

    @Override         
	/**
	* Compares the weight of two items.
	*
	* @param it1 First item to be compared.
	* @param it2 Other item to be compared.
	* @return int value of Parcel with greater weight
	*/
   public int compare(Item it1, Item it2) {             

      return (it2.getDimensions().getWeight() < it1.getDimensions().getWeight() ? -1 :                     

              (it2.getDimensions().getWeight() == it1.getDimensions().getWeight() ? 0 : 1));           

    }    

  }; 
	/**
	* Compares the width of two items.
	*
	*/
  public static Comparator<Item> widthComparator = new Comparator<Item>() {         

    @Override         
	/**
	* Compares the weight of two items.
	*
	* @param it1 First item to be compared.
	* @param it2 Other item to be compared.
	* @return int value of Parcel with greater width
	*/
   public int compare(Item it1, Item it2) {             

      return (it2.getDimensions().getWidth() < it1.getDimensions().getWidth() ? -1 :                     

              (it2.getDimensions().getWidth() == it1.getDimensions().getWidth() ? 0 : 1));           

    }    

  }; 
	/**
	* Compares the height of two items.
	*
	*/
  public static Comparator<Item> heightComparator = new Comparator<Item>() {         

    @Override         
	/**
	* Compares the weight of two items.
	*
	* @param it1 First item to be compared.
	* @param it2 Other item to be compared.
	* @return int value of Parcel with greater height
	*/
   public int compare(Item it1, Item it2) {             

      return (it2.getDimensions().getHeight() < it1.getDimensions().getHeight() ? -1 :                     

              (it2.getDimensions().getHeight() == it1.getDimensions().getHeight() ? 0 : 1));           

    }    

  };

}