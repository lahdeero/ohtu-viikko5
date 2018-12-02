
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        else if (kasvatuskoko < 0) throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D

        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) return false;
        ljono[alkioidenLkm++] = luku;
        if (alkioidenLkm % ljono.length == 0) {
            int[] taulukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(ljono, taulukko);
            ljono = taulukko;
        }
        return true;
    }
    
    public boolean kuuluu(int luku) {
        int[] apu = Arrays.copyOfRange(ljono, 0, alkioidenLkm);
        return Arrays.stream(apu).anyMatch(x -> x == luku);
    }
    
    private int luvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) return i;
        }
        return -1;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) return false;
        int kohta = luvunIndeksi(luku);
        for (int j = kohta; j < alkioidenLkm - 1; j++) ljono[j] = ljono[j+1];
        alkioidenLkm--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += ljono[i];
            if (i != alkioidenLkm - 1) tuotos += ", ";
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) x.lisaa(aTaulu[i]);
        for (int i = 0; i < bTaulu.length; i++) x.lisaa(bTaulu[i]);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) y.lisaa(bTaulu[j]);
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) z.lisaa(aTaulu[i]);
        for (int i = 0; i < bTaulu.length; i++) z.poista(bTaulu[i]);
        return z;
    }
}