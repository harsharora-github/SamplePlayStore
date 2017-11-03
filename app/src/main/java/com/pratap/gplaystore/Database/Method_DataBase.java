package com.pratap.gplaystore.Database;

/**
 * Created by harsh.arora on 02-11-2017.
 */

public class Method_DataBase {


    private String app_url;
    private String app_package;




    public Method_DataBase() {
    }

    public Method_DataBase(String app_url,String app_package) {

        this.app_url=app_url;
        this.app_package=app_package;
    }




    public String getAppURL() {
        return app_url;
    }

    public void setAppURL(String app_url) {
        this.app_url = app_url;
    }

    public  String getAppPackage(){
        return app_package;
    }

    public void setApp_package(String app_package){
        this.app_package = app_package;
    }
}
