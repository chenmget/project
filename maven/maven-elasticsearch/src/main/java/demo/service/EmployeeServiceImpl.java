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
            // �������� ID ���ʾ����
            client.prepareIndex(INDEX, TYPE).setSource(builder, XContentType.JSON);
        } else {
            // ������ ID ���ʾ�޸�
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
		// boolQuery �����Ե��Ӷ����ѯ�������൱��SQL�� AND
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		 // multiMatchQuery �����ֶ�����������ؼ��֣����ԴӶ���ֶ�ƥ��
        if (vo.getKeyword() != null) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(vo.getKeyword(), NAME, PHONE, POST, DESC));
        }
        // matchQuery �����ֶ�����
        if (vo.getName() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(NAME, vo.getName()));
        }
        // wildcardQuery ��ͨ���ƥ�䣺������SQL����� LIKE
        if (vo.getPhone() != null) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(PHONE, "*" + vo.getPhone() + "*"));
        }
        if (vo.getPost() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(POST, vo.getPost()));
        }
        if (vo.getDesc() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(DESC, vo.getDesc()));
        }
        // termQuery �� ͨ�����ڷ��ַ������͵ıȽϣ������Ա�Ϊ�еĻ���Ů�ģ�״̬Ϊ1����2ʲô�ġ�����֮�ȽϹ̶�ֵ
        if (vo.getSex() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery(SEX, vo.getSex()));
        }
        if (vo.getSalary() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery(SALARY, vo.getSalary()));
        }
        // rangeQuery ����Χ��ѯ���ж�һ��ֵ�Ƿ��ڷ�Χ�ڣ����繤�ʴ��ڶ��٣��������֮�䣬�Ƿ�����Ч���ڡ�����
        if (vo.getMinSalary() != null || vo.getMaxSalary() != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(SALARY);
            if (vo.getMinSalary() != null) {
                // from ����Χ��ѯ���������ֵ��������ֵ
                rangeQueryBuilder.from(vo.getMinSalary());
            }
            if (vo.getMaxSalary() != null) {
                // to ����Χ��ѯ���������ֵ��������ֵ
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
        // from ����ҳ�������ӵڼ�����¼��ʼ
        if (vo.getFrom() != null) {
            searchRequestBuilder.setFrom(vo.getFrom());
        }
        // size ����ҳ��������ѯ�ļ�¼��
        if (vo.getSize() != null) {
            searchRequestBuilder.setSize(vo.getSize());
        }
 
        // sort : �������Ĭ�Ϲ���ΪASC���򣬿����޸��������Ҳ�����ö���ֶ�
        searchRequestBuilder.addSort(SortBuilders.fieldSort(SEX))
                .addSort(SortBuilders.fieldSort(SALARY).order(SortOrder.DESC));
 
        System.out.println("��ѯ����Ϊ|" + searchRequestBuilder);
 
        // ��ѯ������������ݶ��� sourceAsMap �����£�����������ٻ�ȡ sourceAsMap ��ֵ
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
