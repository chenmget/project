package demo.util;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class EsCrud {

	public static void createIndex(String index, String type, String document) throws IOException {
		IndexResponse response = EsClient.getConnection().prepareIndex(index, type)
				.setSource(document, XContentType.JSON).get();
		System.out.println("索引名称：" + response.getIndex());
		System.out.println("类型：" + response.getType());
		System.out.println("文档ID：" + response.getId());// OSW6gGkBFez86AxJFIBI
		System.out.println("当前实例状态：" + response.status());
	}

	public static void queryById(String index, String type, String id) throws IOException {
		try {
			GetResponse response = EsClient.getConnection().prepareGet(index, type, id).execute().actionGet();
			System.out.println(response.getSourceAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void query(String index, String type, Map<String, String> map) throws IOException {
		try {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			for (String in : map.keySet()) {
				// map.keySet()返回的是所有key的值
				String str = map.get(in);// 得到每个key多对用value的值
				boolQueryBuilder.must(QueryBuilders.termQuery(in, str));
			}
			SearchResponse  response = EsClient.getConnection().prepareSearch(index).setTypes(type).setQuery(boolQueryBuilder).execute().actionGet();
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update(String index, String type, String document, String id) {

		UpdateResponse response = EsClient.getConnection().prepareUpdate(index, type, id)
				.setDoc(document, XContentType.JSON).get();
		System.out.println("索引名称：" + response.getIndex());
		System.out.println("类型：" + response.getType());
		System.out.println("文档ID：" + response.getId());
		System.out.println("当前实例状态：" + response.status());
	}
}
