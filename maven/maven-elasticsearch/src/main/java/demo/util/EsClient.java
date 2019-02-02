package demo.util;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class EsClient {
	  static TransportClient client = null;
	    private  EsClient(){
	        try{
	        	 String hostName = "127.0.0.1";
	        	TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), 9300);
	      	    TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).
	      	    		addTransportAddress(transportAddress);
	        }catch (Exception ex){
	            client.close();
	        }finally {

	        }
	    }
	    public static  TransportClient getConnection(){

	           if (client==null){
	               synchronized (EsClient.class){
	                   if (client==null){
	                       new EsClient();
	                   }
	               }
	           }
	           return  client;

	    }
}
