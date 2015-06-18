package com.lazywell.android.puydufou.webservices;

/**
 * Created by victor on 17/06/2015.
 */
public class WebServicesDirectory {

    public static final String NAMESPACE = "http://webservices.crazywell.uni.corn/";
    private static final String DOMAIN = "http://192.168.43.142:50080";

    public class Shows{

        public static final String WSDL_URL = DOMAIN + "/ShowService/show?WSDL";

        public class Actions{
            public static final String GET_SHOWS = "getShows";
            public static final String GET_BEST_SCHEDULE = "getBestSchedule";
            public static final String GET_ANECDOTES = "getAnecdotes";
        }
    }

    public class Restaurants{

        public static final String WSDL_URL = DOMAIN + "/RestaurantService/restaurant?WSDL";

        public class Actions{
            public static final String GET_RESTAURANTS = "getRestaurants";
        }
    }

    public class Shops{

        public static final String WSDL_URL = DOMAIN + "/ShopService/shop?WSDL";

        public class Actions{
            public static final String GET_SHOPS = "getShops";
        }
    }
}
