package com.unidev.proxymanager.data;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyHistoryRepository extends MongoRepository<ProxyHistory, String> {
}
