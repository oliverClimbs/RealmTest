package com.oliverclimbs.realmtest

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Index
import java.time.Instant

class Pressure : EmbeddedRealmObject
{
  @Index
  var timestamp: Long = 0L
  var hPa: Float = 0.0F
  var hPa0: Float? = null
  var hPa0Observed: Boolean? = null
  var slope: Int? = null
  var accuracy: Int? = null
  
//  val meters get() = hPaToMeters(hPa, hPa0)
//  val feet get() = metersToFeet(meters)
//
//  val timeString get() = timestamp.toLocalTimeString()
//  val dateString get() = timestamp.toLocalDateString()
  
  var time: Instant
    get() = Instant.ofEpochMilli(timestamp)
    set(value)
    {
      timestamp = value.toEpochMilli()
    }
}

