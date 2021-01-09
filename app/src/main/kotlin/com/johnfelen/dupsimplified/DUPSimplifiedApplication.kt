package com.johnfelen.dupsimplified

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class DUPSimplifiedApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {}
}