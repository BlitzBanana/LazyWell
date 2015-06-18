package com.lazywell.android.puydufou.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.adapters.RestaurantAdapter;
import com.lazywell.android.puydufou.adapters.ShopAdapter;
import com.lazywell.android.puydufou.webservices.clients.RestoShopClient;

public class RestaurantShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_shop);

        RestoShopClient client = new RestoShopClient(this);

        ListView restaurantListView = (ListView)findViewById(R.id.resto_listview);
        ListView shopListView = (ListView)findViewById(R.id.shop_listview);

        restaurantListView.setAdapter(new RestaurantAdapter(this, client.getRestaurants()));
        shopListView.setAdapter(new ShopAdapter(this, client.getShops()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restaurant_shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
