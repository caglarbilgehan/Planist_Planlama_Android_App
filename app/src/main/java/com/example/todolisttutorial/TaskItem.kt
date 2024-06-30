package com.example.todolisttutorial

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// 'task_item_table' adında bir veritabanı tablosu oluşturur.
@Entity(tableName = "task_item_table")
class TaskItem(
    @ColumnInfo(name = "name") var name: String,                       // Görev adı
    @ColumnInfo(name = "desc") var desc: String,                       // Görev açıklaması
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?,    // Görev bitiş zamanı (String formatında)
    @ColumnInfo(name = "completedDateString") var completedDateString: String?, // Görev tamamlanma tarihi (String formatında)
    @PrimaryKey(autoGenerate = true) var id: Int = 0                   // Görev için benzersiz ID
)
{
    // 'completedDate' adlı özel bir fonksiyon, tamamlanma tarihini LocalDate türüne çevirir.
    private fun completedDate(): LocalDate? = if (completedDateString == null) null else LocalDate.parse(completedDateString, dateFormatter)

    // 'dueTime' adlı fonksiyon, bitiş zamanını LocalTime türüne çevirir.
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)

    // Görevin tamamlanmış olup olmadığını kontrol eder.
    fun isCompleted() = completedDate() != null

    // Görevin tamamlanmış olup olmadığına göre bir resim kaynağı döndürür.
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24

    // Görevin tamamlanmış olup olmadığına göre bir resim rengi döndürür.
    fun imageColor(context: Context): Int = if(isCompleted()) green(context) else black(context)

    // Tamamlanmış görevler için yeşil rengi döndürür.
    private fun green(context: Context) = ContextCompat.getColor(context, R.color.green)

    // Tamamlanmamış görevler için siyah rengi döndürür.
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    // Statik (companion object) nesne, zaman ve tarih formatlayıcılarını tutar.
    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}
