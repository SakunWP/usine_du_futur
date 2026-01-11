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
 * Java copy of the eARCOMMANDS_MAPPER_MINI_BUTTON_ACTION enum
 */
public enum ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM {
    /** Dummy value for all unknown cases */
    UNKNOWN (Integer.MIN_VALUE, "Dummy value for all unknown cases"),
    /** Take off or land */
    TAKEOFF_LAND (0, "Take off or land"),
    /** Take a picture */
    TAKE_PICTURE (1, "Take a picture"),
    /** Flip left */
    FLIP_LEFT (2, "Flip left"),
    /** Flip right */
    FLIP_RIGHT (3, "Flip right"),
    /** Flip front */
    FLIP_FRONT (4, "Flip front"),
    /** Flip back */
    FLIP_BACK (5, "Flip back"),
    /** Emergency motors shutdown */
    EMERGENCY (6, "Emergency motors shutdown"),
    /** Launch USB accessory gun action (shoot) */
    ACCESSORY_GUN (7, "Launch USB accessory gun action (shoot)"),
    /** Thrown take off */
    THROWN_TAKEOFF (8, "Thrown take off"),
    /** 90 deg clockwise swipe */
    CW_90_SWIPE (9, "90 deg clockwise swipe"),
    /** 90 deg counter clockwise swipe */
    CCW_90_SWIPE (10, "90 deg counter clockwise swipe"),
    /** 180 deg clockwise swipe */
    CW_180_SWIPE (11, "180 deg clockwise swipe"),
    /** 180 deg counter clockwise swipe */
    CCW_180_SWIPE (12, "180 deg counter clockwise swipe"),
    /** increase gear */
    GEAR_UP (13, "increase gear"),
    /** decrease gear */
    GEAR_DOWN (14, "decrease gear"),
    /** in plane mode make a 180 deg anticlockwise swipe on roll axis */
    PLANE_MODE_HALF_BAREL_ROLL_RIGHT (15, "in plane mode make a 180 deg anticlockwise swipe on roll axis"),
    /** in plane mode make a 180 deg clockwise swipe on roll axis */
    PLANE_MODE_HALF_BAREL_ROLL_LEFT (16, "in plane mode make a 180 deg clockwise swipe on roll axis"),
    /** in plane mode make a 180 deg clockwise swipe on pitch axis */
    PLANE_MODE_BACKSWAP (17, "in plane mode make a 180 deg clockwise swipe on pitch axis"),
    /** vertical circular loop in plane mode */
    PLANE_MODE_LOOPING (18, "vertical circular loop in plane mode"),
    /** switch between plane mode and quad mode */
    PLANE_MODE_TOGGLE (19, "switch between plane mode and quad mode"),
    /** Launch USB accessory claw action (open/close) */
    ACCESSORY_CLAW (20, "Launch USB accessory claw action (open/close)"),
    /** switch continuous light (ON/OFF) */
    LIGHT_CONTINUOUS (21, "switch continuous light (ON/OFF)"),
    /** switch blink light (ON/OFF) */
    LIGHT_BLINK (22, "switch blink light (ON/OFF)"),
    /** switch sinus light (ON/OFF) */
    LIGHT_SINUS (23, "switch sinus light (ON/OFF)"),
    /** toggle between light animations (OFF-continuous-blink-sinus-OFF) */
    LIGHT_TOGGLE (24, "toggle between light animations (OFF-continuous-blink-sinus-OFF)"),
    /** toggle between easy and preferred piloting mode */
    PILOTING_MODE_TOGGLE (25, "toggle between easy and preferred piloting mode"),
    /** start or stop video */
    VIDEO_RECORD_TOGGLE (26, "start or stop video");


    private final int value;
    private final String comment;
    static HashMap<Integer, ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM> valuesList;

    ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM (int value) {
        this.value = value;
        this.comment = null;
    }

    ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM (int value, String comment) {
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
     * Gets the ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM instance from a C enum value
     * @param value C value of the enum
     * @return The ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM instance, or null if the C enum value was not valid
     */
    public static ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM getFromValue (int value) {
        if (null == valuesList) {
            ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM [] valuesArray = ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM.values ();
            valuesList = new HashMap<Integer, ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM> (valuesArray.length);
            for (ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM entry : valuesArray) {
                valuesList.put (entry.getValue (), entry);
            }
        }
        ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM retVal = valuesList.get (value);
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
