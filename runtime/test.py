import unpython
import numpy

@unpython.types("ndarray [ float 2 ]","ndarray [ float 2 ]", "ndarray [ float 2 ]","int","int")
def float32mult(A,B,C,n):
	for i in unpython.gpurange(n):
		for j in unpython.gprange(n):
			sum = float32(0.0)
			for k in range(n):
				sum += A[i,k]*B[k,j]
			C[i,j] = sum
	return 0

