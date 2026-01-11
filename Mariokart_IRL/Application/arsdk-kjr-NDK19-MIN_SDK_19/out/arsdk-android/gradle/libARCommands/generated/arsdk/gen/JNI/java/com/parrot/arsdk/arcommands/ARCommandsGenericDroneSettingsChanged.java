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
public class ARCommandsGenericDroneSettingsChanged {

    private static class Ardrone3MaxAltitudeChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3MaxTiltChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3MaxDistanceChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3NoFlyOverMaxDistanceChanged {
        public int isSet;
        public byte shouldNotFlyOver;
    }

    private static class Ardrone3MaxVerticalSpeedChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3MaxRotationSpeedChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3MaxPitchRollRotationSpeedChanged {
        public int isSet;
        public float current;
        public float min;
        public float max;
    }

    private static class Ardrone3ReturnHomeDelayChanged {
        public int isSet;
        public short delay;
    }

    private static class Ardrone3HomeTypeChanged {
        public int isSet;
        public ARCOMMANDS_ARDRONE3_GPSSETTINGSSTATE_HOMETYPECHANGED_TYPE_ENUM type;
    }

    private static class Ardrone3VideoStabilizationModeChanged {
        public int isSet;
        public ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEOSTABILIZATIONMODECHANGED_MODE_ENUM mode;
    }

    private static class Ardrone3BankedTurnChanged {
        public int isSet;
        public byte state;
    }

    public ARCommandsGenericDroneSettingsChanged () {
    }

    private final Ardrone3MaxAltitudeChanged _ardrone3MaxAltitudeChanged = new Ardrone3MaxAltitudeChanged();
    private final Ardrone3MaxTiltChanged _ardrone3MaxTiltChanged = new Ardrone3MaxTiltChanged();
    private final Ardrone3MaxDistanceChanged _ardrone3MaxDistanceChanged = new Ardrone3MaxDistanceChanged();
    private final Ardrone3NoFlyOverMaxDistanceChanged _ardrone3NoFlyOverMaxDistanceChanged = new Ardrone3NoFlyOverMaxDistanceChanged();
    private final Ardrone3MaxVerticalSpeedChanged _ardrone3MaxVerticalSpeedChanged = new Ardrone3MaxVerticalSpeedChanged();
    private final Ardrone3MaxRotationSpeedChanged _ardrone3MaxRotationSpeedChanged = new Ardrone3MaxRotationSpeedChanged();
    private final Ardrone3MaxPitchRollRotationSpeedChanged _ardrone3MaxPitchRollRotationSpeedChanged = new Ardrone3MaxPitchRollRotationSpeedChanged();
    private final Ardrone3ReturnHomeDelayChanged _ardrone3ReturnHomeDelayChanged = new Ardrone3ReturnHomeDelayChanged();
    private final Ardrone3HomeTypeChanged _ardrone3HomeTypeChanged = new Ardrone3HomeTypeChanged();
    private final Ardrone3VideoStabilizationModeChanged _ardrone3VideoStabilizationModeChanged = new Ardrone3VideoStabilizationModeChanged();
    private final Ardrone3BankedTurnChanged _ardrone3BankedTurnChanged = new Ardrone3BankedTurnChanged();

    public void setArdrone3MaxAltitudeChanged (float current, float min, float max) {
        _ardrone3MaxAltitudeChanged.isSet = 1;
        _ardrone3MaxAltitudeChanged.current = current;
        _ardrone3MaxAltitudeChanged.min = min;
        _ardrone3MaxAltitudeChanged.max = max;
    }

    public void setArdrone3MaxTiltChanged (float current, float min, float max) {
        _ardrone3MaxTiltChanged.isSet = 1;
        _ardrone3MaxTiltChanged.current = current;
        _ardrone3MaxTiltChanged.min = min;
        _ardrone3MaxTiltChanged.max = max;
    }

