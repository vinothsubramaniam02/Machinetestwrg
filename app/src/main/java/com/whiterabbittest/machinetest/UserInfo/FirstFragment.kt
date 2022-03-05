package com.whiterabbittest.machinetest.UserInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.whiterabbittest.machinetest.R
import com.whiterabbittest.machinetest.data.network.viewmodel.TestResponseViewModel
import com.whiterabbittest.machinetest.db.AppDB
import com.whiterabbittest.machinetest.localdb.UserInfoTable
import com.whiterabbittest.machinetest.utlity.SessionSave
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), LifecycleObserver,ShowUserDetails {
    private val testResponseViewModel by viewModel<TestResponseViewModel>()
    private var db: AppDB? = null
    private lateinit var showUserListAdapter: ShowUserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            db = AppDB.getDatabaseClient(requireContext())
            val data = db!!.userInfoTable().getAll()

            if(data.size==0)
            {
                requestGetUserList()
            }
            else
            {
                showUserDetails()
            }
        }

        search_user.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {

                if (s!!.length > 2) {
                    searchUserDetails(s)
                } else {
                    showUserDetails()
                }

                return false
            }
        })




    }

    private fun searchUserDetails(s: String) {
        lifecycleScope.launch {
            delay(500)
            CoroutineScope(Dispatchers.Main).launch {

                db = AppDB.getDatabaseClient(requireContext())
                val data = db!!.userInfoTable().getItemName(s!!)
                if (data.size!=0) {
                    showUserListAdapter = ShowUserListAdapter( this@FirstFragment,data)
                    user_list.adapter = showUserListAdapter
                    showUserListAdapter.notifyDataSetChanged()
                }



            }


        }
    }

    private fun requestGetUserList() {
        testResponseViewModel.reqGetResponse {
            it.forEach {
                CoroutineScope(Dispatchers.IO).launch {


                    db = AppDB.getDatabaseClient(requireContext())


                    val itemCount =
                        UserInfoTable(
                            0,
                            it.id.toString(),
                            it.name,
                            it.username,
                            it.email,
                            it.profile_image,
                            it.address.street,
                            it.address.suite,
                            it.address.city,
                            it.address.zipcode,
                            it.address.geo.lat,
                            it.address.geo.lng,
                            it.phone,
                            it.website,
                            "Testcompany",
                            "Testcompany",
                            "Testcompany"

                        )
                    db!!.userInfoTable().insertAll(itemCount)
                }
            }
            showUserDetails()

        }
    }

    private fun showUserDetails() {
        lifecycleScope.launch {
            delay(500)
            CoroutineScope(Dispatchers.Main).launch {

                db = AppDB.getDatabaseClient(requireContext())


                val data = db!!.userInfoTable().getAll()



                showUserListAdapter = ShowUserListAdapter(this@FirstFragment, data)
                user_list.adapter = showUserListAdapter
                showUserListAdapter.notifyDataSetChanged()


            }
        }    }

    override fun showUserDetails(_category: UserInfoTable) {
SessionSave.saveSession("user_id",_category.user_id,requireContext())
        findNavController().navigate(R.id.SecondFragment)
    }


}