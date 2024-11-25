package hu.bme.aut.android.writer_reader_client.feature.register
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import hu.bme.aut.android.writer_reader_client.ui.common.NormalTextField
import hu.bme.aut.android.writer_reader_client.ui.common.PasswordTextField

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onSuccessfulRegister: () -> Unit = {},
    viewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory),
) {

   // val state by viewModel.state.collectAsStateWithLifecycle()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val hostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                is RegisterUiEvent.RegisterSuccessful -> {
                    onSuccessfulRegister()
                }

                is RegisterUiEvent.RegisterFailed -> {
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
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp),
        contentAlignment = Alignment.Center
    ) {
        if(state.isError){
            Snackbar(
                modifier.align(Alignment.Center),
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                action = {
                    Button(onClick = {viewModel.onIntent(RegisterViewIntent.ErrorOkButtonClicked) })
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
                    text = stringResource(id = R.string.text_sign_up),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge
                )

                NormalTextField(
                    value = state.username,
                    label = stringResource(id = R.string.textfield_label_username),
                    onValueChange = { newValue ->
                        viewModel.onIntent(RegisterViewIntent.UsernameChanged(newValue))
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

                NormalTextField(
                    value = state.email,
                    label = stringResource(id = R.string.textfield_label_email),
                    onValueChange = { newValue ->
                        viewModel.onIntent(RegisterViewIntent.EmailChanged(newValue))
                    },
                    isError = state.isEmailError,
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
                        viewModel.onIntent(RegisterViewIntent.PasswordChanged(newValue))
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
                        viewModel.onIntent(RegisterViewIntent.TogglePasswordVisibility) },
                    onDone = { }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordTextField(
                    value = state.confirmPassword,
                    label = stringResource(id = R.string.textfield_label_confirm_password),
                    onValueChange = { newValue ->
                        viewModel.onIntent(RegisterViewIntent.ConfirmPasswordChanged(newValue))
                    },
                    isError = state.isConfirmPasswordError,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Key,
                            contentDescription = null
                        )
                    },
                    isVisible = state.isConfirmPasswordVisible,
                    onVisibilityChanged = { viewModel.onIntent(RegisterViewIntent.ToggleConfirmPasswordVisibility) },
                    onDone = { }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.onIntent(RegisterViewIntent.RegisterButtonClicked(context = context))
                    },
                    modifier = Modifier.width(TextFieldDefaults.MinWidth)
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
fun RegisterScreenPreview() {
    RegisterScreen()
}
