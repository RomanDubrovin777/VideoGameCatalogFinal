package com.example.videogamescatalog.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.videogamescatalog.Activities.CustomeAdapter;
import com.example.videogamescatalog.Models.GameDataModel;
import com.example.videogamescatalog.R;
import com.example.videogamescatalog.Services.DataService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GamesRescyclerViewFragment extends Fragment {
    private ArrayList<GameDataModel> arrayOfGames;
    private RecyclerView recyclerView;
    private CustomeAdapter adapter;
    private Spinner spinnerGenres, spinnerYears, spinnerDevelopers;
    private String selectedGenre = "";
    private String selectedYear = "";
    private String selectedDeveloper = "";
    private ArrayList<GameDataModel> filteredArrayOfGames;

    private EditText editTextSearch;


    public GamesRescyclerViewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_games_rescycler_view, container, false);
        editTextSearch = view.findViewById(R.id.editTextSearch);
        DataService dataService = new DataService();
        arrayOfGames = dataService.GetAllGames();
        adapter = new CustomeAdapter(arrayOfGames);
        adapter.setOnItemClickListener(gameDataModel ->
        {
            openGameDetailsFragment(gameDataModel);
        });

        recyclerView = view.findViewById(R.id.RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        spinnerGenres = view.findViewById(R.id.spinnerGenres);
        spinnerYears = view.findViewById(R.id.SpinnerDates);
        spinnerDevelopers = view.findViewById(R.id.SpinnerDevop);

        setupSpinner(spinnerGenres, getAllValues("genre"), value ->
        {
            selectedGenre = value;
            filterGames();
        });

        setupSpinner(spinnerYears, getAllValues("year"), value ->
        {
            selectedYear = value;
            filterGames();
        });

        setupSpinner(spinnerDevelopers, getAllValues("developer"), value ->
        {
            selectedDeveloper = value;
            filterGames();
        });

        editTextSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                filterGamesBasedOnSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return view;
    }

    private void filterGamesBasedOnSearch(String iSearchFromGames) {
        String searchFromGames = iSearchFromGames.toLowerCase().trim();
        if (searchFromGames.isEmpty())
        {
            filterGames();
        }
        else
        {
            ArrayList<GameDataModel> searchResults = new ArrayList<>();
            for (GameDataModel game : filteredArrayOfGames) {
                if (game.getM_GameName().toLowerCase().contains(searchFromGames))
                {
                    searchResults.add(game);
                }
            }
            adapter.updateData(searchResults);
        }
    }

    private void openGameDetailsFragment(GameDataModel gameDataModel)
    {
        GameDetailsFragment detailsFragment = GameDetailsFragment.newInstance(
                gameDataModel.getM_Picture(),
                gameDataModel.getM_GameName(),
                gameDataModel.getM_Description(),
                gameDataModel.getM_Platform(),
                gameDataModel.getM_Developer(),
                gameDataModel.getM_Date(),
                gameDataModel.getM_GameUrl(),
                gameDataModel.getM_Genre()
        );
    requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    private void setupSpinner(Spinner spinner, List<String> items, OnItemSelectedListener listener)
    {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                listener.onItemSelected(items.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private List<String> getAllValues(String category) {

        Set<String> uniqueValues = new LinkedHashSet<>();
        uniqueValues.add("ALL");

        for (GameDataModel game : arrayOfGames) {
            switch (category) {
                case "genre":
                    uniqueValues.add(game.getM_Genre());
                    break;
                case "year":
                    String fullDate = game.getM_Date();
                    if (fullDate != null && fullDate.length() >= 4)
                    {
                        String year = fullDate.substring(0, 4);
                        uniqueValues.add(year);
                    }
                    break;
                case "developer":
                    uniqueValues.add(game.getM_Developer());
                    break;
            }
        }
        List<String> sortedValues = new ArrayList<>(uniqueValues);

        if (category.equals("year"))
        {
            sortedValues.subList(1, sortedValues.size()).sort(Comparator.reverseOrder());
        }

        return sortedValues;
    }

    private void filterGames() {

        if (selectedGenre.equals("ALL") && selectedYear.equals("ALL") && selectedDeveloper.equals("ALL"))
        {
            adapter.updateData(arrayOfGames);
            filteredArrayOfGames = new ArrayList<>(arrayOfGames);
            return;
        }

        if (filteredArrayOfGames == null)
        {
            filteredArrayOfGames = new ArrayList<>();
        }

        filteredArrayOfGames.clear();

        for (GameDataModel game : arrayOfGames)
        {
            String fullDate = game.getM_Date();
            String gameYear = (fullDate != null && fullDate.length() >= 4) ? fullDate.substring(0, 4) : "";
            boolean matchesGenre = selectedGenre.equals("ALL") || game.getM_Genre().equals(selectedGenre);
            boolean matchesYear = selectedYear.equals("ALL") || gameYear.equals(selectedYear);
            boolean matchesDeveloper = selectedDeveloper.equals("ALL") || game.getM_Developer().equals(selectedDeveloper);
            if (matchesGenre && matchesYear && matchesDeveloper)
            {
                filteredArrayOfGames.add(game);
            }
        }
        adapter.updateData(filteredArrayOfGames);
    }

    interface OnItemSelectedListener
    {
        void onItemSelected(String value);
    }
}
