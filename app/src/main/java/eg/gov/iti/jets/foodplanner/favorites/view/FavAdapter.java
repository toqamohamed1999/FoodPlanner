package eg.gov.iti.jets.foodplanner.favorites.view;

import static eg.gov.iti.jets.foodplanner.MealAdapter.MEAL_ADAPTER_TYPE;
import static eg.gov.iti.jets.foodplanner.MealAdapter.MEAL_KEY;

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

import eg.gov.iti.jets.foodplanner.MealDetails.view.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.model.Meal;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {
    private Context context;
    private List<Meal> mealsList;

    private FavListenerInterface favListenerInterface;

    public FavAdapter(Context context, List<Meal> mealsList,FavListenerInterface favListenerInterface) {
        this.context = context;
        this.mealsList = mealsList;
        this.favListenerInterface = favListenerInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal meal = mealsList.get(position);
        Picasso.with(context).load(meal.getStrMealThumb())
                .into(holder.mealImageView);



        holder.mealNameTv.setText(meal.getStrMeal());
        holder.mealCategoryTv.setText(meal.getStrCategory());


//        holder.favImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                favListenerInterface.removeFavMealClick(meal);
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, Meal_Details_Activity.class);
                i.putExtra(MEAL_KEY,mealsList.get(position));
                i.putExtra("adapterType",MEAL_ADAPTER_TYPE);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    void setData(final List<Meal> mealsList) {
        this.mealsList = mealsList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImageView,favImageView;
        TextView mealNameTv,mealCategoryTv;


        public MyViewHolder(@NonNull View mealView) {
            super(mealView);
            mealImageView = mealView.findViewById(R.id.fav_item_imageView);
            favImageView = mealView.findViewById(R.id.fav_item_fav_imageview);
            mealNameTv = mealView.findViewById(R.id.fav_item_title_textview);
            mealCategoryTv = mealView.findViewById(R.id.fav_item_category_textView);

        }
    }
}
