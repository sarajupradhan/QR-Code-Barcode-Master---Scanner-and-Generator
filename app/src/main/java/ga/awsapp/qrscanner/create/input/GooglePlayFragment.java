package ga.awsapp.qrscanner.create.input;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ga.awsapp.qrscanner.R;
import ga.awsapp.qrscanner.create.AppListAdapter;
import ga.awsapp.qrscanner.create.schemes.GooglePlay;


public class GooglePlayFragment extends Fragment {

    UpdateView mCallback;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    public static GooglePlayFragment newInstance( ) {
        return new GooglePlayFragment();
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

        final View view =  inflater.inflate(R.layout.fragment_apps_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        new getInstallApp().execute();
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

        public class getInstallApp extends AsyncTask<Void, Void, List<App>> {
            @Override
            protected List<App> doInBackground(Void... voids) {

                List<App> Apks = new ArrayList<>();
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                Context context = getContext();
              if (context != null) {
                  List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);

                  for (ResolveInfo resolveInfo : resolveInfoList) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;

                    if (!isSystemPackage(resolveInfo)) {
                        Apks.add(new App(activityInfo.applicationInfo.packageName, GetAppName(activityInfo.applicationInfo.packageName, context),
                                getAppIconByPackageName(activityInfo.applicationInfo.packageName,context)));
                    }
                }
            }

                return Apks;
            }

            @Override
            protected void onPostExecute(List<App> strings) {
                super.onPostExecute(strings);

                progressBar.setVisibility(View.GONE);
                AppListAdapter appListAdapter = new AppListAdapter(getContext(), strings);
                recyclerView.setAdapter(appListAdapter);
                appListAdapter.setOnItemClickListener(new AppListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String text) {

                GooglePlay googlePlay =  new GooglePlay() ;
                googlePlay.setAppPackage(text);
                mCallback.showQr(googlePlay.toString(), BarcodeFormat.QR_CODE.toString());
                    }
                });
            }
        }


        public Drawable getAppIconByPackageName(String ApkTempPackageName,Context context) {

            Drawable drawable;

            try {
                drawable = context.getPackageManager().getApplicationIcon(ApkTempPackageName);

            } catch (PackageManager.NameNotFoundException e) {

                e.printStackTrace();

                drawable = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
            }
            return drawable;
        }

        public String GetAppName(String ApkPackageName, Context context) {

            String Name = "";

            ApplicationInfo applicationInfo;

            PackageManager packageManager = context.getPackageManager();

            try {

                applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);

                if (applicationInfo != null) {

                    Name = (String) packageManager.getApplicationLabel(applicationInfo);
                }

            } catch (PackageManager.NameNotFoundException e) {

                e.printStackTrace();
            }
            return Name;
        }

        public boolean isSystemPackage(ResolveInfo resolveInfo) {

            return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
        }

        public class App {

            String packageName;
            String name;
            Drawable drawable;

            public App() {
            }

            public App(String packageName, String name, Drawable drawable) {
                this.packageName = packageName;
                this.name = name;
                this.drawable = drawable;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Drawable getDrawable() {
                return drawable;
            }

            public void setDrawable(Drawable drawable) {
                this.drawable = drawable;
            }
        }


    }

