package galstyan.hayk.books.presentation.books.list

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.books.R
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListFragment : Fragment(R.layout.fragment_book_list) {

    private val viewModel: BookListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderUiFromState(state)
                }
            }
        }
    }

    private fun setupList() {
        requireView().findViewById<RecyclerView>(R.id.bookList)
            .apply {
                adapter = BookListAdapter { book, transitionView ->
                    val extras = FragmentNavigatorExtras(
                        transitionView to transitionView.transitionName
                    )
                    viewModel.action(BookListAction.Open(book, extras))
                }
                postponeEnterTransition()
                doOnPreDraw { startPostponedEnterTransition() }
                addOnScrollListener(ScrollEndListener {
                    viewModel.action(BookListAction.LoadMore)
                })
            }
    }


    private fun renderUiFromState(state: BookListUiState) {
        val list = requireView().findViewById<RecyclerView>(R.id.bookList)
        val loading = requireView().findViewById<View>(R.id.loading)
        val adapter = list.adapter as BookListAdapter

        when (state) {
            is BookListUiState.Loading -> {
                loading.isVisible = true
            }

            is BookListUiState.BookList -> {
                loading.isVisible = false
                adapter.submitList(state.books)
            }

            is BookListUiState.ErrorUnknown -> {
                loading.isVisible = false
                Toast.makeText(
                    requireContext(),
                    R.string.error_book_list_generic,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is BookListUiState.NavigateToDetails -> {
                findNavController().navigate(
                    BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(
                        state.book.id
                    ),
                    state.navigationExtras,
                )
                viewModel.action(BookListAction.OpenDone)
            }
        }
    }
}