package eg.gov.iti.jets.foodplanner.plan.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eg.gov.iti.jets.foodplanner.favorites.view.FavInsertListener;
import eg.gov.iti.jets.foodplanner.MealAdapter;
import eg.gov.iti.jets.foodplanner.MealDetails.view.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;

public class PlanMealsAdapter  extends RecyclerView.Adapter<PlanMealsAdapter.MyViewHolder>{
    private Context context;
    private List<PlanMeal> mealsList;
    public  static String MEAL_KEY="meal";

    public PlanMealsAdapter(Context context, List<PlanMeal> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public PlanMealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plan_meal_item, parent, false);

        return new PlanMealsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlanMeal meal = mealsList.get(position);
//        Picasso.get().load(meal.getStrMealThumb())
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.mealImageView);

        Picasso.with(context).load(meal.getStrMealThumb())
                .into(holder.mealImageView);

        holder.mealNameTv.setText(meal.getStrMeal());
        holder.mealCategoryTv.setText(meal.getStrCategory());

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                favInsertListener.insertFavClick(meal);
//                holder.favImageView.setImageResource(R.drawable.ic_baseline_favorite_red);
                //Toast.makeText(context, "Meal deleted from plan", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i=new Intent(context, Meal_Details_Activity.class);
//                i.putExtra(MEAL_KEY,mealsList.get(position));
//                context.startActivity(i);
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
        ImageView mealImageView,deleteImageView;
        TextView mealNameTv,mealCategoryTv;

        public MyViewHolder(@NonNull View mealView) {
            super(mealView);
            mealImageView = mealView.findViewById(R.id.planMeal_item_imageView);
            mealNameTv = mealView.findViewById(R.id.planMeal_item_title_textview);
            mealCategoryTv = mealView.findViewById(R.id.planMeal_item_category_textView);
            deleteImageView = mealView.findViewById(R.id.planMeal_item_delete_imageview);
        }
    }
}
