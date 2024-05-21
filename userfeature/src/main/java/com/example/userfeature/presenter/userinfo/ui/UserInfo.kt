import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.domain.model.UserInfo
import com.example.ui.Elevation
import com.example.ui.LocalElevation
import com.example.ui.LocalPadding
import com.example.ui.LocalTextSizes
import com.example.ui.Padding
import com.example.ui.TextSizes
import com.example.userfeature.R

@Composable
fun UserInfo(userInfo: UserInfo, modifier: Modifier = Modifier) {
    val padding = LocalPadding.current
    val paddingMedium = padding.medium
    val elevation = LocalElevation.current
    val textSizes = LocalTextSizes.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingMedium)
    ) {
        UserDetailsCard(
            userInfo = userInfo,
            padding = padding,
            elevation = elevation,
            textSizes = textSizes
        )
    }
}

@Composable
private fun UserDetailsCard(
    userInfo: UserInfo,
    padding: Padding,
    elevation: Elevation,
    textSizes: TextSizes
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.medium),
        elevation = elevation.medium
    ) {
        UserDetailsContent(
            userInfo = userInfo,
            padding = padding,
            textSizes = textSizes
        )
    }
}

@Composable
private fun UserDetailsContent(
    userInfo: UserInfo,
    padding: Padding,
    textSizes: TextSizes
) {
    Column(
        modifier = Modifier
            .padding(padding.medium)
            .fillMaxWidth()
    ) {
        // Name
        UserInfoText(
            text = userInfo.name,
            fontSize = textSizes.doubleExtraLarge,
            fontWeight = FontWeight.Bold,
            marginBottom = padding.small.calculateBottomPadding()
        )

        // Email
        UserInfoText(
            text = userInfo.email,
            fontSize = textSizes.medium,
            marginBottom = padding.small.calculateBottomPadding()
        )

        // Company info
        Column(
            modifier = Modifier.padding(
                top = padding.medium.calculateTopPadding()
            ),
            horizontalAlignment = Alignment.Start
        ) {
            UserInfoText(
                text = stringResource(R.string.username_text, userInfo.username),
                fontSize = textSizes.large,
                fontWeight = FontWeight.Bold
            )
            UserInfoText(
                text = stringResource(R.string.street_text, userInfo.address.street),
                fontSize = textSizes.medium,
                maxLines = 2
            )
            UserInfoText(
                text = stringResource(R.string.zip_code_text, userInfo.address.zipcode),
                fontSize = textSizes.medium
            )
        }
    }
}

@Composable
private fun UserInfoText(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight? = null,
    maxLines: Int = 1,
    marginBottom: Dp = 0.dp
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = maxLines,
        modifier = Modifier.padding(bottom = marginBottom)
    )
}
