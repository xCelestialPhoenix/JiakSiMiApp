package com.phoenix.jiaksimi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Randomizer.OnFragmentInteractionListener} interface
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
    private Random randGenerator;

    public Randomizer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Randomizer.
     */
    // TODO: Rename and change types and number of parameters
    public static Randomizer newInstance() {
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
        initListener(root);
        randGenerator = new Random();
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

    private void initListener(final View view) {

        randomLunchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Loader.mealList.size() > 0) {
                    lunchTextView.setText(Loader.mealList.get(randGenerator.nextInt(Loader.mealList.size())));
                }
            }
        });

        randomMeatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Loader.meatList.size() > 0) {
                    meatTextView.setText(Loader.meatList.get(randGenerator.nextInt(Loader.meatList.size())));
                }
            }
        });

        randomVegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Loader.vegList.size() > 0) {
                    vegTextView.setText(Loader.vegList.get(randGenerator.nextInt(Loader.vegList.size())));
                }
            }
        });

        randomSoupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Loader.soupList.size() > 0) {
                    soupTextView.setText(Loader.soupList.get(randGenerator.nextInt(Loader.soupList.size())));
                }
            }
        });

        massRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Loader.mealList.size() > 0) {
                    lunchTextView.setText(Loader.mealList.get(randGenerator.nextInt(Loader.mealList.size())));
                }
                if(Loader.meatList.size() > 0) {
                    meatTextView.setText(Loader.meatList.get(randGenerator.nextInt(Loader.meatList.size())));
                }
                if(Loader.vegList.size() > 0) {
                    vegTextView.setText(Loader.vegList.get(randGenerator.nextInt(Loader.vegList.size())));
                }
                if(Loader.soupList.size() > 0) {
                    soupTextView.setText(Loader.soupList.get(randGenerator.nextInt(Loader.soupList.size())));
                }
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
