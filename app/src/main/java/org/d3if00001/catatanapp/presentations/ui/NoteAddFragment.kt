package org.d3if00001.catatanapp.presentations.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if00001.catatanapp.R
import org.d3if00001.catatanapp.databinding.FragmentAddBinding
import org.d3if00001.catatanapp.domain.models.Note
import org.d3if00001.catatanapp.presentations.ui.viewModels.NoteAddUpdateViewModel
import org.d3if00001.catatanapp.presentations.ui.viewModels.viewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class NoteAddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAddUpdateViewModel = obtainViewModel(this)

        binding.submitButton.setOnClickListener {
            val title = binding.tiTitle.text.toString().trim()
            val body = binding.tiIsi.text.toString().trim()
            val category = binding.tiKategori.text.toString().trim()
            val date = Date()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateFormatted = sdf.format(date)
            when{
                title.isEmpty()-> binding.tiTitle.error = getString(R.string.empty)
                body.isEmpty()->binding.tiIsi.error = getString(R.string.empty)
                category.isEmpty()->binding.tiIsi.error = getString(R.string.empty)
                else->{
                    noteAddUpdateViewModel.insert(
                        Note(
                            title = title,
                            body = body,
                            category = category,
                            date = dateFormatted
                        )
                    )
                    Toast.makeText(requireContext(), "Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_noteAddFragment_to_listNotesFragment)
                }
            }
        }

        binding.backButton.setOnClickListener { view->
            view.findNavController().navigate(R.id.action_noteAddFragment_to_listNotesFragment)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_back,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_back->{
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun obtainViewModel(fragment:Fragment): NoteAddUpdateViewModel {
        val factory = viewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[NoteAddUpdateViewModel::class.java]
    }
}