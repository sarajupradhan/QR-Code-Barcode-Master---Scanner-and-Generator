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
import ga.awsapp.qrscanner.create.schemes.Telephone;

public class PhoneInputFragment extends Fragment {

    UpdateView mCallback;

    public static PhoneInputFragment newInstance( ) {
        return new PhoneInputFragment();
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

       final View view =  inflater.inflate(R.layout.fragment_phone_input, container, false);

        Button button =  view.findViewById(R.id.create_button);
        EditText numberText = ((EditText) view.findViewById(R.id.phone_input));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberText.getText().toString();
                if(TextUtils.isEmpty(number)) {
                    numberText.setError("Number can't be empty");
                    return;
                }
                Telephone phone = new Telephone(number);
                mCallback.showQr(phone.toString(), BarcodeFormat.QR_CODE.toString());
            }
        });
       return  view;
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
