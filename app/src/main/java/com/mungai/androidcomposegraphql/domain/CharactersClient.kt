package com.mungai.androidcomposegraphql.domain

interface CharactersClient {

    suspend fun getCharacters() : List<Character>
}