/**
	This class Dimension represents the Physical Dimensions of an object, consisting of
	its width, length, height and weight.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class Dimension
{
	private double width;
	private double length;
	private double height;
	private double weight;
	
	/** This constructer recieves the length, width, height and weight for
		the Dimension object.
	
		@param l Represents the Dimensions' length.
		@param wi Represents the Dimensions' width.
		@param h Represents the Dimensions' height.
		@param we Represents the Dimensions' weight.
	*/
	
	public Dimension(double l, double wi, double h, double we)
	{
		width = wi;
		length = l;
		height = h;
		weight = we;
	}

	/** This constructer recieves the length, width and height for the 
		Dimension object.
	
		@param l Represents the Dimensions' length.
		@param wi Represents the Dimensions' width.
		@param h Represents the Dimensions' height.
	*/
	public Dimension(double l, double wi, double h)
	{
		width = wi;
		length = l;
		height = h;
		weight = 0;
	}
	
	/**
	* Returns the Width of the Dimensions.
	*
	* @return width - Width value of the object class (in double format).
	*/
	
	public double getWidth()
	{
		return width;
	}
	
	/**
	* Returns the Length of the Dimensions.
	*
	* @return length - Length value of the object class (in double format).
	*/
	
	public double getLength()
	{
		return length;
	}
	
	/**
	* Returns the Height of the Dimensions.
	*
	* @return height - Height value of the object class (in double format).
	*/
	
	public double getHeight()
	{
		return height;
	}
	
	/**
	* Returns the Weight of the Dimensions.
	*
	* @return weight - Weight value of the object class (in double format).
	*/
	
	public double getWeight()
	{
		return weight;
	}
	
	/**
	* Sets the Length of the Dimensions.
	*
	* @param l Input to be set as Length of the Dimensions.
	*/
	
	public void setLength(double l)
	{
		length = l;
	}
	
	/**
	* Sets the Width of the Dimensions.
	*
	* @param w Input to be set as Width of the Dimensions.
	*/
	
	public void setWidth(double w)
	{
		width = w;
	}
	
	/**
	* Sets the Weight of the Dimensions.
	*
	* @param w Input to be set as Weight of the Dimensions.
	*/
	
	public void setWeight(double w)
	{
		weight = w;
	}
	
	/**
	* Sets the Height of the Dimensions.
	*
	* @param h Input to be set as Height of the Dimensions.
	*/
	
	public void setHeight(double h)
	{
		height = h;
	}
	
	/**
	* Adds a Length value to the Dimensions' current Length.
	*
	* @param l Input to be added to the current Length of the Dimensions.
	*/
	
	public void addLength(double l)
	{
		length += l;
	}

	/**
	* Adds a Width value to the Dimensions' current Width.
	*
	* @param w Input to be added to the current Width of the Dimensions.
	*/
	
	public void addWidth(double w)
	{
		width += w;
	}

	/**
	* Adds a Height value to the Dimensions' current Height.
	*
	* @param h Input to be added to the current Height of the Dimensions.
	*/
	
	public void addHeight(double h)
	{
		height += h;
	}

	/**
	* Adds a Weight value to the Dimensions' current Weight.
	*
	* @param w Input to be added to the current Weight of the Dimensions.
	*/
	
	public void addWeight(double w)
	{
		weight += w;
	}
}