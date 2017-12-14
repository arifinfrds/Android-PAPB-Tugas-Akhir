package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.reminder;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.tambah_reminder.TambahReminderActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {

    private List<ReminderModel> reminderModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReminderRecycleAdapter adapter;
    private FloatingActionButton fab;

    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reminder, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_reminder);
        adapter = new ReminderRecycleAdapter(reminderModelList, getContext(), getResources());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        reminderModelList.add(new ReminderModel("0", "3 x 1 Sehari", "09.00", "Obat 1"));
        reminderModelList.add(new ReminderModel("1", "2 x 1 sehari ", "19.00", "obat 2"));


        adapter.notifyDataSetChanged();


        fab = v.findViewById(R.id.fab_reminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Tambah ", Toast.LENGTH_SHORT).show();
                navigateToTambahReminderActivity();
            }
        });
        return v;
    }

    private void navigateToTambahReminderActivity() {
        Intent intent = new Intent(getContext(), TambahReminderActivity.class);
        getContext().startActivity(intent);
    }

}
