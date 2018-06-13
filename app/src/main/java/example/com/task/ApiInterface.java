package example.com.task;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users")
    Call<Users> getUsers(

            @Query("page") int pageIndex
    );
}
