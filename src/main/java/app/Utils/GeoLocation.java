package app.Utils;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class GeoLocation {

    public static String distanceCalculation(Double userLati, Double userLong, Double aimLati, Double aimLong) {
        Double EARTH_RADIUS = 6372795.0;

        //переводим координаты в радианы
        Double latUser = userLati * PI / 180;
        Double longUser = userLong * PI / 180;
        Double latAim = aimLati * PI / 180;
        Double longAim = aimLong * PI / 180;

        // косинусы и синусы широт и разницы долгот
        Double cl1 = cos(latUser);
        Double sl1 = sin(latUser);
        Double cl2 = cos(latAim);
        Double sl2 = sin(latAim);
        Double deltaLong = longAim - longUser;
        Double cosDelta = cos(deltaLong);
        Double sinDelta = sin(deltaLong);

        //вычисление длины большого круга
        Double y = Math.sqrt(pow(cl2 * sinDelta, 2) + pow(cl1 * sl2 - sl1 * cl2 * cosDelta, 2));
        Double x = sl1 * sl2 + cl1 * cl2 * cosDelta;

        Double ad = Math.atan2(y, x);
        Double distance = ad * EARTH_RADIUS;

        int lol = 0;
        double gpg = 2.0;

        lol = (int) gpg;


        String distanceInKM = String.format("От тебя до Петропавловской крепости: %d метров", distance.intValue());

        return distanceInKM;
    }
}
