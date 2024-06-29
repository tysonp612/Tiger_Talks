package com.group2.Tiger_Talks.backend.model;

public class UserProfileFriendshipDTO {

    private String email;
    private String userName;
    private String profilePictureUrl;

    public UserProfileFriendshipDTO(UserProfile userProfile) {
        this.email = userProfile.getEmail();
        this.userName = userProfile.getUserName();
        this.profilePictureUrl = userProfile.getProfilePictureUrl();
    }

    public UserProfileFriendshipDTO() {
    }

    public String getEmail() {
        return email;
    }


    public String getUserName() {
        return userName;
    }


    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}