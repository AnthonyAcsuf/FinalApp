package com.example.finalapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

private const val TAG = "GroupListFragment"
class GroupListFragment : Fragment() {

    interface Callbacks {
        fun onGroupSelected(group: Group)
    }
    private var callbacks: Callbacks? = null

    private lateinit var groupRecyclerView: RecyclerView
    private var adapter: GroupAdapter? = null
    private val groupListViewModel: GroupListViewModel by lazy {
        ViewModelProviders.of(this).get(GroupListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group_list, container, false)
        groupRecyclerView = view.findViewById(R.id.group_recycler_view) as RecyclerView;
        groupRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }


    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_group -> {
                val group = Group()
                groupListViewModel.addGroup(group)
                callbacks?.onGroupSelected(group)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    /*public fun test() {
        groupListViewModel.addTest();
    }*/

    public fun updateUI() {
        val groups = groupListViewModel.groups
        adapter = GroupAdapter(groups)
        groupRecyclerView.adapter = adapter
    }

    private inner class GroupHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var group: Group

        private val titleTextView: TextView = itemView.findViewById(R.id.group_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.group_date)

        private val finishedImageView: ImageView = itemView.findViewById(R.id.group_finished)
        //private val descriptionTextView: TextView = itemView.findViewById(R.id.group_description)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(group: Group) {
            this.group = group
            titleTextView.text = this.group.title
            dateTextView.text = this.group.date.toString()
            finishedImageView.visibility = if (group.isFinished) {
                View.VISIBLE
            } else {
                View.GONE
            }
            //descriptionTextView.text = this.group.description
            //memberNumberTextView.text = this.group.memberCount.toString()
        }

        override fun onClick(v: View?) {
            callbacks?.onGroupSelected(group)
        }
    }

    private inner class GroupAdapter(var groups: List<Group>)
        : RecyclerView.Adapter<GroupHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GroupHolder {
            val view = layoutInflater.inflate(R.layout.list_item_group, parent, false)
            return GroupHolder(view)
        }
        override fun getItemCount() = groups.size
        override fun onBindViewHolder(holder: GroupHolder, position: Int) {
            val group = groups[position]
            holder.bind(group)
        }
    }
}
