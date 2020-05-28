package Interface;

import com.google.gson.JsonArray;

import java.util.List;

import Modelo.Posts;
import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    //@GET("posts")
    @POST("initial_load")
    Call<Posts> getPosts();
}