package example.com.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    String TAG = "qwerty";
    ApiInterface apiInterface;

    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rv;
    ProgressBar progressBar;
    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES;
    private int currentPage = PAGE_START;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apiInterface = CallApi.getClient(getApplicationContext()).create(ApiInterface.class);

        rv =  findViewById(R.id.list);
        progressBar =  findViewById(R.id.progress_m);


        adapter = new PaginationAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();


    }

    private Call<Users> callUsersApi(int index) {	//2
        return apiInterface.getUsers(index);
    }

    private List<Result> fetchResults(Response<Users> response) {	//3
        Users users = response.body();
        return users.getResults();
    }
    private void loadFirstPage() {
        callUsersApi(PAGE_START).enqueue(new Callback<Users>() {	//4
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                TOTAL_PAGES = response.body().getTotalPages();
                List<Result> results = fetchResults(response);	//5
                List<ListViewModel> listViewModels = getListView(results);
                Log.d(TAG, "onResponse: "+results.get(0).getFirstName());
                progressBar.setVisibility(View.GONE);
                adapter.addAll(listViewModels);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // handle error
            }
        });
    }

    private void loadNextPage() {
        if(!isLastPage){
            callUsersApi(currentPage).enqueue(new Callback<Users>() {	//6
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    adapter.removeLoadingFooter();
                    isLoading = false;

                    List<Result> results = fetchResults(response);
                    List<ListViewModel> listViewModels = getListView(results);
                    adapter.addAll(listViewModels);
                    Log.d(TAG, "onResponse: "+TOTAL_PAGES + currentPage);
                    if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                    else {isLastPage = true;
                    progressBar.setVisibility(View.INVISIBLE);}

                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    // handle failure
                }
            });
        }
    }

    private List<ListViewModel> getListView(List<Result> results) {
        List<ListViewModel> listViewModels = new ArrayList<>();
        for(Result r : results){
            ListViewModel model = new ListViewModel(r,"invisible");
            listViewModels.add(model);
        }
        return listViewModels;
    }
}
