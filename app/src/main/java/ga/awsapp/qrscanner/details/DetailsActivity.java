package ga.awsapp.qrscanner.details;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.HybridBinarizer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.model.Scan;
import ga.awsapp.qrscanner.util.Utils;
import ga.awsapp.qrscanner.viewmodel.ScanViewModel;
import ga.awsapp.qrscanner.zxingadditional.result.ActionAdapter;
import ga.awsapp.qrscanner.create.QRCode;
import ga.awsapp.qrscanner.zxingadditional.result.ResultHandler;
import ga.awsapp.qrscanner.zxingadditional.result.ResultHandlerFactory;
import static ga.awsapp.qrscanner.create.CreateFragment.BROWSE_IMAGE_URI;
import static ga.awsapp.qrscanner.main.HomeActivity.ADD_TO_CLIP;
import static ga.awsapp.qrscanner.main.HomeActivity.ADD_TO_HISTORY;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_STRING;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_TYPE;
import static ga.awsapp.qrscanner.util.Utils.isStoragePermissionGranted;

public class DetailsActivity extends AppCompatActivity {

   private static final String TAG = DetailsActivity.class.getSimpleName();
   @BindView(R.id.qr_image) ImageView imageView;
   @BindView(R.id.recycler_view) RecyclerView recyclerView;
   @BindView(R.id.resultText) TextView textView;
   @BindView(R.id.scroll_view) ScrollView scrollView;
   @BindView(R.id.content_type) TextView contentType;
   @BindView(R.id.toolbar) Toolbar toolbar;
   @BindView(R.id.parent) LinearLayout parentView;
   @BindView(R.id.time) TextView time;
   private Bitmap qrBitmap;
   private ScanViewModel scanViewModel;
   private Boolean addToHistory;
   private Boolean addToClip = false;
   private SharedPreferences preferences;

   private String textString = "" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String result1 = getIntent().getExtras().getString(QR_STRING);
        String type = getIntent().getExtras().getString(QR_TYPE);
        addToHistory = getIntent().getExtras().getBoolean(ADD_TO_HISTORY);
        addToClip =  getIntent().getExtras().getBoolean(ADD_TO_CLIP);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        scanViewModel = ViewModelProviders.of(this).get(ScanViewModel.class);

        if (result1 != null && type != null)
        {
            QRCode qrCode = new QRCode(result1);
            try {
                qrBitmap =  qrCode.getSimpleBitmap(Color.BLACK, null, type);
                if (qrBitmap!= null)
                {
                    ScanBitmap scanBitmap = new ScanBitmap();
                    scanBitmap.execute(qrBitmap);
                }


            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        else {
            Uri uri = Uri.parse(getIntent().getExtras().getString(BROWSE_IMAGE_URI));
            try {
                qrBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ScanBitmap scanBitmap = new ScanBitmap();
                scanBitmap.execute(qrBitmap);;


            } catch (Exception e) {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.details_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.save_image:
                if (isStoragePermissionGranted(this,this))
                {
                    new Utils().savaImage(qrBitmap,parentView);
                }
                break;

            case R.id.menu_copy:
             {
                 if (!textString.equals(""))
                 {
                     copyText(textString);
                 }
             }
             break;

            case R.id.menu_share:
                {    if (!textString.equals("")) {
                String shareBody = textString;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
            }
            break;

        }
        return super.onOptionsItemSelected(item);
    }




    class ScanBitmap extends AsyncTask<Bitmap, Void, Result>
    {

        @Override
        protected Result doInBackground(Bitmap... bitmaps) {

            Reader reader = new MultiFormatReader();
            Result result = null;
            int[] intArray = new int[bitmaps[0].getWidth() * bitmaps[0].getHeight()];
            bitmaps[0].getPixels(intArray, 0, bitmaps[0].getWidth(), 0, 0, bitmaps[0].getWidth(), bitmaps[0].getHeight());
            LuminanceSource source = new RGBLuminanceSource(bitmaps[0].getWidth(), bitmaps[0].getHeight(), intArray);
            BinaryBitmap bMap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = reader.decode(bMap);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (ChecksumException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result!=null) {
                final ResultHandler resultHandler = ResultHandlerFactory.makeResultHandler(DetailsActivity.this, result);
                getSupportActionBar().setTitle(result.getBarcodeFormat().toString().replace("_"," "));
                textView.setText(result.getText());
                contentType.setText(result.getBarcodeFormat().toString().replace("_"," "));
                int menuItemsCount = resultHandler.getButtonCount();

                final String[] menuItems = new String[menuItemsCount];
                for (int i = 0; i < menuItemsCount; i++) {
                    menuItems[i] = getString(resultHandler.getButtonText(i));
                }
                textView.setText(result.getText());
                Timestamp stamp = new Timestamp(result.getTimestamp());
                Date date = new Date(stamp.getTime());

                time.setText(new SimpleDateFormat("dd MMM yyyy   HH:mm:SS").format(date));
                textString =  result.getText();
                ActionAdapter actionAdapter = new ActionAdapter(DetailsActivity.this, menuItems);
                recyclerView.setLayoutManager(new GridLayoutManager(DetailsActivity.this, 3));
                recyclerView.setAdapter(actionAdapter);
                actionAdapter.setOnItemClickListener(new ActionAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        resultHandler.handleButtonPress(position);
                    }
                });

                if (qrBitmap != null) {
                    imageView.setImageBitmap(qrBitmap);
                }

                ///  add to history
                if (addToHistory && preferences.getBoolean("add_to_history", true)) {
                    scanViewModel.insert(new Scan(result.getText(), result.getBarcodeFormat().toString(), resultHandler.getImageResource(), result.getTimestamp()));

                }
                /// copy to clip after result
                if (addToClip && preferences.getBoolean("auto_copy_switch", true)) {
                   copyText(result.getText());
                }
            }
            else {
                Toast.makeText(DetailsActivity.this, "Unsupported Code try again", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            new Utils().savaImage(qrBitmap,parentView);
        }
    }

    public void copyText( String text)
    {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Text",text);
        clipboard.setPrimaryClip(clip);
    }

}
