package ua.teampush.appteampush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anna on 26.05.2015.
 */
public class RoomList extends BaseAdapter {
    private static ArrayList<Room> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public RoomList(Context context, ArrayList<Room> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.fragment_room, null);
            holder = new ViewHolder();
            holder.txt_Name = (TextView) convertView.findViewById(R.id.name);
            holder.txt_Admin = (TextView) convertView.findViewById(R.id.admin);
            holder.txt_Users = (TextView) convertView.findViewById(R.id.users);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_Name.setText(itemDetailsrrayList.get(position).getName());
        holder.txt_Admin.setText(itemDetailsrrayList.get(position).getAdmin());
        holder.txt_Users.setText(itemDetailsrrayList.get(position).getUsers());
        return convertView;
    }

    static class ViewHolder {
        TextView txt_Name;
        TextView txt_Admin;
        TextView txt_Users;
    }

}
