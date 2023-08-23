package com.example.institutodepesquisaseldorado.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<vBinding : ViewBinding, vModel : ViewModel> : Fragment() {

    private var _binding: vBinding? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: vModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this._binding = getViewBinding(inflater, container)
        return this.binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): vBinding?

    override fun onDestroyView() {
        super.onDestroyView()
        this._binding = null
    }
}
