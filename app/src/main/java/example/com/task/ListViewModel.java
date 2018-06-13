package example.com.task;

import android.databinding.BindingAdapter;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class ListViewModel {

   private String name , url , tag;
   private String visible;



    public ListViewModel(Result result, String visible){
       this.name = result.getFirstName()+" "+result.getLastName();
       this.url = result.getAvatar();
       this.visible = visible;
       this.tag = name+"@"+url;
   }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String isVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
                Picasso
                .get()
                .load(imageUrl)
                .into(imageView);
                imageView.setTag("piyuh");
    }

    @BindingAdapter({"bind:specialTag"})
    public static void setSpecialTag(LinearLayout view, String value) {
        view.setTag(value);
    }
}
