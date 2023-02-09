package eg.gov.iti.jets.foodplanner.favorites.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.favorites.presenter.FavPresenter;
import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritesFragment extends Fragment implements FavViewInterface,FavListenerInterface{

    private RecyclerView recyclerView;

    private ArrayList<Meal> mealsList = new ArrayList<>();

    private FavAdapter favAdapter ;

    private FavPresenter favPresenter;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fav_recyclerView);

        setupRecyclerView();
        setUpPresenter();

    }
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        favAdapter = new FavAdapter(requireContext(), mealsList,this);
        recyclerView.setAdapter(favAdapter);
    }

    void setUpPresenter() {
        favPresenter = new FavPresenter(this, Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
        favPresenter.getStoredFavMeals();


//        allFavPresenter.getStoreFavProducts().observe(FavoritesActivity.this, new Observer<List<Product>>() {
//            @Override
//            public void onChanged(List<Product> productList) {
//                favAdapter.setData(productList);
//            }
//        });

    }




    @Override
    public void getStoredFavMeals(List<Meal> mealsList) {
        favAdapter.setData(mealsList);

    }

    @Override
    public void removeMealFromFav(Meal meal) {
        favPresenter.removeMealFromFav(meal);
        Toast.makeText(requireContext(), "Meal removed from favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeFavMealClick(Meal meal) {removeMealFromFav(meal);}
}
