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
 * Interface for the command <code>ModeInfo</code> in feature <code>FollowMe</code> listener
 * @author Parrot (c) 2013
 */
public interface ARCommandFollowMeModeInfoListener {

    /**
     * Called when a command <code>ModeInfo</code> in feature <code>FollowMe</code> is decoded
     * @param _mode FollowMe mode
     * @param _missing_requirements List of missing requirements to enter this mode on start.\nBit is 0 if the input is not ok, 1 if the input is ok.\nIf at least one input is missing, drone won't able to follow the target.\nIt won't use any fallback either\nInput values used by the FollowMe
     * @param _improvements List of inputs that can improve the mode.\nBit is 0 if the input is not ok, 1 if the input is ok.\nIf at least one input is missing, a downgraded mode will be used. See behavior\nInput values used by the FollowMe
     */
    void onFollowMeModeInfoUpdate (ARCOMMANDS_FOLLOW_ME_MODE_ENUM mode, short missing_requirements, short improvements);
}
