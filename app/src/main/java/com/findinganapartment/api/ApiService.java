package com.findinganapartment.api;



import com.findinganapartment.models.EditProfilePojo;
import com.findinganapartment.models.PropertyPojo;
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

    @Multipart
    @POST("rental/addproperty.php?")
    Call<ResponseData> add_property(
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

    @GET("rental/adminlogin.php?")
    Call<ResponseData> adminlogin(
            @Query("email") String email,
            @Query("pass") String pwd
    );


    @GET("rental/forgotPassword.php")
    Call<ResponseData> forgotPassword
            (

                    @Query("emailid") String emailid
            );

    @GET("/rental/getproperties.php")
    Call<List<PropertyPojo>> getproperties();


}
