package hu.bme.aut.android.writer_reader_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import hu.bme.aut.android.writer_reader_client.navigation.NavGraph
import hu.bme.aut.android.writer_reader_client.ui.common.WorkCard
import hu.bme.aut.android.writer_reader_client.ui.theme.WriterReaderClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WriterReaderClientTheme {
                NavGraph(

                )



            }
        }
    }
}
