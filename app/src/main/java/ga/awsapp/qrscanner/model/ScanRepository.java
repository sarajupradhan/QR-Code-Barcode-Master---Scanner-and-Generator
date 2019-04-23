package ga.awsapp.qrscanner.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ScanRepository {


    private ScanDao scanDao;
    private LiveData<List<Scan>> allScans;

    public ScanRepository(Application application) {
        ScanRoomDatabase database = ScanRoomDatabase.getInstance(application);
        scanDao = database.scanDao();
        allScans = scanDao.getAllScans();
    }

    public void insert(Scan scan) {
        new InsertScanAsyncTask(scanDao).execute(scan);
    }

    public void update(Scan scan) {
        new UpdateScanAsyncTask(scanDao).execute(scan);
    }

    public void delete(Scan scan) {
        new DeleteScanAsyncTask(scanDao).execute(scan);
    }

    public void deleteAllScans() {
        new DeleteAllScansAsyncTask(scanDao).execute();
    }

    public LiveData<List<Scan>> getAllScans() {
        return allScans;
    }

    private static class InsertScanAsyncTask extends AsyncTask<Scan, Void, Void> {
        private ScanDao scanDao;

        private InsertScanAsyncTask(ScanDao scanDao) {
            this.scanDao = scanDao;
        }

        @Override
        protected Void doInBackground(Scan... scans) {
            scanDao.insert(scans[0]);
            return null;
        }
    }

    private static class UpdateScanAsyncTask extends AsyncTask<Scan, Void, Void> {
        private ScanDao scanDao;

        private UpdateScanAsyncTask(ScanDao scanDao) {
            this.scanDao = scanDao;
        }

        @Override
        protected Void doInBackground(Scan... scans) {
            scanDao.update(scans[0]);
            return null;
        }
    }

    private static class DeleteScanAsyncTask extends AsyncTask<Scan, Void, Void> {
        private ScanDao scanDao;

        private DeleteScanAsyncTask(ScanDao scanDao) {
            this.scanDao = scanDao;
        }

        @Override
        protected Void doInBackground(Scan... scans) {
            scanDao.delete(scans[0]);
            return null;
        }
    }

    private static class DeleteAllScansAsyncTask extends AsyncTask<Void, Void, Void> {
        private ScanDao scanDao;

        private DeleteAllScansAsyncTask(ScanDao scanDao) {
            this.scanDao = scanDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            scanDao.deleteAllScans();
            return null;
        }
    }
}