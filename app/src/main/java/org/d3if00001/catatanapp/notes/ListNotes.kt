package org.d3if00001.catatanapp.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if00001.catatanapp.databinding.FragmentListNotesBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListNotes : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private lateinit var rvNotes : RecyclerView
    private lateinit var listNotes: ArrayList<Note>

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        listNotes = ArrayList()
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.US)
        val formattedDate = dateFormat.format(currentDate)
        listNotes.add(
            Note(
                title = "judul",
                body = """
                    Pagi-pagi buta aku sudah bangun dari tidurku. Aku merasa segar dan semangat untuk memulai hari yang baru. 
                    Setelah mandi dan sarapan, aku bersiap-siap untuk pergi ke kantor. 
                    Jalanan masih lengang karena memang masih pagi. Namun, ketika aku tiba di kantor, 
                    suasana sudah ramai dengan para karyawan yang telah datang lebih awal. 
                    Hari ini aku memiliki banyak pekerjaan yang harus diselesaikan, 
                    tapi aku siap menghadapinya dengan semangat yang tinggi.
                """.trimIndent(),
                date = formattedDate,
                category = "a day in my life"
            )
        )
        listNotes.add(
            Note(
                title = "judul",
                body = """
                    Pagi-pagi buta aku sudah bangun dari tidurku. Aku merasa segar dan semangat untuk memulai hari yang baru. 
                    Setelah mandi dan sarapan, aku bersiap-siap untuk pergi ke kantor. 
                    Jalanan masih lengang karena memang masih pagi. Namun, ketika aku tiba di kantor, 
                    suasana sudah ramai dengan para karyawan yang telah datang lebih awal. 
                    Hari ini aku memiliki banyak pekerjaan yang harus diselesaikan, 
                    tapi aku siap menghadapinya dengan semangat yang tinggi.
                """.trimIndent(),
                date = formattedDate,
                category = "a day in my life"
            )
        )
        rvNotes.setHasFixedSize(true)
        setRvNotes()
    }

    fun setRvNotes(){
        rvNotes.layoutManager = LinearLayoutManager(context)
        rvNotes.adapter = NotesAdapter(listNotes = listNotes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}