package com.unidev.proxymanager.data;

/**
 * Proxy checking result
 */
public class ProxyCheckResult {

    public enum CheckStatus { OK, FAILED, TIMEOUT_FAILED}

    private String remoteIp;
    private Long requestTime;
    private CheckStatus checkStatus;

    @Override
    public String toString() {
        return "ProxyCheckResult{" +
                "remoteIp='" + remoteIp + '\'' +
                ", requestTime=" + requestTime +
                ", checkStatus=" + checkStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxyCheckResult that = (ProxyCheckResult) o;

        if (checkStatus != that.checkStatus) return false;
        if (remoteIp != null ? !remoteIp.equals(that.remoteIp) : that.remoteIp != null) return false;
        if (requestTime != null ? !requestTime.equals(that.requestTime) : that.requestTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = remoteIp != null ? remoteIp.hashCode() : 0;
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (checkStatus != null ? checkStatus.hashCode() : 0);
        return result;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }
}
