import com.alien.enterpriseRFID.discovery.*;

public class NetworkDiscoveryTest implements DiscoveryListener {

public NetworkDiscoveryTest() throws Exception{
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
}

