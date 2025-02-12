package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel



class Tab1Fragment : Fragment(), Tab1Listner {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBinding
        val binding: FragmentTab1Binding? = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab1,
            container,
            false
        )
        // Check if binding is not null before accessing it
        binding?.let {
            // Obtain ViewModel instance
            val viewModel = ViewModelProvider(this).get(Tab1ViewModel::class.java)

            // Set the ViewModel to the binding
            it.tab1ViewModel = viewModel

            // Set the lifecycle owner to make LiveData observable
            it.lifecycleOwner = viewLifecycleOwner
            viewModel.tab1Listner = this

            populateCircle(viewModel,binding)
            populateDivision(viewModel,binding)
            populateSubdivision(viewModel,binding)
            populateSection(viewModel,binding)

            // Observe the LiveData for credential validation
//            viewModel.isValidCredential.observe(viewLifecycleOwner, Observer { isValid ->
//                if (isValid) {
//                    // Handle valid credentials (e.g., navigate to another fragment)
//                    // Use NavController to navigate if needed
//                    findNavController().navigate(R.id.action_tab1Fragment_to_nextFragment)
//                } else {
//                    // Handle invalid credentials (e.g., show an error message)
//                    Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
//                }
//            })

            // Return the root view of the binding
            return it.root
        }
        // Return null if binding is null (shouldn't happen)
        return null
    }


    private fun populateCircle(viewModel: Tab1ViewModel, binding: FragmentTab1Binding) {
        // Observe the subdivisionList LiveData
        viewModel.circleList.observe(viewLifecycleOwner, Observer { circles ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                circles
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.optionSubdivision.adapter = adapter
        })

        binding.optionCircle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedSubdivision = parent.getItemAtPosition(position) as String
                // Handle the selected subdivision
                binding.tab1ViewModel.onCircleSelected(selectedSubdivision)
                Toast.makeText(requireContext(), "Selected: $selectedSubdivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun populateDivision(viewModel: Tab1ViewModel, binding: FragmentTab1Binding) {
        // Observe the subdivisionList LiveData
        viewModel.divisionList.observe(viewLifecycleOwner, Observer { divisions ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                divisions
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.optionDivision.adapter = adapter
        })

        binding.optionDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDivision = parent.getItemAtPosition(position) as String
                // Handle the selected subdivision
                binding.tab1ViewModel.onDivisionSelect(selectedDivision)
                Toast.makeText(requireContext(), "Selected: $selectedDivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun populateSubdivision(viewModel: Tab1ViewModel, binding: FragmentTab1Binding) {
        // Observe the subdivisionList LiveData
        viewModel.subdivisionList.observe(viewLifecycleOwner, Observer { subdivisions ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                subdivisions
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.optionSubdivision.adapter = adapter
        })

        binding.optionSubdivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedSubdivision = parent.getItemAtPosition(position) as String
                // Handle the selected subdivision
                binding.tab1ViewModel.onSubDivisionSelect(selectedSubdivision)
                Toast.makeText(requireContext(), "Selected: $selectedSubdivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }

    private fun populateSection(viewModel: Tab1ViewModel, binding: FragmentTab1Binding) {
        // Observe the subdivisionList LiveData
        viewModel.sectionList.observe(viewLifecycleOwner, Observer { sections ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                sections
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.optionSubdivision.adapter = adapter
        })

        binding.optionSubdivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedsection = parent.getItemAtPosition(position) as String
                // Handle the selected section
                binding.tab1ViewModel.onSectionSelect(selectedsection)
                Toast.makeText(requireContext(), "Selected: $selectedsection", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }

    override fun onSuccess() {
        //TODO("Not yet implemented")
        Log.d("log","hi onSuccess")
        //requireContext().toast("hi onSuccess");
    }

    override fun onFailure() {
        //TODO("Not yet implemented")
    }
}