package com.example.todolisttutorial

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.todolisttutorial.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

// RecyclerView.ViewHolder sınıfından türetilen TaskItemViewHolder sınıfı
// context: ViewHolder içinde kullanılacak olan bağlam nesnesi (Context).
// binding: ViewHolder'ın bağlı olduğu layoutun veri bağlaması (TaskItemCellBinding).
// clickListener: Tıklama olaylarını dinlemek için TaskItemClickListener arayüzü.
class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{
    // Saat formatını belirten DateTimeFormatter nesnesi.
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    // Görev öğesini ViewHolder'a bağlamak için kullanılan fonksiyon.
    fun bindTaskItem(taskItem: TaskItem)
    {
        // Görev adını görsel bileşene ayarlar.
        binding.name.text = taskItem.name

        // Görev tamamlanmışsa:
        if (taskItem.isCompleted()){
            // Görev adı ve bitiş zamanı için üstü çizili metin efekti uygulanır.
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            // Görev hücresine tıklama olayı eklenir ve clickListener üzerinden görevi silme işlemi yapılır.
            binding.taskCellContainer.setOnClickListener{
                clickListener.removeTaskItem(taskItem)
            }

            // Tamamlama butonu görseline tamamlama durumuna göre resim ve renk uygulanır.
            binding.completeButton.setImageResource(taskItem.imageResource())
            binding.completeButton.setColorFilter(taskItem.imageColor(context))
        }

        // Tamamlama butonuna tıklama olayı eklenir.
        binding.completeButton.setOnClickListener{
            // Görev tamamlanmamışsa tamamlama işlemi yapılır, tamamlanmışsa geri alma işlemi yapılır.
            if (!taskItem.isCompleted()) {
                clickListener.completeTaskItem(taskItem)
            } else {
                clickListener.unCompleteTaskItem(taskItem)
            }
        }

        // Görev hücresine tıklama olayı eklenir ve clickListener üzerinden görevi düzenleme işlemi yapılır.
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        // Görevin bitiş zamanı varsa, formatlanmış saat bilgisi görsel bileşene eklenir.
        // Yoksa boş bir metin atanır.
        if(taskItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            binding.dueTime.text = ""
    }
}
