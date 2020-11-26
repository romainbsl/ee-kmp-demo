package org.kodein.shared

expect class PlatformContext
expect fun getApplicationFilesDirectoryPath(ctx: PlatformContext): String
