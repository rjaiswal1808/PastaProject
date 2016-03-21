package com.helpiez.app.volley;


import com.helpiez.app.model.BusinessObject;

public class Interfaces {

    public interface IDataRetrievalListener{
        abstract void onDataRetrieved(BusinessObject businessObject);
    }

    public interface IDataRetrievalListenerString{
        public void onDataRetrieved(String string);
    }
}
