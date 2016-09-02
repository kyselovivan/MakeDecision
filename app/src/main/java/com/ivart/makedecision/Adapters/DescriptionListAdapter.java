package com.ivart.makedecision.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.ivart.makedecision.Model.Decision;
import com.ivart.makedecision.Model.DecisionDescription;
import com.ivart.makedecision.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by Ivan on 9/2/2016.
 */

public class DescriptionListAdapter extends RealmBaseAdapter<DecisionDescription> implements ListAdapter {

    RealmResults<DecisionDescription> realmResults;

    private static class ViewHolder {
        TextView textStamp;
        TextView raitStamp;
    }

    public DescriptionListAdapter(Context context, OrderedRealmCollection<DecisionDescription> realmResults) {
        super(context, realmResults);
        this.realmResults = (RealmResults<DecisionDescription>) realmResults;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DescriptionListAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.description_item, parent, false);
            viewHolder = new DescriptionListAdapter.ViewHolder();
            viewHolder.textStamp = (TextView) convertView.findViewById(R.id.txtv_description_text);
            viewHolder.raitStamp = (TextView) convertView.findViewById(R.id.txtv_description_rait);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DescriptionListAdapter.ViewHolder) convertView.getTag();
        }

        DecisionDescription item = adapterData.get(position);
        viewHolder.textStamp.setText(item.getDescriptionText());
        viewHolder.raitStamp.setText(""+item.getRaiting());
        return convertView;
    }
}