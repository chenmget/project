import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import demo.util.EsCrud;
import net.sf.json.JSONObject;

public class main {


	public static void main(String[] args) throws IOException{
//		 JSONObject jsonObject = new JSONObject();
//		 jsonObject.put("orderNo", new
//		 SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "001");
//		 jsonObject.put("orderName", "购买元宝");
//		 jsonObject.put("orderTime", new Date());
//		 jsonObject.put("price", 1.5);
//		 jsonObject.put("ip", "192.168.1.111");
//		 
//		 EsCrud.createIndex("rxpay", "order", jsonObject.toString());
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("price","1.6");
		EsCrud.query("rxpay", "order", map);
	}
}
