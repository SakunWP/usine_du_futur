# libtar/Makefile.  Generated from Makefile.in by configure.

### Path settings
srcdir		= /Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/packages/libtar/libtar
top_srcdir	= /Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/packages/libtar
top_builddir    = ..
prefix		= /usr
exec_prefix	= ${prefix}
bindir		= ${exec_prefix}/bin
libdir		= ${exec_prefix}/lib
includedir	= ${prefix}/include
datarootdir	= ${prefix}/share

PACKAGE_NAME	= libtar
PACKAGE_VERSION	= 1.2.20



SHELL           = /bin/sh

### Installation programs and flags
INSTALL		= /usr/bin/install -p
INSTALL_PROGRAM	= ${INSTALL} -s
INSTALL_DATA	= ${INSTALL} -m 644
LN_S		= ln -s
MKDIR		= mkdir -p -m 755

### Compiler and link options
CC		=  /Users/kjaffres/Library/Android/sdk/ndk/19.2.5345600/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android21-clang
LIBTOOL         = $(SHELL) $(top_builddir)/libtool
CPPFLAGS	= -I.. \
		  -I../lib \
		  -I../listhash \
		  -I${top_srcdir}/lib \
		  -I${top_srcdir}/compat \
		   -isystem/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/include/aarch64-unknown-linux-android21 -isystem/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/include
CFLAGS		=  -isystem/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/include/aarch64-unknown-linux-android21 -isystem/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/include -std=gnu99 -pipe -O2 -g -ffunction-sections -DALCHEMY_BUILD  -fPIC -DANDROID -DANDROID_NDK  -Qunused-arguments
LDFLAGS		= -O2 -Wl,-O1 -L/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/lib/aarch64-unknown-linux-android21 -L/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/lib -L/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/lib/aarch64-unknown-linux-android21 -L/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/lib -Wl,-rpath-link=/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/lib/aarch64-unknown-linux-android21 -Wl,-rpath-link=/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/lib -Wl,-rpath-link=/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/lib/aarch64-unknown-linux-android21 -Wl,-rpath-link=/Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/out/arsdk-android/arm64-v8a/staging/usr/lib
LIBS		=  -ltar
LIBOBJS		=  ${LIBOBJDIR}basename$U.o ${LIBOBJDIR}dirname$U.o ${LIBOBJDIR}fnmatch$U.o ${LIBOBJDIR}snprintf$U.o ${LIBOBJDIR}strmode$U.o
RANLIB		= /Users/kjaffres/Library/Android/sdk/ndk/19.2.5345600/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android-ranlib

VPATH		= /Users/kjaffres/Documents/MarioKart/ARDroneSDK/ARSDK_KJR/packages/libtar/libtar


### Makefile rules - no user-servicable parts below

LIBTAR_OBJS	= libtar.lo
LIBTAR_HDRS	= ../config.h \
		  ${top_srcdir}/compat/compat.h \
		  ${top_srcdir}/lib/libtar.h \
		  ../listhash/libtar_listhash.h
LIBTAR_LIBS	= $(top_builddir)/lib
ALL		= libtar


all: ${ALL}

.PHONY: clean distclean install

libtar: ${LIBTAR_OBJS} ${LIBTAR_LIBS} ${LIBTAR_HDRS}
	$(LIBTOOL) --mode=link ${CC} ${CFLAGS} ${LDFLAGS} -o libtar $(LIBTAR_OBJS) -L${LIBTAR_LIBS} ${LIBS}

${LIBTAR_OBJS}: ${LIBTAR_HDRS}

%.lo: %.c
	$(LIBTOOL) --mode=compile ${CC} ${CFLAGS} ${CPPFLAGS} -c -o $@ $<

clean:
	rm -rf *~ *.o ${ALL} $(LIBTAR_OBJS) core .libs

distclean: clean
	rm -f Makefile

install: ${ALL}
	${MKDIR} ${DESTDIR}${bindir}
	$(LIBTOOL) --mode=install ${INSTALL_PROGRAM} libtar ${DESTDIR}${bindir}

