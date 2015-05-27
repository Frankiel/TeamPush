package ua.teampush.appteampush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Анна on 27.05.2015.
 */
public class MessageList extends BaseAdapter {
    private static ArrayList<Message> itemDetailsrrayList;

    private LayoutInflater l_Inflater;

    public MessageList(Context context, ArrayList<Message> results) {
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
            holder.txt_Text = (TextView) convertView.findViewById(R.id.name);
            holder.txt_User = (TextView) convertView.findViewById(R.id.admin);
            holder.txt_Date = (TextView) convertView.findViewById(R.id.users);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_Text.setText(itemDetailsrrayList.get(position).getText());
        holder.txt_User.setText(itemDetailsrrayList.get(position).getUser());
        holder.txt_Date.setText(itemDetailsrrayList.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView txt_Text;
        TextView txt_User;
        TextView txt_Date;
    }

}