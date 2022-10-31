/**
	This class IrregularObject represents an object with Irregular Dimensions and Volumetric Weight.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class IrregularObj extends Item
{

	/**
		This constructor recieves the name, length, width, height and weight of the object.
		
		@param name Name of the object.
		@param l Length of the object.
		@param w Width of the object.
		@param h Height of the object.
		@param we Weight of the object.
	*/
	public IrregularObj(String name, double w, double l, double h, double we)
	{
		super(name, w, l, h, we);
		
	}
	
	
}