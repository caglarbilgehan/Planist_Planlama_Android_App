package com.example.todolisttutorial

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TaskItem sınıfını temsil eden veritabanı tanımlanıyor
// entities = [TaskItem::class]: Bu veritabanında TaskItem sınıfı kullanılacak.
// version = 1: Veritabanı versiyonu.
// exportSchema = false: Şemanın dışa aktarımı kapalı.
@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
public abstract class TaskItemDatabase : RoomDatabase()
{
    // TaskItemDao arayüzünü döndüren soyut bir fonksiyon.
    abstract fun taskItemDao(): TaskItemDao

    companion object
    {
        // INSTANCE değişkeni, veritabanı örneğini tutar. @Volatile, değişkenin tüm thread'lerde aynı anda görünmesini sağlar.
        @Volatile
        private var INSTANCE: TaskItemDatabase? = null

        // Veritabanı örneğini döndüren fonksiyon.
        fun getDatabase(context: Context): TaskItemDatabase
        {
            // INSTANCE değişkeni null ise synchronized bloğu ile senkronize edilir.
            return INSTANCE ?: synchronized(this)
            {
                // Room veritabanı oluşturucu ile veritabanı örneği oluşturulur.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemDatabase::class.java,
                    "task_item_database" // Veritabanı dosya adı.
                ).build()
                // Oluşturulan örnek INSTANCE değişkenine atanır.
                INSTANCE = instance
                // Veritabanı örneği döndürülür.
                instance
            }
        }
    }
}
