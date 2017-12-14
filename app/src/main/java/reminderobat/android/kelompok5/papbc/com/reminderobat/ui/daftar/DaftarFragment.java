package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.daftar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.DaftarModel;


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


        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_daftar);
        adapter = new DaftarRecycleAdapter(daftarModelList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        daftarModelList.add(new DaftarModel("Amoxsilin","3 x 1 sehari setelah makan"));
        daftarModelList.add(new DaftarModel("Komix","3 x 1 sehari sebelum makan"));


        adapter.notifyDataSetChanged();

        return v;
    }

}
