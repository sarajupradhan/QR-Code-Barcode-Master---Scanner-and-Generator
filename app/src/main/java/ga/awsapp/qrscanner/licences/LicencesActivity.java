package ga.awsapp.qrscanner.licences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ga.awsapp.qrscanner.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LicencesActivity extends AppCompatActivity {

    @BindView(R.id.list_view) ListView mListView;
    @BindView(R.id.recycler_view) RecyclerView  mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licences);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Licences");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayList<Licences> licences1 =  new ArrayList<>();
        licences1.add(new Licences("ZXing","Copyright (C) 2010 ZXing authors\n" +
                " Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " you may not use this file except in compliance with the License.\n" +
                " You may obtain a copy of the License at\n" +
                "      http://www.apache.org/licenses/LICENSE-2.0\n" +
                " Unless required by applicable law or agreed to in writing, software\n" +
                " distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " See the License for the specific language governing permissions and\n" +
                " limitations under the License.."));
        licences1.add(new Licences("ZXing Android Embedded","Copyright (C) 2012-2018 ZXing authors, Journey Mobile\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."));

        licences1.add(new Licences("RxAndroid","Copyright 2015 The RxAndroid authors\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."));

        licences1.add(new Licences("Butter Knife","Copyright 2013 Jake Wharton\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."));
        licences1.add(new Licences("Gson","Copyright 2008 Google Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."));
        licences1.add(new Licences("LeakCanary","Copyright 2015 Square, Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License."));


        Observable<Licences> observable = Observable.fromIterable(licences1).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Licences>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Licences licences) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LicencesAdapter licencesAdapter =  new LicencesAdapter(licences1,LicencesActivity.this);
                mRecyclerView.hasFixedSize();
                mRecyclerView.setLayoutManager(new LinearLayoutManager(LicencesActivity.this));
                mRecyclerView.setAdapter(licencesAdapter);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
