package org.d3if00001.catatanapp.presentations.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.databinding.FragmentDetailBinding
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.presentations.ui.viewModels.MainViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.viewModelFactory

class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    private lateinit var dataTitle : String
    private lateinit var dataBody : String
    private lateinit var dataCategory: String
    private lateinit var dataDate:String
    private var note:Note?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataTitle = DetailFragmentArgs.fromBundle(arguments as Bundle).title
        dataBody = DetailFragmentArgs.fromBundle(arguments as Bundle).body
        dataCategory = DetailFragmentArgs.fromBundle(arguments as Bundle).category
        dataDate = DetailFragmentArgs.fromBundle(arguments as Bundle).date
        noteAddUpdateViewModel = obtainViewModel(requireActivity() as AppCompatActivity)

        binding.titleTextView.text = "Title : $dataTitle"
        binding.bodyTextView.text = dataBody
        binding.categoryTextView.text = "Category : $dataCategory"
        binding.dateTextView.text = "date : $dataDate"

        note = Note(
            title = dataTitle,
            body = dataBody,
            category = dataCategory,
            date = dataDate
        )

    }
    fun onBackPressed(){
        findNavController().navigate(R.id.action_detailFragment_to_listNotesFragment)
    }
    private fun deleteNotes(note:Note,noteViewModel : NoteAddUpdateViewModel){
        noteViewModel.delete(note)
        Toast.makeText(requireContext(),"Fitur Belum Tersedia!",Toast.LENGTH_SHORT).show()
    }
    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = viewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NoteAddUpdateViewModel::class.java]
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_delete->{
                deleteNotes(note = note!!,noteAddUpdateViewModel)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}