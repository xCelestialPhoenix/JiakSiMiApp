package com.phoenix.jiaksimi.Ui;

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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.phoenix.jiaksimi.MainActivity;
import com.phoenix.jiaksimi.R;
import com.phoenix.jiaksimi.Util.FoodType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static android.content.Context.MODE_APPEND;
import static com.phoenix.jiaksimi.Util.Filepath.MEAL_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.MEAT_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.SOUP_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.VEG_DATA_FILEPATH;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Adder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Adder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Adder extends Fragment {

    private final static String LOG_TAG = Adder.class.getName();
    private final static Logger logger = Logger.getLogger(LOG_TAG);

    private OnFragmentInteractionListener mListener;
    private Button addButton;
    private TextView foodEditText;
    private Spinner categorySpinner;
    private ConstraintLayout constraintLayout;


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
                if (foodEditText.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Insert a name to add", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String food = foodEditText.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();
                MainActivity activity = (MainActivity) getActivity();

                //Check which category to write into
                switch (category) {
                    case "Meals":
                        activity.writeData(food + "\n", MEAL_DATA_FILEPATH);
                        activity.addFoodItem(food, FoodType.MEAL);
                        break;
                    case "Meat/Diary":
                        activity.writeData(food + "\n", MEAT_DATA_FILEPATH);
                        activity.addFoodItem(food, FoodType.MEAT);
                        break;
                    case "Soup":
                        activity.writeData(food + "\n", SOUP_DATA_FILEPATH);
                        activity.addFoodItem(food, FoodType.SOUP);
                        break;
                    case "Vegetable":
                        activity.writeData(food + "\n", VEG_DATA_FILEPATH);
                        activity.addFoodItem(food, FoodType.VEG);
                        break;
                    default:
                        logger.log(Level.WARNING, "Invalid food category");
                }

                //Clear out foodEditText after the entry has been stored
                foodEditText.setText("");
                //Confirmation Notification
                Snackbar.make(view, "Item added", Snackbar.LENGTH_SHORT).show();
            }
        });

        foodEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
        void onFragmentInteraction(Uri uri);
    }
}
