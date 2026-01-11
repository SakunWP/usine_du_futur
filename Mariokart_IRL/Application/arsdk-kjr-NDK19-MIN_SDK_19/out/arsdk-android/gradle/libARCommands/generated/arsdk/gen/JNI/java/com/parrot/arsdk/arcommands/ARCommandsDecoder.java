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
import com.parrot.arsdk.arsal.ARSALPrint;

/**
 * Java representation of a C ARCommandsDecoder object.<br>
 * This class allow to decode ARCommands.
 * @author Parrot (c) 2016
 */
public class ARCommandsDecoder {

    /**
     * Storage of the C Pointer
     */
    protected long pointer;

    /**
     * Check validity before all native calls
     */
    protected boolean valid;

/**
 * Dummy throwable to keep the constructors call stack
 */

    private Throwable constructorCallStack;

    protected static final String TAG = "ARCommandsDecoder";

    private static native void nativeStaticInit ();
    static
    {
        nativeStaticInit();
    }

    /**
     * Creates a new, ARCommandsDecoder
     */
    public ARCommandsDecoder () {

        this.pointer = nativeNewDecoder ();
        this.valid = false;
        if (this.pointer != 0) {
            this.valid = true;
        }
        this.constructorCallStack = new Throwable();
    }

    /* ********** */
    /* DESTRUCTOR */
    /* ********** */
    protected void finalize () throws Throwable {
        try {
            if (valid) {
                ARSALPrint.w (TAG, this + ": Finalize error -> dispose () was not called !", this.constructorCallStack);
                dispose ();
            }
        }
        finally {
            super.finalize ();
        }
    }

    /* ************** */
    /* IMPLEMENTATION */
    /* ************** */

    /**
    * Checks the object validity
    * @return <code>true</code> if the object is valid (buffer properly alloc and usable)<br><code>false</code> if the object is invalid (alloc error, disposed object)
    */
    public boolean isValid () {
        return valid;
    }

    /**
    * Marks a native data as unused (so C-allocated memory can be freed)<br>
    * A disposed data is marked as invalid
    */
    public void dispose () {
        if (valid)
            nativeDeleteDecoder (pointer);
        this.valid = false;
        this.pointer = 0;
    }
    /**
     * Decodes a ARCommand, calling commands listeners<br>
     * If a listener was set for the Class/Command contained within the ARCommandsDecoder,
     * its <code>onClassCommandUpdate(...)</code> function will be called in the current thread.
     * @param command command to decode.
     * @return An ARCOMMANDS_DECODER_ERROR_ENUM error code
     */
    public ARCOMMANDS_DECODER_ERROR_ENUM decode (ARCommand command) {
        ARCOMMANDS_DECODER_ERROR_ENUM err = ARCOMMANDS_DECODER_ERROR_ENUM.ARCOMMANDS_DECODER_ERROR;
        if ((!valid) || (command == null) || (!command.isValid())) {
            return err;
        }
        int errInt = nativeDecode (pointer, command.getData(), command.getDataSize());
        if (ARCOMMANDS_DECODER_ERROR_ENUM.getFromValue (errInt) != null) {
            err = ARCOMMANDS_DECODER_ERROR_ENUM.getFromValue (errInt);
        }
        return err;
    }

    /**
     * Decodes a command calling commands listeners<br>
     * If a listener was set for the Class/Command contained within the ARCommandsDecoder,
     * its <code>onClassCommandUpdate(...)</code> function will be called in the current thread.
     * @param command command to decode.
     * @return An ARCOMMANDS_DECODER_ERROR_ENUM error code
     */
    public ARCOMMANDS_DECODER_ERROR_ENUM decode (long data, int size) {
        ARCOMMANDS_DECODER_ERROR_ENUM err = ARCOMMANDS_DECODER_ERROR_ENUM.ARCOMMANDS_DECODER_ERROR;
        if (!valid) {
            return err;
        }
        int errInt = nativeDecode (pointer, data, size);
        if (ARCOMMANDS_DECODER_ERROR_ENUM.getFromValue (errInt) != null) {
            err = ARCOMMANDS_DECODER_ERROR_ENUM.getFromValue (errInt);
        }
        return err;
    }

    private ARCommandGenericDefaultListener _ARCommandGenericDefaultListener;

    /**
     * Set the listener for the command <code>Default</code> in feature <code>Generic</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setGenericDefaultListener (ARCommandGenericDefaultListener _ARCommandGenericDefaultListener_PARAM) {
        _ARCommandGenericDefaultListener = _ARCommandGenericDefaultListener_PARAM;
    }

    private ARCommandGenericSetDroneSettingsListener _ARCommandGenericSetDroneSettingsListener;

    /**
     * Set the listener for the command <code>SetDroneSettings</code> in feature <code>Generic</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setGenericSetDroneSettingsListener (ARCommandGenericSetDroneSettingsListener _ARCommandGenericSetDroneSettingsListener_PARAM) {
        _ARCommandGenericSetDroneSettingsListener = _ARCommandGenericSetDroneSettingsListener_PARAM;
    }

    private ARCommandGenericDroneSettingsChangedListener _ARCommandGenericDroneSettingsChangedListener;

    /**
     * Set the listener for the command <code>DroneSettingsChanged</code> in feature <code>Generic</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setGenericDroneSettingsChangedListener (ARCommandGenericDroneSettingsChangedListener _ARCommandGenericDroneSettingsChangedListener_PARAM) {
        _ARCommandGenericDroneSettingsChangedListener = _ARCommandGenericDroneSettingsChangedListener_PARAM;
    }


    private ARCommandAnimationCancelListener _ARCommandAnimationCancelListener;

    /**
     * Set the listener for the command <code>Cancel</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationCancelListener (ARCommandAnimationCancelListener _ARCommandAnimationCancelListener_PARAM) {
        _ARCommandAnimationCancelListener = _ARCommandAnimationCancelListener_PARAM;
    }

    private ARCommandAnimationStartFlipListener _ARCommandAnimationStartFlipListener;

    /**
     * Set the listener for the command <code>StartFlip</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartFlipListener (ARCommandAnimationStartFlipListener _ARCommandAnimationStartFlipListener_PARAM) {
        _ARCommandAnimationStartFlipListener = _ARCommandAnimationStartFlipListener_PARAM;
    }

    private ARCommandAnimationStartHorizontalPanoramaListener _ARCommandAnimationStartHorizontalPanoramaListener;

    /**
     * Set the listener for the command <code>StartHorizontalPanorama</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartHorizontalPanoramaListener (ARCommandAnimationStartHorizontalPanoramaListener _ARCommandAnimationStartHorizontalPanoramaListener_PARAM) {
        _ARCommandAnimationStartHorizontalPanoramaListener = _ARCommandAnimationStartHorizontalPanoramaListener_PARAM;
    }

    private ARCommandAnimationStartDronieListener _ARCommandAnimationStartDronieListener;

    /**
     * Set the listener for the command <code>StartDronie</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartDronieListener (ARCommandAnimationStartDronieListener _ARCommandAnimationStartDronieListener_PARAM) {
        _ARCommandAnimationStartDronieListener = _ARCommandAnimationStartDronieListener_PARAM;
    }

    private ARCommandAnimationStartHorizontalRevealListener _ARCommandAnimationStartHorizontalRevealListener;

    /**
     * Set the listener for the command <code>StartHorizontalReveal</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartHorizontalRevealListener (ARCommandAnimationStartHorizontalRevealListener _ARCommandAnimationStartHorizontalRevealListener_PARAM) {
        _ARCommandAnimationStartHorizontalRevealListener = _ARCommandAnimationStartHorizontalRevealListener_PARAM;
    }

    private ARCommandAnimationStartVerticalRevealListener _ARCommandAnimationStartVerticalRevealListener;

    /**
     * Set the listener for the command <code>StartVerticalReveal</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartVerticalRevealListener (ARCommandAnimationStartVerticalRevealListener _ARCommandAnimationStartVerticalRevealListener_PARAM) {
        _ARCommandAnimationStartVerticalRevealListener = _ARCommandAnimationStartVerticalRevealListener_PARAM;
    }

    private ARCommandAnimationStartSpiralListener _ARCommandAnimationStartSpiralListener;

    /**
     * Set the listener for the command <code>StartSpiral</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartSpiralListener (ARCommandAnimationStartSpiralListener _ARCommandAnimationStartSpiralListener_PARAM) {
        _ARCommandAnimationStartSpiralListener = _ARCommandAnimationStartSpiralListener_PARAM;
    }

    private ARCommandAnimationStartParabolaListener _ARCommandAnimationStartParabolaListener;

    /**
     * Set the listener for the command <code>StartParabola</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartParabolaListener (ARCommandAnimationStartParabolaListener _ARCommandAnimationStartParabolaListener_PARAM) {
        _ARCommandAnimationStartParabolaListener = _ARCommandAnimationStartParabolaListener_PARAM;
    }

    private ARCommandAnimationStartCandleListener _ARCommandAnimationStartCandleListener;

    /**
     * Set the listener for the command <code>StartCandle</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartCandleListener (ARCommandAnimationStartCandleListener _ARCommandAnimationStartCandleListener_PARAM) {
        _ARCommandAnimationStartCandleListener = _ARCommandAnimationStartCandleListener_PARAM;
    }

    private ARCommandAnimationStartDollySlideListener _ARCommandAnimationStartDollySlideListener;

    /**
     * Set the listener for the command <code>StartDollySlide</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStartDollySlideListener (ARCommandAnimationStartDollySlideListener _ARCommandAnimationStartDollySlideListener_PARAM) {
        _ARCommandAnimationStartDollySlideListener = _ARCommandAnimationStartDollySlideListener_PARAM;
    }

    private ARCommandAnimationAvailabilityListener _ARCommandAnimationAvailabilityListener;

    /**
     * Set the listener for the command <code>Availability</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationAvailabilityListener (ARCommandAnimationAvailabilityListener _ARCommandAnimationAvailabilityListener_PARAM) {
        _ARCommandAnimationAvailabilityListener = _ARCommandAnimationAvailabilityListener_PARAM;
    }

    private ARCommandAnimationStateListener _ARCommandAnimationStateListener;

    /**
     * Set the listener for the command <code>State</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationStateListener (ARCommandAnimationStateListener _ARCommandAnimationStateListener_PARAM) {
        _ARCommandAnimationStateListener = _ARCommandAnimationStateListener_PARAM;
    }

    private ARCommandAnimationFlipStateListener _ARCommandAnimationFlipStateListener;

    /**
     * Set the listener for the command <code>FlipState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationFlipStateListener (ARCommandAnimationFlipStateListener _ARCommandAnimationFlipStateListener_PARAM) {
        _ARCommandAnimationFlipStateListener = _ARCommandAnimationFlipStateListener_PARAM;
    }

    private ARCommandAnimationHorizontalPanoramaStateListener _ARCommandAnimationHorizontalPanoramaStateListener;

    /**
     * Set the listener for the command <code>HorizontalPanoramaState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationHorizontalPanoramaStateListener (ARCommandAnimationHorizontalPanoramaStateListener _ARCommandAnimationHorizontalPanoramaStateListener_PARAM) {
        _ARCommandAnimationHorizontalPanoramaStateListener = _ARCommandAnimationHorizontalPanoramaStateListener_PARAM;
    }

    private ARCommandAnimationDronieStateListener _ARCommandAnimationDronieStateListener;

    /**
     * Set the listener for the command <code>DronieState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationDronieStateListener (ARCommandAnimationDronieStateListener _ARCommandAnimationDronieStateListener_PARAM) {
        _ARCommandAnimationDronieStateListener = _ARCommandAnimationDronieStateListener_PARAM;
    }

    private ARCommandAnimationHorizontalRevealStateListener _ARCommandAnimationHorizontalRevealStateListener;

    /**
     * Set the listener for the command <code>HorizontalRevealState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationHorizontalRevealStateListener (ARCommandAnimationHorizontalRevealStateListener _ARCommandAnimationHorizontalRevealStateListener_PARAM) {
        _ARCommandAnimationHorizontalRevealStateListener = _ARCommandAnimationHorizontalRevealStateListener_PARAM;
    }

    private ARCommandAnimationVerticalRevealStateListener _ARCommandAnimationVerticalRevealStateListener;

    /**
     * Set the listener for the command <code>VerticalRevealState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationVerticalRevealStateListener (ARCommandAnimationVerticalRevealStateListener _ARCommandAnimationVerticalRevealStateListener_PARAM) {
        _ARCommandAnimationVerticalRevealStateListener = _ARCommandAnimationVerticalRevealStateListener_PARAM;
    }

    private ARCommandAnimationSpiralStateListener _ARCommandAnimationSpiralStateListener;

    /**
     * Set the listener for the command <code>SpiralState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationSpiralStateListener (ARCommandAnimationSpiralStateListener _ARCommandAnimationSpiralStateListener_PARAM) {
        _ARCommandAnimationSpiralStateListener = _ARCommandAnimationSpiralStateListener_PARAM;
    }

    private ARCommandAnimationParabolaStateListener _ARCommandAnimationParabolaStateListener;

    /**
     * Set the listener for the command <code>ParabolaState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationParabolaStateListener (ARCommandAnimationParabolaStateListener _ARCommandAnimationParabolaStateListener_PARAM) {
        _ARCommandAnimationParabolaStateListener = _ARCommandAnimationParabolaStateListener_PARAM;
    }

    private ARCommandAnimationCandleStateListener _ARCommandAnimationCandleStateListener;

    /**
     * Set the listener for the command <code>CandleState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationCandleStateListener (ARCommandAnimationCandleStateListener _ARCommandAnimationCandleStateListener_PARAM) {
        _ARCommandAnimationCandleStateListener = _ARCommandAnimationCandleStateListener_PARAM;
    }

    private ARCommandAnimationDollySlideStateListener _ARCommandAnimationDollySlideStateListener;

    /**
     * Set the listener for the command <code>DollySlideState</code> in feature <code>Animation</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setAnimationDollySlideStateListener (ARCommandAnimationDollySlideStateListener _ARCommandAnimationDollySlideStateListener_PARAM) {
        _ARCommandAnimationDollySlideStateListener = _ARCommandAnimationDollySlideStateListener_PARAM;
    }


    private ARCommandARDrone3PilotingFlatTrimListener _ARCommandARDrone3PilotingFlatTrimListener;

    /**
     * Set the listener for the command <code>PilotingFlatTrim</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingFlatTrimListener (ARCommandARDrone3PilotingFlatTrimListener _ARCommandARDrone3PilotingFlatTrimListener_PARAM) {
        _ARCommandARDrone3PilotingFlatTrimListener = _ARCommandARDrone3PilotingFlatTrimListener_PARAM;
    }

    private ARCommandARDrone3PilotingTakeOffListener _ARCommandARDrone3PilotingTakeOffListener;

    /**
     * Set the listener for the command <code>PilotingTakeOff</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingTakeOffListener (ARCommandARDrone3PilotingTakeOffListener _ARCommandARDrone3PilotingTakeOffListener_PARAM) {
        _ARCommandARDrone3PilotingTakeOffListener = _ARCommandARDrone3PilotingTakeOffListener_PARAM;
    }

    private ARCommandARDrone3PilotingPCMDListener _ARCommandARDrone3PilotingPCMDListener;

    /**
     * Set the listener for the command <code>PilotingPCMD</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingPCMDListener (ARCommandARDrone3PilotingPCMDListener _ARCommandARDrone3PilotingPCMDListener_PARAM) {
        _ARCommandARDrone3PilotingPCMDListener = _ARCommandARDrone3PilotingPCMDListener_PARAM;
    }

    private ARCommandARDrone3PilotingLandingListener _ARCommandARDrone3PilotingLandingListener;

    /**
     * Set the listener for the command <code>PilotingLanding</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingLandingListener (ARCommandARDrone3PilotingLandingListener _ARCommandARDrone3PilotingLandingListener_PARAM) {
        _ARCommandARDrone3PilotingLandingListener = _ARCommandARDrone3PilotingLandingListener_PARAM;
    }

    private ARCommandARDrone3PilotingEmergencyListener _ARCommandARDrone3PilotingEmergencyListener;

    /**
     * Set the listener for the command <code>PilotingEmergency</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingEmergencyListener (ARCommandARDrone3PilotingEmergencyListener _ARCommandARDrone3PilotingEmergencyListener_PARAM) {
        _ARCommandARDrone3PilotingEmergencyListener = _ARCommandARDrone3PilotingEmergencyListener_PARAM;
    }

    private ARCommandARDrone3PilotingNavigateHomeListener _ARCommandARDrone3PilotingNavigateHomeListener;

    /**
     * Set the listener for the command <code>PilotingNavigateHome</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingNavigateHomeListener (ARCommandARDrone3PilotingNavigateHomeListener _ARCommandARDrone3PilotingNavigateHomeListener_PARAM) {
        _ARCommandARDrone3PilotingNavigateHomeListener = _ARCommandARDrone3PilotingNavigateHomeListener_PARAM;
    }

    private ARCommandARDrone3PilotingAutoTakeOffModeListener _ARCommandARDrone3PilotingAutoTakeOffModeListener;

    /**
     * Set the listener for the command <code>PilotingAutoTakeOffMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingAutoTakeOffModeListener (ARCommandARDrone3PilotingAutoTakeOffModeListener _ARCommandARDrone3PilotingAutoTakeOffModeListener_PARAM) {
        _ARCommandARDrone3PilotingAutoTakeOffModeListener = _ARCommandARDrone3PilotingAutoTakeOffModeListener_PARAM;
    }

    private ARCommandARDrone3PilotingMoveByListener _ARCommandARDrone3PilotingMoveByListener;

    /**
     * Set the listener for the command <code>PilotingMoveBy</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingMoveByListener (ARCommandARDrone3PilotingMoveByListener _ARCommandARDrone3PilotingMoveByListener_PARAM) {
        _ARCommandARDrone3PilotingMoveByListener = _ARCommandARDrone3PilotingMoveByListener_PARAM;
    }

    private ARCommandARDrone3PilotingUserTakeOffListener _ARCommandARDrone3PilotingUserTakeOffListener;

    /**
     * Set the listener for the command <code>PilotingUserTakeOff</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingUserTakeOffListener (ARCommandARDrone3PilotingUserTakeOffListener _ARCommandARDrone3PilotingUserTakeOffListener_PARAM) {
        _ARCommandARDrone3PilotingUserTakeOffListener = _ARCommandARDrone3PilotingUserTakeOffListener_PARAM;
    }

    private ARCommandARDrone3PilotingCircleListener _ARCommandARDrone3PilotingCircleListener;

    /**
     * Set the listener for the command <code>PilotingCircle</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingCircleListener (ARCommandARDrone3PilotingCircleListener _ARCommandARDrone3PilotingCircleListener_PARAM) {
        _ARCommandARDrone3PilotingCircleListener = _ARCommandARDrone3PilotingCircleListener_PARAM;
    }

    private ARCommandARDrone3PilotingMoveToListener _ARCommandARDrone3PilotingMoveToListener;

    /**
     * Set the listener for the command <code>PilotingMoveTo</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingMoveToListener (ARCommandARDrone3PilotingMoveToListener _ARCommandARDrone3PilotingMoveToListener_PARAM) {
        _ARCommandARDrone3PilotingMoveToListener = _ARCommandARDrone3PilotingMoveToListener_PARAM;
    }

    private ARCommandARDrone3PilotingCancelMoveToListener _ARCommandARDrone3PilotingCancelMoveToListener;

    /**
     * Set the listener for the command <code>PilotingCancelMoveTo</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingCancelMoveToListener (ARCommandARDrone3PilotingCancelMoveToListener _ARCommandARDrone3PilotingCancelMoveToListener_PARAM) {
        _ARCommandARDrone3PilotingCancelMoveToListener = _ARCommandARDrone3PilotingCancelMoveToListener_PARAM;
    }

    private ARCommandARDrone3PilotingStartPilotedPOIListener _ARCommandARDrone3PilotingStartPilotedPOIListener;

    /**
     * Set the listener for the command <code>PilotingStartPilotedPOI</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStartPilotedPOIListener (ARCommandARDrone3PilotingStartPilotedPOIListener _ARCommandARDrone3PilotingStartPilotedPOIListener_PARAM) {
        _ARCommandARDrone3PilotingStartPilotedPOIListener = _ARCommandARDrone3PilotingStartPilotedPOIListener_PARAM;
    }

    private ARCommandARDrone3PilotingStopPilotedPOIListener _ARCommandARDrone3PilotingStopPilotedPOIListener;

    /**
     * Set the listener for the command <code>PilotingStopPilotedPOI</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStopPilotedPOIListener (ARCommandARDrone3PilotingStopPilotedPOIListener _ARCommandARDrone3PilotingStopPilotedPOIListener_PARAM) {
        _ARCommandARDrone3PilotingStopPilotedPOIListener = _ARCommandARDrone3PilotingStopPilotedPOIListener_PARAM;
    }

    private ARCommandARDrone3AnimationsFlipListener _ARCommandARDrone3AnimationsFlipListener;

    /**
     * Set the listener for the command <code>AnimationsFlip</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AnimationsFlipListener (ARCommandARDrone3AnimationsFlipListener _ARCommandARDrone3AnimationsFlipListener_PARAM) {
        _ARCommandARDrone3AnimationsFlipListener = _ARCommandARDrone3AnimationsFlipListener_PARAM;
    }

    private ARCommandARDrone3CameraOrientationListener _ARCommandARDrone3CameraOrientationListener;

    /**
     * Set the listener for the command <code>CameraOrientation</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraOrientationListener (ARCommandARDrone3CameraOrientationListener _ARCommandARDrone3CameraOrientationListener_PARAM) {
        _ARCommandARDrone3CameraOrientationListener = _ARCommandARDrone3CameraOrientationListener_PARAM;
    }

    private ARCommandARDrone3CameraOrientationV2Listener _ARCommandARDrone3CameraOrientationV2Listener;

    /**
     * Set the listener for the command <code>CameraOrientationV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraOrientationV2Listener (ARCommandARDrone3CameraOrientationV2Listener _ARCommandARDrone3CameraOrientationV2Listener_PARAM) {
        _ARCommandARDrone3CameraOrientationV2Listener = _ARCommandARDrone3CameraOrientationV2Listener_PARAM;
    }

    private ARCommandARDrone3CameraVelocityListener _ARCommandARDrone3CameraVelocityListener;

    /**
     * Set the listener for the command <code>CameraVelocity</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraVelocityListener (ARCommandARDrone3CameraVelocityListener _ARCommandARDrone3CameraVelocityListener_PARAM) {
        _ARCommandARDrone3CameraVelocityListener = _ARCommandARDrone3CameraVelocityListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordPictureListener _ARCommandARDrone3MediaRecordPictureListener;

    /**
     * Set the listener for the command <code>MediaRecordPicture</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordPictureListener (ARCommandARDrone3MediaRecordPictureListener _ARCommandARDrone3MediaRecordPictureListener_PARAM) {
        _ARCommandARDrone3MediaRecordPictureListener = _ARCommandARDrone3MediaRecordPictureListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordVideoListener _ARCommandARDrone3MediaRecordVideoListener;

    /**
     * Set the listener for the command <code>MediaRecordVideo</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordVideoListener (ARCommandARDrone3MediaRecordVideoListener _ARCommandARDrone3MediaRecordVideoListener_PARAM) {
        _ARCommandARDrone3MediaRecordVideoListener = _ARCommandARDrone3MediaRecordVideoListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordPictureV2Listener _ARCommandARDrone3MediaRecordPictureV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordPictureV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordPictureV2Listener (ARCommandARDrone3MediaRecordPictureV2Listener _ARCommandARDrone3MediaRecordPictureV2Listener_PARAM) {
        _ARCommandARDrone3MediaRecordPictureV2Listener = _ARCommandARDrone3MediaRecordPictureV2Listener_PARAM;
    }

    private ARCommandARDrone3MediaRecordVideoV2Listener _ARCommandARDrone3MediaRecordVideoV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordVideoV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordVideoV2Listener (ARCommandARDrone3MediaRecordVideoV2Listener _ARCommandARDrone3MediaRecordVideoV2Listener_PARAM) {
        _ARCommandARDrone3MediaRecordVideoV2Listener = _ARCommandARDrone3MediaRecordVideoV2Listener_PARAM;
    }

    private ARCommandARDrone3NetworkWifiScanListener _ARCommandARDrone3NetworkWifiScanListener;

    /**
     * Set the listener for the command <code>NetworkWifiScan</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkWifiScanListener (ARCommandARDrone3NetworkWifiScanListener _ARCommandARDrone3NetworkWifiScanListener_PARAM) {
        _ARCommandARDrone3NetworkWifiScanListener = _ARCommandARDrone3NetworkWifiScanListener_PARAM;
    }

    private ARCommandARDrone3NetworkWifiAuthChannelListener _ARCommandARDrone3NetworkWifiAuthChannelListener;

    /**
     * Set the listener for the command <code>NetworkWifiAuthChannel</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkWifiAuthChannelListener (ARCommandARDrone3NetworkWifiAuthChannelListener _ARCommandARDrone3NetworkWifiAuthChannelListener_PARAM) {
        _ARCommandARDrone3NetworkWifiAuthChannelListener = _ARCommandARDrone3NetworkWifiAuthChannelListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsMaxAltitudeListener _ARCommandARDrone3PilotingSettingsMaxAltitudeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxAltitude</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsMaxAltitudeListener (ARCommandARDrone3PilotingSettingsMaxAltitudeListener _ARCommandARDrone3PilotingSettingsMaxAltitudeListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsMaxAltitudeListener = _ARCommandARDrone3PilotingSettingsMaxAltitudeListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsMaxTiltListener _ARCommandARDrone3PilotingSettingsMaxTiltListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxTilt</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsMaxTiltListener (ARCommandARDrone3PilotingSettingsMaxTiltListener _ARCommandARDrone3PilotingSettingsMaxTiltListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsMaxTiltListener = _ARCommandARDrone3PilotingSettingsMaxTiltListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsAbsolutControlListener _ARCommandARDrone3PilotingSettingsAbsolutControlListener;

    /**
     * Set the listener for the command <code>PilotingSettingsAbsolutControl</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsAbsolutControlListener (ARCommandARDrone3PilotingSettingsAbsolutControlListener _ARCommandARDrone3PilotingSettingsAbsolutControlListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsAbsolutControlListener = _ARCommandARDrone3PilotingSettingsAbsolutControlListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsMaxDistanceListener _ARCommandARDrone3PilotingSettingsMaxDistanceListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxDistance</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsMaxDistanceListener (ARCommandARDrone3PilotingSettingsMaxDistanceListener _ARCommandARDrone3PilotingSettingsMaxDistanceListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsMaxDistanceListener = _ARCommandARDrone3PilotingSettingsMaxDistanceListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener _ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener;

    /**
     * Set the listener for the command <code>PilotingSettingsNoFlyOverMaxDistance</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsNoFlyOverMaxDistanceListener (ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener _ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener = _ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetAutonomousFlightMaxHorizontalSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener (ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener = _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetAutonomousFlightMaxVerticalSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener (ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener = _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetAutonomousFlightMaxHorizontalAcceleration</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener (ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener = _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetAutonomousFlightMaxVerticalAcceleration</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener (ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener = _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetAutonomousFlightMaxRotationSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener (ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener = _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsBankedTurnListener _ARCommandARDrone3PilotingSettingsBankedTurnListener;

    /**
     * Set the listener for the command <code>PilotingSettingsBankedTurn</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsBankedTurnListener (ARCommandARDrone3PilotingSettingsBankedTurnListener _ARCommandARDrone3PilotingSettingsBankedTurnListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsBankedTurnListener = _ARCommandARDrone3PilotingSettingsBankedTurnListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsMinAltitudeListener _ARCommandARDrone3PilotingSettingsMinAltitudeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMinAltitude</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsMinAltitudeListener (ARCommandARDrone3PilotingSettingsMinAltitudeListener _ARCommandARDrone3PilotingSettingsMinAltitudeListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsMinAltitudeListener = _ARCommandARDrone3PilotingSettingsMinAltitudeListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsCirclingDirectionListener _ARCommandARDrone3PilotingSettingsCirclingDirectionListener;

    /**
     * Set the listener for the command <code>PilotingSettingsCirclingDirection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsCirclingDirectionListener (ARCommandARDrone3PilotingSettingsCirclingDirectionListener _ARCommandARDrone3PilotingSettingsCirclingDirectionListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsCirclingDirectionListener = _ARCommandARDrone3PilotingSettingsCirclingDirectionListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsCirclingRadiusListener _ARCommandARDrone3PilotingSettingsCirclingRadiusListener;

    /**
     * Set the listener for the command <code>PilotingSettingsCirclingRadius</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsCirclingRadiusListener (ARCommandARDrone3PilotingSettingsCirclingRadiusListener _ARCommandARDrone3PilotingSettingsCirclingRadiusListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsCirclingRadiusListener = _ARCommandARDrone3PilotingSettingsCirclingRadiusListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsCirclingAltitudeListener _ARCommandARDrone3PilotingSettingsCirclingAltitudeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsCirclingAltitude</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsCirclingAltitudeListener (ARCommandARDrone3PilotingSettingsCirclingAltitudeListener _ARCommandARDrone3PilotingSettingsCirclingAltitudeListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsCirclingAltitudeListener = _ARCommandARDrone3PilotingSettingsCirclingAltitudeListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsPitchModeListener _ARCommandARDrone3PilotingSettingsPitchModeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsPitchMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsPitchModeListener (ARCommandARDrone3PilotingSettingsPitchModeListener _ARCommandARDrone3PilotingSettingsPitchModeListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsPitchModeListener = _ARCommandARDrone3PilotingSettingsPitchModeListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener _ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSetMotionDetectionMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsSetMotionDetectionModeListener (ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener _ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener = _ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener _ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxVerticalSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsMaxVerticalSpeedListener (ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener _ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener = _ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener _ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxRotationSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsMaxRotationSpeedListener (ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener _ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener = _ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsHullProtectionListener _ARCommandARDrone3SpeedSettingsHullProtectionListener;

    /**
     * Set the listener for the command <code>SpeedSettingsHullProtection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsHullProtectionListener (ARCommandARDrone3SpeedSettingsHullProtectionListener _ARCommandARDrone3SpeedSettingsHullProtectionListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsHullProtectionListener = _ARCommandARDrone3SpeedSettingsHullProtectionListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsOutdoorListener _ARCommandARDrone3SpeedSettingsOutdoorListener;

    /**
     * Set the listener for the command <code>SpeedSettingsOutdoor</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsOutdoorListener (ARCommandARDrone3SpeedSettingsOutdoorListener _ARCommandARDrone3SpeedSettingsOutdoorListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsOutdoorListener = _ARCommandARDrone3SpeedSettingsOutdoorListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener _ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxPitchRollRotationSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener (ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener _ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener = _ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener_PARAM;
    }

    private ARCommandARDrone3NetworkSettingsWifiSelectionListener _ARCommandARDrone3NetworkSettingsWifiSelectionListener;

    /**
     * Set the listener for the command <code>NetworkSettingsWifiSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkSettingsWifiSelectionListener (ARCommandARDrone3NetworkSettingsWifiSelectionListener _ARCommandARDrone3NetworkSettingsWifiSelectionListener_PARAM) {
        _ARCommandARDrone3NetworkSettingsWifiSelectionListener = _ARCommandARDrone3NetworkSettingsWifiSelectionListener_PARAM;
    }

    private ARCommandARDrone3NetworkSettingsWifiSecurityListener _ARCommandARDrone3NetworkSettingsWifiSecurityListener;

    /**
     * Set the listener for the command <code>NetworkSettingsWifiSecurity</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkSettingsWifiSecurityListener (ARCommandARDrone3NetworkSettingsWifiSecurityListener _ARCommandARDrone3NetworkSettingsWifiSecurityListener_PARAM) {
        _ARCommandARDrone3NetworkSettingsWifiSecurityListener = _ARCommandARDrone3NetworkSettingsWifiSecurityListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsPictureFormatSelectionListener _ARCommandARDrone3PictureSettingsPictureFormatSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsPictureFormatSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsPictureFormatSelectionListener (ARCommandARDrone3PictureSettingsPictureFormatSelectionListener _ARCommandARDrone3PictureSettingsPictureFormatSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsPictureFormatSelectionListener = _ARCommandARDrone3PictureSettingsPictureFormatSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener _ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsAutoWhiteBalanceSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsAutoWhiteBalanceSelectionListener (ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener _ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener = _ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsExpositionSelectionListener _ARCommandARDrone3PictureSettingsExpositionSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsExpositionSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsExpositionSelectionListener (ARCommandARDrone3PictureSettingsExpositionSelectionListener _ARCommandARDrone3PictureSettingsExpositionSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsExpositionSelectionListener = _ARCommandARDrone3PictureSettingsExpositionSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsSaturationSelectionListener _ARCommandARDrone3PictureSettingsSaturationSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsSaturationSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsSaturationSelectionListener (ARCommandARDrone3PictureSettingsSaturationSelectionListener _ARCommandARDrone3PictureSettingsSaturationSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsSaturationSelectionListener = _ARCommandARDrone3PictureSettingsSaturationSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsTimelapseSelectionListener _ARCommandARDrone3PictureSettingsTimelapseSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsTimelapseSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsTimelapseSelectionListener (ARCommandARDrone3PictureSettingsTimelapseSelectionListener _ARCommandARDrone3PictureSettingsTimelapseSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsTimelapseSelectionListener = _ARCommandARDrone3PictureSettingsTimelapseSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener _ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener;

    /**
     * Set the listener for the command <code>PictureSettingsVideoAutorecordSelection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsVideoAutorecordSelectionListener (ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener _ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener_PARAM) {
        _ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener = _ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsVideoStabilizationModeListener _ARCommandARDrone3PictureSettingsVideoStabilizationModeListener;

    /**
     * Set the listener for the command <code>PictureSettingsVideoStabilizationMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsVideoStabilizationModeListener (ARCommandARDrone3PictureSettingsVideoStabilizationModeListener _ARCommandARDrone3PictureSettingsVideoStabilizationModeListener_PARAM) {
        _ARCommandARDrone3PictureSettingsVideoStabilizationModeListener = _ARCommandARDrone3PictureSettingsVideoStabilizationModeListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsVideoRecordingModeListener _ARCommandARDrone3PictureSettingsVideoRecordingModeListener;

    /**
     * Set the listener for the command <code>PictureSettingsVideoRecordingMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsVideoRecordingModeListener (ARCommandARDrone3PictureSettingsVideoRecordingModeListener _ARCommandARDrone3PictureSettingsVideoRecordingModeListener_PARAM) {
        _ARCommandARDrone3PictureSettingsVideoRecordingModeListener = _ARCommandARDrone3PictureSettingsVideoRecordingModeListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsVideoFramerateListener _ARCommandARDrone3PictureSettingsVideoFramerateListener;

    /**
     * Set the listener for the command <code>PictureSettingsVideoFramerate</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsVideoFramerateListener (ARCommandARDrone3PictureSettingsVideoFramerateListener _ARCommandARDrone3PictureSettingsVideoFramerateListener_PARAM) {
        _ARCommandARDrone3PictureSettingsVideoFramerateListener = _ARCommandARDrone3PictureSettingsVideoFramerateListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsVideoResolutionsListener _ARCommandARDrone3PictureSettingsVideoResolutionsListener;

    /**
     * Set the listener for the command <code>PictureSettingsVideoResolutions</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsVideoResolutionsListener (ARCommandARDrone3PictureSettingsVideoResolutionsListener _ARCommandARDrone3PictureSettingsVideoResolutionsListener_PARAM) {
        _ARCommandARDrone3PictureSettingsVideoResolutionsListener = _ARCommandARDrone3PictureSettingsVideoResolutionsListener_PARAM;
    }

    private ARCommandARDrone3MediaStreamingVideoEnableListener _ARCommandARDrone3MediaStreamingVideoEnableListener;

    /**
     * Set the listener for the command <code>MediaStreamingVideoEnable</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaStreamingVideoEnableListener (ARCommandARDrone3MediaStreamingVideoEnableListener _ARCommandARDrone3MediaStreamingVideoEnableListener_PARAM) {
        _ARCommandARDrone3MediaStreamingVideoEnableListener = _ARCommandARDrone3MediaStreamingVideoEnableListener_PARAM;
    }

    private ARCommandARDrone3MediaStreamingVideoStreamModeListener _ARCommandARDrone3MediaStreamingVideoStreamModeListener;

    /**
     * Set the listener for the command <code>MediaStreamingVideoStreamMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaStreamingVideoStreamModeListener (ARCommandARDrone3MediaStreamingVideoStreamModeListener _ARCommandARDrone3MediaStreamingVideoStreamModeListener_PARAM) {
        _ARCommandARDrone3MediaStreamingVideoStreamModeListener = _ARCommandARDrone3MediaStreamingVideoStreamModeListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsSetHomeListener _ARCommandARDrone3GPSSettingsSetHomeListener;

    /**
     * Set the listener for the command <code>GPSSettingsSetHome</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsSetHomeListener (ARCommandARDrone3GPSSettingsSetHomeListener _ARCommandARDrone3GPSSettingsSetHomeListener_PARAM) {
        _ARCommandARDrone3GPSSettingsSetHomeListener = _ARCommandARDrone3GPSSettingsSetHomeListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsResetHomeListener _ARCommandARDrone3GPSSettingsResetHomeListener;

    /**
     * Set the listener for the command <code>GPSSettingsResetHome</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsResetHomeListener (ARCommandARDrone3GPSSettingsResetHomeListener _ARCommandARDrone3GPSSettingsResetHomeListener_PARAM) {
        _ARCommandARDrone3GPSSettingsResetHomeListener = _ARCommandARDrone3GPSSettingsResetHomeListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsSendControllerGPSListener _ARCommandARDrone3GPSSettingsSendControllerGPSListener;

    /**
     * Set the listener for the command <code>GPSSettingsSendControllerGPS</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsSendControllerGPSListener (ARCommandARDrone3GPSSettingsSendControllerGPSListener _ARCommandARDrone3GPSSettingsSendControllerGPSListener_PARAM) {
        _ARCommandARDrone3GPSSettingsSendControllerGPSListener = _ARCommandARDrone3GPSSettingsSendControllerGPSListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsHomeTypeListener _ARCommandARDrone3GPSSettingsHomeTypeListener;

    /**
     * Set the listener for the command <code>GPSSettingsHomeType</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsHomeTypeListener (ARCommandARDrone3GPSSettingsHomeTypeListener _ARCommandARDrone3GPSSettingsHomeTypeListener_PARAM) {
        _ARCommandARDrone3GPSSettingsHomeTypeListener = _ARCommandARDrone3GPSSettingsHomeTypeListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsReturnHomeDelayListener _ARCommandARDrone3GPSSettingsReturnHomeDelayListener;

    /**
     * Set the listener for the command <code>GPSSettingsReturnHomeDelay</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsReturnHomeDelayListener (ARCommandARDrone3GPSSettingsReturnHomeDelayListener _ARCommandARDrone3GPSSettingsReturnHomeDelayListener_PARAM) {
        _ARCommandARDrone3GPSSettingsReturnHomeDelayListener = _ARCommandARDrone3GPSSettingsReturnHomeDelayListener_PARAM;
    }

    private ARCommandARDrone3AntiflickeringElectricFrequencyListener _ARCommandARDrone3AntiflickeringElectricFrequencyListener;

    /**
     * Set the listener for the command <code>AntiflickeringElectricFrequency</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AntiflickeringElectricFrequencyListener (ARCommandARDrone3AntiflickeringElectricFrequencyListener _ARCommandARDrone3AntiflickeringElectricFrequencyListener_PARAM) {
        _ARCommandARDrone3AntiflickeringElectricFrequencyListener = _ARCommandARDrone3AntiflickeringElectricFrequencyListener_PARAM;
    }

    private ARCommandARDrone3AntiflickeringSetModeListener _ARCommandARDrone3AntiflickeringSetModeListener;

    /**
     * Set the listener for the command <code>AntiflickeringSetMode</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AntiflickeringSetModeListener (ARCommandARDrone3AntiflickeringSetModeListener _ARCommandARDrone3AntiflickeringSetModeListener_PARAM) {
        _ARCommandARDrone3AntiflickeringSetModeListener = _ARCommandARDrone3AntiflickeringSetModeListener_PARAM;
    }

    private ARCommandARDrone3SoundStartAlertSoundListener _ARCommandARDrone3SoundStartAlertSoundListener;

    /**
     * Set the listener for the command <code>SoundStartAlertSound</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SoundStartAlertSoundListener (ARCommandARDrone3SoundStartAlertSoundListener _ARCommandARDrone3SoundStartAlertSoundListener_PARAM) {
        _ARCommandARDrone3SoundStartAlertSoundListener = _ARCommandARDrone3SoundStartAlertSoundListener_PARAM;
    }

    private ARCommandARDrone3SoundStopAlertSoundListener _ARCommandARDrone3SoundStopAlertSoundListener;

    /**
     * Set the listener for the command <code>SoundStopAlertSound</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SoundStopAlertSoundListener (ARCommandARDrone3SoundStopAlertSoundListener _ARCommandARDrone3SoundStopAlertSoundListener_PARAM) {
        _ARCommandARDrone3SoundStopAlertSoundListener = _ARCommandARDrone3SoundStopAlertSoundListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordStatePictureStateChangedListener _ARCommandARDrone3MediaRecordStatePictureStateChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordStatePictureStateChangedListener (ARCommandARDrone3MediaRecordStatePictureStateChangedListener _ARCommandARDrone3MediaRecordStatePictureStateChangedListener_PARAM) {
        _ARCommandARDrone3MediaRecordStatePictureStateChangedListener = _ARCommandARDrone3MediaRecordStatePictureStateChangedListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordStateVideoStateChangedListener _ARCommandARDrone3MediaRecordStateVideoStateChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordStateVideoStateChangedListener (ARCommandARDrone3MediaRecordStateVideoStateChangedListener _ARCommandARDrone3MediaRecordStateVideoStateChangedListener_PARAM) {
        _ARCommandARDrone3MediaRecordStateVideoStateChangedListener = _ARCommandARDrone3MediaRecordStateVideoStateChangedListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener _ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChangedV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordStatePictureStateChangedV2Listener (ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener _ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener_PARAM) {
        _ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener = _ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener_PARAM;
    }

    private ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener _ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoStateChangedV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordStateVideoStateChangedV2Listener (ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener _ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener_PARAM) {
        _ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener = _ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener_PARAM;
    }

    private ARCommandARDrone3MediaRecordStateVideoResolutionStateListener _ARCommandARDrone3MediaRecordStateVideoResolutionStateListener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoResolutionState</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordStateVideoResolutionStateListener (ARCommandARDrone3MediaRecordStateVideoResolutionStateListener _ARCommandARDrone3MediaRecordStateVideoResolutionStateListener_PARAM) {
        _ARCommandARDrone3MediaRecordStateVideoResolutionStateListener = _ARCommandARDrone3MediaRecordStateVideoResolutionStateListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordEventPictureEventChangedListener _ARCommandARDrone3MediaRecordEventPictureEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventPictureEventChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordEventPictureEventChangedListener (ARCommandARDrone3MediaRecordEventPictureEventChangedListener _ARCommandARDrone3MediaRecordEventPictureEventChangedListener_PARAM) {
        _ARCommandARDrone3MediaRecordEventPictureEventChangedListener = _ARCommandARDrone3MediaRecordEventPictureEventChangedListener_PARAM;
    }

    private ARCommandARDrone3MediaRecordEventVideoEventChangedListener _ARCommandARDrone3MediaRecordEventVideoEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventVideoEventChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaRecordEventVideoEventChangedListener (ARCommandARDrone3MediaRecordEventVideoEventChangedListener _ARCommandARDrone3MediaRecordEventVideoEventChangedListener_PARAM) {
        _ARCommandARDrone3MediaRecordEventVideoEventChangedListener = _ARCommandARDrone3MediaRecordEventVideoEventChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateFlatTrimChangedListener _ARCommandARDrone3PilotingStateFlatTrimChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlatTrimChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateFlatTrimChangedListener (ARCommandARDrone3PilotingStateFlatTrimChangedListener _ARCommandARDrone3PilotingStateFlatTrimChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateFlatTrimChangedListener = _ARCommandARDrone3PilotingStateFlatTrimChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateFlyingStateChangedListener _ARCommandARDrone3PilotingStateFlyingStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlyingStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateFlyingStateChangedListener (ARCommandARDrone3PilotingStateFlyingStateChangedListener _ARCommandARDrone3PilotingStateFlyingStateChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateFlyingStateChangedListener = _ARCommandARDrone3PilotingStateFlyingStateChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateAlertStateChangedListener _ARCommandARDrone3PilotingStateAlertStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAlertStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateAlertStateChangedListener (ARCommandARDrone3PilotingStateAlertStateChangedListener _ARCommandARDrone3PilotingStateAlertStateChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateAlertStateChangedListener = _ARCommandARDrone3PilotingStateAlertStateChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener _ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateNavigateHomeStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateNavigateHomeStateChangedListener (ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener _ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener = _ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStatePositionChangedListener _ARCommandARDrone3PilotingStatePositionChangedListener;

    /**
     * Set the listener for the command <code>PilotingStatePositionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStatePositionChangedListener (ARCommandARDrone3PilotingStatePositionChangedListener _ARCommandARDrone3PilotingStatePositionChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStatePositionChangedListener = _ARCommandARDrone3PilotingStatePositionChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateSpeedChangedListener _ARCommandARDrone3PilotingStateSpeedChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateSpeedChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateSpeedChangedListener (ARCommandARDrone3PilotingStateSpeedChangedListener _ARCommandARDrone3PilotingStateSpeedChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateSpeedChangedListener = _ARCommandARDrone3PilotingStateSpeedChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateAttitudeChangedListener _ARCommandARDrone3PilotingStateAttitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAttitudeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateAttitudeChangedListener (ARCommandARDrone3PilotingStateAttitudeChangedListener _ARCommandARDrone3PilotingStateAttitudeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateAttitudeChangedListener = _ARCommandARDrone3PilotingStateAttitudeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener _ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAutoTakeOffModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateAutoTakeOffModeChangedListener (ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener _ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener = _ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateAltitudeChangedListener _ARCommandARDrone3PilotingStateAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAltitudeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateAltitudeChangedListener (ARCommandARDrone3PilotingStateAltitudeChangedListener _ARCommandARDrone3PilotingStateAltitudeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateAltitudeChangedListener = _ARCommandARDrone3PilotingStateAltitudeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateGpsLocationChangedListener _ARCommandARDrone3PilotingStateGpsLocationChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateGpsLocationChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateGpsLocationChangedListener (ARCommandARDrone3PilotingStateGpsLocationChangedListener _ARCommandARDrone3PilotingStateGpsLocationChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateGpsLocationChangedListener = _ARCommandARDrone3PilotingStateGpsLocationChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateLandingStateChangedListener _ARCommandARDrone3PilotingStateLandingStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateLandingStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateLandingStateChangedListener (ARCommandARDrone3PilotingStateLandingStateChangedListener _ARCommandARDrone3PilotingStateLandingStateChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateLandingStateChangedListener = _ARCommandARDrone3PilotingStateLandingStateChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateAirSpeedChangedListener _ARCommandARDrone3PilotingStateAirSpeedChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAirSpeedChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateAirSpeedChangedListener (ARCommandARDrone3PilotingStateAirSpeedChangedListener _ARCommandARDrone3PilotingStateAirSpeedChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateAirSpeedChangedListener = _ARCommandARDrone3PilotingStateAirSpeedChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateMoveToChangedListener _ARCommandARDrone3PilotingStateMoveToChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateMoveToChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateMoveToChangedListener (ARCommandARDrone3PilotingStateMoveToChangedListener _ARCommandARDrone3PilotingStateMoveToChangedListener_PARAM) {
        _ARCommandARDrone3PilotingStateMoveToChangedListener = _ARCommandARDrone3PilotingStateMoveToChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateMotionStateListener _ARCommandARDrone3PilotingStateMotionStateListener;

    /**
     * Set the listener for the command <code>PilotingStateMotionState</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateMotionStateListener (ARCommandARDrone3PilotingStateMotionStateListener _ARCommandARDrone3PilotingStateMotionStateListener_PARAM) {
        _ARCommandARDrone3PilotingStateMotionStateListener = _ARCommandARDrone3PilotingStateMotionStateListener_PARAM;
    }

    private ARCommandARDrone3PilotingStatePilotedPOIListener _ARCommandARDrone3PilotingStatePilotedPOIListener;

    /**
     * Set the listener for the command <code>PilotingStatePilotedPOI</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStatePilotedPOIListener (ARCommandARDrone3PilotingStatePilotedPOIListener _ARCommandARDrone3PilotingStatePilotedPOIListener_PARAM) {
        _ARCommandARDrone3PilotingStatePilotedPOIListener = _ARCommandARDrone3PilotingStatePilotedPOIListener_PARAM;
    }

    private ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener _ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener;

    /**
     * Set the listener for the command <code>PilotingStateReturnHomeBatteryCapacity</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingStateReturnHomeBatteryCapacityListener (ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener _ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener_PARAM) {
        _ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener = _ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener_PARAM;
    }

    private ARCommandARDrone3PilotingEventMoveByEndListener _ARCommandARDrone3PilotingEventMoveByEndListener;

    /**
     * Set the listener for the command <code>PilotingEventMoveByEnd</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingEventMoveByEndListener (ARCommandARDrone3PilotingEventMoveByEndListener _ARCommandARDrone3PilotingEventMoveByEndListener_PARAM) {
        _ARCommandARDrone3PilotingEventMoveByEndListener = _ARCommandARDrone3PilotingEventMoveByEndListener_PARAM;
    }

    private ARCommandARDrone3NetworkStateWifiScanListChangedListener _ARCommandARDrone3NetworkStateWifiScanListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiScanListChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkStateWifiScanListChangedListener (ARCommandARDrone3NetworkStateWifiScanListChangedListener _ARCommandARDrone3NetworkStateWifiScanListChangedListener_PARAM) {
        _ARCommandARDrone3NetworkStateWifiScanListChangedListener = _ARCommandARDrone3NetworkStateWifiScanListChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkStateAllWifiScanChangedListener _ARCommandARDrone3NetworkStateAllWifiScanChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiScanChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkStateAllWifiScanChangedListener (ARCommandARDrone3NetworkStateAllWifiScanChangedListener _ARCommandARDrone3NetworkStateAllWifiScanChangedListener_PARAM) {
        _ARCommandARDrone3NetworkStateAllWifiScanChangedListener = _ARCommandARDrone3NetworkStateAllWifiScanChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener _ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiAuthChannelListChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkStateWifiAuthChannelListChangedListener (ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener _ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener_PARAM) {
        _ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener = _ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener _ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiAuthChannelChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkStateAllWifiAuthChannelChangedListener (ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener _ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener_PARAM) {
        _ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener = _ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxAltitudeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateMaxAltitudeChangedListener (ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener = _ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener _ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxTiltChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateMaxTiltChangedListener (ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener _ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener = _ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener _ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAbsolutControlChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAbsolutControlChangedListener (ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener _ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener = _ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener _ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxDistanceChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateMaxDistanceChangedListener (ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener _ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener = _ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener _ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateNoFlyOverMaxDistanceChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener (ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener _ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener = _ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAutonomousFlightMaxHorizontalSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener (ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener = _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAutonomousFlightMaxVerticalSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener (ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener = _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAutonomousFlightMaxHorizontalAcceleration</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener (ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener = _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAutonomousFlightMaxVerticalAcceleration</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener (ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener = _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateAutonomousFlightMaxRotationSpeed</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener (ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener = _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener _ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateBankedTurnChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateBankedTurnChangedListener (ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener _ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener = _ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMinAltitudeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateMinAltitudeChangedListener (ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener = _ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateCirclingDirectionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateCirclingDirectionChangedListener (ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener = _ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateCirclingRadiusChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateCirclingRadiusChangedListener (ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener = _ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateCirclingAltitudeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateCirclingAltitudeChangedListener (ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener _ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener = _ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener _ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStatePitchModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStatePitchModeChangedListener (ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener _ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener = _ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener_PARAM;
    }

    private ARCommandARDrone3PilotingSettingsStateMotionDetectionListener _ARCommandARDrone3PilotingSettingsStateMotionDetectionListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMotionDetection</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PilotingSettingsStateMotionDetectionListener (ARCommandARDrone3PilotingSettingsStateMotionDetectionListener _ARCommandARDrone3PilotingSettingsStateMotionDetectionListener_PARAM) {
        _ARCommandARDrone3PilotingSettingsStateMotionDetectionListener = _ARCommandARDrone3PilotingSettingsStateMotionDetectionListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener (ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener = _ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener (ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener = _ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener _ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateHullProtectionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsStateHullProtectionChangedListener (ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener _ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener = _ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener _ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateOutdoorChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsStateOutdoorChangedListener (ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener _ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener = _ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener_PARAM;
    }

    private ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxPitchRollRotationSpeedChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener (ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener _ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener_PARAM) {
        _ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener = _ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener _ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener;

    /**
     * Set the listener for the command <code>NetworkSettingsStateWifiSelectionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkSettingsStateWifiSelectionChangedListener (ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener _ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener_PARAM) {
        _ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener = _ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener _ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener;

    /**
     * Set the listener for the command <code>NetworkSettingsStateWifiSecurityChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkSettingsStateWifiSecurityChangedListener (ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener _ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener_PARAM) {
        _ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener = _ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener_PARAM;
    }

    private ARCommandARDrone3NetworkSettingsStateWifiSecurityListener _ARCommandARDrone3NetworkSettingsStateWifiSecurityListener;

    /**
     * Set the listener for the command <code>NetworkSettingsStateWifiSecurity</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3NetworkSettingsStateWifiSecurityListener (ARCommandARDrone3NetworkSettingsStateWifiSecurityListener _ARCommandARDrone3NetworkSettingsStateWifiSecurityListener_PARAM) {
        _ARCommandARDrone3NetworkSettingsStateWifiSecurityListener = _ARCommandARDrone3NetworkSettingsStateWifiSecurityListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener _ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductMotorVersionListChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateProductMotorVersionListChangedListener (ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener _ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener = _ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateProductGPSVersionChangedListener _ARCommandARDrone3SettingsStateProductGPSVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductGPSVersionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateProductGPSVersionChangedListener (ARCommandARDrone3SettingsStateProductGPSVersionChangedListener _ARCommandARDrone3SettingsStateProductGPSVersionChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateProductGPSVersionChangedListener = _ARCommandARDrone3SettingsStateProductGPSVersionChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateMotorErrorStateChangedListener _ARCommandARDrone3SettingsStateMotorErrorStateChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateMotorErrorStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateMotorErrorStateChangedListener (ARCommandARDrone3SettingsStateMotorErrorStateChangedListener _ARCommandARDrone3SettingsStateMotorErrorStateChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateMotorErrorStateChangedListener = _ARCommandARDrone3SettingsStateMotorErrorStateChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener _ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateMotorSoftwareVersionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateMotorSoftwareVersionChangedListener (ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener _ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener = _ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener _ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateMotorFlightsStatusChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateMotorFlightsStatusChangedListener (ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener _ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener = _ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener _ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateMotorErrorLastErrorChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateMotorErrorLastErrorChangedListener (ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener _ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener_PARAM) {
        _ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener = _ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateP7IDListener _ARCommandARDrone3SettingsStateP7IDListener;

    /**
     * Set the listener for the command <code>SettingsStateP7ID</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateP7IDListener (ARCommandARDrone3SettingsStateP7IDListener _ARCommandARDrone3SettingsStateP7IDListener_PARAM) {
        _ARCommandARDrone3SettingsStateP7IDListener = _ARCommandARDrone3SettingsStateP7IDListener_PARAM;
    }

    private ARCommandARDrone3SettingsStateCPUIDListener _ARCommandARDrone3SettingsStateCPUIDListener;

    /**
     * Set the listener for the command <code>SettingsStateCPUID</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SettingsStateCPUIDListener (ARCommandARDrone3SettingsStateCPUIDListener _ARCommandARDrone3SettingsStateCPUIDListener_PARAM) {
        _ARCommandARDrone3SettingsStateCPUIDListener = _ARCommandARDrone3SettingsStateCPUIDListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener _ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStatePictureFormatChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStatePictureFormatChangedListener (ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener _ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener = _ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener _ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateAutoWhiteBalanceChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener (ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener _ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener = _ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateExpositionChangedListener _ARCommandARDrone3PictureSettingsStateExpositionChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateExpositionChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateExpositionChangedListener (ARCommandARDrone3PictureSettingsStateExpositionChangedListener _ARCommandARDrone3PictureSettingsStateExpositionChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateExpositionChangedListener = _ARCommandARDrone3PictureSettingsStateExpositionChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateSaturationChangedListener _ARCommandARDrone3PictureSettingsStateSaturationChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateSaturationChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateSaturationChangedListener (ARCommandARDrone3PictureSettingsStateSaturationChangedListener _ARCommandARDrone3PictureSettingsStateSaturationChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateSaturationChangedListener = _ARCommandARDrone3PictureSettingsStateSaturationChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateTimelapseChangedListener _ARCommandARDrone3PictureSettingsStateTimelapseChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateTimelapseChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateTimelapseChangedListener (ARCommandARDrone3PictureSettingsStateTimelapseChangedListener _ARCommandARDrone3PictureSettingsStateTimelapseChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateTimelapseChangedListener = _ARCommandARDrone3PictureSettingsStateTimelapseChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener _ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateVideoAutorecordChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateVideoAutorecordChangedListener (ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener _ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener = _ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener _ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateVideoStabilizationModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateVideoStabilizationModeChangedListener (ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener _ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener = _ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener _ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateVideoRecordingModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateVideoRecordingModeChangedListener (ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener _ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener = _ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener _ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateVideoFramerateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateVideoFramerateChangedListener (ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener _ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener = _ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener_PARAM;
    }

    private ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener _ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener;

    /**
     * Set the listener for the command <code>PictureSettingsStateVideoResolutionsChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PictureSettingsStateVideoResolutionsChangedListener (ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener _ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener_PARAM) {
        _ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener = _ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener_PARAM;
    }

    private ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener _ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener;

    /**
     * Set the listener for the command <code>MediaStreamingStateVideoEnableChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaStreamingStateVideoEnableChangedListener (ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener _ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener_PARAM) {
        _ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener = _ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener_PARAM;
    }

    private ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener _ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener;

    /**
     * Set the listener for the command <code>MediaStreamingStateVideoStreamModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3MediaStreamingStateVideoStreamModeChangedListener (ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener _ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener_PARAM) {
        _ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener = _ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateHomeChangedListener _ARCommandARDrone3GPSSettingsStateHomeChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateHomeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateHomeChangedListener (ARCommandARDrone3GPSSettingsStateHomeChangedListener _ARCommandARDrone3GPSSettingsStateHomeChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateHomeChangedListener = _ARCommandARDrone3GPSSettingsStateHomeChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateResetHomeChangedListener _ARCommandARDrone3GPSSettingsStateResetHomeChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateResetHomeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateResetHomeChangedListener (ARCommandARDrone3GPSSettingsStateResetHomeChangedListener _ARCommandARDrone3GPSSettingsStateResetHomeChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateResetHomeChangedListener = _ARCommandARDrone3GPSSettingsStateResetHomeChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener _ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateGPSFixStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateGPSFixStateChangedListener (ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener _ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener = _ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener _ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateGPSUpdateStateChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateGPSUpdateStateChangedListener (ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener _ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener = _ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener _ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateHomeTypeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateHomeTypeChangedListener (ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener _ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener = _ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener _ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateReturnHomeDelayChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateReturnHomeDelayChangedListener (ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener _ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener = _ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener _ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener;

    /**
     * Set the listener for the command <code>GPSSettingsStateGeofenceCenterChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSSettingsStateGeofenceCenterChangedListener (ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener _ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener_PARAM) {
        _ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener = _ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener_PARAM;
    }

    private ARCommandARDrone3CameraStateOrientationListener _ARCommandARDrone3CameraStateOrientationListener;

    /**
     * Set the listener for the command <code>CameraStateOrientation</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraStateOrientationListener (ARCommandARDrone3CameraStateOrientationListener _ARCommandARDrone3CameraStateOrientationListener_PARAM) {
        _ARCommandARDrone3CameraStateOrientationListener = _ARCommandARDrone3CameraStateOrientationListener_PARAM;
    }

    private ARCommandARDrone3CameraStateDefaultCameraOrientationListener _ARCommandARDrone3CameraStateDefaultCameraOrientationListener;

    /**
     * Set the listener for the command <code>CameraStateDefaultCameraOrientation</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraStateDefaultCameraOrientationListener (ARCommandARDrone3CameraStateDefaultCameraOrientationListener _ARCommandARDrone3CameraStateDefaultCameraOrientationListener_PARAM) {
        _ARCommandARDrone3CameraStateDefaultCameraOrientationListener = _ARCommandARDrone3CameraStateDefaultCameraOrientationListener_PARAM;
    }

    private ARCommandARDrone3CameraStateOrientationV2Listener _ARCommandARDrone3CameraStateOrientationV2Listener;

    /**
     * Set the listener for the command <code>CameraStateOrientationV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraStateOrientationV2Listener (ARCommandARDrone3CameraStateOrientationV2Listener _ARCommandARDrone3CameraStateOrientationV2Listener_PARAM) {
        _ARCommandARDrone3CameraStateOrientationV2Listener = _ARCommandARDrone3CameraStateOrientationV2Listener_PARAM;
    }

    private ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener _ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener;

    /**
     * Set the listener for the command <code>CameraStateDefaultCameraOrientationV2</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraStateDefaultCameraOrientationV2Listener (ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener _ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener_PARAM) {
        _ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener = _ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener_PARAM;
    }

    private ARCommandARDrone3CameraStateVelocityRangeListener _ARCommandARDrone3CameraStateVelocityRangeListener;

    /**
     * Set the listener for the command <code>CameraStateVelocityRange</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3CameraStateVelocityRangeListener (ARCommandARDrone3CameraStateVelocityRangeListener _ARCommandARDrone3CameraStateVelocityRangeListener_PARAM) {
        _ARCommandARDrone3CameraStateVelocityRangeListener = _ARCommandARDrone3CameraStateVelocityRangeListener_PARAM;
    }

    private ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener _ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener;

    /**
     * Set the listener for the command <code>AntiflickeringStateElectricFrequencyChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AntiflickeringStateElectricFrequencyChangedListener (ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener _ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener_PARAM) {
        _ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener = _ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener_PARAM;
    }

    private ARCommandARDrone3AntiflickeringStateModeChangedListener _ARCommandARDrone3AntiflickeringStateModeChangedListener;

    /**
     * Set the listener for the command <code>AntiflickeringStateModeChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AntiflickeringStateModeChangedListener (ARCommandARDrone3AntiflickeringStateModeChangedListener _ARCommandARDrone3AntiflickeringStateModeChangedListener_PARAM) {
        _ARCommandARDrone3AntiflickeringStateModeChangedListener = _ARCommandARDrone3AntiflickeringStateModeChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener _ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener;

    /**
     * Set the listener for the command <code>GPSStateNumberOfSatelliteChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSStateNumberOfSatelliteChangedListener (ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener _ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener_PARAM) {
        _ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener = _ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener _ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener;

    /**
     * Set the listener for the command <code>GPSStateHomeTypeAvailabilityChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSStateHomeTypeAvailabilityChangedListener (ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener _ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener_PARAM) {
        _ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener = _ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener_PARAM;
    }

    private ARCommandARDrone3GPSStateHomeTypeChosenChangedListener _ARCommandARDrone3GPSStateHomeTypeChosenChangedListener;

    /**
     * Set the listener for the command <code>GPSStateHomeTypeChosenChanged</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3GPSStateHomeTypeChosenChangedListener (ARCommandARDrone3GPSStateHomeTypeChosenChangedListener _ARCommandARDrone3GPSStateHomeTypeChosenChangedListener_PARAM) {
        _ARCommandARDrone3GPSStateHomeTypeChosenChangedListener = _ARCommandARDrone3GPSStateHomeTypeChosenChangedListener_PARAM;
    }

    private ARCommandARDrone3PROStateFeaturesListener _ARCommandARDrone3PROStateFeaturesListener;

    /**
     * Set the listener for the command <code>PROStateFeatures</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3PROStateFeaturesListener (ARCommandARDrone3PROStateFeaturesListener _ARCommandARDrone3PROStateFeaturesListener_PARAM) {
        _ARCommandARDrone3PROStateFeaturesListener = _ARCommandARDrone3PROStateFeaturesListener_PARAM;
    }

    private ARCommandARDrone3AccessoryStateConnectedAccessoriesListener _ARCommandARDrone3AccessoryStateConnectedAccessoriesListener;

    /**
     * Set the listener for the command <code>AccessoryStateConnectedAccessories</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AccessoryStateConnectedAccessoriesListener (ARCommandARDrone3AccessoryStateConnectedAccessoriesListener _ARCommandARDrone3AccessoryStateConnectedAccessoriesListener_PARAM) {
        _ARCommandARDrone3AccessoryStateConnectedAccessoriesListener = _ARCommandARDrone3AccessoryStateConnectedAccessoriesListener_PARAM;
    }

    private ARCommandARDrone3AccessoryStateBatteryListener _ARCommandARDrone3AccessoryStateBatteryListener;

    /**
     * Set the listener for the command <code>AccessoryStateBattery</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3AccessoryStateBatteryListener (ARCommandARDrone3AccessoryStateBatteryListener _ARCommandARDrone3AccessoryStateBatteryListener_PARAM) {
        _ARCommandARDrone3AccessoryStateBatteryListener = _ARCommandARDrone3AccessoryStateBatteryListener_PARAM;
    }

    private ARCommandARDrone3SoundStateAlertSoundListener _ARCommandARDrone3SoundStateAlertSoundListener;

    /**
     * Set the listener for the command <code>SoundStateAlertSound</code> in feature <code>ARDrone3</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setARDrone3SoundStateAlertSoundListener (ARCommandARDrone3SoundStateAlertSoundListener _ARCommandARDrone3SoundStateAlertSoundListener_PARAM) {
        _ARCommandARDrone3SoundStateAlertSoundListener = _ARCommandARDrone3SoundStateAlertSoundListener_PARAM;
    }


    private ARCommandCommonNetworkDisconnectListener _ARCommandCommonNetworkDisconnectListener;

    /**
     * Set the listener for the command <code>NetworkDisconnect</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonNetworkDisconnectListener (ARCommandCommonNetworkDisconnectListener _ARCommandCommonNetworkDisconnectListener_PARAM) {
        _ARCommandCommonNetworkDisconnectListener = _ARCommandCommonNetworkDisconnectListener_PARAM;
    }

    private ARCommandCommonSettingsAllSettingsListener _ARCommandCommonSettingsAllSettingsListener;

    /**
     * Set the listener for the command <code>SettingsAllSettings</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsAllSettingsListener (ARCommandCommonSettingsAllSettingsListener _ARCommandCommonSettingsAllSettingsListener_PARAM) {
        _ARCommandCommonSettingsAllSettingsListener = _ARCommandCommonSettingsAllSettingsListener_PARAM;
    }

    private ARCommandCommonSettingsResetListener _ARCommandCommonSettingsResetListener;

    /**
     * Set the listener for the command <code>SettingsReset</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsResetListener (ARCommandCommonSettingsResetListener _ARCommandCommonSettingsResetListener_PARAM) {
        _ARCommandCommonSettingsResetListener = _ARCommandCommonSettingsResetListener_PARAM;
    }

    private ARCommandCommonSettingsProductNameListener _ARCommandCommonSettingsProductNameListener;

    /**
     * Set the listener for the command <code>SettingsProductName</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsProductNameListener (ARCommandCommonSettingsProductNameListener _ARCommandCommonSettingsProductNameListener_PARAM) {
        _ARCommandCommonSettingsProductNameListener = _ARCommandCommonSettingsProductNameListener_PARAM;
    }

    private ARCommandCommonSettingsCountryListener _ARCommandCommonSettingsCountryListener;

    /**
     * Set the listener for the command <code>SettingsCountry</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsCountryListener (ARCommandCommonSettingsCountryListener _ARCommandCommonSettingsCountryListener_PARAM) {
        _ARCommandCommonSettingsCountryListener = _ARCommandCommonSettingsCountryListener_PARAM;
    }

    private ARCommandCommonSettingsAutoCountryListener _ARCommandCommonSettingsAutoCountryListener;

    /**
     * Set the listener for the command <code>SettingsAutoCountry</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsAutoCountryListener (ARCommandCommonSettingsAutoCountryListener _ARCommandCommonSettingsAutoCountryListener_PARAM) {
        _ARCommandCommonSettingsAutoCountryListener = _ARCommandCommonSettingsAutoCountryListener_PARAM;
    }

    private ARCommandCommonCommonAllStatesListener _ARCommandCommonCommonAllStatesListener;

    /**
     * Set the listener for the command <code>CommonAllStates</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonAllStatesListener (ARCommandCommonCommonAllStatesListener _ARCommandCommonCommonAllStatesListener_PARAM) {
        _ARCommandCommonCommonAllStatesListener = _ARCommandCommonCommonAllStatesListener_PARAM;
    }

    private ARCommandCommonCommonCurrentDateListener _ARCommandCommonCommonCurrentDateListener;

    /**
     * Set the listener for the command <code>CommonCurrentDate</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonCurrentDateListener (ARCommandCommonCommonCurrentDateListener _ARCommandCommonCommonCurrentDateListener_PARAM) {
        _ARCommandCommonCommonCurrentDateListener = _ARCommandCommonCommonCurrentDateListener_PARAM;
    }

    private ARCommandCommonCommonCurrentTimeListener _ARCommandCommonCommonCurrentTimeListener;

    /**
     * Set the listener for the command <code>CommonCurrentTime</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonCurrentTimeListener (ARCommandCommonCommonCurrentTimeListener _ARCommandCommonCommonCurrentTimeListener_PARAM) {
        _ARCommandCommonCommonCurrentTimeListener = _ARCommandCommonCommonCurrentTimeListener_PARAM;
    }

    private ARCommandCommonCommonRebootListener _ARCommandCommonCommonRebootListener;

    /**
     * Set the listener for the command <code>CommonReboot</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonRebootListener (ARCommandCommonCommonRebootListener _ARCommandCommonCommonRebootListener_PARAM) {
        _ARCommandCommonCommonRebootListener = _ARCommandCommonCommonRebootListener_PARAM;
    }

    private ARCommandCommonOverHeatSwitchOffListener _ARCommandCommonOverHeatSwitchOffListener;

    /**
     * Set the listener for the command <code>OverHeatSwitchOff</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonOverHeatSwitchOffListener (ARCommandCommonOverHeatSwitchOffListener _ARCommandCommonOverHeatSwitchOffListener_PARAM) {
        _ARCommandCommonOverHeatSwitchOffListener = _ARCommandCommonOverHeatSwitchOffListener_PARAM;
    }

    private ARCommandCommonOverHeatVentilateListener _ARCommandCommonOverHeatVentilateListener;

    /**
     * Set the listener for the command <code>OverHeatVentilate</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonOverHeatVentilateListener (ARCommandCommonOverHeatVentilateListener _ARCommandCommonOverHeatVentilateListener_PARAM) {
        _ARCommandCommonOverHeatVentilateListener = _ARCommandCommonOverHeatVentilateListener_PARAM;
    }

    private ARCommandCommonControllerIsPilotingListener _ARCommandCommonControllerIsPilotingListener;

    /**
     * Set the listener for the command <code>ControllerIsPiloting</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonControllerIsPilotingListener (ARCommandCommonControllerIsPilotingListener _ARCommandCommonControllerIsPilotingListener_PARAM) {
        _ARCommandCommonControllerIsPilotingListener = _ARCommandCommonControllerIsPilotingListener_PARAM;
    }

    private ARCommandCommonWifiSettingsOutdoorSettingListener _ARCommandCommonWifiSettingsOutdoorSettingListener;

    /**
     * Set the listener for the command <code>WifiSettingsOutdoorSetting</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonWifiSettingsOutdoorSettingListener (ARCommandCommonWifiSettingsOutdoorSettingListener _ARCommandCommonWifiSettingsOutdoorSettingListener_PARAM) {
        _ARCommandCommonWifiSettingsOutdoorSettingListener = _ARCommandCommonWifiSettingsOutdoorSettingListener_PARAM;
    }

    private ARCommandCommonMavlinkStartListener _ARCommandCommonMavlinkStartListener;

    /**
     * Set the listener for the command <code>MavlinkStart</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkStartListener (ARCommandCommonMavlinkStartListener _ARCommandCommonMavlinkStartListener_PARAM) {
        _ARCommandCommonMavlinkStartListener = _ARCommandCommonMavlinkStartListener_PARAM;
    }

    private ARCommandCommonMavlinkPauseListener _ARCommandCommonMavlinkPauseListener;

    /**
     * Set the listener for the command <code>MavlinkPause</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkPauseListener (ARCommandCommonMavlinkPauseListener _ARCommandCommonMavlinkPauseListener_PARAM) {
        _ARCommandCommonMavlinkPauseListener = _ARCommandCommonMavlinkPauseListener_PARAM;
    }

    private ARCommandCommonMavlinkStopListener _ARCommandCommonMavlinkStopListener;

    /**
     * Set the listener for the command <code>MavlinkStop</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkStopListener (ARCommandCommonMavlinkStopListener _ARCommandCommonMavlinkStopListener_PARAM) {
        _ARCommandCommonMavlinkStopListener = _ARCommandCommonMavlinkStopListener_PARAM;
    }

    private ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener _ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener;

    /**
     * Set the listener for the command <code>FlightPlanSettingsReturnHomeOnDisconnect</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanSettingsReturnHomeOnDisconnectListener (ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener _ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener_PARAM) {
        _ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener = _ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener_PARAM;
    }

    private ARCommandCommonCalibrationMagnetoCalibrationListener _ARCommandCommonCalibrationMagnetoCalibrationListener;

    /**
     * Set the listener for the command <code>CalibrationMagnetoCalibration</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationMagnetoCalibrationListener (ARCommandCommonCalibrationMagnetoCalibrationListener _ARCommandCommonCalibrationMagnetoCalibrationListener_PARAM) {
        _ARCommandCommonCalibrationMagnetoCalibrationListener = _ARCommandCommonCalibrationMagnetoCalibrationListener_PARAM;
    }

    private ARCommandCommonCalibrationPitotCalibrationListener _ARCommandCommonCalibrationPitotCalibrationListener;

    /**
     * Set the listener for the command <code>CalibrationPitotCalibration</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationPitotCalibrationListener (ARCommandCommonCalibrationPitotCalibrationListener _ARCommandCommonCalibrationPitotCalibrationListener_PARAM) {
        _ARCommandCommonCalibrationPitotCalibrationListener = _ARCommandCommonCalibrationPitotCalibrationListener_PARAM;
    }

    private ARCommandCommonGPSControllerPositionForRunListener _ARCommandCommonGPSControllerPositionForRunListener;

    /**
     * Set the listener for the command <code>GPSControllerPositionForRun</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonGPSControllerPositionForRunListener (ARCommandCommonGPSControllerPositionForRunListener _ARCommandCommonGPSControllerPositionForRunListener_PARAM) {
        _ARCommandCommonGPSControllerPositionForRunListener = _ARCommandCommonGPSControllerPositionForRunListener_PARAM;
    }

    private ARCommandCommonAudioControllerReadyForStreamingListener _ARCommandCommonAudioControllerReadyForStreamingListener;

    /**
     * Set the listener for the command <code>AudioControllerReadyForStreaming</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAudioControllerReadyForStreamingListener (ARCommandCommonAudioControllerReadyForStreamingListener _ARCommandCommonAudioControllerReadyForStreamingListener_PARAM) {
        _ARCommandCommonAudioControllerReadyForStreamingListener = _ARCommandCommonAudioControllerReadyForStreamingListener_PARAM;
    }

    private ARCommandCommonHeadlightsIntensityListener _ARCommandCommonHeadlightsIntensityListener;

    /**
     * Set the listener for the command <code>HeadlightsIntensity</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonHeadlightsIntensityListener (ARCommandCommonHeadlightsIntensityListener _ARCommandCommonHeadlightsIntensityListener_PARAM) {
        _ARCommandCommonHeadlightsIntensityListener = _ARCommandCommonHeadlightsIntensityListener_PARAM;
    }

    private ARCommandCommonAnimationsStartAnimationListener _ARCommandCommonAnimationsStartAnimationListener;

    /**
     * Set the listener for the command <code>AnimationsStartAnimation</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAnimationsStartAnimationListener (ARCommandCommonAnimationsStartAnimationListener _ARCommandCommonAnimationsStartAnimationListener_PARAM) {
        _ARCommandCommonAnimationsStartAnimationListener = _ARCommandCommonAnimationsStartAnimationListener_PARAM;
    }

    private ARCommandCommonAnimationsStopAnimationListener _ARCommandCommonAnimationsStopAnimationListener;

    /**
     * Set the listener for the command <code>AnimationsStopAnimation</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAnimationsStopAnimationListener (ARCommandCommonAnimationsStopAnimationListener _ARCommandCommonAnimationsStopAnimationListener_PARAM) {
        _ARCommandCommonAnimationsStopAnimationListener = _ARCommandCommonAnimationsStopAnimationListener_PARAM;
    }

    private ARCommandCommonAnimationsStopAllAnimationsListener _ARCommandCommonAnimationsStopAllAnimationsListener;

    /**
     * Set the listener for the command <code>AnimationsStopAllAnimations</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAnimationsStopAllAnimationsListener (ARCommandCommonAnimationsStopAllAnimationsListener _ARCommandCommonAnimationsStopAllAnimationsListener_PARAM) {
        _ARCommandCommonAnimationsStopAllAnimationsListener = _ARCommandCommonAnimationsStopAllAnimationsListener_PARAM;
    }

    private ARCommandCommonAccessoryConfigListener _ARCommandCommonAccessoryConfigListener;

    /**
     * Set the listener for the command <code>AccessoryConfig</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAccessoryConfigListener (ARCommandCommonAccessoryConfigListener _ARCommandCommonAccessoryConfigListener_PARAM) {
        _ARCommandCommonAccessoryConfigListener = _ARCommandCommonAccessoryConfigListener_PARAM;
    }

    private ARCommandCommonChargerSetMaxChargeRateListener _ARCommandCommonChargerSetMaxChargeRateListener;

    /**
     * Set the listener for the command <code>ChargerSetMaxChargeRate</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonChargerSetMaxChargeRateListener (ARCommandCommonChargerSetMaxChargeRateListener _ARCommandCommonChargerSetMaxChargeRateListener_PARAM) {
        _ARCommandCommonChargerSetMaxChargeRateListener = _ARCommandCommonChargerSetMaxChargeRateListener_PARAM;
    }

    private ARCommandCommonFactoryResetListener _ARCommandCommonFactoryResetListener;

    /**
     * Set the listener for the command <code>FactoryReset</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFactoryResetListener (ARCommandCommonFactoryResetListener _ARCommandCommonFactoryResetListener_PARAM) {
        _ARCommandCommonFactoryResetListener = _ARCommandCommonFactoryResetListener_PARAM;
    }

    private ARCommandCommonNetworkEventDisconnectionListener _ARCommandCommonNetworkEventDisconnectionListener;

    /**
     * Set the listener for the command <code>NetworkEventDisconnection</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonNetworkEventDisconnectionListener (ARCommandCommonNetworkEventDisconnectionListener _ARCommandCommonNetworkEventDisconnectionListener_PARAM) {
        _ARCommandCommonNetworkEventDisconnectionListener = _ARCommandCommonNetworkEventDisconnectionListener_PARAM;
    }

    private ARCommandCommonSettingsStateAllSettingsChangedListener _ARCommandCommonSettingsStateAllSettingsChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateAllSettingsChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateAllSettingsChangedListener (ARCommandCommonSettingsStateAllSettingsChangedListener _ARCommandCommonSettingsStateAllSettingsChangedListener_PARAM) {
        _ARCommandCommonSettingsStateAllSettingsChangedListener = _ARCommandCommonSettingsStateAllSettingsChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateResetChangedListener _ARCommandCommonSettingsStateResetChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateResetChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateResetChangedListener (ARCommandCommonSettingsStateResetChangedListener _ARCommandCommonSettingsStateResetChangedListener_PARAM) {
        _ARCommandCommonSettingsStateResetChangedListener = _ARCommandCommonSettingsStateResetChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateProductNameChangedListener _ARCommandCommonSettingsStateProductNameChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductNameChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateProductNameChangedListener (ARCommandCommonSettingsStateProductNameChangedListener _ARCommandCommonSettingsStateProductNameChangedListener_PARAM) {
        _ARCommandCommonSettingsStateProductNameChangedListener = _ARCommandCommonSettingsStateProductNameChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateProductVersionChangedListener _ARCommandCommonSettingsStateProductVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductVersionChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateProductVersionChangedListener (ARCommandCommonSettingsStateProductVersionChangedListener _ARCommandCommonSettingsStateProductVersionChangedListener_PARAM) {
        _ARCommandCommonSettingsStateProductVersionChangedListener = _ARCommandCommonSettingsStateProductVersionChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateProductSerialHighChangedListener _ARCommandCommonSettingsStateProductSerialHighChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductSerialHighChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateProductSerialHighChangedListener (ARCommandCommonSettingsStateProductSerialHighChangedListener _ARCommandCommonSettingsStateProductSerialHighChangedListener_PARAM) {
        _ARCommandCommonSettingsStateProductSerialHighChangedListener = _ARCommandCommonSettingsStateProductSerialHighChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateProductSerialLowChangedListener _ARCommandCommonSettingsStateProductSerialLowChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductSerialLowChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateProductSerialLowChangedListener (ARCommandCommonSettingsStateProductSerialLowChangedListener _ARCommandCommonSettingsStateProductSerialLowChangedListener_PARAM) {
        _ARCommandCommonSettingsStateProductSerialLowChangedListener = _ARCommandCommonSettingsStateProductSerialLowChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateCountryChangedListener _ARCommandCommonSettingsStateCountryChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateCountryChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateCountryChangedListener (ARCommandCommonSettingsStateCountryChangedListener _ARCommandCommonSettingsStateCountryChangedListener_PARAM) {
        _ARCommandCommonSettingsStateCountryChangedListener = _ARCommandCommonSettingsStateCountryChangedListener_PARAM;
    }

    private ARCommandCommonSettingsStateAutoCountryChangedListener _ARCommandCommonSettingsStateAutoCountryChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateAutoCountryChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonSettingsStateAutoCountryChangedListener (ARCommandCommonSettingsStateAutoCountryChangedListener _ARCommandCommonSettingsStateAutoCountryChangedListener_PARAM) {
        _ARCommandCommonSettingsStateAutoCountryChangedListener = _ARCommandCommonSettingsStateAutoCountryChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateAllStatesChangedListener _ARCommandCommonCommonStateAllStatesChangedListener;

    /**
     * Set the listener for the command <code>CommonStateAllStatesChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateAllStatesChangedListener (ARCommandCommonCommonStateAllStatesChangedListener _ARCommandCommonCommonStateAllStatesChangedListener_PARAM) {
        _ARCommandCommonCommonStateAllStatesChangedListener = _ARCommandCommonCommonStateAllStatesChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateBatteryStateChangedListener _ARCommandCommonCommonStateBatteryStateChangedListener;

    /**
     * Set the listener for the command <code>CommonStateBatteryStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateBatteryStateChangedListener (ARCommandCommonCommonStateBatteryStateChangedListener _ARCommandCommonCommonStateBatteryStateChangedListener_PARAM) {
        _ARCommandCommonCommonStateBatteryStateChangedListener = _ARCommandCommonCommonStateBatteryStateChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateMassStorageStateListChangedListener _ARCommandCommonCommonStateMassStorageStateListChangedListener;

    /**
     * Set the listener for the command <code>CommonStateMassStorageStateListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateMassStorageStateListChangedListener (ARCommandCommonCommonStateMassStorageStateListChangedListener _ARCommandCommonCommonStateMassStorageStateListChangedListener_PARAM) {
        _ARCommandCommonCommonStateMassStorageStateListChangedListener = _ARCommandCommonCommonStateMassStorageStateListChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateMassStorageInfoStateListChangedListener _ARCommandCommonCommonStateMassStorageInfoStateListChangedListener;

    /**
     * Set the listener for the command <code>CommonStateMassStorageInfoStateListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateMassStorageInfoStateListChangedListener (ARCommandCommonCommonStateMassStorageInfoStateListChangedListener _ARCommandCommonCommonStateMassStorageInfoStateListChangedListener_PARAM) {
        _ARCommandCommonCommonStateMassStorageInfoStateListChangedListener = _ARCommandCommonCommonStateMassStorageInfoStateListChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateCurrentDateChangedListener _ARCommandCommonCommonStateCurrentDateChangedListener;

    /**
     * Set the listener for the command <code>CommonStateCurrentDateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateCurrentDateChangedListener (ARCommandCommonCommonStateCurrentDateChangedListener _ARCommandCommonCommonStateCurrentDateChangedListener_PARAM) {
        _ARCommandCommonCommonStateCurrentDateChangedListener = _ARCommandCommonCommonStateCurrentDateChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateCurrentTimeChangedListener _ARCommandCommonCommonStateCurrentTimeChangedListener;

    /**
     * Set the listener for the command <code>CommonStateCurrentTimeChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateCurrentTimeChangedListener (ARCommandCommonCommonStateCurrentTimeChangedListener _ARCommandCommonCommonStateCurrentTimeChangedListener_PARAM) {
        _ARCommandCommonCommonStateCurrentTimeChangedListener = _ARCommandCommonCommonStateCurrentTimeChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener _ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener;

    /**
     * Set the listener for the command <code>CommonStateMassStorageInfoRemainingListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateMassStorageInfoRemainingListChangedListener (ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener _ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener_PARAM) {
        _ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener = _ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateWifiSignalChangedListener _ARCommandCommonCommonStateWifiSignalChangedListener;

    /**
     * Set the listener for the command <code>CommonStateWifiSignalChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateWifiSignalChangedListener (ARCommandCommonCommonStateWifiSignalChangedListener _ARCommandCommonCommonStateWifiSignalChangedListener_PARAM) {
        _ARCommandCommonCommonStateWifiSignalChangedListener = _ARCommandCommonCommonStateWifiSignalChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateSensorsStatesListChangedListener _ARCommandCommonCommonStateSensorsStatesListChangedListener;

    /**
     * Set the listener for the command <code>CommonStateSensorsStatesListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateSensorsStatesListChangedListener (ARCommandCommonCommonStateSensorsStatesListChangedListener _ARCommandCommonCommonStateSensorsStatesListChangedListener_PARAM) {
        _ARCommandCommonCommonStateSensorsStatesListChangedListener = _ARCommandCommonCommonStateSensorsStatesListChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateProductModelListener _ARCommandCommonCommonStateProductModelListener;

    /**
     * Set the listener for the command <code>CommonStateProductModel</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateProductModelListener (ARCommandCommonCommonStateProductModelListener _ARCommandCommonCommonStateProductModelListener_PARAM) {
        _ARCommandCommonCommonStateProductModelListener = _ARCommandCommonCommonStateProductModelListener_PARAM;
    }

    private ARCommandCommonCommonStateCountryListKnownListener _ARCommandCommonCommonStateCountryListKnownListener;

    /**
     * Set the listener for the command <code>CommonStateCountryListKnown</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateCountryListKnownListener (ARCommandCommonCommonStateCountryListKnownListener _ARCommandCommonCommonStateCountryListKnownListener_PARAM) {
        _ARCommandCommonCommonStateCountryListKnownListener = _ARCommandCommonCommonStateCountryListKnownListener_PARAM;
    }

    private ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener _ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener;

    /**
     * Set the listener for the command <code>CommonStateDeprecatedMassStorageContentChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateDeprecatedMassStorageContentChangedListener (ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener _ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener_PARAM) {
        _ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener = _ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener_PARAM;
    }

    private ARCommandCommonCommonStateMassStorageContentListener _ARCommandCommonCommonStateMassStorageContentListener;

    /**
     * Set the listener for the command <code>CommonStateMassStorageContent</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateMassStorageContentListener (ARCommandCommonCommonStateMassStorageContentListener _ARCommandCommonCommonStateMassStorageContentListener_PARAM) {
        _ARCommandCommonCommonStateMassStorageContentListener = _ARCommandCommonCommonStateMassStorageContentListener_PARAM;
    }

    private ARCommandCommonCommonStateMassStorageContentForCurrentRunListener _ARCommandCommonCommonStateMassStorageContentForCurrentRunListener;

    /**
     * Set the listener for the command <code>CommonStateMassStorageContentForCurrentRun</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateMassStorageContentForCurrentRunListener (ARCommandCommonCommonStateMassStorageContentForCurrentRunListener _ARCommandCommonCommonStateMassStorageContentForCurrentRunListener_PARAM) {
        _ARCommandCommonCommonStateMassStorageContentForCurrentRunListener = _ARCommandCommonCommonStateMassStorageContentForCurrentRunListener_PARAM;
    }

    private ARCommandCommonCommonStateVideoRecordingTimestampListener _ARCommandCommonCommonStateVideoRecordingTimestampListener;

    /**
     * Set the listener for the command <code>CommonStateVideoRecordingTimestamp</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCommonStateVideoRecordingTimestampListener (ARCommandCommonCommonStateVideoRecordingTimestampListener _ARCommandCommonCommonStateVideoRecordingTimestampListener_PARAM) {
        _ARCommandCommonCommonStateVideoRecordingTimestampListener = _ARCommandCommonCommonStateVideoRecordingTimestampListener_PARAM;
    }

    private ARCommandCommonOverHeatStateOverHeatChangedListener _ARCommandCommonOverHeatStateOverHeatChangedListener;

    /**
     * Set the listener for the command <code>OverHeatStateOverHeatChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonOverHeatStateOverHeatChangedListener (ARCommandCommonOverHeatStateOverHeatChangedListener _ARCommandCommonOverHeatStateOverHeatChangedListener_PARAM) {
        _ARCommandCommonOverHeatStateOverHeatChangedListener = _ARCommandCommonOverHeatStateOverHeatChangedListener_PARAM;
    }

    private ARCommandCommonOverHeatStateOverHeatRegulationChangedListener _ARCommandCommonOverHeatStateOverHeatRegulationChangedListener;

    /**
     * Set the listener for the command <code>OverHeatStateOverHeatRegulationChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonOverHeatStateOverHeatRegulationChangedListener (ARCommandCommonOverHeatStateOverHeatRegulationChangedListener _ARCommandCommonOverHeatStateOverHeatRegulationChangedListener_PARAM) {
        _ARCommandCommonOverHeatStateOverHeatRegulationChangedListener = _ARCommandCommonOverHeatStateOverHeatRegulationChangedListener_PARAM;
    }

    private ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener _ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener;

    /**
     * Set the listener for the command <code>WifiSettingsStateOutdoorSettingsChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonWifiSettingsStateOutdoorSettingsChangedListener (ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener _ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener_PARAM) {
        _ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener = _ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener_PARAM;
    }

    private ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener _ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener;

    /**
     * Set the listener for the command <code>MavlinkStateMavlinkFilePlayingStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkStateMavlinkFilePlayingStateChangedListener (ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener _ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener_PARAM) {
        _ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener = _ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener_PARAM;
    }

    private ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener _ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener;

    /**
     * Set the listener for the command <code>MavlinkStateMavlinkPlayErrorStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkStateMavlinkPlayErrorStateChangedListener (ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener _ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener_PARAM) {
        _ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener = _ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener_PARAM;
    }

    private ARCommandCommonMavlinkStateMissionItemExecutedListener _ARCommandCommonMavlinkStateMissionItemExecutedListener;

    /**
     * Set the listener for the command <code>MavlinkStateMissionItemExecuted</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonMavlinkStateMissionItemExecutedListener (ARCommandCommonMavlinkStateMissionItemExecutedListener _ARCommandCommonMavlinkStateMissionItemExecutedListener_PARAM) {
        _ARCommandCommonMavlinkStateMissionItemExecutedListener = _ARCommandCommonMavlinkStateMissionItemExecutedListener_PARAM;
    }

    private ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener _ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener;

    /**
     * Set the listener for the command <code>FlightPlanSettingsStateReturnHomeOnDisconnectChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener (ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener _ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener_PARAM) {
        _ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener = _ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener_PARAM;
    }

    private ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationStateMagnetoCalibrationStateChangedListener (ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener_PARAM) {
        _ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener = _ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener_PARAM;
    }

    private ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener _ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationRequiredState</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationStateMagnetoCalibrationRequiredStateListener (ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener _ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener_PARAM) {
        _ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener = _ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener_PARAM;
    }

    private ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationAxisToCalibrateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener (ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener_PARAM) {
        _ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener = _ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener_PARAM;
    }

    private ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationStartedChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationStateMagnetoCalibrationStartedChangedListener (ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener _ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener_PARAM) {
        _ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener = _ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener_PARAM;
    }

    private ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener _ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener;

    /**
     * Set the listener for the command <code>CalibrationStatePitotCalibrationStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCalibrationStatePitotCalibrationStateChangedListener (ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener _ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener_PARAM) {
        _ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener = _ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener_PARAM;
    }

    private ARCommandCommonCameraSettingsStateCameraSettingsChangedListener _ARCommandCommonCameraSettingsStateCameraSettingsChangedListener;

    /**
     * Set the listener for the command <code>CameraSettingsStateCameraSettingsChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonCameraSettingsStateCameraSettingsChangedListener (ARCommandCommonCameraSettingsStateCameraSettingsChangedListener _ARCommandCommonCameraSettingsStateCameraSettingsChangedListener_PARAM) {
        _ARCommandCommonCameraSettingsStateCameraSettingsChangedListener = _ARCommandCommonCameraSettingsStateCameraSettingsChangedListener_PARAM;
    }

    private ARCommandCommonFlightPlanStateAvailabilityStateChangedListener _ARCommandCommonFlightPlanStateAvailabilityStateChangedListener;

    /**
     * Set the listener for the command <code>FlightPlanStateAvailabilityStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanStateAvailabilityStateChangedListener (ARCommandCommonFlightPlanStateAvailabilityStateChangedListener _ARCommandCommonFlightPlanStateAvailabilityStateChangedListener_PARAM) {
        _ARCommandCommonFlightPlanStateAvailabilityStateChangedListener = _ARCommandCommonFlightPlanStateAvailabilityStateChangedListener_PARAM;
    }

    private ARCommandCommonFlightPlanStateComponentStateListChangedListener _ARCommandCommonFlightPlanStateComponentStateListChangedListener;

    /**
     * Set the listener for the command <code>FlightPlanStateComponentStateListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanStateComponentStateListChangedListener (ARCommandCommonFlightPlanStateComponentStateListChangedListener _ARCommandCommonFlightPlanStateComponentStateListChangedListener_PARAM) {
        _ARCommandCommonFlightPlanStateComponentStateListChangedListener = _ARCommandCommonFlightPlanStateComponentStateListChangedListener_PARAM;
    }

    private ARCommandCommonFlightPlanStateLockStateChangedListener _ARCommandCommonFlightPlanStateLockStateChangedListener;

    /**
     * Set the listener for the command <code>FlightPlanStateLockStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanStateLockStateChangedListener (ARCommandCommonFlightPlanStateLockStateChangedListener _ARCommandCommonFlightPlanStateLockStateChangedListener_PARAM) {
        _ARCommandCommonFlightPlanStateLockStateChangedListener = _ARCommandCommonFlightPlanStateLockStateChangedListener_PARAM;
    }

    private ARCommandCommonFlightPlanEventStartingErrorEventListener _ARCommandCommonFlightPlanEventStartingErrorEventListener;

    /**
     * Set the listener for the command <code>FlightPlanEventStartingErrorEvent</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanEventStartingErrorEventListener (ARCommandCommonFlightPlanEventStartingErrorEventListener _ARCommandCommonFlightPlanEventStartingErrorEventListener_PARAM) {
        _ARCommandCommonFlightPlanEventStartingErrorEventListener = _ARCommandCommonFlightPlanEventStartingErrorEventListener_PARAM;
    }

    private ARCommandCommonFlightPlanEventSpeedBridleEventListener _ARCommandCommonFlightPlanEventSpeedBridleEventListener;

    /**
     * Set the listener for the command <code>FlightPlanEventSpeedBridleEvent</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonFlightPlanEventSpeedBridleEventListener (ARCommandCommonFlightPlanEventSpeedBridleEventListener _ARCommandCommonFlightPlanEventSpeedBridleEventListener_PARAM) {
        _ARCommandCommonFlightPlanEventSpeedBridleEventListener = _ARCommandCommonFlightPlanEventSpeedBridleEventListener_PARAM;
    }

    private ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener;

    /**
     * Set the listener for the command <code>ARLibsVersionsStateControllerLibARCommandsVersion</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonARLibsVersionsStateControllerLibARCommandsVersionListener (ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener_PARAM) {
        _ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener = _ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener_PARAM;
    }

    private ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener;

    /**
     * Set the listener for the command <code>ARLibsVersionsStateSkyControllerLibARCommandsVersion</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener (ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener_PARAM) {
        _ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener = _ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener_PARAM;
    }

    private ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener;

    /**
     * Set the listener for the command <code>ARLibsVersionsStateDeviceLibARCommandsVersion</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonARLibsVersionsStateDeviceLibARCommandsVersionListener (ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener _ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener_PARAM) {
        _ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener = _ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener_PARAM;
    }

    private ARCommandCommonAudioStateAudioStreamingRunningListener _ARCommandCommonAudioStateAudioStreamingRunningListener;

    /**
     * Set the listener for the command <code>AudioStateAudioStreamingRunning</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAudioStateAudioStreamingRunningListener (ARCommandCommonAudioStateAudioStreamingRunningListener _ARCommandCommonAudioStateAudioStreamingRunningListener_PARAM) {
        _ARCommandCommonAudioStateAudioStreamingRunningListener = _ARCommandCommonAudioStateAudioStreamingRunningListener_PARAM;
    }

    private ARCommandCommonHeadlightsStateIntensityChangedListener _ARCommandCommonHeadlightsStateIntensityChangedListener;

    /**
     * Set the listener for the command <code>HeadlightsStateIntensityChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonHeadlightsStateIntensityChangedListener (ARCommandCommonHeadlightsStateIntensityChangedListener _ARCommandCommonHeadlightsStateIntensityChangedListener_PARAM) {
        _ARCommandCommonHeadlightsStateIntensityChangedListener = _ARCommandCommonHeadlightsStateIntensityChangedListener_PARAM;
    }

    private ARCommandCommonAnimationsStateListListener _ARCommandCommonAnimationsStateListListener;

    /**
     * Set the listener for the command <code>AnimationsStateList</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAnimationsStateListListener (ARCommandCommonAnimationsStateListListener _ARCommandCommonAnimationsStateListListener_PARAM) {
        _ARCommandCommonAnimationsStateListListener = _ARCommandCommonAnimationsStateListListener_PARAM;
    }

    private ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener _ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener;

    /**
     * Set the listener for the command <code>AccessoryStateSupportedAccessoriesListChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAccessoryStateSupportedAccessoriesListChangedListener (ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener _ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener_PARAM) {
        _ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener = _ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener_PARAM;
    }

    private ARCommandCommonAccessoryStateAccessoryConfigChangedListener _ARCommandCommonAccessoryStateAccessoryConfigChangedListener;

    /**
     * Set the listener for the command <code>AccessoryStateAccessoryConfigChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAccessoryStateAccessoryConfigChangedListener (ARCommandCommonAccessoryStateAccessoryConfigChangedListener _ARCommandCommonAccessoryStateAccessoryConfigChangedListener_PARAM) {
        _ARCommandCommonAccessoryStateAccessoryConfigChangedListener = _ARCommandCommonAccessoryStateAccessoryConfigChangedListener_PARAM;
    }

    private ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener _ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener;

    /**
     * Set the listener for the command <code>AccessoryStateAccessoryConfigModificationEnabled</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonAccessoryStateAccessoryConfigModificationEnabledListener (ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener _ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener_PARAM) {
        _ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener = _ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener_PARAM;
    }

    private ARCommandCommonChargerStateMaxChargeRateChangedListener _ARCommandCommonChargerStateMaxChargeRateChangedListener;

    /**
     * Set the listener for the command <code>ChargerStateMaxChargeRateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonChargerStateMaxChargeRateChangedListener (ARCommandCommonChargerStateMaxChargeRateChangedListener _ARCommandCommonChargerStateMaxChargeRateChangedListener_PARAM) {
        _ARCommandCommonChargerStateMaxChargeRateChangedListener = _ARCommandCommonChargerStateMaxChargeRateChangedListener_PARAM;
    }

    private ARCommandCommonChargerStateCurrentChargeStateChangedListener _ARCommandCommonChargerStateCurrentChargeStateChangedListener;

    /**
     * Set the listener for the command <code>ChargerStateCurrentChargeStateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonChargerStateCurrentChargeStateChangedListener (ARCommandCommonChargerStateCurrentChargeStateChangedListener _ARCommandCommonChargerStateCurrentChargeStateChangedListener_PARAM) {
        _ARCommandCommonChargerStateCurrentChargeStateChangedListener = _ARCommandCommonChargerStateCurrentChargeStateChangedListener_PARAM;
    }

    private ARCommandCommonChargerStateLastChargeRateChangedListener _ARCommandCommonChargerStateLastChargeRateChangedListener;

    /**
     * Set the listener for the command <code>ChargerStateLastChargeRateChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonChargerStateLastChargeRateChangedListener (ARCommandCommonChargerStateLastChargeRateChangedListener _ARCommandCommonChargerStateLastChargeRateChangedListener_PARAM) {
        _ARCommandCommonChargerStateLastChargeRateChangedListener = _ARCommandCommonChargerStateLastChargeRateChangedListener_PARAM;
    }

    private ARCommandCommonChargerStateChargingInfoListener _ARCommandCommonChargerStateChargingInfoListener;

    /**
     * Set the listener for the command <code>ChargerStateChargingInfo</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonChargerStateChargingInfoListener (ARCommandCommonChargerStateChargingInfoListener _ARCommandCommonChargerStateChargingInfoListener_PARAM) {
        _ARCommandCommonChargerStateChargingInfoListener = _ARCommandCommonChargerStateChargingInfoListener_PARAM;
    }

    private ARCommandCommonRunStateRunIdChangedListener _ARCommandCommonRunStateRunIdChangedListener;

    /**
     * Set the listener for the command <code>RunStateRunIdChanged</code> in feature <code>Common</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setCommonRunStateRunIdChangedListener (ARCommandCommonRunStateRunIdChangedListener _ARCommandCommonRunStateRunIdChangedListener_PARAM) {
        _ARCommandCommonRunStateRunIdChangedListener = _ARCommandCommonRunStateRunIdChangedListener_PARAM;
    }


    private ARCommandControllerInfoGpsListener _ARCommandControllerInfoGpsListener;

    /**
     * Set the listener for the command <code>Gps</code> in feature <code>ControllerInfo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setControllerInfoGpsListener (ARCommandControllerInfoGpsListener _ARCommandControllerInfoGpsListener_PARAM) {
        _ARCommandControllerInfoGpsListener = _ARCommandControllerInfoGpsListener_PARAM;
    }

    private ARCommandControllerInfoBarometerListener _ARCommandControllerInfoBarometerListener;

    /**
     * Set the listener for the command <code>Barometer</code> in feature <code>ControllerInfo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setControllerInfoBarometerListener (ARCommandControllerInfoBarometerListener _ARCommandControllerInfoBarometerListener_PARAM) {
        _ARCommandControllerInfoBarometerListener = _ARCommandControllerInfoBarometerListener_PARAM;
    }


    private ARCommandDebugGetAllSettingsListener _ARCommandDebugGetAllSettingsListener;

    /**
     * Set the listener for the command <code>GetAllSettings</code> in feature <code>Debug</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDebugGetAllSettingsListener (ARCommandDebugGetAllSettingsListener _ARCommandDebugGetAllSettingsListener_PARAM) {
        _ARCommandDebugGetAllSettingsListener = _ARCommandDebugGetAllSettingsListener_PARAM;
    }

    private ARCommandDebugSetSettingListener _ARCommandDebugSetSettingListener;

    /**
     * Set the listener for the command <code>SetSetting</code> in feature <code>Debug</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDebugSetSettingListener (ARCommandDebugSetSettingListener _ARCommandDebugSetSettingListener_PARAM) {
        _ARCommandDebugSetSettingListener = _ARCommandDebugSetSettingListener_PARAM;
    }

    private ARCommandDebugSettingsInfoListener _ARCommandDebugSettingsInfoListener;

    /**
     * Set the listener for the command <code>SettingsInfo</code> in feature <code>Debug</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDebugSettingsInfoListener (ARCommandDebugSettingsInfoListener _ARCommandDebugSettingsInfoListener_PARAM) {
        _ARCommandDebugSettingsInfoListener = _ARCommandDebugSettingsInfoListener_PARAM;
    }

    private ARCommandDebugSettingsListListener _ARCommandDebugSettingsListListener;

    /**
     * Set the listener for the command <code>SettingsList</code> in feature <code>Debug</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDebugSettingsListListener (ARCommandDebugSettingsListListener _ARCommandDebugSettingsListListener_PARAM) {
        _ARCommandDebugSettingsListListener = _ARCommandDebugSettingsListListener_PARAM;
    }


    private ARCommandDroneManagerDiscoverDronesListener _ARCommandDroneManagerDiscoverDronesListener;

    /**
     * Set the listener for the command <code>DiscoverDrones</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerDiscoverDronesListener (ARCommandDroneManagerDiscoverDronesListener _ARCommandDroneManagerDiscoverDronesListener_PARAM) {
        _ARCommandDroneManagerDiscoverDronesListener = _ARCommandDroneManagerDiscoverDronesListener_PARAM;
    }

    private ARCommandDroneManagerConnectListener _ARCommandDroneManagerConnectListener;

    /**
     * Set the listener for the command <code>Connect</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerConnectListener (ARCommandDroneManagerConnectListener _ARCommandDroneManagerConnectListener_PARAM) {
        _ARCommandDroneManagerConnectListener = _ARCommandDroneManagerConnectListener_PARAM;
    }

    private ARCommandDroneManagerForgetListener _ARCommandDroneManagerForgetListener;

    /**
     * Set the listener for the command <code>Forget</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerForgetListener (ARCommandDroneManagerForgetListener _ARCommandDroneManagerForgetListener_PARAM) {
        _ARCommandDroneManagerForgetListener = _ARCommandDroneManagerForgetListener_PARAM;
    }

    private ARCommandDroneManagerDroneListItemListener _ARCommandDroneManagerDroneListItemListener;

    /**
     * Set the listener for the command <code>DroneListItem</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerDroneListItemListener (ARCommandDroneManagerDroneListItemListener _ARCommandDroneManagerDroneListItemListener_PARAM) {
        _ARCommandDroneManagerDroneListItemListener = _ARCommandDroneManagerDroneListItemListener_PARAM;
    }

    private ARCommandDroneManagerConnectionStateListener _ARCommandDroneManagerConnectionStateListener;

    /**
     * Set the listener for the command <code>ConnectionState</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerConnectionStateListener (ARCommandDroneManagerConnectionStateListener _ARCommandDroneManagerConnectionStateListener_PARAM) {
        _ARCommandDroneManagerConnectionStateListener = _ARCommandDroneManagerConnectionStateListener_PARAM;
    }

    private ARCommandDroneManagerAuthenticationFailedListener _ARCommandDroneManagerAuthenticationFailedListener;

    /**
     * Set the listener for the command <code>AuthenticationFailed</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerAuthenticationFailedListener (ARCommandDroneManagerAuthenticationFailedListener _ARCommandDroneManagerAuthenticationFailedListener_PARAM) {
        _ARCommandDroneManagerAuthenticationFailedListener = _ARCommandDroneManagerAuthenticationFailedListener_PARAM;
    }

    private ARCommandDroneManagerConnectionRefusedListener _ARCommandDroneManagerConnectionRefusedListener;

    /**
     * Set the listener for the command <code>ConnectionRefused</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerConnectionRefusedListener (ARCommandDroneManagerConnectionRefusedListener _ARCommandDroneManagerConnectionRefusedListener_PARAM) {
        _ARCommandDroneManagerConnectionRefusedListener = _ARCommandDroneManagerConnectionRefusedListener_PARAM;
    }

    private ARCommandDroneManagerKnownDroneItemListener _ARCommandDroneManagerKnownDroneItemListener;

    /**
     * Set the listener for the command <code>KnownDroneItem</code> in feature <code>DroneManager</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setDroneManagerKnownDroneItemListener (ARCommandDroneManagerKnownDroneItemListener _ARCommandDroneManagerKnownDroneItemListener_PARAM) {
        _ARCommandDroneManagerKnownDroneItemListener = _ARCommandDroneManagerKnownDroneItemListener_PARAM;
    }


    private ARCommandFollowMeStartListener _ARCommandFollowMeStartListener;

    /**
     * Set the listener for the command <code>Start</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartListener (ARCommandFollowMeStartListener _ARCommandFollowMeStartListener_PARAM) {
        _ARCommandFollowMeStartListener = _ARCommandFollowMeStartListener_PARAM;
    }

    private ARCommandFollowMeStopListener _ARCommandFollowMeStopListener;

    /**
     * Set the listener for the command <code>Stop</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStopListener (ARCommandFollowMeStopListener _ARCommandFollowMeStopListener_PARAM) {
        _ARCommandFollowMeStopListener = _ARCommandFollowMeStopListener_PARAM;
    }

    private ARCommandFollowMeConfigureGeographicListener _ARCommandFollowMeConfigureGeographicListener;

    /**
     * Set the listener for the command <code>ConfigureGeographic</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeConfigureGeographicListener (ARCommandFollowMeConfigureGeographicListener _ARCommandFollowMeConfigureGeographicListener_PARAM) {
        _ARCommandFollowMeConfigureGeographicListener = _ARCommandFollowMeConfigureGeographicListener_PARAM;
    }

    private ARCommandFollowMeConfigureRelativeListener _ARCommandFollowMeConfigureRelativeListener;

    /**
     * Set the listener for the command <code>ConfigureRelative</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeConfigureRelativeListener (ARCommandFollowMeConfigureRelativeListener _ARCommandFollowMeConfigureRelativeListener_PARAM) {
        _ARCommandFollowMeConfigureRelativeListener = _ARCommandFollowMeConfigureRelativeListener_PARAM;
    }

    private ARCommandFollowMeStopAnimationListener _ARCommandFollowMeStopAnimationListener;

    /**
     * Set the listener for the command <code>StopAnimation</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStopAnimationListener (ARCommandFollowMeStopAnimationListener _ARCommandFollowMeStopAnimationListener_PARAM) {
        _ARCommandFollowMeStopAnimationListener = _ARCommandFollowMeStopAnimationListener_PARAM;
    }

    private ARCommandFollowMeStartHelicoidAnimListener _ARCommandFollowMeStartHelicoidAnimListener;

    /**
     * Set the listener for the command <code>StartHelicoidAnim</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartHelicoidAnimListener (ARCommandFollowMeStartHelicoidAnimListener _ARCommandFollowMeStartHelicoidAnimListener_PARAM) {
        _ARCommandFollowMeStartHelicoidAnimListener = _ARCommandFollowMeStartHelicoidAnimListener_PARAM;
    }

    private ARCommandFollowMeStartSwingAnimListener _ARCommandFollowMeStartSwingAnimListener;

    /**
     * Set the listener for the command <code>StartSwingAnim</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartSwingAnimListener (ARCommandFollowMeStartSwingAnimListener _ARCommandFollowMeStartSwingAnimListener_PARAM) {
        _ARCommandFollowMeStartSwingAnimListener = _ARCommandFollowMeStartSwingAnimListener_PARAM;
    }

    private ARCommandFollowMeStartBoomerangAnimListener _ARCommandFollowMeStartBoomerangAnimListener;

    /**
     * Set the listener for the command <code>StartBoomerangAnim</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartBoomerangAnimListener (ARCommandFollowMeStartBoomerangAnimListener _ARCommandFollowMeStartBoomerangAnimListener_PARAM) {
        _ARCommandFollowMeStartBoomerangAnimListener = _ARCommandFollowMeStartBoomerangAnimListener_PARAM;
    }

    private ARCommandFollowMeStartCandleAnimListener _ARCommandFollowMeStartCandleAnimListener;

    /**
     * Set the listener for the command <code>StartCandleAnim</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartCandleAnimListener (ARCommandFollowMeStartCandleAnimListener _ARCommandFollowMeStartCandleAnimListener_PARAM) {
        _ARCommandFollowMeStartCandleAnimListener = _ARCommandFollowMeStartCandleAnimListener_PARAM;
    }

    private ARCommandFollowMeStartDollySlideAnimListener _ARCommandFollowMeStartDollySlideAnimListener;

    /**
     * Set the listener for the command <code>StartDollySlideAnim</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStartDollySlideAnimListener (ARCommandFollowMeStartDollySlideAnimListener _ARCommandFollowMeStartDollySlideAnimListener_PARAM) {
        _ARCommandFollowMeStartDollySlideAnimListener = _ARCommandFollowMeStartDollySlideAnimListener_PARAM;
    }

    private ARCommandFollowMeTargetFramingPositionListener _ARCommandFollowMeTargetFramingPositionListener;

    /**
     * Set the listener for the command <code>TargetFramingPosition</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeTargetFramingPositionListener (ARCommandFollowMeTargetFramingPositionListener _ARCommandFollowMeTargetFramingPositionListener_PARAM) {
        _ARCommandFollowMeTargetFramingPositionListener = _ARCommandFollowMeTargetFramingPositionListener_PARAM;
    }

    private ARCommandFollowMeTargetImageDetectionListener _ARCommandFollowMeTargetImageDetectionListener;

    /**
     * Set the listener for the command <code>TargetImageDetection</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeTargetImageDetectionListener (ARCommandFollowMeTargetImageDetectionListener _ARCommandFollowMeTargetImageDetectionListener_PARAM) {
        _ARCommandFollowMeTargetImageDetectionListener = _ARCommandFollowMeTargetImageDetectionListener_PARAM;
    }

    private ARCommandFollowMeStateListener _ARCommandFollowMeStateListener;

    /**
     * Set the listener for the command <code>State</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeStateListener (ARCommandFollowMeStateListener _ARCommandFollowMeStateListener_PARAM) {
        _ARCommandFollowMeStateListener = _ARCommandFollowMeStateListener_PARAM;
    }

    private ARCommandFollowMeModeInfoListener _ARCommandFollowMeModeInfoListener;

    /**
     * Set the listener for the command <code>ModeInfo</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeModeInfoListener (ARCommandFollowMeModeInfoListener _ARCommandFollowMeModeInfoListener_PARAM) {
        _ARCommandFollowMeModeInfoListener = _ARCommandFollowMeModeInfoListener_PARAM;
    }

    private ARCommandFollowMeGeographicConfigListener _ARCommandFollowMeGeographicConfigListener;

    /**
     * Set the listener for the command <code>GeographicConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeGeographicConfigListener (ARCommandFollowMeGeographicConfigListener _ARCommandFollowMeGeographicConfigListener_PARAM) {
        _ARCommandFollowMeGeographicConfigListener = _ARCommandFollowMeGeographicConfigListener_PARAM;
    }

    private ARCommandFollowMeRelativeConfigListener _ARCommandFollowMeRelativeConfigListener;

    /**
     * Set the listener for the command <code>RelativeConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeRelativeConfigListener (ARCommandFollowMeRelativeConfigListener _ARCommandFollowMeRelativeConfigListener_PARAM) {
        _ARCommandFollowMeRelativeConfigListener = _ARCommandFollowMeRelativeConfigListener_PARAM;
    }

    private ARCommandFollowMeTargetTrajectoryListener _ARCommandFollowMeTargetTrajectoryListener;

    /**
     * Set the listener for the command <code>TargetTrajectory</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeTargetTrajectoryListener (ARCommandFollowMeTargetTrajectoryListener _ARCommandFollowMeTargetTrajectoryListener_PARAM) {
        _ARCommandFollowMeTargetTrajectoryListener = _ARCommandFollowMeTargetTrajectoryListener_PARAM;
    }

    private ARCommandFollowMeHelicoidAnimConfigListener _ARCommandFollowMeHelicoidAnimConfigListener;

    /**
     * Set the listener for the command <code>HelicoidAnimConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeHelicoidAnimConfigListener (ARCommandFollowMeHelicoidAnimConfigListener _ARCommandFollowMeHelicoidAnimConfigListener_PARAM) {
        _ARCommandFollowMeHelicoidAnimConfigListener = _ARCommandFollowMeHelicoidAnimConfigListener_PARAM;
    }

    private ARCommandFollowMeSwingAnimConfigListener _ARCommandFollowMeSwingAnimConfigListener;

    /**
     * Set the listener for the command <code>SwingAnimConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeSwingAnimConfigListener (ARCommandFollowMeSwingAnimConfigListener _ARCommandFollowMeSwingAnimConfigListener_PARAM) {
        _ARCommandFollowMeSwingAnimConfigListener = _ARCommandFollowMeSwingAnimConfigListener_PARAM;
    }

    private ARCommandFollowMeBoomerangAnimConfigListener _ARCommandFollowMeBoomerangAnimConfigListener;

    /**
     * Set the listener for the command <code>BoomerangAnimConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeBoomerangAnimConfigListener (ARCommandFollowMeBoomerangAnimConfigListener _ARCommandFollowMeBoomerangAnimConfigListener_PARAM) {
        _ARCommandFollowMeBoomerangAnimConfigListener = _ARCommandFollowMeBoomerangAnimConfigListener_PARAM;
    }

    private ARCommandFollowMeCandleAnimConfigListener _ARCommandFollowMeCandleAnimConfigListener;

    /**
     * Set the listener for the command <code>CandleAnimConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeCandleAnimConfigListener (ARCommandFollowMeCandleAnimConfigListener _ARCommandFollowMeCandleAnimConfigListener_PARAM) {
        _ARCommandFollowMeCandleAnimConfigListener = _ARCommandFollowMeCandleAnimConfigListener_PARAM;
    }

    private ARCommandFollowMeDollySlideAnimConfigListener _ARCommandFollowMeDollySlideAnimConfigListener;

    /**
     * Set the listener for the command <code>DollySlideAnimConfig</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeDollySlideAnimConfigListener (ARCommandFollowMeDollySlideAnimConfigListener _ARCommandFollowMeDollySlideAnimConfigListener_PARAM) {
        _ARCommandFollowMeDollySlideAnimConfigListener = _ARCommandFollowMeDollySlideAnimConfigListener_PARAM;
    }

    private ARCommandFollowMeTargetFramingPositionChangedListener _ARCommandFollowMeTargetFramingPositionChangedListener;

    /**
     * Set the listener for the command <code>TargetFramingPositionChanged</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeTargetFramingPositionChangedListener (ARCommandFollowMeTargetFramingPositionChangedListener _ARCommandFollowMeTargetFramingPositionChangedListener_PARAM) {
        _ARCommandFollowMeTargetFramingPositionChangedListener = _ARCommandFollowMeTargetFramingPositionChangedListener_PARAM;
    }

    private ARCommandFollowMeTargetImageDetectionStateListener _ARCommandFollowMeTargetImageDetectionStateListener;

    /**
     * Set the listener for the command <code>TargetImageDetectionState</code> in feature <code>FollowMe</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setFollowMeTargetImageDetectionStateListener (ARCommandFollowMeTargetImageDetectionStateListener _ARCommandFollowMeTargetImageDetectionStateListener_PARAM) {
        _ARCommandFollowMeTargetImageDetectionStateListener = _ARCommandFollowMeTargetImageDetectionStateListener_PARAM;
    }


    private ARCommandJumpingSumoPilotingPCMDListener _ARCommandJumpingSumoPilotingPCMDListener;

    /**
     * Set the listener for the command <code>PilotingPCMD</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingPCMDListener (ARCommandJumpingSumoPilotingPCMDListener _ARCommandJumpingSumoPilotingPCMDListener_PARAM) {
        _ARCommandJumpingSumoPilotingPCMDListener = _ARCommandJumpingSumoPilotingPCMDListener_PARAM;
    }

    private ARCommandJumpingSumoPilotingPostureListener _ARCommandJumpingSumoPilotingPostureListener;

    /**
     * Set the listener for the command <code>PilotingPosture</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingPostureListener (ARCommandJumpingSumoPilotingPostureListener _ARCommandJumpingSumoPilotingPostureListener_PARAM) {
        _ARCommandJumpingSumoPilotingPostureListener = _ARCommandJumpingSumoPilotingPostureListener_PARAM;
    }

    private ARCommandJumpingSumoPilotingAddCapOffsetListener _ARCommandJumpingSumoPilotingAddCapOffsetListener;

    /**
     * Set the listener for the command <code>PilotingAddCapOffset</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingAddCapOffsetListener (ARCommandJumpingSumoPilotingAddCapOffsetListener _ARCommandJumpingSumoPilotingAddCapOffsetListener_PARAM) {
        _ARCommandJumpingSumoPilotingAddCapOffsetListener = _ARCommandJumpingSumoPilotingAddCapOffsetListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsJumpStopListener _ARCommandJumpingSumoAnimationsJumpStopListener;

    /**
     * Set the listener for the command <code>AnimationsJumpStop</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsJumpStopListener (ARCommandJumpingSumoAnimationsJumpStopListener _ARCommandJumpingSumoAnimationsJumpStopListener_PARAM) {
        _ARCommandJumpingSumoAnimationsJumpStopListener = _ARCommandJumpingSumoAnimationsJumpStopListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsJumpCancelListener _ARCommandJumpingSumoAnimationsJumpCancelListener;

    /**
     * Set the listener for the command <code>AnimationsJumpCancel</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsJumpCancelListener (ARCommandJumpingSumoAnimationsJumpCancelListener _ARCommandJumpingSumoAnimationsJumpCancelListener_PARAM) {
        _ARCommandJumpingSumoAnimationsJumpCancelListener = _ARCommandJumpingSumoAnimationsJumpCancelListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsJumpLoadListener _ARCommandJumpingSumoAnimationsJumpLoadListener;

    /**
     * Set the listener for the command <code>AnimationsJumpLoad</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsJumpLoadListener (ARCommandJumpingSumoAnimationsJumpLoadListener _ARCommandJumpingSumoAnimationsJumpLoadListener_PARAM) {
        _ARCommandJumpingSumoAnimationsJumpLoadListener = _ARCommandJumpingSumoAnimationsJumpLoadListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsJumpListener _ARCommandJumpingSumoAnimationsJumpListener;

    /**
     * Set the listener for the command <code>AnimationsJump</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsJumpListener (ARCommandJumpingSumoAnimationsJumpListener _ARCommandJumpingSumoAnimationsJumpListener_PARAM) {
        _ARCommandJumpingSumoAnimationsJumpListener = _ARCommandJumpingSumoAnimationsJumpListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsSimpleAnimationListener _ARCommandJumpingSumoAnimationsSimpleAnimationListener;

    /**
     * Set the listener for the command <code>AnimationsSimpleAnimation</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsSimpleAnimationListener (ARCommandJumpingSumoAnimationsSimpleAnimationListener _ARCommandJumpingSumoAnimationsSimpleAnimationListener_PARAM) {
        _ARCommandJumpingSumoAnimationsSimpleAnimationListener = _ARCommandJumpingSumoAnimationsSimpleAnimationListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordPictureListener _ARCommandJumpingSumoMediaRecordPictureListener;

    /**
     * Set the listener for the command <code>MediaRecordPicture</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordPictureListener (ARCommandJumpingSumoMediaRecordPictureListener _ARCommandJumpingSumoMediaRecordPictureListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordPictureListener = _ARCommandJumpingSumoMediaRecordPictureListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordVideoListener _ARCommandJumpingSumoMediaRecordVideoListener;

    /**
     * Set the listener for the command <code>MediaRecordVideo</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordVideoListener (ARCommandJumpingSumoMediaRecordVideoListener _ARCommandJumpingSumoMediaRecordVideoListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordVideoListener = _ARCommandJumpingSumoMediaRecordVideoListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordPictureV2Listener _ARCommandJumpingSumoMediaRecordPictureV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordPictureV2</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordPictureV2Listener (ARCommandJumpingSumoMediaRecordPictureV2Listener _ARCommandJumpingSumoMediaRecordPictureV2Listener_PARAM) {
        _ARCommandJumpingSumoMediaRecordPictureV2Listener = _ARCommandJumpingSumoMediaRecordPictureV2Listener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordVideoV2Listener _ARCommandJumpingSumoMediaRecordVideoV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordVideoV2</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordVideoV2Listener (ARCommandJumpingSumoMediaRecordVideoV2Listener _ARCommandJumpingSumoMediaRecordVideoV2Listener_PARAM) {
        _ARCommandJumpingSumoMediaRecordVideoV2Listener = _ARCommandJumpingSumoMediaRecordVideoV2Listener_PARAM;
    }

    private ARCommandJumpingSumoNetworkSettingsWifiSelectionListener _ARCommandJumpingSumoNetworkSettingsWifiSelectionListener;

    /**
     * Set the listener for the command <code>NetworkSettingsWifiSelection</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkSettingsWifiSelectionListener (ARCommandJumpingSumoNetworkSettingsWifiSelectionListener _ARCommandJumpingSumoNetworkSettingsWifiSelectionListener_PARAM) {
        _ARCommandJumpingSumoNetworkSettingsWifiSelectionListener = _ARCommandJumpingSumoNetworkSettingsWifiSelectionListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkWifiScanListener _ARCommandJumpingSumoNetworkWifiScanListener;

    /**
     * Set the listener for the command <code>NetworkWifiScan</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkWifiScanListener (ARCommandJumpingSumoNetworkWifiScanListener _ARCommandJumpingSumoNetworkWifiScanListener_PARAM) {
        _ARCommandJumpingSumoNetworkWifiScanListener = _ARCommandJumpingSumoNetworkWifiScanListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkWifiAuthChannelListener _ARCommandJumpingSumoNetworkWifiAuthChannelListener;

    /**
     * Set the listener for the command <code>NetworkWifiAuthChannel</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkWifiAuthChannelListener (ARCommandJumpingSumoNetworkWifiAuthChannelListener _ARCommandJumpingSumoNetworkWifiAuthChannelListener_PARAM) {
        _ARCommandJumpingSumoNetworkWifiAuthChannelListener = _ARCommandJumpingSumoNetworkWifiAuthChannelListener_PARAM;
    }

    private ARCommandJumpingSumoAudioSettingsMasterVolumeListener _ARCommandJumpingSumoAudioSettingsMasterVolumeListener;

    /**
     * Set the listener for the command <code>AudioSettingsMasterVolume</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAudioSettingsMasterVolumeListener (ARCommandJumpingSumoAudioSettingsMasterVolumeListener _ARCommandJumpingSumoAudioSettingsMasterVolumeListener_PARAM) {
        _ARCommandJumpingSumoAudioSettingsMasterVolumeListener = _ARCommandJumpingSumoAudioSettingsMasterVolumeListener_PARAM;
    }

    private ARCommandJumpingSumoAudioSettingsThemeListener _ARCommandJumpingSumoAudioSettingsThemeListener;

    /**
     * Set the listener for the command <code>AudioSettingsTheme</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAudioSettingsThemeListener (ARCommandJumpingSumoAudioSettingsThemeListener _ARCommandJumpingSumoAudioSettingsThemeListener_PARAM) {
        _ARCommandJumpingSumoAudioSettingsThemeListener = _ARCommandJumpingSumoAudioSettingsThemeListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener _ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener;

    /**
     * Set the listener for the command <code>RoadPlanAllScriptsMetadata</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanAllScriptsMetadataListener (ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener _ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener = _ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanScriptUploadedListener _ARCommandJumpingSumoRoadPlanScriptUploadedListener;

    /**
     * Set the listener for the command <code>RoadPlanScriptUploaded</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanScriptUploadedListener (ARCommandJumpingSumoRoadPlanScriptUploadedListener _ARCommandJumpingSumoRoadPlanScriptUploadedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanScriptUploadedListener = _ARCommandJumpingSumoRoadPlanScriptUploadedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanScriptDeleteListener _ARCommandJumpingSumoRoadPlanScriptDeleteListener;

    /**
     * Set the listener for the command <code>RoadPlanScriptDelete</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanScriptDeleteListener (ARCommandJumpingSumoRoadPlanScriptDeleteListener _ARCommandJumpingSumoRoadPlanScriptDeleteListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanScriptDeleteListener = _ARCommandJumpingSumoRoadPlanScriptDeleteListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanPlayScriptListener _ARCommandJumpingSumoRoadPlanPlayScriptListener;

    /**
     * Set the listener for the command <code>RoadPlanPlayScript</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanPlayScriptListener (ARCommandJumpingSumoRoadPlanPlayScriptListener _ARCommandJumpingSumoRoadPlanPlayScriptListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanPlayScriptListener = _ARCommandJumpingSumoRoadPlanPlayScriptListener_PARAM;
    }

    private ARCommandJumpingSumoSpeedSettingsOutdoorListener _ARCommandJumpingSumoSpeedSettingsOutdoorListener;

    /**
     * Set the listener for the command <code>SpeedSettingsOutdoor</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoSpeedSettingsOutdoorListener (ARCommandJumpingSumoSpeedSettingsOutdoorListener _ARCommandJumpingSumoSpeedSettingsOutdoorListener_PARAM) {
        _ARCommandJumpingSumoSpeedSettingsOutdoorListener = _ARCommandJumpingSumoSpeedSettingsOutdoorListener_PARAM;
    }

    private ARCommandJumpingSumoMediaStreamingVideoEnableListener _ARCommandJumpingSumoMediaStreamingVideoEnableListener;

    /**
     * Set the listener for the command <code>MediaStreamingVideoEnable</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaStreamingVideoEnableListener (ARCommandJumpingSumoMediaStreamingVideoEnableListener _ARCommandJumpingSumoMediaStreamingVideoEnableListener_PARAM) {
        _ARCommandJumpingSumoMediaStreamingVideoEnableListener = _ARCommandJumpingSumoMediaStreamingVideoEnableListener_PARAM;
    }

    private ARCommandJumpingSumoVideoSettingsAutorecordListener _ARCommandJumpingSumoVideoSettingsAutorecordListener;

    /**
     * Set the listener for the command <code>VideoSettingsAutorecord</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoVideoSettingsAutorecordListener (ARCommandJumpingSumoVideoSettingsAutorecordListener _ARCommandJumpingSumoVideoSettingsAutorecordListener_PARAM) {
        _ARCommandJumpingSumoVideoSettingsAutorecordListener = _ARCommandJumpingSumoVideoSettingsAutorecordListener_PARAM;
    }

    private ARCommandJumpingSumoPilotingStatePostureChangedListener _ARCommandJumpingSumoPilotingStatePostureChangedListener;

    /**
     * Set the listener for the command <code>PilotingStatePostureChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingStatePostureChangedListener (ARCommandJumpingSumoPilotingStatePostureChangedListener _ARCommandJumpingSumoPilotingStatePostureChangedListener_PARAM) {
        _ARCommandJumpingSumoPilotingStatePostureChangedListener = _ARCommandJumpingSumoPilotingStatePostureChangedListener_PARAM;
    }

    private ARCommandJumpingSumoPilotingStateAlertStateChangedListener _ARCommandJumpingSumoPilotingStateAlertStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAlertStateChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingStateAlertStateChangedListener (ARCommandJumpingSumoPilotingStateAlertStateChangedListener _ARCommandJumpingSumoPilotingStateAlertStateChangedListener_PARAM) {
        _ARCommandJumpingSumoPilotingStateAlertStateChangedListener = _ARCommandJumpingSumoPilotingStateAlertStateChangedListener_PARAM;
    }

    private ARCommandJumpingSumoPilotingStateSpeedChangedListener _ARCommandJumpingSumoPilotingStateSpeedChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateSpeedChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoPilotingStateSpeedChangedListener (ARCommandJumpingSumoPilotingStateSpeedChangedListener _ARCommandJumpingSumoPilotingStateSpeedChangedListener_PARAM) {
        _ARCommandJumpingSumoPilotingStateSpeedChangedListener = _ARCommandJumpingSumoPilotingStateSpeedChangedListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener _ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener;

    /**
     * Set the listener for the command <code>AnimationsStateJumpLoadChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsStateJumpLoadChangedListener (ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener _ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener_PARAM) {
        _ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener = _ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener _ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener;

    /**
     * Set the listener for the command <code>AnimationsStateJumpTypeChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsStateJumpTypeChangedListener (ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener _ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener_PARAM) {
        _ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener = _ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener_PARAM;
    }

    private ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener _ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener;

    /**
     * Set the listener for the command <code>AnimationsStateJumpMotorProblemChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAnimationsStateJumpMotorProblemChangedListener (ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener _ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener_PARAM) {
        _ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener = _ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener_PARAM;
    }

    private ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener _ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductGPSVersionChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoSettingsStateProductGPSVersionChangedListener (ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener _ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener_PARAM) {
        _ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener = _ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener _ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordStatePictureStateChangedListener (ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener _ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener = _ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener _ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoStateChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordStateVideoStateChangedListener (ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener _ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener = _ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener _ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChangedV2</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordStatePictureStateChangedV2Listener (ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener _ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener_PARAM) {
        _ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener = _ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener _ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoStateChangedV2</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordStateVideoStateChangedV2Listener (ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener _ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener_PARAM) {
        _ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener = _ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener _ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventPictureEventChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordEventPictureEventChangedListener (ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener _ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener = _ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener_PARAM;
    }

    private ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener _ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventVideoEventChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaRecordEventVideoEventChangedListener (ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener _ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener_PARAM) {
        _ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener = _ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener _ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener;

    /**
     * Set the listener for the command <code>NetworkSettingsStateWifiSelectionChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkSettingsStateWifiSelectionChangedListener (ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener _ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener = _ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkStateWifiScanListChangedListener _ARCommandJumpingSumoNetworkStateWifiScanListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiScanListChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkStateWifiScanListChangedListener (ARCommandJumpingSumoNetworkStateWifiScanListChangedListener _ARCommandJumpingSumoNetworkStateWifiScanListChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkStateWifiScanListChangedListener = _ARCommandJumpingSumoNetworkStateWifiScanListChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener _ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiScanChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkStateAllWifiScanChangedListener (ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener _ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener = _ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener _ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiAuthChannelListChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkStateWifiAuthChannelListChangedListener (ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener _ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener = _ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener _ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiAuthChannelChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkStateAllWifiAuthChannelChangedListener (ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener _ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener = _ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener_PARAM;
    }

    private ARCommandJumpingSumoNetworkStateLinkQualityChangedListener _ARCommandJumpingSumoNetworkStateLinkQualityChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateLinkQualityChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoNetworkStateLinkQualityChangedListener (ARCommandJumpingSumoNetworkStateLinkQualityChangedListener _ARCommandJumpingSumoNetworkStateLinkQualityChangedListener_PARAM) {
        _ARCommandJumpingSumoNetworkStateLinkQualityChangedListener = _ARCommandJumpingSumoNetworkStateLinkQualityChangedListener_PARAM;
    }

    private ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener _ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener;

    /**
     * Set the listener for the command <code>AudioSettingsStateMasterVolumeChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAudioSettingsStateMasterVolumeChangedListener (ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener _ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener_PARAM) {
        _ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener = _ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener_PARAM;
    }

    private ARCommandJumpingSumoAudioSettingsStateThemeChangedListener _ARCommandJumpingSumoAudioSettingsStateThemeChangedListener;

    /**
     * Set the listener for the command <code>AudioSettingsStateThemeChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoAudioSettingsStateThemeChangedListener (ARCommandJumpingSumoAudioSettingsStateThemeChangedListener _ARCommandJumpingSumoAudioSettingsStateThemeChangedListener_PARAM) {
        _ARCommandJumpingSumoAudioSettingsStateThemeChangedListener = _ARCommandJumpingSumoAudioSettingsStateThemeChangedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener _ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener;

    /**
     * Set the listener for the command <code>RoadPlanStateScriptMetadataListChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanStateScriptMetadataListChangedListener (ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener _ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener = _ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener _ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener;

    /**
     * Set the listener for the command <code>RoadPlanStateAllScriptsMetadataChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener (ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener _ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener = _ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener _ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener;

    /**
     * Set the listener for the command <code>RoadPlanStateScriptUploadChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanStateScriptUploadChangedListener (ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener _ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener = _ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener _ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener;

    /**
     * Set the listener for the command <code>RoadPlanStateScriptDeleteChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanStateScriptDeleteChangedListener (ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener _ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener = _ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener_PARAM;
    }

    private ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener _ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener;

    /**
     * Set the listener for the command <code>RoadPlanStatePlayScriptChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoRoadPlanStatePlayScriptChangedListener (ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener _ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener_PARAM) {
        _ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener = _ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener_PARAM;
    }

    private ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener _ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateOutdoorChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoSpeedSettingsStateOutdoorChangedListener (ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener _ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener_PARAM) {
        _ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener = _ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener_PARAM;
    }

    private ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener _ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener;

    /**
     * Set the listener for the command <code>MediaStreamingStateVideoEnableChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoMediaStreamingStateVideoEnableChangedListener (ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener _ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener_PARAM) {
        _ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener = _ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener_PARAM;
    }

    private ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener _ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateAutorecordChanged</code> in feature <code>JumpingSumo</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setJumpingSumoVideoSettingsStateAutorecordChangedListener (ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener _ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener_PARAM) {
        _ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener = _ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener_PARAM;
    }


    private ARCommandMapperGrabListener _ARCommandMapperGrabListener;

    /**
     * Set the listener for the command <code>Grab</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperGrabListener (ARCommandMapperGrabListener _ARCommandMapperGrabListener_PARAM) {
        _ARCommandMapperGrabListener = _ARCommandMapperGrabListener_PARAM;
    }

    private ARCommandMapperMapButtonActionListener _ARCommandMapperMapButtonActionListener;

    /**
     * Set the listener for the command <code>MapButtonAction</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMapButtonActionListener (ARCommandMapperMapButtonActionListener _ARCommandMapperMapButtonActionListener_PARAM) {
        _ARCommandMapperMapButtonActionListener = _ARCommandMapperMapButtonActionListener_PARAM;
    }

    private ARCommandMapperMapAxisActionListener _ARCommandMapperMapAxisActionListener;

    /**
     * Set the listener for the command <code>MapAxisAction</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMapAxisActionListener (ARCommandMapperMapAxisActionListener _ARCommandMapperMapAxisActionListener_PARAM) {
        _ARCommandMapperMapAxisActionListener = _ARCommandMapperMapAxisActionListener_PARAM;
    }

    private ARCommandMapperResetMappingListener _ARCommandMapperResetMappingListener;

    /**
     * Set the listener for the command <code>ResetMapping</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperResetMappingListener (ARCommandMapperResetMappingListener _ARCommandMapperResetMappingListener_PARAM) {
        _ARCommandMapperResetMappingListener = _ARCommandMapperResetMappingListener_PARAM;
    }

    private ARCommandMapperSetExpoListener _ARCommandMapperSetExpoListener;

    /**
     * Set the listener for the command <code>SetExpo</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperSetExpoListener (ARCommandMapperSetExpoListener _ARCommandMapperSetExpoListener_PARAM) {
        _ARCommandMapperSetExpoListener = _ARCommandMapperSetExpoListener_PARAM;
    }

    private ARCommandMapperSetInvertedListener _ARCommandMapperSetInvertedListener;

    /**
     * Set the listener for the command <code>SetInverted</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperSetInvertedListener (ARCommandMapperSetInvertedListener _ARCommandMapperSetInvertedListener_PARAM) {
        _ARCommandMapperSetInvertedListener = _ARCommandMapperSetInvertedListener_PARAM;
    }

    private ARCommandMapperGrabStateListener _ARCommandMapperGrabStateListener;

    /**
     * Set the listener for the command <code>GrabState</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperGrabStateListener (ARCommandMapperGrabStateListener _ARCommandMapperGrabStateListener_PARAM) {
        _ARCommandMapperGrabStateListener = _ARCommandMapperGrabStateListener_PARAM;
    }

    private ARCommandMapperGrabButtonEventListener _ARCommandMapperGrabButtonEventListener;

    /**
     * Set the listener for the command <code>GrabButtonEvent</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperGrabButtonEventListener (ARCommandMapperGrabButtonEventListener _ARCommandMapperGrabButtonEventListener_PARAM) {
        _ARCommandMapperGrabButtonEventListener = _ARCommandMapperGrabButtonEventListener_PARAM;
    }

    private ARCommandMapperGrabAxisEventListener _ARCommandMapperGrabAxisEventListener;

    /**
     * Set the listener for the command <code>GrabAxisEvent</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperGrabAxisEventListener (ARCommandMapperGrabAxisEventListener _ARCommandMapperGrabAxisEventListener_PARAM) {
        _ARCommandMapperGrabAxisEventListener = _ARCommandMapperGrabAxisEventListener_PARAM;
    }

    private ARCommandMapperButtonMappingItemListener _ARCommandMapperButtonMappingItemListener;

    /**
     * Set the listener for the command <code>ButtonMappingItem</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperButtonMappingItemListener (ARCommandMapperButtonMappingItemListener _ARCommandMapperButtonMappingItemListener_PARAM) {
        _ARCommandMapperButtonMappingItemListener = _ARCommandMapperButtonMappingItemListener_PARAM;
    }

    private ARCommandMapperAxisMappingItemListener _ARCommandMapperAxisMappingItemListener;

    /**
     * Set the listener for the command <code>AxisMappingItem</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperAxisMappingItemListener (ARCommandMapperAxisMappingItemListener _ARCommandMapperAxisMappingItemListener_PARAM) {
        _ARCommandMapperAxisMappingItemListener = _ARCommandMapperAxisMappingItemListener_PARAM;
    }

    private ARCommandMapperApplicationAxisEventListener _ARCommandMapperApplicationAxisEventListener;

    /**
     * Set the listener for the command <code>ApplicationAxisEvent</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperApplicationAxisEventListener (ARCommandMapperApplicationAxisEventListener _ARCommandMapperApplicationAxisEventListener_PARAM) {
        _ARCommandMapperApplicationAxisEventListener = _ARCommandMapperApplicationAxisEventListener_PARAM;
    }

    private ARCommandMapperApplicationButtonEventListener _ARCommandMapperApplicationButtonEventListener;

    /**
     * Set the listener for the command <code>ApplicationButtonEvent</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperApplicationButtonEventListener (ARCommandMapperApplicationButtonEventListener _ARCommandMapperApplicationButtonEventListener_PARAM) {
        _ARCommandMapperApplicationButtonEventListener = _ARCommandMapperApplicationButtonEventListener_PARAM;
    }

    private ARCommandMapperExpoMapItemListener _ARCommandMapperExpoMapItemListener;

    /**
     * Set the listener for the command <code>ExpoMapItem</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperExpoMapItemListener (ARCommandMapperExpoMapItemListener _ARCommandMapperExpoMapItemListener_PARAM) {
        _ARCommandMapperExpoMapItemListener = _ARCommandMapperExpoMapItemListener_PARAM;
    }

    private ARCommandMapperInvertedMapItemListener _ARCommandMapperInvertedMapItemListener;

    /**
     * Set the listener for the command <code>InvertedMapItem</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperInvertedMapItemListener (ARCommandMapperInvertedMapItemListener _ARCommandMapperInvertedMapItemListener_PARAM) {
        _ARCommandMapperInvertedMapItemListener = _ARCommandMapperInvertedMapItemListener_PARAM;
    }

    private ARCommandMapperActiveProductListener _ARCommandMapperActiveProductListener;

    /**
     * Set the listener for the command <code>ActiveProduct</code> in feature <code>Mapper</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperActiveProductListener (ARCommandMapperActiveProductListener _ARCommandMapperActiveProductListener_PARAM) {
        _ARCommandMapperActiveProductListener = _ARCommandMapperActiveProductListener_PARAM;
    }


    private ARCommandMapperMiniMapButtonActionListener _ARCommandMapperMiniMapButtonActionListener;

    /**
     * Set the listener for the command <code>MapButtonAction</code> in feature <code>MapperMini</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMiniMapButtonActionListener (ARCommandMapperMiniMapButtonActionListener _ARCommandMapperMiniMapButtonActionListener_PARAM) {
        _ARCommandMapperMiniMapButtonActionListener = _ARCommandMapperMiniMapButtonActionListener_PARAM;
    }

    private ARCommandMapperMiniMapAxisActionListener _ARCommandMapperMiniMapAxisActionListener;

    /**
     * Set the listener for the command <code>MapAxisAction</code> in feature <code>MapperMini</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMiniMapAxisActionListener (ARCommandMapperMiniMapAxisActionListener _ARCommandMapperMiniMapAxisActionListener_PARAM) {
        _ARCommandMapperMiniMapAxisActionListener = _ARCommandMapperMiniMapAxisActionListener_PARAM;
    }

    private ARCommandMapperMiniResetMappingListener _ARCommandMapperMiniResetMappingListener;

    /**
     * Set the listener for the command <code>ResetMapping</code> in feature <code>MapperMini</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMiniResetMappingListener (ARCommandMapperMiniResetMappingListener _ARCommandMapperMiniResetMappingListener_PARAM) {
        _ARCommandMapperMiniResetMappingListener = _ARCommandMapperMiniResetMappingListener_PARAM;
    }

    private ARCommandMapperMiniButtonMappingItemListener _ARCommandMapperMiniButtonMappingItemListener;

    /**
     * Set the listener for the command <code>ButtonMappingItem</code> in feature <code>MapperMini</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMiniButtonMappingItemListener (ARCommandMapperMiniButtonMappingItemListener _ARCommandMapperMiniButtonMappingItemListener_PARAM) {
        _ARCommandMapperMiniButtonMappingItemListener = _ARCommandMapperMiniButtonMappingItemListener_PARAM;
    }

    private ARCommandMapperMiniAxisMappingItemListener _ARCommandMapperMiniAxisMappingItemListener;

    /**
     * Set the listener for the command <code>AxisMappingItem</code> in feature <code>MapperMini</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMapperMiniAxisMappingItemListener (ARCommandMapperMiniAxisMappingItemListener _ARCommandMapperMiniAxisMappingItemListener_PARAM) {
        _ARCommandMapperMiniAxisMappingItemListener = _ARCommandMapperMiniAxisMappingItemListener_PARAM;
    }


    private ARCommandMiniDronePilotingFlatTrimListener _ARCommandMiniDronePilotingFlatTrimListener;

    /**
     * Set the listener for the command <code>PilotingFlatTrim</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingFlatTrimListener (ARCommandMiniDronePilotingFlatTrimListener _ARCommandMiniDronePilotingFlatTrimListener_PARAM) {
        _ARCommandMiniDronePilotingFlatTrimListener = _ARCommandMiniDronePilotingFlatTrimListener_PARAM;
    }

    private ARCommandMiniDronePilotingTakeOffListener _ARCommandMiniDronePilotingTakeOffListener;

    /**
     * Set the listener for the command <code>PilotingTakeOff</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingTakeOffListener (ARCommandMiniDronePilotingTakeOffListener _ARCommandMiniDronePilotingTakeOffListener_PARAM) {
        _ARCommandMiniDronePilotingTakeOffListener = _ARCommandMiniDronePilotingTakeOffListener_PARAM;
    }

    private ARCommandMiniDronePilotingPCMDListener _ARCommandMiniDronePilotingPCMDListener;

    /**
     * Set the listener for the command <code>PilotingPCMD</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingPCMDListener (ARCommandMiniDronePilotingPCMDListener _ARCommandMiniDronePilotingPCMDListener_PARAM) {
        _ARCommandMiniDronePilotingPCMDListener = _ARCommandMiniDronePilotingPCMDListener_PARAM;
    }

    private ARCommandMiniDronePilotingLandingListener _ARCommandMiniDronePilotingLandingListener;

    /**
     * Set the listener for the command <code>PilotingLanding</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingLandingListener (ARCommandMiniDronePilotingLandingListener _ARCommandMiniDronePilotingLandingListener_PARAM) {
        _ARCommandMiniDronePilotingLandingListener = _ARCommandMiniDronePilotingLandingListener_PARAM;
    }

    private ARCommandMiniDronePilotingEmergencyListener _ARCommandMiniDronePilotingEmergencyListener;

    /**
     * Set the listener for the command <code>PilotingEmergency</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingEmergencyListener (ARCommandMiniDronePilotingEmergencyListener _ARCommandMiniDronePilotingEmergencyListener_PARAM) {
        _ARCommandMiniDronePilotingEmergencyListener = _ARCommandMiniDronePilotingEmergencyListener_PARAM;
    }

    private ARCommandMiniDronePilotingAutoTakeOffModeListener _ARCommandMiniDronePilotingAutoTakeOffModeListener;

    /**
     * Set the listener for the command <code>PilotingAutoTakeOffMode</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingAutoTakeOffModeListener (ARCommandMiniDronePilotingAutoTakeOffModeListener _ARCommandMiniDronePilotingAutoTakeOffModeListener_PARAM) {
        _ARCommandMiniDronePilotingAutoTakeOffModeListener = _ARCommandMiniDronePilotingAutoTakeOffModeListener_PARAM;
    }

    private ARCommandMiniDronePilotingFlyingModeListener _ARCommandMiniDronePilotingFlyingModeListener;

    /**
     * Set the listener for the command <code>PilotingFlyingMode</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingFlyingModeListener (ARCommandMiniDronePilotingFlyingModeListener _ARCommandMiniDronePilotingFlyingModeListener_PARAM) {
        _ARCommandMiniDronePilotingFlyingModeListener = _ARCommandMiniDronePilotingFlyingModeListener_PARAM;
    }

    private ARCommandMiniDronePilotingPlaneGearBoxListener _ARCommandMiniDronePilotingPlaneGearBoxListener;

    /**
     * Set the listener for the command <code>PilotingPlaneGearBox</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingPlaneGearBoxListener (ARCommandMiniDronePilotingPlaneGearBoxListener _ARCommandMiniDronePilotingPlaneGearBoxListener_PARAM) {
        _ARCommandMiniDronePilotingPlaneGearBoxListener = _ARCommandMiniDronePilotingPlaneGearBoxListener_PARAM;
    }

    private ARCommandMiniDronePilotingTogglePilotingModeListener _ARCommandMiniDronePilotingTogglePilotingModeListener;

    /**
     * Set the listener for the command <code>PilotingTogglePilotingMode</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingTogglePilotingModeListener (ARCommandMiniDronePilotingTogglePilotingModeListener _ARCommandMiniDronePilotingTogglePilotingModeListener_PARAM) {
        _ARCommandMiniDronePilotingTogglePilotingModeListener = _ARCommandMiniDronePilotingTogglePilotingModeListener_PARAM;
    }

    private ARCommandMiniDroneAnimationsFlipListener _ARCommandMiniDroneAnimationsFlipListener;

    /**
     * Set the listener for the command <code>AnimationsFlip</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneAnimationsFlipListener (ARCommandMiniDroneAnimationsFlipListener _ARCommandMiniDroneAnimationsFlipListener_PARAM) {
        _ARCommandMiniDroneAnimationsFlipListener = _ARCommandMiniDroneAnimationsFlipListener_PARAM;
    }

    private ARCommandMiniDroneAnimationsCapListener _ARCommandMiniDroneAnimationsCapListener;

    /**
     * Set the listener for the command <code>AnimationsCap</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneAnimationsCapListener (ARCommandMiniDroneAnimationsCapListener _ARCommandMiniDroneAnimationsCapListener_PARAM) {
        _ARCommandMiniDroneAnimationsCapListener = _ARCommandMiniDroneAnimationsCapListener_PARAM;
    }

    private ARCommandMiniDroneMediaRecordPictureListener _ARCommandMiniDroneMediaRecordPictureListener;

    /**
     * Set the listener for the command <code>MediaRecordPicture</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMediaRecordPictureListener (ARCommandMiniDroneMediaRecordPictureListener _ARCommandMiniDroneMediaRecordPictureListener_PARAM) {
        _ARCommandMiniDroneMediaRecordPictureListener = _ARCommandMiniDroneMediaRecordPictureListener_PARAM;
    }

    private ARCommandMiniDroneMediaRecordPictureV2Listener _ARCommandMiniDroneMediaRecordPictureV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordPictureV2</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMediaRecordPictureV2Listener (ARCommandMiniDroneMediaRecordPictureV2Listener _ARCommandMiniDroneMediaRecordPictureV2Listener_PARAM) {
        _ARCommandMiniDroneMediaRecordPictureV2Listener = _ARCommandMiniDroneMediaRecordPictureV2Listener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsMaxAltitudeListener _ARCommandMiniDronePilotingSettingsMaxAltitudeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxAltitude</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsMaxAltitudeListener (ARCommandMiniDronePilotingSettingsMaxAltitudeListener _ARCommandMiniDronePilotingSettingsMaxAltitudeListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsMaxAltitudeListener = _ARCommandMiniDronePilotingSettingsMaxAltitudeListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsMaxTiltListener _ARCommandMiniDronePilotingSettingsMaxTiltListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxTilt</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsMaxTiltListener (ARCommandMiniDronePilotingSettingsMaxTiltListener _ARCommandMiniDronePilotingSettingsMaxTiltListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsMaxTiltListener = _ARCommandMiniDronePilotingSettingsMaxTiltListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsBankedTurnListener _ARCommandMiniDronePilotingSettingsBankedTurnListener;

    /**
     * Set the listener for the command <code>PilotingSettingsBankedTurn</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsBankedTurnListener (ARCommandMiniDronePilotingSettingsBankedTurnListener _ARCommandMiniDronePilotingSettingsBankedTurnListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsBankedTurnListener = _ARCommandMiniDronePilotingSettingsBankedTurnListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsMaxThrottleListener _ARCommandMiniDronePilotingSettingsMaxThrottleListener;

    /**
     * Set the listener for the command <code>PilotingSettingsMaxThrottle</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsMaxThrottleListener (ARCommandMiniDronePilotingSettingsMaxThrottleListener _ARCommandMiniDronePilotingSettingsMaxThrottleListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsMaxThrottleListener = _ARCommandMiniDronePilotingSettingsMaxThrottleListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener _ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener;

    /**
     * Set the listener for the command <code>PilotingSettingsPreferredPilotingMode</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsPreferredPilotingModeListener (ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener _ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener = _ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener _ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxVerticalSpeed</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsMaxVerticalSpeedListener (ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener _ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener = _ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener _ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxRotationSpeed</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsMaxRotationSpeedListener (ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener _ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener = _ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsWheelsListener _ARCommandMiniDroneSpeedSettingsWheelsListener;

    /**
     * Set the listener for the command <code>SpeedSettingsWheels</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsWheelsListener (ARCommandMiniDroneSpeedSettingsWheelsListener _ARCommandMiniDroneSpeedSettingsWheelsListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsWheelsListener = _ARCommandMiniDroneSpeedSettingsWheelsListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener _ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxHorizontalSpeed</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsMaxHorizontalSpeedListener (ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener _ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener = _ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener _ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsMaxPlaneModeRotationSpeed</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener (ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener _ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener = _ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener_PARAM;
    }

    private ARCommandMiniDroneSettingsCutOutModeListener _ARCommandMiniDroneSettingsCutOutModeListener;

    /**
     * Set the listener for the command <code>SettingsCutOutMode</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSettingsCutOutModeListener (ARCommandMiniDroneSettingsCutOutModeListener _ARCommandMiniDroneSettingsCutOutModeListener_PARAM) {
        _ARCommandMiniDroneSettingsCutOutModeListener = _ARCommandMiniDroneSettingsCutOutModeListener_PARAM;
    }

    private ARCommandMiniDroneGPSControllerLatitudeForRunListener _ARCommandMiniDroneGPSControllerLatitudeForRunListener;

    /**
     * Set the listener for the command <code>GPSControllerLatitudeForRun</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneGPSControllerLatitudeForRunListener (ARCommandMiniDroneGPSControllerLatitudeForRunListener _ARCommandMiniDroneGPSControllerLatitudeForRunListener_PARAM) {
        _ARCommandMiniDroneGPSControllerLatitudeForRunListener = _ARCommandMiniDroneGPSControllerLatitudeForRunListener_PARAM;
    }

    private ARCommandMiniDroneGPSControllerLongitudeForRunListener _ARCommandMiniDroneGPSControllerLongitudeForRunListener;

    /**
     * Set the listener for the command <code>GPSControllerLongitudeForRun</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneGPSControllerLongitudeForRunListener (ARCommandMiniDroneGPSControllerLongitudeForRunListener _ARCommandMiniDroneGPSControllerLongitudeForRunListener_PARAM) {
        _ARCommandMiniDroneGPSControllerLongitudeForRunListener = _ARCommandMiniDroneGPSControllerLongitudeForRunListener_PARAM;
    }

    private ARCommandMiniDroneConfigurationControllerTypeListener _ARCommandMiniDroneConfigurationControllerTypeListener;

    /**
     * Set the listener for the command <code>ConfigurationControllerType</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneConfigurationControllerTypeListener (ARCommandMiniDroneConfigurationControllerTypeListener _ARCommandMiniDroneConfigurationControllerTypeListener_PARAM) {
        _ARCommandMiniDroneConfigurationControllerTypeListener = _ARCommandMiniDroneConfigurationControllerTypeListener_PARAM;
    }

    private ARCommandMiniDroneConfigurationControllerNameListener _ARCommandMiniDroneConfigurationControllerNameListener;

    /**
     * Set the listener for the command <code>ConfigurationControllerName</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneConfigurationControllerNameListener (ARCommandMiniDroneConfigurationControllerNameListener _ARCommandMiniDroneConfigurationControllerNameListener_PARAM) {
        _ARCommandMiniDroneConfigurationControllerNameListener = _ARCommandMiniDroneConfigurationControllerNameListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryLightControlListener _ARCommandMiniDroneUsbAccessoryLightControlListener;

    /**
     * Set the listener for the command <code>UsbAccessoryLightControl</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryLightControlListener (ARCommandMiniDroneUsbAccessoryLightControlListener _ARCommandMiniDroneUsbAccessoryLightControlListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryLightControlListener = _ARCommandMiniDroneUsbAccessoryLightControlListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryClawControlListener _ARCommandMiniDroneUsbAccessoryClawControlListener;

    /**
     * Set the listener for the command <code>UsbAccessoryClawControl</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryClawControlListener (ARCommandMiniDroneUsbAccessoryClawControlListener _ARCommandMiniDroneUsbAccessoryClawControlListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryClawControlListener = _ARCommandMiniDroneUsbAccessoryClawControlListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryGunControlListener _ARCommandMiniDroneUsbAccessoryGunControlListener;

    /**
     * Set the listener for the command <code>UsbAccessoryGunControl</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryGunControlListener (ARCommandMiniDroneUsbAccessoryGunControlListener _ARCommandMiniDroneUsbAccessoryGunControlListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryGunControlListener = _ARCommandMiniDroneUsbAccessoryGunControlListener_PARAM;
    }

    private ARCommandMiniDroneRemoteControllerSetPairedRemoteListener _ARCommandMiniDroneRemoteControllerSetPairedRemoteListener;

    /**
     * Set the listener for the command <code>RemoteControllerSetPairedRemote</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneRemoteControllerSetPairedRemoteListener (ARCommandMiniDroneRemoteControllerSetPairedRemoteListener _ARCommandMiniDroneRemoteControllerSetPairedRemoteListener_PARAM) {
        _ARCommandMiniDroneRemoteControllerSetPairedRemoteListener = _ARCommandMiniDroneRemoteControllerSetPairedRemoteListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsAutorecordListener _ARCommandMiniDroneVideoSettingsAutorecordListener;

    /**
     * Set the listener for the command <code>VideoSettingsAutorecord</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsAutorecordListener (ARCommandMiniDroneVideoSettingsAutorecordListener _ARCommandMiniDroneVideoSettingsAutorecordListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsAutorecordListener = _ARCommandMiniDroneVideoSettingsAutorecordListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsElectricFrequencyListener _ARCommandMiniDroneVideoSettingsElectricFrequencyListener;

    /**
     * Set the listener for the command <code>VideoSettingsElectricFrequency</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsElectricFrequencyListener (ARCommandMiniDroneVideoSettingsElectricFrequencyListener _ARCommandMiniDroneVideoSettingsElectricFrequencyListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsElectricFrequencyListener = _ARCommandMiniDroneVideoSettingsElectricFrequencyListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsVideoResolutionListener _ARCommandMiniDroneVideoSettingsVideoResolutionListener;

    /**
     * Set the listener for the command <code>VideoSettingsVideoResolution</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsVideoResolutionListener (ARCommandMiniDroneVideoSettingsVideoResolutionListener _ARCommandMiniDroneVideoSettingsVideoResolutionListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsVideoResolutionListener = _ARCommandMiniDroneVideoSettingsVideoResolutionListener_PARAM;
    }

    private ARCommandMiniDroneMinicamPictureListener _ARCommandMiniDroneMinicamPictureListener;

    /**
     * Set the listener for the command <code>MinicamPicture</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamPictureListener (ARCommandMiniDroneMinicamPictureListener _ARCommandMiniDroneMinicamPictureListener_PARAM) {
        _ARCommandMiniDroneMinicamPictureListener = _ARCommandMiniDroneMinicamPictureListener_PARAM;
    }

    private ARCommandMiniDroneMinicamVideoListener _ARCommandMiniDroneMinicamVideoListener;

    /**
     * Set the listener for the command <code>MinicamVideo</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamVideoListener (ARCommandMiniDroneMinicamVideoListener _ARCommandMiniDroneMinicamVideoListener_PARAM) {
        _ARCommandMiniDroneMinicamVideoListener = _ARCommandMiniDroneMinicamVideoListener_PARAM;
    }

    private ARCommandMiniDroneMinicamMassStorageFormatListener _ARCommandMiniDroneMinicamMassStorageFormatListener;

    /**
     * Set the listener for the command <code>MinicamMassStorageFormat</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamMassStorageFormatListener (ARCommandMiniDroneMinicamMassStorageFormatListener _ARCommandMiniDroneMinicamMassStorageFormatListener_PARAM) {
        _ARCommandMiniDroneMinicamMassStorageFormatListener = _ARCommandMiniDroneMinicamMassStorageFormatListener_PARAM;
    }

    private ARCommandMiniDronePilotingStateFlatTrimChangedListener _ARCommandMiniDronePilotingStateFlatTrimChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlatTrimChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStateFlatTrimChangedListener (ARCommandMiniDronePilotingStateFlatTrimChangedListener _ARCommandMiniDronePilotingStateFlatTrimChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStateFlatTrimChangedListener = _ARCommandMiniDronePilotingStateFlatTrimChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStateFlyingStateChangedListener _ARCommandMiniDronePilotingStateFlyingStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlyingStateChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStateFlyingStateChangedListener (ARCommandMiniDronePilotingStateFlyingStateChangedListener _ARCommandMiniDronePilotingStateFlyingStateChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStateFlyingStateChangedListener = _ARCommandMiniDronePilotingStateFlyingStateChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStateAlertStateChangedListener _ARCommandMiniDronePilotingStateAlertStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAlertStateChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStateAlertStateChangedListener (ARCommandMiniDronePilotingStateAlertStateChangedListener _ARCommandMiniDronePilotingStateAlertStateChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStateAlertStateChangedListener = _ARCommandMiniDronePilotingStateAlertStateChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener _ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAutoTakeOffModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStateAutoTakeOffModeChangedListener (ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener _ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener = _ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStateFlyingModeChangedListener _ARCommandMiniDronePilotingStateFlyingModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlyingModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStateFlyingModeChangedListener (ARCommandMiniDronePilotingStateFlyingModeChangedListener _ARCommandMiniDronePilotingStateFlyingModeChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStateFlyingModeChangedListener = _ARCommandMiniDronePilotingStateFlyingModeChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener _ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener;

    /**
     * Set the listener for the command <code>PilotingStatePlaneGearBoxChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStatePlaneGearBoxChangedListener (ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener _ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener = _ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingStatePilotingModeChangedListener _ARCommandMiniDronePilotingStatePilotingModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStatePilotingModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingStatePilotingModeChangedListener (ARCommandMiniDronePilotingStatePilotingModeChangedListener _ARCommandMiniDronePilotingStatePilotingModeChangedListener_PARAM) {
        _ARCommandMiniDronePilotingStatePilotingModeChangedListener = _ARCommandMiniDronePilotingStatePilotingModeChangedListener_PARAM;
    }

    private ARCommandMiniDroneMediaRecordStatePictureStateChangedListener _ARCommandMiniDroneMediaRecordStatePictureStateChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMediaRecordStatePictureStateChangedListener (ARCommandMiniDroneMediaRecordStatePictureStateChangedListener _ARCommandMiniDroneMediaRecordStatePictureStateChangedListener_PARAM) {
        _ARCommandMiniDroneMediaRecordStatePictureStateChangedListener = _ARCommandMiniDroneMediaRecordStatePictureStateChangedListener_PARAM;
    }

    private ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener _ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChangedV2</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMediaRecordStatePictureStateChangedV2Listener (ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener _ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener_PARAM) {
        _ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener = _ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener_PARAM;
    }

    private ARCommandMiniDroneMediaRecordEventPictureEventChangedListener _ARCommandMiniDroneMediaRecordEventPictureEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventPictureEventChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMediaRecordEventPictureEventChangedListener (ARCommandMiniDroneMediaRecordEventPictureEventChangedListener _ARCommandMiniDroneMediaRecordEventPictureEventChangedListener_PARAM) {
        _ARCommandMiniDroneMediaRecordEventPictureEventChangedListener = _ARCommandMiniDroneMediaRecordEventPictureEventChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener _ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxAltitudeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsStateMaxAltitudeChangedListener (ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener _ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener = _ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener _ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxTiltChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsStateMaxTiltChangedListener (ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener _ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener = _ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener _ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateBankedTurnChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsStateBankedTurnChangedListener (ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener _ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener = _ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener _ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateMaxThrottleChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsStateMaxThrottleChangedListener (ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener _ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener = _ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener_PARAM;
    }

    private ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener _ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStatePreferredPilotingModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener (ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener _ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener_PARAM) {
        _ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener = _ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxVerticalSpeedChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener (ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener = _ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxRotationSpeedChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener (ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener = _ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener _ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateWheelsChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsStateWheelsChangedListener (ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener _ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener = _ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxHorizontalSpeedChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener (ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener = _ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener_PARAM;
    }

    private ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener;

    /**
     * Set the listener for the command <code>SpeedSettingsStateMaxPlaneModeRotationSpeedChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener (ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener _ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener_PARAM) {
        _ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener = _ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener_PARAM;
    }

    private ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener _ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductMotorsVersionChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSettingsStateProductMotorsVersionChangedListener (ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener _ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener_PARAM) {
        _ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener = _ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener_PARAM;
    }

    private ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener _ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductInertialVersionChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSettingsStateProductInertialVersionChangedListener (ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener _ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener_PARAM) {
        _ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener = _ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener_PARAM;
    }

    private ARCommandMiniDroneSettingsStateCutOutModeChangedListener _ARCommandMiniDroneSettingsStateCutOutModeChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateCutOutModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneSettingsStateCutOutModeChangedListener (ARCommandMiniDroneSettingsStateCutOutModeChangedListener _ARCommandMiniDroneSettingsStateCutOutModeChangedListener_PARAM) {
        _ARCommandMiniDroneSettingsStateCutOutModeChangedListener = _ARCommandMiniDroneSettingsStateCutOutModeChangedListener_PARAM;
    }

    private ARCommandMiniDroneFloodControlStateFloodControlChangedListener _ARCommandMiniDroneFloodControlStateFloodControlChangedListener;

    /**
     * Set the listener for the command <code>FloodControlStateFloodControlChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneFloodControlStateFloodControlChangedListener (ARCommandMiniDroneFloodControlStateFloodControlChangedListener _ARCommandMiniDroneFloodControlStateFloodControlChangedListener_PARAM) {
        _ARCommandMiniDroneFloodControlStateFloodControlChangedListener = _ARCommandMiniDroneFloodControlStateFloodControlChangedListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryStateLightStateListener _ARCommandMiniDroneUsbAccessoryStateLightStateListener;

    /**
     * Set the listener for the command <code>UsbAccessoryStateLightState</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryStateLightStateListener (ARCommandMiniDroneUsbAccessoryStateLightStateListener _ARCommandMiniDroneUsbAccessoryStateLightStateListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryStateLightStateListener = _ARCommandMiniDroneUsbAccessoryStateLightStateListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryStateClawStateListener _ARCommandMiniDroneUsbAccessoryStateClawStateListener;

    /**
     * Set the listener for the command <code>UsbAccessoryStateClawState</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryStateClawStateListener (ARCommandMiniDroneUsbAccessoryStateClawStateListener _ARCommandMiniDroneUsbAccessoryStateClawStateListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryStateClawStateListener = _ARCommandMiniDroneUsbAccessoryStateClawStateListener_PARAM;
    }

    private ARCommandMiniDroneUsbAccessoryStateGunStateListener _ARCommandMiniDroneUsbAccessoryStateGunStateListener;

    /**
     * Set the listener for the command <code>UsbAccessoryStateGunState</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneUsbAccessoryStateGunStateListener (ARCommandMiniDroneUsbAccessoryStateGunStateListener _ARCommandMiniDroneUsbAccessoryStateGunStateListener_PARAM) {
        _ARCommandMiniDroneUsbAccessoryStateGunStateListener = _ARCommandMiniDroneUsbAccessoryStateGunStateListener_PARAM;
    }

    private ARCommandMiniDroneNavigationDataStateDronePositionListener _ARCommandMiniDroneNavigationDataStateDronePositionListener;

    /**
     * Set the listener for the command <code>NavigationDataStateDronePosition</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneNavigationDataStateDronePositionListener (ARCommandMiniDroneNavigationDataStateDronePositionListener _ARCommandMiniDroneNavigationDataStateDronePositionListener_PARAM) {
        _ARCommandMiniDroneNavigationDataStateDronePositionListener = _ARCommandMiniDroneNavigationDataStateDronePositionListener_PARAM;
    }

    private ARCommandMiniDroneNavigationDataStateDroneSpeedListener _ARCommandMiniDroneNavigationDataStateDroneSpeedListener;

    /**
     * Set the listener for the command <code>NavigationDataStateDroneSpeed</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneNavigationDataStateDroneSpeedListener (ARCommandMiniDroneNavigationDataStateDroneSpeedListener _ARCommandMiniDroneNavigationDataStateDroneSpeedListener_PARAM) {
        _ARCommandMiniDroneNavigationDataStateDroneSpeedListener = _ARCommandMiniDroneNavigationDataStateDroneSpeedListener_PARAM;
    }

    private ARCommandMiniDroneNavigationDataStateDroneAltitudeListener _ARCommandMiniDroneNavigationDataStateDroneAltitudeListener;

    /**
     * Set the listener for the command <code>NavigationDataStateDroneAltitude</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneNavigationDataStateDroneAltitudeListener (ARCommandMiniDroneNavigationDataStateDroneAltitudeListener _ARCommandMiniDroneNavigationDataStateDroneAltitudeListener_PARAM) {
        _ARCommandMiniDroneNavigationDataStateDroneAltitudeListener = _ARCommandMiniDroneNavigationDataStateDroneAltitudeListener_PARAM;
    }

    private ARCommandMiniDroneNavigationDataStateDroneQuaternionListener _ARCommandMiniDroneNavigationDataStateDroneQuaternionListener;

    /**
     * Set the listener for the command <code>NavigationDataStateDroneQuaternion</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneNavigationDataStateDroneQuaternionListener (ARCommandMiniDroneNavigationDataStateDroneQuaternionListener _ARCommandMiniDroneNavigationDataStateDroneQuaternionListener_PARAM) {
        _ARCommandMiniDroneNavigationDataStateDroneQuaternionListener = _ARCommandMiniDroneNavigationDataStateDroneQuaternionListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStatePowerModeChangedListener _ARCommandMiniDroneMinicamStatePowerModeChangedListener;

    /**
     * Set the listener for the command <code>MinicamStatePowerModeChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStatePowerModeChangedListener (ARCommandMiniDroneMinicamStatePowerModeChangedListener _ARCommandMiniDroneMinicamStatePowerModeChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStatePowerModeChangedListener = _ARCommandMiniDroneMinicamStatePowerModeChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStateProductSerialChangedListener _ARCommandMiniDroneMinicamStateProductSerialChangedListener;

    /**
     * Set the listener for the command <code>MinicamStateProductSerialChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStateProductSerialChangedListener (ARCommandMiniDroneMinicamStateProductSerialChangedListener _ARCommandMiniDroneMinicamStateProductSerialChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStateProductSerialChangedListener = _ARCommandMiniDroneMinicamStateProductSerialChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStateStateChangedListener _ARCommandMiniDroneMinicamStateStateChangedListener;

    /**
     * Set the listener for the command <code>MinicamStateStateChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStateStateChangedListener (ARCommandMiniDroneMinicamStateStateChangedListener _ARCommandMiniDroneMinicamStateStateChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStateStateChangedListener = _ARCommandMiniDroneMinicamStateStateChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStateVersionChangedListener _ARCommandMiniDroneMinicamStateVersionChangedListener;

    /**
     * Set the listener for the command <code>MinicamStateVersionChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStateVersionChangedListener (ARCommandMiniDroneMinicamStateVersionChangedListener _ARCommandMiniDroneMinicamStateVersionChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStateVersionChangedListener = _ARCommandMiniDroneMinicamStateVersionChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStatePictureChangedListener _ARCommandMiniDroneMinicamStatePictureChangedListener;

    /**
     * Set the listener for the command <code>MinicamStatePictureChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStatePictureChangedListener (ARCommandMiniDroneMinicamStatePictureChangedListener _ARCommandMiniDroneMinicamStatePictureChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStatePictureChangedListener = _ARCommandMiniDroneMinicamStatePictureChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStateVideoStateChangedListener _ARCommandMiniDroneMinicamStateVideoStateChangedListener;

    /**
     * Set the listener for the command <code>MinicamStateVideoStateChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStateVideoStateChangedListener (ARCommandMiniDroneMinicamStateVideoStateChangedListener _ARCommandMiniDroneMinicamStateVideoStateChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStateVideoStateChangedListener = _ARCommandMiniDroneMinicamStateVideoStateChangedListener_PARAM;
    }

    private ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener _ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener;

    /**
     * Set the listener for the command <code>MinicamStateMassStorageFormatChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneMinicamStateMassStorageFormatChangedListener (ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener _ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener_PARAM) {
        _ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener = _ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener _ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateAutorecordChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsStateAutorecordChangedListener (ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener _ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener = _ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener _ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateElectricFrequencyChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsStateElectricFrequencyChangedListener (ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener _ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener = _ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener_PARAM;
    }

    private ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener _ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateVideoResolutionChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneVideoSettingsStateVideoResolutionChangedListener (ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener _ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener_PARAM) {
        _ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener = _ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener_PARAM;
    }

    private ARCommandMiniDroneRemoteControllerStateConnectionChangedListener _ARCommandMiniDroneRemoteControllerStateConnectionChangedListener;

    /**
     * Set the listener for the command <code>RemoteControllerStateConnectionChanged</code> in feature <code>MiniDrone</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setMiniDroneRemoteControllerStateConnectionChangedListener (ARCommandMiniDroneRemoteControllerStateConnectionChangedListener _ARCommandMiniDroneRemoteControllerStateConnectionChangedListener_PARAM) {
        _ARCommandMiniDroneRemoteControllerStateConnectionChangedListener = _ARCommandMiniDroneRemoteControllerStateConnectionChangedListener_PARAM;
    }


    private ARCommandPowerupPilotingPCMDListener _ARCommandPowerupPilotingPCMDListener;

    /**
     * Set the listener for the command <code>PilotingPCMD</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingPCMDListener (ARCommandPowerupPilotingPCMDListener _ARCommandPowerupPilotingPCMDListener_PARAM) {
        _ARCommandPowerupPilotingPCMDListener = _ARCommandPowerupPilotingPCMDListener_PARAM;
    }

    private ARCommandPowerupPilotingUserTakeOffListener _ARCommandPowerupPilotingUserTakeOffListener;

    /**
     * Set the listener for the command <code>PilotingUserTakeOff</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingUserTakeOffListener (ARCommandPowerupPilotingUserTakeOffListener _ARCommandPowerupPilotingUserTakeOffListener_PARAM) {
        _ARCommandPowerupPilotingUserTakeOffListener = _ARCommandPowerupPilotingUserTakeOffListener_PARAM;
    }

    private ARCommandPowerupPilotingMotorModeListener _ARCommandPowerupPilotingMotorModeListener;

    /**
     * Set the listener for the command <code>PilotingMotorMode</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingMotorModeListener (ARCommandPowerupPilotingMotorModeListener _ARCommandPowerupPilotingMotorModeListener_PARAM) {
        _ARCommandPowerupPilotingMotorModeListener = _ARCommandPowerupPilotingMotorModeListener_PARAM;
    }

    private ARCommandPowerupPilotingSettingsSetListener _ARCommandPowerupPilotingSettingsSetListener;

    /**
     * Set the listener for the command <code>PilotingSettingsSet</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingSettingsSetListener (ARCommandPowerupPilotingSettingsSetListener _ARCommandPowerupPilotingSettingsSetListener_PARAM) {
        _ARCommandPowerupPilotingSettingsSetListener = _ARCommandPowerupPilotingSettingsSetListener_PARAM;
    }

    private ARCommandPowerupMediaRecordPictureV2Listener _ARCommandPowerupMediaRecordPictureV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordPictureV2</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordPictureV2Listener (ARCommandPowerupMediaRecordPictureV2Listener _ARCommandPowerupMediaRecordPictureV2Listener_PARAM) {
        _ARCommandPowerupMediaRecordPictureV2Listener = _ARCommandPowerupMediaRecordPictureV2Listener_PARAM;
    }

    private ARCommandPowerupMediaRecordVideoV2Listener _ARCommandPowerupMediaRecordVideoV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordVideoV2</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordVideoV2Listener (ARCommandPowerupMediaRecordVideoV2Listener _ARCommandPowerupMediaRecordVideoV2Listener_PARAM) {
        _ARCommandPowerupMediaRecordVideoV2Listener = _ARCommandPowerupMediaRecordVideoV2Listener_PARAM;
    }

    private ARCommandPowerupNetworkSettingsWifiSelectionListener _ARCommandPowerupNetworkSettingsWifiSelectionListener;

    /**
     * Set the listener for the command <code>NetworkSettingsWifiSelection</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkSettingsWifiSelectionListener (ARCommandPowerupNetworkSettingsWifiSelectionListener _ARCommandPowerupNetworkSettingsWifiSelectionListener_PARAM) {
        _ARCommandPowerupNetworkSettingsWifiSelectionListener = _ARCommandPowerupNetworkSettingsWifiSelectionListener_PARAM;
    }

    private ARCommandPowerupNetworkWifiScanListener _ARCommandPowerupNetworkWifiScanListener;

    /**
     * Set the listener for the command <code>NetworkWifiScan</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkWifiScanListener (ARCommandPowerupNetworkWifiScanListener _ARCommandPowerupNetworkWifiScanListener_PARAM) {
        _ARCommandPowerupNetworkWifiScanListener = _ARCommandPowerupNetworkWifiScanListener_PARAM;
    }

    private ARCommandPowerupNetworkWifiAuthChannelListener _ARCommandPowerupNetworkWifiAuthChannelListener;

    /**
     * Set the listener for the command <code>NetworkWifiAuthChannel</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkWifiAuthChannelListener (ARCommandPowerupNetworkWifiAuthChannelListener _ARCommandPowerupNetworkWifiAuthChannelListener_PARAM) {
        _ARCommandPowerupNetworkWifiAuthChannelListener = _ARCommandPowerupNetworkWifiAuthChannelListener_PARAM;
    }

    private ARCommandPowerupMediaStreamingVideoEnableListener _ARCommandPowerupMediaStreamingVideoEnableListener;

    /**
     * Set the listener for the command <code>MediaStreamingVideoEnable</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaStreamingVideoEnableListener (ARCommandPowerupMediaStreamingVideoEnableListener _ARCommandPowerupMediaStreamingVideoEnableListener_PARAM) {
        _ARCommandPowerupMediaStreamingVideoEnableListener = _ARCommandPowerupMediaStreamingVideoEnableListener_PARAM;
    }

    private ARCommandPowerupVideoSettingsAutorecordListener _ARCommandPowerupVideoSettingsAutorecordListener;

    /**
     * Set the listener for the command <code>VideoSettingsAutorecord</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupVideoSettingsAutorecordListener (ARCommandPowerupVideoSettingsAutorecordListener _ARCommandPowerupVideoSettingsAutorecordListener_PARAM) {
        _ARCommandPowerupVideoSettingsAutorecordListener = _ARCommandPowerupVideoSettingsAutorecordListener_PARAM;
    }

    private ARCommandPowerupVideoSettingsVideoModeListener _ARCommandPowerupVideoSettingsVideoModeListener;

    /**
     * Set the listener for the command <code>VideoSettingsVideoMode</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupVideoSettingsVideoModeListener (ARCommandPowerupVideoSettingsVideoModeListener _ARCommandPowerupVideoSettingsVideoModeListener_PARAM) {
        _ARCommandPowerupVideoSettingsVideoModeListener = _ARCommandPowerupVideoSettingsVideoModeListener_PARAM;
    }

    private ARCommandPowerupSoundsBuzzListener _ARCommandPowerupSoundsBuzzListener;

    /**
     * Set the listener for the command <code>SoundsBuzz</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupSoundsBuzzListener (ARCommandPowerupSoundsBuzzListener _ARCommandPowerupSoundsBuzzListener_PARAM) {
        _ARCommandPowerupSoundsBuzzListener = _ARCommandPowerupSoundsBuzzListener_PARAM;
    }

    private ARCommandPowerupPilotingStateAlertStateChangedListener _ARCommandPowerupPilotingStateAlertStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAlertStateChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingStateAlertStateChangedListener (ARCommandPowerupPilotingStateAlertStateChangedListener _ARCommandPowerupPilotingStateAlertStateChangedListener_PARAM) {
        _ARCommandPowerupPilotingStateAlertStateChangedListener = _ARCommandPowerupPilotingStateAlertStateChangedListener_PARAM;
    }

    private ARCommandPowerupPilotingStateFlyingStateChangedListener _ARCommandPowerupPilotingStateFlyingStateChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateFlyingStateChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingStateFlyingStateChangedListener (ARCommandPowerupPilotingStateFlyingStateChangedListener _ARCommandPowerupPilotingStateFlyingStateChangedListener_PARAM) {
        _ARCommandPowerupPilotingStateFlyingStateChangedListener = _ARCommandPowerupPilotingStateFlyingStateChangedListener_PARAM;
    }

    private ARCommandPowerupPilotingStateMotorModeChangedListener _ARCommandPowerupPilotingStateMotorModeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateMotorModeChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingStateMotorModeChangedListener (ARCommandPowerupPilotingStateMotorModeChangedListener _ARCommandPowerupPilotingStateMotorModeChangedListener_PARAM) {
        _ARCommandPowerupPilotingStateMotorModeChangedListener = _ARCommandPowerupPilotingStateMotorModeChangedListener_PARAM;
    }

    private ARCommandPowerupPilotingStateAttitudeChangedListener _ARCommandPowerupPilotingStateAttitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAttitudeChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingStateAttitudeChangedListener (ARCommandPowerupPilotingStateAttitudeChangedListener _ARCommandPowerupPilotingStateAttitudeChangedListener_PARAM) {
        _ARCommandPowerupPilotingStateAttitudeChangedListener = _ARCommandPowerupPilotingStateAttitudeChangedListener_PARAM;
    }

    private ARCommandPowerupPilotingStateAltitudeChangedListener _ARCommandPowerupPilotingStateAltitudeChangedListener;

    /**
     * Set the listener for the command <code>PilotingStateAltitudeChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingStateAltitudeChangedListener (ARCommandPowerupPilotingStateAltitudeChangedListener _ARCommandPowerupPilotingStateAltitudeChangedListener_PARAM) {
        _ARCommandPowerupPilotingStateAltitudeChangedListener = _ARCommandPowerupPilotingStateAltitudeChangedListener_PARAM;
    }

    private ARCommandPowerupPilotingSettingsStateSettingChangedListener _ARCommandPowerupPilotingSettingsStateSettingChangedListener;

    /**
     * Set the listener for the command <code>PilotingSettingsStateSettingChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupPilotingSettingsStateSettingChangedListener (ARCommandPowerupPilotingSettingsStateSettingChangedListener _ARCommandPowerupPilotingSettingsStateSettingChangedListener_PARAM) {
        _ARCommandPowerupPilotingSettingsStateSettingChangedListener = _ARCommandPowerupPilotingSettingsStateSettingChangedListener_PARAM;
    }

    private ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener _ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStatePictureStateChangedV2</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordStatePictureStateChangedV2Listener (ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener _ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener_PARAM) {
        _ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener = _ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener_PARAM;
    }

    private ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener _ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener;

    /**
     * Set the listener for the command <code>MediaRecordStateVideoStateChangedV2</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordStateVideoStateChangedV2Listener (ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener _ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener_PARAM) {
        _ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener = _ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener_PARAM;
    }

    private ARCommandPowerupMediaRecordEventPictureEventChangedListener _ARCommandPowerupMediaRecordEventPictureEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventPictureEventChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordEventPictureEventChangedListener (ARCommandPowerupMediaRecordEventPictureEventChangedListener _ARCommandPowerupMediaRecordEventPictureEventChangedListener_PARAM) {
        _ARCommandPowerupMediaRecordEventPictureEventChangedListener = _ARCommandPowerupMediaRecordEventPictureEventChangedListener_PARAM;
    }

    private ARCommandPowerupMediaRecordEventVideoEventChangedListener _ARCommandPowerupMediaRecordEventVideoEventChangedListener;

    /**
     * Set the listener for the command <code>MediaRecordEventVideoEventChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaRecordEventVideoEventChangedListener (ARCommandPowerupMediaRecordEventVideoEventChangedListener _ARCommandPowerupMediaRecordEventVideoEventChangedListener_PARAM) {
        _ARCommandPowerupMediaRecordEventVideoEventChangedListener = _ARCommandPowerupMediaRecordEventVideoEventChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener _ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener;

    /**
     * Set the listener for the command <code>NetworkSettingsStateWifiSelectionChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkSettingsStateWifiSelectionChangedListener (ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener _ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener_PARAM) {
        _ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener = _ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkStateWifiScanListChangedListener _ARCommandPowerupNetworkStateWifiScanListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiScanListChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkStateWifiScanListChangedListener (ARCommandPowerupNetworkStateWifiScanListChangedListener _ARCommandPowerupNetworkStateWifiScanListChangedListener_PARAM) {
        _ARCommandPowerupNetworkStateWifiScanListChangedListener = _ARCommandPowerupNetworkStateWifiScanListChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkStateAllWifiScanChangedListener _ARCommandPowerupNetworkStateAllWifiScanChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiScanChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkStateAllWifiScanChangedListener (ARCommandPowerupNetworkStateAllWifiScanChangedListener _ARCommandPowerupNetworkStateAllWifiScanChangedListener_PARAM) {
        _ARCommandPowerupNetworkStateAllWifiScanChangedListener = _ARCommandPowerupNetworkStateAllWifiScanChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener _ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateWifiAuthChannelListChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkStateWifiAuthChannelListChangedListener (ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener _ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener_PARAM) {
        _ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener = _ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener _ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateAllWifiAuthChannelChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkStateAllWifiAuthChannelChangedListener (ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener _ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener_PARAM) {
        _ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener = _ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener_PARAM;
    }

    private ARCommandPowerupNetworkStateLinkQualityChangedListener _ARCommandPowerupNetworkStateLinkQualityChangedListener;

    /**
     * Set the listener for the command <code>NetworkStateLinkQualityChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupNetworkStateLinkQualityChangedListener (ARCommandPowerupNetworkStateLinkQualityChangedListener _ARCommandPowerupNetworkStateLinkQualityChangedListener_PARAM) {
        _ARCommandPowerupNetworkStateLinkQualityChangedListener = _ARCommandPowerupNetworkStateLinkQualityChangedListener_PARAM;
    }

    private ARCommandPowerupMediaStreamingStateVideoEnableChangedListener _ARCommandPowerupMediaStreamingStateVideoEnableChangedListener;

    /**
     * Set the listener for the command <code>MediaStreamingStateVideoEnableChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupMediaStreamingStateVideoEnableChangedListener (ARCommandPowerupMediaStreamingStateVideoEnableChangedListener _ARCommandPowerupMediaStreamingStateVideoEnableChangedListener_PARAM) {
        _ARCommandPowerupMediaStreamingStateVideoEnableChangedListener = _ARCommandPowerupMediaStreamingStateVideoEnableChangedListener_PARAM;
    }

    private ARCommandPowerupVideoSettingsStateAutorecordChangedListener _ARCommandPowerupVideoSettingsStateAutorecordChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateAutorecordChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupVideoSettingsStateAutorecordChangedListener (ARCommandPowerupVideoSettingsStateAutorecordChangedListener _ARCommandPowerupVideoSettingsStateAutorecordChangedListener_PARAM) {
        _ARCommandPowerupVideoSettingsStateAutorecordChangedListener = _ARCommandPowerupVideoSettingsStateAutorecordChangedListener_PARAM;
    }

    private ARCommandPowerupVideoSettingsStateVideoModeChangedListener _ARCommandPowerupVideoSettingsStateVideoModeChangedListener;

    /**
     * Set the listener for the command <code>VideoSettingsStateVideoModeChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupVideoSettingsStateVideoModeChangedListener (ARCommandPowerupVideoSettingsStateVideoModeChangedListener _ARCommandPowerupVideoSettingsStateVideoModeChangedListener_PARAM) {
        _ARCommandPowerupVideoSettingsStateVideoModeChangedListener = _ARCommandPowerupVideoSettingsStateVideoModeChangedListener_PARAM;
    }

    private ARCommandPowerupSoundsStateBuzzChangedListener _ARCommandPowerupSoundsStateBuzzChangedListener;

    /**
     * Set the listener for the command <code>SoundsStateBuzzChanged</code> in feature <code>Powerup</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setPowerupSoundsStateBuzzChangedListener (ARCommandPowerupSoundsStateBuzzChangedListener _ARCommandPowerupSoundsStateBuzzChangedListener_PARAM) {
        _ARCommandPowerupSoundsStateBuzzChangedListener = _ARCommandPowerupSoundsStateBuzzChangedListener_PARAM;
    }


    private ARCommandRcMonitorChannelsListener _ARCommandRcMonitorChannelsListener;

    /**
     * Set the listener for the command <code>MonitorChannels</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcMonitorChannelsListener (ARCommandRcMonitorChannelsListener _ARCommandRcMonitorChannelsListener_PARAM) {
        _ARCommandRcMonitorChannelsListener = _ARCommandRcMonitorChannelsListener_PARAM;
    }

    private ARCommandRcStartCalibrationListener _ARCommandRcStartCalibrationListener;

    /**
     * Set the listener for the command <code>StartCalibration</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcStartCalibrationListener (ARCommandRcStartCalibrationListener _ARCommandRcStartCalibrationListener_PARAM) {
        _ARCommandRcStartCalibrationListener = _ARCommandRcStartCalibrationListener_PARAM;
    }

    private ARCommandRcInvertChannelListener _ARCommandRcInvertChannelListener;

    /**
     * Set the listener for the command <code>InvertChannel</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcInvertChannelListener (ARCommandRcInvertChannelListener _ARCommandRcInvertChannelListener_PARAM) {
        _ARCommandRcInvertChannelListener = _ARCommandRcInvertChannelListener_PARAM;
    }

    private ARCommandRcAbortCalibrationListener _ARCommandRcAbortCalibrationListener;

    /**
     * Set the listener for the command <code>AbortCalibration</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcAbortCalibrationListener (ARCommandRcAbortCalibrationListener _ARCommandRcAbortCalibrationListener_PARAM) {
        _ARCommandRcAbortCalibrationListener = _ARCommandRcAbortCalibrationListener_PARAM;
    }

    private ARCommandRcResetCalibrationListener _ARCommandRcResetCalibrationListener;

    /**
     * Set the listener for the command <code>ResetCalibration</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcResetCalibrationListener (ARCommandRcResetCalibrationListener _ARCommandRcResetCalibrationListener_PARAM) {
        _ARCommandRcResetCalibrationListener = _ARCommandRcResetCalibrationListener_PARAM;
    }

    private ARCommandRcEnableReceiverListener _ARCommandRcEnableReceiverListener;

    /**
     * Set the listener for the command <code>EnableReceiver</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcEnableReceiverListener (ARCommandRcEnableReceiverListener _ARCommandRcEnableReceiverListener_PARAM) {
        _ARCommandRcEnableReceiverListener = _ARCommandRcEnableReceiverListener_PARAM;
    }

    private ARCommandRcReceiverStateListener _ARCommandRcReceiverStateListener;

    /**
     * Set the listener for the command <code>ReceiverState</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcReceiverStateListener (ARCommandRcReceiverStateListener _ARCommandRcReceiverStateListener_PARAM) {
        _ARCommandRcReceiverStateListener = _ARCommandRcReceiverStateListener_PARAM;
    }

    private ARCommandRcChannelsMonitorStateListener _ARCommandRcChannelsMonitorStateListener;

    /**
     * Set the listener for the command <code>ChannelsMonitorState</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcChannelsMonitorStateListener (ARCommandRcChannelsMonitorStateListener _ARCommandRcChannelsMonitorStateListener_PARAM) {
        _ARCommandRcChannelsMonitorStateListener = _ARCommandRcChannelsMonitorStateListener_PARAM;
    }

    private ARCommandRcChannelValueListener _ARCommandRcChannelValueListener;

    /**
     * Set the listener for the command <code>ChannelValue</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcChannelValueListener (ARCommandRcChannelValueListener _ARCommandRcChannelValueListener_PARAM) {
        _ARCommandRcChannelValueListener = _ARCommandRcChannelValueListener_PARAM;
    }

    private ARCommandRcCalibrationStateListener _ARCommandRcCalibrationStateListener;

    /**
     * Set the listener for the command <code>CalibrationState</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcCalibrationStateListener (ARCommandRcCalibrationStateListener _ARCommandRcCalibrationStateListener_PARAM) {
        _ARCommandRcCalibrationStateListener = _ARCommandRcCalibrationStateListener_PARAM;
    }

    private ARCommandRcChannelActionItemListener _ARCommandRcChannelActionItemListener;

    /**
     * Set the listener for the command <code>ChannelActionItem</code> in feature <code>Rc</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setRcChannelActionItemListener (ARCommandRcChannelActionItemListener _ARCommandRcChannelActionItemListener_PARAM) {
        _ARCommandRcChannelActionItemListener = _ARCommandRcChannelActionItemListener_PARAM;
    }


    private ARCommandSequoiaCamRadiometricCalibStartListener _ARCommandSequoiaCamRadiometricCalibStartListener;

    /**
     * Set the listener for the command <code>RadiometricCalibStart</code> in feature <code>SequoiaCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSequoiaCamRadiometricCalibStartListener (ARCommandSequoiaCamRadiometricCalibStartListener _ARCommandSequoiaCamRadiometricCalibStartListener_PARAM) {
        _ARCommandSequoiaCamRadiometricCalibStartListener = _ARCommandSequoiaCamRadiometricCalibStartListener_PARAM;
    }

    private ARCommandSequoiaCamRadiometricCalibStatusListener _ARCommandSequoiaCamRadiometricCalibStatusListener;

    /**
     * Set the listener for the command <code>RadiometricCalibStatus</code> in feature <code>SequoiaCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSequoiaCamRadiometricCalibStatusListener (ARCommandSequoiaCamRadiometricCalibStatusListener _ARCommandSequoiaCamRadiometricCalibStatusListener_PARAM) {
        _ARCommandSequoiaCamRadiometricCalibStatusListener = _ARCommandSequoiaCamRadiometricCalibStatusListener_PARAM;
    }

    private ARCommandSequoiaCamRadiometricCalibResultListener _ARCommandSequoiaCamRadiometricCalibResultListener;

    /**
     * Set the listener for the command <code>RadiometricCalibResult</code> in feature <code>SequoiaCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSequoiaCamRadiometricCalibResultListener (ARCommandSequoiaCamRadiometricCalibResultListener _ARCommandSequoiaCamRadiometricCalibResultListener_PARAM) {
        _ARCommandSequoiaCamRadiometricCalibResultListener = _ARCommandSequoiaCamRadiometricCalibResultListener_PARAM;
    }


    private ARCommandSkyControllerWifiRequestWifiListListener _ARCommandSkyControllerWifiRequestWifiListListener;

    /**
     * Set the listener for the command <code>WifiRequestWifiList</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiRequestWifiListListener (ARCommandSkyControllerWifiRequestWifiListListener _ARCommandSkyControllerWifiRequestWifiListListener_PARAM) {
        _ARCommandSkyControllerWifiRequestWifiListListener = _ARCommandSkyControllerWifiRequestWifiListListener_PARAM;
    }

    private ARCommandSkyControllerWifiRequestCurrentWifiListener _ARCommandSkyControllerWifiRequestCurrentWifiListener;

    /**
     * Set the listener for the command <code>WifiRequestCurrentWifi</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiRequestCurrentWifiListener (ARCommandSkyControllerWifiRequestCurrentWifiListener _ARCommandSkyControllerWifiRequestCurrentWifiListener_PARAM) {
        _ARCommandSkyControllerWifiRequestCurrentWifiListener = _ARCommandSkyControllerWifiRequestCurrentWifiListener_PARAM;
    }

    private ARCommandSkyControllerWifiConnectToWifiListener _ARCommandSkyControllerWifiConnectToWifiListener;

    /**
     * Set the listener for the command <code>WifiConnectToWifi</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiConnectToWifiListener (ARCommandSkyControllerWifiConnectToWifiListener _ARCommandSkyControllerWifiConnectToWifiListener_PARAM) {
        _ARCommandSkyControllerWifiConnectToWifiListener = _ARCommandSkyControllerWifiConnectToWifiListener_PARAM;
    }

    private ARCommandSkyControllerWifiForgetWifiListener _ARCommandSkyControllerWifiForgetWifiListener;

    /**
     * Set the listener for the command <code>WifiForgetWifi</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiForgetWifiListener (ARCommandSkyControllerWifiForgetWifiListener _ARCommandSkyControllerWifiForgetWifiListener_PARAM) {
        _ARCommandSkyControllerWifiForgetWifiListener = _ARCommandSkyControllerWifiForgetWifiListener_PARAM;
    }

    private ARCommandSkyControllerWifiWifiAuthChannelListener _ARCommandSkyControllerWifiWifiAuthChannelListener;

    /**
     * Set the listener for the command <code>WifiWifiAuthChannel</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiWifiAuthChannelListener (ARCommandSkyControllerWifiWifiAuthChannelListener _ARCommandSkyControllerWifiWifiAuthChannelListener_PARAM) {
        _ARCommandSkyControllerWifiWifiAuthChannelListener = _ARCommandSkyControllerWifiWifiAuthChannelListener_PARAM;
    }

    private ARCommandSkyControllerDeviceRequestDeviceListListener _ARCommandSkyControllerDeviceRequestDeviceListListener;

    /**
     * Set the listener for the command <code>DeviceRequestDeviceList</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerDeviceRequestDeviceListListener (ARCommandSkyControllerDeviceRequestDeviceListListener _ARCommandSkyControllerDeviceRequestDeviceListListener_PARAM) {
        _ARCommandSkyControllerDeviceRequestDeviceListListener = _ARCommandSkyControllerDeviceRequestDeviceListListener_PARAM;
    }

    private ARCommandSkyControllerDeviceRequestCurrentDeviceListener _ARCommandSkyControllerDeviceRequestCurrentDeviceListener;

    /**
     * Set the listener for the command <code>DeviceRequestCurrentDevice</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerDeviceRequestCurrentDeviceListener (ARCommandSkyControllerDeviceRequestCurrentDeviceListener _ARCommandSkyControllerDeviceRequestCurrentDeviceListener_PARAM) {
        _ARCommandSkyControllerDeviceRequestCurrentDeviceListener = _ARCommandSkyControllerDeviceRequestCurrentDeviceListener_PARAM;
    }

    private ARCommandSkyControllerDeviceConnectToDeviceListener _ARCommandSkyControllerDeviceConnectToDeviceListener;

    /**
     * Set the listener for the command <code>DeviceConnectToDevice</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerDeviceConnectToDeviceListener (ARCommandSkyControllerDeviceConnectToDeviceListener _ARCommandSkyControllerDeviceConnectToDeviceListener_PARAM) {
        _ARCommandSkyControllerDeviceConnectToDeviceListener = _ARCommandSkyControllerDeviceConnectToDeviceListener_PARAM;
    }

    private ARCommandSkyControllerSettingsAllSettingsListener _ARCommandSkyControllerSettingsAllSettingsListener;

    /**
     * Set the listener for the command <code>SettingsAllSettings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsAllSettingsListener (ARCommandSkyControllerSettingsAllSettingsListener _ARCommandSkyControllerSettingsAllSettingsListener_PARAM) {
        _ARCommandSkyControllerSettingsAllSettingsListener = _ARCommandSkyControllerSettingsAllSettingsListener_PARAM;
    }

    private ARCommandSkyControllerSettingsResetListener _ARCommandSkyControllerSettingsResetListener;

    /**
     * Set the listener for the command <code>SettingsReset</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsResetListener (ARCommandSkyControllerSettingsResetListener _ARCommandSkyControllerSettingsResetListener_PARAM) {
        _ARCommandSkyControllerSettingsResetListener = _ARCommandSkyControllerSettingsResetListener_PARAM;
    }

    private ARCommandSkyControllerCommonAllStatesListener _ARCommandSkyControllerCommonAllStatesListener;

    /**
     * Set the listener for the command <code>CommonAllStates</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCommonAllStatesListener (ARCommandSkyControllerCommonAllStatesListener _ARCommandSkyControllerCommonAllStatesListener_PARAM) {
        _ARCommandSkyControllerCommonAllStatesListener = _ARCommandSkyControllerCommonAllStatesListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener _ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsAccessPointSSID</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsAccessPointSSIDListener (ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener _ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener = _ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener _ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsAccessPointChannel</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsAccessPointChannelListener (ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener _ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener = _ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsWifiSelectionListener _ARCommandSkyControllerAccessPointSettingsWifiSelectionListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsWifiSelection</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsWifiSelectionListener (ARCommandSkyControllerAccessPointSettingsWifiSelectionListener _ARCommandSkyControllerAccessPointSettingsWifiSelectionListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsWifiSelectionListener = _ARCommandSkyControllerAccessPointSettingsWifiSelectionListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsWifiSecurityListener _ARCommandSkyControllerAccessPointSettingsWifiSecurityListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsWifiSecurity</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsWifiSecurityListener (ARCommandSkyControllerAccessPointSettingsWifiSecurityListener _ARCommandSkyControllerAccessPointSettingsWifiSecurityListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsWifiSecurityListener = _ARCommandSkyControllerAccessPointSettingsWifiSecurityListener_PARAM;
    }

    private ARCommandSkyControllerCameraResetOrientationListener _ARCommandSkyControllerCameraResetOrientationListener;

    /**
     * Set the listener for the command <code>CameraResetOrientation</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCameraResetOrientationListener (ARCommandSkyControllerCameraResetOrientationListener _ARCommandSkyControllerCameraResetOrientationListener_PARAM) {
        _ARCommandSkyControllerCameraResetOrientationListener = _ARCommandSkyControllerCameraResetOrientationListener_PARAM;
    }

    private ARCommandSkyControllerGamepadInfosGetGamepadControlsListener _ARCommandSkyControllerGamepadInfosGetGamepadControlsListener;

    /**
     * Set the listener for the command <code>GamepadInfosGetGamepadControls</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerGamepadInfosGetGamepadControlsListener (ARCommandSkyControllerGamepadInfosGetGamepadControlsListener _ARCommandSkyControllerGamepadInfosGetGamepadControlsListener_PARAM) {
        _ARCommandSkyControllerGamepadInfosGetGamepadControlsListener = _ARCommandSkyControllerGamepadInfosGetGamepadControlsListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener _ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener;

    /**
     * Set the listener for the command <code>ButtonMappingsGetCurrentButtonMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsGetCurrentButtonMappingsListener (ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener _ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener = _ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener _ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener;

    /**
     * Set the listener for the command <code>ButtonMappingsGetAvailableButtonMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsGetAvailableButtonMappingsListener (ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener _ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener = _ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsSetButtonMappingListener _ARCommandSkyControllerButtonMappingsSetButtonMappingListener;

    /**
     * Set the listener for the command <code>ButtonMappingsSetButtonMapping</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsSetButtonMappingListener (ARCommandSkyControllerButtonMappingsSetButtonMappingListener _ARCommandSkyControllerButtonMappingsSetButtonMappingListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsSetButtonMappingListener = _ARCommandSkyControllerButtonMappingsSetButtonMappingListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener _ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener;

    /**
     * Set the listener for the command <code>ButtonMappingsDefaultButtonMapping</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsDefaultButtonMappingListener (ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener _ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener = _ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener _ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener;

    /**
     * Set the listener for the command <code>AxisMappingsGetCurrentAxisMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsGetCurrentAxisMappingsListener (ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener _ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener = _ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener _ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener;

    /**
     * Set the listener for the command <code>AxisMappingsGetAvailableAxisMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsGetAvailableAxisMappingsListener (ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener _ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener = _ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsSetAxisMappingListener _ARCommandSkyControllerAxisMappingsSetAxisMappingListener;

    /**
     * Set the listener for the command <code>AxisMappingsSetAxisMapping</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsSetAxisMappingListener (ARCommandSkyControllerAxisMappingsSetAxisMappingListener _ARCommandSkyControllerAxisMappingsSetAxisMappingListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsSetAxisMappingListener = _ARCommandSkyControllerAxisMappingsSetAxisMappingListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener _ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener;

    /**
     * Set the listener for the command <code>AxisMappingsDefaultAxisMapping</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsDefaultAxisMappingListener (ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener _ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener = _ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener _ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener;

    /**
     * Set the listener for the command <code>AxisFiltersGetCurrentAxisFilters</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersGetCurrentAxisFiltersListener (ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener _ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener = _ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener _ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener;

    /**
     * Set the listener for the command <code>AxisFiltersGetPresetAxisFilters</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersGetPresetAxisFiltersListener (ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener _ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener = _ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersSetAxisFilterListener _ARCommandSkyControllerAxisFiltersSetAxisFilterListener;

    /**
     * Set the listener for the command <code>AxisFiltersSetAxisFilter</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersSetAxisFilterListener (ARCommandSkyControllerAxisFiltersSetAxisFilterListener _ARCommandSkyControllerAxisFiltersSetAxisFilterListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersSetAxisFilterListener = _ARCommandSkyControllerAxisFiltersSetAxisFilterListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener _ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener;

    /**
     * Set the listener for the command <code>AxisFiltersDefaultAxisFilters</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersDefaultAxisFiltersListener (ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener _ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener = _ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener_PARAM;
    }

    private ARCommandSkyControllerCoPilotingSetPilotingSourceListener _ARCommandSkyControllerCoPilotingSetPilotingSourceListener;

    /**
     * Set the listener for the command <code>CoPilotingSetPilotingSource</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCoPilotingSetPilotingSourceListener (ARCommandSkyControllerCoPilotingSetPilotingSourceListener _ARCommandSkyControllerCoPilotingSetPilotingSourceListener_PARAM) {
        _ARCommandSkyControllerCoPilotingSetPilotingSourceListener = _ARCommandSkyControllerCoPilotingSetPilotingSourceListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener _ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener;

    /**
     * Set the listener for the command <code>CalibrationEnableMagnetoCalibrationQualityUpdates</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener (ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener _ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener_PARAM) {
        _ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener = _ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationStartCalibrationListener _ARCommandSkyControllerCalibrationStartCalibrationListener;

    /**
     * Set the listener for the command <code>CalibrationStartCalibration</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationStartCalibrationListener (ARCommandSkyControllerCalibrationStartCalibrationListener _ARCommandSkyControllerCalibrationStartCalibrationListener_PARAM) {
        _ARCommandSkyControllerCalibrationStartCalibrationListener = _ARCommandSkyControllerCalibrationStartCalibrationListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationAbortCalibrationListener _ARCommandSkyControllerCalibrationAbortCalibrationListener;

    /**
     * Set the listener for the command <code>CalibrationAbortCalibration</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationAbortCalibrationListener (ARCommandSkyControllerCalibrationAbortCalibrationListener _ARCommandSkyControllerCalibrationAbortCalibrationListener_PARAM) {
        _ARCommandSkyControllerCalibrationAbortCalibrationListener = _ARCommandSkyControllerCalibrationAbortCalibrationListener_PARAM;
    }

    private ARCommandSkyControllerFactoryResetListener _ARCommandSkyControllerFactoryResetListener;

    /**
     * Set the listener for the command <code>FactoryReset</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerFactoryResetListener (ARCommandSkyControllerFactoryResetListener _ARCommandSkyControllerFactoryResetListener_PARAM) {
        _ARCommandSkyControllerFactoryResetListener = _ARCommandSkyControllerFactoryResetListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiListListener _ARCommandSkyControllerWifiStateWifiListListener;

    /**
     * Set the listener for the command <code>WifiStateWifiList</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiListListener (ARCommandSkyControllerWifiStateWifiListListener _ARCommandSkyControllerWifiStateWifiListListener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiListListener = _ARCommandSkyControllerWifiStateWifiListListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateConnexionChangedListener _ARCommandSkyControllerWifiStateConnexionChangedListener;

    /**
     * Set the listener for the command <code>WifiStateConnexionChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateConnexionChangedListener (ARCommandSkyControllerWifiStateConnexionChangedListener _ARCommandSkyControllerWifiStateConnexionChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateConnexionChangedListener = _ARCommandSkyControllerWifiStateConnexionChangedListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener;

    /**
     * Set the listener for the command <code>WifiStateWifiAuthChannelListChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiAuthChannelListChangedListener (ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener = _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener _ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener;

    /**
     * Set the listener for the command <code>WifiStateAllWifiAuthChannelChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateAllWifiAuthChannelChangedListener (ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener _ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener = _ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiSignalChangedListener _ARCommandSkyControllerWifiStateWifiSignalChangedListener;

    /**
     * Set the listener for the command <code>WifiStateWifiSignalChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiSignalChangedListener (ARCommandSkyControllerWifiStateWifiSignalChangedListener _ARCommandSkyControllerWifiStateWifiSignalChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiSignalChangedListener = _ARCommandSkyControllerWifiStateWifiSignalChangedListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener;

    /**
     * Set the listener for the command <code>WifiStateWifiAuthChannelListChangedV2</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiAuthChannelListChangedV2Listener (ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener = _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiCountryChangedListener _ARCommandSkyControllerWifiStateWifiCountryChangedListener;

    /**
     * Set the listener for the command <code>WifiStateWifiCountryChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiCountryChangedListener (ARCommandSkyControllerWifiStateWifiCountryChangedListener _ARCommandSkyControllerWifiStateWifiCountryChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiCountryChangedListener = _ARCommandSkyControllerWifiStateWifiCountryChangedListener_PARAM;
    }

    private ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener _ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener;

    /**
     * Set the listener for the command <code>WifiStateWifiEnvironmentChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerWifiStateWifiEnvironmentChangedListener (ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener _ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener_PARAM) {
        _ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener = _ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener_PARAM;
    }

    private ARCommandSkyControllerDeviceStateDeviceListListener _ARCommandSkyControllerDeviceStateDeviceListListener;

    /**
     * Set the listener for the command <code>DeviceStateDeviceList</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerDeviceStateDeviceListListener (ARCommandSkyControllerDeviceStateDeviceListListener _ARCommandSkyControllerDeviceStateDeviceListListener_PARAM) {
        _ARCommandSkyControllerDeviceStateDeviceListListener = _ARCommandSkyControllerDeviceStateDeviceListListener_PARAM;
    }

    private ARCommandSkyControllerDeviceStateConnexionChangedListener _ARCommandSkyControllerDeviceStateConnexionChangedListener;

    /**
     * Set the listener for the command <code>DeviceStateConnexionChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerDeviceStateConnexionChangedListener (ARCommandSkyControllerDeviceStateConnexionChangedListener _ARCommandSkyControllerDeviceStateConnexionChangedListener_PARAM) {
        _ARCommandSkyControllerDeviceStateConnexionChangedListener = _ARCommandSkyControllerDeviceStateConnexionChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateAllSettingsChangedListener _ARCommandSkyControllerSettingsStateAllSettingsChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateAllSettingsChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateAllSettingsChangedListener (ARCommandSkyControllerSettingsStateAllSettingsChangedListener _ARCommandSkyControllerSettingsStateAllSettingsChangedListener_PARAM) {
        _ARCommandSkyControllerSettingsStateAllSettingsChangedListener = _ARCommandSkyControllerSettingsStateAllSettingsChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateResetChangedListener _ARCommandSkyControllerSettingsStateResetChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateResetChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateResetChangedListener (ARCommandSkyControllerSettingsStateResetChangedListener _ARCommandSkyControllerSettingsStateResetChangedListener_PARAM) {
        _ARCommandSkyControllerSettingsStateResetChangedListener = _ARCommandSkyControllerSettingsStateResetChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateProductSerialChangedListener _ARCommandSkyControllerSettingsStateProductSerialChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductSerialChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateProductSerialChangedListener (ARCommandSkyControllerSettingsStateProductSerialChangedListener _ARCommandSkyControllerSettingsStateProductSerialChangedListener_PARAM) {
        _ARCommandSkyControllerSettingsStateProductSerialChangedListener = _ARCommandSkyControllerSettingsStateProductSerialChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateProductVariantChangedListener _ARCommandSkyControllerSettingsStateProductVariantChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductVariantChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateProductVariantChangedListener (ARCommandSkyControllerSettingsStateProductVariantChangedListener _ARCommandSkyControllerSettingsStateProductVariantChangedListener_PARAM) {
        _ARCommandSkyControllerSettingsStateProductVariantChangedListener = _ARCommandSkyControllerSettingsStateProductVariantChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateProductVersionChangedListener _ARCommandSkyControllerSettingsStateProductVersionChangedListener;

    /**
     * Set the listener for the command <code>SettingsStateProductVersionChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateProductVersionChangedListener (ARCommandSkyControllerSettingsStateProductVersionChangedListener _ARCommandSkyControllerSettingsStateProductVersionChangedListener_PARAM) {
        _ARCommandSkyControllerSettingsStateProductVersionChangedListener = _ARCommandSkyControllerSettingsStateProductVersionChangedListener_PARAM;
    }

    private ARCommandSkyControllerSettingsStateCPUIDListener _ARCommandSkyControllerSettingsStateCPUIDListener;

    /**
     * Set the listener for the command <code>SettingsStateCPUID</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSettingsStateCPUIDListener (ARCommandSkyControllerSettingsStateCPUIDListener _ARCommandSkyControllerSettingsStateCPUIDListener_PARAM) {
        _ARCommandSkyControllerSettingsStateCPUIDListener = _ARCommandSkyControllerSettingsStateCPUIDListener_PARAM;
    }

    private ARCommandSkyControllerCommonStateAllStatesChangedListener _ARCommandSkyControllerCommonStateAllStatesChangedListener;

    /**
     * Set the listener for the command <code>CommonStateAllStatesChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCommonStateAllStatesChangedListener (ARCommandSkyControllerCommonStateAllStatesChangedListener _ARCommandSkyControllerCommonStateAllStatesChangedListener_PARAM) {
        _ARCommandSkyControllerCommonStateAllStatesChangedListener = _ARCommandSkyControllerCommonStateAllStatesChangedListener_PARAM;
    }

    private ARCommandSkyControllerSkyControllerStateBatteryChangedListener _ARCommandSkyControllerSkyControllerStateBatteryChangedListener;

    /**
     * Set the listener for the command <code>SkyControllerStateBatteryChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSkyControllerStateBatteryChangedListener (ARCommandSkyControllerSkyControllerStateBatteryChangedListener _ARCommandSkyControllerSkyControllerStateBatteryChangedListener_PARAM) {
        _ARCommandSkyControllerSkyControllerStateBatteryChangedListener = _ARCommandSkyControllerSkyControllerStateBatteryChangedListener_PARAM;
    }

    private ARCommandSkyControllerSkyControllerStateGpsFixChangedListener _ARCommandSkyControllerSkyControllerStateGpsFixChangedListener;

    /**
     * Set the listener for the command <code>SkyControllerStateGpsFixChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSkyControllerStateGpsFixChangedListener (ARCommandSkyControllerSkyControllerStateGpsFixChangedListener _ARCommandSkyControllerSkyControllerStateGpsFixChangedListener_PARAM) {
        _ARCommandSkyControllerSkyControllerStateGpsFixChangedListener = _ARCommandSkyControllerSkyControllerStateGpsFixChangedListener_PARAM;
    }

    private ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener _ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener;

    /**
     * Set the listener for the command <code>SkyControllerStateGpsPositionChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSkyControllerStateGpsPositionChangedListener (ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener _ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener_PARAM) {
        _ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener = _ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener_PARAM;
    }

    private ARCommandSkyControllerSkyControllerStateBatteryStateListener _ARCommandSkyControllerSkyControllerStateBatteryStateListener;

    /**
     * Set the listener for the command <code>SkyControllerStateBatteryState</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSkyControllerStateBatteryStateListener (ARCommandSkyControllerSkyControllerStateBatteryStateListener _ARCommandSkyControllerSkyControllerStateBatteryStateListener_PARAM) {
        _ARCommandSkyControllerSkyControllerStateBatteryStateListener = _ARCommandSkyControllerSkyControllerStateBatteryStateListener_PARAM;
    }

    private ARCommandSkyControllerSkyControllerStateAttitudeChangedListener _ARCommandSkyControllerSkyControllerStateAttitudeChangedListener;

    /**
     * Set the listener for the command <code>SkyControllerStateAttitudeChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerSkyControllerStateAttitudeChangedListener (ARCommandSkyControllerSkyControllerStateAttitudeChangedListener _ARCommandSkyControllerSkyControllerStateAttitudeChangedListener_PARAM) {
        _ARCommandSkyControllerSkyControllerStateAttitudeChangedListener = _ARCommandSkyControllerSkyControllerStateAttitudeChangedListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener _ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsStateAccessPointSSIDChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener (ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener _ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener = _ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener _ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsStateAccessPointChannelChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener (ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener _ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener = _ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener _ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsStateWifiSelectionChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsStateWifiSelectionChangedListener (ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener _ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener = _ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener_PARAM;
    }

    private ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener _ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener;

    /**
     * Set the listener for the command <code>AccessPointSettingsStateWifiSecurityChanged</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAccessPointSettingsStateWifiSecurityChangedListener (ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener _ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener_PARAM) {
        _ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener = _ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener_PARAM;
    }

    private ARCommandSkyControllerGamepadInfosStateGamepadControlListener _ARCommandSkyControllerGamepadInfosStateGamepadControlListener;

    /**
     * Set the listener for the command <code>GamepadInfosStateGamepadControl</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerGamepadInfosStateGamepadControlListener (ARCommandSkyControllerGamepadInfosStateGamepadControlListener _ARCommandSkyControllerGamepadInfosStateGamepadControlListener_PARAM) {
        _ARCommandSkyControllerGamepadInfosStateGamepadControlListener = _ARCommandSkyControllerGamepadInfosStateGamepadControlListener_PARAM;
    }

    private ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener _ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener;

    /**
     * Set the listener for the command <code>GamepadInfosStateAllGamepadControlsSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerGamepadInfosStateAllGamepadControlsSentListener (ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener _ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener_PARAM) {
        _ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener = _ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener _ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener;

    /**
     * Set the listener for the command <code>ButtonMappingsStateCurrentButtonMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsStateCurrentButtonMappingsListener (ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener _ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener = _ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener _ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener;

    /**
     * Set the listener for the command <code>ButtonMappingsStateAllCurrentButtonMappingsSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener (ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener _ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener = _ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener _ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener;

    /**
     * Set the listener for the command <code>ButtonMappingsStateAvailableButtonMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsStateAvailableButtonMappingsListener (ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener _ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener = _ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener_PARAM;
    }

    private ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener _ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener;

    /**
     * Set the listener for the command <code>ButtonMappingsStateAllAvailableButtonsMappingsSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener (ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener _ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener_PARAM) {
        _ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener = _ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener _ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener;

    /**
     * Set the listener for the command <code>AxisMappingsStateCurrentAxisMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsStateCurrentAxisMappingsListener (ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener _ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener = _ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener _ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener;

    /**
     * Set the listener for the command <code>AxisMappingsStateAllCurrentAxisMappingsSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener (ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener _ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener = _ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener _ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener;

    /**
     * Set the listener for the command <code>AxisMappingsStateAvailableAxisMappings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsStateAvailableAxisMappingsListener (ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener _ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener = _ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener_PARAM;
    }

    private ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener _ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener;

    /**
     * Set the listener for the command <code>AxisMappingsStateAllAvailableAxisMappingsSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener (ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener _ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener_PARAM) {
        _ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener = _ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener _ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener;

    /**
     * Set the listener for the command <code>AxisFiltersStateCurrentAxisFilters</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersStateCurrentAxisFiltersListener (ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener _ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener = _ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener _ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener;

    /**
     * Set the listener for the command <code>AxisFiltersStateAllCurrentFiltersSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersStateAllCurrentFiltersSentListener (ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener _ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener = _ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener _ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener;

    /**
     * Set the listener for the command <code>AxisFiltersStatePresetAxisFilters</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersStatePresetAxisFiltersListener (ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener _ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener = _ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener_PARAM;
    }

    private ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener _ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener;

    /**
     * Set the listener for the command <code>AxisFiltersStateAllPresetFiltersSent</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerAxisFiltersStateAllPresetFiltersSentListener (ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener _ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener_PARAM) {
        _ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener = _ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener_PARAM;
    }

    private ARCommandSkyControllerCoPilotingStatePilotingSourceListener _ARCommandSkyControllerCoPilotingStatePilotingSourceListener;

    /**
     * Set the listener for the command <code>CoPilotingStatePilotingSource</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCoPilotingStatePilotingSourceListener (ARCommandSkyControllerCoPilotingStatePilotingSourceListener _ARCommandSkyControllerCoPilotingStatePilotingSourceListener_PARAM) {
        _ARCommandSkyControllerCoPilotingStatePilotingSourceListener = _ARCommandSkyControllerCoPilotingStatePilotingSourceListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationState</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationStateMagnetoCalibrationStateListener (ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener_PARAM) {
        _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener = _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationQualityUpdatesState</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener (ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener_PARAM) {
        _ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener = _ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener_PARAM;
    }

    private ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener;

    /**
     * Set the listener for the command <code>CalibrationStateMagnetoCalibrationStateV2</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener (ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener_PARAM) {
        _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener = _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener_PARAM;
    }

    private ARCommandSkyControllerButtonEventsSettingsListener _ARCommandSkyControllerButtonEventsSettingsListener;

    /**
     * Set the listener for the command <code>ButtonEventsSettings</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerButtonEventsSettingsListener (ARCommandSkyControllerButtonEventsSettingsListener _ARCommandSkyControllerButtonEventsSettingsListener_PARAM) {
        _ARCommandSkyControllerButtonEventsSettingsListener = _ARCommandSkyControllerButtonEventsSettingsListener_PARAM;
    }

    private ARCommandSkyControllerCommonEventStateShutdownListener _ARCommandSkyControllerCommonEventStateShutdownListener;

    /**
     * Set the listener for the command <code>CommonEventStateShutdown</code> in feature <code>SkyController</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setSkyControllerCommonEventStateShutdownListener (ARCommandSkyControllerCommonEventStateShutdownListener _ARCommandSkyControllerCommonEventStateShutdownListener_PARAM) {
        _ARCommandSkyControllerCommonEventStateShutdownListener = _ARCommandSkyControllerCommonEventStateShutdownListener_PARAM;
    }


    private ARCommandThermalCamActivateListener _ARCommandThermalCamActivateListener;

    /**
     * Set the listener for the command <code>Activate</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamActivateListener (ARCommandThermalCamActivateListener _ARCommandThermalCamActivateListener_PARAM) {
        _ARCommandThermalCamActivateListener = _ARCommandThermalCamActivateListener_PARAM;
    }

    private ARCommandThermalCamDeactivateListener _ARCommandThermalCamDeactivateListener;

    /**
     * Set the listener for the command <code>Deactivate</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamDeactivateListener (ARCommandThermalCamDeactivateListener _ARCommandThermalCamDeactivateListener_PARAM) {
        _ARCommandThermalCamDeactivateListener = _ARCommandThermalCamDeactivateListener_PARAM;
    }

    private ARCommandThermalCamSetSensitivityListener _ARCommandThermalCamSetSensitivityListener;

    /**
     * Set the listener for the command <code>SetSensitivity</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamSetSensitivityListener (ARCommandThermalCamSetSensitivityListener _ARCommandThermalCamSetSensitivityListener_PARAM) {
        _ARCommandThermalCamSetSensitivityListener = _ARCommandThermalCamSetSensitivityListener_PARAM;
    }

    private ARCommandThermalCamCameraStateListener _ARCommandThermalCamCameraStateListener;

    /**
     * Set the listener for the command <code>CameraState</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamCameraStateListener (ARCommandThermalCamCameraStateListener _ARCommandThermalCamCameraStateListener_PARAM) {
        _ARCommandThermalCamCameraStateListener = _ARCommandThermalCamCameraStateListener_PARAM;
    }

    private ARCommandThermalCamSensitivityListener _ARCommandThermalCamSensitivityListener;

    /**
     * Set the listener for the command <code>Sensitivity</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamSensitivityListener (ARCommandThermalCamSensitivityListener _ARCommandThermalCamSensitivityListener_PARAM) {
        _ARCommandThermalCamSensitivityListener = _ARCommandThermalCamSensitivityListener_PARAM;
    }

    private ARCommandThermalCamCalibrationInfosListener _ARCommandThermalCamCalibrationInfosListener;

    /**
     * Set the listener for the command <code>CalibrationInfos</code> in feature <code>ThermalCam</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setThermalCamCalibrationInfosListener (ARCommandThermalCamCalibrationInfosListener _ARCommandThermalCamCalibrationInfosListener_PARAM) {
        _ARCommandThermalCamCalibrationInfosListener = _ARCommandThermalCamCalibrationInfosListener_PARAM;
    }


    private ARCommandWifiScanListener _ARCommandWifiScanListener;

    /**
     * Set the listener for the command <code>Scan</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiScanListener (ARCommandWifiScanListener _ARCommandWifiScanListener_PARAM) {
        _ARCommandWifiScanListener = _ARCommandWifiScanListener_PARAM;
    }

    private ARCommandWifiUpdateAuthorizedChannelsListener _ARCommandWifiUpdateAuthorizedChannelsListener;

    /**
     * Set the listener for the command <code>UpdateAuthorizedChannels</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiUpdateAuthorizedChannelsListener (ARCommandWifiUpdateAuthorizedChannelsListener _ARCommandWifiUpdateAuthorizedChannelsListener_PARAM) {
        _ARCommandWifiUpdateAuthorizedChannelsListener = _ARCommandWifiUpdateAuthorizedChannelsListener_PARAM;
    }

    private ARCommandWifiSetApChannelListener _ARCommandWifiSetApChannelListener;

    /**
     * Set the listener for the command <code>SetApChannel</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSetApChannelListener (ARCommandWifiSetApChannelListener _ARCommandWifiSetApChannelListener_PARAM) {
        _ARCommandWifiSetApChannelListener = _ARCommandWifiSetApChannelListener_PARAM;
    }

    private ARCommandWifiSetSecurityListener _ARCommandWifiSetSecurityListener;

    /**
     * Set the listener for the command <code>SetSecurity</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSetSecurityListener (ARCommandWifiSetSecurityListener _ARCommandWifiSetSecurityListener_PARAM) {
        _ARCommandWifiSetSecurityListener = _ARCommandWifiSetSecurityListener_PARAM;
    }

    private ARCommandWifiSetCountryListener _ARCommandWifiSetCountryListener;

    /**
     * Set the listener for the command <code>SetCountry</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSetCountryListener (ARCommandWifiSetCountryListener _ARCommandWifiSetCountryListener_PARAM) {
        _ARCommandWifiSetCountryListener = _ARCommandWifiSetCountryListener_PARAM;
    }

    private ARCommandWifiSetEnvironmentListener _ARCommandWifiSetEnvironmentListener;

    /**
     * Set the listener for the command <code>SetEnvironment</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSetEnvironmentListener (ARCommandWifiSetEnvironmentListener _ARCommandWifiSetEnvironmentListener_PARAM) {
        _ARCommandWifiSetEnvironmentListener = _ARCommandWifiSetEnvironmentListener_PARAM;
    }

    private ARCommandWifiScannedItemListener _ARCommandWifiScannedItemListener;

    /**
     * Set the listener for the command <code>ScannedItem</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiScannedItemListener (ARCommandWifiScannedItemListener _ARCommandWifiScannedItemListener_PARAM) {
        _ARCommandWifiScannedItemListener = _ARCommandWifiScannedItemListener_PARAM;
    }

    private ARCommandWifiAuthorizedChannelListener _ARCommandWifiAuthorizedChannelListener;

    /**
     * Set the listener for the command <code>AuthorizedChannel</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiAuthorizedChannelListener (ARCommandWifiAuthorizedChannelListener _ARCommandWifiAuthorizedChannelListener_PARAM) {
        _ARCommandWifiAuthorizedChannelListener = _ARCommandWifiAuthorizedChannelListener_PARAM;
    }

    private ARCommandWifiApChannelChangedListener _ARCommandWifiApChannelChangedListener;

    /**
     * Set the listener for the command <code>ApChannelChanged</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiApChannelChangedListener (ARCommandWifiApChannelChangedListener _ARCommandWifiApChannelChangedListener_PARAM) {
        _ARCommandWifiApChannelChangedListener = _ARCommandWifiApChannelChangedListener_PARAM;
    }

    private ARCommandWifiSecurityChangedListener _ARCommandWifiSecurityChangedListener;

    /**
     * Set the listener for the command <code>SecurityChanged</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSecurityChangedListener (ARCommandWifiSecurityChangedListener _ARCommandWifiSecurityChangedListener_PARAM) {
        _ARCommandWifiSecurityChangedListener = _ARCommandWifiSecurityChangedListener_PARAM;
    }

    private ARCommandWifiCountryChangedListener _ARCommandWifiCountryChangedListener;

    /**
     * Set the listener for the command <code>CountryChanged</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiCountryChangedListener (ARCommandWifiCountryChangedListener _ARCommandWifiCountryChangedListener_PARAM) {
        _ARCommandWifiCountryChangedListener = _ARCommandWifiCountryChangedListener_PARAM;
    }

    private ARCommandWifiEnvironmentChangedListener _ARCommandWifiEnvironmentChangedListener;

    /**
     * Set the listener for the command <code>EnvironmentChanged</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiEnvironmentChangedListener (ARCommandWifiEnvironmentChangedListener _ARCommandWifiEnvironmentChangedListener_PARAM) {
        _ARCommandWifiEnvironmentChangedListener = _ARCommandWifiEnvironmentChangedListener_PARAM;
    }

    private ARCommandWifiRssiChangedListener _ARCommandWifiRssiChangedListener;

    /**
     * Set the listener for the command <code>RssiChanged</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiRssiChangedListener (ARCommandWifiRssiChangedListener _ARCommandWifiRssiChangedListener_PARAM) {
        _ARCommandWifiRssiChangedListener = _ARCommandWifiRssiChangedListener_PARAM;
    }

    private ARCommandWifiSupportedCountriesListener _ARCommandWifiSupportedCountriesListener;

    /**
     * Set the listener for the command <code>SupportedCountries</code> in feature <code>Wifi</code><br>
     * Listeners are static to the class, and are not to be set on every object
     */
    public void setWifiSupportedCountriesListener (ARCommandWifiSupportedCountriesListener _ARCommandWifiSupportedCountriesListener_PARAM) {
        _ARCommandWifiSupportedCountriesListener = _ARCommandWifiSupportedCountriesListener_PARAM;
    }


    void onGenericDefaultUpdate () {
        if(_ARCommandGenericDefaultListener != null) {
            _ARCommandGenericDefaultListener.onGenericDefaultUpdate ();
        }
    }

    void onGenericSetDroneSettingsUpdate (ARCommandsGenericDroneSettings settings) {
        if(_ARCommandGenericSetDroneSettingsListener != null) {
            _ARCommandGenericSetDroneSettingsListener.onGenericSetDroneSettingsUpdate (settings);
        }
    }

    void onGenericDroneSettingsChangedUpdate (ARCommandsGenericDroneSettingsChanged settings) {
        if(_ARCommandGenericDroneSettingsChangedListener != null) {
            _ARCommandGenericDroneSettingsChangedListener.onGenericDroneSettingsChangedUpdate (settings);
        }
    }

    void onAnimationCancelUpdate () {
        if(_ARCommandAnimationCancelListener != null) {
            _ARCommandAnimationCancelListener.onAnimationCancelUpdate ();
        }
    }

    void onAnimationStartFlipUpdate (ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM type) {
        if(_ARCommandAnimationStartFlipListener != null) {
            _ARCommandAnimationStartFlipListener.onAnimationStartFlipUpdate (type);
        }
    }

    void onAnimationStartHorizontalPanoramaUpdate (byte provided_params, float rotation_angle, float rotation_speed) {
        if(_ARCommandAnimationStartHorizontalPanoramaListener != null) {
            _ARCommandAnimationStartHorizontalPanoramaListener.onAnimationStartHorizontalPanoramaUpdate (provided_params, rotation_angle, rotation_speed);
        }
    }

    void onAnimationStartDronieUpdate (byte provided_params, float speed, float distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartDronieListener != null) {
            _ARCommandAnimationStartDronieListener.onAnimationStartDronieUpdate (provided_params, speed, distance, play_mode);
        }
    }

    void onAnimationStartHorizontalRevealUpdate (byte provided_params, float speed, float distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartHorizontalRevealListener != null) {
            _ARCommandAnimationStartHorizontalRevealListener.onAnimationStartHorizontalRevealUpdate (provided_params, speed, distance, play_mode);
        }
    }

    void onAnimationStartVerticalRevealUpdate (byte provided_params, float speed, float vertical_distance, float rotation_angle, float rotation_speed, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartVerticalRevealListener != null) {
            _ARCommandAnimationStartVerticalRevealListener.onAnimationStartVerticalRevealUpdate (provided_params, speed, vertical_distance, rotation_angle, rotation_speed, play_mode);
        }
    }

    void onAnimationStartSpiralUpdate (byte provided_params, float speed, float radius_variation, float vertical_distance, float revolution_nb, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartSpiralListener != null) {
            _ARCommandAnimationStartSpiralListener.onAnimationStartSpiralUpdate (provided_params, speed, radius_variation, vertical_distance, revolution_nb, play_mode);
        }
    }

    void onAnimationStartParabolaUpdate (byte provided_params, float speed, float vertical_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartParabolaListener != null) {
            _ARCommandAnimationStartParabolaListener.onAnimationStartParabolaUpdate (provided_params, speed, vertical_distance, play_mode);
        }
    }

    void onAnimationStartCandleUpdate (byte provided_params, float speed, float vertical_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartCandleListener != null) {
            _ARCommandAnimationStartCandleListener.onAnimationStartCandleUpdate (provided_params, speed, vertical_distance, play_mode);
        }
    }

    void onAnimationStartDollySlideUpdate (byte provided_params, float speed, float angle, float horizontal_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationStartDollySlideListener != null) {
            _ARCommandAnimationStartDollySlideListener.onAnimationStartDollySlideUpdate (provided_params, speed, angle, horizontal_distance, play_mode);
        }
    }

    void onAnimationAvailabilityUpdate (int values) {
        if(_ARCommandAnimationAvailabilityListener != null) {
            _ARCommandAnimationAvailabilityListener.onAnimationAvailabilityUpdate (values);
        }
    }

    void onAnimationStateUpdate (ARCOMMANDS_ANIMATION_TYPE_ENUM type, byte percent) {
        if(_ARCommandAnimationStateListener != null) {
            _ARCommandAnimationStateListener.onAnimationStateUpdate (type, percent);
        }
    }

    void onAnimationFlipStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM type) {
        if(_ARCommandAnimationFlipStateListener != null) {
            _ARCommandAnimationFlipStateListener.onAnimationFlipStateUpdate (state, type);
        }
    }

    void onAnimationHorizontalPanoramaStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float rotation_angle, float rotation_speed) {
        if(_ARCommandAnimationHorizontalPanoramaStateListener != null) {
            _ARCommandAnimationHorizontalPanoramaStateListener.onAnimationHorizontalPanoramaStateUpdate (state, rotation_angle, rotation_speed);
        }
    }

    void onAnimationDronieStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationDronieStateListener != null) {
            _ARCommandAnimationDronieStateListener.onAnimationDronieStateUpdate (state, speed, distance, play_mode);
        }
    }

    void onAnimationHorizontalRevealStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationHorizontalRevealStateListener != null) {
            _ARCommandAnimationHorizontalRevealStateListener.onAnimationHorizontalRevealStateUpdate (state, speed, distance, play_mode);
        }
    }

    void onAnimationVerticalRevealStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float vertical_distance, float rotation_angle, float rotation_speed, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationVerticalRevealStateListener != null) {
            _ARCommandAnimationVerticalRevealStateListener.onAnimationVerticalRevealStateUpdate (state, speed, vertical_distance, rotation_angle, rotation_speed, play_mode);
        }
    }

    void onAnimationSpiralStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float radius_variation, float vertical_distance, float revolution_nb, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationSpiralStateListener != null) {
            _ARCommandAnimationSpiralStateListener.onAnimationSpiralStateUpdate (state, speed, radius_variation, vertical_distance, revolution_nb, play_mode);
        }
    }

    void onAnimationParabolaStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float vertical_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationParabolaStateListener != null) {
            _ARCommandAnimationParabolaStateListener.onAnimationParabolaStateUpdate (state, speed, vertical_distance, play_mode);
        }
    }

    void onAnimationCandleStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float vertical_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationCandleStateListener != null) {
            _ARCommandAnimationCandleStateListener.onAnimationCandleStateUpdate (state, speed, vertical_distance, play_mode);
        }
    }

    void onAnimationDollySlideStateUpdate (ARCOMMANDS_ANIMATION_STATE_ENUM state, float speed, float angle, float horizontal_distance, ARCOMMANDS_ANIMATION_PLAY_MODE_ENUM play_mode) {
        if(_ARCommandAnimationDollySlideStateListener != null) {
            _ARCommandAnimationDollySlideStateListener.onAnimationDollySlideStateUpdate (state, speed, angle, horizontal_distance, play_mode);
        }
    }

    void onARDrone3PilotingFlatTrimUpdate () {
        if(_ARCommandARDrone3PilotingFlatTrimListener != null) {
            _ARCommandARDrone3PilotingFlatTrimListener.onARDrone3PilotingFlatTrimUpdate ();
        }
    }

    void onARDrone3PilotingTakeOffUpdate () {
        if(_ARCommandARDrone3PilotingTakeOffListener != null) {
            _ARCommandARDrone3PilotingTakeOffListener.onARDrone3PilotingTakeOffUpdate ();
        }
    }

    void onARDrone3PilotingPCMDUpdate (byte flag, byte roll, byte pitch, byte yaw, byte gaz, int timestampAndSeqNum) {
        if(_ARCommandARDrone3PilotingPCMDListener != null) {
            _ARCommandARDrone3PilotingPCMDListener.onARDrone3PilotingPCMDUpdate (flag, roll, pitch, yaw, gaz, timestampAndSeqNum);
        }
    }

    void onARDrone3PilotingLandingUpdate () {
        if(_ARCommandARDrone3PilotingLandingListener != null) {
            _ARCommandARDrone3PilotingLandingListener.onARDrone3PilotingLandingUpdate ();
        }
    }

    void onARDrone3PilotingEmergencyUpdate () {
        if(_ARCommandARDrone3PilotingEmergencyListener != null) {
            _ARCommandARDrone3PilotingEmergencyListener.onARDrone3PilotingEmergencyUpdate ();
        }
    }

    void onARDrone3PilotingNavigateHomeUpdate (byte start) {
        if(_ARCommandARDrone3PilotingNavigateHomeListener != null) {
            _ARCommandARDrone3PilotingNavigateHomeListener.onARDrone3PilotingNavigateHomeUpdate (start);
        }
    }

    void onARDrone3PilotingAutoTakeOffModeUpdate (byte state) {
        if(_ARCommandARDrone3PilotingAutoTakeOffModeListener != null) {
            _ARCommandARDrone3PilotingAutoTakeOffModeListener.onARDrone3PilotingAutoTakeOffModeUpdate (state);
        }
    }

    void onARDrone3PilotingMoveByUpdate (float dX, float dY, float dZ, float dPsi) {
        if(_ARCommandARDrone3PilotingMoveByListener != null) {
            _ARCommandARDrone3PilotingMoveByListener.onARDrone3PilotingMoveByUpdate (dX, dY, dZ, dPsi);
        }
    }

    void onARDrone3PilotingUserTakeOffUpdate (byte state) {
        if(_ARCommandARDrone3PilotingUserTakeOffListener != null) {
            _ARCommandARDrone3PilotingUserTakeOffListener.onARDrone3PilotingUserTakeOffUpdate (state);
        }
    }

    void onARDrone3PilotingCircleUpdate (ARCOMMANDS_ARDRONE3_PILOTING_CIRCLE_DIRECTION_ENUM direction) {
        if(_ARCommandARDrone3PilotingCircleListener != null) {
            _ARCommandARDrone3PilotingCircleListener.onARDrone3PilotingCircleUpdate (direction);
        }
    }

    void onARDrone3PilotingMoveToUpdate (double latitude, double longitude, double altitude, ARCOMMANDS_ARDRONE3_PILOTING_MOVETO_ORIENTATION_MODE_ENUM orientation_mode, float heading) {
        if(_ARCommandARDrone3PilotingMoveToListener != null) {
            _ARCommandARDrone3PilotingMoveToListener.onARDrone3PilotingMoveToUpdate (latitude, longitude, altitude, orientation_mode, heading);
        }
    }

    void onARDrone3PilotingCancelMoveToUpdate () {
        if(_ARCommandARDrone3PilotingCancelMoveToListener != null) {
            _ARCommandARDrone3PilotingCancelMoveToListener.onARDrone3PilotingCancelMoveToUpdate ();
        }
    }

    void onARDrone3PilotingStartPilotedPOIUpdate (double latitude, double longitude, double altitude) {
        if(_ARCommandARDrone3PilotingStartPilotedPOIListener != null) {
            _ARCommandARDrone3PilotingStartPilotedPOIListener.onARDrone3PilotingStartPilotedPOIUpdate (latitude, longitude, altitude);
        }
    }

    void onARDrone3PilotingStopPilotedPOIUpdate () {
        if(_ARCommandARDrone3PilotingStopPilotedPOIListener != null) {
            _ARCommandARDrone3PilotingStopPilotedPOIListener.onARDrone3PilotingStopPilotedPOIUpdate ();
        }
    }

    void onARDrone3AnimationsFlipUpdate (ARCOMMANDS_ARDRONE3_ANIMATIONS_FLIP_DIRECTION_ENUM direction) {
        if(_ARCommandARDrone3AnimationsFlipListener != null) {
            _ARCommandARDrone3AnimationsFlipListener.onARDrone3AnimationsFlipUpdate (direction);
        }
    }

    void onARDrone3CameraOrientationUpdate (byte tilt, byte pan) {
        if(_ARCommandARDrone3CameraOrientationListener != null) {
            _ARCommandARDrone3CameraOrientationListener.onARDrone3CameraOrientationUpdate (tilt, pan);
        }
    }

    void onARDrone3CameraOrientationV2Update (float tilt, float pan) {
        if(_ARCommandARDrone3CameraOrientationV2Listener != null) {
            _ARCommandARDrone3CameraOrientationV2Listener.onARDrone3CameraOrientationV2Update (tilt, pan);
        }
    }

    void onARDrone3CameraVelocityUpdate (float tilt, float pan) {
        if(_ARCommandARDrone3CameraVelocityListener != null) {
            _ARCommandARDrone3CameraVelocityListener.onARDrone3CameraVelocityUpdate (tilt, pan);
        }
    }

    void onARDrone3MediaRecordPictureUpdate (byte mass_storage_id) {
        if(_ARCommandARDrone3MediaRecordPictureListener != null) {
            _ARCommandARDrone3MediaRecordPictureListener.onARDrone3MediaRecordPictureUpdate (mass_storage_id);
        }
    }

    void onARDrone3MediaRecordVideoUpdate (ARCOMMANDS_ARDRONE3_MEDIARECORD_VIDEO_RECORD_ENUM record, byte mass_storage_id) {
        if(_ARCommandARDrone3MediaRecordVideoListener != null) {
            _ARCommandARDrone3MediaRecordVideoListener.onARDrone3MediaRecordVideoUpdate (record, mass_storage_id);
        }
    }

    void onARDrone3MediaRecordPictureV2Update () {
        if(_ARCommandARDrone3MediaRecordPictureV2Listener != null) {
            _ARCommandARDrone3MediaRecordPictureV2Listener.onARDrone3MediaRecordPictureV2Update ();
        }
    }

    void onARDrone3MediaRecordVideoV2Update (ARCOMMANDS_ARDRONE3_MEDIARECORD_VIDEOV2_RECORD_ENUM record) {
        if(_ARCommandARDrone3MediaRecordVideoV2Listener != null) {
            _ARCommandARDrone3MediaRecordVideoV2Listener.onARDrone3MediaRecordVideoV2Update (record);
        }
    }

    void onARDrone3NetworkWifiScanUpdate (ARCOMMANDS_ARDRONE3_NETWORK_WIFISCAN_BAND_ENUM band) {
        if(_ARCommandARDrone3NetworkWifiScanListener != null) {
            _ARCommandARDrone3NetworkWifiScanListener.onARDrone3NetworkWifiScanUpdate (band);
        }
    }

    void onARDrone3NetworkWifiAuthChannelUpdate () {
        if(_ARCommandARDrone3NetworkWifiAuthChannelListener != null) {
            _ARCommandARDrone3NetworkWifiAuthChannelListener.onARDrone3NetworkWifiAuthChannelUpdate ();
        }
    }

    void onARDrone3PilotingSettingsMaxAltitudeUpdate (float current) {
        if(_ARCommandARDrone3PilotingSettingsMaxAltitudeListener != null) {
            _ARCommandARDrone3PilotingSettingsMaxAltitudeListener.onARDrone3PilotingSettingsMaxAltitudeUpdate (current);
        }
    }

    void onARDrone3PilotingSettingsMaxTiltUpdate (float current) {
        if(_ARCommandARDrone3PilotingSettingsMaxTiltListener != null) {
            _ARCommandARDrone3PilotingSettingsMaxTiltListener.onARDrone3PilotingSettingsMaxTiltUpdate (current);
        }
    }

    void onARDrone3PilotingSettingsAbsolutControlUpdate (byte on) {
        if(_ARCommandARDrone3PilotingSettingsAbsolutControlListener != null) {
            _ARCommandARDrone3PilotingSettingsAbsolutControlListener.onARDrone3PilotingSettingsAbsolutControlUpdate (on);
        }
    }

    void onARDrone3PilotingSettingsMaxDistanceUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsMaxDistanceListener != null) {
            _ARCommandARDrone3PilotingSettingsMaxDistanceListener.onARDrone3PilotingSettingsMaxDistanceUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsNoFlyOverMaxDistanceUpdate (byte shouldNotFlyOver) {
        if(_ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener != null) {
            _ARCommandARDrone3PilotingSettingsNoFlyOverMaxDistanceListener.onARDrone3PilotingSettingsNoFlyOverMaxDistanceUpdate (shouldNotFlyOver);
        }
    }

    void onARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedListener.onARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedListener.onARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener != null) {
            _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationListener.onARDrone3PilotingSettingsSetAutonomousFlightMaxHorizontalAccelerationUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener != null) {
            _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationListener.onARDrone3PilotingSettingsSetAutonomousFlightMaxVerticalAccelerationUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedListener.onARDrone3PilotingSettingsSetAutonomousFlightMaxRotationSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsBankedTurnUpdate (byte value) {
        if(_ARCommandARDrone3PilotingSettingsBankedTurnListener != null) {
            _ARCommandARDrone3PilotingSettingsBankedTurnListener.onARDrone3PilotingSettingsBankedTurnUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsMinAltitudeUpdate (float current) {
        if(_ARCommandARDrone3PilotingSettingsMinAltitudeListener != null) {
            _ARCommandARDrone3PilotingSettingsMinAltitudeListener.onARDrone3PilotingSettingsMinAltitudeUpdate (current);
        }
    }

    void onARDrone3PilotingSettingsCirclingDirectionUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSETTINGS_CIRCLINGDIRECTION_VALUE_ENUM value) {
        if(_ARCommandARDrone3PilotingSettingsCirclingDirectionListener != null) {
            _ARCommandARDrone3PilotingSettingsCirclingDirectionListener.onARDrone3PilotingSettingsCirclingDirectionUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsCirclingRadiusUpdate (short value) {
        if(_ARCommandARDrone3PilotingSettingsCirclingRadiusListener != null) {
            _ARCommandARDrone3PilotingSettingsCirclingRadiusListener.onARDrone3PilotingSettingsCirclingRadiusUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsCirclingAltitudeUpdate (short value) {
        if(_ARCommandARDrone3PilotingSettingsCirclingAltitudeListener != null) {
            _ARCommandARDrone3PilotingSettingsCirclingAltitudeListener.onARDrone3PilotingSettingsCirclingAltitudeUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsPitchModeUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSETTINGS_PITCHMODE_VALUE_ENUM value) {
        if(_ARCommandARDrone3PilotingSettingsPitchModeListener != null) {
            _ARCommandARDrone3PilotingSettingsPitchModeListener.onARDrone3PilotingSettingsPitchModeUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsSetMotionDetectionModeUpdate (byte enable) {
        if(_ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener != null) {
            _ARCommandARDrone3PilotingSettingsSetMotionDetectionModeListener.onARDrone3PilotingSettingsSetMotionDetectionModeUpdate (enable);
        }
    }

    void onARDrone3SpeedSettingsMaxVerticalSpeedUpdate (float current) {
        if(_ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener != null) {
            _ARCommandARDrone3SpeedSettingsMaxVerticalSpeedListener.onARDrone3SpeedSettingsMaxVerticalSpeedUpdate (current);
        }
    }

    void onARDrone3SpeedSettingsMaxRotationSpeedUpdate (float current) {
        if(_ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener != null) {
            _ARCommandARDrone3SpeedSettingsMaxRotationSpeedListener.onARDrone3SpeedSettingsMaxRotationSpeedUpdate (current);
        }
    }

    void onARDrone3SpeedSettingsHullProtectionUpdate (byte present) {
        if(_ARCommandARDrone3SpeedSettingsHullProtectionListener != null) {
            _ARCommandARDrone3SpeedSettingsHullProtectionListener.onARDrone3SpeedSettingsHullProtectionUpdate (present);
        }
    }

    void onARDrone3SpeedSettingsOutdoorUpdate (byte outdoor) {
        if(_ARCommandARDrone3SpeedSettingsOutdoorListener != null) {
            _ARCommandARDrone3SpeedSettingsOutdoorListener.onARDrone3SpeedSettingsOutdoorUpdate (outdoor);
        }
    }

    void onARDrone3SpeedSettingsMaxPitchRollRotationSpeedUpdate (float current) {
        if(_ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener != null) {
            _ARCommandARDrone3SpeedSettingsMaxPitchRollRotationSpeedListener.onARDrone3SpeedSettingsMaxPitchRollRotationSpeedUpdate (current);
        }
    }

    void onARDrone3NetworkSettingsWifiSelectionUpdate (ARCOMMANDS_ARDRONE3_NETWORKSETTINGS_WIFISELECTION_TYPE_ENUM type, ARCOMMANDS_ARDRONE3_NETWORKSETTINGS_WIFISELECTION_BAND_ENUM band, byte channel) {
        if(_ARCommandARDrone3NetworkSettingsWifiSelectionListener != null) {
            _ARCommandARDrone3NetworkSettingsWifiSelectionListener.onARDrone3NetworkSettingsWifiSelectionUpdate (type, band, channel);
        }
    }

    void onARDrone3NetworkSettingsWifiSecurityUpdate (ARCOMMANDS_ARDRONE3_NETWORKSETTINGS_WIFISECURITY_TYPE_ENUM type, String key, ARCOMMANDS_ARDRONE3_NETWORKSETTINGS_WIFISECURITY_KEYTYPE_ENUM keyType) {
        if(_ARCommandARDrone3NetworkSettingsWifiSecurityListener != null) {
            _ARCommandARDrone3NetworkSettingsWifiSecurityListener.onARDrone3NetworkSettingsWifiSecurityUpdate (type, key, keyType);
        }
    }

    void onARDrone3PictureSettingsPictureFormatSelectionUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_PICTUREFORMATSELECTION_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsPictureFormatSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsPictureFormatSelectionListener.onARDrone3PictureSettingsPictureFormatSelectionUpdate (type);
        }
    }

    void onARDrone3PictureSettingsAutoWhiteBalanceSelectionUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_AUTOWHITEBALANCESELECTION_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsAutoWhiteBalanceSelectionListener.onARDrone3PictureSettingsAutoWhiteBalanceSelectionUpdate (type);
        }
    }

    void onARDrone3PictureSettingsExpositionSelectionUpdate (float value) {
        if(_ARCommandARDrone3PictureSettingsExpositionSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsExpositionSelectionListener.onARDrone3PictureSettingsExpositionSelectionUpdate (value);
        }
    }

    void onARDrone3PictureSettingsSaturationSelectionUpdate (float value) {
        if(_ARCommandARDrone3PictureSettingsSaturationSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsSaturationSelectionListener.onARDrone3PictureSettingsSaturationSelectionUpdate (value);
        }
    }

    void onARDrone3PictureSettingsTimelapseSelectionUpdate (byte enabled, float interval) {
        if(_ARCommandARDrone3PictureSettingsTimelapseSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsTimelapseSelectionListener.onARDrone3PictureSettingsTimelapseSelectionUpdate (enabled, interval);
        }
    }

    void onARDrone3PictureSettingsVideoAutorecordSelectionUpdate (byte enabled, byte mass_storage_id) {
        if(_ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener != null) {
            _ARCommandARDrone3PictureSettingsVideoAutorecordSelectionListener.onARDrone3PictureSettingsVideoAutorecordSelectionUpdate (enabled, mass_storage_id);
        }
    }

    void onARDrone3PictureSettingsVideoStabilizationModeUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEOSTABILIZATIONMODE_MODE_ENUM mode) {
        if(_ARCommandARDrone3PictureSettingsVideoStabilizationModeListener != null) {
            _ARCommandARDrone3PictureSettingsVideoStabilizationModeListener.onARDrone3PictureSettingsVideoStabilizationModeUpdate (mode);
        }
    }

    void onARDrone3PictureSettingsVideoRecordingModeUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEORECORDINGMODE_MODE_ENUM mode) {
        if(_ARCommandARDrone3PictureSettingsVideoRecordingModeListener != null) {
            _ARCommandARDrone3PictureSettingsVideoRecordingModeListener.onARDrone3PictureSettingsVideoRecordingModeUpdate (mode);
        }
    }

    void onARDrone3PictureSettingsVideoFramerateUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEOFRAMERATE_FRAMERATE_ENUM framerate) {
        if(_ARCommandARDrone3PictureSettingsVideoFramerateListener != null) {
            _ARCommandARDrone3PictureSettingsVideoFramerateListener.onARDrone3PictureSettingsVideoFramerateUpdate (framerate);
        }
    }

    void onARDrone3PictureSettingsVideoResolutionsUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGS_VIDEORESOLUTIONS_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsVideoResolutionsListener != null) {
            _ARCommandARDrone3PictureSettingsVideoResolutionsListener.onARDrone3PictureSettingsVideoResolutionsUpdate (type);
        }
    }

    void onARDrone3MediaStreamingVideoEnableUpdate (byte enable) {
        if(_ARCommandARDrone3MediaStreamingVideoEnableListener != null) {
            _ARCommandARDrone3MediaStreamingVideoEnableListener.onARDrone3MediaStreamingVideoEnableUpdate (enable);
        }
    }

    void onARDrone3MediaStreamingVideoStreamModeUpdate (ARCOMMANDS_ARDRONE3_MEDIASTREAMING_VIDEOSTREAMMODE_MODE_ENUM mode) {
        if(_ARCommandARDrone3MediaStreamingVideoStreamModeListener != null) {
            _ARCommandARDrone3MediaStreamingVideoStreamModeListener.onARDrone3MediaStreamingVideoStreamModeUpdate (mode);
        }
    }

    void onARDrone3GPSSettingsSetHomeUpdate (double latitude, double longitude, double altitude) {
        if(_ARCommandARDrone3GPSSettingsSetHomeListener != null) {
            _ARCommandARDrone3GPSSettingsSetHomeListener.onARDrone3GPSSettingsSetHomeUpdate (latitude, longitude, altitude);
        }
    }

    void onARDrone3GPSSettingsResetHomeUpdate () {
        if(_ARCommandARDrone3GPSSettingsResetHomeListener != null) {
            _ARCommandARDrone3GPSSettingsResetHomeListener.onARDrone3GPSSettingsResetHomeUpdate ();
        }
    }

    void onARDrone3GPSSettingsSendControllerGPSUpdate (double latitude, double longitude, double altitude, double horizontalAccuracy, double verticalAccuracy) {
        if(_ARCommandARDrone3GPSSettingsSendControllerGPSListener != null) {
            _ARCommandARDrone3GPSSettingsSendControllerGPSListener.onARDrone3GPSSettingsSendControllerGPSUpdate (latitude, longitude, altitude, horizontalAccuracy, verticalAccuracy);
        }
    }

    void onARDrone3GPSSettingsHomeTypeUpdate (ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM type) {
        if(_ARCommandARDrone3GPSSettingsHomeTypeListener != null) {
            _ARCommandARDrone3GPSSettingsHomeTypeListener.onARDrone3GPSSettingsHomeTypeUpdate (type);
        }
    }

    void onARDrone3GPSSettingsReturnHomeDelayUpdate (short delay) {
        if(_ARCommandARDrone3GPSSettingsReturnHomeDelayListener != null) {
            _ARCommandARDrone3GPSSettingsReturnHomeDelayListener.onARDrone3GPSSettingsReturnHomeDelayUpdate (delay);
        }
    }

    void onARDrone3AntiflickeringElectricFrequencyUpdate (ARCOMMANDS_ARDRONE3_ANTIFLICKERING_ELECTRICFREQUENCY_FREQUENCY_ENUM frequency) {
        if(_ARCommandARDrone3AntiflickeringElectricFrequencyListener != null) {
            _ARCommandARDrone3AntiflickeringElectricFrequencyListener.onARDrone3AntiflickeringElectricFrequencyUpdate (frequency);
        }
    }

    void onARDrone3AntiflickeringSetModeUpdate (ARCOMMANDS_ARDRONE3_ANTIFLICKERING_SETMODE_MODE_ENUM mode) {
        if(_ARCommandARDrone3AntiflickeringSetModeListener != null) {
            _ARCommandARDrone3AntiflickeringSetModeListener.onARDrone3AntiflickeringSetModeUpdate (mode);
        }
    }

    void onARDrone3SoundStartAlertSoundUpdate () {
        if(_ARCommandARDrone3SoundStartAlertSoundListener != null) {
            _ARCommandARDrone3SoundStartAlertSoundListener.onARDrone3SoundStartAlertSoundUpdate ();
        }
    }

    void onARDrone3SoundStopAlertSoundUpdate () {
        if(_ARCommandARDrone3SoundStopAlertSoundListener != null) {
            _ARCommandARDrone3SoundStopAlertSoundListener.onARDrone3SoundStopAlertSoundUpdate ();
        }
    }

    void onARDrone3MediaRecordStatePictureStateChangedUpdate (byte state, byte mass_storage_id) {
        if(_ARCommandARDrone3MediaRecordStatePictureStateChangedListener != null) {
            _ARCommandARDrone3MediaRecordStatePictureStateChangedListener.onARDrone3MediaRecordStatePictureStateChangedUpdate (state, mass_storage_id);
        }
    }

    void onARDrone3MediaRecordStateVideoStateChangedUpdate (ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_VIDEOSTATECHANGED_STATE_ENUM state, byte mass_storage_id) {
        if(_ARCommandARDrone3MediaRecordStateVideoStateChangedListener != null) {
            _ARCommandARDrone3MediaRecordStateVideoStateChangedListener.onARDrone3MediaRecordStateVideoStateChangedUpdate (state, mass_storage_id);
        }
    }

    void onARDrone3MediaRecordStatePictureStateChangedV2Update (ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener != null) {
            _ARCommandARDrone3MediaRecordStatePictureStateChangedV2Listener.onARDrone3MediaRecordStatePictureStateChangedV2Update (state, error);
        }
    }

    void onARDrone3MediaRecordStateVideoStateChangedV2Update (ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener != null) {
            _ARCommandARDrone3MediaRecordStateVideoStateChangedV2Listener.onARDrone3MediaRecordStateVideoStateChangedV2Update (state, error);
        }
    }

    void onARDrone3MediaRecordStateVideoResolutionStateUpdate (ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_VIDEORESOLUTIONSTATE_STREAMING_ENUM streaming, ARCOMMANDS_ARDRONE3_MEDIARECORDSTATE_VIDEORESOLUTIONSTATE_RECORDING_ENUM recording) {
        if(_ARCommandARDrone3MediaRecordStateVideoResolutionStateListener != null) {
            _ARCommandARDrone3MediaRecordStateVideoResolutionStateListener.onARDrone3MediaRecordStateVideoResolutionStateUpdate (streaming, recording);
        }
    }

    void onARDrone3MediaRecordEventPictureEventChangedUpdate (ARCOMMANDS_ARDRONE3_MEDIARECORDEVENT_PICTUREEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_ARDRONE3_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandARDrone3MediaRecordEventPictureEventChangedListener != null) {
            _ARCommandARDrone3MediaRecordEventPictureEventChangedListener.onARDrone3MediaRecordEventPictureEventChangedUpdate (event, error);
        }
    }

    void onARDrone3MediaRecordEventVideoEventChangedUpdate (ARCOMMANDS_ARDRONE3_MEDIARECORDEVENT_VIDEOEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_ARDRONE3_MEDIARECORDEVENT_VIDEOEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandARDrone3MediaRecordEventVideoEventChangedListener != null) {
            _ARCommandARDrone3MediaRecordEventVideoEventChangedListener.onARDrone3MediaRecordEventVideoEventChangedUpdate (event, error);
        }
    }

    void onARDrone3PilotingStateFlatTrimChangedUpdate () {
        if(_ARCommandARDrone3PilotingStateFlatTrimChangedListener != null) {
            _ARCommandARDrone3PilotingStateFlatTrimChangedListener.onARDrone3PilotingStateFlatTrimChangedUpdate ();
        }
    }

    void onARDrone3PilotingStateFlyingStateChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandARDrone3PilotingStateFlyingStateChangedListener != null) {
            _ARCommandARDrone3PilotingStateFlyingStateChangedListener.onARDrone3PilotingStateFlyingStateChangedUpdate (state);
        }
    }

    void onARDrone3PilotingStateAlertStateChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandARDrone3PilotingStateAlertStateChangedListener != null) {
            _ARCommandARDrone3PilotingStateAlertStateChangedListener.onARDrone3PilotingStateAlertStateChangedUpdate (state);
        }
    }

    void onARDrone3PilotingStateNavigateHomeStateChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_STATE_ENUM state, ARCOMMANDS_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_REASON_ENUM reason) {
        if(_ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener != null) {
            _ARCommandARDrone3PilotingStateNavigateHomeStateChangedListener.onARDrone3PilotingStateNavigateHomeStateChangedUpdate (state, reason);
        }
    }

    void onARDrone3PilotingStatePositionChangedUpdate (double latitude, double longitude, double altitude) {
        if(_ARCommandARDrone3PilotingStatePositionChangedListener != null) {
            _ARCommandARDrone3PilotingStatePositionChangedListener.onARDrone3PilotingStatePositionChangedUpdate (latitude, longitude, altitude);
        }
    }

    void onARDrone3PilotingStateSpeedChangedUpdate (float speedX, float speedY, float speedZ) {
        if(_ARCommandARDrone3PilotingStateSpeedChangedListener != null) {
            _ARCommandARDrone3PilotingStateSpeedChangedListener.onARDrone3PilotingStateSpeedChangedUpdate (speedX, speedY, speedZ);
        }
    }

    void onARDrone3PilotingStateAttitudeChangedUpdate (float roll, float pitch, float yaw) {
        if(_ARCommandARDrone3PilotingStateAttitudeChangedListener != null) {
            _ARCommandARDrone3PilotingStateAttitudeChangedListener.onARDrone3PilotingStateAttitudeChangedUpdate (roll, pitch, yaw);
        }
    }

    void onARDrone3PilotingStateAutoTakeOffModeChangedUpdate (byte state) {
        if(_ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener != null) {
            _ARCommandARDrone3PilotingStateAutoTakeOffModeChangedListener.onARDrone3PilotingStateAutoTakeOffModeChangedUpdate (state);
        }
    }

    void onARDrone3PilotingStateAltitudeChangedUpdate (double altitude) {
        if(_ARCommandARDrone3PilotingStateAltitudeChangedListener != null) {
            _ARCommandARDrone3PilotingStateAltitudeChangedListener.onARDrone3PilotingStateAltitudeChangedUpdate (altitude);
        }
    }

    void onARDrone3PilotingStateGpsLocationChangedUpdate (double latitude, double longitude, double altitude, byte latitude_accuracy, byte longitude_accuracy, byte altitude_accuracy) {
        if(_ARCommandARDrone3PilotingStateGpsLocationChangedListener != null) {
            _ARCommandARDrone3PilotingStateGpsLocationChangedListener.onARDrone3PilotingStateGpsLocationChangedUpdate (latitude, longitude, altitude, latitude_accuracy, longitude_accuracy, altitude_accuracy);
        }
    }

    void onARDrone3PilotingStateLandingStateChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_LANDINGSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandARDrone3PilotingStateLandingStateChangedListener != null) {
            _ARCommandARDrone3PilotingStateLandingStateChangedListener.onARDrone3PilotingStateLandingStateChangedUpdate (state);
        }
    }

    void onARDrone3PilotingStateAirSpeedChangedUpdate (float airSpeed) {
        if(_ARCommandARDrone3PilotingStateAirSpeedChangedListener != null) {
            _ARCommandARDrone3PilotingStateAirSpeedChangedListener.onARDrone3PilotingStateAirSpeedChangedUpdate (airSpeed);
        }
    }

    void onARDrone3PilotingStateMoveToChangedUpdate (double latitude, double longitude, double altitude, ARCOMMANDS_ARDRONE3_PILOTINGSTATE_MOVETOCHANGED_ORIENTATION_MODE_ENUM orientation_mode, float heading, ARCOMMANDS_ARDRONE3_PILOTINGSTATE_MOVETOCHANGED_STATUS_ENUM status) {
        if(_ARCommandARDrone3PilotingStateMoveToChangedListener != null) {
            _ARCommandARDrone3PilotingStateMoveToChangedListener.onARDrone3PilotingStateMoveToChangedUpdate (latitude, longitude, altitude, orientation_mode, heading, status);
        }
    }

    void onARDrone3PilotingStateMotionStateUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_MOTIONSTATE_STATE_ENUM state) {
        if(_ARCommandARDrone3PilotingStateMotionStateListener != null) {
            _ARCommandARDrone3PilotingStateMotionStateListener.onARDrone3PilotingStateMotionStateUpdate (state);
        }
    }

    void onARDrone3PilotingStatePilotedPOIUpdate (double latitude, double longitude, double altitude, ARCOMMANDS_ARDRONE3_PILOTINGSTATE_PILOTEDPOI_STATUS_ENUM status) {
        if(_ARCommandARDrone3PilotingStatePilotedPOIListener != null) {
            _ARCommandARDrone3PilotingStatePilotedPOIListener.onARDrone3PilotingStatePilotedPOIUpdate (latitude, longitude, altitude, status);
        }
    }

    void onARDrone3PilotingStateReturnHomeBatteryCapacityUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY_STATUS_ENUM status) {
        if(_ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener != null) {
            _ARCommandARDrone3PilotingStateReturnHomeBatteryCapacityListener.onARDrone3PilotingStateReturnHomeBatteryCapacityUpdate (status);
        }
    }

    void onARDrone3PilotingEventMoveByEndUpdate (float dX, float dY, float dZ, float dPsi, ARCOMMANDS_ARDRONE3_PILOTINGEVENT_MOVEBYEND_ERROR_ENUM error) {
        if(_ARCommandARDrone3PilotingEventMoveByEndListener != null) {
            _ARCommandARDrone3PilotingEventMoveByEndListener.onARDrone3PilotingEventMoveByEndUpdate (dX, dY, dZ, dPsi, error);
        }
    }

    void onARDrone3NetworkStateWifiScanListChangedUpdate (String ssid, short rssi, ARCOMMANDS_ARDRONE3_NETWORKSTATE_WIFISCANLISTCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandARDrone3NetworkStateWifiScanListChangedListener != null) {
            _ARCommandARDrone3NetworkStateWifiScanListChangedListener.onARDrone3NetworkStateWifiScanListChangedUpdate (ssid, rssi, band, channel);
        }
    }

    void onARDrone3NetworkStateAllWifiScanChangedUpdate () {
        if(_ARCommandARDrone3NetworkStateAllWifiScanChangedListener != null) {
            _ARCommandARDrone3NetworkStateAllWifiScanChangedListener.onARDrone3NetworkStateAllWifiScanChangedUpdate ();
        }
    }

    void onARDrone3NetworkStateWifiAuthChannelListChangedUpdate (ARCOMMANDS_ARDRONE3_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED_BAND_ENUM band, byte channel, byte in_or_out) {
        if(_ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener != null) {
            _ARCommandARDrone3NetworkStateWifiAuthChannelListChangedListener.onARDrone3NetworkStateWifiAuthChannelListChangedUpdate (band, channel, in_or_out);
        }
    }

    void onARDrone3NetworkStateAllWifiAuthChannelChangedUpdate () {
        if(_ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener != null) {
            _ARCommandARDrone3NetworkStateAllWifiAuthChannelChangedListener.onARDrone3NetworkStateAllWifiAuthChannelChangedUpdate ();
        }
    }

    void onARDrone3PilotingSettingsStateMaxAltitudeChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateMaxAltitudeChangedListener.onARDrone3PilotingSettingsStateMaxAltitudeChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStateMaxTiltChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateMaxTiltChangedListener.onARDrone3PilotingSettingsStateMaxTiltChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStateAbsolutControlChangedUpdate (byte on) {
        if(_ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAbsolutControlChangedListener.onARDrone3PilotingSettingsStateAbsolutControlChangedUpdate (on);
        }
    }

    void onARDrone3PilotingSettingsStateMaxDistanceChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateMaxDistanceChangedListener.onARDrone3PilotingSettingsStateMaxDistanceChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedUpdate (byte shouldNotFlyOver) {
        if(_ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedListener.onARDrone3PilotingSettingsStateNoFlyOverMaxDistanceChangedUpdate (shouldNotFlyOver);
        }
    }

    void onARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedListener.onARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedListener.onARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationListener.onARDrone3PilotingSettingsStateAutonomousFlightMaxHorizontalAccelerationUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationListener.onARDrone3PilotingSettingsStateAutonomousFlightMaxVerticalAccelerationUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedUpdate (float value) {
        if(_ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedListener.onARDrone3PilotingSettingsStateAutonomousFlightMaxRotationSpeedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateBankedTurnChangedUpdate (byte state) {
        if(_ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateBankedTurnChangedListener.onARDrone3PilotingSettingsStateBankedTurnChangedUpdate (state);
        }
    }

    void onARDrone3PilotingSettingsStateMinAltitudeChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateMinAltitudeChangedListener.onARDrone3PilotingSettingsStateMinAltitudeChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStateCirclingDirectionChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSETTINGSSTATE_CIRCLINGDIRECTIONCHANGED_VALUE_ENUM value) {
        if(_ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateCirclingDirectionChangedListener.onARDrone3PilotingSettingsStateCirclingDirectionChangedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateCirclingRadiusChangedUpdate (short current, short min, short max) {
        if(_ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateCirclingRadiusChangedListener.onARDrone3PilotingSettingsStateCirclingRadiusChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStateCirclingAltitudeChangedUpdate (short current, short min, short max) {
        if(_ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStateCirclingAltitudeChangedListener.onARDrone3PilotingSettingsStateCirclingAltitudeChangedUpdate (current, min, max);
        }
    }

    void onARDrone3PilotingSettingsStatePitchModeChangedUpdate (ARCOMMANDS_ARDRONE3_PILOTINGSETTINGSSTATE_PITCHMODECHANGED_VALUE_ENUM value) {
        if(_ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener != null) {
            _ARCommandARDrone3PilotingSettingsStatePitchModeChangedListener.onARDrone3PilotingSettingsStatePitchModeChangedUpdate (value);
        }
    }

    void onARDrone3PilotingSettingsStateMotionDetectionUpdate (byte enabled) {
        if(_ARCommandARDrone3PilotingSettingsStateMotionDetectionListener != null) {
            _ARCommandARDrone3PilotingSettingsStateMotionDetectionListener.onARDrone3PilotingSettingsStateMotionDetectionUpdate (enabled);
        }
    }

    void onARDrone3SpeedSettingsStateMaxVerticalSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener != null) {
            _ARCommandARDrone3SpeedSettingsStateMaxVerticalSpeedChangedListener.onARDrone3SpeedSettingsStateMaxVerticalSpeedChangedUpdate (current, min, max);
        }
    }

    void onARDrone3SpeedSettingsStateMaxRotationSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener != null) {
            _ARCommandARDrone3SpeedSettingsStateMaxRotationSpeedChangedListener.onARDrone3SpeedSettingsStateMaxRotationSpeedChangedUpdate (current, min, max);
        }
    }

    void onARDrone3SpeedSettingsStateHullProtectionChangedUpdate (byte present) {
        if(_ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener != null) {
            _ARCommandARDrone3SpeedSettingsStateHullProtectionChangedListener.onARDrone3SpeedSettingsStateHullProtectionChangedUpdate (present);
        }
    }

    void onARDrone3SpeedSettingsStateOutdoorChangedUpdate (byte outdoor) {
        if(_ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener != null) {
            _ARCommandARDrone3SpeedSettingsStateOutdoorChangedListener.onARDrone3SpeedSettingsStateOutdoorChangedUpdate (outdoor);
        }
    }

    void onARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener != null) {
            _ARCommandARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedListener.onARDrone3SpeedSettingsStateMaxPitchRollRotationSpeedChangedUpdate (current, min, max);
        }
    }

    void onARDrone3NetworkSettingsStateWifiSelectionChangedUpdate (ARCOMMANDS_ARDRONE3_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_TYPE_ENUM type, ARCOMMANDS_ARDRONE3_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener != null) {
            _ARCommandARDrone3NetworkSettingsStateWifiSelectionChangedListener.onARDrone3NetworkSettingsStateWifiSelectionChangedUpdate (type, band, channel);
        }
    }

    void onARDrone3NetworkSettingsStateWifiSecurityChangedUpdate (ARCOMMANDS_ARDRONE3_NETWORKSETTINGSSTATE_WIFISECURITYCHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener != null) {
            _ARCommandARDrone3NetworkSettingsStateWifiSecurityChangedListener.onARDrone3NetworkSettingsStateWifiSecurityChangedUpdate (type);
        }
    }

    void onARDrone3NetworkSettingsStateWifiSecurityUpdate (ARCOMMANDS_ARDRONE3_NETWORKSETTINGSSTATE_WIFISECURITY_TYPE_ENUM type, String key, ARCOMMANDS_ARDRONE3_NETWORKSETTINGSSTATE_WIFISECURITY_KEYTYPE_ENUM keyType) {
        if(_ARCommandARDrone3NetworkSettingsStateWifiSecurityListener != null) {
            _ARCommandARDrone3NetworkSettingsStateWifiSecurityListener.onARDrone3NetworkSettingsStateWifiSecurityUpdate (type, key, keyType);
        }
    }

    void onARDrone3SettingsStateProductMotorVersionListChangedUpdate (byte motor_number, String type, String software, String hardware) {
        if(_ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener != null) {
            _ARCommandARDrone3SettingsStateProductMotorVersionListChangedListener.onARDrone3SettingsStateProductMotorVersionListChangedUpdate (motor_number, type, software, hardware);
        }
    }

    void onARDrone3SettingsStateProductGPSVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandARDrone3SettingsStateProductGPSVersionChangedListener != null) {
            _ARCommandARDrone3SettingsStateProductGPSVersionChangedListener.onARDrone3SettingsStateProductGPSVersionChangedUpdate (software, hardware);
        }
    }

    void onARDrone3SettingsStateMotorErrorStateChangedUpdate (byte motorIds, ARCOMMANDS_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED_MOTORERROR_ENUM motorError) {
        if(_ARCommandARDrone3SettingsStateMotorErrorStateChangedListener != null) {
            _ARCommandARDrone3SettingsStateMotorErrorStateChangedListener.onARDrone3SettingsStateMotorErrorStateChangedUpdate (motorIds, motorError);
        }
    }

    void onARDrone3SettingsStateMotorSoftwareVersionChangedUpdate (String version) {
        if(_ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener != null) {
            _ARCommandARDrone3SettingsStateMotorSoftwareVersionChangedListener.onARDrone3SettingsStateMotorSoftwareVersionChangedUpdate (version);
        }
    }

    void onARDrone3SettingsStateMotorFlightsStatusChangedUpdate (short nbFlights, short lastFlightDuration, int totalFlightDuration) {
        if(_ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener != null) {
            _ARCommandARDrone3SettingsStateMotorFlightsStatusChangedListener.onARDrone3SettingsStateMotorFlightsStatusChangedUpdate (nbFlights, lastFlightDuration, totalFlightDuration);
        }
    }

    void onARDrone3SettingsStateMotorErrorLastErrorChangedUpdate (ARCOMMANDS_ARDRONE3_SETTINGSSTATE_MOTORERRORLASTERRORCHANGED_MOTORERROR_ENUM motorError) {
        if(_ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener != null) {
            _ARCommandARDrone3SettingsStateMotorErrorLastErrorChangedListener.onARDrone3SettingsStateMotorErrorLastErrorChangedUpdate (motorError);
        }
    }

    void onARDrone3SettingsStateP7IDUpdate (String serialID) {
        if(_ARCommandARDrone3SettingsStateP7IDListener != null) {
            _ARCommandARDrone3SettingsStateP7IDListener.onARDrone3SettingsStateP7IDUpdate (serialID);
        }
    }

    void onARDrone3SettingsStateCPUIDUpdate (String id) {
        if(_ARCommandARDrone3SettingsStateCPUIDListener != null) {
            _ARCommandARDrone3SettingsStateCPUIDListener.onARDrone3SettingsStateCPUIDUpdate (id);
        }
    }

    void onARDrone3PictureSettingsStatePictureFormatChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_PICTUREFORMATCHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStatePictureFormatChangedListener.onARDrone3PictureSettingsStatePictureFormatChangedUpdate (type);
        }
    }

    void onARDrone3PictureSettingsStateAutoWhiteBalanceChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_AUTOWHITEBALANCECHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateAutoWhiteBalanceChangedListener.onARDrone3PictureSettingsStateAutoWhiteBalanceChangedUpdate (type);
        }
    }

    void onARDrone3PictureSettingsStateExpositionChangedUpdate (float value, float min, float max) {
        if(_ARCommandARDrone3PictureSettingsStateExpositionChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateExpositionChangedListener.onARDrone3PictureSettingsStateExpositionChangedUpdate (value, min, max);
        }
    }

    void onARDrone3PictureSettingsStateSaturationChangedUpdate (float value, float min, float max) {
        if(_ARCommandARDrone3PictureSettingsStateSaturationChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateSaturationChangedListener.onARDrone3PictureSettingsStateSaturationChangedUpdate (value, min, max);
        }
    }

    void onARDrone3PictureSettingsStateTimelapseChangedUpdate (byte enabled, float interval, float minInterval, float maxInterval) {
        if(_ARCommandARDrone3PictureSettingsStateTimelapseChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateTimelapseChangedListener.onARDrone3PictureSettingsStateTimelapseChangedUpdate (enabled, interval, minInterval, maxInterval);
        }
    }

    void onARDrone3PictureSettingsStateVideoAutorecordChangedUpdate (byte enabled, byte mass_storage_id) {
        if(_ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateVideoAutorecordChangedListener.onARDrone3PictureSettingsStateVideoAutorecordChangedUpdate (enabled, mass_storage_id);
        }
    }

    void onARDrone3PictureSettingsStateVideoStabilizationModeChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEOSTABILIZATIONMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateVideoStabilizationModeChangedListener.onARDrone3PictureSettingsStateVideoStabilizationModeChangedUpdate (mode);
        }
    }

    void onARDrone3PictureSettingsStateVideoRecordingModeChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEORECORDINGMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateVideoRecordingModeChangedListener.onARDrone3PictureSettingsStateVideoRecordingModeChangedUpdate (mode);
        }
    }

    void onARDrone3PictureSettingsStateVideoFramerateChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEOFRAMERATECHANGED_FRAMERATE_ENUM framerate) {
        if(_ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateVideoFramerateChangedListener.onARDrone3PictureSettingsStateVideoFramerateChangedUpdate (framerate);
        }
    }

    void onARDrone3PictureSettingsStateVideoResolutionsChangedUpdate (ARCOMMANDS_ARDRONE3_PICTURESETTINGSSTATE_VIDEORESOLUTIONSCHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener != null) {
            _ARCommandARDrone3PictureSettingsStateVideoResolutionsChangedListener.onARDrone3PictureSettingsStateVideoResolutionsChangedUpdate (type);
        }
    }

    void onARDrone3MediaStreamingStateVideoEnableChangedUpdate (ARCOMMANDS_ARDRONE3_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED_ENABLED_ENUM enabled) {
        if(_ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener != null) {
            _ARCommandARDrone3MediaStreamingStateVideoEnableChangedListener.onARDrone3MediaStreamingStateVideoEnableChangedUpdate (enabled);
        }
    }

    void onARDrone3MediaStreamingStateVideoStreamModeChangedUpdate (ARCOMMANDS_ARDRONE3_MEDIASTREAMINGSTATE_VIDEOSTREAMMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener != null) {
            _ARCommandARDrone3MediaStreamingStateVideoStreamModeChangedListener.onARDrone3MediaStreamingStateVideoStreamModeChangedUpdate (mode);
        }
    }

    void onARDrone3GPSSettingsStateHomeChangedUpdate (double latitude, double longitude, double altitude) {
        if(_ARCommandARDrone3GPSSettingsStateHomeChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateHomeChangedListener.onARDrone3GPSSettingsStateHomeChangedUpdate (latitude, longitude, altitude);
        }
    }

    void onARDrone3GPSSettingsStateResetHomeChangedUpdate (double latitude, double longitude, double altitude) {
        if(_ARCommandARDrone3GPSSettingsStateResetHomeChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateResetHomeChangedListener.onARDrone3GPSSettingsStateResetHomeChangedUpdate (latitude, longitude, altitude);
        }
    }

    void onARDrone3GPSSettingsStateGPSFixStateChangedUpdate (byte fixed) {
        if(_ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateGPSFixStateChangedListener.onARDrone3GPSSettingsStateGPSFixStateChangedUpdate (fixed);
        }
    }

    void onARDrone3GPSSettingsStateGPSUpdateStateChangedUpdate (ARCOMMANDS_ARDRONE3_GPSSETTINGSSTATE_GPSUPDATESTATECHANGED_STATE_ENUM state) {
        if(_ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateGPSUpdateStateChangedListener.onARDrone3GPSSettingsStateGPSUpdateStateChangedUpdate (state);
        }
    }

    void onARDrone3GPSSettingsStateHomeTypeChangedUpdate (ARCOMMANDS_ARDRONE3_GPSSETTINGSSTATE_HOMETYPECHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateHomeTypeChangedListener.onARDrone3GPSSettingsStateHomeTypeChangedUpdate (type);
        }
    }

    void onARDrone3GPSSettingsStateReturnHomeDelayChangedUpdate (short delay) {
        if(_ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateReturnHomeDelayChangedListener.onARDrone3GPSSettingsStateReturnHomeDelayChangedUpdate (delay);
        }
    }

    void onARDrone3GPSSettingsStateGeofenceCenterChangedUpdate (double latitude, double longitude) {
        if(_ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener != null) {
            _ARCommandARDrone3GPSSettingsStateGeofenceCenterChangedListener.onARDrone3GPSSettingsStateGeofenceCenterChangedUpdate (latitude, longitude);
        }
    }

    void onARDrone3CameraStateOrientationUpdate (byte tilt, byte pan) {
        if(_ARCommandARDrone3CameraStateOrientationListener != null) {
            _ARCommandARDrone3CameraStateOrientationListener.onARDrone3CameraStateOrientationUpdate (tilt, pan);
        }
    }

    void onARDrone3CameraStateDefaultCameraOrientationUpdate (byte tilt, byte pan) {
        if(_ARCommandARDrone3CameraStateDefaultCameraOrientationListener != null) {
            _ARCommandARDrone3CameraStateDefaultCameraOrientationListener.onARDrone3CameraStateDefaultCameraOrientationUpdate (tilt, pan);
        }
    }

    void onARDrone3CameraStateOrientationV2Update (float tilt, float pan) {
        if(_ARCommandARDrone3CameraStateOrientationV2Listener != null) {
            _ARCommandARDrone3CameraStateOrientationV2Listener.onARDrone3CameraStateOrientationV2Update (tilt, pan);
        }
    }

    void onARDrone3CameraStateDefaultCameraOrientationV2Update (float tilt, float pan) {
        if(_ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener != null) {
            _ARCommandARDrone3CameraStateDefaultCameraOrientationV2Listener.onARDrone3CameraStateDefaultCameraOrientationV2Update (tilt, pan);
        }
    }

    void onARDrone3CameraStateVelocityRangeUpdate (float max_tilt, float max_pan) {
        if(_ARCommandARDrone3CameraStateVelocityRangeListener != null) {
            _ARCommandARDrone3CameraStateVelocityRangeListener.onARDrone3CameraStateVelocityRangeUpdate (max_tilt, max_pan);
        }
    }

    void onARDrone3AntiflickeringStateElectricFrequencyChangedUpdate (ARCOMMANDS_ARDRONE3_ANTIFLICKERINGSTATE_ELECTRICFREQUENCYCHANGED_FREQUENCY_ENUM frequency) {
        if(_ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener != null) {
            _ARCommandARDrone3AntiflickeringStateElectricFrequencyChangedListener.onARDrone3AntiflickeringStateElectricFrequencyChangedUpdate (frequency);
        }
    }

    void onARDrone3AntiflickeringStateModeChangedUpdate (ARCOMMANDS_ARDRONE3_ANTIFLICKERINGSTATE_MODECHANGED_MODE_ENUM mode) {
        if(_ARCommandARDrone3AntiflickeringStateModeChangedListener != null) {
            _ARCommandARDrone3AntiflickeringStateModeChangedListener.onARDrone3AntiflickeringStateModeChangedUpdate (mode);
        }
    }

    void onARDrone3GPSStateNumberOfSatelliteChangedUpdate (byte numberOfSatellite) {
        if(_ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener != null) {
            _ARCommandARDrone3GPSStateNumberOfSatelliteChangedListener.onARDrone3GPSStateNumberOfSatelliteChangedUpdate (numberOfSatellite);
        }
    }

    void onARDrone3GPSStateHomeTypeAvailabilityChangedUpdate (ARCOMMANDS_ARDRONE3_GPSSTATE_HOMETYPEAVAILABILITYCHANGED_TYPE_ENUM type, byte available) {
        if(_ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener != null) {
            _ARCommandARDrone3GPSStateHomeTypeAvailabilityChangedListener.onARDrone3GPSStateHomeTypeAvailabilityChangedUpdate (type, available);
        }
    }

    void onARDrone3GPSStateHomeTypeChosenChangedUpdate (ARCOMMANDS_ARDRONE3_GPSSTATE_HOMETYPECHOSENCHANGED_TYPE_ENUM type) {
        if(_ARCommandARDrone3GPSStateHomeTypeChosenChangedListener != null) {
            _ARCommandARDrone3GPSStateHomeTypeChosenChangedListener.onARDrone3GPSStateHomeTypeChosenChangedUpdate (type);
        }
    }

    void onARDrone3PROStateFeaturesUpdate (long features) {
        if(_ARCommandARDrone3PROStateFeaturesListener != null) {
            _ARCommandARDrone3PROStateFeaturesListener.onARDrone3PROStateFeaturesUpdate (features);
        }
    }

    void onARDrone3AccessoryStateConnectedAccessoriesUpdate (byte id, ARCOMMANDS_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_ACCESSORY_TYPE_ENUM accessory_type, String uid, String swVersion, byte list_flags) {
        if(_ARCommandARDrone3AccessoryStateConnectedAccessoriesListener != null) {
            _ARCommandARDrone3AccessoryStateConnectedAccessoriesListener.onARDrone3AccessoryStateConnectedAccessoriesUpdate (id, accessory_type, uid, swVersion, list_flags);
        }
    }

    void onARDrone3AccessoryStateBatteryUpdate (byte id, byte batteryLevel, byte list_flags) {
        if(_ARCommandARDrone3AccessoryStateBatteryListener != null) {
            _ARCommandARDrone3AccessoryStateBatteryListener.onARDrone3AccessoryStateBatteryUpdate (id, batteryLevel, list_flags);
        }
    }

    void onARDrone3SoundStateAlertSoundUpdate (ARCOMMANDS_ARDRONE3_SOUNDSTATE_ALERTSOUND_STATE_ENUM state) {
        if(_ARCommandARDrone3SoundStateAlertSoundListener != null) {
            _ARCommandARDrone3SoundStateAlertSoundListener.onARDrone3SoundStateAlertSoundUpdate (state);
        }
    }

    void onCommonNetworkDisconnectUpdate () {
        if(_ARCommandCommonNetworkDisconnectListener != null) {
            _ARCommandCommonNetworkDisconnectListener.onCommonNetworkDisconnectUpdate ();
        }
    }

    void onCommonSettingsAllSettingsUpdate () {
        if(_ARCommandCommonSettingsAllSettingsListener != null) {
            _ARCommandCommonSettingsAllSettingsListener.onCommonSettingsAllSettingsUpdate ();
        }
    }

    void onCommonSettingsResetUpdate () {
        if(_ARCommandCommonSettingsResetListener != null) {
            _ARCommandCommonSettingsResetListener.onCommonSettingsResetUpdate ();
        }
    }

    void onCommonSettingsProductNameUpdate (String name) {
        if(_ARCommandCommonSettingsProductNameListener != null) {
            _ARCommandCommonSettingsProductNameListener.onCommonSettingsProductNameUpdate (name);
        }
    }

    void onCommonSettingsCountryUpdate (String code) {
        if(_ARCommandCommonSettingsCountryListener != null) {
            _ARCommandCommonSettingsCountryListener.onCommonSettingsCountryUpdate (code);
        }
    }

    void onCommonSettingsAutoCountryUpdate (byte automatic) {
        if(_ARCommandCommonSettingsAutoCountryListener != null) {
            _ARCommandCommonSettingsAutoCountryListener.onCommonSettingsAutoCountryUpdate (automatic);
        }
    }

    void onCommonCommonAllStatesUpdate () {
        if(_ARCommandCommonCommonAllStatesListener != null) {
            _ARCommandCommonCommonAllStatesListener.onCommonCommonAllStatesUpdate ();
        }
    }

    void onCommonCommonCurrentDateUpdate (String date) {
        if(_ARCommandCommonCommonCurrentDateListener != null) {
            _ARCommandCommonCommonCurrentDateListener.onCommonCommonCurrentDateUpdate (date);
        }
    }

    void onCommonCommonCurrentTimeUpdate (String time) {
        if(_ARCommandCommonCommonCurrentTimeListener != null) {
            _ARCommandCommonCommonCurrentTimeListener.onCommonCommonCurrentTimeUpdate (time);
        }
    }

    void onCommonCommonRebootUpdate () {
        if(_ARCommandCommonCommonRebootListener != null) {
            _ARCommandCommonCommonRebootListener.onCommonCommonRebootUpdate ();
        }
    }

    void onCommonOverHeatSwitchOffUpdate () {
        if(_ARCommandCommonOverHeatSwitchOffListener != null) {
            _ARCommandCommonOverHeatSwitchOffListener.onCommonOverHeatSwitchOffUpdate ();
        }
    }

    void onCommonOverHeatVentilateUpdate () {
        if(_ARCommandCommonOverHeatVentilateListener != null) {
            _ARCommandCommonOverHeatVentilateListener.onCommonOverHeatVentilateUpdate ();
        }
    }

    void onCommonControllerIsPilotingUpdate (byte piloting) {
        if(_ARCommandCommonControllerIsPilotingListener != null) {
            _ARCommandCommonControllerIsPilotingListener.onCommonControllerIsPilotingUpdate (piloting);
        }
    }

    void onCommonWifiSettingsOutdoorSettingUpdate (byte outdoor) {
        if(_ARCommandCommonWifiSettingsOutdoorSettingListener != null) {
            _ARCommandCommonWifiSettingsOutdoorSettingListener.onCommonWifiSettingsOutdoorSettingUpdate (outdoor);
        }
    }

    void onCommonMavlinkStartUpdate (String filepath, ARCOMMANDS_COMMON_MAVLINK_START_TYPE_ENUM type) {
        if(_ARCommandCommonMavlinkStartListener != null) {
            _ARCommandCommonMavlinkStartListener.onCommonMavlinkStartUpdate (filepath, type);
        }
    }

    void onCommonMavlinkPauseUpdate () {
        if(_ARCommandCommonMavlinkPauseListener != null) {
            _ARCommandCommonMavlinkPauseListener.onCommonMavlinkPauseUpdate ();
        }
    }

    void onCommonMavlinkStopUpdate () {
        if(_ARCommandCommonMavlinkStopListener != null) {
            _ARCommandCommonMavlinkStopListener.onCommonMavlinkStopUpdate ();
        }
    }

    void onCommonFlightPlanSettingsReturnHomeOnDisconnectUpdate (byte value) {
        if(_ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener != null) {
            _ARCommandCommonFlightPlanSettingsReturnHomeOnDisconnectListener.onCommonFlightPlanSettingsReturnHomeOnDisconnectUpdate (value);
        }
    }

    void onCommonCalibrationMagnetoCalibrationUpdate (byte calibrate) {
        if(_ARCommandCommonCalibrationMagnetoCalibrationListener != null) {
            _ARCommandCommonCalibrationMagnetoCalibrationListener.onCommonCalibrationMagnetoCalibrationUpdate (calibrate);
        }
    }

    void onCommonCalibrationPitotCalibrationUpdate (byte calibrate) {
        if(_ARCommandCommonCalibrationPitotCalibrationListener != null) {
            _ARCommandCommonCalibrationPitotCalibrationListener.onCommonCalibrationPitotCalibrationUpdate (calibrate);
        }
    }

    void onCommonGPSControllerPositionForRunUpdate (double latitude, double longitude) {
        if(_ARCommandCommonGPSControllerPositionForRunListener != null) {
            _ARCommandCommonGPSControllerPositionForRunListener.onCommonGPSControllerPositionForRunUpdate (latitude, longitude);
        }
    }

    void onCommonAudioControllerReadyForStreamingUpdate (byte ready) {
        if(_ARCommandCommonAudioControllerReadyForStreamingListener != null) {
            _ARCommandCommonAudioControllerReadyForStreamingListener.onCommonAudioControllerReadyForStreamingUpdate (ready);
        }
    }

    void onCommonHeadlightsIntensityUpdate (byte left, byte right) {
        if(_ARCommandCommonHeadlightsIntensityListener != null) {
            _ARCommandCommonHeadlightsIntensityListener.onCommonHeadlightsIntensityUpdate (left, right);
        }
    }

    void onCommonAnimationsStartAnimationUpdate (ARCOMMANDS_COMMON_ANIMATIONS_STARTANIMATION_ANIM_ENUM anim) {
        if(_ARCommandCommonAnimationsStartAnimationListener != null) {
            _ARCommandCommonAnimationsStartAnimationListener.onCommonAnimationsStartAnimationUpdate (anim);
        }
    }

    void onCommonAnimationsStopAnimationUpdate (ARCOMMANDS_COMMON_ANIMATIONS_STOPANIMATION_ANIM_ENUM anim) {
        if(_ARCommandCommonAnimationsStopAnimationListener != null) {
            _ARCommandCommonAnimationsStopAnimationListener.onCommonAnimationsStopAnimationUpdate (anim);
        }
    }

    void onCommonAnimationsStopAllAnimationsUpdate () {
        if(_ARCommandCommonAnimationsStopAllAnimationsListener != null) {
            _ARCommandCommonAnimationsStopAllAnimationsListener.onCommonAnimationsStopAllAnimationsUpdate ();
        }
    }

    void onCommonAccessoryConfigUpdate (ARCOMMANDS_COMMON_ACCESSORY_CONFIG_ACCESSORY_ENUM accessory) {
        if(_ARCommandCommonAccessoryConfigListener != null) {
            _ARCommandCommonAccessoryConfigListener.onCommonAccessoryConfigUpdate (accessory);
        }
    }

    void onCommonChargerSetMaxChargeRateUpdate (ARCOMMANDS_COMMON_CHARGER_SETMAXCHARGERATE_RATE_ENUM rate) {
        if(_ARCommandCommonChargerSetMaxChargeRateListener != null) {
            _ARCommandCommonChargerSetMaxChargeRateListener.onCommonChargerSetMaxChargeRateUpdate (rate);
        }
    }

    void onCommonFactoryResetUpdate () {
        if(_ARCommandCommonFactoryResetListener != null) {
            _ARCommandCommonFactoryResetListener.onCommonFactoryResetUpdate ();
        }
    }

    void onCommonNetworkEventDisconnectionUpdate (ARCOMMANDS_COMMON_NETWORKEVENT_DISCONNECTION_CAUSE_ENUM cause) {
        if(_ARCommandCommonNetworkEventDisconnectionListener != null) {
            _ARCommandCommonNetworkEventDisconnectionListener.onCommonNetworkEventDisconnectionUpdate (cause);
        }
    }

    void onCommonSettingsStateAllSettingsChangedUpdate () {
        if(_ARCommandCommonSettingsStateAllSettingsChangedListener != null) {
            _ARCommandCommonSettingsStateAllSettingsChangedListener.onCommonSettingsStateAllSettingsChangedUpdate ();
        }
    }

    void onCommonSettingsStateResetChangedUpdate () {
        if(_ARCommandCommonSettingsStateResetChangedListener != null) {
            _ARCommandCommonSettingsStateResetChangedListener.onCommonSettingsStateResetChangedUpdate ();
        }
    }

    void onCommonSettingsStateProductNameChangedUpdate (String name) {
        if(_ARCommandCommonSettingsStateProductNameChangedListener != null) {
            _ARCommandCommonSettingsStateProductNameChangedListener.onCommonSettingsStateProductNameChangedUpdate (name);
        }
    }

    void onCommonSettingsStateProductVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandCommonSettingsStateProductVersionChangedListener != null) {
            _ARCommandCommonSettingsStateProductVersionChangedListener.onCommonSettingsStateProductVersionChangedUpdate (software, hardware);
        }
    }

    void onCommonSettingsStateProductSerialHighChangedUpdate (String high) {
        if(_ARCommandCommonSettingsStateProductSerialHighChangedListener != null) {
            _ARCommandCommonSettingsStateProductSerialHighChangedListener.onCommonSettingsStateProductSerialHighChangedUpdate (high);
        }
    }

    void onCommonSettingsStateProductSerialLowChangedUpdate (String low) {
        if(_ARCommandCommonSettingsStateProductSerialLowChangedListener != null) {
            _ARCommandCommonSettingsStateProductSerialLowChangedListener.onCommonSettingsStateProductSerialLowChangedUpdate (low);
        }
    }

    void onCommonSettingsStateCountryChangedUpdate (String code) {
        if(_ARCommandCommonSettingsStateCountryChangedListener != null) {
            _ARCommandCommonSettingsStateCountryChangedListener.onCommonSettingsStateCountryChangedUpdate (code);
        }
    }

    void onCommonSettingsStateAutoCountryChangedUpdate (byte automatic) {
        if(_ARCommandCommonSettingsStateAutoCountryChangedListener != null) {
            _ARCommandCommonSettingsStateAutoCountryChangedListener.onCommonSettingsStateAutoCountryChangedUpdate (automatic);
        }
    }

    void onCommonCommonStateAllStatesChangedUpdate () {
        if(_ARCommandCommonCommonStateAllStatesChangedListener != null) {
            _ARCommandCommonCommonStateAllStatesChangedListener.onCommonCommonStateAllStatesChangedUpdate ();
        }
    }

    void onCommonCommonStateBatteryStateChangedUpdate (byte percent) {
        if(_ARCommandCommonCommonStateBatteryStateChangedListener != null) {
            _ARCommandCommonCommonStateBatteryStateChangedListener.onCommonCommonStateBatteryStateChangedUpdate (percent);
        }
    }

    void onCommonCommonStateMassStorageStateListChangedUpdate (byte mass_storage_id, String name) {
        if(_ARCommandCommonCommonStateMassStorageStateListChangedListener != null) {
            _ARCommandCommonCommonStateMassStorageStateListChangedListener.onCommonCommonStateMassStorageStateListChangedUpdate (mass_storage_id, name);
        }
    }

    void onCommonCommonStateMassStorageInfoStateListChangedUpdate (byte mass_storage_id, int size, int used_size, byte plugged, byte full, byte internal) {
        if(_ARCommandCommonCommonStateMassStorageInfoStateListChangedListener != null) {
            _ARCommandCommonCommonStateMassStorageInfoStateListChangedListener.onCommonCommonStateMassStorageInfoStateListChangedUpdate (mass_storage_id, size, used_size, plugged, full, internal);
        }
    }

    void onCommonCommonStateCurrentDateChangedUpdate (String date) {
        if(_ARCommandCommonCommonStateCurrentDateChangedListener != null) {
            _ARCommandCommonCommonStateCurrentDateChangedListener.onCommonCommonStateCurrentDateChangedUpdate (date);
        }
    }

    void onCommonCommonStateCurrentTimeChangedUpdate (String time) {
        if(_ARCommandCommonCommonStateCurrentTimeChangedListener != null) {
            _ARCommandCommonCommonStateCurrentTimeChangedListener.onCommonCommonStateCurrentTimeChangedUpdate (time);
        }
    }

    void onCommonCommonStateMassStorageInfoRemainingListChangedUpdate (int free_space, short rec_time, int photo_remaining) {
        if(_ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener != null) {
            _ARCommandCommonCommonStateMassStorageInfoRemainingListChangedListener.onCommonCommonStateMassStorageInfoRemainingListChangedUpdate (free_space, rec_time, photo_remaining);
        }
    }

    void onCommonCommonStateWifiSignalChangedUpdate (short rssi) {
        if(_ARCommandCommonCommonStateWifiSignalChangedListener != null) {
            _ARCommandCommonCommonStateWifiSignalChangedListener.onCommonCommonStateWifiSignalChangedUpdate (rssi);
        }
    }

    void onCommonCommonStateSensorsStatesListChangedUpdate (ARCOMMANDS_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED_SENSORNAME_ENUM sensorName, byte sensorState) {
        if(_ARCommandCommonCommonStateSensorsStatesListChangedListener != null) {
            _ARCommandCommonCommonStateSensorsStatesListChangedListener.onCommonCommonStateSensorsStatesListChangedUpdate (sensorName, sensorState);
        }
    }

    void onCommonCommonStateProductModelUpdate (ARCOMMANDS_COMMON_COMMONSTATE_PRODUCTMODEL_MODEL_ENUM model) {
        if(_ARCommandCommonCommonStateProductModelListener != null) {
            _ARCommandCommonCommonStateProductModelListener.onCommonCommonStateProductModelUpdate (model);
        }
    }

    void onCommonCommonStateCountryListKnownUpdate (byte listFlags, String countryCodes) {
        if(_ARCommandCommonCommonStateCountryListKnownListener != null) {
            _ARCommandCommonCommonStateCountryListKnownListener.onCommonCommonStateCountryListKnownUpdate (listFlags, countryCodes);
        }
    }

    void onCommonCommonStateDeprecatedMassStorageContentChangedUpdate (byte mass_storage_id, short nbPhotos, short nbVideos, short nbPuds, short nbCrashLogs) {
        if(_ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener != null) {
            _ARCommandCommonCommonStateDeprecatedMassStorageContentChangedListener.onCommonCommonStateDeprecatedMassStorageContentChangedUpdate (mass_storage_id, nbPhotos, nbVideos, nbPuds, nbCrashLogs);
        }
    }

    void onCommonCommonStateMassStorageContentUpdate (byte mass_storage_id, short nbPhotos, short nbVideos, short nbPuds, short nbCrashLogs, short nbRawPhotos) {
        if(_ARCommandCommonCommonStateMassStorageContentListener != null) {
            _ARCommandCommonCommonStateMassStorageContentListener.onCommonCommonStateMassStorageContentUpdate (mass_storage_id, nbPhotos, nbVideos, nbPuds, nbCrashLogs, nbRawPhotos);
        }
    }

    void onCommonCommonStateMassStorageContentForCurrentRunUpdate (byte mass_storage_id, short nbPhotos, short nbVideos, short nbRawPhotos) {
        if(_ARCommandCommonCommonStateMassStorageContentForCurrentRunListener != null) {
            _ARCommandCommonCommonStateMassStorageContentForCurrentRunListener.onCommonCommonStateMassStorageContentForCurrentRunUpdate (mass_storage_id, nbPhotos, nbVideos, nbRawPhotos);
        }
    }

    void onCommonCommonStateVideoRecordingTimestampUpdate (long startTimestamp, long stopTimestamp) {
        if(_ARCommandCommonCommonStateVideoRecordingTimestampListener != null) {
            _ARCommandCommonCommonStateVideoRecordingTimestampListener.onCommonCommonStateVideoRecordingTimestampUpdate (startTimestamp, stopTimestamp);
        }
    }

    void onCommonOverHeatStateOverHeatChangedUpdate () {
        if(_ARCommandCommonOverHeatStateOverHeatChangedListener != null) {
            _ARCommandCommonOverHeatStateOverHeatChangedListener.onCommonOverHeatStateOverHeatChangedUpdate ();
        }
    }

    void onCommonOverHeatStateOverHeatRegulationChangedUpdate (byte regulationType) {
        if(_ARCommandCommonOverHeatStateOverHeatRegulationChangedListener != null) {
            _ARCommandCommonOverHeatStateOverHeatRegulationChangedListener.onCommonOverHeatStateOverHeatRegulationChangedUpdate (regulationType);
        }
    }

    void onCommonWifiSettingsStateOutdoorSettingsChangedUpdate (byte outdoor) {
        if(_ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener != null) {
            _ARCommandCommonWifiSettingsStateOutdoorSettingsChangedListener.onCommonWifiSettingsStateOutdoorSettingsChangedUpdate (outdoor);
        }
    }

    void onCommonMavlinkStateMavlinkFilePlayingStateChangedUpdate (ARCOMMANDS_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_STATE_ENUM state, String filepath, ARCOMMANDS_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_TYPE_ENUM type) {
        if(_ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener != null) {
            _ARCommandCommonMavlinkStateMavlinkFilePlayingStateChangedListener.onCommonMavlinkStateMavlinkFilePlayingStateChangedUpdate (state, filepath, type);
        }
    }

    void onCommonMavlinkStateMavlinkPlayErrorStateChangedUpdate (ARCOMMANDS_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED_ERROR_ENUM error) {
        if(_ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener != null) {
            _ARCommandCommonMavlinkStateMavlinkPlayErrorStateChangedListener.onCommonMavlinkStateMavlinkPlayErrorStateChangedUpdate (error);
        }
    }

    void onCommonMavlinkStateMissionItemExecutedUpdate (int idx) {
        if(_ARCommandCommonMavlinkStateMissionItemExecutedListener != null) {
            _ARCommandCommonMavlinkStateMissionItemExecutedListener.onCommonMavlinkStateMissionItemExecutedUpdate (idx);
        }
    }

    void onCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedUpdate (byte state, byte isReadOnly) {
        if(_ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener != null) {
            _ARCommandCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedListener.onCommonFlightPlanSettingsStateReturnHomeOnDisconnectChangedUpdate (state, isReadOnly);
        }
    }

    void onCommonCalibrationStateMagnetoCalibrationStateChangedUpdate (byte xAxisCalibration, byte yAxisCalibration, byte zAxisCalibration, byte calibrationFailed) {
        if(_ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener != null) {
            _ARCommandCommonCalibrationStateMagnetoCalibrationStateChangedListener.onCommonCalibrationStateMagnetoCalibrationStateChangedUpdate (xAxisCalibration, yAxisCalibration, zAxisCalibration, calibrationFailed);
        }
    }

    void onCommonCalibrationStateMagnetoCalibrationRequiredStateUpdate (byte required) {
        if(_ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener != null) {
            _ARCommandCommonCalibrationStateMagnetoCalibrationRequiredStateListener.onCommonCalibrationStateMagnetoCalibrationRequiredStateUpdate (required);
        }
    }

    void onCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedUpdate (ARCOMMANDS_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONAXISTOCALIBRATECHANGED_AXIS_ENUM axis) {
        if(_ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener != null) {
            _ARCommandCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedListener.onCommonCalibrationStateMagnetoCalibrationAxisToCalibrateChangedUpdate (axis);
        }
    }

    void onCommonCalibrationStateMagnetoCalibrationStartedChangedUpdate (byte started) {
        if(_ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener != null) {
            _ARCommandCommonCalibrationStateMagnetoCalibrationStartedChangedListener.onCommonCalibrationStateMagnetoCalibrationStartedChangedUpdate (started);
        }
    }

    void onCommonCalibrationStatePitotCalibrationStateChangedUpdate (ARCOMMANDS_COMMON_CALIBRATIONSTATE_PITOTCALIBRATIONSTATECHANGED_STATE_ENUM state, byte lastError) {
        if(_ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener != null) {
            _ARCommandCommonCalibrationStatePitotCalibrationStateChangedListener.onCommonCalibrationStatePitotCalibrationStateChangedUpdate (state, lastError);
        }
    }

    void onCommonCameraSettingsStateCameraSettingsChangedUpdate (float fov, float panMax, float panMin, float tiltMax, float tiltMin) {
        if(_ARCommandCommonCameraSettingsStateCameraSettingsChangedListener != null) {
            _ARCommandCommonCameraSettingsStateCameraSettingsChangedListener.onCommonCameraSettingsStateCameraSettingsChangedUpdate (fov, panMax, panMin, tiltMax, tiltMin);
        }
    }

    void onCommonFlightPlanStateAvailabilityStateChangedUpdate (byte AvailabilityState) {
        if(_ARCommandCommonFlightPlanStateAvailabilityStateChangedListener != null) {
            _ARCommandCommonFlightPlanStateAvailabilityStateChangedListener.onCommonFlightPlanStateAvailabilityStateChangedUpdate (AvailabilityState);
        }
    }

    void onCommonFlightPlanStateComponentStateListChangedUpdate (ARCOMMANDS_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED_COMPONENT_ENUM component, byte State) {
        if(_ARCommandCommonFlightPlanStateComponentStateListChangedListener != null) {
            _ARCommandCommonFlightPlanStateComponentStateListChangedListener.onCommonFlightPlanStateComponentStateListChangedUpdate (component, State);
        }
    }

    void onCommonFlightPlanStateLockStateChangedUpdate (byte LockState) {
        if(_ARCommandCommonFlightPlanStateLockStateChangedListener != null) {
            _ARCommandCommonFlightPlanStateLockStateChangedListener.onCommonFlightPlanStateLockStateChangedUpdate (LockState);
        }
    }

    void onCommonFlightPlanEventStartingErrorEventUpdate () {
        if(_ARCommandCommonFlightPlanEventStartingErrorEventListener != null) {
            _ARCommandCommonFlightPlanEventStartingErrorEventListener.onCommonFlightPlanEventStartingErrorEventUpdate ();
        }
    }

    void onCommonFlightPlanEventSpeedBridleEventUpdate () {
        if(_ARCommandCommonFlightPlanEventSpeedBridleEventListener != null) {
            _ARCommandCommonFlightPlanEventSpeedBridleEventListener.onCommonFlightPlanEventSpeedBridleEventUpdate ();
        }
    }

    void onCommonARLibsVersionsStateControllerLibARCommandsVersionUpdate (String version) {
        if(_ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener != null) {
            _ARCommandCommonARLibsVersionsStateControllerLibARCommandsVersionListener.onCommonARLibsVersionsStateControllerLibARCommandsVersionUpdate (version);
        }
    }

    void onCommonARLibsVersionsStateSkyControllerLibARCommandsVersionUpdate (String version) {
        if(_ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener != null) {
            _ARCommandCommonARLibsVersionsStateSkyControllerLibARCommandsVersionListener.onCommonARLibsVersionsStateSkyControllerLibARCommandsVersionUpdate (version);
        }
    }

    void onCommonARLibsVersionsStateDeviceLibARCommandsVersionUpdate (String version) {
        if(_ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener != null) {
            _ARCommandCommonARLibsVersionsStateDeviceLibARCommandsVersionListener.onCommonARLibsVersionsStateDeviceLibARCommandsVersionUpdate (version);
        }
    }

    void onCommonAudioStateAudioStreamingRunningUpdate (byte running) {
        if(_ARCommandCommonAudioStateAudioStreamingRunningListener != null) {
            _ARCommandCommonAudioStateAudioStreamingRunningListener.onCommonAudioStateAudioStreamingRunningUpdate (running);
        }
    }

    void onCommonHeadlightsStateIntensityChangedUpdate (byte left, byte right) {
        if(_ARCommandCommonHeadlightsStateIntensityChangedListener != null) {
            _ARCommandCommonHeadlightsStateIntensityChangedListener.onCommonHeadlightsStateIntensityChangedUpdate (left, right);
        }
    }

    void onCommonAnimationsStateListUpdate (ARCOMMANDS_COMMON_ANIMATIONSSTATE_LIST_ANIM_ENUM anim, ARCOMMANDS_COMMON_ANIMATIONSSTATE_LIST_STATE_ENUM state, ARCOMMANDS_COMMON_ANIMATIONSSTATE_LIST_ERROR_ENUM error) {
        if(_ARCommandCommonAnimationsStateListListener != null) {
            _ARCommandCommonAnimationsStateListListener.onCommonAnimationsStateListUpdate (anim, state, error);
        }
    }

    void onCommonAccessoryStateSupportedAccessoriesListChangedUpdate (ARCOMMANDS_COMMON_ACCESSORYSTATE_SUPPORTEDACCESSORIESLISTCHANGED_ACCESSORY_ENUM accessory) {
        if(_ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener != null) {
            _ARCommandCommonAccessoryStateSupportedAccessoriesListChangedListener.onCommonAccessoryStateSupportedAccessoriesListChangedUpdate (accessory);
        }
    }

    void onCommonAccessoryStateAccessoryConfigChangedUpdate (ARCOMMANDS_COMMON_ACCESSORYSTATE_ACCESSORYCONFIGCHANGED_NEWACCESSORY_ENUM newAccessory, ARCOMMANDS_COMMON_ACCESSORYSTATE_ACCESSORYCONFIGCHANGED_ERROR_ENUM error) {
        if(_ARCommandCommonAccessoryStateAccessoryConfigChangedListener != null) {
            _ARCommandCommonAccessoryStateAccessoryConfigChangedListener.onCommonAccessoryStateAccessoryConfigChangedUpdate (newAccessory, error);
        }
    }

    void onCommonAccessoryStateAccessoryConfigModificationEnabledUpdate (byte enabled) {
        if(_ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener != null) {
            _ARCommandCommonAccessoryStateAccessoryConfigModificationEnabledListener.onCommonAccessoryStateAccessoryConfigModificationEnabledUpdate (enabled);
        }
    }

    void onCommonChargerStateMaxChargeRateChangedUpdate (ARCOMMANDS_COMMON_CHARGERSTATE_MAXCHARGERATECHANGED_RATE_ENUM rate) {
        if(_ARCommandCommonChargerStateMaxChargeRateChangedListener != null) {
            _ARCommandCommonChargerStateMaxChargeRateChangedListener.onCommonChargerStateMaxChargeRateChangedUpdate (rate);
        }
    }

    void onCommonChargerStateCurrentChargeStateChangedUpdate (ARCOMMANDS_COMMON_CHARGERSTATE_CURRENTCHARGESTATECHANGED_STATUS_ENUM status, ARCOMMANDS_COMMON_CHARGERSTATE_CURRENTCHARGESTATECHANGED_PHASE_ENUM phase) {
        if(_ARCommandCommonChargerStateCurrentChargeStateChangedListener != null) {
            _ARCommandCommonChargerStateCurrentChargeStateChangedListener.onCommonChargerStateCurrentChargeStateChangedUpdate (status, phase);
        }
    }

    void onCommonChargerStateLastChargeRateChangedUpdate (ARCOMMANDS_COMMON_CHARGERSTATE_LASTCHARGERATECHANGED_RATE_ENUM rate) {
        if(_ARCommandCommonChargerStateLastChargeRateChangedListener != null) {
            _ARCommandCommonChargerStateLastChargeRateChangedListener.onCommonChargerStateLastChargeRateChangedUpdate (rate);
        }
    }

    void onCommonChargerStateChargingInfoUpdate (ARCOMMANDS_COMMON_CHARGERSTATE_CHARGINGINFO_PHASE_ENUM phase, ARCOMMANDS_COMMON_CHARGERSTATE_CHARGINGINFO_RATE_ENUM rate, byte intensity, byte fullChargingTime) {
        if(_ARCommandCommonChargerStateChargingInfoListener != null) {
            _ARCommandCommonChargerStateChargingInfoListener.onCommonChargerStateChargingInfoUpdate (phase, rate, intensity, fullChargingTime);
        }
    }

    void onCommonRunStateRunIdChangedUpdate (String runId) {
        if(_ARCommandCommonRunStateRunIdChangedListener != null) {
            _ARCommandCommonRunStateRunIdChangedListener.onCommonRunStateRunIdChangedUpdate (runId);
        }
    }

    void onControllerInfoGpsUpdate (double latitude, double longitude, float altitude, float horizontal_accuracy, float vertical_accuracy, float north_speed, float east_speed, float down_speed, double timestamp) {
        if(_ARCommandControllerInfoGpsListener != null) {
            _ARCommandControllerInfoGpsListener.onControllerInfoGpsUpdate (latitude, longitude, altitude, horizontal_accuracy, vertical_accuracy, north_speed, east_speed, down_speed, timestamp);
        }
    }

    void onControllerInfoBarometerUpdate (float pressure, double timestamp) {
        if(_ARCommandControllerInfoBarometerListener != null) {
            _ARCommandControllerInfoBarometerListener.onControllerInfoBarometerUpdate (pressure, timestamp);
        }
    }

    void onDebugGetAllSettingsUpdate () {
        if(_ARCommandDebugGetAllSettingsListener != null) {
            _ARCommandDebugGetAllSettingsListener.onDebugGetAllSettingsUpdate ();
        }
    }

    void onDebugSetSettingUpdate (short id, String value) {
        if(_ARCommandDebugSetSettingListener != null) {
            _ARCommandDebugSetSettingListener.onDebugSetSettingUpdate (id, value);
        }
    }

    void onDebugSettingsInfoUpdate (byte list_flags, short id, String label, ARCOMMANDS_DEBUG_SETTING_TYPE_ENUM type, ARCOMMANDS_DEBUG_SETTING_MODE_ENUM mode, String range_min, String range_max, String range_step, String value) {
        if(_ARCommandDebugSettingsInfoListener != null) {
            _ARCommandDebugSettingsInfoListener.onDebugSettingsInfoUpdate (list_flags, id, label, type, mode, range_min, range_max, range_step, value);
        }
    }

    void onDebugSettingsListUpdate (short id, String value) {
        if(_ARCommandDebugSettingsListListener != null) {
            _ARCommandDebugSettingsListListener.onDebugSettingsListUpdate (id, value);
        }
    }

    void onDroneManagerDiscoverDronesUpdate () {
        if(_ARCommandDroneManagerDiscoverDronesListener != null) {
            _ARCommandDroneManagerDiscoverDronesListener.onDroneManagerDiscoverDronesUpdate ();
        }
    }

    void onDroneManagerConnectUpdate (String serial, String key) {
        if(_ARCommandDroneManagerConnectListener != null) {
            _ARCommandDroneManagerConnectListener.onDroneManagerConnectUpdate (serial, key);
        }
    }

    void onDroneManagerForgetUpdate (String serial) {
        if(_ARCommandDroneManagerForgetListener != null) {
            _ARCommandDroneManagerForgetListener.onDroneManagerForgetUpdate (serial);
        }
    }

    void onDroneManagerDroneListItemUpdate (String serial, short model, String name, byte connection_order, byte active, byte visible, ARCOMMANDS_DRONE_MANAGER_SECURITY_ENUM security, byte has_saved_key, byte rssi, byte list_flags) {
        if(_ARCommandDroneManagerDroneListItemListener != null) {
            _ARCommandDroneManagerDroneListItemListener.onDroneManagerDroneListItemUpdate (serial, model, name, connection_order, active, visible, security, has_saved_key, rssi, list_flags);
        }
    }

    void onDroneManagerConnectionStateUpdate (ARCOMMANDS_DRONE_MANAGER_CONNECTION_STATE_ENUM state, String serial, short model, String name) {
        if(_ARCommandDroneManagerConnectionStateListener != null) {
            _ARCommandDroneManagerConnectionStateListener.onDroneManagerConnectionStateUpdate (state, serial, model, name);
        }
    }

    void onDroneManagerAuthenticationFailedUpdate (String serial, short model, String name) {
        if(_ARCommandDroneManagerAuthenticationFailedListener != null) {
            _ARCommandDroneManagerAuthenticationFailedListener.onDroneManagerAuthenticationFailedUpdate (serial, model, name);
        }
    }

    void onDroneManagerConnectionRefusedUpdate (String serial, short model, String name) {
        if(_ARCommandDroneManagerConnectionRefusedListener != null) {
            _ARCommandDroneManagerConnectionRefusedListener.onDroneManagerConnectionRefusedUpdate (serial, model, name);
        }
    }

    void onDroneManagerKnownDroneItemUpdate (String serial, short model, String name, ARCOMMANDS_DRONE_MANAGER_SECURITY_ENUM security, byte has_saved_key, byte list_flags) {
        if(_ARCommandDroneManagerKnownDroneItemListener != null) {
            _ARCommandDroneManagerKnownDroneItemListener.onDroneManagerKnownDroneItemUpdate (serial, model, name, security, has_saved_key, list_flags);
        }
    }

    void onFollowMeStartUpdate (ARCOMMANDS_FOLLOW_ME_MODE_ENUM mode) {
        if(_ARCommandFollowMeStartListener != null) {
            _ARCommandFollowMeStartListener.onFollowMeStartUpdate (mode);
        }
    }

    void onFollowMeStopUpdate () {
        if(_ARCommandFollowMeStopListener != null) {
            _ARCommandFollowMeStopListener.onFollowMeStopUpdate ();
        }
    }

    void onFollowMeConfigureGeographicUpdate (byte use_default, float distance, float elevation, float azimuth) {
        if(_ARCommandFollowMeConfigureGeographicListener != null) {
            _ARCommandFollowMeConfigureGeographicListener.onFollowMeConfigureGeographicUpdate (use_default, distance, elevation, azimuth);
        }
    }

    void onFollowMeConfigureRelativeUpdate (byte use_default, float distance, float elevation, float azimuth) {
        if(_ARCommandFollowMeConfigureRelativeListener != null) {
            _ARCommandFollowMeConfigureRelativeListener.onFollowMeConfigureRelativeUpdate (use_default, distance, elevation, azimuth);
        }
    }

    void onFollowMeStopAnimationUpdate () {
        if(_ARCommandFollowMeStopAnimationListener != null) {
            _ARCommandFollowMeStopAnimationListener.onFollowMeStopAnimationUpdate ();
        }
    }

    void onFollowMeStartHelicoidAnimUpdate (byte use_default, float speed, float revolution_number, float vertical_distance) {
        if(_ARCommandFollowMeStartHelicoidAnimListener != null) {
            _ARCommandFollowMeStartHelicoidAnimListener.onFollowMeStartHelicoidAnimUpdate (use_default, speed, revolution_number, vertical_distance);
        }
    }

    void onFollowMeStartSwingAnimUpdate (byte use_default, float speed, float vertical_distance) {
        if(_ARCommandFollowMeStartSwingAnimListener != null) {
            _ARCommandFollowMeStartSwingAnimListener.onFollowMeStartSwingAnimUpdate (use_default, speed, vertical_distance);
        }
    }

    void onFollowMeStartBoomerangAnimUpdate (byte use_default, float speed, float distance) {
        if(_ARCommandFollowMeStartBoomerangAnimListener != null) {
            _ARCommandFollowMeStartBoomerangAnimListener.onFollowMeStartBoomerangAnimUpdate (use_default, speed, distance);
        }
    }

    void onFollowMeStartCandleAnimUpdate (byte use_default, float speed, float vertical_distance) {
        if(_ARCommandFollowMeStartCandleAnimListener != null) {
            _ARCommandFollowMeStartCandleAnimListener.onFollowMeStartCandleAnimUpdate (use_default, speed, vertical_distance);
        }
    }

    void onFollowMeStartDollySlideAnimUpdate (byte use_default, float speed, float angle, float horizontal_distance) {
        if(_ARCommandFollowMeStartDollySlideAnimListener != null) {
            _ARCommandFollowMeStartDollySlideAnimListener.onFollowMeStartDollySlideAnimUpdate (use_default, speed, angle, horizontal_distance);
        }
    }

    void onFollowMeTargetFramingPositionUpdate (byte horizontal, byte vertical) {
        if(_ARCommandFollowMeTargetFramingPositionListener != null) {
            _ARCommandFollowMeTargetFramingPositionListener.onFollowMeTargetFramingPositionUpdate (horizontal, vertical);
        }
    }

    void onFollowMeTargetImageDetectionUpdate (float target_azimuth, float target_elevation, float change_of_scale, byte confidence_index, byte is_new_selection, long timestamp) {
        if(_ARCommandFollowMeTargetImageDetectionListener != null) {
            _ARCommandFollowMeTargetImageDetectionListener.onFollowMeTargetImageDetectionUpdate (target_azimuth, target_elevation, change_of_scale, confidence_index, is_new_selection, timestamp);
        }
    }

    void onFollowMeStateUpdate (ARCOMMANDS_FOLLOW_ME_MODE_ENUM mode, ARCOMMANDS_FOLLOW_ME_BEHAVIOR_ENUM behavior, ARCOMMANDS_FOLLOW_ME_ANIMATION_ENUM animation, short animation_available) {
        if(_ARCommandFollowMeStateListener != null) {
            _ARCommandFollowMeStateListener.onFollowMeStateUpdate (mode, behavior, animation, animation_available);
        }
    }

    void onFollowMeModeInfoUpdate (ARCOMMANDS_FOLLOW_ME_MODE_ENUM mode, short missing_requirements, short improvements) {
        if(_ARCommandFollowMeModeInfoListener != null) {
            _ARCommandFollowMeModeInfoListener.onFollowMeModeInfoUpdate (mode, missing_requirements, improvements);
        }
    }

    void onFollowMeGeographicConfigUpdate (byte use_default, float distance, float elevation, float azimuth) {
        if(_ARCommandFollowMeGeographicConfigListener != null) {
            _ARCommandFollowMeGeographicConfigListener.onFollowMeGeographicConfigUpdate (use_default, distance, elevation, azimuth);
        }
    }

    void onFollowMeRelativeConfigUpdate (byte use_default, float distance, float elevation, float azimuth) {
        if(_ARCommandFollowMeRelativeConfigListener != null) {
            _ARCommandFollowMeRelativeConfigListener.onFollowMeRelativeConfigUpdate (use_default, distance, elevation, azimuth);
        }
    }

    void onFollowMeTargetTrajectoryUpdate (double latitude, double longitude, float altitude, float north_speed, float east_speed, float down_speed) {
        if(_ARCommandFollowMeTargetTrajectoryListener != null) {
            _ARCommandFollowMeTargetTrajectoryListener.onFollowMeTargetTrajectoryUpdate (latitude, longitude, altitude, north_speed, east_speed, down_speed);
        }
    }

    void onFollowMeHelicoidAnimConfigUpdate (byte use_default, float speed, float revolution_nb, float vertical_distance) {
        if(_ARCommandFollowMeHelicoidAnimConfigListener != null) {
            _ARCommandFollowMeHelicoidAnimConfigListener.onFollowMeHelicoidAnimConfigUpdate (use_default, speed, revolution_nb, vertical_distance);
        }
    }

    void onFollowMeSwingAnimConfigUpdate (byte use_default, float speed, float vertical_distance) {
        if(_ARCommandFollowMeSwingAnimConfigListener != null) {
            _ARCommandFollowMeSwingAnimConfigListener.onFollowMeSwingAnimConfigUpdate (use_default, speed, vertical_distance);
        }
    }

    void onFollowMeBoomerangAnimConfigUpdate (byte use_default, float speed, float distance) {
        if(_ARCommandFollowMeBoomerangAnimConfigListener != null) {
            _ARCommandFollowMeBoomerangAnimConfigListener.onFollowMeBoomerangAnimConfigUpdate (use_default, speed, distance);
        }
    }

    void onFollowMeCandleAnimConfigUpdate (byte use_default, float speed, float vertical_distance) {
        if(_ARCommandFollowMeCandleAnimConfigListener != null) {
            _ARCommandFollowMeCandleAnimConfigListener.onFollowMeCandleAnimConfigUpdate (use_default, speed, vertical_distance);
        }
    }

    void onFollowMeDollySlideAnimConfigUpdate (byte use_default, float speed, float angle, float horizontal_distance) {
        if(_ARCommandFollowMeDollySlideAnimConfigListener != null) {
            _ARCommandFollowMeDollySlideAnimConfigListener.onFollowMeDollySlideAnimConfigUpdate (use_default, speed, angle, horizontal_distance);
        }
    }

    void onFollowMeTargetFramingPositionChangedUpdate (byte horizontal, byte vertical) {
        if(_ARCommandFollowMeTargetFramingPositionChangedListener != null) {
            _ARCommandFollowMeTargetFramingPositionChangedListener.onFollowMeTargetFramingPositionChangedUpdate (horizontal, vertical);
        }
    }

    void onFollowMeTargetImageDetectionStateUpdate (ARCOMMANDS_FOLLOW_ME_IMAGE_DETECTION_STATUS_ENUM state) {
        if(_ARCommandFollowMeTargetImageDetectionStateListener != null) {
            _ARCommandFollowMeTargetImageDetectionStateListener.onFollowMeTargetImageDetectionStateUpdate (state);
        }
    }

    void onJumpingSumoPilotingPCMDUpdate (byte flag, byte speed, byte turn) {
        if(_ARCommandJumpingSumoPilotingPCMDListener != null) {
            _ARCommandJumpingSumoPilotingPCMDListener.onJumpingSumoPilotingPCMDUpdate (flag, speed, turn);
        }
    }

    void onJumpingSumoPilotingPostureUpdate (ARCOMMANDS_JUMPINGSUMO_PILOTING_POSTURE_TYPE_ENUM type) {
        if(_ARCommandJumpingSumoPilotingPostureListener != null) {
            _ARCommandJumpingSumoPilotingPostureListener.onJumpingSumoPilotingPostureUpdate (type);
        }
    }

    void onJumpingSumoPilotingAddCapOffsetUpdate (float offset) {
        if(_ARCommandJumpingSumoPilotingAddCapOffsetListener != null) {
            _ARCommandJumpingSumoPilotingAddCapOffsetListener.onJumpingSumoPilotingAddCapOffsetUpdate (offset);
        }
    }

    void onJumpingSumoAnimationsJumpStopUpdate () {
        if(_ARCommandJumpingSumoAnimationsJumpStopListener != null) {
            _ARCommandJumpingSumoAnimationsJumpStopListener.onJumpingSumoAnimationsJumpStopUpdate ();
        }
    }

    void onJumpingSumoAnimationsJumpCancelUpdate () {
        if(_ARCommandJumpingSumoAnimationsJumpCancelListener != null) {
            _ARCommandJumpingSumoAnimationsJumpCancelListener.onJumpingSumoAnimationsJumpCancelUpdate ();
        }
    }

    void onJumpingSumoAnimationsJumpLoadUpdate () {
        if(_ARCommandJumpingSumoAnimationsJumpLoadListener != null) {
            _ARCommandJumpingSumoAnimationsJumpLoadListener.onJumpingSumoAnimationsJumpLoadUpdate ();
        }
    }

    void onJumpingSumoAnimationsJumpUpdate (ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_JUMP_TYPE_ENUM type) {
        if(_ARCommandJumpingSumoAnimationsJumpListener != null) {
            _ARCommandJumpingSumoAnimationsJumpListener.onJumpingSumoAnimationsJumpUpdate (type);
        }
    }

    void onJumpingSumoAnimationsSimpleAnimationUpdate (ARCOMMANDS_JUMPINGSUMO_ANIMATIONS_SIMPLEANIMATION_ID_ENUM id) {
        if(_ARCommandJumpingSumoAnimationsSimpleAnimationListener != null) {
            _ARCommandJumpingSumoAnimationsSimpleAnimationListener.onJumpingSumoAnimationsSimpleAnimationUpdate (id);
        }
    }

    void onJumpingSumoMediaRecordPictureUpdate (byte mass_storage_id) {
        if(_ARCommandJumpingSumoMediaRecordPictureListener != null) {
            _ARCommandJumpingSumoMediaRecordPictureListener.onJumpingSumoMediaRecordPictureUpdate (mass_storage_id);
        }
    }

    void onJumpingSumoMediaRecordVideoUpdate (ARCOMMANDS_JUMPINGSUMO_MEDIARECORD_VIDEO_RECORD_ENUM record, byte mass_storage_id) {
        if(_ARCommandJumpingSumoMediaRecordVideoListener != null) {
            _ARCommandJumpingSumoMediaRecordVideoListener.onJumpingSumoMediaRecordVideoUpdate (record, mass_storage_id);
        }
    }

    void onJumpingSumoMediaRecordPictureV2Update () {
        if(_ARCommandJumpingSumoMediaRecordPictureV2Listener != null) {
            _ARCommandJumpingSumoMediaRecordPictureV2Listener.onJumpingSumoMediaRecordPictureV2Update ();
        }
    }

    void onJumpingSumoMediaRecordVideoV2Update (ARCOMMANDS_JUMPINGSUMO_MEDIARECORD_VIDEOV2_RECORD_ENUM record) {
        if(_ARCommandJumpingSumoMediaRecordVideoV2Listener != null) {
            _ARCommandJumpingSumoMediaRecordVideoV2Listener.onJumpingSumoMediaRecordVideoV2Update (record);
        }
    }

    void onJumpingSumoNetworkSettingsWifiSelectionUpdate (ARCOMMANDS_JUMPINGSUMO_NETWORKSETTINGS_WIFISELECTION_TYPE_ENUM type, ARCOMMANDS_JUMPINGSUMO_NETWORKSETTINGS_WIFISELECTION_BAND_ENUM band, byte channel) {
        if(_ARCommandJumpingSumoNetworkSettingsWifiSelectionListener != null) {
            _ARCommandJumpingSumoNetworkSettingsWifiSelectionListener.onJumpingSumoNetworkSettingsWifiSelectionUpdate (type, band, channel);
        }
    }

    void onJumpingSumoNetworkWifiScanUpdate (ARCOMMANDS_JUMPINGSUMO_NETWORK_WIFISCAN_BAND_ENUM band) {
        if(_ARCommandJumpingSumoNetworkWifiScanListener != null) {
            _ARCommandJumpingSumoNetworkWifiScanListener.onJumpingSumoNetworkWifiScanUpdate (band);
        }
    }

    void onJumpingSumoNetworkWifiAuthChannelUpdate () {
        if(_ARCommandJumpingSumoNetworkWifiAuthChannelListener != null) {
            _ARCommandJumpingSumoNetworkWifiAuthChannelListener.onJumpingSumoNetworkWifiAuthChannelUpdate ();
        }
    }

    void onJumpingSumoAudioSettingsMasterVolumeUpdate (byte volume) {
        if(_ARCommandJumpingSumoAudioSettingsMasterVolumeListener != null) {
            _ARCommandJumpingSumoAudioSettingsMasterVolumeListener.onJumpingSumoAudioSettingsMasterVolumeUpdate (volume);
        }
    }

    void onJumpingSumoAudioSettingsThemeUpdate (ARCOMMANDS_JUMPINGSUMO_AUDIOSETTINGS_THEME_THEME_ENUM theme) {
        if(_ARCommandJumpingSumoAudioSettingsThemeListener != null) {
            _ARCommandJumpingSumoAudioSettingsThemeListener.onJumpingSumoAudioSettingsThemeUpdate (theme);
        }
    }

    void onJumpingSumoRoadPlanAllScriptsMetadataUpdate () {
        if(_ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener != null) {
            _ARCommandJumpingSumoRoadPlanAllScriptsMetadataListener.onJumpingSumoRoadPlanAllScriptsMetadataUpdate ();
        }
    }

    void onJumpingSumoRoadPlanScriptUploadedUpdate (String uuid, String md5Hash) {
        if(_ARCommandJumpingSumoRoadPlanScriptUploadedListener != null) {
            _ARCommandJumpingSumoRoadPlanScriptUploadedListener.onJumpingSumoRoadPlanScriptUploadedUpdate (uuid, md5Hash);
        }
    }

    void onJumpingSumoRoadPlanScriptDeleteUpdate (String uuid) {
        if(_ARCommandJumpingSumoRoadPlanScriptDeleteListener != null) {
            _ARCommandJumpingSumoRoadPlanScriptDeleteListener.onJumpingSumoRoadPlanScriptDeleteUpdate (uuid);
        }
    }

    void onJumpingSumoRoadPlanPlayScriptUpdate (String uuid) {
        if(_ARCommandJumpingSumoRoadPlanPlayScriptListener != null) {
            _ARCommandJumpingSumoRoadPlanPlayScriptListener.onJumpingSumoRoadPlanPlayScriptUpdate (uuid);
        }
    }

    void onJumpingSumoSpeedSettingsOutdoorUpdate (byte outdoor) {
        if(_ARCommandJumpingSumoSpeedSettingsOutdoorListener != null) {
            _ARCommandJumpingSumoSpeedSettingsOutdoorListener.onJumpingSumoSpeedSettingsOutdoorUpdate (outdoor);
        }
    }

    void onJumpingSumoMediaStreamingVideoEnableUpdate (byte enable) {
        if(_ARCommandJumpingSumoMediaStreamingVideoEnableListener != null) {
            _ARCommandJumpingSumoMediaStreamingVideoEnableListener.onJumpingSumoMediaStreamingVideoEnableUpdate (enable);
        }
    }

    void onJumpingSumoVideoSettingsAutorecordUpdate (byte enabled) {
        if(_ARCommandJumpingSumoVideoSettingsAutorecordListener != null) {
            _ARCommandJumpingSumoVideoSettingsAutorecordListener.onJumpingSumoVideoSettingsAutorecordUpdate (enabled);
        }
    }

    void onJumpingSumoPilotingStatePostureChangedUpdate (ARCOMMANDS_JUMPINGSUMO_PILOTINGSTATE_POSTURECHANGED_STATE_ENUM state) {
        if(_ARCommandJumpingSumoPilotingStatePostureChangedListener != null) {
            _ARCommandJumpingSumoPilotingStatePostureChangedListener.onJumpingSumoPilotingStatePostureChangedUpdate (state);
        }
    }

    void onJumpingSumoPilotingStateAlertStateChangedUpdate (ARCOMMANDS_JUMPINGSUMO_PILOTINGSTATE_ALERTSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandJumpingSumoPilotingStateAlertStateChangedListener != null) {
            _ARCommandJumpingSumoPilotingStateAlertStateChangedListener.onJumpingSumoPilotingStateAlertStateChangedUpdate (state);
        }
    }

    void onJumpingSumoPilotingStateSpeedChangedUpdate (byte speed, short realSpeed) {
        if(_ARCommandJumpingSumoPilotingStateSpeedChangedListener != null) {
            _ARCommandJumpingSumoPilotingStateSpeedChangedListener.onJumpingSumoPilotingStateSpeedChangedUpdate (speed, realSpeed);
        }
    }

    void onJumpingSumoAnimationsStateJumpLoadChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ANIMATIONSSTATE_JUMPLOADCHANGED_STATE_ENUM state) {
        if(_ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener != null) {
            _ARCommandJumpingSumoAnimationsStateJumpLoadChangedListener.onJumpingSumoAnimationsStateJumpLoadChangedUpdate (state);
        }
    }

    void onJumpingSumoAnimationsStateJumpTypeChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ANIMATIONSSTATE_JUMPTYPECHANGED_STATE_ENUM state) {
        if(_ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener != null) {
            _ARCommandJumpingSumoAnimationsStateJumpTypeChangedListener.onJumpingSumoAnimationsStateJumpTypeChangedUpdate (state);
        }
    }

    void onJumpingSumoAnimationsStateJumpMotorProblemChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ANIMATIONSSTATE_JUMPMOTORPROBLEMCHANGED_ERROR_ENUM error) {
        if(_ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener != null) {
            _ARCommandJumpingSumoAnimationsStateJumpMotorProblemChangedListener.onJumpingSumoAnimationsStateJumpMotorProblemChangedUpdate (error);
        }
    }

    void onJumpingSumoSettingsStateProductGPSVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener != null) {
            _ARCommandJumpingSumoSettingsStateProductGPSVersionChangedListener.onJumpingSumoSettingsStateProductGPSVersionChangedUpdate (software, hardware);
        }
    }

    void onJumpingSumoMediaRecordStatePictureStateChangedUpdate (byte state, byte mass_storage_id) {
        if(_ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener != null) {
            _ARCommandJumpingSumoMediaRecordStatePictureStateChangedListener.onJumpingSumoMediaRecordStatePictureStateChangedUpdate (state, mass_storage_id);
        }
    }

    void onJumpingSumoMediaRecordStateVideoStateChangedUpdate (ARCOMMANDS_JUMPINGSUMO_MEDIARECORDSTATE_VIDEOSTATECHANGED_STATE_ENUM state, byte mass_storage_id) {
        if(_ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener != null) {
            _ARCommandJumpingSumoMediaRecordStateVideoStateChangedListener.onJumpingSumoMediaRecordStateVideoStateChangedUpdate (state, mass_storage_id);
        }
    }

    void onJumpingSumoMediaRecordStatePictureStateChangedV2Update (ARCOMMANDS_JUMPINGSUMO_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_JUMPINGSUMO_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener != null) {
            _ARCommandJumpingSumoMediaRecordStatePictureStateChangedV2Listener.onJumpingSumoMediaRecordStatePictureStateChangedV2Update (state, error);
        }
    }

    void onJumpingSumoMediaRecordStateVideoStateChangedV2Update (ARCOMMANDS_JUMPINGSUMO_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_JUMPINGSUMO_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener != null) {
            _ARCommandJumpingSumoMediaRecordStateVideoStateChangedV2Listener.onJumpingSumoMediaRecordStateVideoStateChangedV2Update (state, error);
        }
    }

    void onJumpingSumoMediaRecordEventPictureEventChangedUpdate (ARCOMMANDS_JUMPINGSUMO_MEDIARECORDEVENT_PICTUREEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_JUMPINGSUMO_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener != null) {
            _ARCommandJumpingSumoMediaRecordEventPictureEventChangedListener.onJumpingSumoMediaRecordEventPictureEventChangedUpdate (event, error);
        }
    }

    void onJumpingSumoMediaRecordEventVideoEventChangedUpdate (ARCOMMANDS_JUMPINGSUMO_MEDIARECORDEVENT_VIDEOEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_JUMPINGSUMO_MEDIARECORDEVENT_VIDEOEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener != null) {
            _ARCommandJumpingSumoMediaRecordEventVideoEventChangedListener.onJumpingSumoMediaRecordEventVideoEventChangedUpdate (event, error);
        }
    }

    void onJumpingSumoNetworkSettingsStateWifiSelectionChangedUpdate (ARCOMMANDS_JUMPINGSUMO_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_TYPE_ENUM type, ARCOMMANDS_JUMPINGSUMO_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener != null) {
            _ARCommandJumpingSumoNetworkSettingsStateWifiSelectionChangedListener.onJumpingSumoNetworkSettingsStateWifiSelectionChangedUpdate (type, band, channel);
        }
    }

    void onJumpingSumoNetworkStateWifiScanListChangedUpdate (String ssid, short rssi, ARCOMMANDS_JUMPINGSUMO_NETWORKSTATE_WIFISCANLISTCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandJumpingSumoNetworkStateWifiScanListChangedListener != null) {
            _ARCommandJumpingSumoNetworkStateWifiScanListChangedListener.onJumpingSumoNetworkStateWifiScanListChangedUpdate (ssid, rssi, band, channel);
        }
    }

    void onJumpingSumoNetworkStateAllWifiScanChangedUpdate () {
        if(_ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener != null) {
            _ARCommandJumpingSumoNetworkStateAllWifiScanChangedListener.onJumpingSumoNetworkStateAllWifiScanChangedUpdate ();
        }
    }

    void onJumpingSumoNetworkStateWifiAuthChannelListChangedUpdate (ARCOMMANDS_JUMPINGSUMO_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED_BAND_ENUM band, byte channel, byte in_or_out) {
        if(_ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener != null) {
            _ARCommandJumpingSumoNetworkStateWifiAuthChannelListChangedListener.onJumpingSumoNetworkStateWifiAuthChannelListChangedUpdate (band, channel, in_or_out);
        }
    }

    void onJumpingSumoNetworkStateAllWifiAuthChannelChangedUpdate () {
        if(_ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener != null) {
            _ARCommandJumpingSumoNetworkStateAllWifiAuthChannelChangedListener.onJumpingSumoNetworkStateAllWifiAuthChannelChangedUpdate ();
        }
    }

    void onJumpingSumoNetworkStateLinkQualityChangedUpdate (byte quality) {
        if(_ARCommandJumpingSumoNetworkStateLinkQualityChangedListener != null) {
            _ARCommandJumpingSumoNetworkStateLinkQualityChangedListener.onJumpingSumoNetworkStateLinkQualityChangedUpdate (quality);
        }
    }

    void onJumpingSumoAudioSettingsStateMasterVolumeChangedUpdate (byte volume) {
        if(_ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener != null) {
            _ARCommandJumpingSumoAudioSettingsStateMasterVolumeChangedListener.onJumpingSumoAudioSettingsStateMasterVolumeChangedUpdate (volume);
        }
    }

    void onJumpingSumoAudioSettingsStateThemeChangedUpdate (ARCOMMANDS_JUMPINGSUMO_AUDIOSETTINGSSTATE_THEMECHANGED_THEME_ENUM theme) {
        if(_ARCommandJumpingSumoAudioSettingsStateThemeChangedListener != null) {
            _ARCommandJumpingSumoAudioSettingsStateThemeChangedListener.onJumpingSumoAudioSettingsStateThemeChangedUpdate (theme);
        }
    }

    void onJumpingSumoRoadPlanStateScriptMetadataListChangedUpdate (String uuid, byte version, String product, String name, long lastModified) {
        if(_ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener != null) {
            _ARCommandJumpingSumoRoadPlanStateScriptMetadataListChangedListener.onJumpingSumoRoadPlanStateScriptMetadataListChangedUpdate (uuid, version, product, name, lastModified);
        }
    }

    void onJumpingSumoRoadPlanStateAllScriptsMetadataChangedUpdate () {
        if(_ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener != null) {
            _ARCommandJumpingSumoRoadPlanStateAllScriptsMetadataChangedListener.onJumpingSumoRoadPlanStateAllScriptsMetadataChangedUpdate ();
        }
    }

    void onJumpingSumoRoadPlanStateScriptUploadChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ROADPLANSTATE_SCRIPTUPLOADCHANGED_RESULTCODE_ENUM resultCode) {
        if(_ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener != null) {
            _ARCommandJumpingSumoRoadPlanStateScriptUploadChangedListener.onJumpingSumoRoadPlanStateScriptUploadChangedUpdate (resultCode);
        }
    }

    void onJumpingSumoRoadPlanStateScriptDeleteChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ROADPLANSTATE_SCRIPTDELETECHANGED_RESULTCODE_ENUM resultCode) {
        if(_ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener != null) {
            _ARCommandJumpingSumoRoadPlanStateScriptDeleteChangedListener.onJumpingSumoRoadPlanStateScriptDeleteChangedUpdate (resultCode);
        }
    }

    void onJumpingSumoRoadPlanStatePlayScriptChangedUpdate (ARCOMMANDS_JUMPINGSUMO_ROADPLANSTATE_PLAYSCRIPTCHANGED_RESULTCODE_ENUM resultCode) {
        if(_ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener != null) {
            _ARCommandJumpingSumoRoadPlanStatePlayScriptChangedListener.onJumpingSumoRoadPlanStatePlayScriptChangedUpdate (resultCode);
        }
    }

    void onJumpingSumoSpeedSettingsStateOutdoorChangedUpdate (byte outdoor) {
        if(_ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener != null) {
            _ARCommandJumpingSumoSpeedSettingsStateOutdoorChangedListener.onJumpingSumoSpeedSettingsStateOutdoorChangedUpdate (outdoor);
        }
    }

    void onJumpingSumoMediaStreamingStateVideoEnableChangedUpdate (ARCOMMANDS_JUMPINGSUMO_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED_ENABLED_ENUM enabled) {
        if(_ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener != null) {
            _ARCommandJumpingSumoMediaStreamingStateVideoEnableChangedListener.onJumpingSumoMediaStreamingStateVideoEnableChangedUpdate (enabled);
        }
    }

    void onJumpingSumoVideoSettingsStateAutorecordChangedUpdate (byte enabled) {
        if(_ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener != null) {
            _ARCommandJumpingSumoVideoSettingsStateAutorecordChangedListener.onJumpingSumoVideoSettingsStateAutorecordChangedUpdate (enabled);
        }
    }

    void onMapperGrabUpdate (int buttons, int axes) {
        if(_ARCommandMapperGrabListener != null) {
            _ARCommandMapperGrabListener.onMapperGrabUpdate (buttons, axes);
        }
    }

    void onMapperMapButtonActionUpdate (short product, ARCOMMANDS_MAPPER_BUTTON_ACTION_ENUM action, int buttons) {
        if(_ARCommandMapperMapButtonActionListener != null) {
            _ARCommandMapperMapButtonActionListener.onMapperMapButtonActionUpdate (product, action, buttons);
        }
    }

    void onMapperMapAxisActionUpdate (short product, ARCOMMANDS_MAPPER_AXIS_ACTION_ENUM action, int axis, int buttons) {
        if(_ARCommandMapperMapAxisActionListener != null) {
            _ARCommandMapperMapAxisActionListener.onMapperMapAxisActionUpdate (product, action, axis, buttons);
        }
    }

    void onMapperResetMappingUpdate (short product) {
        if(_ARCommandMapperResetMappingListener != null) {
            _ARCommandMapperResetMappingListener.onMapperResetMappingUpdate (product);
        }
    }

    void onMapperSetExpoUpdate (short product, int axis, ARCOMMANDS_MAPPER_EXPO_TYPE_ENUM expo) {
        if(_ARCommandMapperSetExpoListener != null) {
            _ARCommandMapperSetExpoListener.onMapperSetExpoUpdate (product, axis, expo);
        }
    }

    void onMapperSetInvertedUpdate (short product, int axis, byte inverted) {
        if(_ARCommandMapperSetInvertedListener != null) {
            _ARCommandMapperSetInvertedListener.onMapperSetInvertedUpdate (product, axis, inverted);
        }
    }

    void onMapperGrabStateUpdate (int buttons, int axes, int buttons_state) {
        if(_ARCommandMapperGrabStateListener != null) {
            _ARCommandMapperGrabStateListener.onMapperGrabStateUpdate (buttons, axes, buttons_state);
        }
    }

    void onMapperGrabButtonEventUpdate (int button, ARCOMMANDS_MAPPER_BUTTON_EVENT_ENUM event) {
        if(_ARCommandMapperGrabButtonEventListener != null) {
            _ARCommandMapperGrabButtonEventListener.onMapperGrabButtonEventUpdate (button, event);
        }
    }

    void onMapperGrabAxisEventUpdate (int axis, byte value) {
        if(_ARCommandMapperGrabAxisEventListener != null) {
            _ARCommandMapperGrabAxisEventListener.onMapperGrabAxisEventUpdate (axis, value);
        }
    }

    void onMapperButtonMappingItemUpdate (int uid, short product, ARCOMMANDS_MAPPER_BUTTON_ACTION_ENUM action, int buttons, byte list_flags) {
        if(_ARCommandMapperButtonMappingItemListener != null) {
            _ARCommandMapperButtonMappingItemListener.onMapperButtonMappingItemUpdate (uid, product, action, buttons, list_flags);
        }
    }

    void onMapperAxisMappingItemUpdate (int uid, short product, ARCOMMANDS_MAPPER_AXIS_ACTION_ENUM action, int axis, int buttons, byte list_flags) {
        if(_ARCommandMapperAxisMappingItemListener != null) {
            _ARCommandMapperAxisMappingItemListener.onMapperAxisMappingItemUpdate (uid, product, action, axis, buttons, list_flags);
        }
    }

    void onMapperApplicationAxisEventUpdate (ARCOMMANDS_MAPPER_AXIS_ACTION_ENUM action, byte value) {
        if(_ARCommandMapperApplicationAxisEventListener != null) {
            _ARCommandMapperApplicationAxisEventListener.onMapperApplicationAxisEventUpdate (action, value);
        }
    }

    void onMapperApplicationButtonEventUpdate (ARCOMMANDS_MAPPER_BUTTON_ACTION_ENUM action) {
        if(_ARCommandMapperApplicationButtonEventListener != null) {
            _ARCommandMapperApplicationButtonEventListener.onMapperApplicationButtonEventUpdate (action);
        }
    }

    void onMapperExpoMapItemUpdate (int uid, short product, int axis, ARCOMMANDS_MAPPER_EXPO_TYPE_ENUM expo, byte list_flags) {
        if(_ARCommandMapperExpoMapItemListener != null) {
            _ARCommandMapperExpoMapItemListener.onMapperExpoMapItemUpdate (uid, product, axis, expo, list_flags);
        }
    }

    void onMapperInvertedMapItemUpdate (int uid, short product, int axis, byte inverted, byte list_flags) {
        if(_ARCommandMapperInvertedMapItemListener != null) {
            _ARCommandMapperInvertedMapItemListener.onMapperInvertedMapItemUpdate (uid, product, axis, inverted, list_flags);
        }
    }

    void onMapperActiveProductUpdate (short product) {
        if(_ARCommandMapperActiveProductListener != null) {
            _ARCommandMapperActiveProductListener.onMapperActiveProductUpdate (product);
        }
    }

    void onMapperMiniMapButtonActionUpdate (byte modes, ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM action, int buttons) {
        if(_ARCommandMapperMiniMapButtonActionListener != null) {
            _ARCommandMapperMiniMapButtonActionListener.onMapperMiniMapButtonActionUpdate (modes, action, buttons);
        }
    }

    void onMapperMiniMapAxisActionUpdate (byte modes, ARCOMMANDS_MAPPER_MINI_AXIS_ACTION_ENUM action, byte axis, int buttons) {
        if(_ARCommandMapperMiniMapAxisActionListener != null) {
            _ARCommandMapperMiniMapAxisActionListener.onMapperMiniMapAxisActionUpdate (modes, action, axis, buttons);
        }
    }

    void onMapperMiniResetMappingUpdate (byte modes) {
        if(_ARCommandMapperMiniResetMappingListener != null) {
            _ARCommandMapperMiniResetMappingListener.onMapperMiniResetMappingUpdate (modes);
        }
    }

    void onMapperMiniButtonMappingItemUpdate (short uid, byte modes, ARCOMMANDS_MAPPER_MINI_BUTTON_ACTION_ENUM action, int buttons, byte list_flags) {
        if(_ARCommandMapperMiniButtonMappingItemListener != null) {
            _ARCommandMapperMiniButtonMappingItemListener.onMapperMiniButtonMappingItemUpdate (uid, modes, action, buttons, list_flags);
        }
    }

    void onMapperMiniAxisMappingItemUpdate (short uid, byte modes, ARCOMMANDS_MAPPER_MINI_AXIS_ACTION_ENUM action, byte axis, int buttons, byte list_flags) {
        if(_ARCommandMapperMiniAxisMappingItemListener != null) {
            _ARCommandMapperMiniAxisMappingItemListener.onMapperMiniAxisMappingItemUpdate (uid, modes, action, axis, buttons, list_flags);
        }
    }

    void onMiniDronePilotingFlatTrimUpdate () {
        if(_ARCommandMiniDronePilotingFlatTrimListener != null) {
            _ARCommandMiniDronePilotingFlatTrimListener.onMiniDronePilotingFlatTrimUpdate ();
        }
    }

    void onMiniDronePilotingTakeOffUpdate () {
        if(_ARCommandMiniDronePilotingTakeOffListener != null) {
            _ARCommandMiniDronePilotingTakeOffListener.onMiniDronePilotingTakeOffUpdate ();
        }
    }

    void onMiniDronePilotingPCMDUpdate (byte flag, byte roll, byte pitch, byte yaw, byte gaz, int timestamp) {
        if(_ARCommandMiniDronePilotingPCMDListener != null) {
            _ARCommandMiniDronePilotingPCMDListener.onMiniDronePilotingPCMDUpdate (flag, roll, pitch, yaw, gaz, timestamp);
        }
    }

    void onMiniDronePilotingLandingUpdate () {
        if(_ARCommandMiniDronePilotingLandingListener != null) {
            _ARCommandMiniDronePilotingLandingListener.onMiniDronePilotingLandingUpdate ();
        }
    }

    void onMiniDronePilotingEmergencyUpdate () {
        if(_ARCommandMiniDronePilotingEmergencyListener != null) {
            _ARCommandMiniDronePilotingEmergencyListener.onMiniDronePilotingEmergencyUpdate ();
        }
    }

    void onMiniDronePilotingAutoTakeOffModeUpdate (byte state) {
        if(_ARCommandMiniDronePilotingAutoTakeOffModeListener != null) {
            _ARCommandMiniDronePilotingAutoTakeOffModeListener.onMiniDronePilotingAutoTakeOffModeUpdate (state);
        }
    }

    void onMiniDronePilotingFlyingModeUpdate (ARCOMMANDS_MINIDRONE_PILOTING_FLYINGMODE_MODE_ENUM mode) {
        if(_ARCommandMiniDronePilotingFlyingModeListener != null) {
            _ARCommandMiniDronePilotingFlyingModeListener.onMiniDronePilotingFlyingModeUpdate (mode);
        }
    }

    void onMiniDronePilotingPlaneGearBoxUpdate (ARCOMMANDS_MINIDRONE_PILOTING_PLANEGEARBOX_STATE_ENUM state) {
        if(_ARCommandMiniDronePilotingPlaneGearBoxListener != null) {
            _ARCommandMiniDronePilotingPlaneGearBoxListener.onMiniDronePilotingPlaneGearBoxUpdate (state);
        }
    }

    void onMiniDronePilotingTogglePilotingModeUpdate () {
        if(_ARCommandMiniDronePilotingTogglePilotingModeListener != null) {
            _ARCommandMiniDronePilotingTogglePilotingModeListener.onMiniDronePilotingTogglePilotingModeUpdate ();
        }
    }

    void onMiniDroneAnimationsFlipUpdate (ARCOMMANDS_MINIDRONE_ANIMATIONS_FLIP_DIRECTION_ENUM direction) {
        if(_ARCommandMiniDroneAnimationsFlipListener != null) {
            _ARCommandMiniDroneAnimationsFlipListener.onMiniDroneAnimationsFlipUpdate (direction);
        }
    }

    void onMiniDroneAnimationsCapUpdate (short offset) {
        if(_ARCommandMiniDroneAnimationsCapListener != null) {
            _ARCommandMiniDroneAnimationsCapListener.onMiniDroneAnimationsCapUpdate (offset);
        }
    }

    void onMiniDroneMediaRecordPictureUpdate (byte mass_storage_id) {
        if(_ARCommandMiniDroneMediaRecordPictureListener != null) {
            _ARCommandMiniDroneMediaRecordPictureListener.onMiniDroneMediaRecordPictureUpdate (mass_storage_id);
        }
    }

    void onMiniDroneMediaRecordPictureV2Update () {
        if(_ARCommandMiniDroneMediaRecordPictureV2Listener != null) {
            _ARCommandMiniDroneMediaRecordPictureV2Listener.onMiniDroneMediaRecordPictureV2Update ();
        }
    }

    void onMiniDronePilotingSettingsMaxAltitudeUpdate (float current) {
        if(_ARCommandMiniDronePilotingSettingsMaxAltitudeListener != null) {
            _ARCommandMiniDronePilotingSettingsMaxAltitudeListener.onMiniDronePilotingSettingsMaxAltitudeUpdate (current);
        }
    }

    void onMiniDronePilotingSettingsMaxTiltUpdate (float current) {
        if(_ARCommandMiniDronePilotingSettingsMaxTiltListener != null) {
            _ARCommandMiniDronePilotingSettingsMaxTiltListener.onMiniDronePilotingSettingsMaxTiltUpdate (current);
        }
    }

    void onMiniDronePilotingSettingsBankedTurnUpdate (byte value) {
        if(_ARCommandMiniDronePilotingSettingsBankedTurnListener != null) {
            _ARCommandMiniDronePilotingSettingsBankedTurnListener.onMiniDronePilotingSettingsBankedTurnUpdate (value);
        }
    }

    void onMiniDronePilotingSettingsMaxThrottleUpdate (float max) {
        if(_ARCommandMiniDronePilotingSettingsMaxThrottleListener != null) {
            _ARCommandMiniDronePilotingSettingsMaxThrottleListener.onMiniDronePilotingSettingsMaxThrottleUpdate (max);
        }
    }

    void onMiniDronePilotingSettingsPreferredPilotingModeUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSETTINGS_PREFERREDPILOTINGMODE_MODE_ENUM mode) {
        if(_ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener != null) {
            _ARCommandMiniDronePilotingSettingsPreferredPilotingModeListener.onMiniDronePilotingSettingsPreferredPilotingModeUpdate (mode);
        }
    }

    void onMiniDroneSpeedSettingsMaxVerticalSpeedUpdate (float current) {
        if(_ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener != null) {
            _ARCommandMiniDroneSpeedSettingsMaxVerticalSpeedListener.onMiniDroneSpeedSettingsMaxVerticalSpeedUpdate (current);
        }
    }

    void onMiniDroneSpeedSettingsMaxRotationSpeedUpdate (float current) {
        if(_ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener != null) {
            _ARCommandMiniDroneSpeedSettingsMaxRotationSpeedListener.onMiniDroneSpeedSettingsMaxRotationSpeedUpdate (current);
        }
    }

    void onMiniDroneSpeedSettingsWheelsUpdate (byte present) {
        if(_ARCommandMiniDroneSpeedSettingsWheelsListener != null) {
            _ARCommandMiniDroneSpeedSettingsWheelsListener.onMiniDroneSpeedSettingsWheelsUpdate (present);
        }
    }

    void onMiniDroneSpeedSettingsMaxHorizontalSpeedUpdate (float current) {
        if(_ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener != null) {
            _ARCommandMiniDroneSpeedSettingsMaxHorizontalSpeedListener.onMiniDroneSpeedSettingsMaxHorizontalSpeedUpdate (current);
        }
    }

    void onMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedUpdate (float current) {
        if(_ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener != null) {
            _ARCommandMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedListener.onMiniDroneSpeedSettingsMaxPlaneModeRotationSpeedUpdate (current);
        }
    }

    void onMiniDroneSettingsCutOutModeUpdate (byte enable) {
        if(_ARCommandMiniDroneSettingsCutOutModeListener != null) {
            _ARCommandMiniDroneSettingsCutOutModeListener.onMiniDroneSettingsCutOutModeUpdate (enable);
        }
    }

    void onMiniDroneGPSControllerLatitudeForRunUpdate (double latitude) {
        if(_ARCommandMiniDroneGPSControllerLatitudeForRunListener != null) {
            _ARCommandMiniDroneGPSControllerLatitudeForRunListener.onMiniDroneGPSControllerLatitudeForRunUpdate (latitude);
        }
    }

    void onMiniDroneGPSControllerLongitudeForRunUpdate (double longitude) {
        if(_ARCommandMiniDroneGPSControllerLongitudeForRunListener != null) {
            _ARCommandMiniDroneGPSControllerLongitudeForRunListener.onMiniDroneGPSControllerLongitudeForRunUpdate (longitude);
        }
    }

    void onMiniDroneConfigurationControllerTypeUpdate (String type) {
        if(_ARCommandMiniDroneConfigurationControllerTypeListener != null) {
            _ARCommandMiniDroneConfigurationControllerTypeListener.onMiniDroneConfigurationControllerTypeUpdate (type);
        }
    }

    void onMiniDroneConfigurationControllerNameUpdate (String name) {
        if(_ARCommandMiniDroneConfigurationControllerNameListener != null) {
            _ARCommandMiniDroneConfigurationControllerNameListener.onMiniDroneConfigurationControllerNameUpdate (name);
        }
    }

    void onMiniDroneUsbAccessoryLightControlUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORY_LIGHTCONTROL_MODE_ENUM mode, byte intensity) {
        if(_ARCommandMiniDroneUsbAccessoryLightControlListener != null) {
            _ARCommandMiniDroneUsbAccessoryLightControlListener.onMiniDroneUsbAccessoryLightControlUpdate (id, mode, intensity);
        }
    }

    void onMiniDroneUsbAccessoryClawControlUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORY_CLAWCONTROL_ACTION_ENUM action) {
        if(_ARCommandMiniDroneUsbAccessoryClawControlListener != null) {
            _ARCommandMiniDroneUsbAccessoryClawControlListener.onMiniDroneUsbAccessoryClawControlUpdate (id, action);
        }
    }

    void onMiniDroneUsbAccessoryGunControlUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORY_GUNCONTROL_ACTION_ENUM action) {
        if(_ARCommandMiniDroneUsbAccessoryGunControlListener != null) {
            _ARCommandMiniDroneUsbAccessoryGunControlListener.onMiniDroneUsbAccessoryGunControlUpdate (id, action);
        }
    }

    void onMiniDroneRemoteControllerSetPairedRemoteUpdate (short msb_mac, short mid_mac, short lsb_mac) {
        if(_ARCommandMiniDroneRemoteControllerSetPairedRemoteListener != null) {
            _ARCommandMiniDroneRemoteControllerSetPairedRemoteListener.onMiniDroneRemoteControllerSetPairedRemoteUpdate (msb_mac, mid_mac, lsb_mac);
        }
    }

    void onMiniDroneVideoSettingsAutorecordUpdate (byte enable) {
        if(_ARCommandMiniDroneVideoSettingsAutorecordListener != null) {
            _ARCommandMiniDroneVideoSettingsAutorecordListener.onMiniDroneVideoSettingsAutorecordUpdate (enable);
        }
    }

    void onMiniDroneVideoSettingsElectricFrequencyUpdate (ARCOMMANDS_MINIDRONE_VIDEOSETTINGS_ELECTRICFREQUENCY_FREQUENCY_ENUM frequency) {
        if(_ARCommandMiniDroneVideoSettingsElectricFrequencyListener != null) {
            _ARCommandMiniDroneVideoSettingsElectricFrequencyListener.onMiniDroneVideoSettingsElectricFrequencyUpdate (frequency);
        }
    }

    void onMiniDroneVideoSettingsVideoResolutionUpdate (ARCOMMANDS_MINIDRONE_VIDEOSETTINGS_VIDEORESOLUTION_TYPE_ENUM type) {
        if(_ARCommandMiniDroneVideoSettingsVideoResolutionListener != null) {
            _ARCommandMiniDroneVideoSettingsVideoResolutionListener.onMiniDroneVideoSettingsVideoResolutionUpdate (type);
        }
    }

    void onMiniDroneMinicamPictureUpdate () {
        if(_ARCommandMiniDroneMinicamPictureListener != null) {
            _ARCommandMiniDroneMinicamPictureListener.onMiniDroneMinicamPictureUpdate ();
        }
    }

    void onMiniDroneMinicamVideoUpdate (ARCOMMANDS_MINIDRONE_MINICAM_VIDEO_RECORD_ENUM record) {
        if(_ARCommandMiniDroneMinicamVideoListener != null) {
            _ARCommandMiniDroneMinicamVideoListener.onMiniDroneMinicamVideoUpdate (record);
        }
    }

    void onMiniDroneMinicamMassStorageFormatUpdate () {
        if(_ARCommandMiniDroneMinicamMassStorageFormatListener != null) {
            _ARCommandMiniDroneMinicamMassStorageFormatListener.onMiniDroneMinicamMassStorageFormatUpdate ();
        }
    }

    void onMiniDronePilotingStateFlatTrimChangedUpdate () {
        if(_ARCommandMiniDronePilotingStateFlatTrimChangedListener != null) {
            _ARCommandMiniDronePilotingStateFlatTrimChangedListener.onMiniDronePilotingStateFlatTrimChangedUpdate ();
        }
    }

    void onMiniDronePilotingStateFlyingStateChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandMiniDronePilotingStateFlyingStateChangedListener != null) {
            _ARCommandMiniDronePilotingStateFlyingStateChangedListener.onMiniDronePilotingStateFlyingStateChangedUpdate (state);
        }
    }

    void onMiniDronePilotingStateAlertStateChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSTATE_ALERTSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandMiniDronePilotingStateAlertStateChangedListener != null) {
            _ARCommandMiniDronePilotingStateAlertStateChangedListener.onMiniDronePilotingStateAlertStateChangedUpdate (state);
        }
    }

    void onMiniDronePilotingStateAutoTakeOffModeChangedUpdate (byte state) {
        if(_ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener != null) {
            _ARCommandMiniDronePilotingStateAutoTakeOffModeChangedListener.onMiniDronePilotingStateAutoTakeOffModeChangedUpdate (state);
        }
    }

    void onMiniDronePilotingStateFlyingModeChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandMiniDronePilotingStateFlyingModeChangedListener != null) {
            _ARCommandMiniDronePilotingStateFlyingModeChangedListener.onMiniDronePilotingStateFlyingModeChangedUpdate (mode);
        }
    }

    void onMiniDronePilotingStatePlaneGearBoxChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSTATE_PLANEGEARBOXCHANGED_STATE_ENUM state) {
        if(_ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener != null) {
            _ARCommandMiniDronePilotingStatePlaneGearBoxChangedListener.onMiniDronePilotingStatePlaneGearBoxChangedUpdate (state);
        }
    }

    void onMiniDronePilotingStatePilotingModeChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSTATE_PILOTINGMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandMiniDronePilotingStatePilotingModeChangedListener != null) {
            _ARCommandMiniDronePilotingStatePilotingModeChangedListener.onMiniDronePilotingStatePilotingModeChangedUpdate (mode);
        }
    }

    void onMiniDroneMediaRecordStatePictureStateChangedUpdate (byte state, byte mass_storage_id) {
        if(_ARCommandMiniDroneMediaRecordStatePictureStateChangedListener != null) {
            _ARCommandMiniDroneMediaRecordStatePictureStateChangedListener.onMiniDroneMediaRecordStatePictureStateChangedUpdate (state, mass_storage_id);
        }
    }

    void onMiniDroneMediaRecordStatePictureStateChangedV2Update (ARCOMMANDS_MINIDRONE_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_MINIDRONE_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener != null) {
            _ARCommandMiniDroneMediaRecordStatePictureStateChangedV2Listener.onMiniDroneMediaRecordStatePictureStateChangedV2Update (state, error);
        }
    }

    void onMiniDroneMediaRecordEventPictureEventChangedUpdate (ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandMiniDroneMediaRecordEventPictureEventChangedListener != null) {
            _ARCommandMiniDroneMediaRecordEventPictureEventChangedListener.onMiniDroneMediaRecordEventPictureEventChangedUpdate (event, error);
        }
    }

    void onMiniDronePilotingSettingsStateMaxAltitudeChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener != null) {
            _ARCommandMiniDronePilotingSettingsStateMaxAltitudeChangedListener.onMiniDronePilotingSettingsStateMaxAltitudeChangedUpdate (current, min, max);
        }
    }

    void onMiniDronePilotingSettingsStateMaxTiltChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener != null) {
            _ARCommandMiniDronePilotingSettingsStateMaxTiltChangedListener.onMiniDronePilotingSettingsStateMaxTiltChangedUpdate (current, min, max);
        }
    }

    void onMiniDronePilotingSettingsStateBankedTurnChangedUpdate (byte state) {
        if(_ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener != null) {
            _ARCommandMiniDronePilotingSettingsStateBankedTurnChangedListener.onMiniDronePilotingSettingsStateBankedTurnChangedUpdate (state);
        }
    }

    void onMiniDronePilotingSettingsStateMaxThrottleChangedUpdate (float max) {
        if(_ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener != null) {
            _ARCommandMiniDronePilotingSettingsStateMaxThrottleChangedListener.onMiniDronePilotingSettingsStateMaxThrottleChangedUpdate (max);
        }
    }

    void onMiniDronePilotingSettingsStatePreferredPilotingModeChangedUpdate (ARCOMMANDS_MINIDRONE_PILOTINGSETTINGSSTATE_PREFERREDPILOTINGMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener != null) {
            _ARCommandMiniDronePilotingSettingsStatePreferredPilotingModeChangedListener.onMiniDronePilotingSettingsStatePreferredPilotingModeChangedUpdate (mode);
        }
    }

    void onMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener != null) {
            _ARCommandMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedListener.onMiniDroneSpeedSettingsStateMaxVerticalSpeedChangedUpdate (current, min, max);
        }
    }

    void onMiniDroneSpeedSettingsStateMaxRotationSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener != null) {
            _ARCommandMiniDroneSpeedSettingsStateMaxRotationSpeedChangedListener.onMiniDroneSpeedSettingsStateMaxRotationSpeedChangedUpdate (current, min, max);
        }
    }

    void onMiniDroneSpeedSettingsStateWheelsChangedUpdate (byte present) {
        if(_ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener != null) {
            _ARCommandMiniDroneSpeedSettingsStateWheelsChangedListener.onMiniDroneSpeedSettingsStateWheelsChangedUpdate (present);
        }
    }

    void onMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener != null) {
            _ARCommandMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedListener.onMiniDroneSpeedSettingsStateMaxHorizontalSpeedChangedUpdate (current, min, max);
        }
    }

    void onMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedUpdate (float current, float min, float max) {
        if(_ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener != null) {
            _ARCommandMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedListener.onMiniDroneSpeedSettingsStateMaxPlaneModeRotationSpeedChangedUpdate (current, min, max);
        }
    }

    void onMiniDroneSettingsStateProductMotorsVersionChangedUpdate (byte motor, String type, String software, String hardware) {
        if(_ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener != null) {
            _ARCommandMiniDroneSettingsStateProductMotorsVersionChangedListener.onMiniDroneSettingsStateProductMotorsVersionChangedUpdate (motor, type, software, hardware);
        }
    }

    void onMiniDroneSettingsStateProductInertialVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener != null) {
            _ARCommandMiniDroneSettingsStateProductInertialVersionChangedListener.onMiniDroneSettingsStateProductInertialVersionChangedUpdate (software, hardware);
        }
    }

    void onMiniDroneSettingsStateCutOutModeChangedUpdate (byte enable) {
        if(_ARCommandMiniDroneSettingsStateCutOutModeChangedListener != null) {
            _ARCommandMiniDroneSettingsStateCutOutModeChangedListener.onMiniDroneSettingsStateCutOutModeChangedUpdate (enable);
        }
    }

    void onMiniDroneFloodControlStateFloodControlChangedUpdate (short delay) {
        if(_ARCommandMiniDroneFloodControlStateFloodControlChangedListener != null) {
            _ARCommandMiniDroneFloodControlStateFloodControlChangedListener.onMiniDroneFloodControlStateFloodControlChangedUpdate (delay);
        }
    }

    void onMiniDroneUsbAccessoryStateLightStateUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORYSTATE_LIGHTSTATE_STATE_ENUM state, byte intensity, byte list_flags) {
        if(_ARCommandMiniDroneUsbAccessoryStateLightStateListener != null) {
            _ARCommandMiniDroneUsbAccessoryStateLightStateListener.onMiniDroneUsbAccessoryStateLightStateUpdate (id, state, intensity, list_flags);
        }
    }

    void onMiniDroneUsbAccessoryStateClawStateUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORYSTATE_CLAWSTATE_STATE_ENUM state, byte list_flags) {
        if(_ARCommandMiniDroneUsbAccessoryStateClawStateListener != null) {
            _ARCommandMiniDroneUsbAccessoryStateClawStateListener.onMiniDroneUsbAccessoryStateClawStateUpdate (id, state, list_flags);
        }
    }

    void onMiniDroneUsbAccessoryStateGunStateUpdate (byte id, ARCOMMANDS_MINIDRONE_USBACCESSORYSTATE_GUNSTATE_STATE_ENUM state, byte list_flags) {
        if(_ARCommandMiniDroneUsbAccessoryStateGunStateListener != null) {
            _ARCommandMiniDroneUsbAccessoryStateGunStateListener.onMiniDroneUsbAccessoryStateGunStateUpdate (id, state, list_flags);
        }
    }

    void onMiniDroneNavigationDataStateDronePositionUpdate (float posx, float posy, short posz, short psi, short ts) {
        if(_ARCommandMiniDroneNavigationDataStateDronePositionListener != null) {
            _ARCommandMiniDroneNavigationDataStateDronePositionListener.onMiniDroneNavigationDataStateDronePositionUpdate (posx, posy, posz, psi, ts);
        }
    }

    void onMiniDroneNavigationDataStateDroneSpeedUpdate (float speed_x, float speed_y, float speed_z, short ts) {
        if(_ARCommandMiniDroneNavigationDataStateDroneSpeedListener != null) {
            _ARCommandMiniDroneNavigationDataStateDroneSpeedListener.onMiniDroneNavigationDataStateDroneSpeedUpdate (speed_x, speed_y, speed_z, ts);
        }
    }

    void onMiniDroneNavigationDataStateDroneAltitudeUpdate (float altitude, short ts) {
        if(_ARCommandMiniDroneNavigationDataStateDroneAltitudeListener != null) {
            _ARCommandMiniDroneNavigationDataStateDroneAltitudeListener.onMiniDroneNavigationDataStateDroneAltitudeUpdate (altitude, ts);
        }
    }

    void onMiniDroneNavigationDataStateDroneQuaternionUpdate (float q_w, float q_x, float q_y, float q_z, short ts) {
        if(_ARCommandMiniDroneNavigationDataStateDroneQuaternionListener != null) {
            _ARCommandMiniDroneNavigationDataStateDroneQuaternionListener.onMiniDroneNavigationDataStateDroneQuaternionUpdate (q_w, q_x, q_y, q_z, ts);
        }
    }

    void onMiniDroneMinicamStatePowerModeChangedUpdate (ARCOMMANDS_MINIDRONE_MINICAMSTATE_POWERMODECHANGED_POWER_MODE_ENUM power_mode) {
        if(_ARCommandMiniDroneMinicamStatePowerModeChangedListener != null) {
            _ARCommandMiniDroneMinicamStatePowerModeChangedListener.onMiniDroneMinicamStatePowerModeChangedUpdate (power_mode);
        }
    }

    void onMiniDroneMinicamStateProductSerialChangedUpdate (String serial_number) {
        if(_ARCommandMiniDroneMinicamStateProductSerialChangedListener != null) {
            _ARCommandMiniDroneMinicamStateProductSerialChangedListener.onMiniDroneMinicamStateProductSerialChangedUpdate (serial_number);
        }
    }

    void onMiniDroneMinicamStateStateChangedUpdate (ARCOMMANDS_MINIDRONE_MINICAMSTATE_STATECHANGED_STATE_ENUM state) {
        if(_ARCommandMiniDroneMinicamStateStateChangedListener != null) {
            _ARCommandMiniDroneMinicamStateStateChangedListener.onMiniDroneMinicamStateStateChangedUpdate (state);
        }
    }

    void onMiniDroneMinicamStateVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandMiniDroneMinicamStateVersionChangedListener != null) {
            _ARCommandMiniDroneMinicamStateVersionChangedListener.onMiniDroneMinicamStateVersionChangedUpdate (software, hardware);
        }
    }

    void onMiniDroneMinicamStatePictureChangedUpdate (ARCOMMANDS_MINIDRONE_MINICAMSTATE_PICTURECHANGED_STATE_ENUM state, ARCOMMANDS_MINIDRONE_MINICAMSTATE_PICTURECHANGED_RESULT_ENUM result) {
        if(_ARCommandMiniDroneMinicamStatePictureChangedListener != null) {
            _ARCommandMiniDroneMinicamStatePictureChangedListener.onMiniDroneMinicamStatePictureChangedUpdate (state, result);
        }
    }

    void onMiniDroneMinicamStateVideoStateChangedUpdate (ARCOMMANDS_MINIDRONE_MINICAMSTATE_VIDEOSTATECHANGED_STATE_ENUM state, ARCOMMANDS_MINIDRONE_MINICAMSTATE_VIDEOSTATECHANGED_ERROR_ENUM error) {
        if(_ARCommandMiniDroneMinicamStateVideoStateChangedListener != null) {
            _ARCommandMiniDroneMinicamStateVideoStateChangedListener.onMiniDroneMinicamStateVideoStateChangedUpdate (state, error);
        }
    }

    void onMiniDroneMinicamStateMassStorageFormatChangedUpdate (byte state) {
        if(_ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener != null) {
            _ARCommandMiniDroneMinicamStateMassStorageFormatChangedListener.onMiniDroneMinicamStateMassStorageFormatChangedUpdate (state);
        }
    }

    void onMiniDroneVideoSettingsStateAutorecordChangedUpdate (byte enabled) {
        if(_ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener != null) {
            _ARCommandMiniDroneVideoSettingsStateAutorecordChangedListener.onMiniDroneVideoSettingsStateAutorecordChangedUpdate (enabled);
        }
    }

    void onMiniDroneVideoSettingsStateElectricFrequencyChangedUpdate (ARCOMMANDS_MINIDRONE_VIDEOSETTINGSSTATE_ELECTRICFREQUENCYCHANGED_FREQUENCY_ENUM frequency) {
        if(_ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener != null) {
            _ARCommandMiniDroneVideoSettingsStateElectricFrequencyChangedListener.onMiniDroneVideoSettingsStateElectricFrequencyChangedUpdate (frequency);
        }
    }

    void onMiniDroneVideoSettingsStateVideoResolutionChangedUpdate (ARCOMMANDS_MINIDRONE_VIDEOSETTINGSSTATE_VIDEORESOLUTIONCHANGED_TYPE_ENUM type) {
        if(_ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener != null) {
            _ARCommandMiniDroneVideoSettingsStateVideoResolutionChangedListener.onMiniDroneVideoSettingsStateVideoResolutionChangedUpdate (type);
        }
    }

    void onMiniDroneRemoteControllerStateConnectionChangedUpdate (byte state) {
        if(_ARCommandMiniDroneRemoteControllerStateConnectionChangedListener != null) {
            _ARCommandMiniDroneRemoteControllerStateConnectionChangedListener.onMiniDroneRemoteControllerStateConnectionChangedUpdate (state);
        }
    }

    void onPowerupPilotingPCMDUpdate (byte flag, byte throttle, byte roll) {
        if(_ARCommandPowerupPilotingPCMDListener != null) {
            _ARCommandPowerupPilotingPCMDListener.onPowerupPilotingPCMDUpdate (flag, throttle, roll);
        }
    }

    void onPowerupPilotingUserTakeOffUpdate (byte state) {
        if(_ARCommandPowerupPilotingUserTakeOffListener != null) {
            _ARCommandPowerupPilotingUserTakeOffListener.onPowerupPilotingUserTakeOffUpdate (state);
        }
    }

    void onPowerupPilotingMotorModeUpdate (ARCOMMANDS_POWERUP_PILOTING_MOTORMODE_MODE_ENUM mode) {
        if(_ARCommandPowerupPilotingMotorModeListener != null) {
            _ARCommandPowerupPilotingMotorModeListener.onPowerupPilotingMotorModeUpdate (mode);
        }
    }

    void onPowerupPilotingSettingsSetUpdate (ARCOMMANDS_POWERUP_PILOTINGSETTINGS_SET_SETTING_ENUM setting, float value) {
        if(_ARCommandPowerupPilotingSettingsSetListener != null) {
            _ARCommandPowerupPilotingSettingsSetListener.onPowerupPilotingSettingsSetUpdate (setting, value);
        }
    }

    void onPowerupMediaRecordPictureV2Update () {
        if(_ARCommandPowerupMediaRecordPictureV2Listener != null) {
            _ARCommandPowerupMediaRecordPictureV2Listener.onPowerupMediaRecordPictureV2Update ();
        }
    }

    void onPowerupMediaRecordVideoV2Update (ARCOMMANDS_POWERUP_MEDIARECORD_VIDEOV2_RECORD_ENUM record) {
        if(_ARCommandPowerupMediaRecordVideoV2Listener != null) {
            _ARCommandPowerupMediaRecordVideoV2Listener.onPowerupMediaRecordVideoV2Update (record);
        }
    }

    void onPowerupNetworkSettingsWifiSelectionUpdate (ARCOMMANDS_POWERUP_NETWORKSETTINGS_WIFISELECTION_TYPE_ENUM type, ARCOMMANDS_POWERUP_NETWORKSETTINGS_WIFISELECTION_BAND_ENUM band, byte channel) {
        if(_ARCommandPowerupNetworkSettingsWifiSelectionListener != null) {
            _ARCommandPowerupNetworkSettingsWifiSelectionListener.onPowerupNetworkSettingsWifiSelectionUpdate (type, band, channel);
        }
    }

    void onPowerupNetworkWifiScanUpdate (ARCOMMANDS_POWERUP_NETWORK_WIFISCAN_BAND_ENUM band) {
        if(_ARCommandPowerupNetworkWifiScanListener != null) {
            _ARCommandPowerupNetworkWifiScanListener.onPowerupNetworkWifiScanUpdate (band);
        }
    }

    void onPowerupNetworkWifiAuthChannelUpdate () {
        if(_ARCommandPowerupNetworkWifiAuthChannelListener != null) {
            _ARCommandPowerupNetworkWifiAuthChannelListener.onPowerupNetworkWifiAuthChannelUpdate ();
        }
    }

    void onPowerupMediaStreamingVideoEnableUpdate (byte enable) {
        if(_ARCommandPowerupMediaStreamingVideoEnableListener != null) {
            _ARCommandPowerupMediaStreamingVideoEnableListener.onPowerupMediaStreamingVideoEnableUpdate (enable);
        }
    }

    void onPowerupVideoSettingsAutorecordUpdate (byte enable) {
        if(_ARCommandPowerupVideoSettingsAutorecordListener != null) {
            _ARCommandPowerupVideoSettingsAutorecordListener.onPowerupVideoSettingsAutorecordUpdate (enable);
        }
    }

    void onPowerupVideoSettingsVideoModeUpdate (ARCOMMANDS_POWERUP_VIDEOSETTINGS_VIDEOMODE_MODE_ENUM mode) {
        if(_ARCommandPowerupVideoSettingsVideoModeListener != null) {
            _ARCommandPowerupVideoSettingsVideoModeListener.onPowerupVideoSettingsVideoModeUpdate (mode);
        }
    }

    void onPowerupSoundsBuzzUpdate (byte enable) {
        if(_ARCommandPowerupSoundsBuzzListener != null) {
            _ARCommandPowerupSoundsBuzzListener.onPowerupSoundsBuzzUpdate (enable);
        }
    }

    void onPowerupPilotingStateAlertStateChangedUpdate (ARCOMMANDS_POWERUP_PILOTINGSTATE_ALERTSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandPowerupPilotingStateAlertStateChangedListener != null) {
            _ARCommandPowerupPilotingStateAlertStateChangedListener.onPowerupPilotingStateAlertStateChangedUpdate (state);
        }
    }

    void onPowerupPilotingStateFlyingStateChangedUpdate (ARCOMMANDS_POWERUP_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
        if(_ARCommandPowerupPilotingStateFlyingStateChangedListener != null) {
            _ARCommandPowerupPilotingStateFlyingStateChangedListener.onPowerupPilotingStateFlyingStateChangedUpdate (state);
        }
    }

    void onPowerupPilotingStateMotorModeChangedUpdate (ARCOMMANDS_POWERUP_PILOTINGSTATE_MOTORMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandPowerupPilotingStateMotorModeChangedListener != null) {
            _ARCommandPowerupPilotingStateMotorModeChangedListener.onPowerupPilotingStateMotorModeChangedUpdate (mode);
        }
    }

    void onPowerupPilotingStateAttitudeChangedUpdate (float roll, float pitch, float yaw) {
        if(_ARCommandPowerupPilotingStateAttitudeChangedListener != null) {
            _ARCommandPowerupPilotingStateAttitudeChangedListener.onPowerupPilotingStateAttitudeChangedUpdate (roll, pitch, yaw);
        }
    }

    void onPowerupPilotingStateAltitudeChangedUpdate (float altitude) {
        if(_ARCommandPowerupPilotingStateAltitudeChangedListener != null) {
            _ARCommandPowerupPilotingStateAltitudeChangedListener.onPowerupPilotingStateAltitudeChangedUpdate (altitude);
        }
    }

    void onPowerupPilotingSettingsStateSettingChangedUpdate (ARCOMMANDS_POWERUP_PILOTINGSETTINGSSTATE_SETTINGCHANGED_SETTING_ENUM setting, float current, float min, float max, byte list_flags) {
        if(_ARCommandPowerupPilotingSettingsStateSettingChangedListener != null) {
            _ARCommandPowerupPilotingSettingsStateSettingChangedListener.onPowerupPilotingSettingsStateSettingChangedUpdate (setting, current, min, max, list_flags);
        }
    }

    void onPowerupMediaRecordStatePictureStateChangedV2Update (ARCOMMANDS_POWERUP_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_POWERUP_MEDIARECORDSTATE_PICTURESTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener != null) {
            _ARCommandPowerupMediaRecordStatePictureStateChangedV2Listener.onPowerupMediaRecordStatePictureStateChangedV2Update (state, error);
        }
    }

    void onPowerupMediaRecordStateVideoStateChangedV2Update (ARCOMMANDS_POWERUP_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_STATE_ENUM state, ARCOMMANDS_POWERUP_MEDIARECORDSTATE_VIDEOSTATECHANGEDV2_ERROR_ENUM error) {
        if(_ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener != null) {
            _ARCommandPowerupMediaRecordStateVideoStateChangedV2Listener.onPowerupMediaRecordStateVideoStateChangedV2Update (state, error);
        }
    }

    void onPowerupMediaRecordEventPictureEventChangedUpdate (ARCOMMANDS_POWERUP_MEDIARECORDEVENT_PICTUREEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_POWERUP_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandPowerupMediaRecordEventPictureEventChangedListener != null) {
            _ARCommandPowerupMediaRecordEventPictureEventChangedListener.onPowerupMediaRecordEventPictureEventChangedUpdate (event, error);
        }
    }

    void onPowerupMediaRecordEventVideoEventChangedUpdate (ARCOMMANDS_POWERUP_MEDIARECORDEVENT_VIDEOEVENTCHANGED_EVENT_ENUM event, ARCOMMANDS_POWERUP_MEDIARECORDEVENT_VIDEOEVENTCHANGED_ERROR_ENUM error) {
        if(_ARCommandPowerupMediaRecordEventVideoEventChangedListener != null) {
            _ARCommandPowerupMediaRecordEventVideoEventChangedListener.onPowerupMediaRecordEventVideoEventChangedUpdate (event, error);
        }
    }

    void onPowerupNetworkSettingsStateWifiSelectionChangedUpdate (ARCOMMANDS_POWERUP_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_TYPE_ENUM type, ARCOMMANDS_POWERUP_NETWORKSETTINGSSTATE_WIFISELECTIONCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener != null) {
            _ARCommandPowerupNetworkSettingsStateWifiSelectionChangedListener.onPowerupNetworkSettingsStateWifiSelectionChangedUpdate (type, band, channel);
        }
    }

    void onPowerupNetworkStateWifiScanListChangedUpdate (String ssid, short rssi, ARCOMMANDS_POWERUP_NETWORKSTATE_WIFISCANLISTCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandPowerupNetworkStateWifiScanListChangedListener != null) {
            _ARCommandPowerupNetworkStateWifiScanListChangedListener.onPowerupNetworkStateWifiScanListChangedUpdate (ssid, rssi, band, channel);
        }
    }

    void onPowerupNetworkStateAllWifiScanChangedUpdate () {
        if(_ARCommandPowerupNetworkStateAllWifiScanChangedListener != null) {
            _ARCommandPowerupNetworkStateAllWifiScanChangedListener.onPowerupNetworkStateAllWifiScanChangedUpdate ();
        }
    }

    void onPowerupNetworkStateWifiAuthChannelListChangedUpdate (ARCOMMANDS_POWERUP_NETWORKSTATE_WIFIAUTHCHANNELLISTCHANGED_BAND_ENUM band, byte channel, byte in_or_out) {
        if(_ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener != null) {
            _ARCommandPowerupNetworkStateWifiAuthChannelListChangedListener.onPowerupNetworkStateWifiAuthChannelListChangedUpdate (band, channel, in_or_out);
        }
    }

    void onPowerupNetworkStateAllWifiAuthChannelChangedUpdate () {
        if(_ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener != null) {
            _ARCommandPowerupNetworkStateAllWifiAuthChannelChangedListener.onPowerupNetworkStateAllWifiAuthChannelChangedUpdate ();
        }
    }

    void onPowerupNetworkStateLinkQualityChangedUpdate (byte quality) {
        if(_ARCommandPowerupNetworkStateLinkQualityChangedListener != null) {
            _ARCommandPowerupNetworkStateLinkQualityChangedListener.onPowerupNetworkStateLinkQualityChangedUpdate (quality);
        }
    }

    void onPowerupMediaStreamingStateVideoEnableChangedUpdate (ARCOMMANDS_POWERUP_MEDIASTREAMINGSTATE_VIDEOENABLECHANGED_ENABLED_ENUM enabled) {
        if(_ARCommandPowerupMediaStreamingStateVideoEnableChangedListener != null) {
            _ARCommandPowerupMediaStreamingStateVideoEnableChangedListener.onPowerupMediaStreamingStateVideoEnableChangedUpdate (enabled);
        }
    }

    void onPowerupVideoSettingsStateAutorecordChangedUpdate (byte enabled) {
        if(_ARCommandPowerupVideoSettingsStateAutorecordChangedListener != null) {
            _ARCommandPowerupVideoSettingsStateAutorecordChangedListener.onPowerupVideoSettingsStateAutorecordChangedUpdate (enabled);
        }
    }

    void onPowerupVideoSettingsStateVideoModeChangedUpdate (ARCOMMANDS_POWERUP_VIDEOSETTINGSSTATE_VIDEOMODECHANGED_MODE_ENUM mode) {
        if(_ARCommandPowerupVideoSettingsStateVideoModeChangedListener != null) {
            _ARCommandPowerupVideoSettingsStateVideoModeChangedListener.onPowerupVideoSettingsStateVideoModeChangedUpdate (mode);
        }
    }

    void onPowerupSoundsStateBuzzChangedUpdate (byte enabled) {
        if(_ARCommandPowerupSoundsStateBuzzChangedListener != null) {
            _ARCommandPowerupSoundsStateBuzzChangedListener.onPowerupSoundsStateBuzzChangedUpdate (enabled);
        }
    }

    void onRcMonitorChannelsUpdate (byte enable) {
        if(_ARCommandRcMonitorChannelsListener != null) {
            _ARCommandRcMonitorChannelsListener.onRcMonitorChannelsUpdate (enable);
        }
    }

    void onRcStartCalibrationUpdate (ARCOMMANDS_RC_CALIBRATION_TYPE_ENUM calibration_type, ARCOMMANDS_RC_CHANNEL_ACTION_ENUM channel_action, ARCOMMANDS_RC_CHANNEL_TYPE_ENUM channel_type) {
        if(_ARCommandRcStartCalibrationListener != null) {
            _ARCommandRcStartCalibrationListener.onRcStartCalibrationUpdate (calibration_type, channel_action, channel_type);
        }
    }

    void onRcInvertChannelUpdate (ARCOMMANDS_RC_CHANNEL_ACTION_ENUM action, byte flag) {
        if(_ARCommandRcInvertChannelListener != null) {
            _ARCommandRcInvertChannelListener.onRcInvertChannelUpdate (action, flag);
        }
    }

    void onRcAbortCalibrationUpdate () {
        if(_ARCommandRcAbortCalibrationListener != null) {
            _ARCommandRcAbortCalibrationListener.onRcAbortCalibrationUpdate ();
        }
    }

    void onRcResetCalibrationUpdate () {
        if(_ARCommandRcResetCalibrationListener != null) {
            _ARCommandRcResetCalibrationListener.onRcResetCalibrationUpdate ();
        }
    }

    void onRcEnableReceiverUpdate (byte enable) {
        if(_ARCommandRcEnableReceiverListener != null) {
            _ARCommandRcEnableReceiverListener.onRcEnableReceiverUpdate (enable);
        }
    }

    void onRcReceiverStateUpdate (ARCOMMANDS_RC_RECEIVER_STATE_ENUM state, String protocol, byte enabled) {
        if(_ARCommandRcReceiverStateListener != null) {
            _ARCommandRcReceiverStateListener.onRcReceiverStateUpdate (state, protocol, enabled);
        }
    }

    void onRcChannelsMonitorStateUpdate (byte state) {
        if(_ARCommandRcChannelsMonitorStateListener != null) {
            _ARCommandRcChannelsMonitorStateListener.onRcChannelsMonitorStateUpdate (state);
        }
    }

    void onRcChannelValueUpdate (byte id, ARCOMMANDS_RC_CHANNEL_ACTION_ENUM action, short value, byte list_flags) {
        if(_ARCommandRcChannelValueListener != null) {
            _ARCommandRcChannelValueListener.onRcChannelValueUpdate (id, action, value, list_flags);
        }
    }

    void onRcCalibrationStateUpdate (ARCOMMANDS_RC_CALIBRATION_TYPE_ENUM calibration_type, ARCOMMANDS_RC_CHANNEL_ACTION_ENUM channel_action, int required, int calibrated, byte neutral_calibrated) {
        if(_ARCommandRcCalibrationStateListener != null) {
            _ARCommandRcCalibrationStateListener.onRcCalibrationStateUpdate (calibration_type, channel_action, required, calibrated, neutral_calibrated);
        }
    }

    void onRcChannelActionItemUpdate (ARCOMMANDS_RC_CHANNEL_ACTION_ENUM action, int supported_type, ARCOMMANDS_RC_CHANNEL_TYPE_ENUM calibrated_type, byte inverted) {
        if(_ARCommandRcChannelActionItemListener != null) {
            _ARCommandRcChannelActionItemListener.onRcChannelActionItemUpdate (action, supported_type, calibrated_type, inverted);
        }
    }

    void onSequoiaCamRadiometricCalibStartUpdate (byte id) {
        if(_ARCommandSequoiaCamRadiometricCalibStartListener != null) {
            _ARCommandSequoiaCamRadiometricCalibStartListener.onSequoiaCamRadiometricCalibStartUpdate (id);
        }
    }

    void onSequoiaCamRadiometricCalibStatusUpdate (byte cam_id, ARCOMMANDS_SEQUOIA_CAM_RADIOMETRIC_CALIB_STATUS_VALUE_ENUM value, byte list_flags) {
        if(_ARCommandSequoiaCamRadiometricCalibStatusListener != null) {
            _ARCommandSequoiaCamRadiometricCalibStatusListener.onSequoiaCamRadiometricCalibStatusUpdate (cam_id, value, list_flags);
        }
    }

    void onSequoiaCamRadiometricCalibResultUpdate (byte cam_id, ARCOMMANDS_SEQUOIA_CAM_RADIOMETRIC_CALIB_RESULT_VALUE_ENUM value, byte list_flags) {
        if(_ARCommandSequoiaCamRadiometricCalibResultListener != null) {
            _ARCommandSequoiaCamRadiometricCalibResultListener.onSequoiaCamRadiometricCalibResultUpdate (cam_id, value, list_flags);
        }
    }

    void onSkyControllerWifiRequestWifiListUpdate () {
        if(_ARCommandSkyControllerWifiRequestWifiListListener != null) {
            _ARCommandSkyControllerWifiRequestWifiListListener.onSkyControllerWifiRequestWifiListUpdate ();
        }
    }

    void onSkyControllerWifiRequestCurrentWifiUpdate () {
        if(_ARCommandSkyControllerWifiRequestCurrentWifiListener != null) {
            _ARCommandSkyControllerWifiRequestCurrentWifiListener.onSkyControllerWifiRequestCurrentWifiUpdate ();
        }
    }

    void onSkyControllerWifiConnectToWifiUpdate (String bssid, String ssid, String passphrase) {
        if(_ARCommandSkyControllerWifiConnectToWifiListener != null) {
            _ARCommandSkyControllerWifiConnectToWifiListener.onSkyControllerWifiConnectToWifiUpdate (bssid, ssid, passphrase);
        }
    }

    void onSkyControllerWifiForgetWifiUpdate (String ssid) {
        if(_ARCommandSkyControllerWifiForgetWifiListener != null) {
            _ARCommandSkyControllerWifiForgetWifiListener.onSkyControllerWifiForgetWifiUpdate (ssid);
        }
    }

    void onSkyControllerWifiWifiAuthChannelUpdate () {
        if(_ARCommandSkyControllerWifiWifiAuthChannelListener != null) {
            _ARCommandSkyControllerWifiWifiAuthChannelListener.onSkyControllerWifiWifiAuthChannelUpdate ();
        }
    }

    void onSkyControllerDeviceRequestDeviceListUpdate () {
        if(_ARCommandSkyControllerDeviceRequestDeviceListListener != null) {
            _ARCommandSkyControllerDeviceRequestDeviceListListener.onSkyControllerDeviceRequestDeviceListUpdate ();
        }
    }

    void onSkyControllerDeviceRequestCurrentDeviceUpdate () {
        if(_ARCommandSkyControllerDeviceRequestCurrentDeviceListener != null) {
            _ARCommandSkyControllerDeviceRequestCurrentDeviceListener.onSkyControllerDeviceRequestCurrentDeviceUpdate ();
        }
    }

    void onSkyControllerDeviceConnectToDeviceUpdate (String deviceName) {
        if(_ARCommandSkyControllerDeviceConnectToDeviceListener != null) {
            _ARCommandSkyControllerDeviceConnectToDeviceListener.onSkyControllerDeviceConnectToDeviceUpdate (deviceName);
        }
    }

    void onSkyControllerSettingsAllSettingsUpdate () {
        if(_ARCommandSkyControllerSettingsAllSettingsListener != null) {
            _ARCommandSkyControllerSettingsAllSettingsListener.onSkyControllerSettingsAllSettingsUpdate ();
        }
    }

    void onSkyControllerSettingsResetUpdate () {
        if(_ARCommandSkyControllerSettingsResetListener != null) {
            _ARCommandSkyControllerSettingsResetListener.onSkyControllerSettingsResetUpdate ();
        }
    }

    void onSkyControllerCommonAllStatesUpdate () {
        if(_ARCommandSkyControllerCommonAllStatesListener != null) {
            _ARCommandSkyControllerCommonAllStatesListener.onSkyControllerCommonAllStatesUpdate ();
        }
    }

    void onSkyControllerAccessPointSettingsAccessPointSSIDUpdate (String ssid) {
        if(_ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener != null) {
            _ARCommandSkyControllerAccessPointSettingsAccessPointSSIDListener.onSkyControllerAccessPointSettingsAccessPointSSIDUpdate (ssid);
        }
    }

    void onSkyControllerAccessPointSettingsAccessPointChannelUpdate (byte channel) {
        if(_ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener != null) {
            _ARCommandSkyControllerAccessPointSettingsAccessPointChannelListener.onSkyControllerAccessPointSettingsAccessPointChannelUpdate (channel);
        }
    }

    void onSkyControllerAccessPointSettingsWifiSelectionUpdate (ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGS_WIFISELECTION_TYPE_ENUM type, ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGS_WIFISELECTION_BAND_ENUM band, byte channel) {
        if(_ARCommandSkyControllerAccessPointSettingsWifiSelectionListener != null) {
            _ARCommandSkyControllerAccessPointSettingsWifiSelectionListener.onSkyControllerAccessPointSettingsWifiSelectionUpdate (type, band, channel);
        }
    }

    void onSkyControllerAccessPointSettingsWifiSecurityUpdate (ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGS_WIFISECURITY_SECURITY_TYPE_ENUM security_type, String key) {
        if(_ARCommandSkyControllerAccessPointSettingsWifiSecurityListener != null) {
            _ARCommandSkyControllerAccessPointSettingsWifiSecurityListener.onSkyControllerAccessPointSettingsWifiSecurityUpdate (security_type, key);
        }
    }

    void onSkyControllerCameraResetOrientationUpdate () {
        if(_ARCommandSkyControllerCameraResetOrientationListener != null) {
            _ARCommandSkyControllerCameraResetOrientationListener.onSkyControllerCameraResetOrientationUpdate ();
        }
    }

    void onSkyControllerGamepadInfosGetGamepadControlsUpdate () {
        if(_ARCommandSkyControllerGamepadInfosGetGamepadControlsListener != null) {
            _ARCommandSkyControllerGamepadInfosGetGamepadControlsListener.onSkyControllerGamepadInfosGetGamepadControlsUpdate ();
        }
    }

    void onSkyControllerButtonMappingsGetCurrentButtonMappingsUpdate () {
        if(_ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener != null) {
            _ARCommandSkyControllerButtonMappingsGetCurrentButtonMappingsListener.onSkyControllerButtonMappingsGetCurrentButtonMappingsUpdate ();
        }
    }

    void onSkyControllerButtonMappingsGetAvailableButtonMappingsUpdate () {
        if(_ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener != null) {
            _ARCommandSkyControllerButtonMappingsGetAvailableButtonMappingsListener.onSkyControllerButtonMappingsGetAvailableButtonMappingsUpdate ();
        }
    }

    void onSkyControllerButtonMappingsSetButtonMappingUpdate (int key_id, String mapping_uid) {
        if(_ARCommandSkyControllerButtonMappingsSetButtonMappingListener != null) {
            _ARCommandSkyControllerButtonMappingsSetButtonMappingListener.onSkyControllerButtonMappingsSetButtonMappingUpdate (key_id, mapping_uid);
        }
    }

    void onSkyControllerButtonMappingsDefaultButtonMappingUpdate () {
        if(_ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener != null) {
            _ARCommandSkyControllerButtonMappingsDefaultButtonMappingListener.onSkyControllerButtonMappingsDefaultButtonMappingUpdate ();
        }
    }

    void onSkyControllerAxisMappingsGetCurrentAxisMappingsUpdate () {
        if(_ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener != null) {
            _ARCommandSkyControllerAxisMappingsGetCurrentAxisMappingsListener.onSkyControllerAxisMappingsGetCurrentAxisMappingsUpdate ();
        }
    }

    void onSkyControllerAxisMappingsGetAvailableAxisMappingsUpdate () {
        if(_ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener != null) {
            _ARCommandSkyControllerAxisMappingsGetAvailableAxisMappingsListener.onSkyControllerAxisMappingsGetAvailableAxisMappingsUpdate ();
        }
    }

    void onSkyControllerAxisMappingsSetAxisMappingUpdate (int axis_id, String mapping_uid) {
        if(_ARCommandSkyControllerAxisMappingsSetAxisMappingListener != null) {
            _ARCommandSkyControllerAxisMappingsSetAxisMappingListener.onSkyControllerAxisMappingsSetAxisMappingUpdate (axis_id, mapping_uid);
        }
    }

    void onSkyControllerAxisMappingsDefaultAxisMappingUpdate () {
        if(_ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener != null) {
            _ARCommandSkyControllerAxisMappingsDefaultAxisMappingListener.onSkyControllerAxisMappingsDefaultAxisMappingUpdate ();
        }
    }

    void onSkyControllerAxisFiltersGetCurrentAxisFiltersUpdate () {
        if(_ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener != null) {
            _ARCommandSkyControllerAxisFiltersGetCurrentAxisFiltersListener.onSkyControllerAxisFiltersGetCurrentAxisFiltersUpdate ();
        }
    }

    void onSkyControllerAxisFiltersGetPresetAxisFiltersUpdate () {
        if(_ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener != null) {
            _ARCommandSkyControllerAxisFiltersGetPresetAxisFiltersListener.onSkyControllerAxisFiltersGetPresetAxisFiltersUpdate ();
        }
    }

    void onSkyControllerAxisFiltersSetAxisFilterUpdate (int axis_id, String filter_uid_or_builder) {
        if(_ARCommandSkyControllerAxisFiltersSetAxisFilterListener != null) {
            _ARCommandSkyControllerAxisFiltersSetAxisFilterListener.onSkyControllerAxisFiltersSetAxisFilterUpdate (axis_id, filter_uid_or_builder);
        }
    }

    void onSkyControllerAxisFiltersDefaultAxisFiltersUpdate () {
        if(_ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener != null) {
            _ARCommandSkyControllerAxisFiltersDefaultAxisFiltersListener.onSkyControllerAxisFiltersDefaultAxisFiltersUpdate ();
        }
    }

    void onSkyControllerCoPilotingSetPilotingSourceUpdate (ARCOMMANDS_SKYCONTROLLER_COPILOTING_SETPILOTINGSOURCE_SOURCE_ENUM source) {
        if(_ARCommandSkyControllerCoPilotingSetPilotingSourceListener != null) {
            _ARCommandSkyControllerCoPilotingSetPilotingSourceListener.onSkyControllerCoPilotingSetPilotingSourceUpdate (source);
        }
    }

    void onSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesUpdate (byte enable) {
        if(_ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener != null) {
            _ARCommandSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesListener.onSkyControllerCalibrationEnableMagnetoCalibrationQualityUpdatesUpdate (enable);
        }
    }

    void onSkyControllerCalibrationStartCalibrationUpdate () {
        if(_ARCommandSkyControllerCalibrationStartCalibrationListener != null) {
            _ARCommandSkyControllerCalibrationStartCalibrationListener.onSkyControllerCalibrationStartCalibrationUpdate ();
        }
    }

    void onSkyControllerCalibrationAbortCalibrationUpdate () {
        if(_ARCommandSkyControllerCalibrationAbortCalibrationListener != null) {
            _ARCommandSkyControllerCalibrationAbortCalibrationListener.onSkyControllerCalibrationAbortCalibrationUpdate ();
        }
    }

    void onSkyControllerFactoryResetUpdate () {
        if(_ARCommandSkyControllerFactoryResetListener != null) {
            _ARCommandSkyControllerFactoryResetListener.onSkyControllerFactoryResetUpdate ();
        }
    }

    void onSkyControllerWifiStateWifiListUpdate (String bssid, String ssid, byte secured, byte saved, int rssi, int frequency) {
        if(_ARCommandSkyControllerWifiStateWifiListListener != null) {
            _ARCommandSkyControllerWifiStateWifiListListener.onSkyControllerWifiStateWifiListUpdate (bssid, ssid, secured, saved, rssi, frequency);
        }
    }

    void onSkyControllerWifiStateConnexionChangedUpdate (String ssid, ARCOMMANDS_SKYCONTROLLER_WIFISTATE_CONNEXIONCHANGED_STATUS_ENUM status) {
        if(_ARCommandSkyControllerWifiStateConnexionChangedListener != null) {
            _ARCommandSkyControllerWifiStateConnexionChangedListener.onSkyControllerWifiStateConnexionChangedUpdate (ssid, status);
        }
    }

    void onSkyControllerWifiStateWifiAuthChannelListChangedUpdate (ARCOMMANDS_SKYCONTROLLER_WIFISTATE_WIFIAUTHCHANNELLISTCHANGED_BAND_ENUM band, byte channel, byte in_or_out) {
        if(_ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener != null) {
            _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedListener.onSkyControllerWifiStateWifiAuthChannelListChangedUpdate (band, channel, in_or_out);
        }
    }

    void onSkyControllerWifiStateAllWifiAuthChannelChangedUpdate () {
        if(_ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener != null) {
            _ARCommandSkyControllerWifiStateAllWifiAuthChannelChangedListener.onSkyControllerWifiStateAllWifiAuthChannelChangedUpdate ();
        }
    }

    void onSkyControllerWifiStateWifiSignalChangedUpdate (byte level) {
        if(_ARCommandSkyControllerWifiStateWifiSignalChangedListener != null) {
            _ARCommandSkyControllerWifiStateWifiSignalChangedListener.onSkyControllerWifiStateWifiSignalChangedUpdate (level);
        }
    }

    void onSkyControllerWifiStateWifiAuthChannelListChangedV2Update (ARCOMMANDS_SKYCONTROLLER_WIFISTATE_WIFIAUTHCHANNELLISTCHANGEDV2_BAND_ENUM band, byte channel, byte in_or_out, byte list_flags) {
        if(_ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener != null) {
            _ARCommandSkyControllerWifiStateWifiAuthChannelListChangedV2Listener.onSkyControllerWifiStateWifiAuthChannelListChangedV2Update (band, channel, in_or_out, list_flags);
        }
    }

    void onSkyControllerWifiStateWifiCountryChangedUpdate (String code) {
        if(_ARCommandSkyControllerWifiStateWifiCountryChangedListener != null) {
            _ARCommandSkyControllerWifiStateWifiCountryChangedListener.onSkyControllerWifiStateWifiCountryChangedUpdate (code);
        }
    }

    void onSkyControllerWifiStateWifiEnvironmentChangedUpdate (ARCOMMANDS_SKYCONTROLLER_WIFISTATE_WIFIENVIRONMENTCHANGED_ENVIRONMENT_ENUM environment) {
        if(_ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener != null) {
            _ARCommandSkyControllerWifiStateWifiEnvironmentChangedListener.onSkyControllerWifiStateWifiEnvironmentChangedUpdate (environment);
        }
    }

    void onSkyControllerDeviceStateDeviceListUpdate (String name) {
        if(_ARCommandSkyControllerDeviceStateDeviceListListener != null) {
            _ARCommandSkyControllerDeviceStateDeviceListListener.onSkyControllerDeviceStateDeviceListUpdate (name);
        }
    }

    void onSkyControllerDeviceStateConnexionChangedUpdate (ARCOMMANDS_SKYCONTROLLER_DEVICESTATE_CONNEXIONCHANGED_STATUS_ENUM status, String deviceName, short deviceProductID) {
        if(_ARCommandSkyControllerDeviceStateConnexionChangedListener != null) {
            _ARCommandSkyControllerDeviceStateConnexionChangedListener.onSkyControllerDeviceStateConnexionChangedUpdate (status, deviceName, deviceProductID);
        }
    }

    void onSkyControllerSettingsStateAllSettingsChangedUpdate () {
        if(_ARCommandSkyControllerSettingsStateAllSettingsChangedListener != null) {
            _ARCommandSkyControllerSettingsStateAllSettingsChangedListener.onSkyControllerSettingsStateAllSettingsChangedUpdate ();
        }
    }

    void onSkyControllerSettingsStateResetChangedUpdate () {
        if(_ARCommandSkyControllerSettingsStateResetChangedListener != null) {
            _ARCommandSkyControllerSettingsStateResetChangedListener.onSkyControllerSettingsStateResetChangedUpdate ();
        }
    }

    void onSkyControllerSettingsStateProductSerialChangedUpdate (String serialNumber) {
        if(_ARCommandSkyControllerSettingsStateProductSerialChangedListener != null) {
            _ARCommandSkyControllerSettingsStateProductSerialChangedListener.onSkyControllerSettingsStateProductSerialChangedUpdate (serialNumber);
        }
    }

    void onSkyControllerSettingsStateProductVariantChangedUpdate (ARCOMMANDS_SKYCONTROLLER_SETTINGSSTATE_PRODUCTVARIANTCHANGED_VARIANT_ENUM variant) {
        if(_ARCommandSkyControllerSettingsStateProductVariantChangedListener != null) {
            _ARCommandSkyControllerSettingsStateProductVariantChangedListener.onSkyControllerSettingsStateProductVariantChangedUpdate (variant);
        }
    }

    void onSkyControllerSettingsStateProductVersionChangedUpdate (String software, String hardware) {
        if(_ARCommandSkyControllerSettingsStateProductVersionChangedListener != null) {
            _ARCommandSkyControllerSettingsStateProductVersionChangedListener.onSkyControllerSettingsStateProductVersionChangedUpdate (software, hardware);
        }
    }

    void onSkyControllerSettingsStateCPUIDUpdate (String id) {
        if(_ARCommandSkyControllerSettingsStateCPUIDListener != null) {
            _ARCommandSkyControllerSettingsStateCPUIDListener.onSkyControllerSettingsStateCPUIDUpdate (id);
        }
    }

    void onSkyControllerCommonStateAllStatesChangedUpdate () {
        if(_ARCommandSkyControllerCommonStateAllStatesChangedListener != null) {
            _ARCommandSkyControllerCommonStateAllStatesChangedListener.onSkyControllerCommonStateAllStatesChangedUpdate ();
        }
    }

    void onSkyControllerSkyControllerStateBatteryChangedUpdate (byte percent) {
        if(_ARCommandSkyControllerSkyControllerStateBatteryChangedListener != null) {
            _ARCommandSkyControllerSkyControllerStateBatteryChangedListener.onSkyControllerSkyControllerStateBatteryChangedUpdate (percent);
        }
    }

    void onSkyControllerSkyControllerStateGpsFixChangedUpdate (byte fixed) {
        if(_ARCommandSkyControllerSkyControllerStateGpsFixChangedListener != null) {
            _ARCommandSkyControllerSkyControllerStateGpsFixChangedListener.onSkyControllerSkyControllerStateGpsFixChangedUpdate (fixed);
        }
    }

    void onSkyControllerSkyControllerStateGpsPositionChangedUpdate (double latitude, double longitude, double altitude, float heading) {
        if(_ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener != null) {
            _ARCommandSkyControllerSkyControllerStateGpsPositionChangedListener.onSkyControllerSkyControllerStateGpsPositionChangedUpdate (latitude, longitude, altitude, heading);
        }
    }

    void onSkyControllerSkyControllerStateBatteryStateUpdate (ARCOMMANDS_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYSTATE_STATE_ENUM state) {
        if(_ARCommandSkyControllerSkyControllerStateBatteryStateListener != null) {
            _ARCommandSkyControllerSkyControllerStateBatteryStateListener.onSkyControllerSkyControllerStateBatteryStateUpdate (state);
        }
    }

    void onSkyControllerSkyControllerStateAttitudeChangedUpdate (float q0, float q1, float q2, float q3) {
        if(_ARCommandSkyControllerSkyControllerStateAttitudeChangedListener != null) {
            _ARCommandSkyControllerSkyControllerStateAttitudeChangedListener.onSkyControllerSkyControllerStateAttitudeChangedUpdate (q0, q1, q2, q3);
        }
    }

    void onSkyControllerAccessPointSettingsStateAccessPointSSIDChangedUpdate (String ssid) {
        if(_ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener != null) {
            _ARCommandSkyControllerAccessPointSettingsStateAccessPointSSIDChangedListener.onSkyControllerAccessPointSettingsStateAccessPointSSIDChangedUpdate (ssid);
        }
    }

    void onSkyControllerAccessPointSettingsStateAccessPointChannelChangedUpdate (byte channel) {
        if(_ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener != null) {
            _ARCommandSkyControllerAccessPointSettingsStateAccessPointChannelChangedListener.onSkyControllerAccessPointSettingsStateAccessPointChannelChangedUpdate (channel);
        }
    }

    void onSkyControllerAccessPointSettingsStateWifiSelectionChangedUpdate (ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_WIFISELECTIONCHANGED_TYPE_ENUM type, ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_WIFISELECTIONCHANGED_BAND_ENUM band, byte channel) {
        if(_ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener != null) {
            _ARCommandSkyControllerAccessPointSettingsStateWifiSelectionChangedListener.onSkyControllerAccessPointSettingsStateWifiSelectionChangedUpdate (type, band, channel);
        }
    }

    void onSkyControllerAccessPointSettingsStateWifiSecurityChangedUpdate (ARCOMMANDS_SKYCONTROLLER_ACCESSPOINTSETTINGSSTATE_WIFISECURITYCHANGED_SECURITY_TYPE_ENUM security_type, String key) {
        if(_ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener != null) {
            _ARCommandSkyControllerAccessPointSettingsStateWifiSecurityChangedListener.onSkyControllerAccessPointSettingsStateWifiSecurityChangedUpdate (security_type, key);
        }
    }

    void onSkyControllerGamepadInfosStateGamepadControlUpdate (ARCOMMANDS_SKYCONTROLLER_GAMEPADINFOSSTATE_GAMEPADCONTROL_TYPE_ENUM type, int id, String name) {
        if(_ARCommandSkyControllerGamepadInfosStateGamepadControlListener != null) {
            _ARCommandSkyControllerGamepadInfosStateGamepadControlListener.onSkyControllerGamepadInfosStateGamepadControlUpdate (type, id, name);
        }
    }

    void onSkyControllerGamepadInfosStateAllGamepadControlsSentUpdate () {
        if(_ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener != null) {
            _ARCommandSkyControllerGamepadInfosStateAllGamepadControlsSentListener.onSkyControllerGamepadInfosStateAllGamepadControlsSentUpdate ();
        }
    }

    void onSkyControllerButtonMappingsStateCurrentButtonMappingsUpdate (int key_id, String mapping_uid) {
        if(_ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener != null) {
            _ARCommandSkyControllerButtonMappingsStateCurrentButtonMappingsListener.onSkyControllerButtonMappingsStateCurrentButtonMappingsUpdate (key_id, mapping_uid);
        }
    }

    void onSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentUpdate () {
        if(_ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener != null) {
            _ARCommandSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentListener.onSkyControllerButtonMappingsStateAllCurrentButtonMappingsSentUpdate ();
        }
    }

    void onSkyControllerButtonMappingsStateAvailableButtonMappingsUpdate (String mapping_uid, String name) {
        if(_ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener != null) {
            _ARCommandSkyControllerButtonMappingsStateAvailableButtonMappingsListener.onSkyControllerButtonMappingsStateAvailableButtonMappingsUpdate (mapping_uid, name);
        }
    }

    void onSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentUpdate () {
        if(_ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener != null) {
            _ARCommandSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentListener.onSkyControllerButtonMappingsStateAllAvailableButtonsMappingsSentUpdate ();
        }
    }

    void onSkyControllerAxisMappingsStateCurrentAxisMappingsUpdate (int axis_id, String mapping_uid) {
        if(_ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener != null) {
            _ARCommandSkyControllerAxisMappingsStateCurrentAxisMappingsListener.onSkyControllerAxisMappingsStateCurrentAxisMappingsUpdate (axis_id, mapping_uid);
        }
    }

    void onSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentUpdate () {
        if(_ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener != null) {
            _ARCommandSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentListener.onSkyControllerAxisMappingsStateAllCurrentAxisMappingsSentUpdate ();
        }
    }

    void onSkyControllerAxisMappingsStateAvailableAxisMappingsUpdate (String mapping_uid, String name) {
        if(_ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener != null) {
            _ARCommandSkyControllerAxisMappingsStateAvailableAxisMappingsListener.onSkyControllerAxisMappingsStateAvailableAxisMappingsUpdate (mapping_uid, name);
        }
    }

    void onSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentUpdate () {
        if(_ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener != null) {
            _ARCommandSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentListener.onSkyControllerAxisMappingsStateAllAvailableAxisMappingsSentUpdate ();
        }
    }

    void onSkyControllerAxisFiltersStateCurrentAxisFiltersUpdate (int axis_id, String filter_uid_or_builder) {
        if(_ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener != null) {
            _ARCommandSkyControllerAxisFiltersStateCurrentAxisFiltersListener.onSkyControllerAxisFiltersStateCurrentAxisFiltersUpdate (axis_id, filter_uid_or_builder);
        }
    }

    void onSkyControllerAxisFiltersStateAllCurrentFiltersSentUpdate () {
        if(_ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener != null) {
            _ARCommandSkyControllerAxisFiltersStateAllCurrentFiltersSentListener.onSkyControllerAxisFiltersStateAllCurrentFiltersSentUpdate ();
        }
    }

    void onSkyControllerAxisFiltersStatePresetAxisFiltersUpdate (String filter_uid, String name) {
        if(_ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener != null) {
            _ARCommandSkyControllerAxisFiltersStatePresetAxisFiltersListener.onSkyControllerAxisFiltersStatePresetAxisFiltersUpdate (filter_uid, name);
        }
    }

    void onSkyControllerAxisFiltersStateAllPresetFiltersSentUpdate () {
        if(_ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener != null) {
            _ARCommandSkyControllerAxisFiltersStateAllPresetFiltersSentListener.onSkyControllerAxisFiltersStateAllPresetFiltersSentUpdate ();
        }
    }

    void onSkyControllerCoPilotingStatePilotingSourceUpdate (ARCOMMANDS_SKYCONTROLLER_COPILOTINGSTATE_PILOTINGSOURCE_SOURCE_ENUM source) {
        if(_ARCommandSkyControllerCoPilotingStatePilotingSourceListener != null) {
            _ARCommandSkyControllerCoPilotingStatePilotingSourceListener.onSkyControllerCoPilotingStatePilotingSourceUpdate (source);
        }
    }

    void onSkyControllerCalibrationStateMagnetoCalibrationStateUpdate (ARCOMMANDS_SKYCONTROLLER_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTATE_STATUS_ENUM status, byte X_Quality, byte Y_Quality, byte Z_Quality) {
        if(_ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener != null) {
            _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateListener.onSkyControllerCalibrationStateMagnetoCalibrationStateUpdate (status, X_Quality, Y_Quality, Z_Quality);
        }
    }

    void onSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateUpdate (byte enabled) {
        if(_ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener != null) {
            _ARCommandSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateListener.onSkyControllerCalibrationStateMagnetoCalibrationQualityUpdatesStateUpdate (enabled);
        }
    }

    void onSkyControllerCalibrationStateMagnetoCalibrationStateV2Update (ARCOMMANDS_SKYCONTROLLER_CALIBRATIONSTATE_MAGNETOCALIBRATIONSTATEV2_STATE_ENUM state) {
        if(_ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener != null) {
            _ARCommandSkyControllerCalibrationStateMagnetoCalibrationStateV2Listener.onSkyControllerCalibrationStateMagnetoCalibrationStateV2Update (state);
        }
    }

    void onSkyControllerButtonEventsSettingsUpdate () {
        if(_ARCommandSkyControllerButtonEventsSettingsListener != null) {
            _ARCommandSkyControllerButtonEventsSettingsListener.onSkyControllerButtonEventsSettingsUpdate ();
        }
    }

    void onSkyControllerCommonEventStateShutdownUpdate (ARCOMMANDS_SKYCONTROLLER_COMMONEVENTSTATE_SHUTDOWN_REASON_ENUM reason) {
        if(_ARCommandSkyControllerCommonEventStateShutdownListener != null) {
            _ARCommandSkyControllerCommonEventStateShutdownListener.onSkyControllerCommonEventStateShutdownUpdate (reason);
        }
    }

    void onThermalCamActivateUpdate (byte cam_id) {
        if(_ARCommandThermalCamActivateListener != null) {
            _ARCommandThermalCamActivateListener.onThermalCamActivateUpdate (cam_id);
        }
    }

    void onThermalCamDeactivateUpdate (byte cam_id) {
        if(_ARCommandThermalCamDeactivateListener != null) {
            _ARCommandThermalCamDeactivateListener.onThermalCamDeactivateUpdate (cam_id);
        }
    }

    void onThermalCamSetSensitivityUpdate (byte cam_id, ARCOMMANDS_THERMAL_CAM_RANGE_ENUM range) {
        if(_ARCommandThermalCamSetSensitivityListener != null) {
            _ARCommandThermalCamSetSensitivityListener.onThermalCamSetSensitivityUpdate (cam_id, range);
        }
    }

    void onThermalCamCameraStateUpdate (byte cam_id, ARCOMMANDS_THERMAL_CAM_STATE_ENUM state, byte list_flags) {
        if(_ARCommandThermalCamCameraStateListener != null) {
            _ARCommandThermalCamCameraStateListener.onThermalCamCameraStateUpdate (cam_id, state, list_flags);
        }
    }

    void onThermalCamSensitivityUpdate (byte cam_id, ARCOMMANDS_THERMAL_CAM_RANGE_ENUM current_range, byte available_ranges, byte list_flags) {
        if(_ARCommandThermalCamSensitivityListener != null) {
            _ARCommandThermalCamSensitivityListener.onThermalCamSensitivityUpdate (cam_id, current_range, available_ranges, list_flags);
        }
    }

    void onThermalCamCalibrationInfosUpdate (byte cam_id, float roll, float pitch, float yaw, byte list_flags) {
        if(_ARCommandThermalCamCalibrationInfosListener != null) {
            _ARCommandThermalCamCalibrationInfosListener.onThermalCamCalibrationInfosUpdate (cam_id, roll, pitch, yaw, list_flags);
        }
    }

    void onWifiScanUpdate (byte band) {
        if(_ARCommandWifiScanListener != null) {
            _ARCommandWifiScanListener.onWifiScanUpdate (band);
        }
    }

    void onWifiUpdateAuthorizedChannelsUpdate () {
        if(_ARCommandWifiUpdateAuthorizedChannelsListener != null) {
            _ARCommandWifiUpdateAuthorizedChannelsListener.onWifiUpdateAuthorizedChannelsUpdate ();
        }
    }

    void onWifiSetApChannelUpdate (ARCOMMANDS_WIFI_SELECTION_TYPE_ENUM type, ARCOMMANDS_WIFI_BAND_ENUM band, byte channel) {
        if(_ARCommandWifiSetApChannelListener != null) {
            _ARCommandWifiSetApChannelListener.onWifiSetApChannelUpdate (type, band, channel);
        }
    }

    void onWifiSetSecurityUpdate (ARCOMMANDS_WIFI_SECURITY_TYPE_ENUM type, String key, ARCOMMANDS_WIFI_SECURITY_KEY_TYPE_ENUM key_type) {
        if(_ARCommandWifiSetSecurityListener != null) {
            _ARCommandWifiSetSecurityListener.onWifiSetSecurityUpdate (type, key, key_type);
        }
    }

    void onWifiSetCountryUpdate (ARCOMMANDS_WIFI_COUNTRY_SELECTION_ENUM selection_mode, String code) {
        if(_ARCommandWifiSetCountryListener != null) {
            _ARCommandWifiSetCountryListener.onWifiSetCountryUpdate (selection_mode, code);
        }
    }

    void onWifiSetEnvironmentUpdate (ARCOMMANDS_WIFI_ENVIRONMENT_ENUM environment) {
        if(_ARCommandWifiSetEnvironmentListener != null) {
            _ARCommandWifiSetEnvironmentListener.onWifiSetEnvironmentUpdate (environment);
        }
    }

    void onWifiScannedItemUpdate (String ssid, short rssi, ARCOMMANDS_WIFI_BAND_ENUM band, byte channel, byte list_flags) {
        if(_ARCommandWifiScannedItemListener != null) {
            _ARCommandWifiScannedItemListener.onWifiScannedItemUpdate (ssid, rssi, band, channel, list_flags);
        }
    }

    void onWifiAuthorizedChannelUpdate (ARCOMMANDS_WIFI_BAND_ENUM band, byte channel, byte environment, byte list_flags) {
        if(_ARCommandWifiAuthorizedChannelListener != null) {
            _ARCommandWifiAuthorizedChannelListener.onWifiAuthorizedChannelUpdate (band, channel, environment, list_flags);
        }
    }

    void onWifiApChannelChangedUpdate (ARCOMMANDS_WIFI_SELECTION_TYPE_ENUM type, ARCOMMANDS_WIFI_BAND_ENUM band, byte channel) {
        if(_ARCommandWifiApChannelChangedListener != null) {
            _ARCommandWifiApChannelChangedListener.onWifiApChannelChangedUpdate (type, band, channel);
        }
    }

    void onWifiSecurityChangedUpdate (String key, ARCOMMANDS_WIFI_SECURITY_TYPE_ENUM key_type) {
        if(_ARCommandWifiSecurityChangedListener != null) {
            _ARCommandWifiSecurityChangedListener.onWifiSecurityChangedUpdate (key, key_type);
        }
    }

    void onWifiCountryChangedUpdate (ARCOMMANDS_WIFI_COUNTRY_SELECTION_ENUM selection_mode, String code) {
        if(_ARCommandWifiCountryChangedListener != null) {
            _ARCommandWifiCountryChangedListener.onWifiCountryChangedUpdate (selection_mode, code);
        }
    }

    void onWifiEnvironmentChangedUpdate (ARCOMMANDS_WIFI_ENVIRONMENT_ENUM environment) {
        if(_ARCommandWifiEnvironmentChangedListener != null) {
            _ARCommandWifiEnvironmentChangedListener.onWifiEnvironmentChangedUpdate (environment);
        }
    }

    void onWifiRssiChangedUpdate (short rssi) {
        if(_ARCommandWifiRssiChangedListener != null) {
            _ARCommandWifiRssiChangedListener.onWifiRssiChangedUpdate (rssi);
        }
    }

    void onWifiSupportedCountriesUpdate (String countries) {
        if(_ARCommandWifiSupportedCountriesListener != null) {
            _ARCommandWifiSupportedCountriesListener.onWifiSupportedCountriesUpdate (countries);
        }
    }

    /* **************** */
    /* NATIVE FUNCTIONS */
    /* **************** */

    /**
     * Memory allocation in native memory space<br>
     * Allocates a decoder and return its C-Pointer
     * @return C-Pointer on the decoder, or 0 (C-NULL) if the alloc failed
     */
    private native long nativeNewDecoder ();

    /**
     * Memory release in native memory space<br>
     * Frees a decoder from its C-Pointer<br>
     * This call is needed because JVM do not know about native memory allocs
     * @param decoder C-Pointer on the decoder to free
     */
    private native void nativeDeleteDecoder (long decoder);

    private native int     nativeDecode (long jdecoder, long jpdata, int jdataSize);

}

