package com.magicbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicbus.R;
import com.magicbus.data.entries.SeatInformation;
import com.magicbus.data.entries.AbstractItem;
import com.magicbus.data.entries.CenterItem;
import com.magicbus.data.entries.EdgeItem;
import com.magicbus.reservation.OnSeatSelected;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private static final String TAG = AirplaneAdapter.class.getSimpleName();

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        TextView tvSeatNo;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked =(ImageView)itemView.findViewById(R.id.img_seat_booked);
            tvSeatNo = (TextView) itemView.findViewById(R.id.tvSeatNo);

        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        TextView tvSeatNo;
        private final ImageView imgSeatSelected;
        private final ImageView imgSeatBooked;


        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            imgSeatBooked =(ImageView)itemView.findViewById(R.id.img_seat_booked);
            tvSeatNo = (TextView) itemView.findViewById(R.id.tvSeatNo);

        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }
    private Map<Integer, Integer> hash = new HashMap<>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SeatInformation> seatInfoList;

    private List<AbstractItem> mItems;


    public AirplaneAdapter(Context context, List<AbstractItem> items, List<SeatInformation> seatInfoList) {
        this.seatInfoList = seatInfoList;


        Log.d(TAG, "AirplaneAdapter: " + seatInfoList.get(0).getS33());
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
        for (int i = 0; i < mItems.size(); i++) {

            int row = i / 5;

            int col = i % 5;
            if (col == 2) {
                hash.put(i, -1);
            }else if (col < 2) {
                hash.put(i, i - row + 1);
            }else {








                hash.put(i, i - row - 1 + 1);
            }

        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int adjPosition = hash.get(position);




        int type = mItems.get(position).getType();
          int value =  Integer.valueOf(mItems.get(position).getLabel());  //position == label?
        Log.d(TAG, "onBindViewHolder: checking the position clicked" + position);
      //  Log.d(TAG, "onBindViewHolder: " + seatInfoList.get(0).);

        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;
                holder.tvSeatNo.setText(String.valueOf(adjPosition));
                SeatInformation seatInformation = seatInfoList.get(0);

                String reserved = "";
                try {

                    Method getSeat = seatInformation.getClass().getDeclaredMethod("getS" + String.valueOf(adjPosition));
                    reserved = (String) getSeat.invoke(seatInformation);

                } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            if (reserved.equals("1")) {
                holder.imgSeatBooked.setVisibility(View.VISIBLE);
                holder.imgSeat.setOnClickListener(null);
                return;
            }
            holder.imgSeatBooked.setVisibility(View.INVISIBLE);
            //   holder.imgSeatBooked

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    toggleSelection(position);

                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            holder.tvSeatNo.setText(String.valueOf(adjPosition));


            SeatInformation seatInformation = seatInfoList.get(0);

            String reserved = "";
            try {

                Method getSeat = seatInformation.getClass().getDeclaredMethod("getS" + String.valueOf(adjPosition));
                reserved = (String) getSeat.invoke(seatInformation);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (reserved.equals("1")) {
                holder.imgSeatBooked.setVisibility(View.VISIBLE);


                holder.imgSeat.setOnClickListener(null);

                return;
            }

            holder.imgSeatBooked.setVisibility(View.INVISIBLE);
            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleSelection(position);
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount());

                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        }
    }

}
