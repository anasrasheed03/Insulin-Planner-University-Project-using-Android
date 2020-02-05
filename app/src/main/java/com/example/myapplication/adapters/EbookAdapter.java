package com.example.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.EbookData;
import com.example.myapplication.R;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {



    private List<EbookData> heroList;
    private Context context;

    private static int currentPosition = 0;

  public   EbookAdapter(List<EbookData> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public EbookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_ebook, parent, false);
        return new EbookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EbookViewHolder holder, final int position) {
        EbookData hero = heroList.get(position);
        holder.textViewName.setText(hero.getBookName());
        holder.textViewRealName.setText(hero.getId());
        holder.textViewTeam.setText(hero.getTitle());
        holder.textViewFirstAppearance.setText(hero.getShortDescp());
        holder.textViewCreatedBy.setText(hero.getBookTopic());
        holder.textViewPublisher.setText(hero.getBookTxt());
        holder.textViewBio.setText(hero.getDocEmail().trim());

        Glide.with(context).load(hero.getBookName()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class EbookViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;

        EbookViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
