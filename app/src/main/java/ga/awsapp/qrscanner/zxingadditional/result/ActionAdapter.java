package ga.awsapp.qrscanner.zxingadditional.result;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;


import ga.awsapp.qrscanner.R;


public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.MyViewHolder> {

    private HashMap<String, Integer> map = new HashMap<>();
    private String[]objects;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    public ActionAdapter(Context context, String[] objects)
    {
        this.context = context;
        this.objects = objects;
        // Options that don't have an icon should use -1 as drawable ID
        map.put(context.getString(R.string.button_add_calendar), R.drawable.ic_add_calendar);
        ////
        map.put(context.getString(R.string.button_copy_text),R.drawable.ic_show_map);
        map.put(context.getString(R.string.button_add_contact), R.drawable.ic_add_contact);
        map.put(context.getString(R.string.button_web_search), R.drawable.ic_web_search);
        map.put(context.getString(R.string.button_dial), R.drawable.ic_dial);
        map.put(context.getString(R.string.button_email), R.drawable.ic_send_email);
        map.put(context.getString(R.string.button_open_browser), R.drawable.ic_web_browse);
        map.put(context.getString(R.string.button_share_by_email), R.drawable.ic_share_via_email);
       // map.put(context.getString(R.string.button_email), R.drawable.ic_send_mms);
        map.put(context.getString(R.string.button_share_by_sms), R.drawable.ic_share_via_sms);
        map.put(context.getString(R.string.button_sms), R.drawable.ic_send_sms);
        map.put(context.getString(R.string.button_mms), R.drawable.ic_send_mms);
        map.put(context.getString(R.string.button_wifi), R.drawable.ic_connect_wifi);
        map.put(context.getString(R.string.button_show_map), R.drawable.ic_show_map);
        map.put(context.getString(R.string.button_share_text), R.drawable.ic_share);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_action,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String title = objects[position];
        int drawableID = map.get(title);
        if (drawableID != -1) {
            // TODO: IDK why the F-word does this throw a resource not found exception here on API 16 and maybe lower
            try {

                holder.actionIcon.setImageDrawable(ContextCompat.getDrawable(context, drawableID));
                holder.actionName.setText(title);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(view,  position);
                        }

                    }
                });
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
                // nothing, you just wont get the awesome icons on the phone :(
            }
        }



    }


    @Override
    public int getItemCount() {
        return objects.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView actionIcon;
        private LinearLayout itemLayout;
        private TextView actionName ;
        public MyViewHolder(View itemView) {
            super(itemView);

            actionIcon = itemView.findViewById(R.id.action_icon);
            itemView = itemView.findViewById(R.id.item_layout);
            actionName = itemView.findViewById(R.id.action_name);
        }
    }
}
