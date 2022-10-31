/**
	This class Product represents an object with Dimensions and Volumetric Weight.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class Product extends Item
{

	/**
		This constructor recieves the name, length, width, height and weight of the product.
		
		@param name Name of the product.
		@param l Length of the product.
		@param w Width of the product.
		@param h Height of the product.
		@param we Weight of the product.
	*/

	public Product(String name, double l, double w, double h, double we)
	{
		super(name, w, l, h, we);
		
	}
	

	
}