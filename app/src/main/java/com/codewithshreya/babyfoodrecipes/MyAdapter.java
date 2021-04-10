package com.codewithshreya.babyfoodrecipes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.codewithshreya.babyfoodrecipes.R.layout.recyler_row_item;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mContext;
    private List<FoodData> myFoodList;
    private int lastPosition = -1;//check here



    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_row_item, viewGroup, false);
        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int position) {

        Glide.with(mContext).load(myFoodList.get(position).getItemImage()).into(foodViewHolder.imageView);
//check here
        foodViewHolder.imageView.setImageResource(Integer.parseInt(myFoodList.get(position).getItemImage()));
        foodViewHolder.mtitle.setText(myFoodList.get(position).getItemName());
        foodViewHolder.mDescription.setText(myFoodList.get(position).getItemDescription());
        foodViewHolder.mLocation.setText(myFoodList.get(position).getLocation());
        //activity for 1st page info of recipes.
        //when user click on cardView thus connecting on clickListener.
        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);

                //check here

                intent.putExtra("Mom-Chef-Name", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Image", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemImage());
                //intent.putExtra("Ingredient", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Description", myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Location", myFoodList.get(foodViewHolder.getAdapterPosition()).getLocation());
                mContext.startActivity(intent);
                //intent for upload activity

            }
        });
        setAnimation(foodViewHolder.itemView, position);

    }

    public void setAnimation(View viewToAnimate, int position)
    {
        if(position> lastPosition)
        {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f,  0.0f, 1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {

        myFoodList = filterList;
        notifyDataSetChanged();
    }
}
class FoodViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mtitle, mDescription, mLocation;
    CardView mCardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.ivImage);
        mtitle = (TextView)itemView.findViewById(R.id.tvTitle);
        mDescription = (TextView)itemView.findViewById(R.id.tvDescription);
        mLocation = (TextView)itemView.findViewById(R.id.tvLocation);

        mCardView = (CardView)itemView.findViewById(R.id.myCardView);
    }
}