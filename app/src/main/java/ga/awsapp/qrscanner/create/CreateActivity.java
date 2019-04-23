package ga.awsapp.qrscanner.create;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.input.ContactInputFragment;
import ga.awsapp.qrscanner.create.input.EmailInputFragment;
import ga.awsapp.qrscanner.create.input.EventInputFragment;
import ga.awsapp.qrscanner.create.input.GooglePlayFragment;
import ga.awsapp.qrscanner.create.input.LocationInputFragment;
import ga.awsapp.qrscanner.create.input.PhoneInputFragment;
import ga.awsapp.qrscanner.details.DetailsActivity;
import ga.awsapp.qrscanner.create.input.SmsInputFragment;
import ga.awsapp.qrscanner.create.input.TextInputFragment;
import ga.awsapp.qrscanner.create.input.UpdateView;
import ga.awsapp.qrscanner.create.input.UrlInputFragment;
import ga.awsapp.qrscanner.create.input.WifiInputFragment;
import static ga.awsapp.qrscanner.main.HomeActivity.ADD_TO_HISTORY;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_STRING;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_TYPE;

public class CreateActivity extends AppCompatActivity implements UpdateView{

    Fragment mFragment ;
    Toolbar mToolbar;
    private String TAG = CreateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int fragmentCode =  getIntent().getExtras().getInt(CreateFragment.FRAGMENT_CODE);

        switch (fragmentCode)
        {
            case 1:
                mFragment =  TextInputFragment.newInstance();
                getSupportActionBar().setTitle("Text");
                break;

            case 9:
                mFragment = UrlInputFragment.newInstance();
                getSupportActionBar().setTitle("Website");
                break;

            case 2:
                mFragment = PhoneInputFragment.newInstance();
                getSupportActionBar().setTitle("Phone");
                break;

            case 3:
                mFragment = SmsInputFragment.newInstance();
                getSupportActionBar().setTitle("Sms");
                break;

            case 4:
                mFragment = EmailInputFragment.newInstance();
                getSupportActionBar().setTitle("Email");
                break;

            case 5:
                mFragment = ContactInputFragment.newInstance();
                getSupportActionBar().setTitle("Contact");
                break;

            case 6:
                mFragment = LocationInputFragment.newInstance();
                getSupportActionBar().setTitle("Location");
                break;


            case 8:
                mFragment = WifiInputFragment.newInstance();
                getSupportActionBar().setTitle("Wifi");
                break;

            case 10:
                mFragment = GooglePlayFragment.newInstance();
                getSupportActionBar().setTitle("Apps");
          break;

            case 7:
                mFragment = EventInputFragment.newInstance();
                getSupportActionBar().setTitle("Event");
          break;

    }

        if (mFragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mFragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressed();
            }
        }
        return true;
    }


    @Override
    public void showQr(String qrString, String Type) {

        Intent intent =  new Intent(CreateActivity.this, DetailsActivity.class);
        intent.putExtra(QR_STRING,qrString);
        intent.putExtra(QR_TYPE,Type);
        intent.putExtra(ADD_TO_HISTORY,true);
        startActivity(intent);
        finish();
    }
}
