package com.phoenix.jiaksimi.Ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.phoenix.jiaksimi.MainActivity;
import com.phoenix.jiaksimi.R;
import com.phoenix.jiaksimi.Util.FoodType;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TabLayout tabLayout;
    private RecyclerView foodRecyclerView;
    private FoodRecyclerViewAdapter rAdapter;

    static Menu newInstance() {
        return new Menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(root);
        initListener();

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;

        //Setting the properties of recyclerView and its components
        rAdapter = new FoodRecyclerViewAdapter(activity, activity.getFoodList(FoodType.MEAL), "meal");
        foodRecyclerView.setAdapter(rAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodRecyclerView.setHasFixedSize(true);

        return root;
    }

    private void initView(final View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        foodRecyclerView = view.findViewById(R.id.foodRecyclerView);
    }

    private void initListener() {

        final MainActivity activity = (MainActivity) getActivity();
        assert activity != null;

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                switch (index) {
                    case 0:
                        rAdapter.changeDataSet(activity.getFoodList(FoodType.MEAL), "meal");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        rAdapter.changeDataSet(activity.getFoodList(FoodType.MEAT), "meat");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        rAdapter.changeDataSet(activity.getFoodList(FoodType.VEG), "veg");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        rAdapter.changeDataSet(activity.getFoodList(FoodType.SOUP), "soup");
                        rAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Unused
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Unused
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
