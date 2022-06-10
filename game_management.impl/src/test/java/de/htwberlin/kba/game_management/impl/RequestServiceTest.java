package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.game_management.export.Status;
import de.htwberlin.kba.user_management.export.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

public class RequestServiceTest {

    @Spy
    @InjectMocks
    RequestServiceImpl service;

    @Mock
    RequestDaoImpl requestDao;

    // changeStatus
    // createRequest
    // getPendingRequestsForCurrentUser

    // optional, da in den jeweiligen Methoden des RequestServiceImpl lediglich der Datenbankaufruf passiert

}
