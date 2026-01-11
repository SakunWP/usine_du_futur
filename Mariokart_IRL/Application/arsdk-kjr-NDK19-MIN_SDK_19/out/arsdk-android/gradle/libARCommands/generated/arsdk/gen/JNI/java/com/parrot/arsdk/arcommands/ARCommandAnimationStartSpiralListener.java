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
 * Interface for the command <code>StartSpiral</code> in feature <code>Animation</code> listener
 * @author Parrot (c) 2013
 */
public interface ARCommandAnimationStartSpiralListener {

    /**
     * Called when a command <code>StartSpiral</code> in feature <code>Animation</code> is decoded
     * @param _provided_params Bitfield of the config parameters on which given values should be used.\nSetting a bit to 1 means that the corresponding parameter should be used,\notherwise default value should be used.\nSpiral animation configuration parameter.
     * @param _speed Desired speed in m/s.\nNot used when speed of provided_params param is 0.
     * @param _radius_variation Desired relative radius variation in m.\nA value of 2 means that the ending radius will be twice as big as the starting radius.\nA value of -2 means that the ending radius will half of the size of the starting radius.\nA value of 1 means that the radius will not change during the animation.\nNot used when radius variation of provided_params param is 0.
     * @param _vertical_distance Desired vertical distance in m.\nIf negative, the spiral will be directed to the ground.\nNot used when vertical distance of provided_params param is 0.
     * @param _revolution_nb The number of revolution (in turn).\nPositive value makes a clockwise spiral, negative is anti-clockwise.\nExample: 1.5 makes an entire turn plus half of a turn
     * @param _play_mode Desired play mode.\nNot used when play mode of provided_params param is 0.\nAnimation play mode.
     */
    void onAnimationStartSpiralUpdate (byte provided_params, float speed, float radius_variation, float vertical_distance, float revolution_nb, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode);
}
