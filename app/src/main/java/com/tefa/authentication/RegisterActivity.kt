package com.tefa.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tefa.authentication.ui.theme.AuthenticationTheme

class RegisterActivity : AppComponentActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.fullNameEt.onFocusChangeListener = this
        mBinding.emailEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.cPasswordEt.onFocusChangeListener = this

    }

    private fun validateFullName(): Boolean{
        val errorMessage: String?
        val value: String = mBinding.fullNameEt.text.toString()
        if(value.isEmpty())
        {
            errorMessage = "Full name is required"
        }

        if(errorMessage != null){
            mBinding.fullNameTil.apply{
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateEmail(){
        var errorMessage: String? = null
        val value = mBinding.emailEt.text.toString()
        if(value.isEmpty())
        {
            errorMessage = "Email is required"
        }
        else if(!Patterns.Email_ADDRESS.matcher(value).matches()){
            errorMessage = "Email address is invalid"
        }

        if(errorMessage != null){
            mBinding.emailTil.apply{
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword(): Boolean{
        var errorMassage: String? = null
        val value = mBinding.passwordEt.text.toString()
        if(value.isEmpty())
        {
            errorMassage = "Password is required"
        }
        else if(value.length < 6){
            errorMassage = "Password must be 6 characters long"
        }

        if(errorMessage != null){
            mBinding.passwordTil.apply{
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMassage == null
    }

    private fun validateConfirmPassword(): Boolean{
        var errorMassage: String? = null
        val value = mBinding.passwordEt.text.toString()
        val confirmPassword = mBinding.cPasswordEt.text.toString()
        if(value.isEmpty())
        {
            errorMassage = "Confirm Password is required"
        }
        else if(value.length < 6){
            errorMassage = "Confirm Password must be 6 characters long"
        }
        if(errorMessage != null){
            mBinding.cPasswordTil.apply{
                isErrorEnabled = true
                errorMassage = errorMessage
            }
        }
        return errorMassage == null

        private fun validatePasswordAndConfirmPassword(){
            var error: String = null
            val password = mBinding.passwordEt.text.toString()
            val confirmPassword = mBinding.passwordEt.text.toString()
            if(password != confirmPassword){
                error = "Confirm password doesn't match with password"
            }

            return error == null
        }

        override fun OnClick(view: View?) {

        }

        override fun onFocusChage(view: View?, hasFocus: Boolean) {
            if(view != null){
                when(view.id){
                    R.id.fullNameEt -> {
                        if(hasFocus){
                            if(mBinding.fullNameTil.isErrorEnabled){
                                mBinding.fullNameTil.isErrorEnabled = false
                            }
                        }else{
                            validateFullName()
                        }
                    }
                    R.id.emailEt -> {
                        if(hasFocus){
                            if(mBinding.emailTil.isErrorEnabled){
                                mBinding.emailTil.isErrorEnabled = false
                            }
                        }else{
                            if(validateEmail()){
                                // do validation for its uniqueness

                            }
                        }
                    }
                    R.id.passwordEt -> {
                        if(hasFocus){
                            if(mBinding.passwordTil.isErrorEnabled){
                                mBinding.passwordTil.isErrorEnabled = false
                            }
                        }else{
                            if(validatePassword() && mBinding.cPasswordEt.text!!.isNotEmpty() && validateConfirmatPassword() && validatePasswordAndConfirmPassword()){
                                if(mBinding.cPasswordTil.isErrorEnabled){
                                    mBinding.cPasswordTil.isErrorEnabled = false
                                }
                                mBinding.cPasswordTil.apply{
                                    setStartIconDrawable(R.drawable.check_circle_24)
                                    startIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }
                    R.id.cPasswordEt -> {
                        if(hasFocus){
                            if(mBinding.cPasswordTil.isErrorEnabled){
                                mBinding.cPasswordTil.isErrorEnabled = false
                            }
                        }else{
                            if(validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()){
                                if(mBinding.PasswordTil.isErrorEnabled){
                                    mBinding.PasswordTil.isErrorEnabled = false
                                }
                                mBinding.cPasswordTil.apply{
                                    setStartIconDrawable(R.drawable.check_circle_24)
                                    startIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }
                }

            }
        }

        override fun onKey(view: View?, event: Int, keyEvent: keyEvent?): Boolean {
            return false
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthenticationTheme {
        Greeting("Android")
    }
}