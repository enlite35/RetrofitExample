package ru.vologda.retrofitexample;

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
   TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // я запускаю сервак в отдельном потоке, может можно и без этого, и это там встроено хз
        Thread thread = new Thread(){
            @Override
            public void run() {
                new  MyServer(8080);
            }
        };
        thread.start();

        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        //getPosts();
       // createPost();
        myLogin();
    }
// здесь я всё делаю стандартно для ретрофита. указываю урл сервака и запускаю метод
    // сам запрос checkUser  прописан в интерфейсе jsonPlaceHolderAPI
    private void myLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<String> call = jsonPlaceHolderAPI.checkUser("user1", "123");
        //асинхронный вызов запроса
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    tv.setText("Code: "+response.code());
                    return;
                }
                //если всё нормально то нам вернётся строка которую мы и покажем на экране
                tv.append("\n "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
               tv.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Post post = new Post(23, "New title", "asdfa");
        Call<Post> call = jsonPlaceHolderAPI.createPost(22,"MyTitle","Mytext");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    tv.setText("Code: "+response.code());
                    return;
                }
                Post postResponse = response.body();
                String s ="";
                s+= response.code()+"\n";
                s+="ID: "+postResponse.getId()+"\n";
                s+="User_ID: "+postResponse.getUserId()+"\n";
                s+="Title: "+postResponse.getTitle()+"\n";
                s+="Text: "+postResponse.getText()+"\n\n";
                tv.append(s);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(1);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    tv.setText("Code: "+response.code());
                    return;
                }
                List<Post> posts = response.body();
                for(Post post: posts){
                    String s ="";
                    s+="ID: "+post.getId()+"\n";
                    s+="User_ID: "+post.getUserId()+"\n";
                    s+="Title: "+post.getTitle()+"\n";
                    s+="Text: "+post.getText()+"\n\n";
                    tv.append(s);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
}
