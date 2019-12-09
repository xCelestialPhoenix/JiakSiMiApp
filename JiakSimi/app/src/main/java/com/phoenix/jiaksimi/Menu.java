package com.phoenix.jiaksimi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Menu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TabLayout tabLayout;
    private RecyclerView foodRecyclerView;
    protected static FoodRecyclerViewAdapter rAdapter;

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance() {
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
        initListener(root);

        //Setting the properties of recyclerView and its components
        rAdapter = new FoodRecyclerViewAdapter(getActivity(), Loader.mealList, "meal");
        foodRecyclerView.setAdapter(rAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodRecyclerView.setHasFixedSize(true);

        return root;
    }

    private void initView(final View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        foodRecyclerView = view.findViewById(R.id.foodRecyclerView);
    }

    private void initListener(final View root) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                switch (index) {
                    case 0:
                        rAdapter.changeDataSet(Loader.mealList, "meal");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        rAdapter.changeDataSet(Loader.meatList, "meat");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        rAdapter.changeDataSet(Loader.vegList, "veg");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        rAdapter.changeDataSet(Loader.soupList, "soup");
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
