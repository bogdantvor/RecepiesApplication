package models

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.IOException

object AssetUtils {
    fun loadImageDrawableFromAssets(context: Context, assetFileName: String): Drawable? {
        return try {
            context.assets.open(assetFileName).use { inputStream ->
                Drawable.createFromStream(inputStream, null)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

