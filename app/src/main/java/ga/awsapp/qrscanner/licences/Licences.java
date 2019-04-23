package ga.awsapp.qrscanner.licences;

import android.support.annotation.NonNull;

public class Licences {

    private String name;
    private String desc;

    public Licences(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
