package com.rk.interviewtask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rk.interviewtask.databinding.ActivityMainBinding
import com.rk.interviewtask.model.PostAdapter
import com.rk.interviewtask.ui.PostViewModel
import com.rk.interviewtask.ui.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val viewModels: PostViewModel by viewModels()
    private lateinit var bin: ActivityMainBinding
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        adapter = PostAdapter()
        bin.recyclerView.layoutManager = LinearLayoutManager(this)
        bin.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModels.uiStateFlow.collect { state ->
                when(state) {
                    is UiState.Loading -> {
                        bin.progressBar.isVisible = true
                    }
                    is UiState.Success -> {
                        bin.progressBar.isVisible = false
                        adapter.submitList(state.data)
                    }
                    is UiState.Error -> {
                        bin.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "No Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}