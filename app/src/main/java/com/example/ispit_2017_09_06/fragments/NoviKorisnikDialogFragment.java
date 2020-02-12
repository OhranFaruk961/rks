package com.example.ispit_2017_09_06.fragments;


import android.app.DialogFragment;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ispit_2017_09_06.R;
import com.example.ispit_2017_09_06.helper.MyRunnable;
import com.example.ispit_2017_09_06.podaci.KorisnikVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoviKorisnikDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoviKorisnikDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyRunnable<KorisnikVM> callback;
    private EditText ime;
    private EditText prezime;
    private EditText adresa;


    public NoviKorisnikDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment NoviKorisnikDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoviKorisnikDialogFragment newInstance(MyRunnable<KorisnikVM> korisnikVMMyRunnable) {
        NoviKorisnikDialogFragment fragment = new NoviKorisnikDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("dva",korisnikVMMyRunnable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            callback = (MyRunnable<KorisnikVM>) getArguments().getSerializable("dva");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_novi_korisnik_dialog, container, false);
        ime = view.findViewById(R.id.korisnikImeInput);
        prezime = view.findViewById(R.id.korisnikPrezimeInput);
        adresa = view.findViewById(R.id.korisnikAdresaInput);
        Button noviKorisnikBtn = view.findViewById(R.id.snimiKorisnikaBtn);
        noviKorisnikBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.run(new KorisnikVM(ime.getText().toString(), prezime.getText().toString(), adresa.getText().toString()));
                getDialog().dismiss();
            }
        });
        return view;
    }

}
