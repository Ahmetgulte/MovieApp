package com.example.movieapp.retrofit;

import com.example.movieapp.model.GenreResponse;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieCredit;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.model.Season;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.model.TvSerieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/trending/movie/day")
    Call<MovieResponse> getTrendMovies();

    @GET("/3/trending/tv/day")
    Call<TvSerieResponse> getTrendSeries();

    @GET("3/movie/popular")
    Call<MovieResponse> getPopularMovies();

    @GET("3/tv/popular")
    Call<TvSerieResponse> getPopularSeries();

    @GET("3/discover/movie")
    Call<MovieResponse> getTopRatedMovies(@Query("vote_average.gte") int vote_average,@Query("vote_count.gte") int vote_count,@Query("sort_by") String sort_by,@Query("language") String language);

    @GET("3/discover/tv")
    Call<TvSerieResponse> getTopRatedSeries(@Query("vote_average.gte") int vote_average,@Query("vote_count.gte") int vote_count,@Query("sort_by") String sort_by,@Query("language") String language);

    @GET("/3/search/movie")
    Call<MovieResponse> searchMovie( @Query("query") String query);

    @GET("/3/genre/movie/list")
    Call<GenreResponse> getMovieGenres(@Query("language") String language);

    @GET("/3/genre/tv/list")
    Call<GenreResponse> getTvSerieGenres(@Query("language") String language);

    @GET("/3/discover/movie")
    Call<MovieResponse> getMoviesByGenre(@Query("sort_by") String sort_by,@Query("with_genres") String genreId );

    @GET("/3/discover/tv")
    Call<TvSerieResponse> getTvSeriesByGenre( @Query("sort_by") String sort_by, @Query("with_genres") String genreId );

    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovieById(@Path("movie_id") int movie_id);

    @GET("/3/tv/{tv_id}")
    Call<TvSerie> getSerieById(@Path("tv_id") int tv_id);


    @GET("/3/tv/{tv_id}/season/{season_number}")
    Call<Season> getEpisodes(@Path("tv_id") String tv_id,@Path("season_number") String season_number);

    @GET("/3/movie/{movie_id}/credits")
    Call<MovieCredit> getMovieActors(@Path("movie_id") int movie_id);
}
