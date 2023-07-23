package com.am.codemanagement.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.Window
import com.am.codemanagement.R
import java.text.SimpleDateFormat

const val API_KEY = "3875342a650d7bd12a1bc92bee310358"
const val BASEURL = "https://api.themoviedb.org/3/movie/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
const val upcomming_url = "/3/movie/upcoming"

const val BASE_URL = "https://api.themoviedb.org"
const val API_GET_POPULAR_MOVIES = "/3/movie/popular"
// Params
const val PARAM_API_KEY = "api_key"
const val PARAM_PAGE = "page"

const val MOVIE_API_KEY = "3875342a650d7bd12a1bc92bee310358"

fun isNetwork(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        return true
    }
    return false
}

fun dateFormat(date: String): String {
    return if (date != "") {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val output = SimpleDateFormat("MMM dd,yyyy")
        val dateTime = simpleDateFormat.parse(date)
        output.format(dateTime)
    } else {
        ""
    }
}

fun setUpLoadingDialog(context: Context): Dialog {
    val dialog = Dialog(context)
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = layoutInflater.inflate(R.layout.dialog_loading, null,false)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(view)
    dialog.setCancelable(false)
    dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.create()
    return dialog
}





