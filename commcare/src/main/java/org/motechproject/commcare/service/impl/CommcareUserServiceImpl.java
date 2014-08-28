package org.motechproject.commcare.service.impl;

import com.google.gson.reflect.TypeToken;
import org.motechproject.commcare.domain.CommcareUser;
import org.motechproject.commcare.domain.CommcareUsersJson;
import org.motechproject.commcare.service.CommcareUserService;
import org.motechproject.commcare.client.CommCareAPIHttpClient;
import org.motechproject.commons.api.json.MotechJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CommcareUserServiceImpl implements CommcareUserService {

    private MotechJsonReader motechJsonReader;

    private CommCareAPIHttpClient commcareHttpClient;

    @Autowired
    public CommcareUserServiceImpl(CommCareAPIHttpClient commcareHttpClient) {
        this.commcareHttpClient = commcareHttpClient;
        this.motechJsonReader = new MotechJsonReader();
    }

     @Override
    public List<CommcareUser> getCommcareUsers(Integer pageSize, Integer pageNumber) {
        String response = commcareHttpClient.usersRequest(pageSize, pageNumber);
        Type commcareUserType = new TypeToken<CommcareUsersJson>() {
                } .getType();
        CommcareUsersJson allUsers = (CommcareUsersJson) motechJsonReader.readFromString(response, commcareUserType);

         return allUsers.getObjects();
    }

    @Override
    public CommcareUser getCommcareUserById(String id) {
        String response = commcareHttpClient.userRequest(id);
        Type commcareUserType = new TypeToken<CommcareUser>() {
        } .getType();
        CommcareUser user = (CommcareUser) motechJsonReader.readFromString(response, commcareUserType);

        return user;
    }
}
