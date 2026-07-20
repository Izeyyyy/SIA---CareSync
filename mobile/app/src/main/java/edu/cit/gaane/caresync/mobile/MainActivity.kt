package edu.cit.gaane.caresync.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.cit.gaane.caresync.mobile.shared.navigation.AppNavigation
import edu.cit.gaane.caresync.mobile.ui.theme.MobileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MobileTheme {

                AppNavigation()

            }

        }

    }

}


@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    MobileTheme {

        AppNavigation()

    }

}