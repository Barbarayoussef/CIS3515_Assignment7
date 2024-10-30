package edu.temple.browsr

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider


class ControlFragment : Fragment() {
    lateinit var backImage: ImageView
    lateinit var forwardImage: ImageView
    lateinit var goImage: ImageView
    lateinit var urlText:EditText
    lateinit var Url: String
    lateinit var webViewModel:WebViewModel
    private lateinit var navigationInterface:NavigationInterface


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationInterface) {
            navigationInterface = context
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewModel = ViewModelProvider(requireActivity())[WebViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout =inflater.inflate(R.layout.fragment_control, container, false)
        backImage= layout.findViewById<ImageView>(R.id.back).apply {
            setOnClickListener{
                navigationInterface.goBack()

            }
        }
        forwardImage= layout.findViewById<ImageView>(R.id.forward).apply {
            setOnClickListener{
                navigationInterface.goForward()
            }
        }
        goImage= layout.findViewById<ImageView>(R.id.go).apply {
            setOnClickListener{
                webViewModel.updateUrl(goTO())
            }
        }
        urlText= layout.findViewById<EditText>(R.id.UrlText)
        webViewModel.get().observe(requireActivity()) { newUrl ->
            urlText.setText(newUrl)
        }
        return layout
    }

    interface NavigationInterface {
        fun goBack()
        fun goForward()
    }

       fun goTO(): String{
           Url = urlText.text.toString()
           if (Url.isNotBlank()) {
               if (!Url.contains(".") || Url.contains(" ")) {
                   Url= "https://duckduckgo.com/?q=$Url"
               } else if (!Url.startsWith("http://") && !Url.startsWith("https://")) {
                   Url= "https://$Url"
               } else {
                   Url
               }
           }
           return Url

       }

    }




