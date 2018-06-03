package com.noman.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.noman.mvvm.view.UserListFragment
import com.noman.mvvm.viewmodel.UserListViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, UserListFragment()).commit()
        }

    }


}