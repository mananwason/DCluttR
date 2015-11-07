package com.mananwason.dcluttr.dcluttr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Manan Wason on 31/10/15.
 */
public class SubActivityAdapter extends RecyclerView.Adapter<SubActivityAdapter.SubActivityViewHolder> {

    private final LayoutInflater inflater;
    List<SubActivityData> subActivityData = Collections.EMPTY_LIST;

    public SubActivityAdapter(Context context, List<SubActivityData> subActivityData) {
        inflater = LayoutInflater.from(context);
        this.subActivityData = subActivityData;
    }


    @Override
    public SubActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_card, parent, false);
        SubActivityViewHolder subActivityViewHolder = new SubActivityViewHolder(view);
        return subActivityViewHolder;
    }

    @Override
    public void onBindViewHolder(SubActivityViewHolder holder, int position) {
        SubActivityData currentCard = subActivityData.get(position);
        holder.title.setText(currentCard.cardTitle);

    }

    @Override
    public int getItemCount() {
        return subActivityData.size();
    }

    class SubActivityViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public SubActivityViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.card_text);
        }
    }
}