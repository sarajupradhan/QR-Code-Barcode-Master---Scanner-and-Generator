
package ga.awsapp.qrscanner.create.schemes;

public class Geolocation extends QRCodeScheme {
    private static final String GEO = "GEO";
    private static final String LAT_LONG_SEPARATOR = ",";

    private double latitude = 0.0;
    private double longitude = 0.0;

    public Geolocation() {
    }

    public Geolocation(double latitude, double longitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    @Override
    public String toString() {
        return GEO + DEFAULT_KEY_VALUE_SEPARATOR +
                latitude + LAT_LONG_SEPARATOR + longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Geolocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Geolocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
}
