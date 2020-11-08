package th.ac.su.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import th.ac.su.speedrecords.adapter.SpeedAdapter;
import th.ac.su.speedrecords.db.AppDatabase;
import th.ac.su.speedrecords.model.SpeedRec;
import th.ac.su.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.speed_records_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addButton = findViewById(R.id.button_add_in_first_page);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddRecordActivity.class);
                startActivity(i);
            }
        });

    }
    protected void onResume() {
        super.onResume();
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final SpeedRec[] speedrecs = db.userDao().getAllUser();


                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        SpeedAdapter adapter = new  SpeedAdapter(MainActivity.this, speedrecs);
                        TextView totaltext = findViewById(R.id.textView_Total);
                        totaltext.setText("TOTAL :"+ speedrecs.length);

                        int Countoverlimit=0;
                        for(SpeedRec s :speedrecs){
                            if(s.speed>80)
                                Countoverlimit++;
                        }
                        TextView Overlimit = findViewById(R.id.textView_overlimit);
                        Overlimit.setText("OVER LIMIT : "+Countoverlimit);
                        mRecyclerView.setAdapter(adapter);
                    }
                });


            }
        });
    }

}

