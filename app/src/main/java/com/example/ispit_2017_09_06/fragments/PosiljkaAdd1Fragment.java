package com.example.ispit_2017_09_06.fragments;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ispit_2017_09_06.R;
import com.example.ispit_2017_09_06.helper.MyRunnable;
import com.example.ispit_2017_09_06.helper.Util;
import com.example.ispit_2017_09_06.podaci.KorisnikVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Bundle fragment_arg;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText primaocIme;
    private EditText primaocAdresa;
    private EditText posiljaocIme;
    private EditText posiljaocAdresa;
    private Button promjeniPrimaocaBtn;
    private Button promjeniPosiljaocaBtn;
    private Button daljeBtn;
    private KorisnikVM primaoc;
    private KorisnikVM posiljaoc;


    public PosiljkaAdd1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PosiljkaAdd1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PosiljkaAdd1Fragment newInstance(Bundle bundle) {
        PosiljkaAdd1Fragment fragment = new PosiljkaAdd1Fragment();
        fragment.fragment_arg = bundle;
        fragment.setArguments(fragment.fragment_arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_posiljka_add1, container, false);
        primaocIme = view.findViewById(R.id.primaocImeInput);
        primaocAdresa = view.findViewById(R.id.primaocAdresaInput);
        posiljaocIme = view.findViewById(R.id.posiljaocImeInput);
        posiljaocAdresa = view.findViewById(R.id.posiljaocAdresaInput);
        promjeniPrimaocaBtn = view.findViewById(R.id.promjeniPrimaocaBtn);
        promjeniPosiljaocaBtn = view.findViewById(R.id.promjeniPosiljaocaBtn);
        daljeBtn = view.findViewById(R.id.daljeBtn);
        setInitState(getArguments());

        promjeniPrimaocaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promjeniPrimaoca();
            }
        });
        promjeniPosiljaocaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promjeniPosiljaoca();
            }
        });
        daljeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dalje();
            }
        });

        return view;
    }
    private void setInitState(Bundle bundle) {
        if (bundle == null) return;

        String mPrimaocIme = bundle.getString("primaocIme");
        String mPrimaocAdresa = bundle.getString("primaocAdresa");
        String mPosiljaocIme = bundle.getString("posiljaocIme");
        String mPosiljaocAdresa = bundle.getString("posiljaocAdresa");

        primaocIme.setText(mPrimaocIme);
        primaocAdresa.setText(mPrimaocAdresa);
        posiljaocIme.setText(mPosiljaocIme);
        posiljaocAdresa.setText(mPosiljaocAdresa);
    }

    private void dalje() {
        onSaveInstanceState();
        Util.otvoriFragmentKaoReplace(getActivity(),R.id.mjestoFragment,this, PosiljkaAdd2Fragment.newInstance(fragment_arg, primaoc,posiljaoc));
    }

    private void promjeniPosiljaoca() {
        MyRunnable<KorisnikVM> korisnikVMMyRunnable = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM korisnikVM) {
                posiljaoc = korisnikVM;
                posiljaocIme.setText(posiljaoc.getIme()+" "+posiljaoc.getPrezime());
                posiljaocAdresa.setText(posiljaoc.getAdresaOpstina());
            }
        };
        PretragaDialogFragment pretragaDialogFragment = PretragaDialogFragment.newInstance(korisnikVMMyRunnable);
        Util.otvoriFragmentKaoDijalog(getActivity(),pretragaDialogFragment);
    }

    private void promjeniPrimaoca() {
        MyRunnable<KorisnikVM> korisnikVMMyRunnable = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM korisnikVM) {
                primaoc = korisnikVM;
                primaocIme.setText(primaoc.getIme()+" "+primaoc.getPrezime());
                primaocAdresa.setText(primaoc.getAdresaOpstina());
            }
        };
        PretragaDialogFragment pretragaDialogFragment = PretragaDialogFragment.newInstance(korisnikVMMyRunnable);
        Util.otvoriFragmentKaoDijalog(getActivity(),pretragaDialogFragment);
    }

    public void onSaveInstanceState() {

        fragment_arg.putString("primaocIme", primaocIme.getText().toString());
        fragment_arg.putString("primaocAdresa", primaocAdresa.getText().toString());
        fragment_arg.putString("posiljaocIme", posiljaocIme.getText().toString());
        fragment_arg.putString("posiljaocAdresa", posiljaocAdresa.getText().toString());
        setArguments(fragment_arg);
    }
}
