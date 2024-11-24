package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.writer_reader_client.R

@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        singleLine = true,
        placeholder = { Text(stringResource(id = R.string.search_bar_placeholder_text)) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    modifier = modifier.clickable {
                        onTextChange("")
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
            }
        },
        shape = androidx.compose.material3.MaterialTheme.shapes.medium,
    )
}

@Preview
@Composable
fun SearchTopAppBar2Preview() {
    SearchTopAppBar(text = "asd", onTextChange = {})
}