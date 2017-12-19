package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.toturial;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.home.FragmentActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class Toturial2 extends Fragment {

    ImageButton mPlay;

    public Toturial2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_toturial2, container, false);
        mPlay = v.findViewById(R.id.btn_Play);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello ", Toast.LENGTH_SHORT).show();
                navigateToFragmentActivity();
            }
        });
        return v;
    }


    private void navigateToFragmentActivity(){
        Intent intent = new Intent(getContext(), FragmentActivity.class);
        getContext().startActivity(intent);
    }

}
