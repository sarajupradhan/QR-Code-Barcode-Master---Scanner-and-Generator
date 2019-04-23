/**
 * Copyright 2016 Areeb Beigh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
