package com.one.customviewsample.viewpager2;

import android.util.Log;

import com.one.customviewsample.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author diaokaibin@gmail.com on 2020/10/27.
 */
public class AdapterFragmentPager extends FragmentStateAdapter {
    private long idToday = R.id.fragment_today;
    private long idThreeDay = R.id.fragment_three_day;
    private long idTraffic1 = R.id.fragment_traffic1;
    private long idTraffic2 = R.id.fragment_traffic2;
    private long idMemo1 = R.id.fragment_memo1;
    private long idMemo2 = R.id.fragment_memo2;


    private ArrayList<Long> ids = new ArrayList<>();
    private Set<Long> mSet = new LinkedHashSet<>();

    public AdapterFragmentPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    public void update() {

        ids.clear();
        mSet.clear();

        ids.add(idToday);
        ids.add(idThreeDay);

        mSet.add(idToday);
        mSet.add(idThreeDay);

        notifyDataSetChanged();
    }

    public void update1() {

        ids.clear();
        mSet.clear();

        ids.add(idToday);
        ids.add(idThreeDay);
        ids.add(idMemo1);


        mSet.add(idToday);
        mSet.add(idThreeDay);
        mSet.add(idMemo1);

        notifyDataSetChanged();

    }

    public void update2() {

        ids.clear();
        mSet.clear();

        ids.add(idToday);
        ids.add(idThreeDay);
        ids.add(idTraffic1);
        ids.add(idMemo1);

        mSet.add(idTraffic1);
        mSet.add(idMemo1);
        mSet.add(idToday);
        mSet.add(idThreeDay);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        long id = ids.get(position);
        if (id == idToday) {
            Log.i("---", "  create TodayWeatherFragment  position : " + position);
            return new TodayWeatherFragment();
        } else if (id == idThreeDay) {
            Log.i("---", "  create ThreeDaysWeatherFragment  position : " + position);
            return new ThreeDaysWeatherFragment();

        } else if (id == idTraffic1) {
            Log.i("---", "  create Traffic1Fragment  position : " + position);
            return new Traffic1Fragment();
        } else if (id == idTraffic2) {
            Log.i("---", "  create Traffic2Fragment  position : " + position);
            return new Traffic2Fragment();

        } else if (id == idMemo1) {
            Log.i("---", "  create Memo1Fragment  position : " + position);
            return new Memo1Fragment();
        } else if (id == idMemo2) {
            Log.i("---", "  create Memo2Fragment  position : " + position);
            return new Memo2Fragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    @Override
    public long getItemId(int position) {
        return ids.get(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return mSet.contains(itemId);
    }


}
