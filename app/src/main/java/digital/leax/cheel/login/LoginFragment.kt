package digital.leax.cheel.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import digital.leax.cheel.databinding.FragmentLoginBinding
import digital.leax.cheel.utils.addPlaylistList
import digital.leax.cheel.utils.setCurrentPlaylistSelected
import digital.leax.cheel.utils.setToken

private const val TAG = "LoginFragment"

class LoginFragment : Fragment() {

    private val model: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPlaylistList(requireContext(), "My playlist")
        addPlaylistList(requireContext(), "BipBop")
        addPlaylistList(requireContext(), "Help")

        setCurrentPlaylistSelected(requireContext(), null)

        model.getState().observe(viewLifecycleOwner, Observer { updateUi(it!!) })

        binding.loginButton.setOnClickListener {
            model.login(binding.login.text.toString(), binding.password.text.toString())
        }

        binding.login.doAfterTextChanged {
            model.UpdateLogin(
                binding.login.text.toString(),
                binding.password.text.toString()
            )
        }
        binding.password.doAfterTextChanged {
            model.UpdateLogin(
                binding.login.text.toString(),
                binding.password.text.toString()
            )
        }

        model.UpdateLogin(
            binding.login.text.toString(),
            binding.password.text.toString()
        )

    }

    private fun updateUi(state: LoginViewModelState) {
        when (state) {
            is LoginViewModelState.Success -> {
                binding.loginButton.isEnabled = state.loginButtonEnable
                Toast.makeText(context, "GOOD", Toast.LENGTH_SHORT).show()
                setToken(requireContext(),state.token)
                goToMainApp()
            }
            is LoginViewModelState.Failure -> {
                binding.loginButton.isEnabled = state.loginButtonEnable
                Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
            is LoginViewModelState.UpdateLogin -> {
                binding.loginButton.isEnabled = state.loginButtonEnable
            }
        }
    }

    private fun goToMainApp() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToMenuFragment()
        findNavController().navigate(action)
    }

}