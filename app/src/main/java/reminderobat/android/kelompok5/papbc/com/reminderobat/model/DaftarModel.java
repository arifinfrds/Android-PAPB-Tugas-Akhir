package reminderobat.android.kelompok5.papbc.com.reminderobat.model;

/**
 * Created by Firmanda on 12/12/2017.
 */

public class DaftarModel {
    public DaftarModel(String namaObat, String keterangan) {
        this.namaObat = namaObat;
        this.keterangan = keterangan;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    String namaObat,keterangan;
}
