package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentCreateAccountBinding
import com.artem.coffeeshop.databinding.FragmentCreateAccountBinding.inflate
import com.artem.coffeeshop.presentation.account.viewModelAccount.ViewModelAccount



class  CreateAccountFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}