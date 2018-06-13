package example.com.task;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import example.com.task.databinding.ListBinding;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // flag for footer ProgressBar (i.e. last item of list)
    private boolean isLoadingAdded = false;
    private List<ListViewModel> results;



    private Context context;
    LayoutInflater inflater;

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public PaginationAdapter(Context context) {
        this.context = context;
        results = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if(inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }


        switch (viewType) {
            case ITEM:
                final ListBinding listBinding = ListBinding.inflate(inflater,parent,false);
                ListViewModel model = listBinding.getUserview();

                viewHolder = new UserVH(listBinding);

                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ListViewModel result = results.get(position); // Movie

        switch (getItemViewType(position)) {



            case ITEM:
                final UserVH userVH = (UserVH) holder;
                ((UserVH) holder).listBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("lol", "onClick:  "+v.getTag() );
                        Intent intent = new Intent(context,DetailActivity.class);
                        intent.putExtra("details",v.getTag().toString());
                        context.startActivity(intent);
                    }
                });
                userVH.bind(result);

                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

            return (position == results.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        }



    public void add(ListViewModel r) {
        results.add(r);
        notifyItemInserted(results.size() - 1);
    }

    public void addAll(List<ListViewModel> moveResults) {
        for (ListViewModel result : moveResults) {
            add(result);
        }
    }




    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ListViewModel(new Result(),"invisible"));
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = results.size() - 1;
        ListViewModel result = getItem(position);

        if (result != null) {
            results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ListViewModel getItem(int position) {
        return results.get(position);
    }

    protected class UserVH extends RecyclerView.ViewHolder {

        private ListBinding listBinding;

        public UserVH(ListBinding listBinding) {
            super(listBinding.getRoot());


            this.listBinding = listBinding;

        }

        public void bind(ListViewModel listViewModel){
            this.listBinding.setUserview(listViewModel);

        }

        public ListBinding getListBinding() {
            return listBinding;
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {


        public LoadingVH(View itemView) {
            super(itemView);

        }


    }

}



