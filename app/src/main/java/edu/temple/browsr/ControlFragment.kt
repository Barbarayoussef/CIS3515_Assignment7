package edu.temple.browsr

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
                (activity as MainActivity).supportFragmentManager.findFragmentById(R.id.page)?.let { fragment ->
                    if (fragment is PageFragment) {
                        fragment.goBack()
                        //urlText.text=Editable.Factory.getInstance().newEditable(Url)
                    }
                }
            }
        }
        forwardImage= layout.findViewById<ImageView>(R.id.forward).apply {
            setOnClickListener{
                (activity as MainActivity).supportFragmentManager.findFragmentById(R.id.page)?.let { fragment ->
                    if (fragment is PageFragment) {
                        fragment.goForward()
                        //urlText.text=Editable.Factory.getInstance().newEditable(Url)
                    }
                }
            }
        }
        goImage= layout.findViewById<ImageView>(R.id.go).apply {
            setOnClickListener{
                webViewModel.updateUrl(goTO())
            }
        }
        urlText= layout.findViewById<EditText>(R.id.UrlText)
        webViewModel.get().observe(requireActivity()) { newUrl ->
            urlText.text= Editable.Factory.getInstance().newEditable(newUrl)
        }
        return layout
    }





       fun goTO(): String{
        Url=urlText.text.toString()
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




