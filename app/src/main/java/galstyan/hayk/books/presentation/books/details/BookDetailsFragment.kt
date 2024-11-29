package galstyan.hayk.books.presentation.books.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.books.R
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {

    private val viewModel: BookDetailsViewModel by viewModels()
    private val args: BookDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        view!!.findViewById<ImageView>(R.id.coverImage)!!.transitionName = args.bookId.toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderUiFromState(state)
                }
            }
        }
    }


    private fun renderUiFromState(state: BookDetailsUiState) {
        val coverImage = requireView().findViewById<ImageView>(R.id.coverImage)
        val titleText = requireView().findViewById<TextView>(R.id.bookTitleText)
        val authorText = requireView().findViewById<TextView>(R.id.bookAuthorText)
        val detailsText = requireView().findViewById<TextView>(R.id.bookDetailsText)
        val loading = requireView().findViewById<View>(R.id.loading)

        when (state) {
            is BookDetailsUiState.Loading -> {
                loading.isVisible = true
            }

            is BookDetailsUiState.BookDetails -> {
                loading.isVisible = false
                coverImage.load(state.book.cover) {
                    listener(onSuccess = { _, _ -> startPostponedEnterTransition() })
                }
                titleText.text = state.book.title
                authorText.text = state.book.authors.joinToString(separator = "\n") { it.name }
                detailsText.text = state.book.subjects.joinToString(separator = "\n") { it }
            }

            is BookDetailsUiState.ErrorUnknown -> {
                loading.isVisible = false
                Toast.makeText(
                    requireContext(),
                    R.string.error_book_details_generic,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}