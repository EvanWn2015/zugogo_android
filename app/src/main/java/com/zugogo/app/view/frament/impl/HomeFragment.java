package com.zugogo.app.view.frament.impl;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.common.collect.Lists;
import com.zugogo.app.R;
import com.zugogo.app.view.frament.abs.AbsMenuFragment;
import com.zugogo.app.model.api.ApiManager;
import com.zugogo.app.model.api.ThreadCallback;
import com.zugogo.app.view.customize.layout.OnLoadLayout;
import com.zugogo.app.util.image.NetworkImageHolderView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.zugogo.app.view.activity.BasisActivity.LOADING_BAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends AbsMenuFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private static HomeFragment FRAGMENT = null;
    private Context context;
    private Handler handler;
    private ConvenientBanner convenientBanner;
    private OnLoadLayout onLoadLayout;
    private HotSpotsAdapter adapter;
    private ListView listView;
    private List<String> list = Lists.newArrayList();

    // 輪播圖片資料
    private List<String> networkImages = Lists.newArrayList();
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static AbsMenuFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Object... o) {
        return new HomeFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Bundle bundle) {
        Log.d(TAG, "");
        return new HomeFragment();
    }

    public static AbsMenuFragment getInstance(Bundle bundle) {
        if (FRAGMENT == null) {
            FRAGMENT = new HomeFragment();
            FRAGMENT.setArguments(bundle);
        }
        return FRAGMENT;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.lv_hot_spots);
        onLoadLayout = view.findViewById(R.id.fragment_ptr_frame);
        adapter = new HotSpotsAdapter(inflater, list);
        listView.setAdapter(adapter);

        // TODO 輪播效果
        convenientBanner = view.findViewById(R.id.convenientBanner);
        networkImages.clear();
        networkImages.addAll(Arrays.asList(images));
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.d(TAG, "image click event");
                    }
                });
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.startTurning(3000);
        // TODO 輪播壓測
//        convenientBanner.startTurning(100);

        handler = new Handler();
        onLoadLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                list.clear();
                for (int i = 0; i < 10; i++) {
                    new Random().nextInt(100);
                    list.add("Refres" + new Random().nextInt(100));
                }
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(list);
                        onLoadLayout.refreshComplete();
                    }
                }, 500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                return ((OnLoadLayout) frame).isTop();

            }
        });

        onLoadLayout.setOnLoadListener(new OnLoadLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                for (int i = 0; i < 10; i++) {
                    new Random().nextInt(100);
                    list.add("" + new Random().nextInt(100));
                }
                adapter.setData(list);
            }
        });

        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        testRequest();

        // TODO request 壓力測試
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                testRequest();
//                handler.post(this);
//            }
//        },100);

    }


    private void testRequest(){
        LOADING_BAR.show();
        ApiManager.test(new ThreadCallback() {
            @Override
            public void onSuccess(String responseBody) {
                Log.d(TAG, responseBody);

                for (int i = 1; i < 20; i++) {
                    list.add(i + "_title");
                }
                adapter.setData(list);
                LOADING_BAR.hide();
            }

            @Override
            public void onFail(Exception error) {
                Log.d(TAG, "", error);
                LOADING_BAR.hide();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        networkImages.clear();
        networkImages.addAll(Arrays.asList(images));
        convenientBanner.notifyDataSetChanged();
    }

    public class HotSpotsAdapter extends BaseAdapter {
        private LayoutInflater inflater = null;
        private List<String> list;

        HotSpotsAdapter(LayoutInflater inflater, List list) {
            this.list = list;
            this.inflater = inflater;
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

        public void setData(List<String> list) {
            this.list = list;
            this.notifyDataSetChanged();
        }

        @Nullable
        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }
    }

    static class ViewHolder {
        public TextView title;
    }

}
