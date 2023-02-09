package eg.gov.iti.jets.foodplanner.search.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.model.Category;
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

        Picasso.with(context).load(category.getStrCategoryThumb())
                .into(holder.categoryImageView, new com.squareup.picasso.Callback() {
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

        ProgressBar progressBar;

        public MyViewHolder(@NonNull View categoryView) {
            super(categoryView);
            categoryImageView = categoryView.findViewById(R.id.category_item_imageView);
            categoryNameTv = categoryView.findViewById(R.id.category_item_title_textView);
            progressBar = categoryView.findViewById(R.id.category_item_progressbar);
        }
    }
}
