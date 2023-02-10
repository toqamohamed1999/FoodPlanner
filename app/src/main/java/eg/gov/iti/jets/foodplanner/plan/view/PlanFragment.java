package eg.gov.iti.jets.foodplanner.plan.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.gov.iti.jets.foodplanner.MealDetails.view.Meal_Details_Activity;
import eg.gov.iti.jets.foodplanner.MyDialog;
import eg.gov.iti.jets.foodplanner.MySharedPref;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.authentication.view.LoginActivity;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.PlanMeal;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.plan.presenter.PlanPresenter;
import eg.gov.iti.jets.foodplanner.searchBy.view.OnSearchingActivity;

public class PlanFragment extends Fragment implements PlanViewInterface, WeekDayListener, PlanMealDeleteListener {
    PlanRecycleAdapter planRecycleAdapter;
    PlanMealsAdapter planMealsAdapter;
    PlanPresenter planPresenter;
    private static final String TAG = "PlanFragment";

    private RecyclerView recyclerView, plan_day_recyclerView;
    String selectedDay;
    TextView plan_dayName_text;
    List<PlanMeal> planMealArrayList = new ArrayList<>();

    private String weekDay = "";

    Button addBtn;
    MySharedPref mySharedPref;

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
        setAddPlanEvent();
    }

    public void init(View view) {
        recyclerView = view.findViewById(R.id.plan_weekDays_recyclerView);
        plan_day_recyclerView = view.findViewById(R.id.plan_day_recyclerView);
        plan_dayName_text = view.findViewById(R.id.plan_dayName_text);
        addBtn = view.findViewById(R.id.plan_add_btn);
        planPresenter = new PlanPresenter(this, Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));
        mySharedPref = new MySharedPref(requireContext());
    }

    private void setUpRecyclerView(View view) {
        final LinearLayoutManager daysLayoutManager = new LinearLayoutManager(view.getContext());
        daysLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(daysLayoutManager);
        List<String> allDays = Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        planRecycleAdapter = new PlanRecycleAdapter(view.getContext(), allDays, this);
        recyclerView.setAdapter(planRecycleAdapter);
    }

    private void setUpPlanRecyclerView(View view) {
        final LinearLayoutManager daysLayoutManager = new LinearLayoutManager(view.getContext());
        plan_day_recyclerView.setLayoutManager(daysLayoutManager);
        daysLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        planMealsAdapter = new PlanMealsAdapter(view.getContext(), planMealArrayList, this);
        plan_day_recyclerView.setAdapter(planMealsAdapter);
    }

    private void setAddPlanEvent() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySharedPref.sharedPrefRead().getEmail().equals("not found")) {
                    AlertDialog.Builder builder = MyDialog.myDialog(requireContext());
                    builder.setMessage("Do yo want to login?");
                    builder.setTitle("You are not logged in!");
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // finish();
                        Intent i = new Intent(requireContext(), LoginActivity.class);
                        startActivity(i);
                    });

                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    showAddMenu();
                }
            }
        });
    }

    @Override
    public void getStoredPlanMeals(String day) {
        planPresenter.getStoredPlanMeals(day);
        weekDay = day;
    }

    @Override
    public void getStoredPlanMeals(List<PlanMeal> mealList) {
        if (mealList.size() == 0) {
            Toast.makeText(requireContext(), "No Planed Meals for " + weekDay, Toast.LENGTH_SHORT).show();
        }
        planMealsAdapter.setData(mealList);
        plan_dayName_text.setText(weekDay);
    }

    @Override
    public void removeFavMealClick(PlanMeal planMeal) {
        planPresenter.deletePlanMeal(planMeal);
    }

    public void showAddMenu() {
        PopupMenu popup = new PopupMenu(requireContext(), addBtn);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.add_btn_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(requireContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                if (item.getTitle().equals("Add from search")) {
                    startActivity(new Intent(requireContext(), OnSearchingActivity.class));
                }
                return true;
            }
        });

        popup.show();
    }
}