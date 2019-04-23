package ga.awsapp.qrscanner.licences;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.util.ViewAnimation;

public class LicencesAdapter extends RecyclerView.Adapter<LicencesAdapter.MyViewHolder> {

    private ArrayList<Licences> mLicences;
    private Context mContext;

    public LicencesAdapter(ArrayList<Licences> licences, Context context) {
        mLicences = licences;
        mContext = context;
    }

    @NonNull
    @Override
    public LicencesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.licences_list,null));
    }

    @Override
    public void onBindViewHolder(@NonNull LicencesAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(mLicences.get(i).getName());
        myViewHolder.desc.setText(mLicences.get(i).getDesc());
        myViewHolder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(myViewHolder.arrow, myViewHolder.desc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLicences.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc;
        ImageView arrow;
        RelativeLayout item_layout ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.name);
            desc  =  itemView.findViewById(R.id.desc);
            arrow=  itemView.findViewById(R.id.arrow_btn);
            item_layout =  itemView.findViewById(R.id.item_layout);
        }
    }

    private void toggleSection(View bt, final View lyt) {
        boolean show = toggleArrow(bt);
        if (show) {
            ViewAnimation.expand(lyt, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                }
            });
        } else {
            ViewAnimation.collapse(lyt);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

}