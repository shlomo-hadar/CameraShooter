package shop_fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerashooter.R;

import java.util.ArrayList;
import java.util.Objects;

import CustomCard.ElementInfo;
import CustomCard.RecyclerViewAdapter;

public class FragmentMagazines extends Fragment {

    ShopActivity parent;

    @Override  @Nullable  @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_magazines, container, false);
        parent = (ShopActivity) getActivity();

        ArrayList<ElementInfo> elements = new ArrayList<>();
        elements.add(new ElementInfo("Pistol", 100, R.drawable.ic_magazine_24));
        elements.add(new ElementInfo("Pistol2", 200, R.drawable.ic_magazine_24));

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_magazines);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), elements, (ShopActivity) getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setMoney(int ammount){
        parent.setMoney(ammount);
    }
}
