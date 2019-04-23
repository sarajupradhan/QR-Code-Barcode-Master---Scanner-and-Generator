package ga.awsapp.qrscanner.create;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.input.GooglePlayFragment;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder>{

    Context context;
    List<GooglePlayFragment.App> stringList;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(String text );
    }

    public AppListAdapter(Context context, List<GooglePlayFragment.App> list){
        this.context = context;
        stringList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout cardView;
        public ImageView imageView;
        public TextView textView_App_Name;
        public TextView textView_App_Package_Name;
        public ViewHolder (View view){

            super(view);
            cardView = (LinearLayout) view.findViewById(R.id.card_view);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            textView_App_Name = (TextView) view.findViewById(R.id.Apk_Name);
            textView_App_Package_Name = (TextView) view.findViewById(R.id.Apk_Package_Name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view2 = LayoutInflater.from(context).inflate(R.layout.app_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){

        final GooglePlayFragment.App app =  stringList.get(position);

        viewHolder.textView_App_Name.setText(app.getName());
        viewHolder.textView_App_Package_Name.setText(app.getPackageName());
        viewHolder.imageView.setImageDrawable(app.getDrawable());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(app.getPackageName());
                }
            }
        });
    }



    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount(){

        return stringList.size();
    }

}