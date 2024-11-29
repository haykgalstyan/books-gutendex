package galstyan.hayk.books.presentation.books.list

import androidx.recyclerview.widget.RecyclerView

class ScrollEndListener(
    private val onScrollEnd: () -> Unit
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!recyclerView.canScrollVertically(1)) onScrollEnd()
    }
}