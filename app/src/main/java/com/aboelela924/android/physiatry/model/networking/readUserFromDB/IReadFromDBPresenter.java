package com.aboelela924.android.physiatry.model.networking.readUserFromDB;

import com.aboelela924.android.physiatry.model.IBasicPresenter;
import com.aboelela924.android.physiatry.model.dataModeling.User;

public interface IReadFromDBPresenter extends IBasicPresenter {

    void onSuccess(User user);

}
