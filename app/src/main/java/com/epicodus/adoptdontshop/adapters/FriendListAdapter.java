package com.epicodus.adoptdontshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.util.Log;
import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.models.Friend;
import com.epicodus.adoptdontshop.ui.FriendDetailActivity;
import com.epicodus.adoptdontshop.ui.FriendDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {

//    private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;

    private ArrayList<Friend> mFriends = new ArrayList<>();
    private Context mContext;

    public FriendListAdapter(Context context, ArrayList<Friend> friends) {
        mContext = context;
        mFriends = friends;
    }

    @Override
    public FriendListAdapter.FriendViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item, parent, false);
        FriendViewHolder viewHolder = new FriendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendListAdapter.FriendViewHolder holder, int position) {
        holder.bindFriend(mFriends.get(position));
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.friendImageView) ImageView mFriendImageView;
        @Bind(R.id.friendNameTextView) TextView mNameTextView;
        @Bind(R.id.ageTextView) TextView mAgeTextView;
        @Bind(R.id.animalTextView) TextView mAnimalTextView;

        private Context mContext;
        private int mOrientation;

        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, FriendDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_FRIENDS, Parcels.wrap(mFriends));
                mContext.startActivity(intent);
            }
        }

        public void bindFriend(Friend friend) {

//            ArrayList<String> ImageURLList = (mFriend.getImageURL());
//            String largeImageURL = ImageURLList.get(0).substring(0, ImageURLList.get(0).length() - 34).concat("o.jpg");
//            Picasso.with(view.getContext())
//                    .load(largeImageURL)
//                    .resize(MAX_WIDTH, MAX_HEIGHT)
//                    .centerCrop()
//                    .into(mImageLabel);

            mNameTextView.setText(friend.getName());
            mAgeTextView.setText("Age: " + friend.getAge());
            mAnimalTextView.setText(friend.getAnimal());
            List<String> ImageURLList = (friend.getImageURL());
            Picasso.with(mContext).load(ImageURLList.get(0)).into(mFriendImageView);
        }

        private void createDetailFragment(int position) {
            FriendDetailFragment detailFragment = FriendDetailFragment.newInstance(mFriends, position);

            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.friendDetailContainer, detailFragment);

            ft.commit();
        }

    }

}
