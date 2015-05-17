package com.unidev.proxymanager.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Proxy history object
 */
@Document(indexName = "proxy_history")
public class ProxyHistory {

    @Id
    private String id;

    private String ip;
    private Integer port;

    private Long requestCount = 0L;

    private Integer score;
    private List<Integer> history = new ArrayList<>();

    private Double requestTime = 0D;

    private Date addDate = new Date();
    private Date lastUpdate = new Date(0L);


    public ProxyHistory() {
    }

    public ProxyHistory(String id, String ip, Integer port, Long requestCount, Integer score, List<Integer> history, Double requestTime, Date addDate, Date lastUpdate) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.requestCount = requestCount;
        this.score = score;
        this.history = history;
        this.requestTime = requestTime;
        this.addDate = addDate;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxyHistory that = (ProxyHistory) o;

        if (addDate != null ? !addDate.equals(that.addDate) : that.addDate != null) return false;
        if (history != null ? !history.equals(that.history) : that.history != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (requestCount != null ? !requestCount.equals(that.requestCount) : that.requestCount != null) return false;
        if (requestTime != null ? !requestTime.equals(that.requestTime) : that.requestTime != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (requestCount != null ? requestCount.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProxyHistory{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", requestCount=" + requestCount +
                ", score=" + score +
                ", history=" + history +
                ", requestTime=" + requestTime +
                ", addDate=" + addDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }

    public Double getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Double requestTime) {
        this.requestTime = requestTime;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void addScore(Integer addScore) {
        setScore(getScore() + addScore);
    }
}
