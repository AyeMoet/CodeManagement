package com.am.codemanagement.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.am.codemanagement.data.vos.UpcommingMovieVO

@Database(
    entities = [UpcommingMovieVO::class],

    version = 1,

    exportSchema = false
)

abstract class MovieDatabase : RoomDatabase() {
    companion object {

        private val DB_NAME = "THE_MOVIE_DB"
        var dbInstance: MovieDatabase? = null
        //app တစ်ခုလံုးမှာ movieDatabase object တစ်ခုပဲရှိချင်လို့
        fun getDBInstance(context: Context): MovieDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()//အရင်version မှာသိမ်းထားတာေတွကို ဖျက်ပီး အသစ်ပြန်သိမ်းမယ်,application  crash မဖြစ်သွားဘူး
                        .build()
                }
            }
            return dbInstance
        }
    }
    abstract fun movieDao(): MovieDao

}