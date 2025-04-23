package com.example.newbundle
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {

    lateinit var btnSendData: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        btnSendData = view.findViewById(R.id.btnSendData)

        btnSendData.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("key", "Home Fragment")

            var profileFragment = ProfileFragment()
            profileFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, profileFragment)?.commit()
        }

        return view
    }
}