    public void setArdrone3MaxDistanceChanged (float current, float min, float max) {
        _ardrone3MaxDistanceChanged.isSet = 1;
        _ardrone3MaxDistanceChanged.current = current;
        _ardrone3MaxDistanceChanged.min = min;
        _ardrone3MaxDistanceChanged.max = max;
    }

    public void setArdrone3NoFlyOverMaxDistanceChanged (byte shouldNotFlyOver) {
        _ardrone3NoFlyOverMaxDistanceChanged.isSet = 1;
        _ardrone3NoFlyOverMaxDistanceChanged.shouldNotFlyOver = shouldNotFlyOver;
    }

    public void setArdrone3MaxVerticalSpeedChanged (float current, float min, float max) {
        _ardrone3MaxVerticalSpeedChanged.isSet = 1;
        _ardrone3MaxVerticalSpeedChanged.current = current;
        _ardrone3MaxVerticalSpeedChanged.min = min;
        _ardrone3MaxVerticalSpeedChanged.max = max;
    }

    public void setArdrone3MaxRotationSpeedChanged (float current, float min, float max) {
        _ardrone3MaxRotationSpeedChanged.isSet = 1;
        _ardrone3MaxRotationSpeedChanged.current = current;
        _ardrone3MaxRotationSpeedChanged.min = min;
        _ardrone3MaxRotationSpeedChanged.max = max;
    }

    public void setArdrone3MaxPitchRollRotationSpeedChanged (float current, float min, float max) {
        _ardrone3MaxPitchRollRotationSpeedChanged.isSet = 1;
        _ardrone3MaxPitchRollRotationSpeedChanged.current = current;
        _ardrone3MaxPitchRollRotationSpeedChanged.min = min;
        _ardrone3MaxPitchRollRotationSpeedChanged.max = max;
    }

    public void setArdrone3ReturnHomeDelayChanged (short delay) {
        _ardrone3ReturnHomeDelayChanged.isSet = 1;
        _ardrone3ReturnHomeDelayChanged.delay = delay;
    }

    public void setArdrone3HomeTypeChanged (ARCOMMANDS_ARDRONE3_GPSSETTINGSSTATE_HOMETYPECHANGED_TYPE_ENUM type) {
        _ardrone3HomeTypeChanged.isSet = 1;
        _ardrone3HomeTypeChanged.type = type;
    }

    public void setArdrone3VideoStabilizationModeChanged (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEOSTABILIZATIONMODECHANGED_MODE_ENUM mode) {
        _ardrone3VideoStabilizationModeChanged.isSet = 1;
        _ardrone3VideoStabilizationModeChanged.mode = mode;
    }

    public void setArdrone3BankedTurnChanged (byte state) {
        _ardrone3BankedTurnChanged.isSet = 1;
        _ardrone3BankedTurnChanged.state = state;
    }

    public int getArdrone3MaxAltitudeChangedIsSet () {
        return _ardrone3MaxAltitudeChanged.isSet;
    }

    public float getArdrone3MaxAltitudeChangedCurrent () {
        return _ardrone3MaxAltitudeChanged.current;
    }

    public float getArdrone3MaxAltitudeChangedMin () {
        return _ardrone3MaxAltitudeChanged.min;
    }

    public float getArdrone3MaxAltitudeChangedMax () {
        return _ardrone3MaxAltitudeChanged.max;
    }

    public int getArdrone3MaxTiltChangedIsSet () {
        return _ardrone3MaxTiltChanged.isSet;
    }

    public float getArdrone3MaxTiltChangedCurrent () {
        return _ardrone3MaxTiltChanged.current;
    }

    public float getArdrone3MaxTiltChangedMin () {
        return _ardrone3MaxTiltChanged.min;
    }

    public float getArdrone3MaxTiltChangedMax () {
        return _ardrone3MaxTiltChanged.max;
    }

    public int getArdrone3MaxDistanceChangedIsSet () {
        return _ardrone3MaxDistanceChanged.isSet;
    }

