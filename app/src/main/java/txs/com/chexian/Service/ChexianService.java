package txs.com.chexian.Service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import txs.com.chexian.bean.User;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ChexianService {
    // @GET("User/register")


    //Call<Integer> addReviews(@Body User user);
    //Call<Integer> addReviews(@Query("userName") String userName, @Query("passWord") String passWord
    /// , @Query("phone") String phone);
    //   http://139.199.182.242:8080/AutoInsurance/User/login
    // User/register?userName=bbb&passWord=bbbb&phone=12311111&eMail=111@111
   @FormUrlEncoded
    @POST("User/register")
    Call<Integer> register(
            @Field("usr_userName") String userName,
            @Field("usr_passWord") String passWord,
            @Field("usr_phone") String phone);
   /* @FormUrlEncoded
    @POST("User/register")
    Call<Integer> register(
            @Body User user);
*/



@FormUrlEncoded
    @POST("User/login")
    Call<User> login(
            @Field("loginName") String loginName,
            @Field("passWord") String passWord,
            @Field("loginFlag") String loginFlag

           );
    @FormUrlEncoded
    @POST("User/validateInfo")
    Call<String> searchUser(
            @Field("valInfo") String valInfo,
            @Field("flag") String flag
    );

}
