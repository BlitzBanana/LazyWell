package com.lazywell.android.puydufou.webservices;

/**
 * Created by victor on 17/06/2015.
 */
public class WebServicesDirectory {

    public static final String NAMESPACE = "http://webservices.crazywell.uni.corn/";
    private static final String DOMAIN = "http://192.168.43.142:50080";

    public class Shows{

        public static final String METHOD = "show";
        public static final String WSDL_URL = DOMAIN + "/ShowService/show?WSDL";

        public class Actions{
            public static final String GET_SHOWS = DOMAIN + "/ShowService/getShows";
            public static final String GET_BEST_SCHEDULE = DOMAIN + "/ShowService/getBestSchedule";
            public static final String GET_ANECDOTES = DOMAIN + "/ShowService/getAnecdotes";
        }
    }
}
