package eg.gov.iti.jets.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.model.Country;
import eg.gov.iti.jets.foodplanner.searchResult.view.SearchResultActivity;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private Context context;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> CountryList) {
        this.context = context;
        this.countryList = CountryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Country country = countryList.get(position);

        holder.countryNameTv.setText(country.getStrArea());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra(SearchFragment.COUNTRY_KEY,country.getStrArea());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void setData(final List<Country> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
     //   ImageView categoryImageView;
        TextView countryNameTv;

        public MyViewHolder(@NonNull View countryView) {
            super(countryView);
         //   categoryImageView = categoryView.findViewById(R.id.category_item_imageView);
            countryNameTv = countryView.findViewById(R.id.country_item_title_textView);
        }
    }
}
