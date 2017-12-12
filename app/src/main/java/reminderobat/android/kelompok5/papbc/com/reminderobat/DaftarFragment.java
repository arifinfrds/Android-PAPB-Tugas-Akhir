package reminderobat.android.kelompok5.papbc.com.reminderobat;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarFragment extends Fragment {

    private List<DaftarModel> daftarModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DaftarRecycleAdapter adapter;

    public DaftarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_daftar, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_admin);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_daftar);
        adapter = new DaftarRecycleAdapter(daftarModelList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        daftarModelList.add(new DaftarModel("Obat 1","Keterangan 1"));
        daftarModelList.add(new DaftarModel("Obat 1","Keterangan 2"));


        adapter.notifyDataSetChanged();
       return v;
    }

}
