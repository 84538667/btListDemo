package com.example.btlistDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ListView lv;
    private int lastposition = -1;
    MyAdapter ma;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv = (ListView)findViewById(R.id.lv);
        ma = new MyAdapter( this );
        lv.setAdapter(ma);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                ma.setPos( position, lastposition );
                ma.notifyDataSetChanged();

                updatePos(position);
            }
        });
    }

    private void updatePos( int position ){
        if( lastposition == position )
            lastposition = -1 ;
        else
            lastposition = position;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 20; i++) {
            Map<String, Object> childdata = new HashMap<String, Object>();
            childdata.put("text", i);
            data.add(childdata);
        }

        return data;
    }

    public class MyAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        private List<Map<String, Object>> mData;
        private int thispos = -1;
        private int lastpos = -1;
        public MyAdapter(Context context ) {
            this.mInflater = LayoutInflater.from(context);
            mData =getData();
        }

        public void setPos(int thispos, int lastpos ){
            this.thispos = thispos;
            this.lastpos = lastpos;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        public final class ViewHolder {
            public listCell lc;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            convertView = null;
            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_cell, null);

                holder.lc = (listCell) convertView.findViewById(R.id.lcell);

                initLC( holder, position );
                showOrHideButton(holder, position);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.lc.setText(""+ mData.get(position).get("text"));

            return convertView;
        }

        private void showOrHideButton( ViewHolder holder, int position ){
            if(position == thispos && thispos != lastpos )  {
                holder.lc.showButton();
            }
            if( position == lastpos ){
                holder.lc.hideButton();
            }
        }

        private void initLC( ViewHolder holder, int position ){
            if( position != lastpos ){
                holder.lc.initCell();
            }
        }
    }
}
