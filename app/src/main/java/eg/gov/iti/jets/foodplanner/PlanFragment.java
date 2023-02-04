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
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_notes);
        final LinearLayoutManager dayslayoutManager  = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(dayslayoutManager);

        List<String> alldays = Arrays.asList("Monday", "Tuesday", "wednesday", "Thursday", "Friday" , "Saturday" , "Sunday");
        planRecycleAdapter = new PlanRecycleAdapter(view.getContext() , alldays);
        recyclerView.setAdapter(planRecycleAdapter);
    }
}