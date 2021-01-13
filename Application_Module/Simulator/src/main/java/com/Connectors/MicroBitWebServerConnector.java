package com.Connectors;

import com.Objects.Sensor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MicroBitWebServerConnector {
    @POST("api/updateSensor/")
    Call<Void> updateSensor(@Body Sensor s);

    @POST("api/disabledSensor/")
    Call<Void> disableSensor(@Body Sensor s);
}
