package com.example.jesusizquierdo.tiendamex.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jesusizquierdo.tiendamex.R;

/**
 * Created by Jesus Izquierdo on 4/26/2017.
 */

public class QuickOrderDialogFragment extends DialogFragment {

    TextView foodName, quantity;
    ImageView foodImage;

    public static interface OnCompleteListenerQM {
        public void onComplete(String email, String password, String name);
    }

    private OnCompleteListenerQM onCompleteListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.onCompleteListener = (OnCompleteListenerQM) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.quickorder_custom_dialog, null);

        foodName = (TextView) v.findViewById(R.id.textViewQOfoodName);
        foodImage = (ImageView) v.findViewById(R.id.imageViewQOfood);
        quantity = (TextView) v.findViewById(R.id.quantity_quick_order_textView);

        Bundle bundle = getArguments();
        String nameOfFood;
        int pic = bundle.getInt("key");
        nameOfFood = bundle.getString("link");


        foodName.setText(nameOfFood);
        foodImage.setImageResource(pic);
        quantity.setText("Quantity : " + bundle.getInt("key2"));

        builder.setTitle("Quick Order")
                .setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setView(v);

        return builder.create();


    }


}
