package com.oliverclimbs.realmtest

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.annotations.Index
import java.time.Instant

class Flag : EmbeddedRealmObject
{
  @Index
  var timestamp: Long = 0L
  var type: String? = null
  var pitch: String? = null
  var distance: Int? = null
  var gradeKey: Int? = null
  var name: String? = null
  var lead: Boolean? = null
  var outcome: String? = null
  
  enum class Type
  {
    Trail, Base, Start, Belay, Pitch, Top, POI;
    
    override fun toString(): String
    {
      return when (this)
      {
        Trail -> "Trail"
        Base -> "Base"
        Start -> "Start"
        Belay -> "Belay"
        Pitch -> "Pitch"
        Top -> "Top"
        POI -> "POI"
      }
    }
  }
  
  enum class Outcome
  {
    OnSight, Flash, RedPoint, Lead, GreenPoint, Follow, Project, Attempt, Unknown;
    
    override fun toString(): String
    {
      return when (this)
      {
        OnSight -> "On Sight"
        Flash -> "Flash"
        RedPoint -> "Red Point"
        Lead -> "Lead"
        GreenPoint -> "Green Point"
        Follow -> "Follow/Top Rope"
        Project -> "Project"
        Attempt -> "Attempt"
        Unknown -> "Unknown"
      }
    }
  }
  
//  val timeString get() = timestamp.toLocalTimeString()
//  val dateString get() = timestamp.toLocalTimeString()
  
  var time: Instant
    get() = Instant.ofEpochMilli(timestamp)
    set(value)
    {
      timestamp = value.toEpochMilli()
    }
}


