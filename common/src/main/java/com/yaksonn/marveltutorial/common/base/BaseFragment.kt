package com.yaksonn.marveltutorial.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yaksonn.marveltutorial.common.ext.Inflate
import com.yaksonn.marveltutorial.common.ext.runSafely
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController


abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    /**
     * Returns Current ViewModel Instance
     */
    val viewModel: VM by lazy { ViewModelProvider(this)[getViewModelKey()] }

    /**
     * Returns ViewModel class type
     */
    protected abstract fun getViewModelKey(): Class<VM>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        listeners()
    }

    open fun initUI() {
    }


    open fun listeners() {
    }

    fun navigate(directions: NavDirections) = runSafely {
        findNavController().navigate(directions)
    }

}