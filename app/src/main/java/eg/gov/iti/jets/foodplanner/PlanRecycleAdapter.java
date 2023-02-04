package eg.gov.iti.jets.foodplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanRecycleAdapter extends RecyclerView.Adapter<PlanRecycleAdapter.ViewHolder> {


    private final Context context;

    private final List<String> days;
    private final LayoutInflater layoutInflater;

    public PlanRecycleAdapter(Context context, List<String> days) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.days = days;
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

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView day;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);

        }
    }
}