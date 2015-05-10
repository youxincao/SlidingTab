package study.weilun.com.slidingtab.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * Created by weilun on 2015/5/8.
 */
public class SlidingTabLayout extends HorizontalScrollView {

    public interface TabColorizer {
        int getIndicatorColor(int position);

        int getDividerColor(int position);
    }

    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerChangeListener;

    private final SlidingTabStrip mTabStrip;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // TODO  check the function
        setHorizontalScrollBarEnabled(false);

        // TODO check this function
        setFillViewport(true);

        mTabStrip = new SlidingTabStrip(context);
        addView(mTabStrip, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置显示内容的ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();
        this.mViewPager = viewPager;

        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final View.OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < adapter.getCount(); i++) {
            TextView textView = createDefaultTabView(getContext());
            textView.setText(adapter.getPageTitle(i));
            textView.setOnClickListener(tabClickListener);
            mTabStrip.addView(textView);
        }
    }

    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        return textView;
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}
