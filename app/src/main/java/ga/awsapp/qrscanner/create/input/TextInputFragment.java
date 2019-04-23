package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;

import ga.awsapp.qrscanner.R;


public class TextInputFragment extends Fragment {

    private UpdateView mCallback;
    private String QR_TYPE;
    String[] format = {
            "QR CODE",
            "AZTEC",
            "CODABAR",
            "CODE 39",
            "CODE 128",
            "DATA MATRIX",
            "EAN 8",
            "EAN 13",
            "ITF",
            "PDF 417",
            "UPC A"
    };

    public static TextInputFragment newInstance( ) {
        return new TextInputFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_text_input,container,false);

        EditText textView =  view.findViewById(R.id.text_input);
        TextView length = view.findViewById(R.id.length);
        Button button =  view.findViewById(R.id.create_button);
        QR_TYPE = BarcodeFormat.QR_CODE.toString();


        Spinner spinnerDropDown = (Spinner) view.findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(),android.
                R.layout.simple_spinner_dropdown_item ,format);

        spinnerDropDown.setAdapter(adapter);
        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                QR_TYPE = format[spinnerDropDown.getSelectedItemPosition()].replace(" ","_");
                switch (QR_TYPE)
                {
                    case "QR_CODE":
                    case "AZTEC":
                    case "DATA_MATRIX":
                    case "PDF_417":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_TEXT);
                        length.setText("1 - 1000");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1000)});
                        break;

                    case "CODABAR":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("1 - 16");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(16)});
                        break;

                    case "CODE_39":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("1 - 25");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(25)});
                        break;

                    case "CODE_128":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("1 - 128");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(128)});
                        break;

                    case "EAN_8":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("7");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                        break;

                    case "EAN_13":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("12");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
                        break;

                    case "ITF":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("14");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(14)});
                        break;

                    case "UPC_A":
                        textView.getText().clear();
                        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
                        length.setText("11");
                        textView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(11)});
                        break;


                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (QR_TYPE)
                {
                    case "EAN_8":
                    {
                        if (textView.getText().length() < 7)
                        {
                            textView.setError("Number length should be 7");
                            return;
                        }

                    }
                    break;

                    case "EAN_13":
                    {
                        if (textView.getText().length() < 12)
                        {
                            textView.setError("Number length should be 12");
                            return;
                        }
                    }
                    break;

                    case "ITF":
                    {
                        if (textView.getText().length() < 14)
                        {
                            textView.setError("Number length should be 14");
                            return;
                        }
                    }
                    break;

                    case "UPC_A":
                    {
                        if (textView.getText().length() < 11)
                        {
                            textView.setError("Number length should be 11");
                            return;
                        }
                    }
                    break;

                    default:
                        if(TextUtils.isEmpty(textView.getText().toString())) {
                            textView.setError("Text can't be empty");
                            return;
                        }
                        break;
                }

                String s  =  textView.getText().toString();
                mCallback.showQr(s, QR_TYPE);
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

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


}
