package GeoLocation;

import org.junit.jupiter.api.BeforeEach;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.ArrayList;
import java.util.List;

public class GeoLocationTest {


    List<Location> locationList = new ArrayList<>();

@BeforeEach
        public void setUp() {
        Location petroKrepost = new Location();
        petroKrepost.setLatitude(59.950157);
        petroKrepost.setLongitude(30.315352);
        Location zimniy = new Location();
        zimniy.setLatitude(59.939916);
        zimniy.setLongitude(30.314699);
        Location moskovskiyRailwaySt = new Location();
        moskovskiyRailwaySt.setLatitude(59.930102);
        moskovskiyRailwaySt.setLongitude(30.362520);
        locationList.add(petroKrepost);
        locationList.add(zimniy);
        locationList.add(moskovskiyRailwaySt);
    }
}
