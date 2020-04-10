package com.beloushkin.test.mymvvmapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.beloushkin.test.mymvvmapp.databinding.FragmentProfileBinding

//PRESENTER LEVEL
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ProfileRepositoryImplementationFaked()
                return ProfileViewModel(repository) as T
            }
        }
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set the layout viewmodel. ViewModel knows nothing what we do here
        binding.viewModel = viewModel
        binding.lifecycleOwner =viewLifecycleOwner

        viewModel.needNavigateToStart.observe(viewLifecycleOwner, Observer {
            need -> if(need) navigateToStart()
        })
    }

    private fun navigateToStart(): Unit = TODO("Not implemented")

}