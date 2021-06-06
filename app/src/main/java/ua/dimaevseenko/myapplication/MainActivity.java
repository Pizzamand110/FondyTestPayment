package ua.dimaevseenko.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cloudipsp.android.Card;
import com.cloudipsp.android.Order;

import java.util.HashMap;
import java.util.List;

import ua.dimaevseenko.myapplication.fragments.CardFragment;
import ua.dimaevseenko.myapplication.fragments.WebViewFragment;

public class MainActivity extends AppCompatActivity implements CardFragment.OnPayListener, WebViewFragment.CallbackResult {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, new CardFragment()).commit();

    }

    @Override
    public void OnPay(Order order, Card card) {
        System.out.println("OnPay");

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, new WebViewFragment(order, card)).commit();

    }

    @Override
    public void onBackPressed() {
        List<Fragment> list = getSupportFragmentManager().getFragments();

        for (Fragment f: list){
            if(f.toString()=="WebViewFragment"){
                getSupportFragmentManager().beginTransaction().remove(f).commit();
            }
        }

    }

    @Override
    public void onCallbackResult(String result) {
        System.out.println(result);
    }
}
