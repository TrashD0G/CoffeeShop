package com.artem.coffeeshop.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.artem.coffeeshop.CoffeeShopApplication
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.FragmentCreateAccountBinding
import com.artem.coffeeshop.presentation.account.viewModelAccount.ViewModelAccount
import com.artem.coffeeshop.presentation.account.viewModelAccount.ViewModelAccountFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class CreateAccountFragment : Fragment(), CoroutineScope {

    private var fragmentCreateAccountBinding: FragmentCreateAccountBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelAccountFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentCreateAccountBinding =
            FragmentCreateAccountBinding.inflate(inflater, container, false)

        return fragmentCreateAccountBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as CoffeeShopApplication)
            .applicationCreateAccountComponent.injectCreateAccountFragment(this)


        val viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(ViewModelAccount::class.java)


        val editTextEmailData: EditText? = fragmentCreateAccountBinding?.editTextEmailCreateAccount
        val editTextPasswordData: EditText? =
            fragmentCreateAccountBinding?.editTextPasswordCreateAccount
        val editTextFirstNameData: EditText? =
            fragmentCreateAccountBinding?.editTextFirstNameCreateAccount
        val editTextLastNameData: EditText? =
            fragmentCreateAccountBinding?.editTextLastNameCreateAccount


        viewModel.emailValidate.observe(viewLifecycleOwner,
            { if (!it) editTextEmailData?.error = "Ошибка ввода!" })
        viewModel.passwordValidate.observe(viewLifecycleOwner,
            { if (!it) editTextPasswordData?.error = "Ошибка ввода!" })
        viewModel.firstName.observe(viewLifecycleOwner,
            { if (!it) editTextFirstNameData?.error = "Ошибка ввода!" })
        viewModel.lastName.observe(viewLifecycleOwner,
            { if (!it) editTextLastNameData?.error = "Ошибка ввода!" })

        viewModel.createAccountResult.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            if (it == "Аккаунт создан!") {
                view.findNavController()
                    .navigate(R.id.action_createAccountFragment_to_enterAccountFragment)
            }
        })



        fragmentCreateAccountBinding?.buttonCreate?.setOnClickListener {

            viewModel.checkInput(
                editTextEmailData?.text.toString(),
                editTextPasswordData?.text.toString(),
                editTextFirstNameData?.text.toString(),
                editTextLastNameData?.text.toString()
            )

        }

        fragmentCreateAccountBinding?.buttonBack?.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_createAccountFragment_to_enterAccountFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentCreateAccountBinding = null
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}