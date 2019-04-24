package com.example.android.internshalaproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpinnerFragment extends Fragment {

    int i,j;
    String arr1[][] = new String[9][5];
    Spinner spinner,spinner2;

    public SpinnerFragment() {
        // Required empty public constructor
        for(j=0;j<9;j++) {
            for(i=0;i<5;i++) {
                arr1[j][i] = Integer.toString((i+1)*(j+1));

            }
        }
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_spinner, container, false);

        String [] values =
                {"1","2","3","4","5","6","7","8","9"};
        spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) v.findViewById(R.id.spinner2);

        spinner2.setEnabled(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                                  if (parent.equals(spinner)) {

                                                      spinner2.setEnabled(true);
                                                      if (spinner.getSelectedItem().equals("1")) {
                                                          ArrayAdapter<String> s1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[0]);
                                                          s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s1);
                                                      } else if (spinner.getSelectedItem().equals("2")) {
                                                          ArrayAdapter<String> s2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[1]);
                                                          s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s2);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("3")) {
                                                          ArrayAdapter<String> s3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[2]);
                                                          s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s3);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("4")) {
                                                          ArrayAdapter<String> s4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[3]);
                                                          s4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s4);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("5")) {
                                                          ArrayAdapter<String> s5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[4]);
                                                          s5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s5);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("6")) {
                                                          ArrayAdapter<String> s6 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[5]);
                                                          s6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s6);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("7")) {
                                                          ArrayAdapter<String> s7 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[6]);
                                                          s7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s7);
                                                      }
                                                      else if (spinner.getSelectedItem().equals("8")) {
                                                          ArrayAdapter<String> s8 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[7]);
                                                          s8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s8);
                                                      }
                                                      else {
                                                          ArrayAdapter<String> s9 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arr1[8]);
                                                          s9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                          spinner2.setAdapter(s9);
                                                      }

                                                  }
                                              }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }

}
