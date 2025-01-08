package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.miluconnect.profeliomp.domain.models.Offer
import org.jetbrains.compose.resources.painterResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.image_rest

@Composable
fun OffersListItem(
    offer: Offer,
    onOfferClick: (Offer) -> Unit,
    modifier: Modifier = Modifier,
) {

    /**
     * Component logic
     * */
    val painter = rememberAsyncImagePainter(
        model = offer.imageUrl,
        placeholder = painterResource(Res.drawable.image_rest),
        error = painterResource(Res.drawable.image_rest),
        contentScale = ContentScale.Crop
    )

    /**
     * Return
     * */
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
        modifier = modifier.clickable(onClick = {})
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(0.65f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    contentDescription = offer.title,
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = offer.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = offer.city,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = offer.category,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Arrow forward",
                modifier = Modifier.size(36.dp)
            )
        }
    }

//    Text(text = "Offer: ${offer.title}, Category: ${offer.category}, City: ${offer.city}")
}