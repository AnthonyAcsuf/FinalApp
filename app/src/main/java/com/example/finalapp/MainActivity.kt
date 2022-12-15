package com.example.finalapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array.newInstance
import java.util.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), GroupListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val currentFragment = supportFragmentManager.findFragmentById(R.id.groupsFragment_container)
        val fragment = GroupListFragment()
        if (currentFragment == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.groupsFragment_container, fragment)
                .commit()
        }

    }

    override fun onGroupSelected(group: Group) {


        //Log.d(TAG, "MainActivity.onGroupSelected: ${group.groupName}")
        val fragment = GroupFragment.newInstance(group)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.groupsFragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }


}