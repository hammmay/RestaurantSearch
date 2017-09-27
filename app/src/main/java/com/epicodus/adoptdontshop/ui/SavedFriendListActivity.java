package com.epicodus.adoptdontshop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.adoptdontshop.Constants;
import com.epicodus.adoptdontshop.R;
import com.epicodus.adoptdontshop.adapters.FirebaseFriendViewHolder;
import com.epicodus.adoptdontshop.models.Friend;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedFriendListActivity extends AppCompatActivity {
        private DatabaseReference mFriendReference;
        private FirebaseRecyclerAdapter mFirebaseAdapter;

        @Bind(R.id.recyclerView)
        RecyclerView mRecyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_friends);
            ButterKnife.bind(this);

            mFriendReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FRIENDS);
            setUpFirebaseAdapter();
        }

        private void setUpFirebaseAdapter() {
            mFirebaseAdapter = new FirebaseRecyclerAdapter<Friend, FirebaseFriendViewHolder>
                    (Friend.class, R.layout.friend_list_item, FirebaseFriendViewHolder.class,
                            mFriendReference) {

                @Override
                protected void populateViewHolder(FirebaseFriendViewHolder viewHolder,
                                                  Friend model, int position) {
                    viewHolder.bindFriend(model);
                }
            };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mFirebaseAdapter.cleanup();
        }
    }