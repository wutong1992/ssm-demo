package com.zhu.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhutao
 * @Date: 2020/9/22 17:24
 */
public class IpAdrressUtil {

    /**
     * X-Forwarded-For：Squid 服务代理
     */
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * Proxy-Client-IP：apache 服务代理
     */
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    /**
     * WL-Proxy-Client-IP：weblogic 服务代理
     */
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * HTTP_CLIENT_IP：有些代理服务器
     */
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    /**
     * X-Real-IP：nginx服务代理
     */
    private static final String X_REAL_IP = "X-Real-IP";

    private static final String UNKNOWN = "unknown";

    /**
     * 获取请求真实IP
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        String ipAddresses = request.getHeader(X_FORWARDED_FOR);

        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader(PROXY_CLIENT_IP);
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader(WL_PROXY_CLIENT_IP);
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader(HTTP_CLIENT_IP);
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            //
            ipAddresses = request.getHeader(X_REAL_IP);
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
