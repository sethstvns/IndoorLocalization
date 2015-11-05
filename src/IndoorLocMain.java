import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.Tag;

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
		reader.setNetworkConnection("192.168.0.8", 23);//port here
		reader.setUsername("alien");
		reader.setPassword("password");
		
		//turn off autonomous mode if it is on
		try {
			reader.autoModeReset();
		} catch (AlienReaderException e1) {
			System.out.println("autoMode reset broke.");
			System.exit(1);
		}

		try {
			reader.getAutoMode();
		} catch (AlienReaderException e0) {
			System.out.println("test failed.");
			System.exit(0);
		}
		
		System.out.println(reader.getReaderReply());
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
		System.out.println(reader.getReaderReply());
		
		//Test for open reader 1
		if(!reader.isOpen()){
			System.out.println("Connection with reader is not open and it should be at this point");
		}
		System.out.println(reader.getReaderReply());
		//Test for open reader 2
		try
		{
		reader.test();
		}catch(AlienReaderConnectionException e6)
		{
			System.out.println("Connection with reader could not be established.");
			System.exit(6);
		}
		System.out.println(reader.getReaderReply());
		
		//set network timeout (currently 2 min)
		try
		{
			reader.setNetworkTimeout(180);
		} catch(AlienReaderException e1) {
			System.out.println("Could not set network timeout.");
			System.exit(1);
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
		
		Tag[] tagList0 = null;
		try {
			tagList0 = reader.getTagList();
		} catch (AlienReaderException e10) {
			System.out.println("Could not return tag list.");
			System.exit(10);
		}
		
		System.out.println(tagList0[0]);
		
		//close connection with the reader
		reader.close();

	}
}
