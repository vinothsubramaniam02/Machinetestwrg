package com.whiterabbittest.machinetest.data.network.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlm.recharege.di.OnSuccess
import com.whiterabbittest.machinetest.data.network.api.response.ResponseTest
import com.whiterabbittest.machinetest.data.network.repository.TestResponseRepository
import kotlinx.coroutines.launch

class TestResponseViewModel(
    private val notificationSettingsRepository: TestResponseRepository,
    val context: Context
) : ViewModel() {

    fun reqGetResponse(
        onSuccess: OnSuccess<ResponseTest>,

    ){
        viewModelScope.launch {
            notificationSettingsRepository.reqGetResponse(onSuccess)
        }
    }

}