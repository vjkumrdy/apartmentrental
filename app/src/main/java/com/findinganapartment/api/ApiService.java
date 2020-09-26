package com.findinganapartment.api;



import com.findinganapartment.models.EditProfilePojo;
import com.findinganapartment.models.ResponseData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    @Multipart
    @POST("rental/user_registration.php?")
    Call<ResponseData> userRegistration(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    /*@GET("rental/user_registration.php?")
    Call<ResponseData> userRegistration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phonenumber") String rental,
            @Query("role") String role,
            @Query("pwd") String pwd);*/


    @GET("/rental/user_login.php?")
    Call<ResponseData> userLogin(
            @Query("email") String email,
            @Query("pwd") String pwd
    );


    @GET("Jobsearch/forgotPassword.php")
    Call<ResponseData> forgotPassword
            (

                    @Query("emailid") String emailid
            );

    @GET("SmartTowingSystem/getUserProfile.php")
    Call<List<EditProfilePojo>> getMyProfile(@Query("uname") String email);

    @GET("SmartTowingSystem/update_user_profile.php")
    Call<ResponseData> update_user_profile(

            @Query("first_name") String fname,
            @Query("phonenumber") String phone,
            @Query("emailid") String email,
            @Query("pwd") String pwd
    );

  /*  @GET("VideoStreaming/home.json")
    Call<HomeScreenPojo> home();

    @GET("VideoStreaming/category.json")
    Call<List<ViewmoreListPojo>> viewmorecategory();


    @GET("/VideoStreaming/get_category_details.php?")
    Call<List<CategoryDetailsPojo>> get_category_details(@Query("slug") String slug);

    @GET("VideoStreaming/update_exp_date.php?")
    Call<ResponseData> update_exp_date(
            @Query("uname") String uname,
            @Query("exp_date") LocalDate exp_date);*/

}
