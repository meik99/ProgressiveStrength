package com.rynkbit.progressivestrength;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.progressivestrength.db.sqlite.facade.DayFacade;
import com.rynkbit.progressivestrength.entity.Day;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by michael on 26.03.18.
 */

class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayViewHolder> {
    private final Context context;
    private final DayFacade dayFacade;

    public DayListAdapter(Context context) {
        this.context = context;
        this.dayFacade = new DayFacade(context);
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_day, parent, false);
        DayViewHolder viewHolder = new DayViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {
        Day day = dayFacade.findAll().get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dddd dd-MMMM-yyyy");

        holder.txtDate.setText(sdf.format(day.getDate()));
        holder.txtNumberExercises.setText(
                String.format(
                        Locale.getDefault(),
                        holder.itemView.getContext().getString(R.string.number_exercises),
                        day.getExercises().size()));
    }

    @Override
    public int getItemCount() {
        return dayFacade.findAll().size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder{

        TextView txtDate;
        TextView txtNumberExercises;

        public DayViewHolder(View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDate);
            txtNumberExercises = itemView.findViewById(R.id.txtNumberExercises);
        }
    }
}
