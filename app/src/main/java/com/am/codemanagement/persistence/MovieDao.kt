package com.am.codemanagement.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.am.codemanagement.data.vos.UpcommingMovieVO

@Dao
interface MovieDao {
    //for movieId
    @Query("SELECT * FROM movie")
    fun getAllData(): List<UpcommingMovieVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<UpcommingMovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovies(movie: UpcommingMovieVO?)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): LiveData<List<UpcommingMovieVO>>

    //read single movieVo always be nullable type
    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<UpcommingMovieVO>?

    @Query("SELECT * FROM movie WHERE original_title LIKE :originalTitle || '%'")
    fun getMovieByTitle(originalTitle: String): List<UpcommingMovieVO>

    @Query("DELETE FROM movie")
    fun deleteAllMovie()
}