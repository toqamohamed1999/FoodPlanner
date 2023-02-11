package eg.gov.iti.jets.foodplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Observable;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private static final String TAG = "MainActivity";
    SwipeRefreshLayout swipeRefreshLayout;
    NavController navController;
    int fragmentID;

    String fragmentLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initUi();
        setupBottomNav();

        Log.i(TAG, "onCreate: lifeCycle");

        if(savedInstanceState!=null) {
            fragmentID = savedInstanceState.getInt("fragmentID");
            fragmentLabel = savedInstanceState.getString("fragmentLabel");
            Log.i(TAG, "onCreate: "+fragmentLabel);
            navController.navigate(fragmentID);
            navController.navigate(fragmentLabel);
        }
    }
    void initUi(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        swipeRefreshLayout=findViewById(R.id.swipRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    void setupBottomNav(){

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        fragmentID=navController.getCurrentDestination().getId();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.e(TAG, "onDestinationChanged: "+destination.getLabel());
                fragmentLabel = destination.getLabel().toString();
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragmentID",fragmentID);
        outState.putString("fragmentLabel",fragmentLabel);
        Log.i(TAG, "onSaveInstanceState: lifeCycle");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: lifeCycle ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: lifeCycle ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: lifeCycle ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: lifeCycle ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: lifeCycle ");
    }
}