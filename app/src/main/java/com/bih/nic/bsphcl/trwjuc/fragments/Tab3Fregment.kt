package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.adapters.MaterialAdapter
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab3Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab3.Tab3Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab3.Tab3ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Tab3Fregment : Fragment(),Tab3Listner {
    private lateinit var binding: FragmentTab3Binding
    private lateinit var viewModel: Tab3ViewModel
    var viewPager: ViewPager2?=null
    //var recycler_view : RecyclerView ?=null
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
        // Inflate the layout for this fragment using DataBinding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab3,
            container,
            false
        )
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        // Observe the shared data

        // Check if binding is not null before accessing it
        binding?.let {
            // Obtain ViewModel instance
            viewModel = ViewModelProvider(this).get(Tab3ViewModel::class.java)

            // Set the ViewModel to the binding
            it.tab3ViewModel = viewModel

            // Set the lifecycle owner to make LiveData observable
            it.lifecycleOwner = viewLifecycleOwner
            viewModel.tab3Listner = this
            sharedViewModel.data.observe(viewLifecycleOwner, Observer { data ->
                viewModel.trwNo.value = data
            })
            val myList: MutableList<String> = mutableListOf()
            // Set up the AutoCompleteTextView
            var adapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line, // Layout for dropdown items
                myList
            )
            binding.spYearMan.setAdapter(adapter)

            // Observe the material list and update the adapter
            viewModel.materialList.observe(requireActivity(), Observer { materials ->
                adapter.clear()
                adapter.addAll(materials)
                adapter.notifyDataSetChanged()
            })
            // Handle item click
            binding.spYearMan.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                //Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
                viewModel.onMaterialSelected(selectedItem)
            }
            // Observe the selected material
            viewModel.selectedMaterial.observe(requireActivity(), Observer { selected ->
                viewModel.onMaterialSelected(selected)
                if (!selected.isNullOrBlank()){
                    if (viewModel._materialSelected?.value == 11 || viewModel._materialSelected?.value == 12) {
                        binding.editWeight.visibility=View.GONE
                    }
                }
            })
            // Observe the form state from ViewModel
            viewModel.formState.observe(requireActivity(), Observer { formState ->
                formState.errorMessage?.let {
                    // Show the error message
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                }

                // Enable or disable the submit button based on form validity
                //binding?.buttonTab1?.isEnabled = formState.isValid
            })
            viewModel.materialUtilized1?.observe(requireActivity(), {
                // important part: initialize the adapter only once
                val productAdapter = MaterialAdapter(requireContext(), it) // Pass only the data
                binding?.materialLst?.layoutManager = LinearLayoutManager(requireActivity())
                binding.materialLst.adapter = productAdapter

                // Optional: Update the result number text change
//                val productNumber = materialList.size.toString()
//                binding.textViewResultNumber.text = "$productNumber sonuç bulundu"
            })
            // Return the root view of the binding
            return it.root
        }
        // Return null if binding is null (shouldn't happen)
        return null
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.data.observe(viewLifecycleOwner, Observer { data ->
            viewModel.trwNo.value = data
        })
        if (viewModel.trwNo.value.isNullOrEmpty() || viewModel.trwNo.value.equals("No data Found")){
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Info")
            builder.setMessage("Please fill first tab first !")

            builder.setPositiveButton("OK") { dialog, _ ->
                // Perform an action when "Yes" is clicked
                dialog.dismiss()
                viewPager?.currentItem = 0
            }
            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    override fun onSuccess() {

    }

    override fun onFailure() {

    }
}