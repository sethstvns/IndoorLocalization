import com.alien.enterpriseRFID.reader.*;

public class IndoorLocMain {
		
	public static void main(String [] args) {
		
		
		
	
	//discoverReader opens and tests the connection with the reader
		//this discovers the reader automatically and associates it with discoveryItem
//		try
//		{
//			reader = discoveryItem.getReader();
//		}catch(AlienDiscoveryUnknownReaderException e1)
//		{
//			System.out.println("Reader not found.");
//			System.exit(1);
//		}
		
		//Create a connection with the known IP
		AlienClass1Reader reader = new AlienClass1Reader();
		reader.setConnection("192.168.0.8", 5000);//port here
				
		//this opens the connection with the reader for communication
		try
		{
			reader.open();
		}catch(AlienReaderConnectionRefusedException e2)
		{
			System.out.println("Reader refused connection.");
			System.exit(2);
		}catch(AlienReaderConnectionException e3)
		{
			System.out.println("Connection with reader could not be established.");
			System.exit(3);
		}catch(AlienReaderNotValidException e4)
		{
			System.out.println("Connected reader is not an Alien reader.");
			System.exit(4);
		}catch(AlienReaderTimeoutException e5)
		{
			System.out.println("Communication with reader timed out.");
			System.exit(5);
		}
		
		//Test for open reader 1
		if(!reader.isOpen()){
			System.out.println("Connection with reader is not open and it should be at this point");
		}
		
		//Test for open reader 2
		try
		{
		reader.test();
		}catch(AlienReaderConnectionException e6)
		{
			System.out.println("Connection with reader could not be established.");
			System.exit(6);
		}
		
		//output identifiers for the reader
		System.out.println("Connection Established to " + reader.getUsername());
		try {
			System.out.println("Reader Type: " + reader.getReaderType() + " ver. " + reader.getReaderVersion());
		} catch (AlienReaderException e7) {
			
			System.out.println("Could not read ReaderType/ReaderVersion.");
			System.exit(7);
		}
		
		try {
			System.out.println("Reader Address: " + reader.getIPAddress());
		} catch (AlienReaderException e8) {
			System.out.println("Could not read ReaderIP.");
			System.exit(8);
		}
		
		try {
			System.out.println("Reader MAC Address: " + reader.getMACAddress());
		} catch (AlienReaderException e9) {
			System.out.println("Could not read ReaderMAC.");
			System.exit(9);
		}
		
		//close connection with the reader for now
		reader.close();

	}
}
