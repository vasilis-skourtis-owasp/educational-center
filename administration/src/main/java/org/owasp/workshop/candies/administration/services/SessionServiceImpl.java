package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.dtos.UserSessionInfo;
import org.owasp.workshop.candies.administration.model.User;
import org.owasp.workshop.candies.administration.model.UserRoleValue;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService{

    private Map<String, UserSessionInfo> sessionRegistry = new HashMap<>();
    private int counter = 0;

    @Override
    public UserSessionInfo createSessionForUser(User user) {
        String sessionToken = createSessionKey();
        UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), user.getUsername(), user.getUserRole(), sessionToken);

        sessionRegistry.put(sessionToken, userSessionInfo);

        return userSessionInfo;
    }

    @Override
    public UserSessionInfo getUserSessionInfo(String sessionToken) {
        return sessionRegistry.get(sessionToken);
    }

    private String createSessionKey() {
        return Long.toString(98745232000L + (++counter));
    }


    @Override
    public boolean isUserLoggedIn(String sessionToken) {
        return sessionRegistry.containsKey(sessionToken);
    }

    @Override
    public boolean isUserAdmin(String sessionToken) {
        return sessionRegistry.containsKey(sessionToken) && UserRoleValue.ADMIN_ROLE.equalsIgnoreCase(sessionRegistry.get(sessionToken).getUserRole());
    }

    @Override
    public String createSessionInfoParameterSequence(String sessionToken) {
        if (!isUserLoggedIn(sessionToken))
            return "";

        UserSessionInfo userSessionInfo = sessionRegistry.get(sessionToken);
        return createSessionInfoParameterSequence(userSessionInfo.getStudentID(), userSessionInfo.getUserRole(), userSessionInfo.getSessionToken());
    }

    protected String createSessionInfoParameterSequence(Long studentID, String userRole, String sessionToken) {
        StringBuilder sb = new StringBuilder();
        sb.append("session=").append(sessionToken);
        sb.append("&userrole=").append(userRole);

        if(null!=studentID) {
            sb.append("&studentID=").append(studentID);
        }
        else{
            sb.append("&studentID=").append(0);
        }

        return sb.toString();

    }

    @Override
    public String constructPathWithSessionInfo(String path, String sessionToken) {
        return path + "?" + createSessionInfoParameterSequence(sessionToken);
    }


}
