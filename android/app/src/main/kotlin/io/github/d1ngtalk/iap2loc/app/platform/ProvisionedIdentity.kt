package io.github.d1ngtalk.iap2loc.app.platform

import android.content.Context

/**
 * MFi material a distribution build may provision itself instead of asking the user to import it.
 *
 * The public sources never provision anything: [load] returns null, so a stock APK only uses the
 * files the user imports. A distribution build replaces this one file with an implementation that
 * supplies already-unwrapped material plus the text to show; nothing else in the public repository
 * knows how that material is packed, stored or protected.
 *
 * Contracts the rest of the app relies on:
 * - [material] is a complete key/certificate pair, or null when nothing usable is available;
 * - [description] names the source in logs and the console when [material] is used;
 * - [warning] explains why provisioned material is unavailable, and is surfaced in the console.
 */
internal class ProvisionedIdentity(
    val material: Pair<ByteArray, ByteArray>?,
    val description: String,
    val warning: String?,
) {
    companion object {
        /** Called once while the app object is created; replaced by distribution builds. */
        fun load(context: Context): ProvisionedIdentity? = null
    }
}
