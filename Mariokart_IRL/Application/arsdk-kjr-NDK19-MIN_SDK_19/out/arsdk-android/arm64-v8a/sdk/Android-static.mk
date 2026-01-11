# GENERATED FILE, DO NOT EDIT

ifndef ALCHEMY_SDK_ANDROID_STATIC_arm64-v8a_INCLUDED
ALCHEMY_SDK_ANDROID_STATIC_arm64-v8a_INCLUDED = 1

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := curl-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libcurl.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libressl-libssl-static \
	libressl-libcrypto-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := json-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libjson-c.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARCommands-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarcommands.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARController-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarcontroller.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARDiscovery-static \
	libARCommands-static \
	libARUtils-static \
	libARStream-static \
	libARStream2-static \
	libARNetworkAL-static \
	libARNetwork-static \
	librtsp-static \
	libsdp-static \
	libfutils-static \
	libpomp-static \
	libmux-static \
	json-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARDataTransfer-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libardatatransfer.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARDiscovery-static \
	libARCommands-static \
	libARUtils-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARDiscovery-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libardiscovery.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARNetwork-static \
	libARNetworkAL-static \
	libARCommands-static \
	libmux-static \
	libpomp-static \
	json-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARMavlink-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarmavlink.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include \
	$(LOCAL_PATH)/usr/include/libARMavlink/generated
LOCAL_STATIC_LIBRARIES := libARSAL-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARMedia-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarmedia.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARDiscovery-static \
	json-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARNetwork-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarnetwork.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARNetworkAL-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARNetworkAL-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarnetworkal.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libmux-static \
	libpomp-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARSAL-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarsal.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include \
	$(LOCAL_PATH)/usr/include/libARSAL/Config/android
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARStream-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarstream.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARNetworkAL-static \
	libARNetwork-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARStream2-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarstream2.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libmux-static \
	libpomp-static \
	libARMedia-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARUpdater-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarupdater.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARDiscovery-static \
	libARCommands-static \
	libARUtils-static \
	libARDataTransfer-static \
	libpuf-static \
	libmux-static \
	libpomp-static \
	json-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARUtils-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libarutils.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libARSAL-static \
	libARDiscovery-static \
	libARCommands-static \
	libmux-static \
	curl-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libfutils-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libfutils.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libulog-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libmux-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libmux.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libpomp-static \
	libulog-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libpomp-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libpomp.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libulog-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libpuf-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libpuf.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libulog-static \
	libtar-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libressl-libssl-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libssl.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libressl-libcrypto-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libcrypto.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := librtsp-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/librtsp.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libpomp-static \
	libfutils-static \
	libulog-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libsdp-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libsdp.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
LOCAL_STATIC_LIBRARIES := libfutils-static \
	libulog-static
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libtar-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libtar.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libulog-static
LOCAL_SRC_FILES := $(LOCAL_PATH)/usr/lib/libulog.a
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/usr/include
include $(PREBUILT_STATIC_LIBRARY)

endif
