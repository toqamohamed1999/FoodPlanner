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

import java.util.Arrays;
import java.util.List;

import eg.gov.iti.jets.foodplanner.PlanRecycleAdapter;
import eg.gov.iti.jets.foodplanner.R;
import eg.gov.iti.jets.foodplanner.database.LocalSource;
import eg.gov.iti.jets.foodplanner.model.Repo;
import eg.gov.iti.jets.foodplanner.network.RemoteSource;
import eg.gov.iti.jets.foodplanner.plan.presenter.PlanPresenter;

public class PlanFragment extends Fragment implements PlanViewInterface{
    PlanRecycleAdapter planRecycleAdapter;
    PlanPresenter planPresenter;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planPresenter=new PlanPresenter(this, Repo.getInstance(requireContext(), LocalSource.getLocalSource(requireContext()), RemoteSource.getRemoteSource()));

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
        recyclerView = view.findViewById(R.id.plan_weekDays_recyclerView);

        setUpRecyclerView(view);

    }

    private void setUpRecyclerView(View view){
        final LinearLayoutManager daysLayoutManager  = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(daysLayoutManager);
        List<String> allDays = Arrays.asList("Saturday", "Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        planRecycleAdapter = new PlanRecycleAdapter(view.getContext() , allDays);
        recyclerView.setAdapter(planRecycleAdapter);
    }
}