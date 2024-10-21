package com.example.workmanager.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.workmanager.R
import com.example.workmanager.presentation.viewmodel.MainViewModel
import com.example.workmanager.presentation.viewmodel.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        lifecycleScope.launch {
            viewModel.getQuote()
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    handleUIState(state)
                }
            }
        }


    }
    private fun handleUIState(state: UIState) {
        if (state.data.isNotEmpty()) {
            Toast.makeText(this, "Data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
        }
    }
}