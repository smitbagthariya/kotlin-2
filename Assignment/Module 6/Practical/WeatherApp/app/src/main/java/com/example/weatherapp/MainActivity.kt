package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    var List: MutableList<Model> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter(applicationContext, List)
        recyclerView.adapter = adapter

        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        fetchWeatherData("Rajkot", progressBar)
    }

    private fun fetchWeatherData(city: String, progressBar: ProgressBar) {

        var apiClient = ApiClient.getapiclient()
        var apiInterface = apiClient.create(Apiinterface::class.java)

        var call = apiInterface.getdata(city, "78f5b797538848ddc9fae05b60eeb011", "metric")

        call.enqueue(object : Callback<Model> {

            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                progressBar.visibility = View.GONE

                if (response.isSuccessful) {

                    var weatherData = response.body()

                    if (weatherData != null) {

                        List.clear()
                        List.add(weatherData)
                        adapter.notifyDataSetChanged()

                    } else {

                        Toast.makeText(applicationContext, "no data", Toast.LENGTH_SHORT).show()
                    }


                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {

                Toast.makeText(applicationContext, "no data found ", Toast.LENGTH_SHORT).show()

            }
        })
    }


    private fun showDummyData() {

        var dummyWeather = Model(

            name = "Dummy City",
            main = Main(temp = 22.5, humidity = 60),
            weather = listOf(Weather(description = "Clear sky", icon = "01d")),
            wind = Wind(speed = 5.5)
        )

        List.clear()
        List.add(dummyWeather)
        adapter.notifyDataSetChanged()
    }
}