package com.example.mylibrary

import com.example.retrofitdemonew.AlbumList
import com.example.retrofitdemonew.AlbumListItem
import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<AlbumList>

    @GET("/albums")
    suspend fun getAlbumssorted(@Query("userId") userid:Int) : Response<AlbumList>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id")albumid:Int) :Response<AlbumListItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumListItem) : Response<AlbumListItem>
}