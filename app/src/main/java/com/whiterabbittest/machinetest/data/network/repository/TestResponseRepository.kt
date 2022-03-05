package com.whiterabbittest.machinetest.data.network.repository


import com.mlm.recharege.di.OnSuccess
import com.whiterabbittest.machinetest.data.network.api.response.ResponseTest
import com.whiterabbittest.machinetest.data.network.api.service.TestRequestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface TestResponseRepository {
    suspend fun reqGetResponse (
        onSuccess: OnSuccess<ResponseTest>,
    )

    companion object Factory{
        fun create(api : TestRequestApi) : TestResponseRepository = TestResponseRepositoryImpl(api)
    }


}
class TestResponseRepositoryImpl(val api: TestRequestApi) : TestResponseRepository {
    override suspend fun reqGetResponse (
        onSuccess: OnSuccess<ResponseTest>,

    ) {


        withContext(Dispatchers.IO)
        {
            try {

                val response = api.reqNotificationSettings()
                if(response.isSuccessful)
                {
                    response.body().let {

                            withContext(Dispatchers.IO)
                            {
                                onSuccess(it!!)
                            }


                    }
                }

            }catch (e: Exception)
            {

            }
        }
    }

}