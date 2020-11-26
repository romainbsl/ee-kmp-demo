package org.kodein.shared

import android.app.Application


actual class PlatformContext(val application: Application)

actual fun getApplicationFilesDirectoryPath(ctx: PlatformContext): String =
    ctx.application.filesDir.absolutePath
