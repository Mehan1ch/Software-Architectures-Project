package hu.bme.aut.android.writer_reader_client.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.feature.register.RegisterViewIntent
import hu.bme.aut.android.writer_reader_client.ui.common.NormalTextField
import hu.bme.aut.android.writer_reader_client.ui.common.PasswordTextField
import hu.bme.aut.android.writer_reader_client.ui.common.WorkCard


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
    onSuccessfulLogin: () -> Unit = {},
    onRegisterButtonClicked: () -> Unit = {}
){

   // val state by viewModel.state.collectAsStateWithLifecycle()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val hostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginUiEvent.LoginSuccessful -> {
                    onSuccessfulLogin()
                }

                is LoginUiEvent.LoginFailed -> {
                    hostState.showSnackbar(
                        message = event.error,
                        withDismissAction = true,
                        actionLabel = "OK"
                        //modifier = Modifier.align(Alignment.BottomCenter)
                        //snackbarData.dismissActionContentColor
                        //snackbarData.actionColor
                        //snackbarData.contentColor

                    )

                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center

    ){
        if(state.isError){
            Snackbar(
                modifier.align(Alignment.Center),
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                action = {
                    Button(onClick = {viewModel.onIntent(LoginViewIntent.ErrorOkButtonClicked) })
                    {
                        Text("OK")
                    }
                }
            ) {
                Text(text = state.throwable?.message ?: "An unknown error occurred")
            }
        }
        else{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(26.dp))

                NormalTextField(
                    value = state.email,
                    label = stringResource(id = R.string.textfield_label_email),
                    onValueChange = { newValue ->
                        viewModel.onIntent(LoginViewIntent.EmailChanged(newValue))
                    },
                    isError = state.isUsernameError,

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {},
                    onDone = { }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordTextField(
                    value = state.password,
                    label = stringResource(id = R.string.textfield_label_password),
                    onValueChange = { newValue ->
                        viewModel.onIntent(LoginViewIntent.PasswordChanged(newValue))
                    },
                    isError = state.isPasswordError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Key,
                            contentDescription = null
                        )
                    },
                    isVisible = state.isPasswordVisible,
                    onVisibilityChanged = {
                        viewModel.onIntent(LoginViewIntent.TogglePasswordVisibility)
                    },
                    onDone = { }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onIntent(LoginViewIntent.LoginButtonClicked(context = context, onSuccessfulLogin = onSuccessfulLogin)
                    )},
                    modifier = Modifier.width(TextFieldDefaults.MinWidth)
                ) {
                    Text(text = stringResource(id = R.string.button_label_login))
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = onRegisterButtonClicked
                ) {
                    Text(text = stringResource(id = R.string.button_label_register))
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}