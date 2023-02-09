package eg.gov.iti.jets.foodplanner;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientsAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingrediants_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
//        Picasso.get().load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",ingredient.getStrIngredient()))
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.ingredientImageView);

        Picasso.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",ingredient.getStrIngredient()))
                .into(holder.ingredientImageView, new com.squareup.picasso.Callback() {
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

        holder.ingredientNameTv.setText(ingredient.getStrIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

   public void setData(final List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImageView;
        TextView ingredientNameTv;

        ProgressBar progressBar;
        public MyViewHolder(@NonNull View ingredientView) {
            super(ingredientView);
            ingredientImageView = ingredientView.findViewById(R.id.ingredientImage);
            ingredientNameTv = ingredientView.findViewById(R.id.ingredientName);
            progressBar = ingredientView.findViewById(R.id.ingredient_progressbar);
        }
    }
}
