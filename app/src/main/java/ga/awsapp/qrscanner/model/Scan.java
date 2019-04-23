package ga.awsapp.qrscanner.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "scans")
public class Scan implements Comparable<Scan>{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "icon")
    private String icon;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private long timestamp;


    public Scan(String text,String type, String icon, long timestamp){
        this.id= UUID.randomUUID().toString();
        this.text=text;
        this.type=type;
        this.icon=icon;
        this.timestamp=timestamp;

    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getIcon() {
        return icon;
    }

    public void setIcon(@NonNull String icon) {
        this.icon = icon;
    }

    @NonNull
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(@NonNull Scan o) {
        return 0;
    }


}
