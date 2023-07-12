package com.mungai.androidcomposegraphql.data

import com.apollographql.apollo3.ApolloClient
import com.mungai.CharactersQuery
import com.mungai.androidcomposegraphql.domain.Character
import com.mungai.androidcomposegraphql.domain.CharactersClient

class CharactersClientImpl(
    private val apolloClient: ApolloClient
) : CharactersClient {

    override suspend fun getCharacters(): List<Character> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.map { character ->
                Character(
                    id = character?.id.orEmpty(),
                    name = character?.name.orEmpty(),
                    image = character?.image.orEmpty()
                )
            } ?: emptyList()

    }
}