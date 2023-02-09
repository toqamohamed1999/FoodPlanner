package eg.gov.iti.jets.foodplanner.plan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.gov.iti.jets.foodplanner.PlanRecycleAdapter;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.plan.presenter.PlanPresenter;

public class PlanFragment extends Fragment implements PlanViewInterface,WeekDayListener {
    PlanRecycleAdapter planRecycleAdapter;
    PlanMealsAdapter planMealsAdapter;
    PlanPresenter planPresenter;

    private RecyclerView recyclerView, plan_day_recyclerView;
    String selectedDay;
    TextView plan_dayName_text;
    List<PlanMeal> planMealArrayList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setUpRecyclerView(view);
        setUpPlanRecyclerView(view);
    }

    public void init(View view) {
        recyclerView = view.findViewById(R.id.plan_weekDays_recyclerView);
        plan_day_recyclerView = view.findViewById(R.id.plan_day_recyclerView);
        plan_dayName_text = view.findViewById(R.id.plan_dayName_text);
        planPresenter = new PlanPresenter(this, Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
    }

    private void setUpRecyclerView(View view) {
        final LinearLayoutManager daysLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(daysLayoutManager);
        List<String> allDays = Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        planRecycleAdapter = new PlanRecycleAdapter(view.getContext(), allDays,this);
        recyclerView.setAdapter(planRecycleAdapter);
    }
    private void setUpPlanRecyclerView(View view) {
        final LinearLayoutManager daysLayoutManager = new LinearLayoutManager(view.getContext());
        plan_day_recyclerView.setLayoutManager(daysLayoutManager);
       daysLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        planMealsAdapter = new PlanMealsAdapter(view.getContext(), planMealArrayList);
        plan_day_recyclerView.setAdapter(planMealsAdapter);
    }

    @Override
    public void getStoredPlanMeals(String day) {
        planPresenter.getStoredPlanMeals(day);
    }

    @Override
    public void getStoredPlanMeals(List<PlanMeal> mealList) {
        planMealsAdapter.setData(mealList);
    }
}