# GENERATED FILE, DO NOT EDIT

LOCAL_PATH := $(call my-dir)

ifneq ("$(TARGET_OS)","linux")
  $(error This sdk is for TARGET_OS=linux)
endif

ifneq ("$(TARGET_OS_FLAVOUR)","android")
  $(error This sdk is for TARGET_OS_FLAVOUR=android)
endif

ifneq ("$(TARGET_ARCH)","aarch64")
  $(error This sdk is for TARGET_ARCH=aarch64)
endif

ifneq ("$(TARGET_CC_FLAVOUR)","clang")
  $(error This sdk is for TARGET_CC_FLAVOUR=clang)
endif

ifneq ("$(TARGET_TOOLCHAIN_TRIPLET)","aarch64-unknown-linux-android21")
  $(error This sdk is for TARGET_TOOLCHAIN_TRIPLET=aarch64-unknown-linux-android21)
endif

ifneq ("$(TARGET_LIBC)","bionic")
  $(error This sdk is for TARGET_LIBC=bionic)
endif

ifneq ("$(TARGET_CC_VERSION)","8.0.2")
  $(error This sdk is for TARGET_CC_VERSION=8.0.2)
endif

include $(CLEAR_VARS)
LOCAL_MODULE := ARSDKBuildUtils
LOCAL_DESCRIPTION := ARSDK Build Utils
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 413c29657c8311719be2557780c0181a38d4b420
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_7_5-105-g413c296
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ARSDKBuildUtils
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := curl
LOCAL_DESCRIPTION := cURL
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 12b3261e23b356fdf60ee9e69fc4e9f960819918
LOCAL_REVISION_DESCRIBE := 12b3261
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ARSDKTools
LOCAL_EXPORT_LDLIBS := \
	-lcurl
LOCAL_LIBRARIES := libressl
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_HOST_MODULE := arsdkgen
LOCAL_REVISION := 6faa46898d94fa207fd1b039643d356d14b7db42
LOCAL_REVISION_DESCRIBE := 6faa468
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/arsdk-xml
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_HOST_MODULE := mavgen
LOCAL_REVISION := ed9b5620b539729d0fd3cf1187e1d66047cbb574
LOCAL_REVISION_DESCRIBE := ed9b562
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/mavlink.git
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := json
LOCAL_DESCRIPTION := JSON-C
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 12b3261e23b356fdf60ee9e69fc4e9f960819918
LOCAL_REVISION_DESCRIBE := 12b3261
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ARSDKTools
LOCAL_EXPORT_LDLIBS := \
	-ljson-c
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := libARCommands
LOCAL_DESCRIPTION := ARSDK Piloting and User Commands
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := d25b24e3d37c4fdf092d83196f575baf17420652
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-276-gd25b24e
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARCommands
LOCAL_LIBRARIES := libARSAL
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarcommands.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARController
LOCAL_DESCRIPTION := ARSDK Controller
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 8c90f306a10339801f9790a5cbf624f074b4e6ca
LOCAL_REVISION_DESCRIBE := 8c90f30
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARController
LOCAL_LIBRARIES := libARSAL libARDiscovery libARCommands libARUtils libARStream libARStream2 libARNetworkAL libARNetwork librtsp libsdp libfutils libpomp libmux uthash json
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarcontroller.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARDataTransfer
LOCAL_DESCRIPTION := ARSDK DataTransfer
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 5e1ac3519a7b17915f6175952c5619765b2a4552
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-49-g5e1ac35
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARDataTransfer
LOCAL_LIBRARIES := libARSAL libARDiscovery libARCommands libARUtils
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libardatatransfer.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARDiscovery
LOCAL_DESCRIPTION := ARSDK Discovery and Connection Management Layer
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 05555aee4b38e91ab1dd98f6b0c35d1a71acdad2
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-423-g05555ae
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARDiscovery
LOCAL_LIBRARIES := libARSAL libARNetwork libARNetworkAL libARCommands libmux libpomp libARDiscovery_headers json
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libardiscovery.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARDiscovery_headers
LOCAL_DESCRIPTION := ARSDK Discovery and Connection Management Layer (Headers only)
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 05555aee4b38e91ab1dd98f6b0c35d1a71acdad2
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-423-g05555ae
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARDiscovery
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := libARMavlink
LOCAL_DESCRIPTION := ARSDK Mavlink
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 7c0f5f5d41ac962de64a438cf49cfb54b0e80be9
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-51-g7c0f5f5
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARMavlink
LOCAL_EXPORT_C_INCLUDES := \
	$(LOCAL_PATH)/usr/include/libARMavlink/generated
