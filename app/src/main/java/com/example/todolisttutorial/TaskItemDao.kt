package com.example.todolisttutorial

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// TaskItemDao adında bir arayüz tanımlanır.
// Bu arayüz, veritabanı işlemleri için kullanılacak veri erişim nesnesidir (DAO - Data Access Object).
@Dao
interface TaskItemDao
{
    // Veritabanındaki tüm görev öğelerini sorgulayan bir fonksiyon.
    // Görev öğeleri, ID'lerine göre artan sırayla sıralanır ve Flow olarak döndürülür.
    @Query("SELECT * FROM task_item_table ORDER BY id ASC")
    fun allTaskItems(): Flow<List<TaskItem>>

    // Yeni bir görev öğesi eklemek veya mevcut bir görev öğesini güncellemek için bir fonksiyon.
    // Çakışma durumunda (örneğin aynı ID'ye sahip bir görev varsa), mevcut görev öğesi yenisiyle değiştirilir.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItem(taskItem: TaskItem)

    // Mevcut bir görev öğesini güncellemek için bir fonksiyon.
    @Update
    suspend fun updateTaskItem(taskItem: TaskItem)

    // Mevcut bir görev öğesini silmek için bir fonksiyon.
    @Delete
    suspend fun deleteTaskItem(taskItem: TaskItem)
}
