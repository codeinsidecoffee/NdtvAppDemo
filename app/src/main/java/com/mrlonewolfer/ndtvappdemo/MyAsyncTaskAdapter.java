package com.mrlonewolfer.ndtvappdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MyAsyncTaskAdapter extends AsyncTask<String,Void,ArrayList<NewsBean>> {

    Context context;
    NewsBean newsBean;
    ArrayList<NewsBean> newsArrayList;
    String myurl;

    public MyAsyncTaskAdapter(Context context, String myurl, OnResponseListener listener) {
        this.context = context;
        this.myurl = myurl;
        this.listener = listener;
    }

    public interface OnResponseListener{
        void onResponse(ArrayList<NewsBean> newsBeanArrayList);
    }
    OnResponseListener listener;


    @Override
    protected ArrayList<NewsBean> doInBackground(String... strings) {

            doParsing();
        return newsArrayList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        newsArrayList= new ArrayList<>();
    }



    private void doParsing() {
        SAXParserFactory factory=SAXParserFactory.newInstance();

        try {
            SAXParser parser=factory.newSAXParser();
            DefaultHandler handler= new DefaultHandler(){
                boolean bTitle, bDescription, bStoryImage, bLink;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    if(localName.equals("title")) {
                        bTitle=true;
                        newsBean=new NewsBean();
                    }else if(localName.equals("StoryImage")){
                        bStoryImage=true;
                    }else if(localName.equals("description")){
                        bDescription=true;
                    }else if(localName.equals("link")){
                        bLink=true;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    if(bTitle){
                        bTitle=false;
                    }else if(bLink){
                        bLink=false;
                    }else if(bDescription){
                        bDescription=false;
                        newsArrayList.add(newsBean);
                    }else if(bStoryImage){
                        bStoryImage=false;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if(bTitle){
                        newsBean.setTitle(new String(ch,start,length));
                    }else if(bLink){
                        newsBean.setLink(new String(ch,start,length));
                    }else if(bDescription){
                        newsBean.setDescription(new String(ch,start,length));
                    }else if(bStoryImage){
                        newsBean.setStoryImage(new String(ch,start,length));
                    }
                }
            };
            parser.parse(myurl,handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<NewsBean> newsBeans) {
        super.onPostExecute(newsBeans);
        listener.onResponse(newsArrayList);
    }
}
