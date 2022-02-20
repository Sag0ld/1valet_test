package com.example.a1valet.views.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.a1valet.R
import com.example.a1valet.databinding.FragmentDevicesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.scope.scopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DeviceFragment : Fragment() {

    private var _binding: FragmentDevicesBinding? = null
    private val deviceViewModel: DeviceViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            deviceViewModel.fetchDevices()
        }
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.to_detailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}