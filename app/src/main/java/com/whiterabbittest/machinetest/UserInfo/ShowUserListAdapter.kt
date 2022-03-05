package com.whiterabbittest.machinetest.UserInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hr.data.network.di.loadCircularImage
import com.whiterabbittest.machinetest.R
import com.whiterabbittest.machinetest.localdb.UserInfoTable
import kotlinx.android.synthetic.main.user_list_item.view.*


class ShowUserListAdapter(
    val showUserDetails: ShowUserDetails,
    upComingList: List<UserInfoTable>
) :
    RecyclerView.Adapter<ShowUserListAdapter.CategoryHolder>() {

    val upComingList = upComingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = upComingList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindUi(position)
    }


    inner class CategoryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUi(position: Int) {
            view.apply {
                upComingList[position].let { _category ->

                    user_name.text = _category.name?.capitalize()
                    company_name.text = _category.company_name?.capitalize()
                    email.text = _category.email
                    _category.profile_image?.let { user_Image.loadCircularImage(it) }

                    main_lay.setOnClickListener {
                        showUserDetails.showUserDetails(_category)
                    }
                }


            }
        }
    }

}