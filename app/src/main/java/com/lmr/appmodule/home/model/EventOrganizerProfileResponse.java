
package com.lmr.appmodule.home.model;

import com.google.gson.annotations.SerializedName;


public class EventOrganizerProfileResponse {

    @SerializedName("data")
    private EventOrganizerProfileData mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Boolean mSuccess;

    public EventOrganizerProfileData getData() {
        return mData;
    }

    public void setData(EventOrganizerProfileData data) {
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

    public class EventOrganizerProfileData {

        @SerializedName("aboutOrganizer")
        private String mAboutOrganizer;
        @SerializedName("eventOrganizerID")
        private Long mEventOrganizerID;
        @SerializedName("eventOrganizerName")
        private String mEventOrganizerName;
        @SerializedName("organizerAddress")
        private String mOrganizerAddress;

        public String getAboutOrganizer() {
            return mAboutOrganizer;
        }

        public void setAboutOrganizer(String aboutOrganizer) {
            mAboutOrganizer = aboutOrganizer;
        }

        public Long getEventOrganizerID() {
            return mEventOrganizerID;
        }

        public void setEventOrganizerID(Long eventOrganizerID) {
            mEventOrganizerID = eventOrganizerID;
        }

        public String getEventOrganizerName() {
            return mEventOrganizerName;
        }

        public void setEventOrganizerName(String eventOrganizerName) {
            mEventOrganizerName = eventOrganizerName;
        }

        public String getOrganizerAddress() {
            return mOrganizerAddress;
        }

        public void setOrganizerAddress(String organizerAddress) {
            mOrganizerAddress = organizerAddress;
        }

    }


}
