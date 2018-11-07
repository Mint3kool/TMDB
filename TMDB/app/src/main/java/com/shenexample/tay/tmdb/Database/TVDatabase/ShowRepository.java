package com.shenexample.tay.tmdb.Database.TVDatabase;

import android.app.Application;

import com.shenexample.tay.tmdb.TV.ShowFragment;

public class ShowRepository {

    private ShowDAO showDAO;
    private ShowFragment showFragment;

    public ShowRepository(Application app, ShowFragment fragment) {
        ShowDatabaseInit db = ShowDatabaseInit.getDatabase(app);
        showDAO = db.ShowDAO();
        showFragment = fragment;
    }

    public Show getShow(int id) {
        return showDAO.getShow(id);
    }
}
