package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.miluconnect.profeliomp.domain.models.Offer
import org.jetbrains.compose.resources.painterResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.image_rest



@Composable
fun OffersListItem(
    offer: Offer,
    modifier: Modifier = Modifier,
) {

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = offer.imageUrl,
        onSuccess = {
            imageLoadResult = if(it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image size"))
            }
        },
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        }
    )

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
        modifier = modifier.clickable(onClick = {})
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = modifier.height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                when (val result = imageLoadResult) {
                    null -> CircularProgressIndicator()
                    else -> {
                        Image(
                            contentDescription = offer.title,
                            painter = if (result.isSuccess) {
                                painter
                            } else {
                                painterResource(Res.drawable.image_rest)
                            },
                            contentScale = if (result.isSuccess) {
                                ContentScale.Crop
                            } else {
                                ContentScale.Fit
                            }
                        )
                    }
                }

            }
        }

    }

//    Text(text = "Offer: ${offer.title}, Category: ${offer.category}, City: ${offer.city}")
}