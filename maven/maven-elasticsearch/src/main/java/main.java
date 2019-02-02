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
	    // http����Ķ˿���9200���ͻ�����9300
	    public final static int PORT = 9300; 
	public static void main(String[] args) throws IOException {
//		 String hostName = "127.0.0.1";
//		// on startup
//		 Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();
//	    //TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), 9300);
//	    TransportClient client = new PreBuiltTransportClient(settings).
//	    		addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9200));
//	    client.prepareIndex("1", "1").setSource("1", XContentType.JSON);
//		 // on shutdown
//		 client.close();
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        // ����client
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));
        
//        String json = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//            "}";
//
//        IndexResponse response = client.prepareIndex("twitter", "_doc")
//                .setSource(json, XContentType.JSON)
//                .get();
//        System.out.println(response.getId());  //LGdNnWgBSP69BoX8m-_g
        
        GetResponse response = client.prepareGet("twitter", "_doc", "LGdNnWgBSP69BoX8m-_g").get();
        System.out.println(response);
        
        client.close();
	}
}
