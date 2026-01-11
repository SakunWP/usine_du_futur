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

package com.parrot.arsdk.arcommands;

/**
 * Java copy of the ARCommandsGenericListFlags
 */
public class ARCommandsGenericDroneSettings {

    private static class Ardrone3MaxAltitude {
        public int isSet;
        public float current;
    }

    private static class Ardrone3MaxTilt {
        public int isSet;
        public float current;
    }

    private static class Ardrone3MaxDistance {
        public int isSet;
        public float value;
    }

    private static class Ardrone3NoFlyOverMaxDistance {
        public int isSet;
        public byte shouldNotFlyOver;
    }

    private static class Ardrone3MaxVerticalSpeed {
        public int isSet;
        public float current;
    }

    private static class Ardrone3MaxRotationSpeed {
        public int isSet;
        public float current;
    }

    private static class Ardrone3MaxPitchRollRotationSpeed {
        public int isSet;
        public float current;
    }

    private static class Ardrone3ReturnHomeDelay {
        public int isSet;
        public short delay;
    }

    private static class Ardrone3HomeType {
        public int isSet;
        public ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM type;
    }

    private static class Ardrone3VideoStabilizationMode {
        public int isSet;
        public ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEOSTABILIZATIONMODE_MODE_ENUM mode;
    }

    private static class Ardrone3BankedTurn {
        public int isSet;
        public byte value;
    }

    public ARCommandsGenericDroneSettings () {
    }

    private final Ardrone3MaxAltitude _ardrone3MaxAltitude = new Ardrone3MaxAltitude();
    private final Ardrone3MaxTilt _ardrone3MaxTilt = new Ardrone3MaxTilt();
    private final Ardrone3MaxDistance _ardrone3MaxDistance = new Ardrone3MaxDistance();
    private final Ardrone3NoFlyOverMaxDistance _ardrone3NoFlyOverMaxDistance = new Ardrone3NoFlyOverMaxDistance();
    private final Ardrone3MaxVerticalSpeed _ardrone3MaxVerticalSpeed = new Ardrone3MaxVerticalSpeed();
    private final Ardrone3MaxRotationSpeed _ardrone3MaxRotationSpeed = new Ardrone3MaxRotationSpeed();
    private final Ardrone3MaxPitchRollRotationSpeed _ardrone3MaxPitchRollRotationSpeed = new Ardrone3MaxPitchRollRotationSpeed();
    private final Ardrone3ReturnHomeDelay _ardrone3ReturnHomeDelay = new Ardrone3ReturnHomeDelay();
    private final Ardrone3HomeType _ardrone3HomeType = new Ardrone3HomeType();
    private final Ardrone3VideoStabilizationMode _ardrone3VideoStabilizationMode = new Ardrone3VideoStabilizationMode();
    private final Ardrone3BankedTurn _ardrone3BankedTurn = new Ardrone3BankedTurn();

    public void setArdrone3MaxAltitude (float current) {
        _ardrone3MaxAltitude.isSet = 1;
        _ardrone3MaxAltitude.current = current;
    }

    public void setArdrone3MaxTilt (float current) {
        _ardrone3MaxTilt.isSet = 1;
        _ardrone3MaxTilt.current = current;
    }

    public void setArdrone3MaxDistance (float value) {
        _ardrone3MaxDistance.isSet = 1;
        _ardrone3MaxDistance.value = value;
    }

    public void setArdrone3NoFlyOverMaxDistance (byte shouldNotFlyOver) {
        _ardrone3NoFlyOverMaxDistance.isSet = 1;
        _ardrone3NoFlyOverMaxDistance.shouldNotFlyOver = shouldNotFlyOver;
    }

    public void setArdrone3MaxVerticalSpeed (float current) {
        _ardrone3MaxVerticalSpeed.isSet = 1;
        _ardrone3MaxVerticalSpeed.current = current;
    }

    public void setArdrone3MaxRotationSpeed (float current) {
        _ardrone3MaxRotationSpeed.isSet = 1;
        _ardrone3MaxRotationSpeed.current = current;
    }

