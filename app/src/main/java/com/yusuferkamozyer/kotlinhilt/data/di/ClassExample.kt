package com.yusuferkamozyer.kotlinhilt.data.di

import android.content.Context
import androidx.room.Room
import com.yusuferkamozyer.kotlinhilt.data.local.AgentFavDatabase
import com.yusuferkamozyer.kotlinhilt.data.local.AgentDetailDao
import com.yusuferkamozyer.kotlinhilt.data.local.repository.AgentLocalRepository
import com.yusuferkamozyer.kotlinhilt.data.local.repository.AgentLocalRepositoryImp
import com.yusuferkamozyer.kotlinhilt.data.remote.AgentsAPI
import com.yusuferkamozyer.kotlinhilt.data.remote.repository.AgentRemoteRepository
import com.yusuferkamozyer.kotlinhilt.data.remote.repository.AgentRemoteRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.util.Constants.BASE_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
    fun provideRoomDatabase(@ApplicationContext context: Context):AgentFavDatabase{
        return Room.databaseBuilder(context,AgentFavDatabase::class.java,"AgentDatabase").build()
    }
    @Provides
    @ViewModelScoped
    fun provideRoomDataAccessObj(db:AgentFavDatabase):AgentDetailDao{
        return db.agentDetailDao()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): AgentsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_VALUE).addConverterFactory(GsonConverterFactory.create())
            .build().create(AgentsAPI::class.java)
    }
    @Provides
    @ViewModelScoped
    fun provideRepository(api: AgentsAPI): AgentRemoteRepository {
        return AgentRemoteRepositoryImp(api)
    }
    @Provides
    @ViewModelScoped
    fun provideLocalRepository(dao: AgentDetailDao): AgentLocalRepository {
        return AgentLocalRepositoryImp(dao)
    }

}