package com.findinganapartment.api;

import com.findinganapartment.models.EditProfilePojo;
import com.findinganapartment.models.GetPhotosPojo;
import com.findinganapartment.models.MyRequestPojo;
import com.findinganapartment.models.PricePojo;
import com.findinganapartment.models.PropertyPojo;
import com.findinganapartment.models.ResponseData;

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


    @Multipart
    @POST("rental/addphotos.php?")
    Call<ResponseData> addphotos(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("rental/addfavlist.php?")
    Call<ResponseData> addfavlist(
            @Query("p_id") String p_id,
            @Query("email") String email,
            @Query("s") String s
    );



    @GET("/rental/getphotos.php?")
    Call<List<GetPhotosPojo>> getphotos
            (@Query("pid") String pid );

    /*@GET("rental/user_registration.php?")
    Call<ResponseData> userRegistration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phonenumber") String rental,
            @Query("role") String role,
            @Query("pwd") String pwd);*/

    @GET("/rental/landlordlogin.php?")
    Call<ResponseData> landlordlogin(
            @Query("email") String email,
            @Query("pwd") String pwd,
            @Query("role") String role
    );

    @GET("/rental/booknow.php?")
    Call<ResponseData> booknow(
            @Query("pid") String pid,
            @Query("bname") String bname,
            @Query("bmsg") String bmsg
    );


    @GET("/rental/reply.php?")
    Call<ResponseData> reply(
            @Query("bid") String bid,
            @Query("lname") String lname,
            @Query("lmsg") String lmsg
    );




    @GET("/rental/user_login.php?")
    Call<ResponseData> userLogin(
            @Query("email") String email,
            @Query("pwd") String pwd,
            @Query("role") String role
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

    @GET("/rental/getprofile.php?")
    Call<List<EditProfilePojo>> getprofile
            (
                    @Query("email") String email);

    @GET("/rental/getproperties.php")
    Call<List<PropertyPojo>> getproperties();


    @GET("/rental/getprices.php")
    Call<List<PricePojo>> getprices();

    @GET("/rental/updateproperty.php?")
    Call<ResponseData> updateproperty(
            @Query("id") String id,
            @Query("pname") String pname,
            @Query("ptype") String ptype,
            @Query("pbeds") String pbeds,
            @Query("pbath") String pbath,
            @Query("parea") String parea,
            @Query("ppets") String ppets,
            @Query("pprice") String pprice,
            @Query("des") String des,
            @Query("location") String location
    );

    @GET("/rental/testfav.php")
    Call<List<PropertyPojo>> userviewpropertylist(@Query("email") String email);


    @GET("/rental/searchviewpropertylist.php")
    Call<List<PropertyPojo>> searchviewpropertylist();

    @GET("/rental/landlordviewproperties.php")
    Call<List<PropertyPojo>> landlordviewproperties(@Query("email") String email);

    @GET("/rental/getmyproperties.php?")
    Call<List<PropertyPojo>> getmyproperties(@Query("email") String email);

    @GET("/rental/myrequests.php?")
    Call<List<MyRequestPojo>> myrequests(@Query("email") String email);


    @GET("/rental/tenantrequests.php?")
    Call<List<MyRequestPojo>> tenantrequests(@Query("email") String email);




    @GET("/rental/updatepropertystatus.php")
    Call<ResponseData> updatepropertystatus(
            @Query("status") String status,
            @Query("id") String id
    );


    @GET("/rental/search.php")
    Call<List<PropertyPojo>> search(
            @Query("price") String price,
            @Query("location") String location,
            @Query("beds") String beds,
            @Query("type") String type
    );


    @GET("/rental/updatepropertystatus.php")
    Call<ResponseData> updatestatus(
            @Query("id") String id,
            @Query("status") String status

    );

    @GET("/rental/deleteproperty.php?")
    Call<ResponseData> deleteproperty(
            @Query("id") String id

    );

    @GET("/rental/getfavlist.php")
    Call<List<PropertyPojo>> getfavlist( @Query("email") String email);

}
