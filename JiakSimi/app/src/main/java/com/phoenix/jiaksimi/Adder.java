package com.phoenix.jiaksimi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_APPEND;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Adder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Adder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Adder extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button addButton;
    private TextView foodEditText;
    private Spinner categorySpinner;
    private ConstraintLayout constraintLayout;

    public Adder() {
        // Required empty public constructor
    }

    public static Adder newInstance() {
        return new Adder();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_adder, container, false);
        initView(root);
        initListener(root);
        return root;
    }

    private void writeData(String data, String fileName) {
        FileOutputStream outStream = null;
        try {
            outStream = getActivity().openFileOutput(fileName, MODE_APPEND);
            outStream.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView(final View view) {
        foodEditText = view.findViewById(R.id.foodEditText);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        addButton = view.findViewById(R.id.addButton);
        constraintLayout = view.findViewById(R.id.constraintLayout);
    }

    private void initListener(final View view) {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Prevent adding empty entries into the dataset
                if(foodEditText.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Insert a name to add",
                            Snackbar.LENGTH_SHORT).show();
                } else {
                    String food = foodEditText.getText().toString();
                    String category = categorySpinner.getSelectedItem().toString();

                    //Check which category to write into
                    switch (category) {
                        case "Meals":
                            writeData(food + "\n", "meal.data");
                            Loader.mealList.add(food);
                            break;
                        case "Meat/Diary":
                            writeData(food + "\n", "meat.data");
                            Loader.meatList.add(food);
                            break;
                        case "Soup":
                            writeData(food + "\n", "soup.data");
                            Loader.soupList.add(food);
                            break;
                        case "Vegetable":
                            writeData(food + "\n", "veg.data");
                            Loader.vegList.add(food);
                            break;
                    }

                    //Clear out foodEditText after the entry has been stored
                    foodEditText.setText("");
                    //Notify recyclerView adapter to update
                    Menu.rAdapter.notifyDataSetChanged();
                    //Confirmation Notification
                    Snackbar.make(view, "Item added", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        foodEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                foodEditText.clearFocus();
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
