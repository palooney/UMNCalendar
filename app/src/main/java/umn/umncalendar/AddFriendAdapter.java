package umn.umncalendar;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by AartiRajan on 4/4/2017.
 */

public class AddFriendAdapter extends ArrayAdapter<User> {
    // List<Event> eventList;
    public AddFriendAdapter(@NonNull Context context, List<User> events) {
        super(context, R.layout.custom_row_friends, events);
        //eventList = events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row_add_friend, parent, false);

        User user = getItem(position);
        TextView name = (TextView) customView.findViewById(R.id.friend_name);
        ImageView pic = (ImageView) customView.findViewById(R.id.friend_pic);

        name.setText(user.getUser_name());
        pic.setImageResource(user.getUser_pic());
        setAddFriendListener(customView,user);
        return customView;
    }

    public void setAddFriendListener(View customView, final User friend) {
        final Button addThisFriend = (Button) customView.findViewById(R.id.add_friend);
        // cancelRsvp.setText("Add");
        addThisFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setMessage("Are you sure you want to add " + friend.getUser_name() + " to your friend list?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (!User.getFriends().contains(friend)) {
                            User.getAllUsers().remove(friend);
                            User.getFriends().add(friend);
                            Toast.makeText(getContext(), "Friend request has been sent to " + friend.getUser_name(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }

                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}