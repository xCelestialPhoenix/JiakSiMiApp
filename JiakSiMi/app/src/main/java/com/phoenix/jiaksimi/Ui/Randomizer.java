package com.phoenix.jiaksimi.Ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.phoenix.jiaksimi.MainActivity;
import com.phoenix.jiaksimi.R;
import com.phoenix.jiaksimi.Util.FoodType;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Randomizer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Randomizer extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView lunchTextView;
    private TextView meatTextView;
    private TextView vegTextView;
    private TextView soupTextView;
    private Button randomLunchButton;
    private Button randomMeatButton;
    private Button randomVegButton;
    private Button randomSoupButton;
    private Button massRandomButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Randomizer.
     */
    static Randomizer newInstance() {
        return new Randomizer();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_randomizer, container, false);
        initView(root);
        initListener();
        return root;
    }

    private void initView(final View view) {
        randomLunchButton = view.findViewById(R.id.lunchRandButton);
        randomMeatButton = view.findViewById(R.id.meatRandButton);
        randomVegButton = view.findViewById(R.id.vegRandButton);
        randomSoupButton = view.findViewById(R.id.soupRandButton);
        massRandomButton = view.findViewById(R.id.massRandButton);
        lunchTextView = view.findViewById(R.id.lunchTextView);
        meatTextView = view.findViewById(R.id.meatTextView);
        vegTextView = view.findViewById(R.id.vegTextView);
        soupTextView = view.findViewById(R.id.soupTextView);
    }

    private void initListener() {

        final MainActivity activity = (MainActivity) getActivity();
        assert activity != null;

        randomLunchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lunchTextView.setText(activity.randomizeFood(FoodType.LUNCH));
            }
        });

        randomMeatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                meatTextView.setText(activity.randomizeFood(FoodType.MEAT));
            }
        });

        randomVegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vegTextView.setText(activity.randomizeFood(FoodType.VEG));
            }
        });

        randomSoupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soupTextView.setText(activity.randomizeFood(FoodType.SOUP));
            }
        });

        massRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lunchTextView.setText(activity.randomizeFood(FoodType.LUNCH));
                meatTextView.setText(activity.randomizeFood(FoodType.MEAT));
                vegTextView.setText(activity.randomizeFood(FoodType.VEG));
                soupTextView.setText(activity.randomizeFood(FoodType.SOUP));
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
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
