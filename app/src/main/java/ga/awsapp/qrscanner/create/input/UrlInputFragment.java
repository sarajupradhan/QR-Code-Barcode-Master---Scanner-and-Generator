package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.zxing.BarcodeFormat;
import ga.awsapp.qrscanner.R;

public class UrlInputFragment extends Fragment {

    private UpdateView mCallback;

    public static UrlInputFragment newInstance( ) {
        return new UrlInputFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_url_input, container, false);

        Button button =  view.findViewById(R.id.create_button);
       EditText urlText =  ((EditText) view.findViewById(R.id.url_input));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = urlText.getText().toString();

                if(TextUtils.isEmpty(url)) {
                    urlText.setError("Url can't be empty");
                    return;
                }

                if (!(url.startsWith("http://") || url.startsWith("https://")))
                    url = "http://" + url;
                mCallback.showQr(url, BarcodeFormat.QR_CODE.toString());
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
