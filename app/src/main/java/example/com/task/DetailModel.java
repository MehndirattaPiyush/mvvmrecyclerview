package example.com.task;

import android.databinding.BindingAdapter;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class DetailModel {

    private String name , url ;


    public DetailModel(String name, String url){
        this.name = name;
        this.url = url;

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


    @BindingAdapter({"bind:imageUrl"})
    public static void Image(ImageView imageView, String imageUrl) {
        Picasso
                .get()
                .load(imageUrl)
                .into(imageView);
        imageView.setTag("piyuh");
    }

}

