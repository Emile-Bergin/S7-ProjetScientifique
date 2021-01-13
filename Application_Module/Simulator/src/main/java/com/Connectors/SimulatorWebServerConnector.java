package com.Connectors;

import com.Objects.Mission;
import com.Objects.Sensor;
import com.Objects.Truck;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface SimulatorWebServerConnector {
    @POST("api/updateTruck/")
    Call<Void> updateTruck(@Body Truck t);

    @POST("api/updateMission/")
    Call<Void> updateMission(@Body Mission m);

    @POST("api/updateSensor/")
    Call<Void> updateSensor(@Body Sensor s);

    @GET("api/getSensors")
    Call<List<Sensor>> getSensors();
}
