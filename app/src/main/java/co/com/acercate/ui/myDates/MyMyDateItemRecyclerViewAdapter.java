package co.com.acercate.ui.myDates;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.com.acercate.R;
import co.com.acercate.ui.myDates.MyDatesContent.MyDatesItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MyDatesItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMyDateItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMyDateItemRecyclerViewAdapter.ViewHolder> {

    private final List<MyDatesItem> mValues;

    public MyMyDateItemRecyclerViewAdapter(List<MyDatesItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_my_date_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public MyDatesItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}