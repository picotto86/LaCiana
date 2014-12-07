package com.picotto86.laciana;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by picot_000 on 03/12/2014.
 */
public class NotizieFragment extends android.app.Fragment {

    private RecyclerView mRecyclerView;
    View rootView;
    private MyRecyclerAdapter adapter;
    private ArrayList<FeedItem> feedItemList = new ArrayList<FeedItem>();

    public NotizieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_notizie, container, false);

        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        final String url = "http://www.tabaccaiofurbo.it/cianas.php";

        new AsyncHttpTask().execute(url);

        return rootView;
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        ProgressDialog progressDialog;
        int progressStatus;

        @Override
        protected void onPreExecute() {
            //setProgressBarIndeterminateVisibility(true);
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Initializing Please Wait");
            progressDialog.setTitle("Loading");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();
            progressStatus = 0;
        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 1;
            HttpURLConnection urlConnection = null;
            String url = null;

            url=new String(params[0]);

            Document doc = null;

            try {
                doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
            } catch (IOException e) {
                e.printStackTrace();
            }


                    parseResult(doc);

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            //setProgressBarIndeterminateVisibility(false);
            /* Download complete. Lets update UI */
            if (result == 1) {
                adapter = new MyRecyclerAdapter(NotizieFragment.this, feedItemList);
                mRecyclerView.setAdapter(adapter);

            } else {
                Log.e("d:", "Failed to fetch data!");
            }

            progressDialog.dismiss();
        }
    }

    private void parseResult(Document doc) {



        Elements tabella=doc.select("table.tdvisualizza");

        if (null == feedItemList) {
            feedItemList = new ArrayList<FeedItem>();
        }

        Elements righe=tabella.select("tr");

            for(Element campi : righe) {

                if (!campi.text().equals("\u00a0") && !campi.text().equals("Ultime Notizie")) {

                    FeedItem item = new FeedItem();

                    feedItemList.add(item);

                    Element link=campi.getElementById("a.href");

                    //Element link=campi.select("a").first();

                    //Log.d("d:","link "+link.text());

                    //item.setTitle(campi.text().replaceAll("LINK",""));
                    item.setTitle(campi.toString());

                }
            }




    }
}
