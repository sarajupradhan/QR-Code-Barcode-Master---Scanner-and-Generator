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
import ga.awsapp.qrscanner.create.schemes.VCard;


public class ContactInputFragment extends Fragment {

    private UpdateView mCallback;
    public static ContactInputFragment newInstance( ) {
        return new ContactInputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View view =  inflater.inflate(R.layout.fragment_contact_input, container, false);
       EditText  nameTv =  view.findViewById(R.id.name_input);
       EditText  orgTv  =  view.findViewById(R.id.organization_input);
       EditText  tittleTv =    view.findViewById(R.id.tittle_input);
       EditText phoneTv   =    view.findViewById(R.id.phone_input);
       EditText websiteTv =    view.findViewById(R.id.website_input);
       EditText emailTv    =   view.findViewById(R.id.email_input);
       EditText addressTv   =  view.findViewById(R.id.address_input);

        Button button =  view.findViewById(R.id.create_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameTv.getText().toString();
                String org = orgTv.getText().toString();
                String title = tittleTv.getText().toString();
                String phone = phoneTv.getText().toString();
                String website = websiteTv.getText().toString();
                String email = emailTv.getText().toString();
                String address = addressTv.getText().toString();

                if(TextUtils.isEmpty(name)) {
                    nameTv.setError("Name can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(org)) {
                    orgTv.setError("Organization can't be empty");
                    return;
                }


                if(TextUtils.isEmpty(title)) {
                    tittleTv.setError("Tittle can't be empty");
                    return;
                }


                if(TextUtils.isEmpty(phone)) {
                    phoneTv.setError("Phone can't be empty");
                    return;
                }


                if(TextUtils.isEmpty(website)) {
                    websiteTv.setError("Website can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    emailTv.setError("Email can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(address)) {
                    addressTv.setError("Address can't be empty");
                    return;
                }

                VCard vCard = new VCard(name)
                        .setCompany(org)
                        .setTitle(title)
                        .setPhoneNumber(phone)
                        .setWebsite(website)
                        .setEmail(email)
                        .setAddress(address);
                mCallback.showQr(vCard.toString(), BarcodeFormat.QR_CODE.toString());
            }
        });

     return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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
