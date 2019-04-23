package ga.awsapp.qrscanner.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.model.Scan;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScanListAdapter extends RecyclerView.Adapter<ScanListAdapter.TaskHolder>{

    private List<Scan> scanList=new ArrayList<>();

    private Context context;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(String text, String type, int position);
    }

    public ScanListAdapter(Context context, List<Scan> scanList){
        this.context = context;
        this.scanList = scanList;
    }


    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.scan_item,parent,false);
        return new TaskHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {

       Scan scan =  scanList.get(position);

        holder.taskName.setText(scan.getText()+"");

        int imageResource = context.getResources().getIdentifier(scan.getIcon(), null, context.getPackageName());
        holder.img.setImageResource(imageResource);

        Timestamp stamp = new Timestamp(scan.getTimestamp());
        Date date = new Date(stamp.getTime());
        holder.time.setText(new SimpleDateFormat("dd MMM yyyy   HH:mm:SS").format(date));
        holder.format.setText(scan.getType().replace("_"," "));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick( scan.getText(),scan.getType(), position);
                }
            }
        });



    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        return scanList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder{
        TextView taskName, time, format;
        ImageView img;
        public TaskHolder(View v){
            super(v);
            taskName=v.findViewById(R.id.text);
            img=v.findViewById(R.id.img);
            time=v.findViewById(R.id.time);
            format =v.findViewById(R.id.format)

          ;
        }

    }
}
