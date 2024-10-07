package com.lmr.app_utils

import android.app.DatePickerDialog
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import android.provider.Settings
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.lmr.R


import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern


object MyUtils {
    var passwordNotVisible = 0
    var newPasswordNotVisible = 0
    var confirmPasswordNotVisible = 0
    fun isValidEmail(email: String?): Boolean? {
        var isValid = false
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }

    fun checkDates(startDate:String,endDate:String):Boolean{
        val dfDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var isCheck= false
        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))){
                isCheck = true
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return isCheck
    }


    fun showLoader(context: Context) {
        try {
         //   LoaderFragment.showLoader(( context as FragmentActivity).supportFragmentManager)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun hideLoader(context: Context) {
        try {
        //    LoaderFragment.dismissLoader(( context as FragmentActivity).supportFragmentManager)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
     fun getAge(dobString: String?): Int {
        var date: Date? = null
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            date = sdf.parse(dobString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date == null) return 0
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob.time = date
        val year = dob[Calendar.YEAR]
        val month = dob[Calendar.MONTH]
        val day = dob[Calendar.DAY_OF_MONTH]
        dob[year-1, month + 1] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
         if (age==-1){
             age = 0
         }
        return age
    }
    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File? {
        val destinationFilename =
            File(context.filesDir.path + File.separatorChar + queryName(context, uri))
        try {
            context.contentResolver.openInputStream(uri).use { ins ->
                createFileFromStream(
                    ins!!,
                    destinationFilename
                )
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
        return destinationFilename
    }
    fun createFileFromStream(ins: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while (ins.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
    }
    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }
  /*  fun getTimeFormat(data: conversations):String{
        val timeFormat = SimpleDateFormat("hh:mm a")
        return timeFormat.format(Date(data.timestamp.toLong()))
    }*/

    fun getConvertedTimeStamp(timestamp: String):String{
        val timeFormat = SimpleDateFormat("hh:mm a")
        return timeFormat.format(Date(timestamp.toLong()))
    }


    fun getConvertedDateStamp(datestamp: String):String{
        val dateFormat = SimpleDateFormat("MMM dd, yyyy")
        return dateFormat.format(Date(datestamp.toLong()))
    }

/*     fun parseError(activityResult: ActivityResult, binding: ViewBinding) {
        if (activityResult.resultCode == ImagePicker.RESULT_ERROR) {
            snackBarWithRedBackground(binding.root, ImagePicker.getError(activityResult.data))
        } else {
            showSnackBar(binding.root,binding.root.context.getString(R.string.task_cancelled))
        }
    }*/
    fun filePermission(context: Context){
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
               /* val getpermission = Intent()
                getpermission.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                (context as Activity).startActivity(getpermission)*/
                val uri = Uri.parse("package:com.cts.ltypartnerapp")

                context.startActivity(
                    Intent(
                        Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                        uri
                    )
                )
            }
        }
    }

  /*  fun setImageInGlide(context: Context,imagePath:String,imageView:ImageView){
        Glide.with(context)
            .load(imagePath)
            .circleCrop()
            .placeholder(R.drawable.profile3x)
            .into(imageView)
    }*/

    /* fun clearPinViewChild(binding: ViewBinding,s: String) {
        if ( binding is VerifyOtpBinding){
            for (i in 0 until binding.pinEmail.childCount) {
                val child = binding.pinEmail.getChildAt(i) as EditText
                child.setText("")
            }
        } else if (binding is ResetPasswordBinding){
            for (i in 0 until binding.pin.childCount) {
                val child = binding.pin.getChildAt(i) as EditText
                child.setText("")
            }
        } else if (binding is VerifySignupOtpBinding){
            if (s == MyConstant.email){
                for (i in 0 until binding.pinEmail.childCount) {
                    val child = binding.pinEmail.getChildAt(i) as EditText
                    child.setText("")
                }
            }else if (s == MyConstant.phone){
                for (i in 0 until binding.pinPhone.childCount) {
                    val child = binding.pinPhone.getChildAt(i) as EditText
                    child.setText("")
                }
            }
        }
    }*/
    fun setLocale(lang: String?, context: Context) {
        val locale = Locale(lang!!)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }


/*    fun loadFile(context: Context) {
        val intent = Intent(context, FilePickerActivity::class.java)
        intent.putExtra(
            FilePickerActivity.CONFIGS, Configurations.Builder()
            .setCheckPermission(true)
            .setShowImages(false)
            .setShowFiles(true)
            .setShowAudios(false)
            .setShowVideos(false)
            .enableImageCapture(false)
            .enableVideoCapture(false)
            .setSuffixes("pdf", "docx", "xlsx", "doc", "xls", "txt","jpeg","png","jpg")
            .setSingleChoiceMode(true)
            .setSkipZeroSizeFiles(true)
            .build()
        )
        (context as Activity).startActivityForResult(intent, PICK_PDF_CODE)

    }*/

    fun isValidPass(pass: String?): Boolean? {
        var isValid = false
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{4,}$"
        //Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
        val expression2 =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[d$@!%*?&#.])[A-Za-z\\d$@$!%*?&#._;<>(){}-~`?/:+=]{8,}"
        val pattern = Pattern.compile(expression2)
        val matcher = pattern.matcher(pass)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }
    fun isValidMobile(phone: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", phone)) {
            phone.length > 10 && phone.length <= 11
        } else false
    }


    fun getActivityResult( data: Intent?): File {
        var url:String?=null
        val uri = data?.data
        val filePath = uri?.path!!
        val picturePath: List<String> = filePath.split(":")
        val first = picturePath[0]
        val second = picturePath[1]
        if (first == "/document/raw" || first == "/document/primary") {
            url = "/storage/emulated/0/$second"
        }
        return File(url!!)
    }
  /*  fun jsonConvertToList(response: JsonObject):ArrayList<Profile> {
        Log.d("response proifle", ""+response)
        val jsonObject = JSONObject(response.toString())
        val jsonObjectData = jsonObject.optJSONObject("data")
        val profiles: JSONObject = jsonObjectData!!.getJSONObject("profiles")
        val x: Iterator<*> = profiles.keys()
        val jsonArray = JSONArray()
        val nameList = ArrayList<String>()
        val profilesList = ArrayList<Profile>()

        while (x.hasNext()) {
            val key = x.next() as String
            nameList.add(key)
            jsonArray.put(profiles[key])
        }
        for (i in 0 until jsonArray.length()) {
            val book = jsonArray.optJSONObject(i)
            val profile = Profile(nameList.get(i),book.optString("agentReserve"),book.optString("companyReserve"),book.optString("agentCommission"))
            profilesList.add(profile)
        }
        return profilesList
    }

    fun jsonConvertUsers(response: JSONObject?):ArrayList<SelectedUser> {
        Log.d("response proifle", ""+response)
        val x: Iterator<*> = response!!.keys()
        val jsonArray = JSONArray()
        val nameList = ArrayList<String>()
        val profilesList = ArrayList<SelectedUser>()

        while (x.hasNext()) {
            val key = x.next() as String
            nameList.add(key)
            jsonArray.put(response[key])
        }
        for (i in 0 until jsonArray.length()) {
            val book = jsonArray.opt(i).toString()
            val profile = SelectedUser(book,nameList.get(i))
            profilesList.add(profile)
        }
        return profilesList
    }*/


     fun parseDateToddMMyyyy(date: String?): Date? {
        val inputPattern = "yyyy-MM-dd HH:mm"
       // val outputPattern = "MMMM dd, yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
      //  val outputFormat = SimpleDateFormat(outputPattern)
        var newDate: Date? = null
        var str: String? = null
        try {
            newDate = inputFormat.parse(date)
          //  str = outputFormat.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return newDate
    }

     fun parseTimeToddMMyyyy(time: String?): String? {
        val inputPattern = "HH:mm"
        val outputPattern = "h:mmaa"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var newDate: Date? = null
        var str: String? = null
        try {
            newDate = inputFormat.parse(time)
            str = outputFormat.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

   /*  fun datePickerDialog(calendar: Calendar, binding: ViewBinding):String  {
         var caldate = ""
         if (binding is ActivitySignupBinding){
             calendar.add(Calendar.YEAR, -16)
             val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                 calendar.set(Calendar.YEAR, year)
                 calendar.set(Calendar.MONTH, month)
                 calendar.set(Calendar.DAY_OF_MONTH, day)
                 val showFormat = "dd.MM.yyyy"
                 val showDateFormat = SimpleDateFormat(showFormat, Locale.getDefault())
                 binding.dob.text = showDateFormat.format(calendar.time)
                 caldate = binding.dob.text.toString()
             }
             val datePickerDialog = DatePickerDialog(
                 binding.root.context, date,
                 calendar[Calendar.YEAR],
                 calendar[Calendar.MONTH],
                 calendar[Calendar.DAY_OF_MONTH])
             datePickerDialog.show()
             datePickerDialog.datePicker.maxDate = calendar.timeInMillis
         }
         else if (binding is ActivityAddNewAgentBinding){
             calendar.add(Calendar.YEAR, -16)
             val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                 calendar.set(Calendar.YEAR, year)
                 calendar.set(Calendar.MONTH, month)
                 calendar.set(Calendar.DAY_OF_MONTH, day)
                 val showFormat = "dd.MM.yyyy"
                 val showDateFormat = SimpleDateFormat(showFormat, Locale.getDefault())
                 binding.dob.text = showDateFormat.format(calendar.time)
                 caldate = binding.dob.text.toString()
             }
             val datePickerDialog = DatePickerDialog(
                 binding.root.context, date,
                 calendar[Calendar.YEAR],
                 calendar[Calendar.MONTH],
                 calendar[Calendar.DAY_OF_MONTH])
             datePickerDialog.show()
             datePickerDialog.datePicker.maxDate = calendar.timeInMillis
         }
         else if (binding is ActivityAgentEditAndUpdateBinding){
             calendar.add(Calendar.YEAR, -16)
             val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                 calendar.set(Calendar.YEAR, year)
                 calendar.set(Calendar.MONTH, month)
                 calendar.set(Calendar.DAY_OF_MONTH, day)
                 val showFormat = "dd.MM.yyyy"
                 val showDateFormat = SimpleDateFormat(showFormat, Locale.getDefault())
                 binding.dob.text = showDateFormat.format(calendar.time)
                 caldate = binding.dob.text.toString()
             }
             val datePickerDialog = DatePickerDialog(
                 binding.root.context, date,
                 calendar[Calendar.YEAR],
                 calendar[Calendar.MONTH],
                 calendar[Calendar.DAY_OF_MONTH])
             datePickerDialog.show()
             datePickerDialog.datePicker.maxDate = calendar.timeInMillis
         }
         else if ( binding is ActivityAddCustomersBinding){
             calendar.add(Calendar.YEAR, -16)
             val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                 calendar.set(Calendar.YEAR, year)
                 calendar.set(Calendar.MONTH, month)
                 calendar.set(Calendar.DAY_OF_MONTH, day)
                 val showFormat = "dd.MM.yyyy"
                 val showDateFormat = SimpleDateFormat(showFormat, Locale.getDefault())
                 binding.dob.text = showDateFormat.format(calendar.time)
                 caldate = binding.dob.text.toString()
             }
             val datePickerDialog = DatePickerDialog(
                 binding.root.context, date,
                 calendar[Calendar.YEAR],
                 calendar[Calendar.MONTH],
                 calendar[Calendar.DAY_OF_MONTH])
             datePickerDialog.show()
             datePickerDialog.datePicker.maxDate = calendar.timeInMillis
         } else if (binding is ActivityEditCustomerBinding){
             calendar.add(Calendar.YEAR, -16)
             val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                 calendar.set(Calendar.YEAR, year)
                 calendar.set(Calendar.MONTH, month)
                 calendar.set(Calendar.DAY_OF_MONTH, day)
                 val showFormat = "dd.MM.yyyy"
                 val showDateFormat = SimpleDateFormat(showFormat, Locale.getDefault())
                 binding.dob.text = showDateFormat.format(calendar.time)
                 caldate = binding.dob.text.toString()
             }
             val datePickerDialog = DatePickerDialog(
                 binding.root.context, date,
                 calendar[Calendar.YEAR],
                 calendar[Calendar.MONTH],
                 calendar[Calendar.DAY_OF_MONTH])
             datePickerDialog.show()
             datePickerDialog.datePicker.maxDate = calendar.timeInMillis
         }
         return caldate
    }
     fun passwordShowHide(binding: ViewBinding, s: String){
         if (binding is ActivitySignupBinding){
             if (s==newPassword){
                 if (passwordNotVisible == 0) {
                     binding.editPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivUserPassword.setImageResource(R.drawable.password_show_new)
                     passwordNotVisible = 1
                 } else {
                     binding.editPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivUserPassword.setImageResource(R.drawable.password_hidden)
                     passwordNotVisible = 0
                 }
             }else{
                 if (newPasswordNotVisible == 0) {
                     binding.editConfrimPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivUserConfrimPassword.setImageResource(R.drawable.password_show_new)
                     newPasswordNotVisible = 1
                 } else {
                     binding.editConfrimPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivUserConfrimPassword.setImageResource(R.drawable.password_hidden)
                     newPasswordNotVisible = 0
                 }
             }
         }
         if (binding is FragmentProfileBinding){
             if (s== oldPassword){
                 if (passwordNotVisible == 0) {
                     binding.editPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivUserPassword.setImageResource(R.drawable.password_show_new)
                     passwordNotVisible = 1
                 } else {
                     binding.editPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivUserPassword.setImageResource(R.drawable.password_hidden)
                     passwordNotVisible = 0
                 }
             }else if(s== newPassword){
                 if (newPasswordNotVisible == 0) {
                     binding.editNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivUserNewConfrimPassword.setImageResource(R.drawable.password_show_new)
                     newPasswordNotVisible = 1
                 } else {
                     binding.editNewPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivUserNewConfrimPassword.setImageResource(R.drawable.password_hidden)
                     newPasswordNotVisible = 0
                 }
             }
             else{
                 if (confirmPasswordNotVisible == 0) {
                     binding.editConfrimPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivUserConfrimPassword.setImageResource(R.drawable.password_show_new)
                     confirmPasswordNotVisible = 1
                 } else {
                     binding.editConfrimPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivUserConfrimPassword.setImageResource(R.drawable.password_hidden)
                     confirmPasswordNotVisible = 0
                 }
             }
         }
         if (binding is ResetPasswordBinding){
             if (s==newPassword){
                 if (passwordNotVisible == 0) {
                     binding.newPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivNewPassword.setImageResource(R.drawable.password_show_new)
                     passwordNotVisible = 1
                 } else {
                     binding.newPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivNewPassword.setImageResource(R.drawable.password_hidden)
                     passwordNotVisible = 0
                 }
             }else{
                 if (newPasswordNotVisible == 0) {
                     binding.confirmPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                     binding.ivConfrimPassword.setImageResource(R.drawable.password_show_new)
                     newPasswordNotVisible = 1
                 } else {
                     binding.confirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                     binding.ivConfrimPassword.setImageResource(R.drawable.password_hidden)
                     newPasswordNotVisible = 0
                 }
             }
         }
         if (binding is ActivityLoginBinding){
             if (passwordNotVisible == 0) {
                 binding.password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                 binding.hideShow.setImageResource(R.drawable.password_show_new)
                 passwordNotVisible = 1
             } else {
                 binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                 binding.hideShow.setImageResource(R.drawable.password_hidden)
                 passwordNotVisible = 0
             }

         }
    }
    fun timeOutOtp(context: Context, otpTime: String, binding: ViewBinding, emailId: String?, phone: String?){
        if (binding is VerifyOtpBinding){
            binding.codeEmail.text = "${context.getString(R.string.an_6_digit_code_has_been_sent_to)}${" "}${"Mobile"}"
            val timer = object : CountDownTimer(otpTime.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeInStr = (millisUntilFinished / 1000).toString()
                    binding.viewTime.text = "${context.getString(R.string.resend_otp_in)}${" "}$timeInStr${" "}${context.getString(R.string.seconds)}"
                }
                override fun onFinish() {
                    binding.viewTime.visibility= View.GONE
                    binding.resendOtp.visibility=View.VISIBLE
                }
            }.start()
        }
        else if (binding is ResetPasswordBinding){
            binding.codeMessage.text = "${context.getString(R.string.an_6_digit_code_has_been_sent_to)}${" "}${emailId}"
            val timer = object : CountDownTimer(otpTime.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeInStr = (millisUntilFinished / 1000).toString()
                    binding.viewTime.text = "${context.getString(R.string.resend_otp_in)}${" "}$timeInStr${" "}${context.getString(R.string.seconds)}"
                }
                override fun onFinish() {
                    binding.viewTime.visibility= View.GONE
                    binding.resendOtp.visibility=View.VISIBLE
                }
            }.start()
        }
        else if (binding is VerifyPhoneNumberBinding){
            binding.codeMessage.text = "${context.getString(R.string.an_6_digit_code_has_been_sent_to)}${" "}${emailId}"
            val timer = object : CountDownTimer(otpTime.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeInStr = (millisUntilFinished / 1000).toString()
                    binding.viewTime.text = "${context.getString(R.string.resend_otp_in)}${" "}$timeInStr${" "}${context.getString(R.string.seconds)}"
                }
                override fun onFinish() {
                    binding.viewTime.visibility= View.GONE
                    binding.resendOtp.visibility=View.VISIBLE
                }
            }.start()
        }
        else if (binding is VerifySignupOtpBinding){
            binding.codePhone.text = "${context.getString(R.string.an_6_digit_code_has_been_sent_to)}${" "}$phone"
            binding.codeEmail.text = "${context.getString(R.string.an_6_digit_code_has_been_sent_to)}${" "}${emailId}"
            val timer = object : CountDownTimer(otpTime.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeInStr = (millisUntilFinished / 1000).toString()
                    binding.viewTime.text = "${context.getString(R.string.resend_otp_in)}${" "}$timeInStr${" "}${context.getString(R.string.seconds)}"
                }
                override fun onFinish() {
                    binding.viewTime.visibility= View.GONE
                    binding.resendOtp.visibility=View.VISIBLE
                }
            }.start()
            val timer_phone = object : CountDownTimer(otpTime.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeInStr = (millisUntilFinished / 1000).toString()
                    binding.viewTimePhone.text = "${context.getString(R.string.resend_otp_in)}${" "}$timeInStr${" "}${context.getString(R.string.seconds)}"
                }
                override fun onFinish() {
                    binding.viewTimePhone.visibility= View.GONE
                    binding.resendOtpPhone.visibility=View.VISIBLE
                }
            }.start()
        }
    }
*/
    fun changeDateFormat(dob: String): String {
        val formatter: DateFormat = SimpleDateFormat("dd.MM.yyyy")
        val date = formatter.parse(dob) as Date
        val newFormat = SimpleDateFormat("yyyy-MM-dd")
        return newFormat.format(date)
    }
    fun getConvertedDateFormat(dob: String):String{
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = formatter.parse(dob)
        val newFormat = SimpleDateFormat("dd.MM.yyyy")
        return newFormat.format(date)
    }

     fun downloadFile(myHTTPUrl: String, context: Context) {
        try {
            val request = DownloadManager.Request(Uri.parse(myHTTPUrl))
            request.setTitle("File download")
            request.setDescription("File is being downloaded...")
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val nameOfFile: String = URLUtil.guessFileName(myHTTPUrl, null, MimeTypeMap.getFileExtensionFromUrl(myHTTPUrl))
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameOfFile)
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            manager!!.enqueue(request)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

   /* fun drawerHeaderTitle(binding: ActivityMainBinding){
        when (MyConstant.HeaderTitle) {
            MyConstant.dashboard -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.home)
                MyConstant.HeaderTitle  = ""

            }
            MyConstant.category -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.selected_categories)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.agent -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.agent)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.customers -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.customers)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.manageProposal -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.manage_proposal)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.financialStatement -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.financial_statement)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.letterManagements -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.letter_managements)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.leads -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.leads)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.submissions -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.submissions)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.companyInfo -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.company_info)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.rulesAccess -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.rules_access)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.searchCover -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.search_cover)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.message -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.message)
                 MyConstant.HeaderTitle  = ""
            }
            MyConstant.agenda -> {
                binding.contentView.customHeader.tvHeading.text= binding.root.context.getString(R.string.agenda)
                 MyConstant.HeaderTitle  = ""
            }
        }
    }
*/
    fun getSpannableString(s:String?):SpannableString{
        var currentDate = s
        val now = Date()
        currentDate = SimpleDateFormat("EEEE, MMMM dd, yyyy").format(now)
        val mSpannableString = SpannableString(currentDate)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
      return  mSpannableString
    }
    fun isValidURL(url: String?): Boolean {
        return Patterns.WEB_URL.matcher(url!!).matches()
    }

    fun getCurrentDateWithYYMMDDFormat():String{
        var newDate = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        newDate = sdf.format(Date())
        return newDate
    }

    fun getChangeDateFormat(dateFilter: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy")
        val date = inputFormat.parse(dateFilter)
        return outputFormat.format(date)
    }


    fun getSortedStringColor(colorCode: String?): String {
        val withoutFirstCharacter = colorCode?.substring(1)
        val withoutLastCharacter  = withoutFirstCharacter?.substring(0, withoutFirstCharacter.length - 1)
        val list: List<String> = listOf(*withoutLastCharacter?.split(",")!!.toTypedArray())
        val r = list[0]
        val g = list[1]
        val b = list[2]
        return String.format("#%02x%02x%02x", Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b))
    }
    fun showProgressDialog(context: Context): ProgressDialog {
        val pDialog = ProgressDialog(context)
        pDialog.setMessage(context.getString(R.string.please_wait))
        pDialog.setCancelable(false)
        pDialog.isIndeterminate = true
        pDialog.show()
        return pDialog
    }




    fun hideProgressDialog(pDialog: ProgressDialog) {
        if (pDialog.isShowing) pDialog.dismiss()
    }
  /*  fun errorBody(error: String?, context: Context): String {
        try {
            val errorBody = JSONObject(error!!)
            val message =  errorBody.getJSONObject("error")
            if (message.optString("errorCode")=="ER-TOKEN-4000"){
                tokenExpire(context)
            }
            return message.optString("serverErrorMessage")
        }catch (e:Exception){
            e.printStackTrace()
        }
       return ""
    }
*/
/*    private fun tokenExpire(context: Context) {
         AppPref.getInstance(context).setToken("")
        context.startActivity(Intent(context,LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(MyConstant.tokenExpire,true)
        })
    }*/



    fun removeCommaFromString(any:Any):String{
        var s: String = any.toString()
        s = s.substring(1, s.length - 1)
        return s
    }

    fun getDatePickerDialog(context: Context, myCalendar: Calendar, date: DatePickerDialog.OnDateSetListener){
        val datePickerDialog = DatePickerDialog(
            context, date,
            myCalendar[Calendar.YEAR],
            myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH])
        datePickerDialog.show()
        datePickerDialog.datePicker.maxDate = myCalendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeFromDate(checkIn:String):String{
        try {
            val dateTime = LocalDateTime.parse(checkIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val newString = DateTimeFormatter.ofPattern("H:mm a").format(dateTime)
            return newString
        }catch (e:Exception){
            e.printStackTrace()
        }
       return "NA"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getConvertedDate(createdAt:String): String {
        try {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val newDate = (LocalDate.parse(createdAt, formatter).format(formatter2))
            return newDate
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "NA"
    }
    fun currentMonth():String{
        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        var currentMonth = cal.get(Calendar.MONTH)
           currentMonth += 1
        if (currentMonth<10)
            return "0$currentMonth"
        return currentMonth.toString()
    }
    fun currentYear():String{
        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        val currentYear  = cal.get(Calendar.YEAR)
        if (currentYear<10)
            return "0$currentYear"
        return currentYear.toString()
    }

    fun getDayFromDate(month:String):String{
        val dateParts: List<String> = month.split("-")
        val year = dateParts[0]
        val month = dateParts[1]
        val day = dateParts[2]
        return  day
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStrToDate(date : String) : LocalDate{
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val date: LocalDate = LocalDate.parse(date, formatter)
        return date
    }

    fun getMothFromDate(month:String):String{
        val dateParts: List<String> = month.split("-")
        val year = dateParts[0]
        val month = dateParts[1]
        val day = dateParts[2]
        return  month
    }

    fun getYearFromDate(year:String):String{
        val dateParts: List<String> = year.split("-")
        val year = dateParts[0]
        val month = dateParts[1]
        val day = dateParts[2]
        return  year
    }

   /* fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(true)
            .apply(RequestOptions.circleCropTransform())
            .thumbnail(0.5f)
            .listener(object :RequestListener<Drawable?>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }
            })
            .into(view)
    }
*/
    fun convertDateFormat(dateInput : String) : String{
      //  yyyy-MM-dd'T'HH:mm:ss'Z'
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
       // val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
      //  val inputDateStr = "2013-06-24"
        val date: Date = inputFormat.parse(dateInput)
        val outputDateStr: String = outputFormat.format(date)
        return outputDateStr
    }
    fun errorBody(error: String?, context: Context): String {
        try {
            val errorBody = JSONObject(error!!)
            val message =  errorBody.getJSONObject("error")
            if (message.optString("errorCode")=="ER-TOKEN-4000"){
            //    tokenExpire(context)
            }
            return message.optString("serverErrorMessage")
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

}