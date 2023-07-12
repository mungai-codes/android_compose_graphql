package com.mungai.androidcomposegraphql.di

import com.apollographql.apollo3.ApolloClient
import com.mungai.androidcomposegraphql.data.CharactersClientImpl
import com.mungai.androidcomposegraphql.domain.CharactersClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun providesCharactersClient(apolloClient: ApolloClient): CharactersClient {
        return CharactersClientImpl(apolloClient)
    }
}