import ctypes
prange = xrange
gpurange = xrange
calrt = ctypes.cdll.LoadLibrary('libaticalrt.so')
aux = ctypes.cdll.LoadLibrary('unpyaux.so')
def types(*args):
	def g(f):
		return f
	return g
def init():
	aux.unpyInitGpu()
def shutdown():
	aux.unpyCloseGpu()
def enableGpu():
	aux.unpyEnableGpu()
def enableUnroll():
	aux.unpyEnableUnroll()
def disableUnroll():
	aux.unpyDisableUnroll()
