package com.catnip.introsliderpage.ui.formfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.catnip.introsliderpage.R
import com.catnip.introsliderpage.databinding.FragmentFormBinding


class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        binding.btnSetName.setOnClickListener {
            //todo : navigate to menu, bring data name to menu page or using sharedpreference
            val name = binding.etName.text.toString()
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
        }
    }

}