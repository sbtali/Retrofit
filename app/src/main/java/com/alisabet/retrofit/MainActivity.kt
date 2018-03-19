package com.alisabet.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    //1.add these libraries : 'com.squareup.retrofit2:retrofit:2.4.0' and 'com.squareup.retrofit2:converter-gson:2.4.0'
    //2.make a model class (called Category)
    //3.make CategoryResponse class
    //4.make APIInterface interface
    //5.make callWebService in activity and call it
    //6.get internet permission

   companion object {
       lateinit var textview : TextView
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.textview)
        callWebService()
    }



    fun callWebService() {
        val apiService = APIInterface.create()
        val call = apiService.getCategoryDetails()
        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: retrofit2.Response<CategoryResponse>?) {
                if (response != null) {
                    var list: List<Category> = response.body()!!.categories!!
                    var msg: String = ""
                    for (item: Category in list.iterator()) {
                        msg = msg + item.title + "\n"
                    }
                    textview.text = msg
                }

            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                textview.text = "fail"
            }
        })
    }


}
