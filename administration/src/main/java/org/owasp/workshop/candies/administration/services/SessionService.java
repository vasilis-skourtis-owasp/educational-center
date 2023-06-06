package org.owasp.workshop.candies.administration.services;

import org.owasp.workshop.candies.administration.dtos.StudentUser;
import org.owasp.workshop.candies.administration.dtos.UserSessionInfo;
import org.owasp.workshop.candies.administration.model.User;

public interface SessionService {

    UserSessionInfo createSessionForUser(User user);
    UserSessionInfo getUserSessionInfo(String sessionToken);
    boolean isUserLoggedIn(String sessionToken);

    boolean isUserAdmin(String sessionToken);

    String createSessionInfoParameterSequence(String sessionToken);

    String constructPathWithSessionInfo(String path, String sessionToken);
}
