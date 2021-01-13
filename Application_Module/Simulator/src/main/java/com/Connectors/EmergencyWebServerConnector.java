package com.Connectors;

import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Truck;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface EmergencyWebServerConnector {

    @GET("api/getTrucks/")
    Call<List<Truck>> getTrucks();

    @GET("api/getMissions/")
    Call<List<Mission>> getMissions();

    @GET("api/getFires/")
    Call<List<Fire>> getFires();

    @POST("api/updateTruck/")
    Call<Void> updateTruck(@Body Truck t);

}
