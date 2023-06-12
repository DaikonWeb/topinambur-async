package topinambur.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import java.io.PrintStream


fun String.httpAsync(scope: CoroutineScope = GlobalScope, printer: PrintStream? = null) =
    HttpAsync(scope, this, printer)