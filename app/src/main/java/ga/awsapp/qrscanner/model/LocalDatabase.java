package ga.awsapp.qrscanner.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Scan.class},exportSchema = false,version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static volatile LocalDatabase INSTANCE;
    public abstract ScanDao scanDao();

    public static LocalDatabase newInstance(Context context){
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            LocalDatabase.class, "Scan.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
