package org.example;

import de.htwberlin.kba.game_management.export.Game;
import de.htwberlin.kba.game_management.export.Request;
import de.htwberlin.kba.game_management.export.RequestService;
import de.htwberlin.kba.user_management.export.User;
import de.htwberlin.kba.user_management.export.UserService;
import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;
    private final UserService userService;


    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @PostMapping(value = "/create/{reqName}/{recName}")
    public ResponseEntity<Void> createRequest(@PathVariable("reqName") String reqName, @PathVariable("recName") String recName) throws URISyntaxException, URISyntaxException {
        User requester = userService.getUserByUserName(reqName);
        User receiver = userService.getUserByUserName(recName);

        Request request = requestService.createRequest(requester, receiver);
        URI uri = new URI("/game/" + request.getRequestId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "changeStatus/{requestId}/{accept}")
    public ResponseEntity<?> changeStatus(@PathVariable("requestId") String requestId, @PathVariable("accept") String accept) {
        Boolean accept_bol = Boolean.FALSE;
        if (accept.equals("1")) {
            accept_bol = Boolean.TRUE;
        }

        Request request = requestService.getRequestById(Long.valueOf(requestId));

        requestService.changeStatus(accept_bol, request);
        return request != null? ResponseEntity.ok(request) : ResponseEntity.notFound().build();
    }

    @GetMapping("/pendingRequests/{userName}")
    public List<Request> getPendingRequestsForCurrentUser(@PathVariable("userName") String userName){
        User user = userService.getUserByUserName(userName);
        return requestService.getPendingRequestsForCurrentUser(user);
    }

    @GetMapping(value = "/all")
    public List<Request> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return requests;
    }

}
