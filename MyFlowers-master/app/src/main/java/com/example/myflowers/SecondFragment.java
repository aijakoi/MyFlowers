package com.example.myflowers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowers.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ArrayList<Kukka> kukatLista;
    private RecyclerView recyclerView;
    private recyclerAdapter.RecyclerViewClickListener listener;
    private SearchView searchView;
    private recyclerAdapter adapter;
    private ArrayList<Kukka> filteredList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setAdapter() {
        setOnClickListener();
        adapter = new recyclerAdapter(kukatLista, listener);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                if (filteredList.size() == 0) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("kukanNimi", kukatLista.get(position).getKukkaNimi());
                    intent.putExtra("kukanKuva", kukatLista.get(position).getKukkaKuva());
                    intent.putExtra("kukanKuvaus", kukatLista.get(position).getKukkaKuvaus());
                    startActivity(intent);
                } else {

                    String filteredListItemName = filteredList.get(position).getKukkaNimi();
                    int actualPosition = -1;
                    for (Kukka kukka : kukatLista) {
                        if (filteredListItemName == kukka.getKukkaNimi()) {
                            actualPosition = kukatLista.indexOf(kukka);
                        }
                    }

                    Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("kukanNimi", kukatLista.get(actualPosition).getKukkaNimi());
                    intent.putExtra("kukanKuva", kukatLista.get(actualPosition).getKukkaKuva());
                    intent.putExtra("kukanKuvaus", kukatLista.get(actualPosition).getKukkaKuvaus());
                    startActivity(intent);
                }
            }
        };
    }

    private void setKukkaInfo() {
        kukatLista.add(new Kukka("Kaktus", R.drawable.kaktus, R.string.Kaktus));
        kukatLista.add(new Kukka("Kielo", R.drawable.kielo, R.string.Kielo));
        kukatLista.add(new Kukka("Sinikello", R.drawable.sinikello, R.string.Sinikello));
        kukatLista.add(new Kukka("Myrkkymuratti", R.drawable.myrkkymuratti, R.string.Myrkkymuratti));
        kukatLista.add(new Kukka("Auringonkukka", R.drawable.auringonkukka, R.string.Auringonkukka));
        kukatLista.add(new Kukka("Narsissi", R.drawable.narsissi, R.string.Narsissi));
        kukatLista.add(new Kukka("Rentunruusu", R.drawable.rentunruusu, R.string.Rentunruusu));
        kukatLista.add(new Kukka("Jukka Palmu", R.drawable.jukkapalmu, R.string.Jukkapalmu));
        kukatLista.add(new Kukka("Yukkapalmu", R.drawable.yukkapalmu, R.string.Yukkapalmu));
        kukatLista.add(new Kukka("Hortenssia", R.drawable.hortenssia, R.string.Hortenssia));
        kukatLista.add(new Kukka("Peikko", R.drawable.peikko, R.string.Peikko));
        kukatLista.add(new Kukka("Nukkumatti", R.drawable.nukkumatti, R.string.Kaktus));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.recyclerview);
        kukatLista = new ArrayList<>();
        setKukkaInfo();

        setAdapter();
        setOnClickListener();

        searchView = getActivity().findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        filteredList = new ArrayList<>();

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController( SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void filterList(String text) {
        filteredList = new ArrayList<>();
        for (Kukka kukka : kukatLista) {
            if(kukka.getKukkaNimi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(kukka);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Ei löytynyt kasveja", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}