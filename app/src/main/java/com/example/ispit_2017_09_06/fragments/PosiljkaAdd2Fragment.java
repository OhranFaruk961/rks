package com.example.ispit_2017_09_06.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ispit_2017_09_06.R;
import com.example.ispit_2017_09_06.helper.Util;
import com.example.ispit_2017_09_06.podaci.KorisnikVM;
import com.example.ispit_2017_09_06.podaci.PosiljkaVM;
import com.example.ispit_2017_09_06.podaci.Storage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private KorisnikVM primaoc;
    private KorisnikVM posiljaoc;
    private EditText masa;
    private EditText napomena;
    private EditText iznos;
    private Switch switchic;
    private Button zavrsiBtn;


    public PosiljkaAdd2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PosiljkaAdd2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PosiljkaAdd2Fragment newInstance(KorisnikVM primaoc, KorisnikVM posiljaoc) {
        PosiljkaAdd2Fragment fragment = new PosiljkaAdd2Fragment();
        Bundle args = new Bundle();
        args.putSerializable("tri",primaoc);
        args.putSerializable("cetiri",posiljaoc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            this.primaoc =(KorisnikVM) getArguments().getSerializable("tri");
            this.posiljaoc =(KorisnikVM) getArguments().getSerializable("cetiri");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_posiljka_add2, container, false);
        masa = view.findViewById(R.id.masaInput);
        napomena = view.findViewById(R.id.napomenaInput);
        iznos = view.findViewById(R.id.iznosInput);
        switchic = view.findViewById(R.id.switchic);
        zavrsiBtn = view.findViewById(R.id.zavrsiBtn);

        zavrsiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zavrsi();
            }
        });

        return view;
    }

    private void zavrsi() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("dodavanje");
        alert.setMessage("Da li ste sigurni");

        alert.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PosiljkaVM posiljkaVM = new PosiljkaVM();
                posiljkaVM.primaoc = primaoc;
                posiljkaVM.posljiaoc = posiljaoc;
                posiljkaVM.napomena = (napomena.getText().toString());
                posiljkaVM.masa = Float.parseFloat (masa.getText().toString());
                posiljkaVM.iznosNaplate = Double.parseDouble (iznos.getText().toString());
                posiljkaVM.naplatiPouzecem = switchic.isSelected();
                int broj =    Storage.getPosiljkeAll().size();
                Storage.getPosiljkeAll().add(posiljkaVM);
                broj =  Storage.getPosiljkeAll().size();
                Toast.makeText(getActivity(),"Posiljka uspjesno snimljena",Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Util.otvoriFragmentKaoReplace(getActivity(),R.id.mjestoFragment,PosiljkaAdd1Fragment.newInstance());
            }
        });

        alert.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Posiljka nije  snimljena",Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Util.otvoriFragmentKaoReplace(getActivity(),R.id.mjestoFragment,PosiljkaAdd2Fragment.newInstance(primaoc,posiljaoc));
            }
        });
        alert.show();
    }
}
