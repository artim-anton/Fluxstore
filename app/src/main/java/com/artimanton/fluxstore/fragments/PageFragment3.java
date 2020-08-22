package com.artimanton.fluxstore.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.artimanton.fluxstore.ListingActivity;
import com.artimanton.fluxstore.LoginActivity;
import com.artimanton.fluxstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class PageFragment3 extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.
                inflate(R.layout.onboarding_3,container,

                        false);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }

            }
        };


        TextView textView = rootView.findViewById(R.id.skip);
        TextView tvSignIn = rootView.findViewById(R.id.sign_in);
        TextView tvSignUp = rootView.findViewById(R.id.sign_up);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Sign in");
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Sign up");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openListingActivity();
                }
            });

        return rootView;

    }

    private void openListingActivity() {
        Intent intent = new Intent(getContext(), ListingActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void showDialog(final String textButton) {
        final AlertDialog.Builder alert;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            alert = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
        }else {
            alert = new AlertDialog.Builder(getContext());
        }
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.sign_in_up,null);

        final TextView username = view.findViewById(R.id.et_email);
        final TextView userpassword = view.findViewById(R.id.et_password);
        Button btn_login = view.findViewById(R.id.btn_sign);
        Button btn_close = view.findViewById(R.id.btn_close);

        btn_login.setText(textButton);

        alert.setView(view);

        //alert.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString();
                String user_password = userpassword.getText().toString();
                if (!user_name.isEmpty() || !user_password.isEmpty()){
                    switch (textButton){
                        case("Sign in"):
                            registration(user_name,user_password);
                        break;
                        case("Sign up"):
                            signin(user_name,user_password);
                        break;
                }
                } else {Toast.makeText(getContext(), "Email or password must not be empty",Toast.LENGTH_LONG).show();}
            }
        });

        final AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getContext(), "Authorization successful", Toast.LENGTH_SHORT).show();
                    openListingActivity();
                }else
                    Toast.makeText(getContext(), "Authorization failed", Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    openListingActivity();
                }
                else
                    Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();

            }
        });
    }



}
