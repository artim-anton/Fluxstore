package com.artimanton.fluxstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.artimanton.fluxstore.DressesActivity;
import com.artimanton.fluxstore.R;

public class PageFragment3 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.
                inflate(R.layout.onboarding_3,container,

                        false);


        TextView textView = rootView.findViewById(R.id.skip);
        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DressesActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

        return rootView;

    }


}
