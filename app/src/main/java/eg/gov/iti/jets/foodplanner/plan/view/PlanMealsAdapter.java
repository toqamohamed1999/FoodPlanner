package eg.gov.iti.jets.foodplanner.plan.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eg.gov.iti.jets.foodplanner.MyDialog;
import eg.gov.iti.jets.foodplanner.favorites.view.FavInsertListener;
import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.MealDetails.view.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public class PlanMealsAdapter extends RecyclerView.Adapter<PlanMealsAdapter.MyViewHolder> {
    private Context context;
    private List<PlanMeal> mealsList;

    private PlanMealDeleteListener planMealDeleteListener;
    public static String PlAN_MEAL_KEY = "planMeal";

    public static String PLAN_MEAL_ADAPTER_TYPE = "PlanMealsAdapter";

    public PlanMealsAdapter(Context context, List<PlanMeal> mealsList, PlanMealDeleteListener planMealDeleteListener) {
        this.context = context;
        this.mealsList = mealsList;
        this.planMealDeleteListener = planMealDeleteListener;

    }

    @NonNull
    @Override
    public PlanMealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plan_meal_item, parent, false);

        return new PlanMealsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlanMeal planMeal = mealsList.get(position);

        Picasso.with(context).load(planMeal.getStrMealThumb())
                .into(holder.mealImageView);
        holder.mealNameTv.setText(planMeal.getStrMeal());
        holder.mealCategoryTv.setText(planMeal.getStrCategory());





        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlanMeal(planMeal);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Meal_Details_Activity.class);
                i.putExtra(PlAN_MEAL_KEY, mealsList.get(position));
                i.putExtra("adapterType", PLAN_MEAL_ADAPTER_TYPE);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void setData(final List<PlanMeal> mealsList) {
        this.mealsList = mealsList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImageView, deleteImageView;
        TextView mealNameTv, mealCategoryTv;

        public MyViewHolder(@NonNull View mealView) {
            super(mealView);
            mealImageView = mealView.findViewById(R.id.planMeal_item_imageView);
            mealNameTv = mealView.findViewById(R.id.planMeal_item_title_textview);
            mealCategoryTv = mealView.findViewById(R.id.planMeal_item_category_textView);
            deleteImageView = mealView.findViewById(R.id.planMeal_item_delete_imageview);
        }
    }

    private void deletePlanMeal(PlanMeal planMeal) {
        AlertDialog.Builder builder = MyDialog.myDialog(context);
        builder.setMessage("Do you want to remove " + planMeal.getStrMeal() + " meal from " + planMeal.getWeekDay() + "'s plan?");
        builder.setIcon(R.drawable.baseline_delete_24);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

            planMealDeleteListener.removeFavMealClick(planMeal);

        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
