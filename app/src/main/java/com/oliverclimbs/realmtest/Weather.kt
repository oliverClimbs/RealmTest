package com.oliverclimbs.realmtest

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Index
import java.time.Instant

class Weather : EmbeddedRealmObject
{
  @Index
  var timestamp: Long = 0L
  var pressure: Float? = null
  var temperature: Float? = null
  var humidity: Float?  = null
  var stations: String? = null
  
  val observed get() = stations != null && stations!!.isNotEmpty()
  
  var time: Instant
    get() = Instant.ofEpochMilli(timestamp)
    set(value)
    {
      timestamp = value.toEpochMilli()
    }
}