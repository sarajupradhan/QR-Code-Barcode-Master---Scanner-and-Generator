package ga.awsapp.qrscanner.create;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.scan.ContinuousCaptureActivity;


public class CreateFragment extends Fragment implements View.OnClickListener {

    private static final int BROWSE_IMAGE_REQUEST_CODE = 123;
    public static final String FRAGMENT_CODE = "FRAGMENT_CODE";
    public static final String BROWSE_IMAGE_URI = "BROWSE_IMAGE_URI";
    private SharedPreferences preferences;
    public final int CAMERA_PERMISSION_CODE = 0123;


    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_static, container, false);
        ButterKnife.bind(this, view);

        view.findViewById(R.id.text_btn).setOnClickListener(this);
        view.findViewById(R.id.phone_btn).setOnClickListener(this);
        view.findViewById(R.id.sms_btn).setOnClickListener(this);
        view.findViewById(R.id.website_btn).setOnClickListener(this);
        view.findViewById(R.id.wifi_btn).setOnClickListener(this);
        view.findViewById(R.id.location_btn).setOnClickListener(this);
        view.findViewById(R.id.contact_btn).setOnClickListener(this);
        view.findViewById(R.id.event_btn).setOnClickListener(this);
        view.findViewById(R.id.email_btn).setOnClickListener(this);
        view.findViewById(R.id.app_btn).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @OnClick(R.id.scan_from_camera_btn)
    void openScan() {
        if (checkPermission()) {

            Intent intent = new Intent(getContext(), ContinuousCaptureActivity.class);
            startActivity(intent);

        } else {
            requestPermission();
        }


    }
//        IntentIntegrator integrator = new IntentIntegrator(getActivity());
//        integrator.setCameraId(0);
//        integrator.setCaptureActivity(CustomScannerActivity.class);
//        integrator.setBeepEnabled(preferences.getBoolean("beep_switch",true));
//        integrator.setVibrateEnabled(preferences.getBoolean("vibrate_switch",true));
//        integrator.initiateScan();


    @Override
    public void onClick(View v) {

        int code = 0;
        switch (v.getId()) {
            case R.id.text_btn:
                code = 1;
                break;

            case R.id.sms_btn:
                code = 3;
                break;

            case R.id.phone_btn:
                code = 2;
                break;

            case R.id.website_btn:
                code = 9;
                break;

            case R.id.wifi_btn:
                code = 8;
                break;

            case R.id.location_btn:
                code = 6;
                break;

            case R.id.contact_btn:
                code = 5;
                break;

            case R.id.event_btn:
                code = 7;
                break;

            case R.id.email_btn:
                code = 4;
                break;

            case R.id.app_btn:
                code = 10;
                break;

        }

        Intent intent = new Intent(getContext(), CreateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(FRAGMENT_CODE, code);
        startActivity(intent);

    }


    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        requestPermissions(
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getContext(), ContinuousCaptureActivity.class);
                    startActivity(intent);
                    // main logic
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
//                            showMessageOKCancel("You need to allow access permissions",
//                                    new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                requestPermission();
//                                            }
//                                        }
//                                    });
                        }
                    }
                }
                break;
        }
    }
}
