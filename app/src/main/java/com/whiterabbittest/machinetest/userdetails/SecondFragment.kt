package com.whiterabbittest.machinetest.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.hr.data.network.di.loadCircularImage
import com.whiterabbittest.machinetest.R
import com.whiterabbittest.machinetest.databinding.FragmentSecondBinding
import com.whiterabbittest.machinetest.db.AppDB
import com.whiterabbittest.machinetest.utlity.SessionSave
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(),LifecycleObserver {

    private var db: AppDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            db = AppDB.getDatabaseClient(requireContext())
            val data = db!!.userInfoTable()
                .getUserDetails(SessionSave.getSession("user_id", requireContext()))
            name.setText(data[0].name)
            user_name.setText(data[0].username)

            email_id.setText(data[0].email)

            street.setText(data[0].street)
            suite.setText(data[0].suite)
            city.setText(data[0].city)
            zipcode.setText(data[0].zipcode)
            phone.setText(data[0].phone)
            website.setText(data[0].website)

            data.get(0).profile_image?.let { profile_image_employee.loadCircularImage(it) }

        }
    }
}