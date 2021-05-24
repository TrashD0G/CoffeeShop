package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.artem.coffeeshop.CoffeeShopApplication
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentEnterAccountBinding
import com.artem.coffeeshop.presentation.account.viewModelEnterAccount.ViewModelEnterAccount
import com.artem.coffeeshop.presentation.account.viewModelEnterAccount.ViewModelEnterAccountFactory
import com.artem.coffeeshop.utilites.TAG
import javax.inject.Inject


class EnterAccountFragment : Fragment() {

    private var fragmentEnterAccountBinding: FragmentEnterAccountBinding? = null

    @Inject
    lateinit var viewModelEnterFactory: ViewModelEnterAccountFactory


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

        // Dagger inject
        (requireActivity().applicationContext as CoffeeShopApplication)
            .applicationAppComponent.injectEnterAccountFragment(this)

        // Data binding
        val editTextEmailData = fragmentEnterAccountBinding?.editTextAccountLogin
        val editTextPasswordData = fragmentEnterAccountBinding?.editTextAccountPassword

        // ViewModel
        val viewModel = ViewModelProvider(requireActivity(), viewModelEnterFactory).get(ViewModelEnterAccount::class.java)
        viewModel.emailValidate.observe(viewLifecycleOwner,
            { if (!it) editTextEmailData?.error = "Ошибка ввода!" })
        viewModel.passwordValidate.observe(viewLifecycleOwner,
            { if (!it) editTextPasswordData?.error = "Ошибка ввода!" })
        viewModel.enterAccountResult.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            Log.i(TAG,"Enter fragment result: " + it.toString())
            if (it == "Выполнен вход!") {
                view.findNavController().navigate(R.id.action_enterAccountFragment_to_mainScreenActivity)
            }
        })

        fragmentEnterAccountBinding?.buttonEnter?.setOnClickListener {

            viewModel.checkInput(
                editTextEmailData?.text.toString(),
                editTextPasswordData?.text.toString()
            )
        }

        fragmentEnterAccountBinding?.buttonRegister?.setOnClickListener {
            view.findNavController().navigate(R.id.action_enterAccountFragment_to_createAccountFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        fragmentEnterAccountBinding = null
    }
}