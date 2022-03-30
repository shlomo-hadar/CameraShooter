package shop_fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.camerashooter.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class ShopActivity extends AppCompatActivity {

    private TextView tv_money, shop_type;
    private int money;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

//        Log.v("AVI", this.getClass().getName()+"");
        tv_money = findViewById(R.id.tv_money);
        shop_type = findViewById(R.id.tv_shop_type);
        shop_type.setText("Guns");
        money = 990; //get from firebase
        //TODO show real money
        tv_money.setText(money+"");

        BottomNavigationView bottomNav = findViewById(R.id.shop_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentGuns()).commit();
        }

    }

    public boolean setMoney(int money){
        if (this.money + money < 0)
            return false;
        this.money += money;
        tv_money.setText(this.money+"");
        return true;
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment selectedFragment = new FragmentGuns();
            switch (item.getItemId()) {

                case R.id.guns:
                    shop_type.setText("Guns");
                    selectedFragment = new FragmentGuns();

                    break;

                case R.id.scopes:
                    shop_type.setText("Scopes");
                    selectedFragment = new FragmentScopes();
                    break;

                case R.id.magazines:
                    shop_type.setText("Magazines");
                    selectedFragment = new FragmentMagazines();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };
}
