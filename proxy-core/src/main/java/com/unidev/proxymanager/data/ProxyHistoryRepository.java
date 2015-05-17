package com.unidev.proxymanager.data;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyHistoryRepository extends ElasticsearchRepository<ProxyHistory, String> {
}
