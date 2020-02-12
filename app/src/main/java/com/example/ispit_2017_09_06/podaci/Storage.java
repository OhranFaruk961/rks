package com.example.ispit_2017_09_06.podaci;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adil on 18/06/2016.
 */
public class Storage {


    private static List<KorisnikVM> korisnici;
    public static List<KorisnikVM> getKorisniciAll()
    {
        if (korisnici == null)
        {
            korisnici = new ArrayList<>();
            korisnici.add(new KorisnikVM("E.", "Junuz", "sjeverni logor - Mostar"));
            korisnici.add(new KorisnikVM("A.", "Joldic", "sjeverni logor - Mostar"));
            korisnici.add(new KorisnikVM("E.", "Sudic", "sjeverni logor - Mostar"));
            korisnici.add(new KorisnikVM("M.", "Herceg", "sjeverni logor - Mostar"));
        }
        return korisnici;
    }

    public static List<KorisnikVM> getKorisniciByName(String name)
    {
        List<KorisnikVM> rezultat = new ArrayList<>();
        for (KorisnikVM x : getKorisniciAll()) {
            if ((x.getIme() + " " + x.getPrezime()).toLowerCase().startsWith(name.toLowerCase()) || (x.getPrezime() + " " + x.getIme()).toLowerCase().startsWith(name.toLowerCase()))
            {
                rezultat.add(x);
            }
        }
        return rezultat;
    }

    private static List<PosiljkaVM> posilje;
    public static List<PosiljkaVM> getPosiljkeAll()
    {
        if (posilje == null)
        {
            posilje = new ArrayList<>();
            posilje.add(new PosiljkaVM(getKorisniciAll().get(0), getKorisniciAll().get(1), 15, false, null, ""));
            posilje.add(new PosiljkaVM(getKorisniciAll().get(2), getKorisniciAll().get(3), 15, true, 1000.0, "Lomljivi. Oprez."));
            posilje.add(new PosiljkaVM(getKorisniciAll().get(3), getKorisniciAll().get(0), 105, false, null, "nazvati na mobitel"));
            posilje.add(new PosiljkaVM(getKorisniciAll().get(0), getKorisniciAll().get(1), 5, true, 1000.0, "lomljivo"));
        }
        return posilje;
    }
}
