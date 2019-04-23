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
import com.google.zxing.BarcodeFormat;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.schemes.Geolocation;


public class LocationInputFragment extends Fragment {

    private UpdateView mCallback;
    public static LocationInputFragment newInstance( ) {
        return new LocationInputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_location_input, container, false);
       EditText latText =  ((EditText) view.findViewById(R.id.latitude_input));
       EditText longText =  ((EditText)view.findViewById(R.id.longitude_input));
        Button button =  view.findViewById(R.id.create_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String latitude = latText.getText().toString();
                String longitude = longText.getText().toString();

                if(TextUtils.isEmpty(latitude)) {
                    latText.setError("Latitude can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(longitude)) {
                    longText.setError("Longitude can't be empty");
                    return;
                }

                Geolocation geolocation = new Geolocation(
                        Double.parseDouble(latitude),
                        Double.parseDouble(longitude));
                mCallback.showQr(geolocation.toString(), BarcodeFormat.QR_CODE.toString());
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
