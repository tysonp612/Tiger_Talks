package com.group2.Tiger_Talks.backend.controller;

import com.group2.Tiger_Talks.backend.model.Socials.Friendship;
import com.group2.Tiger_Talks.backend.service.Socials.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestParam String senderEmail, @RequestParam String receiverEmail) {
        try {
            friendService.sendFriendRequest(senderEmail, receiverEmail);
            return ResponseEntity.ok("Friend request sent.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptFriend(@RequestParam Integer friendshipRequestId) {
        try {
            friendService.acceptFriendRequest(friendshipRequestId);
            return ResponseEntity.ok("Friend request accepted.");
        } catch (IllegalStateException err) {
            return ResponseEntity.status(404).body(err);
        }
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectFriend(@RequestParam Integer friendshipRequestId) {
        return friendService.rejectFriendRequest(friendshipRequestId)
                .map(err -> ResponseEntity.status(404).body("Friend request rejected."))
                .orElseGet(() -> ResponseEntity.ok("Removed friend"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFriends(@RequestParam String email) {
        List<Friendship> friends = friendService.getAllFriends(email);
        return ResponseEntity.ok(friends);
    }
}