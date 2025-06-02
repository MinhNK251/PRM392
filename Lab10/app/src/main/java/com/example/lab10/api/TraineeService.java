package com.example.lab10.api;

import com.example.lab10.model.Trainee;

import retrofit2.Call;
import retrofit2.http.*;

public interface TraineeService {
    String TRAINEES = "Nhanvien"; // "Nhanvien" là tên của table được tạo trong API

    @GET(TRAINEES)
    Call<Trainee[]> getAllTrainees();

    @GET(TRAINEES + "/{id}")
    Call<Trainee> getTraineeById(@Path("id") Object id);

    @POST(TRAINEES)
    Call<Trainee> createTrainees(@Body Trainee trainee);

    @PUT(TRAINEES + "/{id}")
    Call<Trainee> updateTrainees(@Path("id") long id, @Body Trainee trainee);

    @DELETE(TRAINEES + "/{id}")
    Call<Trainee> deleteTrainees(@Path("id") long id);
}