package com.jcy.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http交互工具类
 */
public class HttpClientUtil {

    protected static final Logger                           logger                          = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final String                              METHOD_POST                     = "POST";
    public static final String                              METHOD_GET                      = "GET";
    public static final String                              DEFAULT_CHARSET                 = "utf-8";
    public static final String                              DEFAULT_CONTENT_TYPE            = "application/json;charset=UTF-8";
    public static final int                                 DEFAULT_CONNECT_TIMEOUT         = 15000;
    public static final int                                 DEFAULT_READ_TIMEOUT            = 15000;
    public static final int                                 DEFAULT_CONNECT_REQUEST_TIMEOUT = 15000;

    private static final int                                MAX_TOTAL                       = 64;

    private static final int                                MAX_PER_ROUTE                   = 32;

    private static final RequestConfig                      requestConfig;

    private static final PoolingHttpClientConnectionManager connectionManager;

    private static final HttpClientBuilder                  httpBuilder;

    private static final CloseableHttpClient                httpClient;

    private static final CloseableHttpClient                httpsClient;

    private static SSLContext                               sslContext                      = null;

    static {

        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs,
                                         String string) {
                    return true;
                }
            }).build();
        } catch (KeyStoreException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (KeyManagementException ex) {
            logger.error(ex.getMessage(), ex);
        }
        requestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_READ_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT).build();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", new PlainConnectionSocketFactory()).register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)).build();
        connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        httpBuilder = HttpClientBuilder.create();
        httpBuilder.setDefaultRequestConfig(requestConfig);
        httpBuilder.setConnectionManager(connectionManager);
        httpBuilder.setRetryHandler(new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception,
                                        int executionCount,
                                        HttpContext context) {
                if (executionCount > 3) {
                    logger.warn("Maximum tries reached for client http pool");
                    return false;
                }

                if (exception instanceof NoHttpResponseException) {// NoHttpResponseException 重试
                    return true;
                } else if (exception instanceof ConnectTimeoutException) {// 连接超时重试
                    return true;
                } else if (exception instanceof SocketTimeoutException) { // 响应超时不重试，避免造成业务数据不一致
                    return false;
                } else {
                    return false;
                }

            }
        });
        httpClient = httpBuilder.build();
        httpsClient = httpBuilder.build();
    }

    private HttpClientUtil() {

    }

    public static String get(String url,
                             Map<String, String> headers) {
        HttpGet request = new HttpGet(url);
        try {
            wrapHeader(request, headers);// 设置请求头
            return RegexUtil.isHttps(url) ? execute(request, httpsClient) : execute(request, httpClient);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }
    /**
     * 功能描述: GET 请求
     * 
     * @param url
     * @param headers
     * @param timeout 超时时间
     * @return String
     * @version 1.0.1
     * @author xiaowux
     * @create 2018年7月26日
     */
    public static String get(String url,
                             Map<String, String> headers, int timeout) {
        HttpGet request = new HttpGet(url);
        RequestConfig reqConfig = requestConfig;
        CloseableHttpClient httpsCloseableHttpClient = httpsClient;
        CloseableHttpClient httpCloseableHttpClient = httpClient;
        if(timeout>0){
            reqConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
            httpBuilder.setDefaultRequestConfig(reqConfig);
            httpsCloseableHttpClient = httpBuilder.build();
            httpCloseableHttpClient = httpBuilder.build();
        }
        try {
            wrapHeader(request, headers);// 设置请求头
            return RegexUtil.isHttps(url) ? execute(request, httpsCloseableHttpClient) : execute(request, httpCloseableHttpClient);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }

    public static String postBody(String url,
                                  String body,
                                  Map<String, String> headers) {
        HttpPost request = new HttpPost(url);
        try {
            wrapHeader(request, headers);// 设置请求头
            wrapStringEntity(request, body);// 设置body
            return RegexUtil.isHttps(url) ? execute(request, httpsClient) : execute(request, httpClient);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }

    public static String postForm(String url,
                                  Map<String, String> params,
                                  Map<String, String> headers) {
        HttpPost request = new HttpPost(url);
        try {
            wrapHeader(request, headers);// 设置请求头
            wrapFormEntity(request, params);
            return RegexUtil.isHttps(url) ? execute(request, httpsClient) : execute(request, httpClient);
        } catch (Exception e) {
            logger.error(url + ":" + e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }
    /**
     * 功能描述: 
     * 
     * @param url
     * @param params
     * @param headers
     * @param timeout
     * @return String
     * @version 1.0.1
     * @author xiaowux
     * @create 2018年7月17日
     */
    public static String postForm(String url,
                                  Map<String, String> params,
                                  Map<String, String> headers, int timeout) {
        HttpPost request = new HttpPost(url);
        RequestConfig reqConfig = requestConfig;
        CloseableHttpClient httpsCloseableHttpClient = httpsClient;
        CloseableHttpClient httpCloseableHttpClient = httpClient;
        if(timeout>0){
            reqConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
            httpBuilder.setDefaultRequestConfig(reqConfig);
            httpsCloseableHttpClient = httpBuilder.build();
            httpCloseableHttpClient = httpBuilder.build();
        }
        try {
            wrapHeader(request, headers);// 设置请求头
            wrapFormEntity(request, params);
            return RegexUtil.isHttps(url) ? execute(request, httpsCloseableHttpClient) : execute(request, httpCloseableHttpClient);
        } catch (Exception e) {
            logger.error(url + ":" + e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }
    /**
     * 
     * 功能描述: 通过代理请求服务器
     * 
     * @param proxy
     * @param url
     * @param params
     * @param headers
     * @return String
     * @version 1.0.0
     * @author piaohailin
     */
    public static String postForm(String proxy,
                                  String url,
                                  Map<String, String> params,
                                  Map<String, String> headers) {
        HttpPost request = new HttpPost(url);
        HttpHost target = null;
        if (proxy != null && !"".equals(proxy.trim())) {
            // target
            String scheme = null;
            if (RegexUtil.isHttps(url)) {
                scheme = "https";
            } else {
                scheme = "http";
            }
            target = new HttpHost(url.substring(scheme.length() + 3, url.length()), -1, scheme); // 依次是目标请求地址，端口号,协议类型

            // proxy
            String[] temp = proxy.split(":");
            String proxyHostname = temp[0];
            int proxyPort = Integer.valueOf(temp[1]);

            // 依次是代理地址，代理端口号，协议类型
            HttpHost httpProxy = new HttpHost(proxyHostname, proxyPort, scheme);
            RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
            // 请求地址
            request.setConfig(config);
        }

        try {
            wrapHeader(request, headers);// 设置请求头
            wrapFormEntity(request, params);
            if (proxy != null && target != null) {
                return RegexUtil.isHttps(url) ? execute(target, request, httpsClient) : execute(target, request, httpClient);
            } else {
                return RegexUtil.isHttps(url) ? execute(target, request, httpsClient) : execute(target, request, httpClient);
            }
        } catch (Exception e) {
            logger.error(url + ":" + e.getMessage(), e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }

    private static String execute(HttpRequestBase request,
                                  CloseableHttpClient httpClient) {
        return execute(null, request, httpClient);
    }

    /**
     * 
     * 功能描述: 支持代理的方法
     * 
     * @param target
     * @param request
     * @param httpClient
     * @return String
     * @version 1.0.0
     * @author piaohailin
     */
    private static String execute(HttpHost target,
                                  HttpRequestBase request,
                                  CloseableHttpClient httpClient) {
        String respJson = null;
        int statusCode = -1;
        CloseableHttpResponse response = null;
        try {
            if (target != null) {
                response = httpClient.execute(target, request);
            } else {
                response = httpClient.execute(request);
            }
            statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                respJson = EntityUtils.toString(httpEntity, DEFAULT_CHARSET);
                EntityUtils.consume(httpEntity);
            }

        } catch (Exception e) {
            logger.error(request.getURI() + ":" + e.getMessage(), e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e2) {
                    logger.error(e2.getMessage(), e2);
                }
            }
        }

        logger.info("request url : {}", request.getURI());
        logger.info("response from url : {},statusCode={}", respJson, statusCode);
        return respJson;
    }

    private static void wrapHeader(HttpRequestBase request,
                                   Map<String, String> headers) {
        // 设置请求头
        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
        request.addHeader("x-forwarded-for", ServerIPPropertyDefiner.getHostIp());
    }

    private static void wrapStringEntity(HttpPost request,
                                         String body) {
        // 设置body
        if (body != null) {
            StringEntity entity = new StringEntity(body, DEFAULT_CHARSET);// 解决中文乱码问题
            entity.setContentEncoding(DEFAULT_CHARSET);
            request.setEntity(entity);
        }
    }

    private static void wrapFormEntity(HttpPost request,
                                       Map<String, String> params)
            throws UnsupportedEncodingException {
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, DEFAULT_CHARSET));
        }
    }
    // public static void main(String[] args) {
    // System.out.println(ServerIPPropertyDefiner.getHostIp());
    // String scheme = "http";
    // String url = "http://10.200.177.100/test/testip.htm";
    // System.out.println(get(url,null));
    //
    // }
    // public static void main(String[] args) {
    // String scheme = "http";
    // String url = "http://www.baidu.com/isUpgrade";
    // System.out.println(url.substring(scheme.length()+ 3, url.length()));
    // 输出 www.baidu.com/isUpgrade
    // }

    // public static void main(String[] args) {
    // Map<String, String> params = new HashMap<>();
    // // params.put("username", "viponline15");
    // // params.put("token",
    // //
    // "UsacLqX6TmC8kMUjVcF19x4CxdAQcMvkzG225S4WmGOoIUu8o3yH61Z_uUHfxxdfvftYkf-fXBPh%0D%0Ab3Pw5JuKXxa1um2kFGjghBwTtRRvPSgpcc4o6bTP7tRq_rK_c4fjWgG1wAXHCxRGxaMR4wX5fh8z%0D%0ADXvLxrnfdzXQmWM2FS4");
    // // params.put("format", "json");
    // // String result = HttpClientUtil.postForm(signIn, params, null);
    //
    // Map<String, String> headers = new HashMap<String, String>();
    //// > User-Agent: curl/7.19.7 (x86_64-redhat-linux-gnu) libcurl/7.19.7 NSS/3.13.1.0 zlib/1.2.3 libidn/1.18
    // libssh2/1.2.2
    //// > Host: api.usergrowth.pptv.com
    //// > Accept: */*
    //// > Proxy-Connection: Keep-Alive
    // headers.put("User-Agent", "curl/7.19.7 (x86_64-redhat-linux-gnu) libcurl/7.19.7 NSS/3.13.1.0 zlib/1.2.3
    // libidn/1.18 libssh2/1.2.2");
    // headers.put("Host", "api.usergrowth.pptv.com");
    // headers.put("Accept", "*/*");
    // headers.put("Proxy-Connection", "Keep-Alive");
    //
    //
    //// headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
    //// headers.put("Accept-Encoding", "gzip, deflate");
    //// headers.put("Accept-Language", "zh-CN,zh;q=0.9");
    //// headers.put("Cache-Control", "no-cache");
    //// headers.put("Connection", "keep-alive");
    //// headers.put("DNT", "1");
    //// headers.put("Host", "api.usergrowth.pptv.com");
    //// headers.put("Pragma", "no-cache");
    //// headers.put("Upgrade-Insecure-Requests", "Upgrade-Insecure-Requests");
    //// headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)
    // Chrome/66.0.3359.181 Safari/537.36");
    //
    //// String result = HttpClientUtil.postForm("proxy.cnsuning.com:8080",
    // "http://api.usergrowth.pptv.com/springmvc/isTodayPcard", params, headers);
    //// String result = HttpClientUtil.postForm("http://api.usergrowth.pptv.com/springmvc/isTodayPcard", params,
    // headers);
    // String result =
    // HttpClientUtil.get("http://ppms.admin.cnsuning.com/test/ugs/sign.do?username=viponline15&token=KDsclkKYNGBYDg3oqu9WG18RM8onmY1VZj0sacvO1ynpgLtnWU53CdZJYuSReQnn522C5cNUy6-s%0D%0AASNMBuspdAkTKtUqV6hKj6UA9MUi6-6aTgazBOKVEluat9JI3wLPtvOQyhN-mk7xamQxv4ndE7Wd%0D%0AuqDb_VfNecSQ7IF9q5s",
    // null);
    // System.out.println(result);
    //
    // }
}
