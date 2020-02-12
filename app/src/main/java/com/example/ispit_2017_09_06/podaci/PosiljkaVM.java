package com.example.ispit_2017_09_06.podaci;

/**
 * Created by Adil on 02/02/2017.
 */

public class PosiljkaVM  {
	private static int brojac=0;
    public KorisnikVM posljiaoc;
    public KorisnikVM primaoc;
    public float masa;
    public boolean naplatiPouzecem;
    public Double iznosNaplate;

	public int brojPosiljke;
    public String napomena;

    public PosiljkaVM(KorisnikVM posljiaoc, KorisnikVM primaoc, float masa, boolean naplatiPouzecem, Double iznosNaplate, String napomena) {
        this.posljiaoc = posljiaoc;
        this.primaoc = primaoc;
        this.masa = masa;
        this.naplatiPouzecem = naplatiPouzecem;
        this.iznosNaplate = iznosNaplate;
        this.brojPosiljke = brojac++;
        this.napomena = napomena;
    }

    public PosiljkaVM()
    {

    }
}
