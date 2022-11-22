package com.example.aplictrianrect.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aplictrianrect.R

class CreditosFragment : Fragment() {

    lateinit var clCreditos: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creditos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clCreditos = view.findViewById(R.id.clCreditos)

        clCreditos.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit();
            clCreditos.isClickable = false
        }
    }
}