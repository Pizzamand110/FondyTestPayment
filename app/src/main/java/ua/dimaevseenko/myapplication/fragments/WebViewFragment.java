package ua.dimaevseenko.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cloudipsp.android.Card;
import com.cloudipsp.android.Cloudipsp;
import com.cloudipsp.android.CloudipspWebView;
import com.cloudipsp.android.Order;
import com.cloudipsp.android.Receipt;

import org.jetbrains.annotations.NotNull;

import ua.dimaevseenko.myapplication.R;

public class WebViewFragment extends Fragment implements Cloudipsp.PayCallback {

    CloudipspWebView webView;

    Order order;
    Card card;

    Cloudipsp cloudipsp;

    public WebViewFragment(Order order, Card card){
        this.order = order;
        this.card = card;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        webView = view.findViewById(R.id.web_view);

        cloudipsp = new Cloudipsp(1479173, webView);
        pay();

        return view;
    }

    private void pay(){
        try {
            cloudipsp.pay(card, order, this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaidProcessed(Receipt receipt) {
        ((CallbackResult) getActivity()).onCallbackResult(receipt.status.toString());

       getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onPaidFailure(Cloudipsp.Exception e) {
        e.printStackTrace();
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "WebViewFragment";
    }

    public interface CallbackResult{
        void onCallbackResult(String result);
    }
}
