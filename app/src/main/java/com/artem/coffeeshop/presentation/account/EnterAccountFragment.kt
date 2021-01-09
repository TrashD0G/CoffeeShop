package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentEnterAccountBinding


class EnterAccountFragment : Fragment() {

    private var fragmentEnterAccountBinding: FragmentEnterAccountBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentEnterAccountBinding =
            FragmentEnterAccountBinding.inflate(inflater, container, false)

        return fragmentEnterAccountBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fragmentEnterAccountBinding?.buttonRegister?.setOnClickListener {
            view.findNavController().navigate(R.id.action_enterAccountFragment_to_createAccountFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        fragmentEnterAccountBinding = null
    }
}