package com.example.todolisttutorial

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolisttutorial.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

// 'NewTaskSheet' sınıfı, 'BottomSheetDialogFragment' sınıfını genişletir ve bir görev ekleme veya düzenleme ekranı sağlar.
class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment()
{
    // 'binding' ve 'taskViewModel' adında iki değişken tanımlıyoruz.
    // Ayrıca, 'dueTime' adında bir değişken tanımlıyoruz ve bu değişken görev bitiş saatini tutuyor.
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    // Fragment oluşturulduğunda yapılacak işlemleri tanımlıyoruz.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        // Eğer bir 'taskItem' varsa, düzenleme modu etkinleştirilir ve ilgili alanlar doldurulur.
        if (taskItem != null)
        {
            binding.taskTitle.text = "Görev Düzenle"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.desc)
            binding.deleteButton.setOnClickListener {
                deleteAction()
            }
            if(taskItem!!.dueTime() != null)
            {
                dueTime = taskItem!!.dueTime()!!
                updateTimeButtonText()
            }
        }
        else
        {
            // Yeni bir görev ekleme modu etkinleştirilir.
            binding.taskTitle.text = "Yeni Görev"
        }

        // 'taskViewModel' değişkeni, ViewModelProvider ile atanır.
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }

        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }

    // Saat seçici dialog açılır.
    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Görev Bitiş Saati")
        dialog.show()
    }

    // Saat düğmesi metni güncellenir.
    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    // Fragment'in görünümü oluşturulur.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    // Görev kaydetme işlemi yapılır.
    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        val dueTimeString = if(dueTime == null) null else TaskItem.timeFormatter.format(dueTime)
        if(taskItem == null)
        {
            val newTask = TaskItem(name, desc, dueTimeString, null)
            taskViewModel.addTaskItem(newTask)
        }
        else
        {
            taskItem!!.name = name
            taskItem!!.desc = desc
            taskItem!!.dueTimeString = dueTimeString

            taskViewModel.updateTaskItem(taskItem!!)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

    // Görev silme işlemi yapılır.
    private fun deleteAction()
    {
        taskViewModel.removeTaskItem(taskItem!!)
        dismiss()
    }
}
