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

import eg.gov.iti.jets.foodplanner.model.Category;
import eg.gov.iti.jets.foodplanner.search.view.SearchFragment;
import eg.gov.iti.jets.foodplanner.searchResult.view.SearchResultActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private Context context;
    private List<Category> categoriesList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoriesList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        Picasso.get().load(category.getStrCategoryThumb())
                .placeholder(R.drawable.cake)
                .into(holder.categoryImageView);
        holder.categoryNameTv.setText(category.getStrCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra(SearchFragment.CATEGORY_KEY,category.getStrCategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void setData(final List<Category> categoryList) {
        this.categoriesList = categoryList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImageView;
        TextView categoryNameTv;

        public MyViewHolder(@NonNull View categoryView) {
            super(categoryView);
            categoryImageView = categoryView.findViewById(R.id.category_item_imageView);
            categoryNameTv = categoryView.findViewById(R.id.category_item_title_textView);
        }
    }
}
