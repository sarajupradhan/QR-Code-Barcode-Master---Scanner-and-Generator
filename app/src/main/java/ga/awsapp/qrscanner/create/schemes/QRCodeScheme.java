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

public abstract class QRCodeScheme {

    @Override
    public abstract String toString();

    public static final String LINE_FEED = "\n";
    public static final String DEFAULT_PARAM_SEPARATOR = "\r?\n";
    public static final String DEFAULT_KEY_VALUE_SEPARATOR = ":";
    public static final String DEFAULT_LINE_END = ";";
}
