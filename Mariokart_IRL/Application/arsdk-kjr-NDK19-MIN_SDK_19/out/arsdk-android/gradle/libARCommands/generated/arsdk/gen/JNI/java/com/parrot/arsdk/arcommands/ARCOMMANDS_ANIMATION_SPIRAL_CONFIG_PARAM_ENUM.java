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
 * Java copy of the eARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM enum
 */
public enum ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM {
    /** Dummy value for all unknown cases */
    UNKNOWN (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** Speed parameter. */
    SPEED (0, "Speed parameter."),
    /** Radius variation parameter. */
    RADIUS_VARIATION (1, "Radius variation parameter."),
    /** Vertical distance parameter. */
    VERTICAL_DISTANCE (2, "Vertical distance parameter."),
    /** Revolution number parameter. */
    REVOLUTION_NB (3, "Revolution number parameter."),
    /** Play mode parameter. */
    PLAY_MODE (4, "Play mode parameter.");


    private final int value;
    private final String comment;
    static HashMap<Integer, ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM> valuesList;

    ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM (int value, String comment) {
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
     * Gets the ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM [] valuesArray = ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM.values ();
            valuesList = new HashMap<Integer, ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM> (valuesArray.length);
            for (ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCOMMANDS_ANIMATION_SPIRAL_CONFIG_PARAM_ENUM retVal = valuesList.get (value);
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