LOCAL_LIBRARIES := libARSAL
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarmavlink.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARMedia
LOCAL_DESCRIPTION := Library to manage video encapsulation
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := ffac8b08137414ecb5a7bcca091b460f8f4205bc
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-122-gffac8b0
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARMedia
LOCAL_LIBRARIES := libARSAL libARDiscovery json
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarmedia.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARNetwork
LOCAL_DESCRIPTION := ARSDK Network Control Library
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 2acc72543820e573dbd73fdbe020f4101335d5b2
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-54-g2acc725
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARNetwork
LOCAL_CONFIG_FILES := 1
sdk.libARNetwork.config := $(LOCAL_PATH)/config/libARNetwork.config
$(call load-config)
LOCAL_LIBRARIES := libARSAL libARNetworkAL
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarnetwork.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARNetworkAL
LOCAL_DESCRIPTION := ARSDK Network Control Library for Specific OS
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 4266b1e6ebc943159aa49c385adc04b4e975a08d
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-66-g4266b1e
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARNetworkAL
LOCAL_LIBRARIES := libARSAL libmux libpomp
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarnetworkal.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARSAL
LOCAL_DESCRIPTION := ARSDK Software Abstraction Layer
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 22e1f6b12aa6e46b26a75b768fd224c29ee00326
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-79-g22e1f6b
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARSAL
LOCAL_EXPORT_C_INCLUDES := \
	$(LOCAL_PATH)/usr/include/libARSAL/Config/android
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarsal.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARStream
LOCAL_DESCRIPTION := ARSDK Stream library
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 34a4b21ee105237b52e372eba53c5d309df79f8c
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-143-g34a4b21
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARStream
LOCAL_LIBRARIES := libARSAL libARNetworkAL libARNetwork
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarstream.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARStream2
LOCAL_DESCRIPTION := Parrot Streaming Library
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 4a22e26e52d22526e041588ebf63fb5c6805e124
LOCAL_REVISION_DESCRIBE := 4a22e26
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARStream2
LOCAL_LIBRARIES := libARSAL libmux libpomp libARMedia
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarstream2.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARUpdater
LOCAL_DESCRIPTION := ARSDK Updater
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := df4573835562fb3e0f6cbe145ca721b41b3b2658
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-97-gdf45738
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARUpdater
LOCAL_LIBRARIES := libARSAL libARDiscovery libARCommands libARUtils libARDataTransfer libpuf libmux libpomp json
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarupdater.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libARUtils
LOCAL_DESCRIPTION := ARSDK Utils
LOCAL_CATEGORY_PATH := dragon/libs
LOCAL_REVISION := 0c59b926aa2a08403129bd3c8864af46a3e027f6
LOCAL_REVISION_DESCRIBE := ARSDK3_version_3_1_0-125-g0c59b92
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libARUtils
LOCAL_LIBRARIES := libARSAL libARDiscovery libARCommands libmux curl
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libarutils.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libfutils
LOCAL_DESCRIPTION := c utility functions
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 99273914ba908c725450ebc753dbce1d68e836c2
LOCAL_REVISION_DESCRIBE := 9927391
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libfutils
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libulog
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libfutils.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libmux
LOCAL_REVISION := 4455b3bada76a67dbba60df1ee497d400d1361e4
LOCAL_REVISION_DESCRIBE := 4455b3b
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libmux
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libpomp libulog
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libmux.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libpomp
LOCAL_DESCRIPTION := Printf Oriented Message Protocol
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := a2a3bcbbfa8700b7c726827b5577ffb7d813e1dd
LOCAL_REVISION_DESCRIBE := a2a3bcb
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libpomp
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libulog
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libpomp.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libpuf
LOCAL_DESCRIPTION := Helper library for accessing parrot firmware files
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 08cfa7ef8db86bfa555d1536b1ef71cd884b5809
LOCAL_REVISION_DESCRIBE := 08cfa7e
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libpuf
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libulog zlib libtar
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libpuf.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libressl
LOCAL_DESCRIPTION := LibreSSL
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 12b3261e23b356fdf60ee9e69fc4e9f960819918
LOCAL_REVISION_DESCRIBE := 12b3261
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ARSDKTools
LOCAL_EXPORT_LDLIBS := \
	-lssl \
	-lcrypto
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := librtsp
LOCAL_DESCRIPTION := Real Time Streaming Protocol library
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := b9419acd191f6a9885f5dab13634e0825cd55931
LOCAL_REVISION_DESCRIBE := b9419ac
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/librtsp
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libpomp libfutils libulog
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := librtsp.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libsdp
LOCAL_DESCRIPTION := Session Description Protocol library
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := ab5404739bebbc7b775c778b7df69f40dcc2fbe6
LOCAL_REVISION_DESCRIBE := ab54047
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libsdp
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_LIBRARIES := libfutils libulog
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libsdp.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libtar
LOCAL_DESCRIPTION := tar library
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := da7b886f0af9742653a176006c782af75f2164dd
LOCAL_REVISION_DESCRIBE := da7b886
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/libtar
LOCAL_EXPORT_LDLIBS := \
	-ltar
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := libulog
LOCAL_DESCRIPTION := A minimalistic logging library derived from Android logger
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 8cc69a2ee465bab5ec219647a1eeca38c0cfadb1
LOCAL_REVISION_DESCRIBE := 8cc69a2
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ulog
LOCAL_EXPORT_C_INCLUDES :=
LOCAL_SDK := $(LOCAL_PATH)
LOCAL_DESTDIR := usr/lib
LOCAL_MODULE_FILENAME := libulog.so
include $(BUILD_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := uthash
LOCAL_DESCRIPTION := uthash
LOCAL_CATEGORY_PATH := libs
LOCAL_REVISION := 12b3261e23b356fdf60ee9e69fc4e9f960819918
LOCAL_REVISION_DESCRIBE := 12b3261
LOCAL_REVISION_URL := https://github.com/Parrot-Developers/ARSDKTools
LOCAL_SDK := $(LOCAL_PATH)
include $(BUILD_PREBUILT)

define arsdkgen-macro

         
# Setup some internal variables
arsdkgen_python := arsdkgen.py $(if $(call streq,$(V),1),-v)
arsdkgen_generator_path := $1
arsdkgen_module_build_dir := $(call local-get-build-dir)
arsdkgen_out_dir := $(if $(call is-path-absolute,$2),$2,$$(arsdkgen_module_build_dir)/$2)
arsdkgen_done_file := $$(arsdkgen_module_build_dir)/$(LOCAL_MODULE)-arsdkgen.done
$(if $(wildcard $(arsdkgen-macro-path)/arsdkgen.py), arsdkgen_gen_files := $$(shell $(arsdkgen-macro-path)/$$(arsdkgen_python) -f -o $$(arsdkgen_out_dir) $1 -- $(subst $(colon),$(space),$3) || echo FAILED)
	, arsdkgen_gen_files := $$(shell $(HOST_OUT_STAGING)/usr/lib/arsdkgen/$$(arsdkgen_python) -f -o $$(arsdkgen_out_dir) $1 -- $(subst $(colon),$(space),$3) || echo FAILED)
)
$$(if $$(call streq,$$(arsdkgen_gen_files),FAILED),$$(error Failed to list files))

# Create a dependency between generated files and .done file with an empty
# command to make sure regeneration is correctly triggered to files
# depending on them
$$(arsdkgen_gen_files): $$(arsdkgen_done_file)
	$(empty)

# Actual generation rule
# The copy of xml is staging is done in 2 steps because several modules could use
# the same xml the move ensure atomicity of the copy.
$$(arsdkgen_done_file): $(addprefix $(HOST_OUT_STAGING)/usr/lib/arsdkgen/,$(arsdkgen_files))
$$(arsdkgen_done_file): PRIVATE_OUT_DIR := $$(arsdkgen_out_dir)
$$(arsdkgen_done_file): .FORCE
	@echo "$$(PRIVATE_MODULE): Generating arsdk files"
	$(Q) $(HOST_OUT_STAGING)/usr/lib/arsdkgen/$$(arsdkgen_python) -o $$(PRIVATE_OUT_DIR) $1 -- $(subst $(colon),$(space),$3)
	@mkdir -p $$(dir $$@)
	@touch $$@

# Update alchemy variables for the module
LOCAL_CLEAN_FILES += $$(arsdkgen_done_file) $(if $(call is-path-absolute,$2),$(empty),$$(arsdkgen_gen_files))
LOCAL_EXPORT_PREREQUISITES += $$(arsdkgen_gen_files)
LOCAL_PREREQUISITES += $$(arsdkgen_gen_files)
LOCAL_CUSTOM_TARGETS += $$(arsdkgen_done_file)
LOCAL_DEPENDS_HOST_MODULES += host.arsdkgen
LOCAL_C_INCLUDES += $$(arsdkgen_out_dir)
LOCAL_DONE_FILES += $$(notdir $$(arsdkgen_done_file))

     
endef
$(call local-register-custom-macro,arsdkgen-macro)
define mavgen-macro

         
# Setup some internal variables
mavgen_xml_file := $3
mavgen_module_build_dir := $(call local-get-build-dir)
mavgen_out_dir := $(if $(call is-path-absolute,$2),$2,$$(mavgen_module_build_dir)/$2)
mavgen_done_file := $$(mavgen_module_build_dir)/$$(notdir $$(mavgen_xml_file)).done
mavgen_dep_file := $$(mavgen_module_build_dir)/$$(notdir $$(mavgen_xml_file)).d

# Actual generation rule
# The copy of xml is staging is done in 2 steps because several modules could use
# the same xml the move ensure atomicity of the copy.
$$(mavgen_done_file): PRIVATE_OUT_DIR := $$(mavgen_out_dir)/mavlink
$$(mavgen_done_file): PRIVATE_DEP_FILE := $$(mavgen_dep_file)
$$(mavgen_done_file): $$(mavgen_xml_file)
	@echo "$$(PRIVATE_MODULE): Generating mavlink files from $$(call path-from-top,$3)"
	$(Q) cd $(HOST_OUT_STAGING)/usr/lib/mavgen && python -m pymavlink.tools.mavgen --lang $1 -o $$(PRIVATE_OUT_DIR) $3
	@mkdir -p $(TARGET_OUT_STAGING)/usr/share/mavlink
	$(Q) cp -af $3 $(TARGET_OUT_STAGING)/usr/share/mavlink/$(notdir $3).$$(PRIVATE_MODULE)
	$(Q) mv -f $(TARGET_OUT_STAGING)/usr/share/mavlink/$(notdir $3).$$(PRIVATE_MODULE) $(TARGET_OUT_STAGING)/usr/share/mavlink/$(notdir $3)
	@mkdir -p $$(dir $$@)
	@:>$$(PRIVATE_DEP_FILE)
	@for header in $$$$(find $$(PRIVATE_OUT_DIR) -name '*.h'); do echo "$$$${header}: $$@" >> $$(PRIVATE_DEP_FILE); echo -e "\t@:" >> $$(PRIVATE_DEP_FILE); done
	@touch $$@
	@mkdir -p $(TARGET_OUT_BUILD)/mavlink/wireshark/plugins
	$(Q) cd $(HOST_OUT_STAGING)/usr/lib/mavgen && python -m pymavlink.tools.mavgen --lang=WLua -o $(TARGET_OUT_BUILD)/mavlink/wireshark/plugins/mymavlink.lua $3
-include $$(mavgen_dep_file)

# Update alchemy variables for the module
LOCAL_CLEAN_FILES += $$(mavgen_done_file) $$(mavgen_dep_file)
LOCAL_EXPORT_PREREQUISITES += $$(mavgen_done_file)
LOCAL_CUSTOM_TARGETS += $$(mavgen_done_file)
LOCAL_DEPENDS_HOST_MODULES += host.mavgen
LOCAL_C_INCLUDES += $$(mavgen_out_dir)

     
endef
$(call local-register-custom-macro,mavgen-macro)
