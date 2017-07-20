package com.pan.thirdpartymodule.ui.share.weibo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author : Pan
 * Date : 20/07/2017
 */

public class WeiBoShareActivity extends BaseActivity<WeiBoSharePresenter>
        implements WbShareCallback, WeiBoShareView {

    @BindView(R.id.iv_image)
    ImageView mImage;
    @BindView(R.id.tv_text)
    TextView mText;
    @BindView(R.id.btn_share)
    Button mShare;

    private WbShareHandler mWbShareHandler;

    @Override
    protected int initContentView() {
        return R.layout.activity_weibo_share;
    }

    @Override
    protected void init() {
        this.mWbShareHandler = new WbShareHandler(this);
        mWbShareHandler.registerApp();
        mPresenter.attachView(this);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected WeiBoSharePresenter bindPresenter() {
        return new WeiBoSharePresenterImpl(this);
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWbShareHandler.doResultIntent(intent, this);
    }

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, getString(R.string.cancel), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.fail), Toast.LENGTH_LONG).show();
    }

    @Override
    public String getText() {
        return mText.getText().toString();
    }

    @Override
    public Bitmap getBitmap() {
        BitmapDrawable drawable = (BitmapDrawable) mImage.getDrawable();
        return drawable.getBitmap();
    }

    @OnClick(R.id.btn_share)
    public void share() {
        mPresenter.sendMessage(mWbShareHandler, true, true);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WeiBoShareActivity.class);
        context.startActivity(intent);
    }
}
