package com.example.retrofit1

import com.google.gson.annotations.SerializedName

class Post(val userId : Int = 0,
           val title : String? = null,
           @SerializedName("body") val text: String? = null)
{
    val id = 0
}

/*
package com.codinginflow.retrofitexample;
import com.google.gson.annotations.SerializedName;
public class Post {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
}
*/
