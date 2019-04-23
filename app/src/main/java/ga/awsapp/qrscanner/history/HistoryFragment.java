package ga.awsapp.qrscanner.history;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.details.DetailsActivity;
import ga.awsapp.qrscanner.model.Scan;
import ga.awsapp.qrscanner.viewmodel.ScanViewModel;

import static ga.awsapp.qrscanner.main.HomeActivity.ADD_TO_HISTORY;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_STRING;
import static ga.awsapp.qrscanner.main.HomeActivity.QR_TYPE;


public class HistoryFragment  extends Fragment {

    @BindView(R.id.recycler_view) RecyclerView  mRecyclerView;
    @BindView(R.id.empty_view) LinearLayout emptyLayout;
    private ScanListAdapter  scanListAdapter;
    private List<Scan> mScanList=  new ArrayList<>();
    private ScanViewModel scanViewModel;
    public HistoryFragment() {

    }

    @NonNull
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.history_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_delete:
                new AlertDialog.Builder(getContext())
                        .setTitle("Are you sure want to clear all history ?")
                        .setMessage("This will delete all the history include scanned and generated codes.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // Log.d("MainActivity", "Sending atomic bombs to Jupiter");
                                scanViewModel.deleteAllScans();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view  =  inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scanViewModel = ViewModelProviders.of(getActivity()).get(ScanViewModel.class);
        scanViewModel.getAllScans().observe(this, new Observer<List<Scan>>() {
            @Override
            public void onChanged(@Nullable List<Scan> scans) {
                mScanList = scans;
                Log.d("checklistItem",new Gson().toJson(mScanList));
                if (mScanList.size()<1) {
                    emptyLayout.setVisibility(View.VISIBLE);

                }
                else {
                    emptyLayout.setVisibility(View.GONE);
                }

                scanListAdapter = new ScanListAdapter(getContext(), mScanList);
                mRecyclerView.setAdapter(scanListAdapter);

                // Log.e("Adapter",""+items.size());
                scanListAdapter.setOnItemClickListener(new ScanListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String text, String type, int position) {
                        Intent intent = new Intent(getContext(), DetailsActivity.class);
                        intent.putExtra(QR_STRING,text);
                        intent.putExtra(QR_TYPE,type);
                        intent.putExtra(ADD_TO_HISTORY,false);
                        startActivity(intent);
                    }
                });
            }});



        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            public static final float ALPHA_FULL = 1.0f;

            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;

                    Paint p = new Paint();
                    Bitmap icon;

                    if (dX > 0) {

                        //color : left side (swiping towards right)
                        p.setARGB(255, 255, 0, 0);
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);

                        // icon : left side (swiping towards right)
                        icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.delete);
                        c.drawBitmap(icon,
                                (float) itemView.getLeft() + convertDpToPx(16),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,
                                p);
                    } else {

                        //color : right side (swiping towards left)
                        p.setARGB(255, 255, 0, 0);

                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);

                        //icon : left side (swiping towards right)
                        icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.delete);
                        c.drawBitmap(icon,
                                (float) itemView.getRight() - convertDpToPx(16) - icon.getWidth(),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,
                                p);
                    }

                    // Fade out the view when it is swiped out of the parent
                    final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);

                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }

            private int convertDpToPx(int dp){
                return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //swiped position

                scanViewModel.delete(mScanList.get(position));


            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
