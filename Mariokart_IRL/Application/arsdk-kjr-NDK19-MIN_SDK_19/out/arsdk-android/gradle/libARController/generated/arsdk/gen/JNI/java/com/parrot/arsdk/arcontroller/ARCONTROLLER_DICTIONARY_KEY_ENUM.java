/*
    Copyright (C) 2014 Parrot SA

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:
    * Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in
    the documentation and/or other materials provided with the
    distribution.
    * Neither the name of Parrot nor the names
    of its contributors may be used to endorse or promote products
    derived from this software without specific prior written
    permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
    "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
    LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
    FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
    COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
    OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
    AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
    SUCH DAMAGE.
*/

package com.parrot.arsdk.arcontroller;

import java.util.HashMap;

/**
 * Java copy of the eARCONTROLLER_DICTIONARY_KEY enum
 */
public enum ARCONTROLLER_DICTIONARY_KEY_ENUM {
    /** Dummy value for all unknown cases */
    eARCONTROLLER_DICTIONARY_KEY_UNKNOWN_ENUM_VALUE (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** Key used to define the feature <code>Generic</code> */
    ARCONTROLLER_DICTIONARY_KEY_GENERIC (0, "Key used to define the feature <code>Generic</code>"),
    /** Key used to define the event <code>DroneSettingsChanged</code> in project <code>Generic</code>*/
    ARCONTROLLER_DICTIONARY_KEY_GENERIC_DRONESETTINGSCHANGED (1, "Key used to define the event <code>DroneSettingsChanged</code> in project <code>Generic</code>"),
    /** Key used to define the feature <code>Animation</code> */
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION (2, "Key used to define the feature <code>Animation</code>"),
    /** Key used to define the event <code>Availability</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_AVAILABILITY (3, "Key used to define the event <code>Availability</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>State</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_STATE (4, "Key used to define the event <code>State</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>FlipState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_FLIPSTATE (5, "Key used to define the event <code>FlipState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>HorizontalPanoramaState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_HORIZONTALPANORAMASTATE (6, "Key used to define the event <code>HorizontalPanoramaState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>DronieState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_DRONIESTATE (7, "Key used to define the event <code>DronieState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>HorizontalRevealState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_HORIZONTALREVEALSTATE (8, "Key used to define the event <code>HorizontalRevealState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>VerticalRevealState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_VERTICALREVEALSTATE (9, "Key used to define the event <code>VerticalRevealState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>SpiralState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_SPIRALSTATE (10, "Key used to define the event <code>SpiralState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>ParabolaState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_PARABOLASTATE (11, "Key used to define the event <code>ParabolaState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>CandleState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_CANDLESTATE (12, "Key used to define the event <code>CandleState</code> in project <code>Animation</code>"),
    /** Key used to define the event <code>DollySlideState</code> in project <code>Animation</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ANIMATION_DOLLYSLIDESTATE (13, "Key used to define the event <code>DollySlideState</code> in project <code>Animation</code>"),
    /** Key used to define the feature <code>ARDrone3</code> */
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3 (14, "Key used to define the feature <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDSTATE_PICTURESTATECHANGED (15, "Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDSTATE_VIDEOSTATECHANGED (16, "Key used to define the event <code>MediaRecordStateVideoStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDSTATE_PICTURESTATECHANGEDV2 (17, "Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2 (18, "Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoResolutionState</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDSTATE_VIDEORESOLUTIONSTATE (19, "Key used to define the event <code>MediaRecordStateVideoResolutionState</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDEVENT_PICTUREEVENTCHANGED (20, "Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIARECORDEVENT_VIDEOEVENTCHANGED (21, "Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateFlatTrimChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLATTRIMCHANGED (22, "Key used to define the event <code>PilotingStateFlatTrimChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED (23, "Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED (24, "Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateNavigateHomeStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED (25, "Key used to define the event <code>PilotingStateNavigateHomeStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStatePositionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED (26, "Key used to define the event <code>PilotingStatePositionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateSpeedChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED (27, "Key used to define the event <code>PilotingStateSpeedChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateAttitudeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ATTITUDECHANGED (28, "Key used to define the event <code>PilotingStateAttitudeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateAutoTakeOffModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_AUTOTAKEOFFMODECHANGED (29, "Key used to define the event <code>PilotingStateAutoTakeOffModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateAltitudeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALTITUDECHANGED (30, "Key used to define the event <code>PilotingStateAltitudeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateGpsLocationChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_GPSLOCATIONCHANGED (31, "Key used to define the event <code>PilotingStateGpsLocationChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateLandingStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_LANDINGSTATECHANGED (32, "Key used to define the event <code>PilotingStateLandingStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateAirSpeedChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_AIRSPEEDCHANGED (33, "Key used to define the event <code>PilotingStateAirSpeedChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateMoveToChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_MOVETOCHANGED (34, "Key used to define the event <code>PilotingStateMoveToChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateMotionState</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_MOTIONSTATE (35, "Key used to define the event <code>PilotingStateMotionState</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStatePilotedPOI</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_PILOTEDPOI (36, "Key used to define the event <code>PilotingStatePilotedPOI</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingStateReturnHomeBatteryCapacity</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY (37, "Key used to define the event <code>PilotingStateReturnHomeBatteryCapacity</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingEventMoveByEnd</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGEVENT_MOVEBYEND (38, "Key used to define the event <code>PilotingEventMoveByEnd</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSTATE_WIFISCANLISTCHANGED (39, "Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSTATE_ALLWIFISCANCHANGED (40, "Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED (41, "Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSTATE_ALLWIFIAUTHCHANNELCHANGED (42, "Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxAltitudeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_MAXALTITUDECHANGED (43, "Key used to define the event <code>PilotingSettingsStateMaxAltitudeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxTiltChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_MAXTILTCHANGED (44, "Key used to define the event <code>PilotingSettingsStateMaxTiltChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAbsolutControlChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_ABSOLUTCONTROLCHANGED (45, "Key used to define the event <code>PilotingSettingsStateAbsolutControlChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxDistanceChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_MAXDISTANCECHANGED (46, "Key used to define the event <code>PilotingSettingsStateMaxDistanceChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateNoFlyOverMaxDistanceChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_NOFLYOVERMAXDISTANCECHANGED (47, "Key used to define the event <code>PilotingSettingsStateNoFlyOverMaxDistanceChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxHorizontalSpeed</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_AUTONOMOUSFLIGHTMAXHORIZONTALSPEED (48, "Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxHorizontalSpeed</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxVerticalSpeed</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_AUTONOMOUSFLIGHTMAXVERTICALSPEED (49, "Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxVerticalSpeed</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxHorizontalAcceleration</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_AUTONOMOUSFLIGHTMAXHORIZONTALACCELERATION (50, "Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxHorizontalAcceleration</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxVerticalAcceleration</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_AUTONOMOUSFLIGHTMAXVERTICALACCELERATION (51, "Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxVerticalAcceleration</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxRotationSpeed</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_AUTONOMOUSFLIGHTMAXROTATIONSPEED (52, "Key used to define the event <code>PilotingSettingsStateAutonomousFlightMaxRotationSpeed</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateBankedTurnChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_BANKEDTURNCHANGED (53, "Key used to define the event <code>PilotingSettingsStateBankedTurnChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMinAltitudeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_MINALTITUDECHANGED (54, "Key used to define the event <code>PilotingSettingsStateMinAltitudeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateCirclingDirectionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_CIRCLINGDIRECTIONCHANGED (55, "Key used to define the event <code>PilotingSettingsStateCirclingDirectionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateCirclingRadiusChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_CIRCLINGRADIUSCHANGED (56, "Key used to define the event <code>PilotingSettingsStateCirclingRadiusChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateCirclingAltitudeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_CIRCLINGALTITUDECHANGED (57, "Key used to define the event <code>PilotingSettingsStateCirclingAltitudeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStatePitchModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_PITCHMODECHANGED (58, "Key used to define the event <code>PilotingSettingsStatePitchModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMotionDetection</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSETTINGSSTATE_MOTIONDETECTION (59, "Key used to define the event <code>PilotingSettingsStateMotionDetection</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SPEEDSETTINGSSTATE_MAXVERTICALSPEEDCHANGED (60, "Key used to define the event <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SPEEDSETTINGSSTATE_MAXROTATIONSPEEDCHANGED (61, "Key used to define the event <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SpeedSettingsStateHullProtectionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SPEEDSETTINGSSTATE_HULLPROTECTIONCHANGED (62, "Key used to define the event <code>SpeedSettingsStateHullProtectionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SpeedSettingsStateOutdoorChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SPEEDSETTINGSSTATE_OUTDOORCHANGED (63, "Key used to define the event <code>SpeedSettingsStateOutdoorChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxPitchRollRotationSpeedChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SPEEDSETTINGSSTATE_MAXPITCHROLLROTATIONSPEEDCHANGED (64, "Key used to define the event <code>SpeedSettingsStateMaxPitchRollRotationSpeedChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED (65, "Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkSettingsStateWifiSecurityChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSETTINGSSTATE_WIFISECURITYCHANGED (66, "Key used to define the event <code>NetworkSettingsStateWifiSecurityChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>NetworkSettingsStateWifiSecurity</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_NETWORKSETTINGSSTATE_WIFISECURITY (67, "Key used to define the event <code>NetworkSettingsStateWifiSecurity</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateProductMotorVersionListChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_PRODUCTMOTORVERSIONLISTCHANGED (68, "Key used to define the event <code>SettingsStateProductMotorVersionListChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateProductGPSVersionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_PRODUCTGPSVERSIONCHANGED (69, "Key used to define the event <code>SettingsStateProductGPSVersionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateMotorErrorStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED (70, "Key used to define the event <code>SettingsStateMotorErrorStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateMotorSoftwareVersionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORSOFTWAREVERSIONCHANGED (71, "Key used to define the event <code>SettingsStateMotorSoftwareVersionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateMotorFlightsStatusChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORFLIGHTSSTATUSCHANGED (72, "Key used to define the event <code>SettingsStateMotorFlightsStatusChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateMotorErrorLastErrorChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORLASTERRORCHANGED (73, "Key used to define the event <code>SettingsStateMotorErrorLastErrorChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateP7ID</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_P7ID (74, "Key used to define the event <code>SettingsStateP7ID</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SettingsStateCPUID</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_CPUID (75, "Key used to define the event <code>SettingsStateCPUID</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStatePictureFormatChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_PICTUREFORMATCHANGED (76, "Key used to define the event <code>PictureSettingsStatePictureFormatChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateAutoWhiteBalanceChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_AUTOWHITEBALANCECHANGED (77, "Key used to define the event <code>PictureSettingsStateAutoWhiteBalanceChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateExpositionChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_EXPOSITIONCHANGED (78, "Key used to define the event <code>PictureSettingsStateExpositionChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateSaturationChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_SATURATIONCHANGED (79, "Key used to define the event <code>PictureSettingsStateSaturationChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateTimelapseChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_TIMELAPSECHANGED (80, "Key used to define the event <code>PictureSettingsStateTimelapseChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateVideoAutorecordChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_VIDEOAUTORECORDCHANGED (81, "Key used to define the event <code>PictureSettingsStateVideoAutorecordChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateVideoStabilizationModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_VIDEOSTABILIZATIONMODECHANGED (82, "Key used to define the event <code>PictureSettingsStateVideoStabilizationModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateVideoRecordingModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_VIDEORECORDINGMODECHANGED (83, "Key used to define the event <code>PictureSettingsStateVideoRecordingModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateVideoFramerateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_VIDEOFRAMERATECHANGED (84, "Key used to define the event <code>PictureSettingsStateVideoFramerateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PictureSettingsStateVideoResolutionsChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PICTURESETTINGSSTATE_VIDEORESOLUTIONSCHANGED (85, "Key used to define the event <code>PictureSettingsStateVideoResolutionsChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED (86, "Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>MediaStreamingStateVideoStreamModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_MEDIASTREAMINGSTATE_VIDEOSTREAMMODECHANGED (87, "Key used to define the event <code>MediaStreamingStateVideoStreamModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateHomeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED (88, "Key used to define the event <code>GPSSettingsStateHomeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateResetHomeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED (89, "Key used to define the event <code>GPSSettingsStateResetHomeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateGPSFixStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSFIXSTATECHANGED (90, "Key used to define the event <code>GPSSettingsStateGPSFixStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateGPSUpdateStateChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSUPDATESTATECHANGED (91, "Key used to define the event <code>GPSSettingsStateGPSUpdateStateChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateHomeTypeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMETYPECHANGED (92, "Key used to define the event <code>GPSSettingsStateHomeTypeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateReturnHomeDelayChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RETURNHOMEDELAYCHANGED (93, "Key used to define the event <code>GPSSettingsStateReturnHomeDelayChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSSettingsStateGeofenceCenterChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GEOFENCECENTERCHANGED (94, "Key used to define the event <code>GPSSettingsStateGeofenceCenterChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>CameraStateOrientation</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_CAMERASTATE_ORIENTATION (95, "Key used to define the event <code>CameraStateOrientation</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>CameraStateDefaultCameraOrientation</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_CAMERASTATE_DEFAULTCAMERAORIENTATION (96, "Key used to define the event <code>CameraStateDefaultCameraOrientation</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>CameraStateOrientationV2</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_CAMERASTATE_ORIENTATIONV2 (97, "Key used to define the event <code>CameraStateOrientationV2</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>CameraStateDefaultCameraOrientationV2</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_CAMERASTATE_DEFAULTCAMERAORIENTATIONV2 (98, "Key used to define the event <code>CameraStateDefaultCameraOrientationV2</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>CameraStateVelocityRange</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_CAMERASTATE_VELOCITYRANGE (99, "Key used to define the event <code>CameraStateVelocityRange</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>AntiflickeringStateElectricFrequencyChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ANTIFLICKERINGSTATE_ELECTRICFREQUENCYCHANGED (100, "Key used to define the event <code>AntiflickeringStateElectricFrequencyChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>AntiflickeringStateModeChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ANTIFLICKERINGSTATE_MODECHANGED (101, "Key used to define the event <code>AntiflickeringStateModeChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSStateNumberOfSatelliteChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_NUMBEROFSATELLITECHANGED (102, "Key used to define the event <code>GPSStateNumberOfSatelliteChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSStateHomeTypeAvailabilityChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_HOMETYPEAVAILABILITYCHANGED (103, "Key used to define the event <code>GPSStateHomeTypeAvailabilityChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>GPSStateHomeTypeChosenChanged</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_HOMETYPECHOSENCHANGED (104, "Key used to define the event <code>GPSStateHomeTypeChosenChanged</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>PROStateFeatures</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PROSTATE_FEATURES (105, "Key used to define the event <code>PROStateFeatures</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>AccessoryStateConnectedAccessories</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES (106, "Key used to define the event <code>AccessoryStateConnectedAccessories</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>AccessoryStateBattery</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_BATTERY (107, "Key used to define the event <code>AccessoryStateBattery</code> in project <code>ARDrone3</code>"),
    /** Key used to define the event <code>SoundStateAlertSound</code> in project <code>ARDrone3</code>*/
    ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SOUNDSTATE_ALERTSOUND (108, "Key used to define the event <code>SoundStateAlertSound</code> in project <code>ARDrone3</code>"),
    /** Key used to define the feature <code>Common</code> */
    ARCONTROLLER_DICTIONARY_KEY_COMMON (109, "Key used to define the feature <code>Common</code>"),
    /** Key used to define the event <code>NetworkEventDisconnection</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_NETWORKEVENT_DISCONNECTION (110, "Key used to define the event <code>NetworkEventDisconnection</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateAllSettingsChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_ALLSETTINGSCHANGED (111, "Key used to define the event <code>SettingsStateAllSettingsChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateResetChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_RESETCHANGED (112, "Key used to define the event <code>SettingsStateResetChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateProductNameChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTNAMECHANGED (113, "Key used to define the event <code>SettingsStateProductNameChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateProductVersionChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTVERSIONCHANGED (114, "Key used to define the event <code>SettingsStateProductVersionChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateProductSerialHighChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTSERIALHIGHCHANGED (115, "Key used to define the event <code>SettingsStateProductSerialHighChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateProductSerialLowChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTSERIALLOWCHANGED (116, "Key used to define the event <code>SettingsStateProductSerialLowChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateCountryChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_COUNTRYCHANGED (117, "Key used to define the event <code>SettingsStateCountryChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>SettingsStateAutoCountryChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_AUTOCOUNTRYCHANGED (118, "Key used to define the event <code>SettingsStateAutoCountryChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateAllStatesChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_ALLSTATESCHANGED (119, "Key used to define the event <code>CommonStateAllStatesChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateBatteryStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_BATTERYSTATECHANGED (120, "Key used to define the event <code>CommonStateBatteryStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateMassStorageStateListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGESTATELISTCHANGED (121, "Key used to define the event <code>CommonStateMassStorageStateListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateMassStorageInfoStateListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGEINFOSTATELISTCHANGED (122, "Key used to define the event <code>CommonStateMassStorageInfoStateListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateCurrentDateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_CURRENTDATECHANGED (123, "Key used to define the event <code>CommonStateCurrentDateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateCurrentTimeChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_CURRENTTIMECHANGED (124, "Key used to define the event <code>CommonStateCurrentTimeChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateMassStorageInfoRemainingListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGEINFOREMAININGLISTCHANGED (125, "Key used to define the event <code>CommonStateMassStorageInfoRemainingListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateWifiSignalChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_WIFISIGNALCHANGED (126, "Key used to define the event <code>CommonStateWifiSignalChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateSensorsStatesListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED (127, "Key used to define the event <code>CommonStateSensorsStatesListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateProductModel</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_PRODUCTMODEL (128, "Key used to define the event <code>CommonStateProductModel</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateCountryListKnown</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_COUNTRYLISTKNOWN (129, "Key used to define the event <code>CommonStateCountryListKnown</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateDeprecatedMassStorageContentChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_DEPRECATEDMASSSTORAGECONTENTCHANGED (130, "Key used to define the event <code>CommonStateDeprecatedMassStorageContentChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateMassStorageContent</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGECONTENT (131, "Key used to define the event <code>CommonStateMassStorageContent</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateMassStorageContentForCurrentRun</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGECONTENTFORCURRENTRUN (132, "Key used to define the event <code>CommonStateMassStorageContentForCurrentRun</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CommonStateVideoRecordingTimestamp</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_VIDEORECORDINGTIMESTAMP (133, "Key used to define the event <code>CommonStateVideoRecordingTimestamp</code> in project <code>Common</code>"),
    /** Key used to define the event <code>OverHeatStateOverHeatChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_OVERHEATSTATE_OVERHEATCHANGED (134, "Key used to define the event <code>OverHeatStateOverHeatChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>OverHeatStateOverHeatRegulationChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_OVERHEATSTATE_OVERHEATREGULATIONCHANGED (135, "Key used to define the event <code>OverHeatStateOverHeatRegulationChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>WifiSettingsStateOutdoorSettingsChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_WIFISETTINGSSTATE_OUTDOORSETTINGSCHANGED (136, "Key used to define the event <code>WifiSettingsStateOutdoorSettingsChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>MavlinkStateMavlinkFilePlayingStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED (137, "Key used to define the event <code>MavlinkStateMavlinkFilePlayingStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>MavlinkStateMavlinkPlayErrorStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED (138, "Key used to define the event <code>MavlinkStateMavlinkPlayErrorStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>MavlinkStateMissionItemExecuted</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MISSIONITEMEXECUTED (139, "Key used to define the event <code>MavlinkStateMissionItemExecuted</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanSettingsStateReturnHomeOnDisconnectChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED (140, "Key used to define the event <code>FlightPlanSettingsStateReturnHomeOnDisconnectChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTATECHANGED (141, "Key used to define the event <code>CalibrationStateMagnetoCalibrationStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationRequiredState</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONREQUIREDSTATE (142, "Key used to define the event <code>CalibrationStateMagnetoCalibrationRequiredState</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationAxisToCalibrateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONAXISTOCALIBRATECHANGED (143, "Key used to define the event <code>CalibrationStateMagnetoCalibrationAxisToCalibrateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationStartedChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTARTEDCHANGED (144, "Key used to define the event <code>CalibrationStateMagnetoCalibrationStartedChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CalibrationStatePitotCalibrationStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_PITOTCALIBRATIONSTATECHANGED (145, "Key used to define the event <code>CalibrationStatePitotCalibrationStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>CameraSettingsStateCameraSettingsChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CAMERASETTINGSSTATE_CAMERASETTINGSCHANGED (146, "Key used to define the event <code>CameraSettingsStateCameraSettingsChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanStateAvailabilityStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_AVAILABILITYSTATECHANGED (147, "Key used to define the event <code>FlightPlanStateAvailabilityStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanStateComponentStateListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED (148, "Key used to define the event <code>FlightPlanStateComponentStateListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanStateLockStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_LOCKSTATECHANGED (149, "Key used to define the event <code>FlightPlanStateLockStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanEventStartingErrorEvent</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANEVENT_STARTINGERROREVENT (150, "Key used to define the event <code>FlightPlanEventStartingErrorEvent</code> in project <code>Common</code>"),
    /** Key used to define the event <code>FlightPlanEventSpeedBridleEvent</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANEVENT_SPEEDBRIDLEEVENT (151, "Key used to define the event <code>FlightPlanEventSpeedBridleEvent</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ARLibsVersionsStateControllerLibARCommandsVersion</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ARLIBSVERSIONSSTATE_CONTROLLERLIBARCOMMANDSVERSION (152, "Key used to define the event <code>ARLibsVersionsStateControllerLibARCommandsVersion</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ARLibsVersionsStateSkyControllerLibARCommandsVersion</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ARLIBSVERSIONSSTATE_SKYCONTROLLERLIBARCOMMANDSVERSION (153, "Key used to define the event <code>ARLibsVersionsStateSkyControllerLibARCommandsVersion</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ARLibsVersionsStateDeviceLibARCommandsVersion</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ARLIBSVERSIONSSTATE_DEVICELIBARCOMMANDSVERSION (154, "Key used to define the event <code>ARLibsVersionsStateDeviceLibARCommandsVersion</code> in project <code>Common</code>"),
    /** Key used to define the event <code>AudioStateAudioStreamingRunning</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_AUDIOSTATE_AUDIOSTREAMINGRUNNING (155, "Key used to define the event <code>AudioStateAudioStreamingRunning</code> in project <code>Common</code>"),
    /** Key used to define the event <code>HeadlightsStateIntensityChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_HEADLIGHTSSTATE_INTENSITYCHANGED (156, "Key used to define the event <code>HeadlightsStateIntensityChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>AnimationsStateList</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ANIMATIONSSTATE_LIST (157, "Key used to define the event <code>AnimationsStateList</code> in project <code>Common</code>"),
    /** Key used to define the event <code>AccessoryStateSupportedAccessoriesListChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ACCESSORYSTATE_SUPPORTEDACCESSORIESLISTCHANGED (158, "Key used to define the event <code>AccessoryStateSupportedAccessoriesListChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>AccessoryStateAccessoryConfigChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ACCESSORYSTATE_ACCESSORYCONFIGCHANGED (159, "Key used to define the event <code>AccessoryStateAccessoryConfigChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>AccessoryStateAccessoryConfigModificationEnabled</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_ACCESSORYSTATE_ACCESSORYCONFIGMODIFICATIONENABLED (160, "Key used to define the event <code>AccessoryStateAccessoryConfigModificationEnabled</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ChargerStateMaxChargeRateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CHARGERSTATE_MAXCHARGERATECHANGED (161, "Key used to define the event <code>ChargerStateMaxChargeRateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ChargerStateCurrentChargeStateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CHARGERSTATE_CURRENTCHARGESTATECHANGED (162, "Key used to define the event <code>ChargerStateCurrentChargeStateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ChargerStateLastChargeRateChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CHARGERSTATE_LASTCHARGERATECHANGED (163, "Key used to define the event <code>ChargerStateLastChargeRateChanged</code> in project <code>Common</code>"),
    /** Key used to define the event <code>ChargerStateChargingInfo</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_CHARGERSTATE_CHARGINGINFO (164, "Key used to define the event <code>ChargerStateChargingInfo</code> in project <code>Common</code>"),
    /** Key used to define the event <code>RunStateRunIdChanged</code> in project <code>Common</code>*/
    ARCONTROLLER_DICTIONARY_KEY_COMMON_RUNSTATE_RUNIDCHANGED (165, "Key used to define the event <code>RunStateRunIdChanged</code> in project <code>Common</code>"),
    /** Key used to define the feature <code>ControllerInfo</code> */
    ARCONTROLLER_DICTIONARY_KEY_CONTROLLER_INFO (166, "Key used to define the feature <code>ControllerInfo</code>"),
    /** Key used to define the feature <code>Debug</code> */
    ARCONTROLLER_DICTIONARY_KEY_DEBUG (167, "Key used to define the feature <code>Debug</code>"),
    /** Key used to define the event <code>SettingsInfo</code> in project <code>Debug</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DEBUG_SETTINGSINFO (168, "Key used to define the event <code>SettingsInfo</code> in project <code>Debug</code>"),
    /** Key used to define the event <code>SettingsList</code> in project <code>Debug</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DEBUG_SETTINGSLIST (169, "Key used to define the event <code>SettingsList</code> in project <code>Debug</code>"),
    /** Key used to define the feature <code>DroneManager</code> */
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER (170, "Key used to define the feature <code>DroneManager</code>"),
    /** Key used to define the event <code>DroneListItem</code> in project <code>DroneManager</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER_DRONELISTITEM (171, "Key used to define the event <code>DroneListItem</code> in project <code>DroneManager</code>"),
    /** Key used to define the event <code>ConnectionState</code> in project <code>DroneManager</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER_CONNECTIONSTATE (172, "Key used to define the event <code>ConnectionState</code> in project <code>DroneManager</code>"),
    /** Key used to define the event <code>AuthenticationFailed</code> in project <code>DroneManager</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER_AUTHENTICATIONFAILED (173, "Key used to define the event <code>AuthenticationFailed</code> in project <code>DroneManager</code>"),
    /** Key used to define the event <code>ConnectionRefused</code> in project <code>DroneManager</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER_CONNECTIONREFUSED (174, "Key used to define the event <code>ConnectionRefused</code> in project <code>DroneManager</code>"),
    /** Key used to define the event <code>KnownDroneItem</code> in project <code>DroneManager</code>*/
    ARCONTROLLER_DICTIONARY_KEY_DRONE_MANAGER_KNOWNDRONEITEM (175, "Key used to define the event <code>KnownDroneItem</code> in project <code>DroneManager</code>"),
    /** Key used to define the feature <code>FollowMe</code> */
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME (176, "Key used to define the feature <code>FollowMe</code>"),
    /** Key used to define the event <code>State</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_STATE (177, "Key used to define the event <code>State</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>ModeInfo</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_MODEINFO (178, "Key used to define the event <code>ModeInfo</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>GeographicConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_GEOGRAPHICCONFIG (179, "Key used to define the event <code>GeographicConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>RelativeConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_RELATIVECONFIG (180, "Key used to define the event <code>RelativeConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>TargetTrajectory</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_TARGETTRAJECTORY (181, "Key used to define the event <code>TargetTrajectory</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>HelicoidAnimConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_HELICOIDANIMCONFIG (182, "Key used to define the event <code>HelicoidAnimConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>SwingAnimConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_SWINGANIMCONFIG (183, "Key used to define the event <code>SwingAnimConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>BoomerangAnimConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_BOOMERANGANIMCONFIG (184, "Key used to define the event <code>BoomerangAnimConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>CandleAnimConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_CANDLEANIMCONFIG (185, "Key used to define the event <code>CandleAnimConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>DollySlideAnimConfig</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_DOLLYSLIDEANIMCONFIG (186, "Key used to define the event <code>DollySlideAnimConfig</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>TargetFramingPositionChanged</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_TARGETFRAMINGPOSITIONCHANGED (187, "Key used to define the event <code>TargetFramingPositionChanged</code> in project <code>FollowMe</code>"),
    /** Key used to define the event <code>TargetImageDetectionState</code> in project <code>FollowMe</code>*/
    ARCONTROLLER_DICTIONARY_KEY_FOLLOW_ME_TARGETIMAGEDETECTIONSTATE (188, "Key used to define the event <code>TargetImageDetectionState</code> in project <code>FollowMe</code>"),
    /** Key used to define the feature <code>JumpingSumo</code> */
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO (189, "Key used to define the feature <code>JumpingSumo</code>"),
    /** Key used to define the event <code>PilotingStatePostureChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_PILOTINGSTATE_POSTURECHANGED (190, "Key used to define the event <code>PilotingStatePostureChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_PILOTINGSTATE_ALERTSTATECHANGED (191, "Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>PilotingStateSpeedChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_PILOTINGSTATE_SPEEDCHANGED (192, "Key used to define the event <code>PilotingStateSpeedChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>AnimationsStateJumpLoadChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ANIMATIONSSTATE_JUMPLOADCHANGED (193, "Key used to define the event <code>AnimationsStateJumpLoadChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>AnimationsStateJumpTypeChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ANIMATIONSSTATE_JUMPTYPECHANGED (194, "Key used to define the event <code>AnimationsStateJumpTypeChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>AnimationsStateJumpMotorProblemChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ANIMATIONSSTATE_JUMPMOTORPROBLEMCHANGED (195, "Key used to define the event <code>AnimationsStateJumpMotorProblemChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>SettingsStateProductGPSVersionChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_SETTINGSSTATE_PRODUCTGPSVERSIONCHANGED (196, "Key used to define the event <code>SettingsStateProductGPSVersionChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDSTATE_PICTURESTATECHANGED (197, "Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoStateChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDSTATE_VIDEOSTATECHANGED (198, "Key used to define the event <code>MediaRecordStateVideoStateChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDSTATE_PICTURESTATECHANGEDV2 (199, "Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2 (200, "Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDEVENT_PICTUREEVENTCHANGED (201, "Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIARECORDEVENT_VIDEOEVENTCHANGED (202, "Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED (203, "Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSTATE_WIFISCANLISTCHANGED (204, "Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSTATE_ALLWIFISCANCHANGED (205, "Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED (206, "Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSTATE_ALLWIFIAUTHCHANNELCHANGED (207, "Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>NetworkStateLinkQualityChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_NETWORKSTATE_LINKQUALITYCHANGED (208, "Key used to define the event <code>NetworkStateLinkQualityChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>AudioSettingsStateMasterVolumeChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_AUDIOSETTINGSSTATE_MASTERVOLUMECHANGED (209, "Key used to define the event <code>AudioSettingsStateMasterVolumeChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>AudioSettingsStateThemeChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_AUDIOSETTINGSSTATE_THEMECHANGED (210, "Key used to define the event <code>AudioSettingsStateThemeChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>RoadPlanStateScriptMetadataListChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ROADPLANSTATE_SCRIPTMETADATALISTCHANGED (211, "Key used to define the event <code>RoadPlanStateScriptMetadataListChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>RoadPlanStateAllScriptsMetadataChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ROADPLANSTATE_ALLSCRIPTSMETADATACHANGED (212, "Key used to define the event <code>RoadPlanStateAllScriptsMetadataChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>RoadPlanStateScriptUploadChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ROADPLANSTATE_SCRIPTUPLOADCHANGED (213, "Key used to define the event <code>RoadPlanStateScriptUploadChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>RoadPlanStateScriptDeleteChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ROADPLANSTATE_SCRIPTDELETECHANGED (214, "Key used to define the event <code>RoadPlanStateScriptDeleteChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>RoadPlanStatePlayScriptChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_ROADPLANSTATE_PLAYSCRIPTCHANGED (215, "Key used to define the event <code>RoadPlanStatePlayScriptChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>SpeedSettingsStateOutdoorChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_SPEEDSETTINGSSTATE_OUTDOORCHANGED (216, "Key used to define the event <code>SpeedSettingsStateOutdoorChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED (217, "Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>JumpingSumo</code>*/
    ARCONTROLLER_DICTIONARY_KEY_JUMPINGSUMO_VIDEOSETTINGSSTATE_AUTORECORDCHANGED (218, "Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>JumpingSumo</code>"),
    /** Key used to define the feature <code>Mapper</code> */
    ARCONTROLLER_DICTIONARY_KEY_MAPPER (219, "Key used to define the feature <code>Mapper</code>"),
    /** Key used to define the event <code>GrabState</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_GRABSTATE (220, "Key used to define the event <code>GrabState</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>GrabButtonEvent</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_GRABBUTTONEVENT (221, "Key used to define the event <code>GrabButtonEvent</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>GrabAxisEvent</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_GRABAXISEVENT (222, "Key used to define the event <code>GrabAxisEvent</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>ButtonMappingItem</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_BUTTONMAPPINGITEM (223, "Key used to define the event <code>ButtonMappingItem</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>AxisMappingItem</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_AXISMAPPINGITEM (224, "Key used to define the event <code>AxisMappingItem</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>ApplicationAxisEvent</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_APPLICATIONAXISEVENT (225, "Key used to define the event <code>ApplicationAxisEvent</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>ApplicationButtonEvent</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_APPLICATIONBUTTONEVENT (226, "Key used to define the event <code>ApplicationButtonEvent</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>ExpoMapItem</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_EXPOMAPITEM (227, "Key used to define the event <code>ExpoMapItem</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>InvertedMapItem</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_INVERTEDMAPITEM (228, "Key used to define the event <code>InvertedMapItem</code> in project <code>Mapper</code>"),
    /** Key used to define the event <code>ActiveProduct</code> in project <code>Mapper</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_ACTIVEPRODUCT (229, "Key used to define the event <code>ActiveProduct</code> in project <code>Mapper</code>"),
    /** Key used to define the feature <code>MapperMini</code> */
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_MINI (230, "Key used to define the feature <code>MapperMini</code>"),
    /** Key used to define the event <code>ButtonMappingItem</code> in project <code>MapperMini</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_MINI_BUTTONMAPPINGITEM (231, "Key used to define the event <code>ButtonMappingItem</code> in project <code>MapperMini</code>"),
    /** Key used to define the event <code>AxisMappingItem</code> in project <code>MapperMini</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MAPPER_MINI_AXISMAPPINGITEM (232, "Key used to define the event <code>AxisMappingItem</code> in project <code>MapperMini</code>"),
    /** Key used to define the feature <code>MiniDrone</code> */
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE (233, "Key used to define the feature <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStateFlatTrimChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_FLATTRIMCHANGED (234, "Key used to define the event <code>PilotingStateFlatTrimChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED (235, "Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_ALERTSTATECHANGED (236, "Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStateAutoTakeOffModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_AUTOTAKEOFFMODECHANGED (237, "Key used to define the event <code>PilotingStateAutoTakeOffModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStateFlyingModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_FLYINGMODECHANGED (238, "Key used to define the event <code>PilotingStateFlyingModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStatePlaneGearBoxChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_PLANEGEARBOXCHANGED (239, "Key used to define the event <code>PilotingStatePlaneGearBoxChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingStatePilotingModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSTATE_PILOTINGMODECHANGED (240, "Key used to define the event <code>PilotingStatePilotingModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MEDIARECORDSTATE_PICTURESTATECHANGED (241, "Key used to define the event <code>MediaRecordStatePictureStateChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MEDIARECORDSTATE_PICTURESTATECHANGEDV2 (242, "Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED (243, "Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxAltitudeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSETTINGSSTATE_MAXALTITUDECHANGED (244, "Key used to define the event <code>PilotingSettingsStateMaxAltitudeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxTiltChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSETTINGSSTATE_MAXTILTCHANGED (245, "Key used to define the event <code>PilotingSettingsStateMaxTiltChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingSettingsStateBankedTurnChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSETTINGSSTATE_BANKEDTURNCHANGED (246, "Key used to define the event <code>PilotingSettingsStateBankedTurnChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingSettingsStateMaxThrottleChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSETTINGSSTATE_MAXTHROTTLECHANGED (247, "Key used to define the event <code>PilotingSettingsStateMaxThrottleChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>PilotingSettingsStatePreferredPilotingModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_PILOTINGSETTINGSSTATE_PREFERREDPILOTINGMODECHANGED (248, "Key used to define the event <code>PilotingSettingsStatePreferredPilotingModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SPEEDSETTINGSSTATE_MAXVERTICALSPEEDCHANGED (249, "Key used to define the event <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SPEEDSETTINGSSTATE_MAXROTATIONSPEEDCHANGED (250, "Key used to define the event <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SpeedSettingsStateWheelsChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SPEEDSETTINGSSTATE_WHEELSCHANGED (251, "Key used to define the event <code>SpeedSettingsStateWheelsChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxHorizontalSpeedChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SPEEDSETTINGSSTATE_MAXHORIZONTALSPEEDCHANGED (252, "Key used to define the event <code>SpeedSettingsStateMaxHorizontalSpeedChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SpeedSettingsStateMaxPlaneModeRotationSpeedChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SPEEDSETTINGSSTATE_MAXPLANEMODEROTATIONSPEEDCHANGED (253, "Key used to define the event <code>SpeedSettingsStateMaxPlaneModeRotationSpeedChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SettingsStateProductMotorsVersionChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SETTINGSSTATE_PRODUCTMOTORSVERSIONCHANGED (254, "Key used to define the event <code>SettingsStateProductMotorsVersionChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SettingsStateProductInertialVersionChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SETTINGSSTATE_PRODUCTINERTIALVERSIONCHANGED (255, "Key used to define the event <code>SettingsStateProductInertialVersionChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>SettingsStateCutOutModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_SETTINGSSTATE_CUTOUTMODECHANGED (256, "Key used to define the event <code>SettingsStateCutOutModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>FloodControlStateFloodControlChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_FLOODCONTROLSTATE_FLOODCONTROLCHANGED (257, "Key used to define the event <code>FloodControlStateFloodControlChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>UsbAccessoryStateLightState</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_USBACCESSORYSTATE_LIGHTSTATE (258, "Key used to define the event <code>UsbAccessoryStateLightState</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>UsbAccessoryStateClawState</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_USBACCESSORYSTATE_CLAWSTATE (259, "Key used to define the event <code>UsbAccessoryStateClawState</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>UsbAccessoryStateGunState</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_USBACCESSORYSTATE_GUNSTATE (260, "Key used to define the event <code>UsbAccessoryStateGunState</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>NavigationDataStateDronePosition</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_NAVIGATIONDATASTATE_DRONEPOSITION (261, "Key used to define the event <code>NavigationDataStateDronePosition</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>NavigationDataStateDroneSpeed</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_NAVIGATIONDATASTATE_DRONESPEED (262, "Key used to define the event <code>NavigationDataStateDroneSpeed</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>NavigationDataStateDroneAltitude</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_NAVIGATIONDATASTATE_DRONEALTITUDE (263, "Key used to define the event <code>NavigationDataStateDroneAltitude</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>NavigationDataStateDroneQuaternion</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_NAVIGATIONDATASTATE_DRONEQUATERNION (264, "Key used to define the event <code>NavigationDataStateDroneQuaternion</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStatePowerModeChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_POWERMODECHANGED (265, "Key used to define the event <code>MinicamStatePowerModeChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStateProductSerialChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_PRODUCTSERIALCHANGED (266, "Key used to define the event <code>MinicamStateProductSerialChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStateStateChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_STATECHANGED (267, "Key used to define the event <code>MinicamStateStateChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStateVersionChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_VERSIONCHANGED (268, "Key used to define the event <code>MinicamStateVersionChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStatePictureChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_PICTURECHANGED (269, "Key used to define the event <code>MinicamStatePictureChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStateVideoStateChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_VIDEOSTATECHANGED (270, "Key used to define the event <code>MinicamStateVideoStateChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>MinicamStateMassStorageFormatChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_MINICAMSTATE_MASSSTORAGEFORMATCHANGED (271, "Key used to define the event <code>MinicamStateMassStorageFormatChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_VIDEOSETTINGSSTATE_AUTORECORDCHANGED (272, "Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>VideoSettingsStateElectricFrequencyChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_VIDEOSETTINGSSTATE_ELECTRICFREQUENCYCHANGED (273, "Key used to define the event <code>VideoSettingsStateElectricFrequencyChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>VideoSettingsStateVideoResolutionChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_VIDEOSETTINGSSTATE_VIDEORESOLUTIONCHANGED (274, "Key used to define the event <code>VideoSettingsStateVideoResolutionChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the event <code>RemoteControllerStateConnectionChanged</code> in project <code>MiniDrone</code>*/
    ARCONTROLLER_DICTIONARY_KEY_MINIDRONE_REMOTECONTROLLERSTATE_CONNECTIONCHANGED (275, "Key used to define the event <code>RemoteControllerStateConnectionChanged</code> in project <code>MiniDrone</code>"),
    /** Key used to define the feature <code>Powerup</code> */
    ARCONTROLLER_DICTIONARY_KEY_POWERUP (276, "Key used to define the feature <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSTATE_ALERTSTATECHANGED (277, "Key used to define the event <code>PilotingStateAlertStateChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSTATE_FLYINGSTATECHANGED (278, "Key used to define the event <code>PilotingStateFlyingStateChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingStateMotorModeChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSTATE_MOTORMODECHANGED (279, "Key used to define the event <code>PilotingStateMotorModeChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingStateAttitudeChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSTATE_ATTITUDECHANGED (280, "Key used to define the event <code>PilotingStateAttitudeChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingStateAltitudeChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSTATE_ALTITUDECHANGED (281, "Key used to define the event <code>PilotingStateAltitudeChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>PilotingSettingsStateSettingChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_PILOTINGSETTINGSSTATE_SETTINGCHANGED (282, "Key used to define the event <code>PilotingSettingsStateSettingChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_MEDIARECORDSTATE_PICTURESTATECHANGEDV2 (283, "Key used to define the event <code>MediaRecordStatePictureStateChangedV2</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2 (284, "Key used to define the event <code>MediaRecordStateVideoStateChangedV2</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_MEDIARECORDEVENT_PICTUREEVENTCHANGED (285, "Key used to define the event <code>MediaRecordEventPictureEventChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_MEDIARECORDEVENT_VIDEOEVENTCHANGED (286, "Key used to define the event <code>MediaRecordEventVideoEventChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED (287, "Key used to define the event <code>NetworkSettingsStateWifiSelectionChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSTATE_WIFISCANLISTCHANGED (288, "Key used to define the event <code>NetworkStateWifiScanListChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSTATE_ALLWIFISCANCHANGED (289, "Key used to define the event <code>NetworkStateAllWifiScanChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED (290, "Key used to define the event <code>NetworkStateWifiAuthChannelListChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSTATE_ALLWIFIAUTHCHANNELCHANGED (291, "Key used to define the event <code>NetworkStateAllWifiAuthChannelChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>NetworkStateLinkQualityChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_NETWORKSTATE_LINKQUALITYCHANGED (292, "Key used to define the event <code>NetworkStateLinkQualityChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED (293, "Key used to define the event <code>MediaStreamingStateVideoEnableChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_VIDEOSETTINGSSTATE_AUTORECORDCHANGED (294, "Key used to define the event <code>VideoSettingsStateAutorecordChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>VideoSettingsStateVideoModeChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_VIDEOSETTINGSSTATE_VIDEOMODECHANGED (295, "Key used to define the event <code>VideoSettingsStateVideoModeChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the event <code>SoundsStateBuzzChanged</code> in project <code>Powerup</code>*/
    ARCONTROLLER_DICTIONARY_KEY_POWERUP_SOUNDSSTATE_BUZZCHANGED (296, "Key used to define the event <code>SoundsStateBuzzChanged</code> in project <code>Powerup</code>"),
    /** Key used to define the feature <code>Rc</code> */
    ARCONTROLLER_DICTIONARY_KEY_RC (297, "Key used to define the feature <code>Rc</code>"),
    /** Key used to define the event <code>ReceiverState</code> in project <code>Rc</code>*/
    ARCONTROLLER_DICTIONARY_KEY_RC_RECEIVERSTATE (298, "Key used to define the event <code>ReceiverState</code> in project <code>Rc</code>"),
    /** Key used to define the event <code>ChannelsMonitorState</code> in project <code>Rc</code>*/
    ARCONTROLLER_DICTIONARY_KEY_RC_CHANNELSMONITORSTATE (299, "Key used to define the event <code>ChannelsMonitorState</code> in project <code>Rc</code>"),
    /** Key used to define the event <code>ChannelValue</code> in project <code>Rc</code>*/
    ARCONTROLLER_DICTIONARY_KEY_RC_CHANNELVALUE (300, "Key used to define the event <code>ChannelValue</code> in project <code>Rc</code>"),
    /** Key used to define the event <code>CalibrationState</code> in project <code>Rc</code>*/
    ARCONTROLLER_DICTIONARY_KEY_RC_CALIBRATIONSTATE (301, "Key used to define the event <code>CalibrationState</code> in project <code>Rc</code>"),
    /** Key used to define the event <code>ChannelActionItem</code> in project <code>Rc</code>*/
    ARCONTROLLER_DICTIONARY_KEY_RC_CHANNELACTIONITEM (302, "Key used to define the event <code>ChannelActionItem</code> in project <code>Rc</code>"),
    /** Key used to define the feature <code>SequoiaCam</code> */
    ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM (303, "Key used to define the feature <code>SequoiaCam</code>"),
    /** Key used to define the event <code>RadiometricCalibStatus</code> in project <code>SequoiaCam</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBSTATUS (304, "Key used to define the event <code>RadiometricCalibStatus</code> in project <code>SequoiaCam</code>"),
    /** Key used to define the event <code>RadiometricCalibResult</code> in project <code>SequoiaCam</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT (305, "Key used to define the event <code>RadiometricCalibResult</code> in project <code>SequoiaCam</code>"),
    /** Key used to define the feature <code>SkyController</code> */
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER (306, "Key used to define the feature <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiList</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFILIST (307, "Key used to define the event <code>WifiStateWifiList</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateConnexionChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_CONNEXIONCHANGED (308, "Key used to define the event <code>WifiStateConnexionChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiAuthChannelListChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFIAUTHCHANNELLISTCHANGED (309, "Key used to define the event <code>WifiStateWifiAuthChannelListChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateAllWifiAuthChannelChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_ALLWIFIAUTHCHANNELCHANGED (310, "Key used to define the event <code>WifiStateAllWifiAuthChannelChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiSignalChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFISIGNALCHANGED (311, "Key used to define the event <code>WifiStateWifiSignalChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiAuthChannelListChangedV2</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFIAUTHCHANNELLISTCHANGEDV2 (312, "Key used to define the event <code>WifiStateWifiAuthChannelListChangedV2</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiCountryChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFICOUNTRYCHANGED (313, "Key used to define the event <code>WifiStateWifiCountryChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>WifiStateWifiEnvironmentChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFIENVIRONMENTCHANGED (314, "Key used to define the event <code>WifiStateWifiEnvironmentChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>DeviceStateDeviceList</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_DEVICESTATE_DEVICELIST (315, "Key used to define the event <code>DeviceStateDeviceList</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>DeviceStateConnexionChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_DEVICESTATE_CONNEXIONCHANGED (316, "Key used to define the event <code>DeviceStateConnexionChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateAllSettingsChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_ALLSETTINGSCHANGED (317, "Key used to define the event <code>SettingsStateAllSettingsChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateResetChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_RESETCHANGED (318, "Key used to define the event <code>SettingsStateResetChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateProductSerialChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_PRODUCTSERIALCHANGED (319, "Key used to define the event <code>SettingsStateProductSerialChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateProductVariantChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_PRODUCTVARIANTCHANGED (320, "Key used to define the event <code>SettingsStateProductVariantChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateProductVersionChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_PRODUCTVERSIONCHANGED (321, "Key used to define the event <code>SettingsStateProductVersionChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SettingsStateCPUID</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SETTINGSSTATE_CPUID (322, "Key used to define the event <code>SettingsStateCPUID</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CommonStateAllStatesChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_COMMONSTATE_ALLSTATESCHANGED (323, "Key used to define the event <code>CommonStateAllStatesChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SkyControllerStateBatteryChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYCHANGED (324, "Key used to define the event <code>SkyControllerStateBatteryChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SkyControllerStateGpsFixChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_GPSFIXCHANGED (325, "Key used to define the event <code>SkyControllerStateGpsFixChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SkyControllerStateGpsPositionChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_GPSPOSITIONCHANGED (326, "Key used to define the event <code>SkyControllerStateGpsPositionChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SkyControllerStateBatteryState</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYSTATE (327, "Key used to define the event <code>SkyControllerStateBatteryState</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>SkyControllerStateAttitudeChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_ATTITUDECHANGED (328, "Key used to define the event <code>SkyControllerStateAttitudeChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AccessPointSettingsStateAccessPointSSIDChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_ACCESSPOINTSSIDCHANGED (329, "Key used to define the event <code>AccessPointSettingsStateAccessPointSSIDChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AccessPointSettingsStateAccessPointChannelChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_ACCESSPOINTCHANNELCHANGED (330, "Key used to define the event <code>AccessPointSettingsStateAccessPointChannelChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AccessPointSettingsStateWifiSelectionChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_WIFISELECTIONCHANGED (331, "Key used to define the event <code>AccessPointSettingsStateWifiSelectionChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AccessPointSettingsStateWifiSecurityChanged</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_WIFISECURITYCHANGED (332, "Key used to define the event <code>AccessPointSettingsStateWifiSecurityChanged</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>GamepadInfosStateGamepadControl</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_GAMEPADINFOSSTATE_GAMEPADCONTROL (333, "Key used to define the event <code>GamepadInfosStateGamepadControl</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>GamepadInfosStateAllGamepadControlsSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_GAMEPADINFOSSTATE_ALLGAMEPADCONTROLSSENT (334, "Key used to define the event <code>GamepadInfosStateAllGamepadControlsSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>ButtonMappingsStateCurrentButtonMappings</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_BUTTONMAPPINGSSTATE_CURRENTBUTTONMAPPINGS (335, "Key used to define the event <code>ButtonMappingsStateCurrentButtonMappings</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>ButtonMappingsStateAllCurrentButtonMappingsSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_BUTTONMAPPINGSSTATE_ALLCURRENTBUTTONMAPPINGSSENT (336, "Key used to define the event <code>ButtonMappingsStateAllCurrentButtonMappingsSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>ButtonMappingsStateAvailableButtonMappings</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_BUTTONMAPPINGSSTATE_AVAILABLEBUTTONMAPPINGS (337, "Key used to define the event <code>ButtonMappingsStateAvailableButtonMappings</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>ButtonMappingsStateAllAvailableButtonsMappingsSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_BUTTONMAPPINGSSTATE_ALLAVAILABLEBUTTONSMAPPINGSSENT (338, "Key used to define the event <code>ButtonMappingsStateAllAvailableButtonsMappingsSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisMappingsStateCurrentAxisMappings</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISMAPPINGSSTATE_CURRENTAXISMAPPINGS (339, "Key used to define the event <code>AxisMappingsStateCurrentAxisMappings</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisMappingsStateAllCurrentAxisMappingsSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISMAPPINGSSTATE_ALLCURRENTAXISMAPPINGSSENT (340, "Key used to define the event <code>AxisMappingsStateAllCurrentAxisMappingsSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisMappingsStateAvailableAxisMappings</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISMAPPINGSSTATE_AVAILABLEAXISMAPPINGS (341, "Key used to define the event <code>AxisMappingsStateAvailableAxisMappings</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisMappingsStateAllAvailableAxisMappingsSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISMAPPINGSSTATE_ALLAVAILABLEAXISMAPPINGSSENT (342, "Key used to define the event <code>AxisMappingsStateAllAvailableAxisMappingsSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisFiltersStateCurrentAxisFilters</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISFILTERSSTATE_CURRENTAXISFILTERS (343, "Key used to define the event <code>AxisFiltersStateCurrentAxisFilters</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisFiltersStateAllCurrentFiltersSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISFILTERSSTATE_ALLCURRENTFILTERSSENT (344, "Key used to define the event <code>AxisFiltersStateAllCurrentFiltersSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisFiltersStatePresetAxisFilters</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISFILTERSSTATE_PRESETAXISFILTERS (345, "Key used to define the event <code>AxisFiltersStatePresetAxisFilters</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>AxisFiltersStateAllPresetFiltersSent</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_AXISFILTERSSTATE_ALLPRESETFILTERSSENT (346, "Key used to define the event <code>AxisFiltersStateAllPresetFiltersSent</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CoPilotingStatePilotingSource</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_COPILOTINGSTATE_PILOTINGSOURCE (347, "Key used to define the event <code>CoPilotingStatePilotingSource</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationState</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTATE (348, "Key used to define the event <code>CalibrationStateMagnetoCalibrationState</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationQualityUpdatesState</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_CALIBRATIONSTATE_MAGNETOCALIBRATIONQUALITYUPDATESSTATE (349, "Key used to define the event <code>CalibrationStateMagnetoCalibrationQualityUpdatesState</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CalibrationStateMagnetoCalibrationStateV2</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTATEV2 (350, "Key used to define the event <code>CalibrationStateMagnetoCalibrationStateV2</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>ButtonEventsSettings</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_BUTTONEVENTS_SETTINGS (351, "Key used to define the event <code>ButtonEventsSettings</code> in project <code>SkyController</code>"),
    /** Key used to define the event <code>CommonEventStateShutdown</code> in project <code>SkyController</code>*/
    ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_COMMONEVENTSTATE_SHUTDOWN (352, "Key used to define the event <code>CommonEventStateShutdown</code> in project <code>SkyController</code>"),
    /** Key used to define the feature <code>ThermalCam</code> */
    ARCONTROLLER_DICTIONARY_KEY_THERMAL_CAM (353, "Key used to define the feature <code>ThermalCam</code>"),
    /** Key used to define the event <code>CameraState</code> in project <code>ThermalCam</code>*/
    ARCONTROLLER_DICTIONARY_KEY_THERMAL_CAM_CAMERASTATE (354, "Key used to define the event <code>CameraState</code> in project <code>ThermalCam</code>"),
    /** Key used to define the event <code>Sensitivity</code> in project <code>ThermalCam</code>*/
    ARCONTROLLER_DICTIONARY_KEY_THERMAL_CAM_SENSITIVITY (355, "Key used to define the event <code>Sensitivity</code> in project <code>ThermalCam</code>"),
    /** Key used to define the event <code>CalibrationInfos</code> in project <code>ThermalCam</code>*/
    ARCONTROLLER_DICTIONARY_KEY_THERMAL_CAM_CALIBRATIONINFOS (356, "Key used to define the event <code>CalibrationInfos</code> in project <code>ThermalCam</code>"),
    /** Key used to define the feature <code>Wifi</code> */
    ARCONTROLLER_DICTIONARY_KEY_WIFI (357, "Key used to define the feature <code>Wifi</code>"),
    /** Key used to define the event <code>ScannedItem</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_SCANNEDITEM (358, "Key used to define the event <code>ScannedItem</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>AuthorizedChannel</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_AUTHORIZEDCHANNEL (359, "Key used to define the event <code>AuthorizedChannel</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>ApChannelChanged</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_APCHANNELCHANGED (360, "Key used to define the event <code>ApChannelChanged</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>SecurityChanged</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_SECURITYCHANGED (361, "Key used to define the event <code>SecurityChanged</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>CountryChanged</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_COUNTRYCHANGED (362, "Key used to define the event <code>CountryChanged</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>EnvironmentChanged</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_ENVIRONMENTCHANGED (363, "Key used to define the event <code>EnvironmentChanged</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>RssiChanged</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_RSSICHANGED (364, "Key used to define the event <code>RssiChanged</code> in project <code>Wifi</code>"),
    /** Key used to define the event <code>SupportedCountries</code> in project <code>Wifi</code>*/
    ARCONTROLLER_DICTIONARY_KEY_WIFI_SUPPORTEDCOUNTRIES (365, "Key used to define the event <code>SupportedCountries</code> in project <code>Wifi</code>"),
    /** Unused, iterator maximum value */
    ARCONTROLLER_DICTIONARY_DICTIONARY_KEY_MAX (366, "Unused, iterator maximum value");

    private final int value;
    private final String comment;
    static HashMap<Integer, ARCONTROLLER_DICTIONARY_KEY_ENUM> valuesList;

    ARCONTROLLER_DICTIONARY_KEY_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCONTROLLER_DICTIONARY_KEY_ENUM (int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * Gets the int value of the enum
     * @return int value of the enum
     */
    public int getValue () {
        return value;
    }

    /**
     * Gets the ARCONTROLLER_DICTIONARY_KEY_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCONTROLLER_DICTIONARY_KEY_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCONTROLLER_DICTIONARY_KEY_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCONTROLLER_DICTIONARY_KEY_ENUM [] valuesArray = ARCONTROLLER_DICTIONARY_KEY_ENUM.values ();
            valuesList = new HashMap<Integer, ARCONTROLLER_DICTIONARY_KEY_ENUM> (valuesArray.length);
            for (ARCONTROLLER_DICTIONARY_KEY_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCONTROLLER_DICTIONARY_KEY_ENUM retVal = valuesList.get (value);
        if (retVal == null) {
            retVal = eARCONTROLLER_DICTIONARY_KEY_UNKNOWN_ENUM_VALUE;
        }
        return retVal;    }

    /**
     * Returns the enum comment as a description string
     * @return The enum description
     */
    public String toString () {
        if (this.comment != null) {
            return this.comment;
        }
        return super.toString ();
    }
}
