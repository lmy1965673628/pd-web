package com.pd.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.stereotype.Service;

import com.pd.pojo.Item;
import com.pd.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SolrClient solrClient;

	@Override
	public List<Item> findItemByKey(String key) throws Exception {
		SolrQuery q = new SolrQuery(key);
		q.setStart(0);
		q.setRows(20);
		//rest 远程调用solr服务器,执行查询获得查询结果
		QueryResponse r = solrClient.query(q);
		List<Item> list = r.getBeans(Item.class);
		return list;
	}

}






