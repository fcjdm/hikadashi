import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = true) =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)
