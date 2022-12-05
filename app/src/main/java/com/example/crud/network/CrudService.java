package com.example.crud.network;

import com.example.crud.series.Series;
import com.example.crud.templates.Template;
import com.example.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudService {

    @GET("venusMessageHistory")
    Call<List<Message>> fetchMessages();

    @POST("venusMessageHistory")
    Call<Message> createMessage(@Body Message message);

    @DELETE("venusMessageHistory/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT("venusMessageHistory/{id}")
    Call<Void> updateMessage(@Path("id") String id, @Body Message message);

    @GET("venusTemplates")
    Call<List<Template>> fetchTemplates();

    @POST("venusTemplates")
    Call<Template> createTemplate(@Body Template template);

    @DELETE("venusTemplates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT("venusTemplates/{id}")
    Call<Void> updateTemplate(@Path("id") String id, @Body Template template);

    @GET("rajeshSeries")
    Call<List<Series>> fetchSeries();

    @POST("rajeshSeries")
    Call<Series> createSeries(@Body Series series);

    @DELETE("rajeshSeries/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT("rajeshSeries/{id}")
    Call<Void> updateSeries(@Path("id") String id, @Body Series series);
}
