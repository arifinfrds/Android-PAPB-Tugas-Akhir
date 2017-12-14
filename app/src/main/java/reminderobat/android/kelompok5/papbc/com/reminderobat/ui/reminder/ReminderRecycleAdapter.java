package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.reminder;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel;

/**
 * Created by Firmanda on 12/12/2017.
 */

public class ReminderRecycleAdapter extends RecyclerView.Adapter<ReminderRecycleAdapter.ViewHolder> {
    Context context;
    List<reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel> ReminderModel;
    Resources resources;

    public ReminderRecycleAdapter(List<ReminderModel> ReminderModel, Context context, Resources resources) {
        this.ReminderModel = ReminderModel;
        this.context = context;
        this.resources = resources;
    }

    @Override
    public ReminderRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_reminder, viewGroup, false);
        return new ReminderRecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminderRecycleAdapter.ViewHolder viewHolder, final int i) {
        ReminderModel ReminderModel1 = ReminderModel.get(i);
        viewHolder.row_item_jam.setText(ReminderModel1.getJam());
        viewHolder.row_item_keterangan.setText(ReminderModel1.getKeterangan());
        String clock = ReminderModel1.getJam();
        String[] parts = clock.split("\\.");
        String jam = parts[0];
        String menit = parts[1];

        int id=resources.getIdentifier("reminderobat.android.kelompok5.papbc.com.reminderobat:drawable/sun",
                null, null);
        int jams = Integer.parseInt(jam);
        if(jams<18){
            id = resources.getIdentifier("reminderobat.android.kelompok5.papbc.com.reminderobat:drawable/sun",
                    null, null);
        }else if(jams>=18){
            id = resources.getIdentifier("reminderobat.android.kelompok5.papbc.com.reminderobat:drawable/plus",
                    null, null);
        }
        viewHolder.row_item_gambar.setImageResource(id);

        viewHolder.row_item_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderModel.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, ReminderModel.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ReminderModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView row_item_jam;
        TextView row_item_keterangan;
        ImageView row_item_gambar;
        ImageView row_item_hapus;

        public ViewHolder(View view) {
            super(view);

            row_item_jam = (TextView) view.findViewById(R.id.textreminderJam);
            row_item_keterangan = (TextView) view.findViewById(R.id.textreminderKet);
            row_item_gambar = (ImageView) view.findViewById(R.id.imgremindersunnight);
            row_item_hapus = (ImageView) view.findViewById(R.id.imgreminderhapus);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
