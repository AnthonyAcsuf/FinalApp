package com.example.finalapp

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "GroupListViewModel"
class GroupListViewModel : ViewModel() {
    var groups = mutableListOf<Group>();

    fun addGroup(group: Group) {
        groups.add(group);
    }
}
