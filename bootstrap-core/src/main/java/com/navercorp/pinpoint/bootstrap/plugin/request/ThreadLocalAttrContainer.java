package com.navercorp.pinpoint.bootstrap.plugin.request;

/**
 * @author tangxqa
 */
public class ThreadLocalAttrContainer {
    public static final String ATTR_KEY_IP_NGINX = "X-Real-IP";
    public static final String ATTR_KEY_IP_APPPLAT = "X-Appplat-IP";



    //来自用户请求的真实的用户ip
    private ThreadLocal<String> userRealIP = new ThreadLocal<String>();

    private ThreadLocalAttrContainer() {
    }

    public ThreadLocal<String> getUserRealIP() {
        return userRealIP;
    }

    private static class ContainerInnerClass {
        private static ThreadLocalAttrContainer instance = new ThreadLocalAttrContainer();

    }

    public static ThreadLocalAttrContainer getInstance() {
        return ContainerInnerClass.instance;
    }

}
