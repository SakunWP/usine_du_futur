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
 * Java copy of the eARCOMMANDS_RC_CHANNEL_ACTION enum
 */
public enum ARCOMMANDS_RC_CHANNEL_ACTION_ENUM {
    /** Dummy value for all unknown cases */
    UNKNOWN (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** Invalid/Unused channel. */
    INVALID (0, "Invalid/Unused channel."),
    /** Roll axis channel. */
    ROLL (1, "Roll axis channel."),
    /** Pitch axis channel. */
    PITCH (2, "Pitch axis channel."),
    /** Yaw axis channel. */
    YAW (3, "Yaw axis channel."),
    /** Gaz / Throttle / Altitude axis channel. */
    GAZ (4, "Gaz / Throttle / Altitude axis channel."),
    /** Takeoff / Land channel. */
    TAKEOFF_LAND (5, "Takeoff / Land channel."),
    /** Emergency channel. */
    EMERGENCY (6, "Emergency channel."),
    /** Return Home channel. */
    RETURN_HOME (7, "Return Home channel."),
    /** RC Piloting mode. Auto mode: used for doing flightplans and for assisted flying with a non-RC controller. Easy manual mode: used for assisted flying with a RC controller. Manual mode: used for non-assisted flying with a RC controller and for directly controlling the servomotors. */
    PILOTING_MODE (8, "RC Piloting mode. Auto mode: used for doing flightplans and for assisted flying with a non-RC controller. Easy manual mode: used for assisted flying with a RC controller. Manual mode: used for non-assisted flying with a RC controller and for directly controlling the servomotors."),
    /** RC take control. When take control is activated the RC controller, if available, becomes the main controller. */
    TAKE_CONTROL (9, "RC take control. When take control is activated the RC controller, if available, becomes the main controller.");


    private final int value;
    private final String comment;
    static HashMap<Integer, ARCOMMANDS_RC_CHANNEL_ACTION_ENUM> valuesList;

    ARCOMMANDS_RC_CHANNEL_ACTION_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCOMMANDS_RC_CHANNEL_ACTION_ENUM (int value, String comment) {
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
     * Gets the ARCOMMANDS_RC_CHANNEL_ACTION_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCOMMANDS_RC_CHANNEL_ACTION_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCOMMANDS_RC_CHANNEL_ACTION_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCOMMANDS_RC_CHANNEL_ACTION_ENUM [] valuesArray = ARCOMMANDS_RC_CHANNEL_ACTION_ENUM.values ();
            valuesList = new HashMap<Integer, ARCOMMANDS_RC_CHANNEL_ACTION_ENUM> (valuesArray.length);
            for (ARCOMMANDS_RC_CHANNEL_ACTION_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCOMMANDS_RC_CHANNEL_ACTION_ENUM retVal = valuesList.get (value);
        if (retVal == null) {
            retVal = UNKNOWN;
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
