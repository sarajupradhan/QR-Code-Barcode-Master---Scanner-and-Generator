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

public class SMS extends QRCodeScheme {
    private static final String SMS = "SMS";

    private String number;
    private String message;

    public SMS() {
    }

    public SMS(String number, String message) {
        this.setMessage(message);
        this.setNumber(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(SMS).append(DEFAULT_KEY_VALUE_SEPARATOR).append(number);
        if (message != null)
            sb.append(DEFAULT_KEY_VALUE_SEPARATOR).append(message);

        return sb.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getNumber() {
        return number;
    }

    public SMS setMessage(String message) {
        this.message = message;
        return this;
    }

    public SMS setNumber(String number) {
        this.number = number;
        return this;
    }
}
