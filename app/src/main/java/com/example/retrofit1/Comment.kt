package com.example.retrofit1

import com.google.gson.annotations.SerializedName

class Comment {
    val postId = 0
    val id = 0
    val name: String? = null
    val email: String? = null

    @SerializedName("body")
    val text: String? = null
}
/*
package com.codinginflow.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int postId;

    private int id;

    private String name;

    private String email;

    @SerializedName("body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
 */