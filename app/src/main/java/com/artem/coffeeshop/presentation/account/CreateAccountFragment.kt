package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentCreateAccountBinding
import com.artem.coffeeshop.databinding.FragmentEnterAccountBinding
import com.artem.coffeeshop.presentation.account.viewModelAccount.ViewModelAccount



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

        val viewModel = ViewModelProvider(requireActivity()).get(ViewModelAccount::class.java)


        val editTextEmailData: EditText? = fragmentCreateAccountBinding?.editTextEmailCreateAccount
        val editTextPasswordData: EditText? = fragmentCreateAccountBinding?.editTextPasswordCreateAccount
        val editTextPhoneNumberData: EditText? = fragmentCreateAccountBinding?.editTextPhoneNumberCreateAccount
        val editTextFirstNameData: EditText? = fragmentCreateAccountBinding?.editTextFirstNameCreateAccount
        val editTextLastNameData: EditText? = fragmentCreateAccountBinding?.editTextLastNameCreateAccount


        viewModel.emailValidate.observe(viewLifecycleOwner,{ if (!it) editTextEmailData?.error = "Ошибка ввода!" })
        viewModel.passwordValidate.observe(viewLifecycleOwner,{ if (!it) editTextPasswordData?.error = "Ошибка ввода!" })
        viewModel.phoneNumberValidate.observe(viewLifecycleOwner,{if (!it) editTextPhoneNumberData?.error = "Ошибка ввода!"})
        viewModel.firstName.observe(viewLifecycleOwner,{if (!it) editTextFirstNameData?.error = "Ошибка ввода!"})
        viewModel.lastName.observe(viewLifecycleOwner,{if (!it) editTextLastNameData?.error = "Ошибка ввода!"})


        fragmentCreateAccountBinding?.buttonCreate?.setOnClickListener { viewModel.checkInput(
                editTextEmailData?.text.toString(),
                editTextPasswordData?.text.toString(),
                editTextPhoneNumberData?.text.toString(),
                editTextFirstNameData?.text.toString(),
                editTextLastNameData?.text.toString()) }



        fragmentCreateAccountBinding?.buttonBack?.setOnClickListener {
            view.findNavController().navigate(R.id.action_createAccountFragment_to_enterAccountFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCreateAccountBinding = null
    }

}