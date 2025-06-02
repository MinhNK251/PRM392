package com.example.lab10.api;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
