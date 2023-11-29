package com.oliverclimbs.realmtest

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.FullText
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.time.Instant

@Suppress("PropertyName")
class Outing : RealmObject
{
  @PrimaryKey
  var _id: ObjectId = ObjectId()

  @Index
  var start: Long = 0L
  var stop: Long = 0L
  var timeZone: Float? = null

  @FullText
  var name: String = ""

  var pressure: RealmList<Pressure> = realmListOf()
  var gps: RealmList<GPS> = realmListOf()
  var weather: RealmList<Weather> = realmListOf()
  var flag: RealmList<Flag> = realmListOf()

  var location: GPS? = null
  var pitches: Int? = null
  var type: String? = null
  var gradeKey: Int? = null
  var meters: Int? = null
  var heightMetric: Boolean? = null
  var slope: Int? = null
  var rope: Int? = null
  var doubleRope: Boolean? = null
  var difficulty: Float? = null
  var averageHeartRate: Int? = null
  var barometricDriftCompensation: Boolean? = null
  var multiPitchDriftCompensation: Boolean? = null
  var distanceThreshold: Int? = null
  var speedThreshold: Float? = null
  var climbTimeout: Int? = null
  var climbTimeoutEnabled: Boolean? = null
  var rappelTimeout: Int? = null
  var restTimeout: Int? = null
  var processNoise: Double? = null
  var climbNoiseLimit: Double? = null
  var rappelNoiseLimit: Double? = null
  var measureNoise: Double? = null
  var initialEstimationNoise: Double? = null
  var model: String? = null

  val duration: Instant get() = Instant.ofEpochMilli(stop - start)

//  val startTimeString get() = start.toLocalTimeString()
//  val stopTimeString get() = stop.toLocalTimeString()
//  val startDateString get() = start.toLocalDateString()
//  val stopDateString get() = stop.toLocalDateString()

  var startTime: Instant
    get() = Instant.ofEpochMilli(start)
    set(value)
    {
      start = value.toEpochMilli()
    }

  var stopTime: Instant
    get() = Instant.ofEpochMilli(stop)
    set(value)
    {
      stop = value.toEpochMilli()
    }

//  val feet get() = metersToFeet(meters?.toDouble() ?: 0.0)

  enum class Type
  {
    Trad, Sport, Aid, Ice, Mixed, Other;

    override fun toString(): String
    {
      return when (this)
      {
        Trad -> "Trad"
        Sport -> "Sport"
        Aid -> "Aid"
        Ice -> "Ice"
        Mixed -> "Mixed"
        Other -> "Other"
      }
    }
  }

  // ---------------------------------------------------------------------------------------------
//  fun getValues(outingName: String = nowToFilename()): Outing
//  {
//    name = outingName
//    rope = sharedViewModel.upDown.rope
//    doubleRope = sharedViewModel.upDown.rappelType == DOUBLE_ROPE
//    slope = sharedViewModel.slopeUsed.value
//    meters = sharedViewModel.upDown.totalDistance
//    difficulty = sharedViewModel.outingDifficulty
//    averageHeartRate = sharedViewModel.outingHeartRate
//    barometricDriftCompensation = sharedViewModel.barometricDriftCompensation
//    multiPitchDriftCompensation = sharedViewModel.upDown.driftCompensation
//    climbTimeoutEnabled = sharedViewModel.upDown.upTimeoutEnabled
//    measureNoise = sharedViewModel.upDown.kalmanFilter.measurementNoise
//    processNoise = sharedViewModel.upDown.kalmanSettings.processNoise
//    initialEstimationNoise = sharedViewModel.upDown.kalmanFilter.initialStateNoise
//    rappelTimeout = sharedViewModel.upDown.downTimeout.toMinutes().toInt()
//    restTimeout = sharedViewModel.upDown.pauseTimeout.toMinutes().toInt()
//    climbNoiseLimit = sharedViewModel.upDown.kalmanSettings.upNoiseLimit
//    rappelNoiseLimit = sharedViewModel.upDown.kalmanSettings.downNoiseLimit
//    distanceThreshold = sharedViewModel.upDown.distanceThreshold.toInt()
//    speedThreshold = sharedViewModel.upDown.speedThreshold.toFloat()
//    timeZone = timeZoneOffset()
//    model = Build.MODEL
//
//    if (climbTimeoutEnabled == true)
//      climbTimeout = sharedViewModel.upDown.upTimeout.toMinutes().toInt()
//
//    return this
//
//  }

  // ---------------------------------------------------------------------------------------------
//  fun setValues(): Outing
//  {
//    sharedViewModel.postOutingName(name)
//    sharedViewModel.upDown.rope = rope ?: 0
//    sharedViewModel.upDown.rappelType = if (doubleRope == true) DOUBLE_ROPE else SINGLE_ROPE
//    sharedViewModel.postSlopeUsed(slope ?: SLOPE_DEFAULT)
//    sharedViewModel.upDown.totalDistance = meters ?: 0
//    sharedViewModel.outingDifficulty = difficulty ?: DIFFICULTY_DEFAULT
//    sharedViewModel.outingHeartRate = averageHeartRate ?: 0
//    sharedViewModel.barometricDriftCompensation = barometricDriftCompensation ?: false
//    sharedViewModel.upDown.driftCompensation = multiPitchDriftCompensation ?: false
//    sharedViewModel.upDown.kalmanFilter.measurementNoise = measureNoise ?: MEASURE_NOISE_DEFAULT
//    sharedViewModel.upDown.kalmanSettings.setProcessNoises(processNoise ?: PROCESS_NOISE_DEFAULT)
//    sharedViewModel.upDown.kalmanFilter.initialStateNoise =
//      initialEstimationNoise ?: INITIAL_ESTIMATION_NOISE_DEFAULT
//    sharedViewModel.upDown.downTimeout =
//      Duration.ofMinutes((rappelTimeout ?: RAPPEL_TIMEOUT_DEFAULT).toLong())
//    sharedViewModel.upDown.pauseTimeout =
//      Duration.ofMinutes((restTimeout ?: REST_TIMEOUT_DEFAULT).toLong())
//    sharedViewModel.upDown.kalmanSettings.upNoiseLimit = climbNoiseLimit ?: 1.0
//    sharedViewModel.upDown.kalmanSettings.downNoiseLimit = rappelNoiseLimit ?: 1.0
//    sharedViewModel.upDown.distanceThreshold =
//      (distanceThreshold ?: DISTANCE_THRESHOLD_DEFAULT).toDouble()
//    sharedViewModel.upDown.speedThreshold = (speedThreshold ?: SPEED_THRESHOLD_DEFAULT).toDouble()
//
//    if (climbTimeoutEnabled == true)
//      sharedViewModel.upDown.upTimeout =
//        Duration.ofMinutes((climbTimeout ?: CLIMB_TIMEOUT_DEFAULT).toLong())
//    else
//      sharedViewModel.upDown.disableUpTimeout()
//
//    return this
//  }
}