package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

 class PostBasicDetailEvent(){

     @SerializedName("eventBasicDetailsID")
     var eventBasicDetailsID: Int = 0

     @SerializedName("eventID")
     var eventID: Int = 0

     @SerializedName("eventName")
     var eventName: String = ""

     @SerializedName("eventTypeID")
     var eventTypeID: Int = 0

     @SerializedName("eventCategoryID")
     var eventCategoryID: Int = 0

     @SerializedName("numberOfPeople")
     var numberOfPeople: Int = 0

     @SerializedName("maximumCapacity")
     var maximumCapacity: Int = 0

     @SerializedName("publishingMethod")
     var publishingMethod: Int = 0

     @SerializedName("ticketType")
     var ticketType: Int = 0

     @SerializedName("ageGroupID")
     var ageGroupID: Int = 0

}
