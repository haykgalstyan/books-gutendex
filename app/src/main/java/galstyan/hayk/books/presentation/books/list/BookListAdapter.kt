package galstyan.hayk.books.presentation.books.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import galstyan.hayk.books.R
import galstyan.hayk.books.domain.model.Book

class BookListAdapter(
    private val onBookClick: (Book, transitionView: View) -> Unit
) : ListAdapter<Book, BookListAdapter.BookViewHolder>(BookDiff()) {

    inner class BookViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val bookImage: ImageView = itemView.findViewById(R.id.bookImage)
        private val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        private val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)

        init {
            itemView.setOnClickListener {
                onBookClick(getItem(adapterPosition), bookImage)
            }
        }

        fun bind(book: Book) {
            bookTitle.text = book.title
            bookAuthor.text = book.authors.joinToString("\n") { it.name }
            bookImage.transitionName = book.id.toString()
            bookImage.load(book.cover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_list, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}