package com.bisapp.rxjavaexamples;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bisapp.rxjavaexamples.database.AppDatabase;
import com.bisapp.rxjavaexamples.database.Readings;
import com.bisapp.rxjavaexamples.database.dao.ReadingDao;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

public class AddReadingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReadingAdapter adapter;
    private List<Readings> readings = new ArrayList<>();
    private Button saveBtn;
    private EditText readingEdittext;
    private ReadingDao readingDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);
        recyclerView = findViewById(R.id.recyclerview);
        saveBtn = findViewById(R.id.saveBtn);
        AppDatabase appDatabase = App.getAppDatabase(getApplicationContext());
        readingDao = appDatabase.readingDao();
        adapter = new ReadingAdapter(readings);
        readings.addAll(readingDao.filterReadings("%%"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        readingEdittext = findViewById(R.id.edittext);
        /*saveBtn.setOnClickListener(v->{
            Readings readings = new Readings();
            readings.setName(readingEdittext.getText().toString());
            readingDao.add(readings);
        });*/

        throttleButtonClicks();
        debouncing(readingDao, readingEdittext);

        //normalEdittextWatcher(readingDao, readingEdittext);

    }



    private void throttleButtonClicks() {
        RxView.clicks(saveBtn)
                .throttleFirst(3,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query->{
                    Readings readings = new Readings();
                    readings.setName(readingEdittext.getText().toString());
                    readingDao.add(readings);
                    Log.d("Throttle","Click Me!!!");
                });
    }

    private void normalEdittextWatcher(ReadingDao readingDao, EditText readingEdittext) {
        readingEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                readings.clear();
                String formattedQuery = "%"+s+"%";
                Log.d("HTTP",s.toString());
                readings.addAll(readingDao.filterReadings(formattedQuery));

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void debouncing(ReadingDao readingDao, EditText readingEdittext) {
        RxTextView.textChanges(readingEdittext)
                .debounce(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query->{
                    filterLogics(readingDao,query);
                });
    }

    private void filterLogics(ReadingDao readingDao, CharSequence query) {
        readings.clear();
        Log.d("HTTP",query.toString());
        String formattedQuery = "%"+query+"%";
        readings.addAll(readingDao.filterReadings(formattedQuery));

        adapter.notifyDataSetChanged();
    }

    private class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingHolder>{

        List<Readings> readingsList;

        public ReadingAdapter(List<Readings> readingsList) {
            this.readingsList = readingsList;
        }

        @NonNull
        @Override
        public ReadingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lay,parent,false);
            return new ReadingHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ReadingHolder holder, int position) {

            Readings readings = readingsList.get(position);
            holder.itemTv.setText(readings.getName());

        }

        @Override
        public int getItemCount() {
            return readingsList.size();
        }

        private class ReadingHolder extends RecyclerView.ViewHolder{

            TextView itemTv;
            public ReadingHolder(@NonNull View itemView) {
                super(itemView);
                itemTv = itemView.findViewById(R.id.reading_tv);
            }
        }
    }

}
