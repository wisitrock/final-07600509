package th.ac.su.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.Locale;

import th.ac.su.speedrecords.db.AppDatabase;
import th.ac.su.speedrecords.model.SpeedRec;
import th.ac.su.speedrecords.util.AppExecutors;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveButton =findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText distance_EditText = findViewById(R.id.distance_edit_text);
                EditText time__EditText = findViewById(R.id.time_edit_text);
                String distancetext =distance_EditText.getText().toString().trim();
                String timetext = time__EditText.getText().toString().trim();

                double distance = Double.parseDouble(distancetext);
                double time = Double.parseDouble(timetext);

                double speed = (distance / 1000) / (time / 3600);

              //  mSpeedTextView.setText(String.format(Locale.getDefault(), "%.2f", speed));

                final SpeedRec speedrec = new SpeedRec(0, distance, time, speed);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // workerthread
                        AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                        db.userDao().addUser(speedrec);
                        finish();
                    }
                });
            }
        });
    }
}