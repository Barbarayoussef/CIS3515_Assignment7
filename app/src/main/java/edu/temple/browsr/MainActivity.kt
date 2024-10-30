package edu.temple.browsr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),ControlFragment.NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.page, PageFragment())
                .commit()
        }
    }

    override fun goBack() {
        ( supportFragmentManager
            .findFragmentById(R.id.page) as PageFragment ).goBack()
    }

    override fun goForward() {
        ( supportFragmentManager
            .findFragmentById(R.id.page) as PageFragment ).goForward()
    }

}