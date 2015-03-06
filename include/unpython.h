#include <cal.h>
#include <calcl.h>
extern int unpyIsGpuEnabled();
extern void unpyEnableGpu();
extern void unpyDisableGpu();

extern int unpyIsUnrollEnabled();
extern void unpyEnableUnroll();
extern void unpyDisableUnroll();

extern void unpyInitGpu();
extern void unpyCloseGpu();
extern CALdevice unpyGetDevice();

extern double unpyTimer();

extern void unpyLog(char *str);


