package ua.teampush.appteampush;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anna on 26.05.2015.
 */
public class ItemList extends BaseAdapter {

        private static ArrayList<Item> itemDetailsrrayList;

        private Integer[] imgid = {
                R.drawable.ic_action_group,
                R.drawable.ic_action_settings,
                R.drawable.ic_action_about,
                R.drawable.ic_out,
                R.drawable.ic_action
        };

        private LayoutInflater l_Inflater;

        public ItemList(Context context, ArrayList<Item> results) {
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
                convertView = l_Inflater.inflate(R.layout.fragment_main, null);
                holder = new ViewHolder();
                holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
                holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
            holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);

            return convertView;
        }

        static class ViewHolder {
            TextView txt_itemName;
            ImageView itemImage;
        }

    }
