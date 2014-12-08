package com.picotto86.laciana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFragment extends android.app.Fragment {

    private WebView mWebView;
    private String url;

    public WebFragment() {
    }

    public WebFragment(String title) {

        url=title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.web_view, container, false);

        mWebView=(WebView)rootView.findViewById(R.id.webView);

        if(mWebView != null){
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }


        return rootView;
    }

}