    public void setArdrone3MaxPitchRollRotationSpeed (float current) {
        _ardrone3MaxPitchRollRotationSpeed.isSet = 1;
        _ardrone3MaxPitchRollRotationSpeed.current = current;
    }

    public void setArdrone3ReturnHomeDelay (short delay) {
        _ardrone3ReturnHomeDelay.isSet = 1;
        _ardrone3ReturnHomeDelay.delay = delay;
    }

    public void setArdrone3HomeType (ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM type) {
        _ardrone3HomeType.isSet = 1;
        _ardrone3HomeType.type = type;
    }

    public void setArdrone3VideoStabilizationMode (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEOSTABILIZATIONMODE_MODE_ENUM mode) {
        _ardrone3VideoStabilizationMode.isSet = 1;
        _ardrone3VideoStabilizationMode.mode = mode;
    }

    public void setArdrone3BankedTurn (byte value) {
        _ardrone3BankedTurn.isSet = 1;
        _ardrone3BankedTurn.value = value;
    }

    public int getArdrone3MaxAltitudeIsSet () {
        return _ardrone3MaxAltitude.isSet;
    }

    public float getArdrone3MaxAltitudeCurrent () {
        return _ardrone3MaxAltitude.current;
    }

    public int getArdrone3MaxTiltIsSet () {
        return _ardrone3MaxTilt.isSet;
    }

    public float getArdrone3MaxTiltCurrent () {
        return _ardrone3MaxTilt.current;
    }

    public int getArdrone3MaxDistanceIsSet () {
        return _ardrone3MaxDistance.isSet;
    }

    public float getArdrone3MaxDistanceValue () {
        return _ardrone3MaxDistance.value;
    }

    public int getArdrone3NoFlyOverMaxDistanceIsSet () {
        return _ardrone3NoFlyOverMaxDistance.isSet;
    }

    public byte getArdrone3NoFlyOverMaxDistanceShouldNotFlyOver () {
        return _ardrone3NoFlyOverMaxDistance.shouldNotFlyOver;
    }

    public int getArdrone3MaxVerticalSpeedIsSet () {
        return _ardrone3MaxVerticalSpeed.isSet;
    }

    public float getArdrone3MaxVerticalSpeedCurrent () {
        return _ardrone3MaxVerticalSpeed.current;
    }

    public int getArdrone3MaxRotationSpeedIsSet () {
        return _ardrone3MaxRotationSpeed.isSet;
    }

    public float getArdrone3MaxRotationSpeedCurrent () {
        return _ardrone3MaxRotationSpeed.current;
    }

    public int getArdrone3MaxPitchRollRotationSpeedIsSet () {
        return _ardrone3MaxPitchRollRotationSpeed.isSet;
    }

    public float getArdrone3MaxPitchRollRotationSpeedCurrent () {
        return _ardrone3MaxPitchRollRotationSpeed.current;
    }

    public int getArdrone3ReturnHomeDelayIsSet () {
        return _ardrone3ReturnHomeDelay.isSet;
    }

    public short getArdrone3ReturnHomeDelayDelay () {
        return _ardrone3ReturnHomeDelay.delay;
    }

    public int getArdrone3HomeTypeIsSet () {
        return _ardrone3HomeType.isSet;
    }

    public ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM getArdrone3HomeTypeType () {
        return _ardrone3HomeType.type;
    }

    public int getArdrone3VideoStabilizationModeIsSet () {
        return _ardrone3VideoStabilizationMode.isSet;
    }

    public ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEOSTABILIZATIONMODE_MODE_ENUM getArdrone3VideoStabilizationModeMode () {
        return _ardrone3VideoStabilizationMode.mode;
    }

    public int getArdrone3BankedTurnIsSet () {
        return _ardrone3BankedTurn.isSet;
    }

    public byte getArdrone3BankedTurnValue () {
        return _ardrone3BankedTurn.value;
    }

}
