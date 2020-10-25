package com.example.retrofit1

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyTest {
    fun test() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApi::class.java
        )
        val call = jsonPlaceHolderApi.posts()
        call!!.enqueue(object : Callback<List<Post?>?> {
            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                if (!response.isSuccessful) {
                    return
                }
                val posts = response.body()!!
                for (post in posts) {
                    var content = ""
                    content += """
                        ID: ${post?.id}
                        """.trimIndent()
                    content += """
                        User ID: ${post?.userId}
                        """.trimIndent()
                    content += """
                        Title: ${post?.title}
                        """.trimIndent()
                    content += """
                        Text: ${post?.text}
                        """.trimIndent()
                }
            }

            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {}
        })
    }
}