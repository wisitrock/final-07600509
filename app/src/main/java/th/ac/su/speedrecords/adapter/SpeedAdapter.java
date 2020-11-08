package th.ac.su.speedrecords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import th.ac.su.speedrecords.R;
import th.ac.su.speedrecords.model.SpeedRec;


public class SpeedAdapter extends RecyclerView.Adapter<SpeedAdapter.MyViewHolder> {
    public  Context mContext;
    public SpeedRec[] mSpeedRec;
    public SpeedAdapter(Context context,  SpeedRec[] users){
        this.mContext=context;
        this.mSpeedRec =users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_speedrec,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       SpeedRec speed = mSpeedRec[position];

       holder.speedresult.setText(String.format(Locale.getDefault(), "%.1f", speed.speed)+" KM/H");

       holder.disandtime.setText(speed.distance+" METERS, "+speed.time+"SECONDS");

        holder.genderImageView.setImageResource(speed.speed>80 ? R.drawable.red_cow:0);
    }

    @Override
    public int getItemCount() {
        return mSpeedRec.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView speedresult;
        TextView disandtime;
        ImageView genderImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedresult=itemView.findViewById(R.id.result_speed_text_view);
            this.disandtime=itemView.findViewById(R.id.result_dis_and_time_text_view);
            this.genderImageView=itemView.findViewById(R.id.Overlimit_imageView);

        }
    }
}
