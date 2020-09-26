package co.com.acercate.ui.assignDate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import co.com.acercate.R;

public class AssignDateFragment extends Fragment {


    Spinner spProvider;
    Spinner spAvailableDates;
    Button btAssingDate;

    ArrayList<String> arrayListProvider = new ArrayList<>();
    ArrayList<String> arrayAvailableDates = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_assign_date, container, false);
        spProvider = root.findViewById(R.id.spProvider);
        spAvailableDates = root.findViewById(R.id.spAvailableDates);
        btAssingDate = root.findViewById(R.id.btAssingDate);


        for (int i = 0; i < 5; i++) {
            arrayListProvider.add("Prestador " + (i + 1));
        }

        ArrayAdapter<String> arrayAdapterProvider = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayListProvider);
        arrayAdapterProvider.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvider.setAdapter(arrayAdapterProvider);
        spProvider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayAvailableDates.clear();
                for (int i = 0; i <= id; i++) {
                    arrayAvailableDates.add("Cita " + (i + 1));
                }
                ArrayAdapter<String> arrayAdapterAvialableDates = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arrayAvailableDates);
                arrayAdapterAvialableDates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAvailableDates.setAdapter(arrayAdapterAvialableDates);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return root;
    }
}