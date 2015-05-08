package study.weilun.com.slidingtab.layout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by weilun on 2015/5/8.
 */
public class SlidingTabLayout extends HorizontalScrollView {

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerChangeListener ;

    private final SlidingTabStrip mTabStrip ;

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
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();
        this.mViewPager = viewPager;

        if( viewPager != null ) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    private void populateTabStrip() {
        final PagerAdapter dapter = mViewPager.getAdapter();
        final View.OnClickListener tabClickListener = new TabClickListener();
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
                if( v == mTabStrip.getChildAt(i) ){
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}
