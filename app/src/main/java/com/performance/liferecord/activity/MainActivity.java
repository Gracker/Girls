package com.performance.liferecord.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.performance.liferecord.R;
import com.performance.liferecord.fragment.GirlFragment;
import com.performance.liferecord.fragment.PostFragment;
import com.performance.liferecord.utils.MessageEvent;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.performance.liferecord.R.id.bottomBarItemActivity;
import static com.performance.liferecord.R.id.bottomBarItemMain;
import static com.performance.liferecord.R.id.bottomBarItemSettings;
import static com.performance.liferecord.R.id.bottomBarItemVideos;
import static com.performance.liferecord.R.id.bottomBarItemWeb;

public class MainActivity extends AppCompatActivity {

    private static final int BOTTOM_ITEM_TITLE_MEIZI_INDEX = 0;
    private static final int BOTTOM_ITEM_TITLE_ANDROID_INDEX = 1;
    private static final int BOTTOM_ITEM_TITLE_IOS_INDEX = 2;
    private static final int BOTTOM_ITEM_TITLE_VIDEO_INDEX = 3;
    private static final int BOTTOM_ITEM_TITLE_WEB_INDEX = 4;
    private static final String BOTTOM_ITEM_TITLE_MEIZI = "福利";
    private static final String BOTTOM_ITEM_TITLE_ANDROID = "Android";
    private static final String BOTTOM_ITEM_TITLE_IOS = "iOS";
    private static final String BOTTOM_ITEM_TITLE_VIDEO = "休息视频";
    private static final String BOTTOM_ITEM_TITLE_Web = "前端";
    private BottomBar mBottomBar;
    private Fragment mGirlFragment;
    private Fragment mAndroidFragment;
    private Fragment mIOSFragment;
    private Fragment mVideoFragment;
    private Fragment mWebFragment;
    private int mCurrentFragmentIndex = 0;
    private boolean mGirlFragmentAdded = false;
    private boolean mAndroidFragmentAdded = false;
    private boolean mIOSFragmentAdded = false;
    private boolean mVideoFragmentAdded = false;
    private boolean mWebFragmentAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomBar(savedInstanceState);
    }

    private void initBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();
        mBottomBar.useFixedMode(); // show all title and icon on bottom bar
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                Log.v("Gracker", "onMenuTabSelected id = " + menuItemId);
                switch (menuItemId) {
                    case bottomBarItemActivity:
                        switchToFragment(BOTTOM_ITEM_TITLE_MEIZI_INDEX);
                        break;
                    case bottomBarItemMain:
                        switchToFragment(BOTTOM_ITEM_TITLE_ANDROID_INDEX);
                        break;
                    case bottomBarItemSettings:
                        switchToFragment(BOTTOM_ITEM_TITLE_IOS_INDEX);
                        break;
                    case bottomBarItemVideos:
                        switchToFragment(BOTTOM_ITEM_TITLE_VIDEO_INDEX);
                        break;
                    case bottomBarItemWeb:
                        switchToFragment(BOTTOM_ITEM_TITLE_WEB_INDEX);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                Log.v("Gracker", "onMenuTabSelected id = " + menuItemId);
                switch (menuItemId) {
                    case bottomBarItemActivity:
                        refreshData(BOTTOM_ITEM_TITLE_MEIZI_INDEX);
                        break;
                    case bottomBarItemMain:
                        refreshData(BOTTOM_ITEM_TITLE_ANDROID_INDEX);
                        break;
                    case bottomBarItemSettings:
                        refreshData(BOTTOM_ITEM_TITLE_IOS_INDEX);
                        break;
                    case bottomBarItemVideos:
                        refreshData(BOTTOM_ITEM_TITLE_VIDEO_INDEX);
                        break;
                    case bottomBarItemWeb:
                        refreshData(BOTTOM_ITEM_TITLE_WEB_INDEX);
                        break;
                    default:
                        break;
                }
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(BOTTOM_ITEM_TITLE_MEIZI_INDEX, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(BOTTOM_ITEM_TITLE_ANDROID_INDEX, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(BOTTOM_ITEM_TITLE_IOS_INDEX, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        mBottomBar.mapColorForTab(BOTTOM_ITEM_TITLE_VIDEO_INDEX, ContextCompat.getColor(this, R.color.colorLightBlue));
        mBottomBar.mapColorForTab(BOTTOM_ITEM_TITLE_WEB_INDEX, ContextCompat.getColor(this, R.color.cpb_default_color));
        mBottomBar.setActiveTabColor("#009688");
    }

    private void refreshData(int menuItemId) {
    }

    private void switchToFragment(int i) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);

        if (mCurrentFragmentIndex == i) {
            fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
            fragmentTransaction.commitAllowingStateLoss();
            mCurrentFragmentIndex = i;
            if (i == BOTTOM_ITEM_TITLE_MEIZI_INDEX) {
                mGirlFragmentAdded = true;
            }
            return;
        }

        fragmentTransaction.hide(getFragment(mCurrentFragmentIndex));

        switch (i) {
            case BOTTOM_ITEM_TITLE_MEIZI_INDEX:
                if (mGirlFragmentAdded) {
                    fragmentTransaction.show(getFragment(i));
                } else {
                    fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
                    mGirlFragmentAdded = true;
                }
                break;
            case BOTTOM_ITEM_TITLE_ANDROID_INDEX:
                if (mAndroidFragmentAdded) {
                    fragmentTransaction.show(getFragment(i));
                } else {
                    fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
                    mAndroidFragmentAdded = true;
                }
                break;
            case BOTTOM_ITEM_TITLE_IOS_INDEX:
                if (mIOSFragmentAdded) {
                    fragmentTransaction.show(getFragment(i));
                } else {
                    fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
                    mIOSFragmentAdded = true;
                }
                break;
            case BOTTOM_ITEM_TITLE_VIDEO_INDEX:
                if (mVideoFragmentAdded) {
                    fragmentTransaction.show(getFragment(i));
                } else {
                    fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
                    mVideoFragmentAdded = true;
                }
                break;
            case BOTTOM_ITEM_TITLE_WEB_INDEX:
                if (mWebFragmentAdded) {
                    fragmentTransaction.show(getFragment(i));
                } else {
                    fragmentTransaction.add(R.id.fragmentContainer, getFragment(i));
                    mWebFragmentAdded = true;
                }
                break;
            default:
                break;
        }


        fragmentTransaction.commitAllowingStateLoss();
        mCurrentFragmentIndex = i;
    }

    private Fragment getFragment(int menuItemId) {
        switch (menuItemId) {
            case BOTTOM_ITEM_TITLE_MEIZI_INDEX:
                if (mGirlFragment == null) {
                    mGirlFragment = GirlFragment.newInstance(BOTTOM_ITEM_TITLE_MEIZI);
                }
                return mGirlFragment;
            case BOTTOM_ITEM_TITLE_ANDROID_INDEX:
                if (mAndroidFragment == null) {
                    mAndroidFragment = PostFragment.newInstance(BOTTOM_ITEM_TITLE_ANDROID);
                }
                return mAndroidFragment;
            case BOTTOM_ITEM_TITLE_IOS_INDEX:
                if (mIOSFragment == null) {
                    mIOSFragment = PostFragment.newInstance(BOTTOM_ITEM_TITLE_IOS);
                }
                return mIOSFragment;
            case BOTTOM_ITEM_TITLE_VIDEO_INDEX:
                if (mVideoFragment == null) {
                    mVideoFragment = PostFragment.newInstance(BOTTOM_ITEM_TITLE_VIDEO);
                }
                return mVideoFragment;
            case BOTTOM_ITEM_TITLE_WEB_INDEX:
                if (mWebFragment == null) {
                    mWebFragment = PostFragment.newInstance(BOTTOM_ITEM_TITLE_Web);
                }
                return mWebFragment;
            default:
                break;

        }
        return null;
    }

    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
