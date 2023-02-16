package eg.gov.iti.jets.foodplanner.plan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.gov.iti.jets.foodplanner.R;

public class PlanRecycleAdapter extends RecyclerView.Adapter<PlanRecycleAdapter.ViewHolder> {


    private final Context context;

    private final List<String> days;
    private final LayoutInflater layoutInflater;
    WeekDayListener weekDayListener;

    private static int rowIndex = -1;

    public PlanRecycleAdapter(Context context, List<String> days, WeekDayListener weekDayListener) {
        this.context = context;
        this.days = days;
        this.weekDayListener = weekDayListener;
        layoutInflater = LayoutInflater.from(context);
        rowIndex = -1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = layoutInflater.inflate(R.layout.week_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String d = days.get(position);
        holder.day.setText(d);

        if (rowIndex == holder.getLayoutPosition()) {
            holder.day.setTextColor(holder.itemView.getContext().getColor(R.color.brown));
        } else {
            holder.day.setTextColor(holder.itemView.getContext().getColor(R.color.black));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekDayListener.getStoredPlanMeals(d);

                rowIndex = holder.getLayoutPosition();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView day;
        ConstraintLayout layoutC ;
        CardView card ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            layoutC = itemView.findViewById(R.id.layOutConstarin);
            card = itemView.findViewById(R.id.cardWeek);

        }
    }
}