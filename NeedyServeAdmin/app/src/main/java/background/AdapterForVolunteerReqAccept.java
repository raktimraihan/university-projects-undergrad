package background;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.needyserveadmin.R;
import com.example.needyserveadmin.VolunteerFinalAccept;
import com.example.needyserveadmin.VolunteerInformation;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterForVolunteerReqAccept extends RecyclerView.Adapter<AdapterForVolunteerReqAccept.ExampleViewHolder> {
    private ArrayList<VolunteerInformation> volunteerInformations;
    private Context context;
    private String id, name, email, phone, gender, donor_status;

    @Override
    public AdapterForVolunteerReqAccept.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_navigation_drawer_item, parent, false);
        AdapterForVolunteerReqAccept.ExampleViewHolder exampleViewHolder = new AdapterForVolunteerReqAccept.ExampleViewHolder(view);
        return  exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterForVolunteerReqAccept.ExampleViewHolder holder, final int position) {
        final VolunteerInformation vol = volunteerInformations.get(position);
        holder.vdName.setText(vol.getName().toUpperCase());
        holder.vEmail.setText("Email: "+vol.getEmail());
        holder.vPhone.setText("Phone: "+vol.getPhone());
        holder.vGender.setText("Gender: "+vol.getGender());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder window= new AlertDialog.Builder(context);
                window.setTitle("Caution!!").setMessage("Once you register a volunteer, It can't be undone immediately..").setPositiveButton("Register as Volunteer. ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BackGroundVolunteerBan backgroundVolunteerCheckoutFinal = new BackGroundVolunteerBan(context,vol.getId());
                        backgroundVolunteerCheckoutFinal.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    }
                }).setNegativeButton("Cancel",null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return volunteerInformations.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView vdName, vLocation, vPhone, vServingPerson, vEmail, vGender;
        public ConstraintLayout constraintLayout;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            vdName = itemView.findViewById(R.id.vol_name);
            vEmail = itemView.findViewById(R.id.vol_email);
            vPhone = itemView.findViewById(R.id.vol_phone);
            vGender = itemView.findViewById(R.id.vol_gender);
            constraintLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

    public AdapterForVolunteerReqAccept(ArrayList<VolunteerInformation> foodItems, Context context, String id){
        this.volunteerInformations = foodItems;
        this.context=context;
        this.id=id;
    }


}
