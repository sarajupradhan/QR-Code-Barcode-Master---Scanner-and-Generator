package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.zxing.BarcodeFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.schemes.Event;

public class EventInputFragment extends Fragment {

    private UpdateView mCallback;
    private int year;
    private int month;
    private int dayOfMonth;
    private int currentHour ;
    private int currentMinute;
    private String amPm ;

    private SimpleDateFormat dateFormate;
    private SimpleDateFormat timeFormate;
    Calendar cal = Calendar.getInstance();

    @BindView(R.id.start_date) TextView startDate;
    @BindView(R.id.end_date) TextView endDate;
    @BindView(R.id.start_time) TextView startTime;
    @BindView(R.id.end_time) TextView endTime;
    @BindView(R.id.text_input) EditText summery;


    public static EventInputFragment newInstance( ) {
        return new EventInputFragment();
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


        View view =  inflater.inflate(R.layout.fragment_event_input, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        dateFormate = new SimpleDateFormat("dd MMM yyyy");
        timeFormate = new SimpleDateFormat("hh:mm aa");

        Date date =  new Date();
        startDate.setText(dateFormate.format(date));
        endDate.setText(dateFormate.format(date));
        startTime.setText(timeFormate.format(date));
        endTime.setText(timeFormate.format(new Date(System.currentTimeMillis() + 3600 * 1000)));

    }

    @OnClick(R.id.start_date)
     void selectStartDate( )
    {  year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getContext(),
                (datePicker, year, month, day) -> {
                    startDate.setText(formatDate(year,month,day));
                }, year, month, dayOfMonth).show();
    }


    @OnClick(R.id.end_date)
    void selectEndDate( )
    {
        DatePickerDialog datePickerDialog =   new DatePickerDialog(getContext(),
                (datePicker, year, month, day) -> {
                    endDate.setText(formatDate(year,month,day));
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    @OnClick(R.id.start_time)
    void selectStartTime( )
    {
        TimePickerDialog timePicker =  new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "pm";
                    hourOfDay = hourOfDay - 12;
                } else {
                    amPm = "am";
                }
                startTime.setText(String.format("%02d:%02d", hourOfDay, minutes) +" "+amPm);
            }
        }, currentHour, currentMinute, false);
        timePicker.show();

    }

    @OnClick(R.id.end_time)
    void selectEndTime( )
    {
        TimePickerDialog timePicker =  new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "pm";
                    hourOfDay = hourOfDay - 12;
                } else {
                    amPm = "am";
                }
                endTime.setText(String.format("%02d:%02d", hourOfDay, minutes) +" "+amPm);
            }
        }, currentHour, currentMinute, false);
        timePicker.show();
    }

    @OnClick(R.id.create_button)
    void createQr( ){

        if(TextUtils.isEmpty(summery.getText())) {
            summery.setError("Summery can't be empty");
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m a");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
        Date start = null;
        Date end = null;
        try {
            start = dateFormat.parse(startDate.getText().toString()+" "+ startTime.getText().toString());
            end = dateFormat.parse(endDate.getText().toString()+" "+ endTime.getText().toString());

            Event event =  new Event();
             event.setStart(sdf.format(start));
             event.setEnd(sdf.format(end));
             event.setSummary(summery.getText().toString());

            Log.d("checkLogEvent",event.toString());

            mCallback.showQr(event.toString(), BarcodeFormat.QR_CODE.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    private String formatDate(int year, int month, int day) {

        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        return dateFormate.format(date);
    }

}
