/**
	This class Document represents a Document with Dimensions and Pages.
	
	@author Marco Jameson D. Pangilinan and Adrian A. Donato
	@version 1.0


*/
public class Document extends Item 
{
	/**
		This constructor recieves the name, length, width and number of pages as parameters for the document.
		
		@param name Name of the document.
		@param length Length of the document.
		@param width Width of the document.
		@param pages Number of pages the document has.
	*/

	public Document(String name, double length, double width, int pages)
	{
		super(name, width, length, pages/25.0, ((pages/25.0)*200)/1000);
		this.pages = pages;
	}
	
	/**
	* Returns the number of pages of the Document.
	*
	* @return Pages of the Document.
	*/

	public int getNumPages()
	{
		return pages;
	}

	private int pages;
}