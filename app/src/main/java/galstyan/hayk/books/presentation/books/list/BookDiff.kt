package galstyan.hayk.books.presentation.books.list

import androidx.recyclerview.widget.DiffUtil
import galstyan.hayk.books.domain.model.Book

class BookDiff : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}