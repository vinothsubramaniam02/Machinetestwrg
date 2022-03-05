/*
 * *
 *  * Created by Nethaji on 27/6/20 1:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27/6/20 1:13 PM
 *
 */





import com.whiterabbittest.machinetest.data.network.api.service.TestRequestApi
import com.whiterabbittest.machinetest.data.network.repository.TestResponseRepository
import com.whiterabbittest.machinetest.data.network.viewmodel.TestResponseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Networking modules here
 * Must be a singleton
 * Also, using the default overload methods
 **/
val NETWORKING_MODULE = module {
    single { HttpClientManager.newInstance(androidContext()) }
    single { get<HttpClientManager>().createApi<TestRequestApi>() }


}

/**
 * Repository modules here
 * Must be a singleton
 **/
val REPOSITORY_MODULE = module {

    single { TestResponseRepository.create(get()) }


}

/**
 * ViewModel modules here
 **/
val VIEW_MODEL_MODULE = module {
    viewModel { TestResponseViewModel(get(), androidContext()) }


}

