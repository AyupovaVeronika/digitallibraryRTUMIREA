package com.example.digitallibrary.offline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.digitallibrary.R
import com.example.digitallibrary.databinding.FragmentMainMenuOfflineBinding
import com.example.digitallibrary.fragment.reading.ListReadFragment


class MainMenuOfflineFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuOfflineBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
// Создание экземпляра класса Fragment1Binding и связывание его с разметкой фрагмента
        binding = FragmentMainMenuOfflineBinding.inflate(inflater, container, false)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ListReadFragment())
                .commit()
        }

        // Возвращение корневого View разметки фрагмента
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
            } else {
                requireActivity().finish()
            }
        }
    }
}