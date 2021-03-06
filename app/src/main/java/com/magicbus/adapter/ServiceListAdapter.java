package com.magicbus.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.data.entries.ServiceList;

import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {

    List<ServiceList> serviceLists;




    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public ServiceListAdapter(List<ServiceList> serviceLists) {
        this.serviceLists = serviceLists;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ServiceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListAdapter.MyViewHolder myViewHolder, int position) {
        ServiceList serviceList = serviceLists.get(position);
        myViewHolder.routeName.setText(serviceList.getRoutename());
        myViewHolder.departCity.setText(serviceList.getRoute_startfrom());
        myViewHolder.arriveCity.setText(serviceList.getRoute_destination());
    }

    public String getItemIdString(int position) {
        return serviceLists.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return serviceLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView routeName, departCity, arriveCity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            routeName = itemView.findViewById(R.id.tv_routeName);
            departCity = itemView.findViewById(R.id.tv_startFrom);
            arriveCity = itemView.findViewById(R.id.tv_destination);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onItemClickListener.onItemClick(v, getPosition());

//            FragmentTransaction ft = Context

        }
    }
}
