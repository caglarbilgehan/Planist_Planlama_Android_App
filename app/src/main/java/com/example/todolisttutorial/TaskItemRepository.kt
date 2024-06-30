package com.example.todolisttutorial

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// TaskItemRepository sınıfı, veritabanı işlemlerini yönetir.
// taskItemDao: Veritabanı işlemleri için kullanılacak DAO (Data Access Object).
class TaskItemRepository(private val taskItemDao: TaskItemDao)
{
    // Tüm görev öğelerini içeren Flow nesnesi.
    // Flow, veri akışını temsil eder ve veri değişiklikleri anında izlenebilir.
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    // Ana iş parçacığında çalışacak işlemleri belirtmek için @WorkerThread anotasyonu kullanılır.
    // Yeni bir görev öğesi ekler.
    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem)
    {
        taskItemDao.insertTaskItem(taskItem)
    }

    // Güncelleme işlemi için @WorkerThread anotasyonu kullanılarak ana iş parçacığında çalışacak işlem belirtilir.
    // Bir görev öğesini günceller.
    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem)
    {
        taskItemDao.updateTaskItem(taskItem)
    }

    // Silme işlemi için @WorkerThread anotasyonu kullanılarak ana iş parçacığında çalışacak işlem belirtilir.
    // Bir görev öğesini siler.
    @WorkerThread
    suspend fun deleteTaskItem(taskItem: TaskItem)
    {
        taskItemDao.deleteTaskItem(taskItem)
    }
}
