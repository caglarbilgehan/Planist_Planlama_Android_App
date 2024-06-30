package com.example.todolisttutorial

// TaskItemClickListener adlı bir arayüz tanımlanır.
// Bu arayüz, görev öğelerine tıklama olaylarını dinlemek için kullanılır.
interface TaskItemClickListener
{
    // Görev öğesini düzenlemek için çağrılacak fonksiyon.
    fun editTaskItem(taskItem: TaskItem)

    // Görev öğesini tamamlanmış olarak işaretlemek için çağrılacak fonksiyon.
    fun completeTaskItem(taskItem: TaskItem)

    // Görev öğesinin tamamlanmasını geri almak için çağrılacak fonksiyon.
    fun unCompleteTaskItem(taskItem: TaskItem)

    // Görev öğesini silmek için çağrılacak fonksiyon.
    fun removeTaskItem(taskItem: TaskItem)
}
