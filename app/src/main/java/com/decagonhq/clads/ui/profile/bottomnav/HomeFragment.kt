package com.decagonhq.clads.ui.profile.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.HomeFragmentBinding
import com.decagonhq.clads.ui.profile.adapter.HomeFragmentClientsRecyclerAdapter
import com.decagonhq.clads.util.ChartData.chartData
import com.decagonhq.clads.util.DummyDataUtil

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeFragmentYearDropdown: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            homeFragmentClientListRecyclerView.apply {

                adapter =
                    HomeFragmentClientsRecyclerAdapter(
                        DummyDataUtil.populateClient()
                    )
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                setHasFixedSize(true)
            }
        }
        homeFragmentYearDropdown = binding.homeFragmentYearDropdownAutocompleteTextView
        chartData(view)
    }

    override fun onResume() {
        super.onResume()
        /*Set up Account Category Dropdown*/
        val chartYear = resources.getStringArray(R.array.Year)
        val accountCategoriesArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.chart_year_dropdown_item, chartYear)
        homeFragmentYearDropdown.setAdapter(accountCategoriesArrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
