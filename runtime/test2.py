import test
import time
from numpy import *
A = zeros(100,100)

t1 = time.time()
test.float32mult(A,B,C,100)
t2 = time.time()