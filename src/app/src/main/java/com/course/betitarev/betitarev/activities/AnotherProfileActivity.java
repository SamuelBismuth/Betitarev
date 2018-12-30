package com.course.betitarev.betitarev.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.course.betitarev.betitarev.R;
import com.course.betitarev.betitarev.helper.FireBaseQuery;
import com.course.betitarev.betitarev.objects.BasicAdmin;
import com.course.betitarev.betitarev.objects.CurrentAdmin;
import com.course.betitarev.betitarev.objects.CurrentPlayer;
import com.course.betitarev.betitarev.objects.Friend;
import com.course.betitarev.betitarev.objects.Friends;
import com.course.betitarev.betitarev.objects.Mail;
import com.course.betitarev.betitarev.objects.User;
import com.course.betitarev.betitarev.objects.UsersNamesHashmap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * This activity is the profile of each {@link User}.
 */
public class AnotherProfileActivity extends AppCompatActivity {

    private static Mail email;
    private User friend;
    private Friend currentFriend;
    private ImageView mPictureSrc;
    private Animator mCurrentAnimator;  // Hold a reference to the current animator, so that it can be canceled mid-way.

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    /**
     * The function on create is call every time we create this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                name = null;
            } else {
                name = extras.getString("Name");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("Name");
        }
        Log.e("number of users name", "" + UsersNamesHashmap.getAllKeysForValue(name).size());
        friend = UsersNamesHashmap.getAllKeysForValue(name).get(0);
        Log.e("in another...", friend.getUserid() + "this is userid here");

        email = friend.getMail();
        TextView mNameTextView = findViewById(R.id.name);
        mNameTextView.setText(name);

        TextView mEmailTextView = findViewById(R.id.email);
        mEmailTextView.setText(friend.getMail().getMail());

        mPictureSrc = findViewById(R.id.profile_image);
        setProfileImage();

        final ImageView profileImageView = findViewById(R.id.profile_image);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(profileImageView, profileImageView.getDrawable());
            }
        });

        Button mAddFriendBtn = findViewById(R.id.btn_add_friend);
        mAddFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentPlayer.getInstance().getFriends().addFriend(currentFriend);
                Friends CurrentFriendFriends = friend.getFriends();
                CurrentFriendFriends.addFriend(new Friend(CurrentPlayer.getInstance().getName() + " " + CurrentPlayer.getInstance().getFamilyName(), CurrentPlayer.getInstance().getMail(), CurrentPlayer.getInstance().getPushToken()));
                FireBaseQuery.updateUserFriends(view.getContext(), friend);

            }
        });
        currentFriend = new Friend(friend.getName() + " " + friend.getFamilyName(), friend.getMail(), friend.getPushToken());
        if (CurrentPlayer.getInstance().getFriends().isFriend(currentFriend)) {
            mAddFriendBtn.setText("UNFRIEND");
            Log.e("isAlreadyFriend", "yes");
            mAddFriendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CurrentPlayer.getInstance().getFriends().removeFriend(currentFriend);
                    Friends CurrentFriendFriends = friend.getFriends();
                    CurrentFriendFriends.removeFriend(new Friend(CurrentPlayer.getInstance().getName() + " " + CurrentPlayer.getInstance().getFamilyName(), CurrentPlayer.getInstance().getMail(), CurrentPlayer.getInstance().getPushToken()));
                    FireBaseQuery.updateUserFriends(view.getContext(), friend);

                }
            });
        }

        //btn_remove properties
        Button btn_remove = findViewById(R.id.btn_remove);
        try {
            if (CurrentPlayer.getInstance().getMail().getMail().endsWith("betitarev.com")) {
                Log.e("removebutton", "mail: " + CurrentPlayer.getInstance().getMail().getMail().endsWith("betitarev.com"));
                btn_remove.setVisibility(View.VISIBLE);
                // need to think how to do this line: we need to insert some how basic admin//CurrentAdmin.getInstance(CurrentPlayer.getInstance().get,CurrentPlayer.getInstance().getUserid());
                Log.e("removebutton", "failed");
                BasicAdmin ba = new BasicAdmin(CurrentPlayer.getInstance().getName(), CurrentPlayer.getInstance().getName(), CurrentPlayer.getInstance().getMail(), CurrentPlayer.getInstance().getPushToken());
                CurrentAdmin.getInstance(ba, ba.getUserid());
            } else
                btn_remove.setVisibility(View.GONE);
        } catch (Exception e) {
            btn_remove.setVisibility(View.GONE);
            Log.e("removebuttonincatch", "oof");
        }

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("setonclick", "inside");
                Intent intent = new Intent(AnotherProfileActivity.this, MainActivity.class);
                Log.e("setonclick", "inside1");
                startActivity(intent);
                Log.e("setonclick", "inside2");

                Log.e("setonclick", friend.getUserid() + "this is userid here");
                CurrentAdmin.getInstance().removePlayer(friend);
                Log.e("setonclick", "removed another player");
            }
        });

    }

    /**
     * This function set the profile image.
     */
    private void setProfileImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("images/" + email.getMail() + "/profile");
        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(mPictureSrc);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("downloadImage", "failed");
            }
        });

    }

    /**
     * This function make a zoom when the {@link User} click in the profile image.
     *
     * @param thumbView
     * @param imageResId
     */
    private void zoomImageFromThumb(final View thumbView, Drawable imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = findViewById(
                R.id.expanded_image);
        expandedImageView.setImageDrawable(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.activity_profile_layout)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

}