package reminderobat.android.kelompok5.papbc.com.reminderobat.model;

/**
 * Created by Firmanda on 12/12/2017.
 */

/*
modified by arifinfrds
* */

public class ReminderModel {

    String id;
    String keterangan, jam;
    String namaObat;

    public ReminderModel(String id, String keterangan, String jam, String namaObat) {
        this.id = id;
        this.keterangan = keterangan;
        this.jam = jam;
        this.namaObat = namaObat;
    }

    public String getId() {
        return id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getJam() {
        return jam;
    }

    public String getNamaObat() {
        return namaObat;
    }
}
