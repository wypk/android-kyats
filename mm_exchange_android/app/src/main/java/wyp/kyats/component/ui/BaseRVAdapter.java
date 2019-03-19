package wyp.kyats.component.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import wyp.kyats.R;
import wyp.kyats.component.util.Logger;

/**
 * Created by Wai Yan on 11/27/18.
 */
public abstract class BaseRVAdapter<MODEL> extends RecyclerView.Adapter<BaseRVAdapter.ViewHolder> {

    private Context context;
    private List<MODEL> items = new ArrayList<>();
    private OnViewHolderClickListener<MODEL> viewHolderClickListener;
    private int lastPosition = -1;

    public BaseRVAdapter(Context context,
            OnViewHolderClickListener<MODEL> viewHolderClickListener) {
        super();
        this.context = context;
        this.viewHolderClickListener = viewHolderClickListener;
    }

    protected abstract View createView(Context context, ViewGroup viewGroup, int viewType);

    protected abstract void bindView(BaseRVAdapter.ViewHolder viewHolder, MODEL item);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(this.createView(context, viewGroup, viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRVAdapter.ViewHolder holder, int position) {
        bindView(holder, this.getItem(position));
        this.setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<MODEL> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    private MODEL getItem(int index) {
        return ((items != null && index < items.size()) ? items.get(index) : null);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > this.lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.rv_item_sliding_up);
            viewToAnimate.startAnimation(animation);
            this.lastPosition = position;
        }
    }

    @FunctionalInterface
    public interface OnViewHolderClickListener<T> {
        void onClick(View view, int position, T data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View selfView;

        @SuppressLint("UseSparseArrays")
        ViewHolder(View view) {
            super(view);

            this.selfView = view;

            Logger.d(this.getClass(), "View instance : " + this.selfView.getClass().getName());

            this.selfView.setOnClickListener(clickedView -> {

                Logger.d(this.getClass(), "View instance clicked : " + clickedView.getClass().getName());

                BaseRVAdapter.this.viewHolderClickListener.onClick(
                        this.selfView,
                        this.getAdapterPosition(),
                        BaseRVAdapter.this.getItem(getAdapterPosition()));
            });
        }
    }
}
