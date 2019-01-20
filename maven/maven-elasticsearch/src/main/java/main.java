import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class main {
	public static void main(String[] args) throws UnknownHostException {
		 String hostName = "127.0.0.1";
		// on startup
	    TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), 9300);
	    TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).
	    		addTransportAddress(transportAddress);

		 // on shutdown
		 client.close();
	}
}
