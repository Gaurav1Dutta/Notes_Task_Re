package com.example.notes_task_re

import android.app.DatePickerDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes_task_re.database.NotesDb
import com.example.notes_task_re.databinding.FragmentAddNotesBinding
import com.example.notes_task_re.entites.NotesEntity
import java.text.SimpleDateFormat
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAddNotes.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAddNotes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentAddNotesBinding
    lateinit var mainActivity: MainActivity
    lateinit var notesDb : NotesDb
    private var notesID = -1
    var notes_entites = NotesEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNotesBinding.inflate(layoutInflater)
        notesDb = NotesDb.getDataBase(mainActivity)
        arguments?.let {
            notesID = it.getInt("id")
            if(notesID>-1){
                //   getEntityInfo()
            }
        }
        binding.btnAdd.setOnClickListener {
            if(binding.etTitle.text.toString().isNullOrBlank()){
                binding.etTitle.error = mainActivity.resources.getString(R.string.enter_title)
            }else if(binding.etDescription.text.toString().isNullOrBlank()){
                binding.etDescription.error = mainActivity.resources.getString(R.string.enter_description)
            }else{
                if(notesID >-1){
                    var notes_entities = NotesEntity(id= notes_entites.id, title= binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString())
                    class updateClass : AsyncTask<Void, Void, Void>(){
                        override fun doInBackground(vararg params: Void?): Void? {
                            notesDb.notesDbInterface().UpdateNotes(notes_entities)
                            return null
                        }
                        override fun onPostExecute(result: Void?) {
                            super.onPostExecute(result)
                            mainActivity.navController.popBackStack()
                        }
                    }
                    updateClass().execute()
                }else{

                    var notes_entites = NotesEntity(title= binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString())
                    var notesId = -1L
                    class insertClass : AsyncTask<Void, Void, Void>(){
                        override fun doInBackground(vararg params: Void?): Void? {

                            notesId = notesDb.notesDbInterface().InsertNotes(notes_entites)
                            return null
                        }
                    }
                    insertClass().execute()
                }
                //  mainActivity.noteList.add(note)
            }
        }
        binding.tvPickDate.setOnClickListener {
            var datePicker = DatePickerDialog(mainActivity, {_,year, month, date->
                var simpleDateFormat = SimpleDateFormat("dd-MMM-yyyy")
                var calendar = Calendar.getInstance()
                calendar.set(year, month, date)
                var selectedDate = simpleDateFormat.format(calendar.time)
                System.out.println("in selected Date $selectedDate")
            }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE))
            datePicker.show()
        }
        return binding.root
    }
    fun getEntityInfo(){
        class getEntity : AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                notes_entites = notesDb.notesDbInterface().getNotesById(id)
                return null
            }
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                binding.etTitle.setText(notes_entites.title)
                binding.etDescription.setText(notes_entites.description)
                binding.btnAdd.setText("Update")
            }

        }
        getEntity().execute()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAddNotes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAddNotes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}