import java.util.*;




public class JohnnyMoves
{

	public static void main(String args[])
	{
		
		int menuChoice = -1, regchoice, totItem, seqNum =0;
		String name, insure, dName, tracks, pass;
		boolean isCorrect = false;
		double dwidth, dlength; 
		int dpages, currDate;
	
		Scanner sc = new Scanner(System.in);
		Tracker tr1 = new Tracker();
		Thread t1 = new Thread(tr1);
		t1.start();
		currDate = tr1.getDate();
		
		while(menuChoice != 3)
		{
			
			if(currDate != tr1.getDate())
			{
				currDate = tr1.getDate();

			}
			System.out.println("========================================================================");	
			System.out.println(tr1.showDate());
			
		System.out.println();
		System.out.println("Welcome To Johnny Moves!");
		System.out.println();
		System.out.println("What do you want to do today?");
		System.out.println("[1] Make a Shipment");
		System.out.println("[2] Track an Item");
		System.out.println("[3] Exit ");
		System.out.print("Your Choice:");
		menuChoice = sc.nextInt();
		sc.nextLine();
			
		switch(menuChoice)
		{
			case 1: tr1.updateSeqNum();
					System.out.println();
					System.out.println("Making a new Shipping...");
					System.out.println();
					System.out.print("Enter Name of Recipient: ");
					name = sc.nextLine();
					System.out.println();
					System.out.println("Choose an intended region of destination");
					System.out.println("[1] Metro Manila");
					System.out.println("[2] Provincial Luzon");
					System.out.println("[3] Visayas");
					System.out.println("[4] Mindanao");
					System.out.print("Your Choice: ");
					regchoice = sc.nextInt();
					System.out.println();
					System.out.print("Enter number of items to be shipped: ");
					totItem = sc.nextInt();
					sc.nextLine();
					System.out.println();
					System.out.print("Would you like to ensure your parcel? [Yes/No]: ");
					insure = sc.nextLine();
					System.out.println();
					Delivery d1 = new Delivery(name, regchoice, totItem,insure);
					
					for(int i = 0; i<totItem; i++)
					{	System.out.println("Entering details for Item Type Document");
						System.out.print("Enter item name: ");
						dName = sc.nextLine();
					
						System.out.print("Enter length: ");
						dlength = sc.nextDouble();
						System.out.print("Enter width: ");
						dwidth = sc.nextDouble();
						System.out.print("Enter pages: ");
						dpages = sc.nextInt();
							sc.nextLine();
						Document dc1 = new Document(dName, dlength, dwidth, dpages);
						System.out.println("Adding item #" + (i+1) + " of " + totItem);
						d1.getParcel().addItem(dc1);
						
			
						
					}
					
					System.out.println();
					System.out.println("Choose among valid parcel/s");
					d1.getParcel().displayPossibleParcels();
					System.out.print("Your Choice: ");
					int pChoice = sc.nextInt();
					d1.getParcel().setParcelChoice(pChoice);
					System.out.println();

					
					d1.computeTotalFee();
					System.out.println();
					d1.setStatus(1);
					d1.setStartDate(tr1.getDate());
					d1.setSeqCode(tr1.getSeqNum());
					tr1.updateSeqNum();
					tr1.getDeliInfo().addDelivery(d1);
					System.out.print("Shipping Booked! Details are: ");
					System.out.println(d1.toString());
					break;
					
			case 2: System.out.println("========================================================================");
					System.out.println(tr1.showDate());
					System.out.println("Welcome to Shipping Tracker!");
					System.out.print("Enter tracking number: ");
					tracks = sc.nextLine();
					System.out.println("Status: " + tr1.showSpecificShipping(tracks));
					System.out.println("========================================================================");
					break;
			case 3: 
				while(!isCorrect)
				{					
					System.out.print("Enter admin password: ");
					pass = sc.nextLine();
				
					if(pass.compareToIgnoreCase("password")==0)
					{
							isCorrect = true;
					}
					else 
					{
					isCorrect = false; 
					}
				}
				break;
			}
			
		}
		tr1.terminate();
		sc.close();
		
		
		
		
		
		
	}

























}