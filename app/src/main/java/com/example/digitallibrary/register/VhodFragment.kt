package com.example.digitallibrary.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.digitallibrary.R
import com.example.digitallibrary.databinding.FragmentVhodBinding
import com.example.digitallibrary.offline.MenuOfflineActivity
import com.google.firebase.auth.FirebaseAuth

class VhodFragment : Fragment() {
    private var _binding: FragmentVhodBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVhodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.vhodButton.setOnClickListener {
            val email = binding.emailVhodText.text.toString().trim()
            val password = binding.passwordVhodText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Создаем Intent и переходим в MenuOfflineActivity
                        val intent = Intent(requireActivity(), MenuOfflineActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Очистка стека задач
                        startActivity(intent)
                        requireActivity().finish() // Закрыть текущую активность
                    } else {
                        Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toForgotPassword.setOnClickListener {
            view.findNavController().navigate(R.id.action_vhodFragment_to_forgotFragment)
        }

        binding.toRegisterButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_vhodFragment_to_signupFragment)
        }
    }

    private fun compareLogin(login: String) {
        if (login.isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()) {
            Toast.makeText(requireContext(), "Неверный адрес электронной почты", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.sendPasswordResetEmail(login).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Проверьте свою электронную почту", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
