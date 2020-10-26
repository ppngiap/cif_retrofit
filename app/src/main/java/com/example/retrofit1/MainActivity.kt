package com.example.retrofit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var textViewResult: TextView
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.text_view_result)
        textViewResult.text = ""

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        deletePost()
//        updatePostWithPatch()
//        updatePostWithPut()
//        createPostWithMap()
//        createPostWithFields()
//        createPost()
//        getCommentsWithId()
//        getCommentsWithUrl()
//        getPostsWithArray()
//        getPostsWithMap()
//        getPosts()
    }

    private fun deletePost() {
        val call = jsonPlaceHolderApi.deletePost(2)
        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                textViewResult.text = "Code: " + response.code()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                textViewResult.text = "Code: " + t.message
            }
        })
    }

    private fun updatePostWithPatch() {
        val post = Post(20, null, "updatePostWithPut, should see title not null")
        val call: Call<Post> = jsonPlaceHolderApi.patchPost(20, post)
        call.enqueue(createPostCallback())
    }

    private fun updatePostWithPut() {
        val post = Post(20, null, "updatePostWithPut, should see title null")
        val call: Call<Post> = jsonPlaceHolderApi.putPost(20, post)
        call.enqueue(createPostCallback())
    }

    private fun createPostWithMap() {
        val map = HashMap<String, String>()
        map.put("userId", "3")
        map.put("title", "New Title")
        map.put("body", "Hello from map")
        val call: Call<Post> = jsonPlaceHolderApi.createPost(map)
        call.enqueue(createPostCallback())
    }

    private fun createPostWithFields() {
        val call: Call<Post> = jsonPlaceHolderApi.createPost(2, "New Title", "Hello from fields")
        call.enqueue(createPostCallback())
    }

    private fun createPost() {
        val post = Post(2, "New Titile", "Hello")
        val call: Call<Post> = jsonPlaceHolderApi.createPost(post)
        call.enqueue(createPostCallback())
    }

    private fun getCommentsWithId() {
        val call = jsonPlaceHolderApi.comments(3)
        call!!.enqueue(createCommentCallback())
    }

    private fun getCommentsWithUrl() {
        val call = jsonPlaceHolderApi
            .comments("https://jsonplaceholder.typicode.com/posts/3/comments");
        call!!.enqueue(createCommentCallback())
    }

    private fun getPostsWithArray() {
        val ids : Array<Int?> = arrayOf(1, 4)
        val call = jsonPlaceHolderApi.posts(ids, "id", "desc")
        call!!.enqueue(createGetPostCallback())
    }

    private fun getPostsWithMap() {
        val map = HashMap<String?, String?>()
        map.put("userId", "1")
        map.put("_sort", "id")
        map.put("_order", "desc")
        val call = jsonPlaceHolderApi.posts(map)
        call!!.enqueue(createGetPostCallback())
    }

    private fun getPosts() {
        val call = jsonPlaceHolderApi.posts()
        call!!.enqueue(createGetPostCallback())
    }

    private fun createCommentCallback(): Callback<List<Comment?>?> {
        return object: Callback<List<Comment?>?> {
            override fun onResponse(call: Call<List<Comment?>?>, response: Response<List<Comment?>?>) {
                if (!response.isSuccessful) {
                    textViewResult.text = "Code: " + response.code()
                    return
                }
                val posts = response.body()!!
                for (post in posts) {
                    var content = ""
                    content += "ID: ${post?.id}\n"
                    content += "Post ID: ${post?.postId}\n"
                    content += "Name: ${post?.name}\n"
                    content += "Email: ${post?.email}\n"
                    content += "Text: ${post?.text}\n"

                    textViewResult.append(content)
                }
            }

            override fun onFailure(call: Call<List<Comment?>?>, t: Throwable) {
                textViewResult.text = t.message
            }
        }
    }

    private fun createPostCallback(): Callback<Post?> {
       return object: Callback<Post?> {
           override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
               if (!response.isSuccessful) {
                   textViewResult.text = "Code: " + response.code()
                   return
               }
               val post = response.body()!!
               var content = "Code: ${response.code()}\n"
               content += "ID: ${post.id}\n"
               content += "User ID: ${post.userId}\n"
               content += "Title: ${post?.title}\n"
               content += "Text: ${post?.text}\n"

               textViewResult.append(content)
           }

           override fun onFailure(call: Call<Post?>, t: Throwable) {
               textViewResult.text = t.message
           }
       }
    }

    private fun createGetPostCallback(): Callback<List<Post?>?> {
        return object: Callback<List<Post?>?> {
            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                if (!response.isSuccessful) {
                    textViewResult.text = "Code: " + response.code()
                    return
                }
                val posts = response.body()!!
                for (post in posts) {
                    var content = "Code: ${response.code()}\n"
                    content += "ID: ${post?.id}\n"
                    content += "User ID: ${post?.userId}\n"
                    content += "Title: ${post?.title}\n"
                    content += "Text: ${post?.text}\n"

                    textViewResult.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
                textViewResult.text = t.message
            }
        }
    }
}


/*
package com.codinginflow.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
 */