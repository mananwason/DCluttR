package com.mananwason.dcluttr.dcluttr;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manan Wason on 31/10/15.
 */
public class SubActivity extends AppCompatActivity {
    List<List<String>> mDataList;

    private RecyclerView mVerticalList;
    private ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
//image1= (ImageView) findViewById(R.id.im)
        prepareData();
        initListView();
    }

    private void prepareData() {
        mDataList = new ArrayList<>();
        int vItemCount = 25;
        int hItemCount = 20;
        for (int i = 0; i < vItemCount; i++) {
            List<String> hList = new ArrayList<>();
            for (int j = 0; j < hItemCount; j++) {
                hList.add("Item." + j);
            }
            mDataList.add(hList);
        }
    }

    private void initListView() {
        mVerticalList = (RecyclerView) findViewById(R.id.vertical_list);
        mVerticalList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        VerticalAdapter verticalAdapter = new VerticalAdapter();
        verticalAdapter.setData(mDataList);
        mVerticalList.setAdapter(verticalAdapter);
    }

    private static class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<List<String>> mDataList;

        public VerticalAdapter() {
        }

        public void setData(List<List<String>> data) {
            mDataList = data;
            notifyDataSetChanged();
        }

        private class HorizontalListViewHolder extends RecyclerView.ViewHolder {

//            private TextView title;
            private RecyclerView horizontalList;
            private HorizontalAdapter horizontalAdapter;

            public HorizontalListViewHolder(View itemView) {
                super(itemView);
                Context context = itemView.getContext();
//                title = (TextView) itemView.findViewById(R.id.item_title);
                horizontalList = (RecyclerView) itemView.findViewById(R.id.item_horizontal_list);
                horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                horizontalAdapter = new HorizontalAdapter();
                horizontalList.setAdapter(horizontalAdapter);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context).inflate(R.layout.vertical_list_item, parent, false);
            HorizontalListViewHolder holder = new HorizontalListViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
            HorizontalListViewHolder holder = (HorizontalListViewHolder) rawHolder;
//            holder.title.setText("Horizontal List No." + position);
            holder.horizontalAdapter.setData(mDataList.get(position));
            holder.horizontalAdapter.setRowIndex(position);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    private static class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mDataList;
        private int mRowIndex = -1;
        private int[] mColors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.DKGRAY};

        public HorizontalAdapter() {
        }

        public void setData(List<String> data) {
            if (mDataList != data) {
                mDataList = data;
                notifyDataSetChanged();
            }
        }

        public void setRowIndex(int index) {
            mRowIndex = index;
        }

        private class ItemViewHolder extends RecyclerView.ViewHolder {
            private ImageView image;


            public ItemViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image1);
                itemView.setOnClickListener(mItemClickListener);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_list_item, parent, false);
            ItemViewHolder holder = new ItemViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
            ItemViewHolder holder = (ItemViewHolder) rawHolder;
            Uri uri = Uri.parse("http://pctechmag.com/wp-content/uploads/2013/09/android-double-down.jpg");
            Picasso.with(holder.image.getContext()).load(uri).error(R.drawable.github).into(holder.image);
//            holder.image.set("ABC");
//            holder.itemView.setBackgroundColor(mColors[position % mColors.length]);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private View.OnClickListener mItemClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int columnIndex = (int) v.getTag();
                int rowIndex = mRowIndex;

                String text = String.format("rowIndex:%d ,columnIndex:%d", rowIndex, columnIndex);
                showToast(v.getContext(), text);
                Log.d("test", text);
            }
        };
    }

    private static Toast sToast;

    public static void showToast(Context context, String text) {
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        sToast.show();
    }
}
