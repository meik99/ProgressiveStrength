package com.rynkbit.progressivestrength;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rynkbit.progressivestrength.day.DayActivity;
import com.rynkbit.progressivestrength.db.sqlite.facade.DayFacade;
import com.rynkbit.progressivestrength.entity.Day;

import java.text.SimpleDateFormat;
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
        final Day day = dayFacade.findAll().get(position);
        SimpleDateFormat sdf = new DayDateFormat();

        holder.txtDate.setText(sdf.format(day.getDate()));
        holder.txtNumberExercises.setText(
                String.format(
                        Locale.getDefault(),
                        holder.itemView.getContext().getString(R.string.number_exercises),
                        day.getExercises().size()));

        holder.itemView.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), DayActivity.class);
            intent.putExtra(DayActivity.EXTRA_DAY, day);
            view.getContext().startActivity(intent);
        });
        holder.itemView.setOnLongClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.delete_day);
            builder.setMessage(R.string.delete_day_warning);
            builder.setPositiveButton(R.string.delete, (dialog, which) -> {
                dayFacade.remove(day.getId());
                notifyDataSetChanged();
                dialog.dismiss();
            });
            builder.setNegativeButton(R.string.cancel, ((dialog, which) -> {
                dialog.dismiss();
            }));
            builder.create().show();
            return false;
        });
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
