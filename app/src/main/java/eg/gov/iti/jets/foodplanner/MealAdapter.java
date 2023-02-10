package eg.gov.iti.jets.foodplanner;

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
import eg.gov.iti.jets.foodplanner.model.Meal;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {
    private Context context;
    private List<Meal> mealsList;
    public  static String MEAL_KEY="meal";

    private FavInsertListener favInsertListener;

    public MealAdapter(Context context, List<Meal> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
    }

    public MealAdapter(Context context, List<Meal> mealsList, FavInsertListener favInsertListener) {
        this.context = context;
        this.mealsList = mealsList;
        this.favInsertListener = favInsertListener;
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
//        Picasso.get().load(meal.getStrMealThumb())
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.mealImageView);

        Picasso.with(context).load(meal.getStrMealThumb())
                .into(holder.mealImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (holder.progressBar != null) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.mealNameTv.setText(meal.getStrMeal());
        holder.mealCategoryTv.setText(meal.getStrCategory());

        holder.favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favInsertListener.insertFavClick(meal);
                holder.favImageView.setImageResource(R.drawable.ic_baseline_favorite_red);
                Toast.makeText(context, "Meal added to favorite", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Meal_Details_Activity.class);
                i.putExtra(MEAL_KEY,mealsList.get(position));
                context.startActivity(i);
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
        ImageView mealImageView,favImageView;
        TextView mealNameTv,mealCategoryTv;

        ProgressBar progressBar;

        public MyViewHolder(@NonNull View mealView) {
            super(mealView);
            mealImageView = mealView.findViewById(R.id.meal_item_imageView);
            mealNameTv = mealView.findViewById(R.id.meal_item_title_textview);
            mealCategoryTv = mealView.findViewById(R.id.meal_item_category_textView);
            favImageView = mealView.findViewById(R.id.meal_item_fav_imageview);
            progressBar = mealView.findViewById(R.id.meal_item_progressbar);
        }
    }
}
