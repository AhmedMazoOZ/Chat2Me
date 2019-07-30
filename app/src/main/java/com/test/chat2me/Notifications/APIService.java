package com.test.chat2me.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
            "Content-Type:application/json",
            "Authorization:key=AAAAUcsJCf4:APA91bGo4N35VuFBCmPaov6NGK7oWugLdsgWui5QYvCUtL4q2HQgctoRKC4mUIA78FOBntap5WzkJBM_YRE89MttOhnh3iwBhfVDKLh387CfJThnYuo3hg8py4RuI6h-8-flul7GME3loHovKJkw4TXW0uG4wnarQA"
    }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
