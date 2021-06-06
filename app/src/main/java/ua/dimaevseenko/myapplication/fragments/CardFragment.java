package ua.dimaevseenko.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cloudipsp.android.Card;
import com.cloudipsp.android.CardInputLayout;
import com.cloudipsp.android.Currency;
import com.cloudipsp.android.Order;

import org.jetbrains.annotations.NotNull;

import ua.dimaevseenko.myapplication.R;

public class CardFragment extends Fragment implements View.OnClickListener{

    private EditText editCard;
    private EditText editExpYy;
    private EditText editExpMm;
    private EditText editCvv;
    private CardInputLayout cardLayout;
    private Button pay;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        editCard = view.findViewById(R.id.edit_card_number);
        editExpYy = view.findViewById(R.id.edit_yy);
        editExpMm = view.findViewById(R.id.edit_mm);
        editCvv = view.findViewById(R.id.edit_cvv);
        // ^^^ these fields used only as example for Cloudipsp.setStrictUiBlocking(false);
        cardLayout = view.findViewById(R.id.card_layout);
        cardLayout.setCardNumberFormatting(true);

        pay = view.findViewById(R.id.buttonPay);
        pay.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPay : pay();
            break;
        }
    }

    private void pay(){
        Order order = new Order(10000, Currency.UAH, "vb_" + System.currentTimeMillis(), "PayTest");
        order.setLang(Order.Lang.ru);

        Card card = cardLayout.confirm(new CardInputLayout.ConfirmationErrorHandler() {
            @Override
            public void onCardInputErrorClear(CardInputLayout view, EditText editText) {

            }

            @Override
            public void onCardInputErrorCatched(CardInputLayout view, EditText editText, String error) {

            }
        });

        OnPayListener onPayListener = (OnPayListener) getContext();
        onPayListener.OnPay(order, card);
    }

    public interface OnPayListener{
        void OnPay(Order order, Card card);
    }
}
