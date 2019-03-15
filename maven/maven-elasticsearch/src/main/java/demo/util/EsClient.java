package demo.util;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class EsClient {
	static TransportClient client = null;
	
	public final static String HOST = "127.0.0.1";
	public final static int PORT = 9300;
	
	private EsClient() {
		try {
			Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST), PORT));
		} catch (Exception ex) {
			client.close();
		} finally {

		}
	}

	public static TransportClient getConnection() {
		if (client == null) {
			synchronized (EsClient.class) {
				if (client == null) {
					new EsClient();
				}
			}
		}
		return client;
	}
}
