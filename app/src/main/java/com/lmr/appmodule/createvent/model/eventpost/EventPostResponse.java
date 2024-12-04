
package com.lmr.appmodule.createvent.model.eventpost;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EventPostResponse {

    @SerializedName("data")
    private Data mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("success")
    private Boolean mSuccess;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
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

    public class Data {

        @SerializedName("endTime")
        private Object mEndTime;
        @SerializedName("eventEndDate")
        private Object mEventEndDate;
        @SerializedName("eventID")
        private Long mEventID;
        @SerializedName("eventName")
        private Object mEventName;
        @SerializedName("eventStartDate")
        private Object mEventStartDate;
        @SerializedName("lstEventImages")
        private List<Object> mLstEventImages;
        @SerializedName("startTime")
        private Object mStartTime;

        public Object getEndTime() {
            return mEndTime;
        }

        public void setEndTime(Object endTime) {
            mEndTime = endTime;
        }

        public Object getEventEndDate() {
            return mEventEndDate;
        }

        public void setEventEndDate(Object eventEndDate) {
            mEventEndDate = eventEndDate;
        }

        public Long getEventID() {
            return mEventID;
        }

        public void setEventID(Long eventID) {
            mEventID = eventID;
        }

        public Object getEventName() {
            return mEventName;
        }

        public void setEventName(Object eventName) {
            mEventName = eventName;
        }

        public Object getEventStartDate() {
            return mEventStartDate;
        }

        public void setEventStartDate(Object eventStartDate) {
            mEventStartDate = eventStartDate;
        }

        public List<Object> getLstEventImages() {
            return mLstEventImages;
        }

        public void setLstEventImages(List<Object> lstEventImages) {
            mLstEventImages = lstEventImages;
        }

        public Object getStartTime() {
            return mStartTime;
        }

        public void setStartTime(Object startTime) {
            mStartTime = startTime;
        }

    }


}
