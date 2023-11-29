package com.oliverclimbs.realmtest

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Index
import java.time.Instant

class GPS : EmbeddedRealmObject
{
  @Index
  var timestamp = 0L
  var lat: Double = -1.0
  var lon: Double = -1.0
  var accuracy: Float? = null
  var meters: Double? = null
  var verticalAccuracy: Float? = null
  var bearing: Float? = null
  var bearingAccuracy: Float? = null
  var speed: Float? = null
  var speedAccuracy: Float? = null
  var satellites: Int? = null
  var metersHPa: Double? = null
  var slopeFromPressure: Int? = null
  var slopeFromGPS: Int? = null

  // ---------------------------------------------------------------------------------------------
//  val feet: Double?
//    get()
//    {
//      return meters?.let { meters ->
//        metersToFeet(meters)
//      }
//    }

  // ---------------------------------------------------------------------------------------------
//  val feetHPa: Double?
//    get()
//    {
//      return metersHPa?.let { meters ->
//        metersToFeet(meters)
//      }
//    }

//  val timeString get() = timestamp.toLocalTimeString()
//  val dateString get() = timestamp.toLocalDateString()

  var time: Instant
    get() = Instant.ofEpochMilli(timestamp)
    set(value)
    {
      timestamp = value.toEpochMilli()
    }
}

