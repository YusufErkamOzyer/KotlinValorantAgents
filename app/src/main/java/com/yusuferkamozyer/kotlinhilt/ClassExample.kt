package com.yusuferkamozyer.kotlinhilt

import com.google.gson.Gson
import com.yusuferkamozyer.kotlinhilt.repository.AgentRepository
import com.yusuferkamozyer.kotlinhilt.repository.AgentRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.util.Constants.BASE_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@InstallIn(ViewModelComponent::class)
@Module
class myModule(){
    /*@Provides
    @ActivityScoped
    fun providerFunc():MyInterface{
        return InterfaceImplementer()
    }*/
    @Provides
    @ViewModelScoped
    fun provideRetrofit():AgentsAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_VALUE).addConverterFactory(GsonConverterFactory.create())
            .build().create(AgentsAPI::class.java)
    }
    @Provides
    @ViewModelScoped
    fun provideRepository(api:AgentsAPI):AgentRepository{
        return AgentRepositoryImp(api)
    }

}