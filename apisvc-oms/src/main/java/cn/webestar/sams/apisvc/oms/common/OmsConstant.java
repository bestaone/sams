package cn.webestar.sams.apisvc.oms.common;

public class OmsConstant {

    public static class AUTH {

        public final static String TOKEN_HEADER = "Authorization";

        public final static String TOKEN_PREFIX = "Bearer ";

        public final static String IGNORE_METHOD = "OPTIONS";

        /**
         * token 有效期
         */
        public final static Integer ACCESS_TOKEN_EXPIRE = 60 * 60 * 3;;

        /**
         * refresh_token 有效期
         */
        public final static Integer REFRESH_TOKEN_EXPIRE = 60 * 60 * 24 * 3;

        /**
         * token key 的key格式
         */
        public final static String ACCESS_TOKEN_CACHE_KEY = "oms:accessToken:%s:%s";

        /**
         * refresh_token 的key格式
         */
        public final static String REFRESH_TOKEN_CACHE_KEY = "oms:refreshToken:%s:%s";

        public final static String[] IGNORE_URLS = {"/api/open"};
    }

}
