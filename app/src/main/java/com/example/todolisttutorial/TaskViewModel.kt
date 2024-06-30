package com.example.todolisttutorial

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate

// ViewModel sınıfı, görev öğeleri ile etkileşim için kullanılır
class TaskViewModel(private val repository: TaskItemRepository): ViewModel() {

    // Görev öğelerinin LiveData listesi, repository'den gelen veriye bağlı olarak güncellenir
    val taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    // Yeni bir görev öğesi eklemek için kullanılan fonksiyon
    fun addTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(taskItem)
    }

    // Mevcut bir görev öğesini güncellemek için kullanılan fonksiyon
    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)
    }

    // Bir görev öğesini silmek için kullanılan fonksiyon
    fun removeTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        repository.deleteTaskItem(taskItem)
    }

    // Bir görev öğesini tamamlanmış olarak işaretlemek için kullanılan fonksiyon
    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }

    // Bir görev öğesini tamamlanmamış olarak işaretlemek için kullanılan fonksiyon
    fun setunCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (taskItem.isCompleted())
            taskItem.completedDateString = null
        repository.updateTaskItem(taskItem)
    }
}

// ViewModelProvider.Factory arayüzünü uygulayan fabrika sınıfı
class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory {

    // ViewModel sınıfını oluşturan metod
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verilen sınıf TaskViewModel sınıfı ile eşleşiyorsa ilgili ViewModel'i oluşturur
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        // Geçersiz bir ViewModel sınıfı isteği durumunda hata fırlatılır
        throw IllegalArgumentException("Bilinmeyen ViewModel sınıfı")
    }
}
