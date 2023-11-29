package com.oliverclimbs.realmtest

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmDatabase
{
  private val config = RealmConfiguration.Builder(
    schema = setOf(Pressure::class,
                   GPS::class,
                   Flag::class,
                   Weather::class,
                   Outing::class))
    .name("ibex.realm")
    .compactOnLaunch { totalBytes, usedBytes -> true } // TODO: Optimize for production
    .deleteRealmIfMigrationNeeded() // TODO: remove this for production
    .build()

  fun getDatabase(): Realm = Realm.open(config)

}