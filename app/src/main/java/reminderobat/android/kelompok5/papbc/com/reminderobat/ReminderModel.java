package reminderobat.android.kelompok5.papbc.com.reminderobat;

/**
 * Created by Firmanda on 12/12/2017.
 */

public class ReminderModel {
    public ReminderModel(String keterangan, String jam) {
        this.keterangan = keterangan;
        this.jam = jam;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getJam() {
        return jam;
    }
    String keterangan,jam;
}
