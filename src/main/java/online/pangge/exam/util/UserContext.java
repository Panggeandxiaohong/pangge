package online.pangge.exam.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
    public static final String USERINSESSION = "USER_IN_REDIS";
    public static final String PERMISSIONINSESSION = "PERMISSION_IN_REDIS";
    public static final String MENUINSESSION = "MENU_IN_REDIS";
    public static ThreadLocal<HttpServletRequest> loacl = new ThreadLocal<>();

    public static void setLoacl(HttpServletRequest request) {
        loacl.set(request);
    }

    public static HttpServletRequest getLocal() {
        return loacl.get();
    }
}
