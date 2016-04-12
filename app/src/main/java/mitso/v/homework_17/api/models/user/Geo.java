package mitso.v.homework_17.api.models.user;

import java.io.Serializable;

public class Geo implements Serializable {

    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return  "\n--------------- lat = " + lat +
                "\n--------------- lng = " + lng;
    }
}
