package org.d3if00001.catatanapp.notes.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if00001.catatanapp.databinding.FragmentListNotesBinding
import org.d3if00001.catatanapp.notes.MainViewModel
import org.d3if00001.catatanapp.notes.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.notes.NotesAdapter
import org.d3if00001.catatanapp.notes.entity.Note
import org.d3if00001.catatanapp.notes.viewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
class ListNotes : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private lateinit var rvNotes : RecyclerView
    private lateinit var adapter: NotesAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNotes = binding.rvNotes
        adapter = NotesAdapter()
        val mainViewModel = obtainViewModel(requireActivity() as AppCompatActivity)
        mainViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->
            if (notes != null) {
                adapter.setListNotes(notes as ArrayList<Note>)
                rvNotes.layoutManager = LinearLayoutManager(activity)
                rvNotes.setHasFixedSize(true)
                rvNotes.adapter = adapter
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = viewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}