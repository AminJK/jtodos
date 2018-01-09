package com.niufennan.jtodos.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenUtil {
    private static final int INTERVAL = 7;// token过期时间间隔 天
    private static final String SALT = "jtodos";// 加盐
    private static final int HOUR = 3;// 检查token过期线程执行时间 时
    private static Map<String, Token> tokenMap = new HashMap<String, Token>();
    private static TokenUtil tokenUtil = null;
    static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    static {
        //开启监听
        listenTask();
    }
    public static TokenUtil getTokenUtil() {
        if (tokenUtil == null) {
            synInit();
        }
        return tokenUtil;
    }

    private static synchronized void synInit() {
        if (tokenUtil == null) {
            tokenUtil = new TokenUtil();
        }
    }
    private TokenUtil() {//禁止实例化
    }
    public static Map<String, Token> getTokenMap() {
        return tokenMap;
    }
    public static Token generateToken(String uniq, int id) {
        String signature=MD5(System.currentTimeMillis() + SALT + uniq + id);
        Token token = new Token(signature, System.currentTimeMillis(),id);
        synchronized (tokenMap) {
            tokenMap.put(signature, token);
        }
        return token;
    }
    public static boolean removeToken(String signature) {
        synchronized (tokenMap) {
            tokenMap.remove(signature);
        }
        return true;
    }
    public static long volidateToken(String signature) {
        Token token = (Token) tokenMap.get(signature);
        if (token != null && token.getSignature().equals(signature)) {
            return token.getId();
        }
        return -1;
    }
    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            return byte2hex(mdInst.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String byte2hex(byte[] b) {
        StringBuilder sbDes = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < b.length; i++) {
            tmp = (Integer.toHexString(b[i] & 0xFF));
            if (tmp.length() == 1) {
                sbDes.append("0");
            }
            sbDes.append(tmp);
        }
        return sbDes.toString();
    }
    public static void listenTask() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //定制每天的HOUR点，从明天开始
        calendar.set(year, month, day + 1, HOUR, 0, 0);
        // calendar.set(year, month, day, 17, 11, 40);
        Date date = calendar.getTime();
        scheduler.scheduleAtFixedRate(new ListenToken(), (date.getTime() - System.currentTimeMillis()) / 1000, 60 * 60 * 24, TimeUnit.SECONDS);
    }
    static class ListenToken implements Runnable {
        public ListenToken() {
            super();
        }
        public void run() {//监听Token列表
            try {
                synchronized (tokenMap) {
                    for (int i = 0; i < 5; i++) {
                        if (tokenMap != null && !tokenMap.isEmpty()) {
                            for (Map.Entry<String, Token> entry : tokenMap.entrySet()) {
                                Token token = (Token) entry.getValue();
                                int interval = (int) ((System.currentTimeMillis() - token.getTimestamp()) / 1000 / 60 / 60 / 24);
                                if (interval > INTERVAL) {
                                    tokenMap.remove(entry.getKey());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
