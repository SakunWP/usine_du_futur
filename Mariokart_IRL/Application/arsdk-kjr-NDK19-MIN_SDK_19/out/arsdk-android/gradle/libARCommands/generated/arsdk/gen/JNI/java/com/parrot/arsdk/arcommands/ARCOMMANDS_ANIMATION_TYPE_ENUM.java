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
 * Java copy of the eARCOMMANDS_ANIMATION_TYPE enum
 */
public enum ARCOMMANDS_ANIMATION_TYPE_ENUM {
    /** Dummy value for all unknown cases */
    UNKNOWN (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** No animation */
    NONE (0, "No animation"),
    /** The drone makes a flip */
    FLIP (1, "The drone makes a flip"),
    /** The drone horizontaly rotates on itself */
    HORIZONTAL_PANORAMA (2, "The drone horizontaly rotates on itself"),
    /** The drone flies away on a given distance with a computed angle */
    DRONIE (3, "The drone flies away on a given distance with a computed angle"),
    /** The drone starts looking down, then moves forward while slowly looking at the horizon */
    HORIZONTAL_REVEAL (4, "The drone starts looking down, then moves forward while slowly looking at the horizon"),
    /** The drone starts looking down, then moves up while slowly looking at the horizon. When it reaches its target altitude, it rotates on itself to do a panorama. */
    VERTICAL_REVEAL (5, "The drone starts looking down, then moves up while slowly looking at the horizon. When it reaches its target altitude, it rotates on itself to do a panorama."),
    /** The drone circles around its target. */
    SPIRAL (6, "The drone circles around its target."),
    /** The drone makes a parabola on top of its target and ends on the other side of it. */
    PARABOLA (7, "The drone makes a parabola on top of its target and ends on the other side of it."),
    /** The drone flies horizontally in direction of the target then flies up. */
    CANDLE (8, "The drone flies horizontally in direction of the target then flies up."),
    /** The drone slides horizontally. */
    DOLLY_SLIDE (9, "The drone slides horizontally.");


    private final int value;
    private final String comment;
    static HashMap<Integer, ARCOMMANDS_ANIMATION_TYPE_ENUM> valuesList;

    ARCOMMANDS_ANIMATION_TYPE_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCOMMANDS_ANIMATION_TYPE_ENUM (int value, String comment) {
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
     * Gets the ARCOMMANDS_ANIMATION_TYPE_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCOMMANDS_ANIMATION_TYPE_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCOMMANDS_ANIMATION_TYPE_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCOMMANDS_ANIMATION_TYPE_ENUM [] valuesArray = ARCOMMANDS_ANIMATION_TYPE_ENUM.values ();
            valuesList = new HashMap<Integer, ARCOMMANDS_ANIMATION_TYPE_ENUM> (valuesArray.length);
            for (ARCOMMANDS_ANIMATION_TYPE_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCOMMANDS_ANIMATION_TYPE_ENUM retVal = valuesList.get (value);
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
