import com.alien.enterpriseRFID.discovery.*;
import com.alien.enterpriseRFID.reader.*;




public class IndoorLocMain implements DiscoveryListener {
	public void NetworkDiscovery() throws Exception{
		NetworkDiscoveryListenerService service = new NetworkDiscoveryListenerService();
		service.setDiscoveryListener(this);
		service.startService();
		while(service.isRunning()){
			Thread.sleep(100);
		}
	}
	public void readerAdded(DiscoveryItem discoveryItem){
		System.out.println("Added:\n" + discoveryItem.toString());
		
	}

	public void readerRenewed(DiscoveryItem discoveryItem){
		System.out.println("Renew " + discoveryItem.toString());
	}

	public void readerRemoved(DiscoveryItem discoveryItem){
		System.out.println("Removed:\n" + discoveryItem.toString());
	}
	
	public static void main(String [] args) {
		
		DiscoveryItem discoveryItem = new DiscoveryItem();
		AlienClass1Reader reader = new AlienClass1Reader();
//		NetworkDiscoveryListenerService service = new NetworkDiscoveryListenerService();
//		service.setDiscoveryListener(this);
//		try
//		{
//		service.startService();
//		}catch(AlienDiscoverySocketException e0)
//		{
//			System.out.println("Reader refused connection.");
//			System.exit(0);
//		}
//		while(service.isRunning());
//			Thread.sleep(100);
//		}
		
	
	//discoverReader opens and tests the connection with the reader
		//this discovers the reader automatically and associates it with discoveryItem
		try
		{
			reader = discoveryItem.getReader();
		}catch(AlienDiscoveryUnknownReaderException e1)
		{
			System.out.println("Reader not found.");
			System.exit(1);
		}
		
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
		System.out.println("Connection Established to " + discoveryItem.getUsername());
		System.out.println("Reader Type: " + discoveryItem.getReaderType() + " ver. " + discoveryItem.getReaderVersion());
		System.out.println("Reader Address: " + discoveryItem.getReaderAddress());
		System.out.println("Reader MAC Address: " + discoveryItem.getReaderMACAddress());
		
		//close connection with the reader for now
		reader.close();

	}
}
