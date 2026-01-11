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
 * Interface for the command <code>PilotingPCMD</code> in feature <code>ARDrone3</code> listener
 * @author Parrot (c) 2013
 */
public interface ARCommandARDrone3PilotingPCMDListener {

    /**
     * Called when a command <code>PilotingPCMD</code> in feature <code>ARDrone3</code> is decoded
     * @param _flag Boolean flag: 1 if the roll and pitch values should be taken in consideration. 0 otherwise
     * @param _roll Roll angle as signed percentage.\nOn copters:\nRoll angle expressed as signed percentage of the max pitch/roll setting, in range [-100, 100]\n-100 corresponds to a roll angle of max pitch/roll to the left (drone will fly left)\n100 corresponds to a roll angle of max pitch/roll to the right (drone will fly right)\nThis value may be clamped if necessary, in order to respect the maximum supported physical tilt of the copter.\n\nOn fixed wings:\nRoll angle expressed as signed percentage of the physical max roll of the wing, in range [-100, 100]\nNegative value makes the plane fly to the left\nPositive value makes the plane fly to the right
     * @param _pitch Pitch angle as signed percentage.\nOn copters:\nExpressed as signed percentage of the max pitch/roll setting, in range [-100, 100]\n-100 corresponds to a pitch angle of max pitch/roll towards sky (drone will fly backward)\n100 corresponds to a pitch angle of max pitch/roll towards ground (drone will fly forward)\nThis value may be clamped if necessary, in order to respect the maximum supported physical tilt of the copter.\n\nOn fixed wings:\nExpressed as signed percentage of the physical max pitch of the wing, in range [-100, 100]\nNegative value makes the plane fly in direction of the sky\nPositive value makes the plane fly in direction of the ground
     * @param _yaw Yaw rotation speed as signed percentage.\nOn copters:\nExpressed as signed percentage of the max yaw rotation speed setting, in range [-100, 100].\n-100 corresponds to a counter-clockwise rotation of max yaw rotation speed\n100 corresponds to a clockwise rotation of max yaw rotation speed\nThis value may be clamped if necessary, in order to respect the maximum supported physical tilt of the copter.\n\nOn fixed wings:\nGiving more than a fixed value (75% for the moment) triggers a circle.\nPositive value will trigger a clockwise circling\nNegative value will trigger a counter-clockwise circling
     * @param _gaz Throttle as signed percentage.\nOn copters:\nExpressed as signed percentage of the max vertical speed setting, in range [-100, 100]\n-100 corresponds to a max vertical speed towards ground\n100 corresponds to a max vertical speed towards sky\nThis value may be clamped if necessary, in order to respect the maximum supported physical tilt of the copter.\nDuring the landing phase, putting some positive gaz will cancel the land.\n\nOn fixed wings:\nExpressed as signed percentage of the physical max throttle, in range [-100, 100]\nNegative value makes the plane fly slower\nPositive value makes the plane fly faster
     * @param _timestampAndSeqNum Command timestamp in milliseconds (low 24 bits) + command sequence number (high 8 bits) [0;255].
     */
    void onARDrone3PilotingPCMDUpdate (byte flag, byte roll, byte pitch, byte yaw, byte gaz, int timestampAndSeqNum);
}
