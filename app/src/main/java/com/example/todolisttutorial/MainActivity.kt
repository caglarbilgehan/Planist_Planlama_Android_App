package com.example.todolisttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolisttutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener
{
    // 'binding' adında bir değişken tanımlıyoruz ve bu değişken ActivityMainBinding türünde olacak.
    private lateinit var binding: ActivityMainBinding

    // 'taskViewModel' adında bir değişken tanımlıyoruz ve bu değişken TaskViewModel türünde olacak.
    // by viewModels kullanarak ViewModel'i oluşturuyoruz ve TaskItemModelFactory kullanarak
    // repository'yi sağlıyoruz.
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // Layout inflater kullanarak ActivityMainBinding'in kök görünümünü belirliyoruz.
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Bu kök görünümü setContentView ile aktivitenin ana içeriği olarak ayarlıyoruz.
        setContentView(binding.root)

        // 'newTaskButton' adlı düğmeye tıklama dinleyicisi ekliyoruz.
        // Bu düğmeye tıklandığında yeni bir görev ekleme dialog'u açılıyor.
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        // RecyclerView ayarlarını yapıyoruz.
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        // 'taskItems' adlı gözlemlenebilir veriyi izliyoruz.
        taskViewModel.taskItems.observe(this){
            // RecyclerView'i layout manager ve adapter ile ayarlıyoruz.
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    // Bir görev öğesini düzenlemek için kullanılacak fonksiyon.
    override fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager, "newTaskTag")
    }

    // Bir görev öğesini tamamlanmış olarak işaretlemek için kullanılacak fonksiyon.
    override fun completeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setCompleted(taskItem)
    }

    // Bir görev öğesinin tamamlanmasını geri almak için kullanılacak fonksiyon.
    override fun unCompleteTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setunCompleted(taskItem)
    }

    // Bir görev öğesini silmek için kullanılacak fonksiyon.
    override fun removeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.removeTaskItem(taskItem)
    }
}
