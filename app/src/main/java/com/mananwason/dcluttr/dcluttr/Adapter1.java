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
public class Adapter1 extends RecyclerView.Adapter<Adapter1.SubActivityViewHolder1> {

    private final LayoutInflater inflater;
    List<SubActivityData> subActivityData = Collections.EMPTY_LIST;

    public Adapter1(Context context, List<SubActivityData> subActivityData) {
        inflater = LayoutInflater.from(context);
        this.subActivityData = subActivityData;
    }


    @Override
    public SubActivityViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_card, parent, false);
        SubActivityViewHolder1 subActivityViewHolder = new SubActivityViewHolder1(view);
        return subActivityViewHolder;
    }

    @Override
    public void onBindViewHolder(SubActivityViewHolder1 holder, int position) {
        SubActivityData currentCard = subActivityData.get(position);
        holder.title.setText(currentCard.cardTitle);

    }

    @Override
    public int getItemCount() {
        return subActivityData.size();
    }

    class SubActivityViewHolder1 extends RecyclerView.ViewHolder {

        TextView title;

        public SubActivityViewHolder1(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.card_text);
        }
    }
}