package com.am.codemanagement.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.am.codemanagement.persistence.typeconverters.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie")
@TypeConverters(
    GenreIdsTypeConverter::class
)
data class UpcommingMovieVO(
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "isFavourite")
    var isFavourite : Boolean?
):Serializable {
}