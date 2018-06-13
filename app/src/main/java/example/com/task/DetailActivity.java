package example.com.task;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import example.com.task.databinding.DetailBinding;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);


        DetailBinding binding = DataBindingUtil.setContentView(this, R.layout.content_detail);

        String details = getIntent().getStringExtra("details");

        String name = details.split("@")[0];
        String url = details.split("@")[1];

        DetailModel model = new DetailModel(name,url);
        binding.setDetailview(model);

    }

}
