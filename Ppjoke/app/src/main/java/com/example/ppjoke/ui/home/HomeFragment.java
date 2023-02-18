package com.example.ppjoke.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;

import com.example.libnavannotation.FragmentDestination;
import com.example.ppjoke.databinding.FragmentHomeBinding;
import com.example.ppjoke.exoplayer.PageListPlayDetector;
import com.example.ppjoke.model.Feed;
import com.example.ppjoke.ui.AbsListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

@FragmentDestination(pageUrl = "main/tabs/home",asStarter = true)
public class HomeFragment extends AbsListFragment<Feed, HomeViewModel> {
    private FragmentHomeBinding binding;
    private PageListPlayDetector playDetector;
    private String feedType;
    private boolean shouldPause = true;

    public static HomeFragment newInstance(String feedType) {
        Bundle args = new Bundle();
        args.putString("feedType", feedType);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider( this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        playDetector = new PageListPlayDetector(this, mRecyclerView);
        return root;
    }

    @Override
    public PagedListAdapter getAdapter() {
        feedType = getArguments() == null ? "all" : getArguments().getString("feedType");
        return new FeedAdapter(getContext(), feedType) ;
//        return new FeedAdapter(getContext(), feedType) {
//            @Override
//            public void onViewAttachedToWindow2(@NonNull ViewHolder holder) {
//                if (holder.isVideoItem()) {
//                    playDetector.addTarget(holder.getListPlayerView());
//                }
//            }
//
//            @Override
//            public void onViewDetachedFromWindow2(@NonNull ViewHolder holder) {
//                playDetector.removeTarget(holder.getListPlayerView());
//            }
//
//            @Override
//            public void onStartFeedDetailActivity(Feed feed) {
//                boolean isVideo = feed.itemType == Feed.TYPE_VIDEO;
//                shouldPause = !isVideo;
//            }
//
//            @Override
//            public void onCurrentListChanged(@Nullable PagedList<Feed> previousList, @Nullable PagedList<Feed> currentList) {
//                //这个方法是在我们每提交一次 pagelist对象到adapter 就会触发一次
//                //每调用一次 adpater.submitlist
//                if (previousList != null && currentList != null) {
//                    if (!currentList.containsAll(previousList)) {
//                        mRecyclerView.scrollToPosition(0);
//                    }
//                }
//            }
//        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}