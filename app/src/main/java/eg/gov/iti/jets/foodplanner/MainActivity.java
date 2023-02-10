package eg.gov.iti.jets.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Log.i(TAG, "onCreate: ");
        initUi();
        setupBottomNav();

        if(savedInstanceState!=null) {
            fragmentID = savedInstanceState.getInt("fragmentID");
            navController.navigate(fragmentID);
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
        Log.i(TAG, "setupBottomNav: "+fragmentID);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Log.i(TAG, "setupBottomNav: "+fragmentID);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        fragmentID=navController.getCurrentDestination().getId();
        Log.i(TAG, "setupBottomNav: "+fragmentID);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragmentID",fragmentID);
    }
}