    public float getArdrone3MaxDistanceChangedCurrent () {
        return _ardrone3MaxDistanceChanged.current;
    }

    public float getArdrone3MaxDistanceChangedMin () {
        return _ardrone3MaxDistanceChanged.min;
    }

    public float getArdrone3MaxDistanceChangedMax () {
        return _ardrone3MaxDistanceChanged.max;
    }

    public int getArdrone3NoFlyOverMaxDistanceChangedIsSet () {
        return _ardrone3NoFlyOverMaxDistanceChanged.isSet;
    }

    public byte getArdrone3NoFlyOverMaxDistanceChangedShouldNotFlyOver () {
        return _ardrone3NoFlyOverMaxDistanceChanged.shouldNotFlyOver;
    }

    public int getArdrone3MaxVerticalSpeedChangedIsSet () {
        return _ardrone3MaxVerticalSpeedChanged.isSet;
    }

    public float getArdrone3MaxVerticalSpeedChangedCurrent () {
        return _ardrone3MaxVerticalSpeedChanged.current;
    }

    public float getArdrone3MaxVerticalSpeedChangedMin () {
        return _ardrone3MaxVerticalSpeedChanged.min;
    }

    public float getArdrone3MaxVerticalSpeedChangedMax () {
        return _ardrone3MaxVerticalSpeedChanged.max;
    }

    public int getArdrone3MaxRotationSpeedChangedIsSet () {
        return _ardrone3MaxRotationSpeedChanged.isSet;
    }

    public float getArdrone3MaxRotationSpeedChangedCurrent () {
        return _ardrone3MaxRotationSpeedChanged.current;
    }

    public float getArdrone3MaxRotationSpeedChangedMin () {
        return _ardrone3MaxRotationSpeedChanged.min;
    }

    public float getArdrone3MaxRotationSpeedChangedMax () {
        return _ardrone3MaxRotationSpeedChanged.max;
    }

    public int getArdrone3MaxPitchRollRotationSpeedChangedIsSet () {
        return _ardrone3MaxPitchRollRotationSpeedChanged.isSet;
    }

    public float getArdrone3MaxPitchRollRotationSpeedChangedCurrent () {
        return _ardrone3MaxPitchRollRotationSpeedChanged.current;
    }

    public float getArdrone3MaxPitchRollRotationSpeedChangedMin () {
        return _ardrone3MaxPitchRollRotationSpeedChanged.min;
    }

    public float getArdrone3MaxPitchRollRotationSpeedChangedMax () {
        return _ardrone3MaxPitchRollRotationSpeedChanged.max;
    }

    public int getArdrone3ReturnHomeDelayChangedIsSet () {
        return _ardrone3ReturnHomeDelayChanged.isSet;
    }

    public short getArdrone3ReturnHomeDelayChangedDelay () {
        return _ardrone3ReturnHomeDelayChanged.delay;
    }

    public int getArdrone3HomeTypeChangedIsSet () {
        return _ardrone3HomeTypeChanged.isSet;
    }

    public ARCOMMANDS_ARDRONE3_GPSSETTINGSSTATE_HOMETYPECHANGED_TYPE_ENUM getArdrone3HomeTypeChangedType () {
        return _ardrone3HomeTypeChanged.type;
    }

    public int getArdrone3VideoStabilizationModeChangedIsSet () {
        return _ardrone3VideoStabilizationModeChanged.isSet;
    }

    public ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEOSTABILIZATIONMODECHANGED_MODE_ENUM getArdrone3VideoStabilizationModeChangedMode () {
        return _ardrone3VideoStabilizationModeChanged.mode;
    }

    public int getArdrone3BankedTurnChangedIsSet () {
        return _ardrone3BankedTurnChanged.isSet;
    }

    public byte getArdrone3BankedTurnChangedState () {
        return _ardrone3BankedTurnChanged.state;
    }

}
