package com.example.toastermessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.mylibrary.AlbumService
import com.example.mylibrary.RetrofitInstance
import com.example.retrofitdemonew.AlbumList
import com.example.retrofitdemonew.AlbumListItem
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retservice : AlbumService
    lateinit var  textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.tvvalues)
        retservice= RetrofitInstance.getRetrofitInstance()
            .create(AlbumService::class.java)
        getRequestwithQueryParameters()
    }

    private fun getRequestwithQueryParameters(){

        val responseLiveData : LiveData<Response<AlbumList>> = liveData {
            val response :Response<AlbumList> =retservice.getAlbumssorted(3)

            emit(response)
        }


        responseLiveData.observe(this, Observer {

            val albumsList : MutableListIterator<AlbumListItem>? =it.body()?.listIterator()

            if(albumsList !=null){

                while (albumsList.hasNext()){

                    val albumsItem : AlbumListItem = albumsList.next()
                    Log.v("albumsItem", albumsItem.title)
                    val resultString=" "+" Album title: ${albumsItem.title}"+"\n"+
                            " "+" Album id: ${albumsItem.id}"+"\n"+
                            " "+" User id: ${albumsItem.userId}"+"\n\n\n"

                    textView?.append(resultString)
                }
            }
        })
    }
}