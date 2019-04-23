package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.google.zxing.BarcodeFormat;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.schemes.WiFi;


public class WifiInputFragment extends Fragment {


    private UpdateView mCallback;
    private String security;

    public static WifiInputFragment newInstance( ) {
        return new WifiInputFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_wifi_input, container, false);

        Button button =  view.findViewById(R.id.create_button);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.network_type_input);
        EditText ssidText = ((EditText) view.findViewById(R.id.ssid_input));
        EditText passwordText = ((EditText) view.findViewById(R.id.password_input));

        security = "WPA";
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View passwdInput = view.findViewById(R.id.password_input);
                        switch (checkedId) {
                            case R.id.open:
                                passwdInput.setEnabled(false);
                                security = "";
                                break;
                            case R.id.wpa_wpa2:
                                security = "WPA";
                                passwdInput.setEnabled(true);
                                break;
                            case R.id.wep:
                                security = "WEP";
                                passwdInput.setEnabled(true);
                                break;
                        }
                    }
                }
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ssid = ssidText.getText().toString();
                String passwd = "";


                if (!security.equals(""))
                    passwd = passwordText.getText().toString();

                if(TextUtils.isEmpty(ssid)) {
                    ssidText.setError("SSID can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(passwd)) {
                    passwordText.setError("Password can't be empty");
                    return;
                }

                WiFi wifi = new WiFi()
                        .setSsid(ssid)
                        .setPassword(passwd)
                        .setType(security);
                mCallback.showQr(wifi.toString(), BarcodeFormat.QR_CODE.toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        try {
            mCallback = (UpdateView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement HeadlineListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
