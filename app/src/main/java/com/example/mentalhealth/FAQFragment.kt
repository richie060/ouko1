package com.example.mentalhealth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import mentalhealth.R


class FAQFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_a_q, container, false)

        val game1 = view.findViewById<CardView>(R.id.cardviewgame1)
        val game2 = view.findViewById<CardView>(R.id.cardviewgame2)



        game1.setOnClickListener {
            val url = "https://play.google.com/store/apps/details?id=com.litesprite.sinaspritepro&hl=en&gl=US"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        game1.setOnClickListener {
            val url = "https://play.google.com/store/apps/details?id=com.finch.finch&hl=en_US&gl=US"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    return view
    }
}