package com.ivart.makedecision.Adapters;

/**
 * Created by Ivan on 8/31/2016.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class DecisionListAdapter extends RealmBaseAdapter<Decision> implements ListAdapter {

    RealmResults<Decision> realmResults;

    private static class ViewHolder {
        ImageView imgStamp;
        TextView timestamp;
    }

    public DecisionListAdapter(Context context, OrderedRealmCollection<Decision> realmResults) {
        super(context, realmResults);
        this.realmResults = (RealmResults<Decision>)realmResults;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.decision_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.txtv_decision_name);
            viewHolder.imgStamp = (ImageView) convertView.findViewById(R.id.imgv_decision_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Decision item = adapterData.get(position);
        viewHolder.imgStamp.setImageResource(R.mipmap.logo);
        viewHolder.timestamp.setText(item.getmDecisionName());
        return convertView;
    }

    public RealmResults<Decision> getRealmResults(){
        return realmResults;
    }
}
