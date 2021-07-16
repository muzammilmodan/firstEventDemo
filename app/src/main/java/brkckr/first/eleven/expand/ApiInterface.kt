package brkckr.first.eleven.expand


import retrofit2.Call;
import retrofit2.http.GET;

interface ApiInterface {
    @get:GET("movielist.json")
    val allMovies: Call<List<Movie?>?>?
}