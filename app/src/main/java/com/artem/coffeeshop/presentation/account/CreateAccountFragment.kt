package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentCreateAccountBinding
import com.artem.coffeeshop.databinding.FragmentEnterAccountBinding


class  CreateAccountFragment : Fragment() {

    private var fragmentCreateAccountBinding: FragmentCreateAccountBinding? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        fragmentCreateAccountBinding = FragmentCreateAccountBinding.inflate(inflater, container, false)

        return fragmentCreateAccountBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fragmentCreateAccountBinding?.buttonBack?.setOnClickListener {
            view.findNavController().navigate(R.id.action_createAccountFragment_to_enterAccountFragment)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCreateAccountBinding = null
    }

}