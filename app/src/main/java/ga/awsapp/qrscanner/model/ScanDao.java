package ga.awsapp.qrscanner.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ScanDao {

    @Insert
    void insert(Scan scan);

    @Update
    void update(Scan scan);

    @Delete
    void delete(Scan scan);

    @Query("DELETE FROM scans")
    void deleteAllScans();

    @Query("SELECT * FROM scans")
    LiveData<List<Scan>> getAllScans();
}