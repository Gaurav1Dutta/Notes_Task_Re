package com.example.notes_task_re

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.notes_task_re.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_controller)

        binding.bottomView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.lvNotes->{
                    navController.navigate(R.id.lvNotes)
                }
                R.id.lvTask->{
                    navController.navigate(R.id.lvTask)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}