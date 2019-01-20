package demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import demo.model.EmployeeVo;
import demo.util.EsClient;
import net.sf.json.JSONObject;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static TransportClient client=EsClient.getConnection();
	
	public void save(EmployeeVo vo) throws IOException {
		String builder = this.getBuilder(vo);
        if (vo.getId() == null) {
            // 若不传入 ID 则表示新增
            client.prepareIndex(INDEX, TYPE).setSource(builder, XContentType.JSON);
        } else {
            // 若传入 ID 则表示修改
            client.prepareUpdate(INDEX, TYPE, vo.getId()).setDoc(builder).get();
        }
	}
	
	private String getBuilder(EmployeeVo vo) throws IOException {
		//Map<Object,Object> map=ReflectionUtil.objectToMap(vo);
		return JSONObject.fromObject(vo).toString();
    }
	

	public void delete(EmployeeVo vo) {
		if (vo.getIds() != null) {
            vo.getIds().forEach(id -> client.prepareDelete(INDEX, TYPE, id).get());
        }
	}

	public EmployeeVo search(EmployeeVo vo) {
		// boolQuery ：可以叠加多个查询条件，相当于SQL的 AND
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		 // multiMatchQuery ：多字段搜索，输入关键字，可以从多个字段匹配
        if (vo.getKeyword() != null) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(vo.getKeyword(), NAME, PHONE, POST, DESC));
        }
        // matchQuery ：单字段搜索
        if (vo.getName() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(NAME, vo.getName()));
        }
        // wildcardQuery ：通配符匹配：类似于SQL里面的 LIKE
        if (vo.getPhone() != null) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(PHONE, "*" + vo.getPhone() + "*"));
        }
        if (vo.getPost() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(POST, vo.getPost()));
        }
        if (vo.getDesc() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(DESC, vo.getDesc()));
        }
        // termQuery ： 通常用于非字符串类型的比较，比如性别为男的或者女的，状态为1或者2什么的。。总之比较固定值
        if (vo.getSex() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery(SEX, vo.getSex()));
        }
        if (vo.getSalary() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery(SALARY, vo.getSalary()));
        }
        // rangeQuery ：范围查询，判断一个值是否在范围内，比如工资大于多少，年龄多少之间，是否在有效期内。。。
        if (vo.getMinSalary() != null || vo.getMaxSalary() != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(SALARY);
            if (vo.getMinSalary() != null) {
                // from ：范围查询里面的下限值，包含该值
                rangeQueryBuilder.from(vo.getMinSalary());
            }
            if (vo.getMaxSalary() != null) {
                // to ：范围查询里面的上限值，包含该值
                rangeQueryBuilder.to(vo.getMaxSalary());
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        if (vo.getStartTime() != null || vo.getEndTime() != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(JOIN_TIME);
            if (vo.getStartTime() != null) {
                rangeQueryBuilder.from(vo.getStartTime());
            }
            if (vo.getEndTime() != null) {
                rangeQueryBuilder.to(vo.getEndTime());
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEX).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(boolQueryBuilder);
        // from ：翻页参数，从第几条记录开始
        if (vo.getFrom() != null) {
            searchRequestBuilder.setFrom(vo.getFrom());
        }
        // size ：翻页参数，查询的记录数
        if (vo.getSize() != null) {
            searchRequestBuilder.setSize(vo.getSize());
        }
 
        // sort : 排序规则，默认规则为ASC升序，可以修改排序规则也可以用多个字段
        searchRequestBuilder.addSort(SortBuilders.fieldSort(SEX))
                .addSort(SortBuilders.fieldSort(SALARY).order(SortOrder.DESC));
 
        System.out.println("查询条件为|" + searchRequestBuilder);
 
        // 查询结果，由于数据都在 sourceAsMap 参数下，所以下面会再获取 sourceAsMap 的值
        SearchHits hits = searchRequestBuilder.get().getHits();
 
        List<Map<String, Object>> data = new ArrayList<>();
        hits.forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            sourceAsMap.put("_id", hit.getId());
            data.add(sourceAsMap);
        });
 
        return new EmployeeVo(data, hits.totalHits);
	}

}
