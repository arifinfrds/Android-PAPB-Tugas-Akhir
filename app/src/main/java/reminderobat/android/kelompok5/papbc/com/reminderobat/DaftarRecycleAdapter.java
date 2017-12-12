package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firmanda on 12/12/2017.
 */

public class DaftarRecycleAdapter extends RecyclerView.Adapter<DaftarRecycleAdapter.ViewHolder> {
    Context context;
    List<DaftarModel> DaftarModel;

    public DaftarRecycleAdapter(List<DaftarModel> DaftarModel, Context context) {
        this.DaftarModel = DaftarModel;
        this.context = context;
    }

    @Override
    public DaftarRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_daftar, viewGroup, false);
        return new DaftarRecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DaftarRecycleAdapter.ViewHolder viewHolder, int i) {
        DaftarModel DaftarModel1 = DaftarModel.get(i);
        viewHolder.row_item_namaobat.setText(DaftarModel1.getNamaObat());
        viewHolder.row_item_keterangan.setText(DaftarModel1.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return DaftarModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView row_item_namaobat;
        TextView row_item_keterangan;

        public ViewHolder(View view) {
            super(view);

            row_item_namaobat = (TextView) view.findViewById(R.id.textNamaObat);
            row_item_keterangan = (TextView) view.findViewById(R.id.textKeterangan);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
