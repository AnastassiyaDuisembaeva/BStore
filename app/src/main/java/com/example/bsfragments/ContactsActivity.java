package com.example.bsfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class ContactsActivity extends AppCompatActivity {
    private MapView mapview;
    private final String MAPKIT_API_KEY = "7944a636-4992-4f4f-90e2-5cbdde8bf9a9";
    private final Point TARGET_LOCATION = new Point(43.255312, 76.947213);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_contacts);
        mapview = (MapView)findViewById(R.id.map);
        mapview.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }
    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}
