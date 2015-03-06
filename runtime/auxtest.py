import ctypes
aux = ctypes.cdll.LoadLibrary('unpyaux.so')
aux.unpyLog('hello')
