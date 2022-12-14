package com.example.fleetech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.fleetech.databinding.ListItemBinding
import com.example.fleetech.databinding.PaymentChildItemBinding
import com.example.fleetech.databinding.PmtHeaderLayoutBinding
import com.example.fleetech.retrofit.response.JDocument
import com.example.fleetech.retrofit.response.JMyPayment

//class ExpandableListAdapter internal constructor(
//    private val context: Context,
//    private val titleList: List<String>,
//    private val dataList: List<JMyPayment>
//) : BaseExpandableListAdapter() {
//
//    private val inflater: LayoutInflater = LayoutInflater.from(context)
//    private lateinit var groupBinding: PmtHeaderLayoutBinding
//    private lateinit var itemBinding: PaymentChildItemBinding
//
//    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
//        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
//    }
//
//    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
//        return expandedListPosition.toLong()
//    }
//
//    override fun getChildView(
//        listPosition: Int,
//        expandedListPosition: Int,
//        isLastChild: Boolean,
//        view: View?,
//        parent: ViewGroup
//    ): View {
//        var convertView = view
//        val holder: ItemViewHolder
//        if (convertView == null) {
//            itemBinding = PaymentChildItemBinding.inflate(inflater)
//            convertView = itemBinding.root
//            holder = ItemViewHolder()
//            holder.label = itemBinding.expandedListItem
//            convertView.tag = holder
//        } else {
//            holder = convertView.tag as ItemViewHolder
//        }
//        val expandedListText = getChild(listPosition, expandedListPosition) as String
//        holder.label!!.text = expandedListText
//        return convertView
//    }
//
//    override fun getChildrenCount(listPosition: Int): Int {
//        return this.dataList[this.titleList[listPosition]]!!.size
//    }
//
//    override fun getGroup(listPosition: Int): Any {
//        return this.titleList[listPosition]
//    }
//
//    override fun getGroupCount(): Int {
//        return this.titleList.size
//    }
//
//    override fun getGroupId(listPosition: Int): Long {
//        return listPosition.toLong()
//    }
//
//    override fun getGroupView(
//        listPosition: Int,
//        isExpanded: Boolean,
//        view: View?,
//        parent: ViewGroup
//    ): View {
//        var convertView = view
//        val holder: GroupViewHolder
//        if (convertView == null) {
//            groupBinding = PmtHeaderLayoutBinding.inflate(inflater)
//            convertView = groupBinding.root
//            holder = GroupViewHolder()
//            holder.label = groupBinding.headerText
//            convertView.tag = holder
//        } else {
//            holder = convertView.tag as GroupViewHolder
//        }
//        val listTitle = getGroup(listPosition) as String
//        holder.label!!.text = listTitle
//        return convertView
//    }
//
//    override fun hasStableIds(): Boolean {
//        return false
//    }
//
//    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
//        return true
//    }
//
//    inner class ItemViewHolder {
//        internal var label: TextView? = null
//    }
//
//    inner class GroupViewHolder {
//        internal var label: TextView? = null
//    }

//}