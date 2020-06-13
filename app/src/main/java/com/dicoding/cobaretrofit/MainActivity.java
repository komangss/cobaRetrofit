package com.dicoding.cobaretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);

        // with this retrofit instance here we can now create our JsonPlaceHolderApi
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()) // this how we define that we want to parse the json
                .build();

//        remember this is not interface, this is the job of retrofit
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts();
//        getComments();
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComment(3);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("Code : " + response.code());
                    return;
                }

                List<Comment> comments = response.body();
                ;

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void getPosts() {

//        to execute the network request, we have to use call object that you write earlier
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{2, 3, 6}, "id", "desc"); // if you don't want use some of this parameter, you can set it to null
//        but we cant pass null to primitive type of variable (int) then change to Integer

//        we don't use call.execute() // because this is asynchronous, we run in main thread and it will freeze our app
//        but we don't need to create new thread, we just use retrofit method call enqueue
        call.enqueue(new Callback<List<Post>>() { // we have to pass a new Callback
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("not successful, code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

}

