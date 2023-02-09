package eg.gov.iti.jets.foodplanner;

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

public class PlanFragment extends Fragment {
    PlanRecycleAdapter planRecycleAdapter;

    private RecyclerView recyclerView;

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