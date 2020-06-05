package com.broad.web.framework.utils;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

/***
 * 批次号、流水号生成工具
 */
public class IdUtils {

    private final static String BATCH_PATTERN = "yyMMdd";
    private final static String TRANS_PATTERN = "yyMMddHHmmssSSS";

    /***
     * 生成批次号,yyyyMMddHHmmssSSS加4位随机数,后续考虑重载
     *
     * @return
     */
    public synchronized static String getShortBatchId() {
        String prefix = DateFormatUtils.format(new Date(), BATCH_PATTERN);
        return prefix + getRandomStr();
    }

    private synchronized static String getRandomStr() {
        return String.valueOf(RandomUtil.randomInt(1000, 9999));
    }

    /***
     * 生成交易流水号
     * @return
     */
    public synchronized static String getTransactionId() {
        String prefix = DateFormatUtils.format(new Date(), TRANS_PATTERN);
        long ip = ipToLong(getLocalIp());
        return prefix + ip + getRandomStr();
    }


    private static String getLocalIp() {
        Enumeration<NetworkInterface> netInterfaces = null;
        String ip = "127.0.0.1";
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface nif = netInterfaces.nextElement();
                Enumeration<InetAddress> iNetAddress = nif.getInetAddresses();
                while (iNetAddress.hasMoreElements()) {
                    ip = iNetAddress.nextElement().getHostAddress();
                    if (ip != null && ip.contains(":")) {
                        continue;
                    }
                    return ip;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    private static long ipToLong(String strIp) {
        String[] ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

}