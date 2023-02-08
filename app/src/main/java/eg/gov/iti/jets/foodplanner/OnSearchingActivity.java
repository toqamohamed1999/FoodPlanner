package eg.gov.iti.jets.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class OnSearchingActivity extends AppCompatActivity {

    private static final String TAG = "OnSearchingActivity";

    private SearchView searchView;

    private RecyclerView recyclerView;

    private ImageButton backImageButton;

    AutoCompleteTextView autoCompleteTextView;

    private ArrayAdapter<String> arrayAdapter;

    private String[] searchByArr = {"By Meal name,By Category,By Country"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_searching);
        getSupportActionBar().hide();

        initUI();
        setUpAutoCompleteTv();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.onSearching_recyclerView);
        searchView = findViewById(R.id.onSearching_searchView);
        backImageButton = findViewById(R.id.onSearching_back_imageBtn);
        autoCompleteTextView = findViewById(R.id.onSearching_autoCompleteTextView);
    }

    private void setUpAutoCompleteTv() {

        ArrayList<String> searchByList = new ArrayList<>();
        searchByList.add("Meal name");
        searchByList.add("Category");
        searchByList.add("Country");

        arrayAdapter = new ArrayAdapter<>(OnSearchingActivity.this, android.R.layout.simple_list_item_1, searchByList);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setText(arrayAdapter.getItem(0),false);



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: "+parent.getItemAtPosition(position).toString());
            }
        });

    }
}