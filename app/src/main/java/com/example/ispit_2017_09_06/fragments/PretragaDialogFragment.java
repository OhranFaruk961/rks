package com.example.ispit_2017_09_06.fragments;


import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ispit_2017_09_06.R;
import com.example.ispit_2017_09_06.helper.MyRunnable;
import com.example.ispit_2017_09_06.helper.Util;
import com.example.ispit_2017_09_06.podaci.KorisnikVM;
import com.example.ispit_2017_09_06.podaci.Storage;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PretragaDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PretragaDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyRunnable<KorisnikVM> callback;
    private EditText pretraga;
    private ListView lista;
    private Button noviKorisnikBtn;
    private Button pretragaBtn;
    private TextView ime;
    private TextView adresa;


    public PretragaDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PretragaDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PretragaDialogFragment newInstance(MyRunnable<KorisnikVM> korisnikVMMyRunnable) {
        PretragaDialogFragment fragment = new PretragaDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("jedan",korisnikVMMyRunnable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setStyle(STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            callback = (MyRunnable<KorisnikVM>) getArguments().getSerializable("jedan");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pretraga_dialog, container, false);

        pretraga = view.findViewById(R.id.pretragaInput);
        lista = view.findViewById(R.id.pretragaLista);
        noviKorisnikBtn = view.findViewById(R.id.noviKorisnikBtn);
        pretragaBtn = view.findViewById(R.id.pretragaBtn);

        pretragaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popunipodatke(pretraga.getText().toString());
            }
        });
        noviKorisnikBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novikorisnik();
            }
        });

        return view;
    }

    private void novikorisnik() {
        MyRunnable<KorisnikVM> korisnikVMMyRunnable = new MyRunnable<KorisnikVM>() {
            @Override
            public void run(KorisnikVM korisnikVM) {
                Storage.getKorisniciAll().add(korisnikVM);
                popunipodatke(pretraga.getText().toString());
            }
        };
        NoviKorisnikDialogFragment noviKorisnikDialogFragment = NoviKorisnikDialogFragment.newInstance(korisnikVMMyRunnable);
        Util.otvoriFragmentKaoDijalog(getActivity(),noviKorisnikDialogFragment);
    }

    private void popunipodatke(String kveri) {
        final List<KorisnikVM> korisnici = Storage.getKorisniciByName(kveri);
        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return korisnici.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater =(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.korisnikitem,parent,false);
                ime = view.findViewById(R.id.prvi);
                adresa = view.findViewById(R.id.drugi);
                ime.setText(korisnici.get(position).getIme()+" "+korisnici.get(position).getPrezime());
                adresa.setText(korisnici.get(position).getAdresaOpstina());
                return view;
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                callback.run(korisnici.get(position));
                getDialog().dismiss();
                return false;
            }
        });

    }

}
