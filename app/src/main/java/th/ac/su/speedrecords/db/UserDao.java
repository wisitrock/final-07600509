package th.ac.su.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.speedrecords.model.SpeedRec;

@Dao
public interface UserDao {

    @Query("SELECT * FROM speedrecordtb")
    SpeedRec [] getAllUser();

    @Query("SELECT * FROM speedrecordtb WHERE id=:id")
    SpeedRec getUserById(int id);

    @Insert
    void addUser(SpeedRec users);

    @Delete
    void deleteUser(SpeedRec user);

}
