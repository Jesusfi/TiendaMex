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
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.R;

/**
 * Created by Jesus Izquierdo on 4/19/2017.
 */

public class SimpleDialogFragment extends DialogFragment {
    EditText email, password, name;

    public  interface OnCompleteListener {
         void onComplete(String email, String password, String name);
    }

    private OnCompleteListener onCompleteListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.onCompleteListener = (OnCompleteListener) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_dialog_layout, null);

        name = (EditText) v.findViewById(R.id.textviewNameSignUp);
        email = (EditText) v.findViewById(R.id.editTextEmailS);
        password = (EditText) v.findViewById(R.id.editTextPasswordS);


        builder.setTitle("Please Enter your information")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String emailString = email.getText().toString().trim();
                        String passwordString = password.getText().toString().trim();
                        String nameString = name.getText().toString().trim();

                        onCompleteListener.onComplete(emailString, passwordString, nameString);


                    }
                })
                .setView(v);

        return builder.create();


    }
}
