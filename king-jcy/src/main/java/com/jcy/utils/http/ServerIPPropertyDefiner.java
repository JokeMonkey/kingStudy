package com.jcy.utils.http;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyDefiner;


/** 
 * 功能描述: 
 * 
 * @version 1.0.4
 * @author admin
 */
public class ServerIPPropertyDefiner extends ContextAwareBase implements PropertyDefiner {

    private static final Logger logger = LoggerFactory.getLogger(ServerIPPropertyDefiner.class);

    @Override
    public String getPropertyValue() {
        try {
            String ip = getHostIp();
            char[] ips = ip.toCharArray();
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < ips.length; i++) {
                if ('.' != ips[i]) {
                    buffer.append(ips[i]);
                }
            }
            return buffer.toString();
        } catch (Exception e) {
            logger.error("Can not find LocalServer IP:" + e.getMessage(), e);
        }
        return "NOT_WEBSPHERE_WEB_SERVER";
    }
    
    
    /**
     * 功能描述: 
     * 
     * @return String
     * @version 1.0.4
     * @author admin
     */
    public static String getHostIp() {
        List<String> ips = new ArrayList<>();
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
                wrapAddress(ips, netInterface);
            }
        } catch (SocketException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return collectionToDelimitedString(ips, ",");
    }
    
    
    /**
     * 功能描述: 
     * 
     * @param ips
     * @param netInterface void
     * @version 1.0.4
     * @author admin
     */
    private static void wrapAddress(List<String> ips,
                                    NetworkInterface netInterface) {
        Enumeration<InetAddress> inteAddresses = netInterface.getInetAddresses();
        while (inteAddresses.hasMoreElements()) {
            InetAddress inetAddress = inteAddresses.nextElement();
            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                ips.add(inetAddress.getHostAddress());
            }
        }
    }

    /**
     * 功能描述: 
     * 
     * @param list
     * @param delim
     * @return String
     * @version 1.0.4
     * @author admin
     */
    private static String collectionToDelimitedString(List<String> list,
                                                      String delim) {
        if (null == list || list.isEmpty()) {
            return "";
        }
        String[] arr = list.toArray(new String[list.size()]);
        return StringUtils.join(arr, delim);
    }
}
