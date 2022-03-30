package shop_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerashooter.R;

import java.util.ArrayList;

import CustomCard.ElementInfo;
import CustomCard.RecyclerViewAdapter;

public class FragmentScopes extends Fragment {

    @Override
    @Nullable
    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scopes, container, false);

        ArrayList<ElementInfo> elements = new ArrayList<>();
        elements.add(new ElementInfo("Default Scope", 100, R.drawable.ic_default_scope_24));
        elements.add(new ElementInfo("Rectangle Scope", 300, R.drawable.ic_rectangle_scope_24));
        elements.add(new ElementInfo("Circle Scope", 300, R.drawable.ic_circle_scope_24));
        elements.add(new ElementInfo("Large Circle Scope", 700, R.drawable.ic_large_circle_scope_with_lines_24));
        elements.add(new ElementInfo("Sniper Scope", 1000, R.drawable.ic_sniper_scope_24));

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_scopes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), elements, (ShopActivity) getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }
}

