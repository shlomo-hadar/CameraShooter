package shop_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerashooter.R;

import java.util.ArrayList;

import CustomCard.RecyclerViewAdapter;
import CustomCard.ElementInfo;

public class FragmentGuns extends Fragment {


        @Override @Nullable @SuppressLint("SetTextI18n")
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_guns, container, false);


            ArrayList<ElementInfo> elements = new ArrayList<>();

            elements.add(new ElementInfo("Pistol",500,R.drawable.ic_pistol));
            elements.add(new ElementInfo("Pistol with silencer",700,R.drawable.ic_pistol_silencer));
            elements.add(new ElementInfo("Uzi",1200,R.drawable.ic_uzi));
            elements.add(new ElementInfo("MK-47",2000,R.drawable.ic_mk47));
            elements.add(new ElementInfo("M4",2200,R.drawable.ic_m4));
            elements.add(new ElementInfo("Sniper",2600,R.drawable.ic_sniper));
            elements.add(new ElementInfo("ShotGun",3000,R.drawable.ic_shotgun));

            RecyclerView recyclerView = view.findViewById(R.id.recycle_view_guns);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), elements, (ShopActivity) getActivity());
            recyclerView.setAdapter(adapter);

            return view;

        }
}
