package eg.gov.iti.jets.foodplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {
    private Context context;
    private List<Meal> mealsList;

    public MealAdapter(Context context, List<Meal> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal meal = mealsList.get(position);
        Picasso.get().load(meal.getStrMealThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mealImageView);
        holder.mealNameTv.setText(meal.getStrMeal());
        holder.mealCategoryTv.setText(meal.getStrCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Meal_Details_Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public void setData(final List<Meal> mealsList) {
        this.mealsList = mealsList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImageView;
        TextView mealNameTv,mealCategoryTv;

        public MyViewHolder(@NonNull View mealView) {
            super(mealView);
            mealImageView = mealView.findViewById(R.id.meal_item_imageView);
            mealNameTv = mealView.findViewById(R.id.meal_item_title_textview);
            mealCategoryTv = mealView.findViewById(R.id.meal_item_category_textView);
        }
    }
}
