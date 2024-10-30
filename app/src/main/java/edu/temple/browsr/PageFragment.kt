package edu.temple.browsr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
var Url=""
class PageFragment : Fragment() {
    private lateinit var webView: WebView
    private lateinit var webViewModel: WebViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewModel = ViewModelProvider(requireActivity())[WebViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout= inflater.inflate(R.layout.fragment_page, container, false)
        webView= layout.findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.let {
                    // Update ViewModel only if URL has changed
                    if (webViewModel.get().value != it) {
                        webViewModel.updateUrl(it)
                    }
                }
            }

        }
        webViewModel.get().observe(viewLifecycleOwner) { newUrl ->
            if (newUrl != webView.url) {  // Only load if the URL is different
                webView.loadUrl(newUrl)
            }
        }
        //webView.loadUrl()
        return layout
    }
    fun goBack() {
        if (webView.canGoBack()) {
            webView.goBack()
            //Url = webView.url.toString()
        }
    }

    fun goForward() {
        if (webView.canGoForward()) {
            webView.goForward()
            //Url=webView.url.toString()
        }
    }



}