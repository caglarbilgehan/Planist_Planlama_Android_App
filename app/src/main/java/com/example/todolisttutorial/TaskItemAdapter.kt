package com.example.todolisttutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolisttutorial.databinding.TaskItemCellBinding

// TaskItemAdapter sınıfı, RecyclerView.Adapter sınıfını genişletir ve TaskItemViewHolder ile çalışır.
// Bu adaptör, görev öğelerini bir RecyclerView içinde görüntülemek için kullanılır.
class TaskItemAdapter(
    private val taskItems: List<TaskItem>,              // Görev öğelerinin listesini tutar.
    private val clickListener: TaskItemClickListener    // Tıklama olaylarını dinlemek için bir arayüz.
): RecyclerView.Adapter<TaskItemViewHolder>()
{
    // onCreateViewHolder, yeni bir ViewHolder oluşturmak için çağrılır.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val from = LayoutInflater.from(parent.context)  // LayoutInflater nesnesi oluşturulur.
        val binding = TaskItemCellBinding.inflate(from, parent, false)  // TaskItemCellBinding kullanılarak view bağlanır.
        return TaskItemViewHolder(parent.context, binding, clickListener)  // Yeni bir TaskItemViewHolder döndürülür.
    }

    // onBindViewHolder, belirli bir konumdaki veriyi bağlamak için çağrılır.
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.bindTaskItem(taskItems[position])  // TaskItemViewHolder'a belirli bir görev öğesi bağlanır.
    }

    // getItemCount, adaptördeki öğe sayısını döndürür.
    override fun getItemCount(): Int = taskItems.size  // Görev öğelerinin sayısını döndürür.
}
