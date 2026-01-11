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

import java.util.HashMap;

/**
 * Java copy of the eARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE enum
 */
public enum ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM {
    /** Dummy value for all unknown cases */
    eARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_UNKNOWN_ENUM_VALUE (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** The flight envelope of Mambo FPV has been divided in three piloting modes. The first one corresponds to the well-known flying mode currently used for Mambo, which is based in an horizontal stabilisation (performed via the vertical camera and the acceleration information) and a vertical acceleration (by means of the ultrasound, the barometer and the vertical accelerometer) in order for the drone to stay in fixed point position when no piloting commands are sent by the user. */
    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_EASY (0, "The flight envelope of Mambo FPV has been divided in three piloting modes. The first one corresponds to the well-known flying mode currently used for Mambo, which is based in an horizontal stabilisation (performed via the vertical camera and the acceleration information) and a vertical acceleration (by means of the ultrasound, the barometer and the vertical accelerometer) in order for the drone to stay in fixed point position when no piloting commands are sent by the user."),
    /** The second piloting mode consists of deactivating the horizontal stabilisation. Thus, in this flying mode, when no piloting command is received, the drone will try to stay at 0° tilt angle instead of responding to a 0 m/s horizontal speed reference. This behaviour will result in a slight horizontal drift. */
    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_MEDIUM (1, "The second piloting mode consists of deactivating the horizontal stabilisation. Thus, in this flying mode, when no piloting command is received, the drone will try to stay at 0° tilt angle instead of responding to a 0 m/s horizontal speed reference. This behaviour will result in a slight horizontal drift."),
    /** The third piloting mode also adds the vertical stabilisation deactivation and, therefore, a slight vertical drift. When flying in the third mode, a closed loop height control is no longer performed in order for the drone to keep a certain height and vertical speed. Instead, the vertical command coming from the user will directly generate an open loop acceleration command. */
    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_DIFFICULT (2, "The third piloting mode also adds the vertical stabilisation deactivation and, therefore, a slight vertical drift. When flying in the third mode, a closed loop height control is no longer performed in order for the drone to keep a certain height and vertical speed. Instead, the vertical command coming from the user will directly generate an open loop acceleration command."),
    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_MAX (3);


    private final int value;
    private final String comment;
    static HashMap<Integer, ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM> valuesList;

    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM (int value, String comment) {
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
     * Gets the ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM [] valuesArray = ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM.values ();
            valuesList = new HashMap<Integer, ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM> (valuesArray.length);
            for (ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM retVal = valuesList.get (value);
        if (retVal == null) {
            retVal = eARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_UNKNOWN_ENUM_VALUE;
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
