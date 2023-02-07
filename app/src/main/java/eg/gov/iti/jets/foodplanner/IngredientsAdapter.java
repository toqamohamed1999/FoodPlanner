package eg.gov.iti.jets.foodplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        Picasso.get().load(ingredient.getStrThumbnail())
                .placeholder(R.drawable.cake)
                .into(holder.ingredientImageView);
        holder.ingredientNameTv.setText(ingredient.getStrIngredient());

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    void setData(final List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImageView;
        TextView ingredientNameTv;

        public MyViewHolder(@NonNull View ingredientView) {
            super(ingredientView);
            ingredientImageView = ingredientView.findViewById(R.id.ingredientImage);
            ingredientNameTv = ingredientView.findViewById(R.id.ingredientName);
        }
    }
}
