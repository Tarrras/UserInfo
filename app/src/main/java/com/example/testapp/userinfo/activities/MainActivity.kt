package com.example.testapp.userinfo.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.example.testapp.userinfo.R
import com.example.testapp.userinfo.fragments.ListFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_fragment = ListFragment()
        val ft =supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_container,list_fragment).addToBackStack(null).commit()
    }
}
