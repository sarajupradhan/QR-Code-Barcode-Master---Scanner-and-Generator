package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import ga.awsapp.qrscanner.create.schemes.Email;


public class EmailInputFragment extends Fragment {

    UpdateView mCallback;

    public static EmailInputFragment newInstance() {
        return new EmailInputFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_email_input, container, false);
        Button button =  view.findViewById(R.id.create_button);
        EditText emailText = ((EditText) view.findViewById(R.id.email_to_input));
        EditText  subjectText = ((EditText) view.findViewById(R.id.subject_input));
        EditText  contentText = ((EditText) view.findViewById(R.id.content_input));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //     mUpdateView.showQr();
                String emailTo = emailText.getText().toString();
                String subject = subjectText.getText().toString();
                String content = contentText.getText().toString();

                if(TextUtils.isEmpty(emailTo)) {
                    emailText.setError("Email can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(subject)) {
                    subjectText.setError("Subject can't be empty");
                    return;
                }

                if(TextUtils.isEmpty(content)) {
                    contentText.setError("Content can't be empty");
                    return;
                }
                Email email = new Email(Email.MailType.COMPREHENSIVE)
                        .setTo(emailTo)
                        .setSub(subject)
                        .setBody(content);
                mCallback.showQr(email.toString(), BarcodeFormat.QR_CODE.toString());
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
