package com.example.todolisttutorial

import android.app.Application

// Uygulama sınıfı, uygulama başlatıldığında ilk olarak yüklenir
class TodoApplication : Application()
{
    // Uygulama yaşam döngüsü boyunca tek bir TaskItemDatabase nesnesi oluşturulur
    private val database by lazy { TaskItemDatabase.getDatabase(this) }

    // TaskItemRepository nesnesi, TaskItemDatabase'den alınan taskItemDao ile oluşturulur
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }
}
