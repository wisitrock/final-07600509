package th.ac.su.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "speedrecordtb")
public class SpeedRec {

        @PrimaryKey(autoGenerate = true)
        public final int id;

        @ColumnInfo(name = "Distance")
        public final  double distance;

        @ColumnInfo(name = "Time")
        public final  double time;

        @ColumnInfo(name = "Speed")
        public final  double speed;



        // Alt+Insert

        public SpeedRec(int id, double distance, double time, double speed) {
            this.id = id;
            this.distance = distance;
            this.time = time;
            this.speed = speed;
        }


}
