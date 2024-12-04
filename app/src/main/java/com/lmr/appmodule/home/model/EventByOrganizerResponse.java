
package com.lmr.appmodule.home.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class EventByOrganizerResponse {

    @SerializedName("data")
    private List<EventByOrganizer> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Boolean mSuccess;

    public List<EventByOrganizer> getData() {
        return mData;
    }

    public void setData(List<EventByOrganizer> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

    public class EventByOrganizer {

        @SerializedName("endTime")
        private String mEndTime;
        @SerializedName("eventID")
        private Long mEventID;
        @SerializedName("eventName")
        private String mEventName;
        @SerializedName("eventStartDate")
        private String mEventStartDate;
        @SerializedName("isLiked")
        private Boolean mIsLiked;
        @SerializedName("location")
        private String mLocation;
        @SerializedName("numberOfPeople")
        private Long mNumberOfPeople;
        @SerializedName("profileImage")
        private String mProfileImage;
        @SerializedName("startTime")
        private String mStartTime;

        public String getEndTime() {
            return mEndTime;
        }

        public void setEndTime(String endTime) {
            mEndTime = endTime;
        }

        public Long getEventID() {
            return mEventID;
        }

        public void setEventID(Long eventID) {
            mEventID = eventID;
        }

        public String getEventName() {
            return mEventName;
        }

        public void setEventName(String eventName) {
            mEventName = eventName;
        }

        public String getEventStartDate() {
            return mEventStartDate;
        }

        public void setEventStartDate(String eventStartDate) {
            mEventStartDate = eventStartDate;
        }

        public Boolean getIsLiked() {
            return mIsLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            mIsLiked = isLiked;
        }

        public String getLocation() {
            return mLocation;
        }

        public void setLocation(String location) {
            mLocation = location;
        }

        public Long getNumberOfPeople() {
            return mNumberOfPeople;
        }

        public void setNumberOfPeople(Long numberOfPeople) {
            mNumberOfPeople = numberOfPeople;
        }

        public String getProfileImage() {
            return mProfileImage;
        }

        public void setProfileImage(String profileImage) {
            mProfileImage = profileImage;
        }

        public String getStartTime() {
            return mStartTime;
        }

        public void setStartTime(String startTime) {
            mStartTime = startTime;
        }

    }

}


