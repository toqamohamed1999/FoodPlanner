package eg.gov.iti.jets.foodplanner.network.MyFireBase;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodplanner.model.Meal;
import eg.gov.iti.jets.foodplanner.network.FirebaseInterface;
import eg.gov.iti.jets.foodplanner.network.NetworkProfileDelegate;

public class MyFirebase {

    private static final String TAG = "MyFirebase";

    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;

    private Meal meal;

    private FirebaseInterface firebaseInterface;

    ArrayList<Meal> mealsList = new ArrayList<>();

    public MyFirebase(FirebaseInterface firebaseInterface) {
        this.firebaseInterface = firebaseInterface;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid());
    }

    public  void getMealsFromFirebase(NetworkProfileDelegate networkProfileDelegate){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    meal  = item.getValue(Meal.class);
                    mealsList.add(meal);
                    Log.i(TAG, "onDataChange: " + meal.toString());
                }
                firebaseInterface.getMealsFromFirebase(mealsList);
                networkProfileDelegate.onResultGetMealsFromFirebase("success backup");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                networkProfileDelegate.onResultGetMealsFromFirebase("failed backup");
                Log.i(TAG, "onCancelled: getMealsFromFirebase "+error.getMessage());
            }
        });
    }

    public void storeMealsToFirebase(NetworkProfileDelegate networkProfileDelegate,List<Meal> mealsList){

        databaseReference.setValue(mealsList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                networkProfileDelegate.onResultStoreMealsToFirebase("success store backup");
                Log.i(TAG, "onComplete: storeMealsToFirebase");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure storeMealsToFirebase: " + e.getMessage());
                networkProfileDelegate.onResultStoreMealsToFirebase("failed store backup");

            }
        });
    }
}
