package edu.iau.cshj.csc402.lab3



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChainA(modifier: Modifier = Modifier) {
    Text(
        text = "content",
        modifier = modifier
            .background(Color(0xFF3DDC84))
            .padding(24.dp)
    )
}

@Composable
fun ChainB(modifier: Modifier = Modifier) {
    Text(
        text = "content",
        modifier = modifier
            .padding(24.dp)
            .background(Color(0xFF3DDC84))
    )
}

@Composable
fun ChainC(modifier: Modifier = Modifier) {
    Text(
        text = "content",
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF3DDC84))
            .padding(24.dp)
    )
}

@Composable
fun ClickablePaddingExample(modifier: Modifier = Modifier) {
    Column(
            modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Clickable then Padding",
            modifier = Modifier
                .clickable { }
                .padding(16.dp)
        )

        Text(
            text = "Padding then Clickable",
            modifier = Modifier
                .padding(16.dp)
                .clickable { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderPuzzlePreview() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ChainA()
        ChainB()
        ChainC()

        ClickablePaddingExample()
    }
}