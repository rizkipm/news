package com.example.news

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.news.api.ApiClient
import com.example.news.models.RegisterRequest
import com.example.news.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val etFullname: EditText = findViewById(R.id.etFullname)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val fullname = etFullname.text.toString()
            val email = etEmail.text.toString()

        val registerRequest = RegisterRequest(username, password, fullname, email)
        try {
            ApiClient.apiService.register(registerRequest).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                    } else {
                        val errorMessage = response.errorBody()?.string()?: "Unknown error"
                        Log.e("Register error", errorMessage)
                        Toast.makeText(this@MainActivity, "Register failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Error occured: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error occurred: ${e.message}", e)
        }
            }
    }
}