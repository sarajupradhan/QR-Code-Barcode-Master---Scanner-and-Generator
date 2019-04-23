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


public class VCard extends QRCodeScheme {
    private static final String BEGIN_VCARD = "BEGIN" + DEFAULT_KEY_VALUE_SEPARATOR + "VCARD";
    private static final String VERSION = "VERSION" + DEFAULT_KEY_VALUE_SEPARATOR + "3.0";
    private static final String NAME = "N";
    private static final String COMPANY = "ORG";
    private static final String TITLE = "TITLE";
    private static final String PHONE = "TEL";
    private static final String WEB = "URL";
    private static final String EMAIL = "EMAIL";
    private static final String ADDRESS = "ADR";
    private static final String NOTE = "NOTE";

    private String name;
    private String company;
    private String title;
    private String phoneNumber;
    private String email;
    private String address;
    private String website;
    private String note;

    public VCard() {
    }

    public VCard(String name) {
        this.name = name;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(BEGIN_VCARD).append(LINE_FEED);
        sb.append(VERSION);

        if (name != null) {
            sb.append(NAME).append(DEFAULT_KEY_VALUE_SEPARATOR).append(name);
        }
        if (company != null) {
            sb.append(LINE_FEED).append(COMPANY).append(DEFAULT_KEY_VALUE_SEPARATOR).append(company);
        }
        if (title != null) {
            sb.append(LINE_FEED).append(TITLE).append(DEFAULT_KEY_VALUE_SEPARATOR).append(title);
        }
        if (phoneNumber != null) {
            sb.append(LINE_FEED).append(PHONE).append(DEFAULT_KEY_VALUE_SEPARATOR).append(phoneNumber);
        }
        if (website != null) {
            sb.append(LINE_FEED).append(WEB).append(DEFAULT_KEY_VALUE_SEPARATOR).append(website);
        }
        if (email != null) {
            sb.append(LINE_FEED).append(EMAIL).append(DEFAULT_KEY_VALUE_SEPARATOR).append(email);
        }
        if (address != null) {
            sb.append(LINE_FEED).append(ADDRESS).append(DEFAULT_KEY_VALUE_SEPARATOR).append(address);
        }
        if (note != null) {
            sb.append(LINE_FEED).append(NOTE).append(DEFAULT_KEY_VALUE_SEPARATOR).append(note);
        }
        sb.append(LINE_FEED).append("END:VCARD");
        return sb.toString();
    }

    public VCard setAddress(String address) {
        this.address = address;
        return this;
    }

    public VCard setCompany(String company) {
        this.company = company;
        return this;
    }

    public VCard setEmail(String email) {
        this.email = email;
        return this;
    }

    public VCard setName(String name) {
        this.name = name;
        return this;
    }

    public VCard setNote(String note) {
        this.note = note;
        return this;
    }

    public VCard setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public VCard setTitle(String title) {
        this.title = title;
        return this;
    }

    public VCard setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitle() {
        return title;
    }
}
