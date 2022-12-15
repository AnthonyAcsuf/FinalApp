package com.example.finalapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.finalapp.placeholder.PlaceholderContent
import java.io.Serializable
import java.util.*

/**
 * A fragment representing a list of Items.
 */
private const val TAG = "GroupFragment"
private const val ARG_GROUP = "group_id"
class GroupFragment : Fragment() {

    private lateinit var group: Group
    private lateinit var titleField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var dateViewText: TextView
    private lateinit var finishedCheckBox: CheckBox
    //private lateinit var memberCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        group = Group();

        this.group = arguments?.getSerializable(ARG_GROUP) as Group
        Log.d(TAG, "args bundle group title: ${group.title}")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group, container, false)
        titleField = view.findViewById(R.id.group_title) as EditText
        descriptionField = view.findViewById(R.id.group_description) as EditText
        dateViewText = view.findViewById(R.id.group_date) as TextView
        finishedCheckBox = view.findViewById(R.id.group_solved) as CheckBox
        //memberCount = view.findViewById(R.id.group_member_number) as TextView
        Log.d(TAG, "onCreateView: ${group.title}")

        return view
    }



    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This space intentionally left blank
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                group.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }
        titleField.addTextChangedListener(titleWatcher)
        finishedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                group.isFinished = isChecked
            }
        }


        val descriptionWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This space intentionally left blank
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                group.description = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }
        descriptionField.addTextChangedListener(descriptionWatcher)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        Log.d("from UI", "get title: ${group.title}")
        Log.d("from UI", "get description: ${group.description}")
        titleField.setText(group.title)
        descriptionField.setText(group.description)
        finishedCheckBox.isChecked = group.isFinished

    }

    companion object {

        fun newInstance(group: Group): GroupFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GROUP, group)
            }
            return GroupFragment().apply {
                arguments = args
            }
        }

    }
}