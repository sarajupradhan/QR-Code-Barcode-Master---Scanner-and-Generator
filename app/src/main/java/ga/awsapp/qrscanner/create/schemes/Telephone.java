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

public class Telephone extends QRCodeScheme {
    private static final String TEL = "TEL";

    private String number;

    public Telephone() {
    }

    public Telephone(String number) {
        this.setNumber(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(TEL).append(DEFAULT_KEY_VALUE_SEPARATOR);
        if (number != null)
            sb.append(number);

        return sb.toString();
    }

    public String getNumber() {
        return number;
    }

    public Telephone setNumber(String number) {
        this.number = number;
        return this;
    }
}

