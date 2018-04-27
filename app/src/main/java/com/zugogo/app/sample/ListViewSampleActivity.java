package com.zugogo.app.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.zugogo.app.R;
import com.zugogo.app.view.activity.BasisActivity;
import com.zugogo.app.view.customize.layout.OnLoadLayout;

import java.util.ArrayList;
import java.util.Random;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class ListViewSampleActivity extends BasisActivity {

    private static final String TAG = ListViewSampleActivity.class.getSimpleName();
    private Context context;
    private OnLoadLayout ptrFrame;
    private ListView mListView;
    private MyDataListAdapter adapter;
    private ArrayList<String> list = Lists.newArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);
        context = this;


        ptrFrame = findViewById(R.id.list_view_with_empty_view_fragment_ptr_frame);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                list.clear();
                for(int i=0; i< 10 ; i++){
                    new Random().nextInt(100);
                    list.add("Refres" + new Random().nextInt(100));
                }
                adapter.setData(list);
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        adapter.setData(Lists.newArrayList("VVV", "FGFGF"));
                        ptrFrame.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                Log.d(TAG, ":::"+ ((OnLoadLayout)frame).isTop());
                return ((OnLoadLayout)frame).isTop();
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });


        ptrFrame.setOnLoadListener(new OnLoadLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                for(int i=0; i< 10 ; i++){
                    new Random().nextInt(100);
                    list.add("OnLoad" + new Random().nextInt(100));
                }
                adapter.setData(list);
            }
        });

        mListView = findViewById(R.id.list_view_with_empty_view_fragment_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        for(int i=0; i< 10 ; i++){
            new Random().nextInt(100);
            list.add("OnLoad" + new Random().nextInt(100));
        }

        adapter = new MyDataListAdapter(context, list);
        mListView.setAdapter(adapter);
    }


    class MyDataListAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater = null;
        private ArrayList<String> list;

        public MyDataListAdapter(Context context, ArrayList list) {
            this.context = context;
            this.list = list;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.item_sample, null);
                holder.title = view.findViewById(R.id.tv_item);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            // TODO item view
            holder.title.setText(list.get(i));
            return view;
        }

        @Nullable
        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }

        public void setData(ArrayList<String> list) {
            this.list = list;
            this.notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        public TextView title;
    }
}
