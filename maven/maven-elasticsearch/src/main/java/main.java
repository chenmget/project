import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class main {
	
	 public final static String HOST = "127.0.0.1";
	    public final static int PORT = 9300; 
	public static void main(String[] args) throws IOException {

		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));
        GetResponse response = client.prepareGet("twitter", "_doc", "LGdNnWgBSP69BoX8m-_g").get();
        System.out.println(response);
        
        client.close();
	}
}
