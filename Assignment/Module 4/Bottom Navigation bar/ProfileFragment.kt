package com.example.newbundle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileFragment : Fragment() {

    lateinit var txtReceivedData: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        txtReceivedData = view.findViewById(R.id.txtReceivedData)

        var data = arguments?.getString("key")
        if (data != null) {
            txtReceivedData.text = data
        }

        return view
    }
}