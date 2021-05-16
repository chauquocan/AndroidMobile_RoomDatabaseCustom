package com.example.androidmobile_roomdatabasecustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<Location> locations;
    LocationDao locationDao;

    public LocationAdapter(Context context, List<Location> locations, LocationDao locationDao) {
        this.context = context;
        this.locations = locations;
        this.inflater = LayoutInflater.from(context);
        this.locationDao = locationDao;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_item,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.tvName.setText(location.getId()+". " + location.getName());
        holder.btn_update.setBackgroundResource(R.drawable.ic_edit);
        holder.btn_remove.setBackgroundResource(R.drawable.ic_remove);

        holder.btn_remove.setOnClickListener(v -> {
            locationDao.delete(location);
            locations= locationDao.getAll();
            Toast.makeText(context, location.getName() +" Deleted", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });
        holder.btn_update.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LocationAdapter adapter;
        TextView tvName;
        ImageButton btn_update,btn_remove;

        public ViewHolder(@NonNull View itemView,LocationAdapter adapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btn_remove =itemView.findViewById(R.id.btn_remove);
            btn_update = itemView.findViewById(R.id.btn_update);
        }
    }
}
