package app.originality.com.originality.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import app.originality.com.originality.R;

public class MenuFragmentAdapter extends OriginalityBaseAdapter<String> {

    public MenuFragmentAdapter(Activity act, List<String> data) {
        super(act, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mAct).inflate(R.layout.item_menu_fragment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mData.get(position));
        return convertView;
    }

    private class ViewHolder {

        private TextView textView;

        public ViewHolder(View convertView) {
            this.textView = (TextView) convertView.findViewById(R.id.id_menu_content);
        }
    }
}